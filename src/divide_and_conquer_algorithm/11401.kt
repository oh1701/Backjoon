package divide_and_conquer_algorithm

import java.util.StringTokenizer

/**
 * 자연수
 * \(N\)과 정수
 * \(K\)가 주어졌을 때 이항 계수
 * \(\binom{N}{K}\)를 1,000,000,007로 나눈 나머지를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에
 * \(N\)과
 * \(K\)가 주어진다. (1 ≤
 * \(N\) ≤ 4,000,000, 0 ≤
 * \(K\) ≤
 * \(N\))
 *
 * 출력
 *
 * \(\binom{N}{K}\)를 1,000,000,007로 나눈 나머지를 출력한다.
 *
 * 예제 입력 1
 * 5 2
 * 예제 출력 1
 * 10
 * */

/*
* 이항 계수를 구하는 공식은 다음과 같다고 한다.
* n! / (k! * (n - k)!)
* */
private const val DIV = 1_000_000_007L
private lateinit var factorialArr: LongArray

fun `11401-이항 계수3`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val token = StringTokenizer(br.readLine())
    val n = token.nextToken().toLong()
    val k = token.nextToken().toLong()

    factorialArr = LongArray(n.toInt() + 1)

    factorialArr[0] = 1
    factorialArr[1] = 1

    for(i in 2 .. n){
        factorialArr[i.toInt()] = (i * factorialArr[i.toInt() - 1]) % DIV
    }

    val denominator = factorialArr[k.toInt()] * factorialArr[(n - k).toInt()] % DIV
    /*
    * 모듈러 나눗셈 공식
    * (A / B) % p = (A * B^-1) % p
    * -> 모듈러 곱셈 역원 = (B * B^-1) % p
    * -> 페르마의 소정리 = B * B^(p-2) % p = 1
    * 즉, B^-1 = B^(p-2)
    *
    * factorialArr[n] / 이항계수 % DIV
    * -> factorialArr[n] * 이항계수^DIV-2 % p
    * -> factorialArr[n] * power(이항계수, DIV-2)
    * */
    val inverseDenominator = factorialArr[n.toInt()] * power(denominator, DIV-2) % DIV

    bw.write(inverseDenominator.toString())
    bw.flush()
    bw.close()
    br.close()
}

private fun power(a: Long, b: Long): Long {
    if(b == 1L){
        return a % DIV
    }

    val half = power(a, b / 2)
    val temp = (half * half) % DIV

    return if(b % 2 == 1L){
        (temp * a) % DIV
    } else {
        temp
    }
}