package divisors_multiples_primes

import kotlin.math.pow
import kotlin.math.sqrt

/**
 * 정수 n(0 ≤ n ≤ 4*10의9승)가 주어졌을 때, n보다 크거나 같은 소수 중 가장 작은 소수 찾는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 테스트 케이스의 개수가 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있고, 정수 n이 주어진다.
 *
 * 출력
 * 각각의 테스트 케이스에 대해서 n보다 크거나 같은 소수 중 가장 작은 소수를 한 줄에 하나씩 출력한다.
 *
 * 예제 입력 1
 * 3
 * 6
 * 20
 * 100
 * 예제 출력 1
 * 7
 * 23
 * 101
 * */
fun `4134-다음 소수`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val stringBuilder = StringBuilder()

    repeat(br.readLine().toInt()){
        var n = br.readLine().toLong()

        while (true){
            if(isPrime(n)){
                stringBuilder.appendLine(n.toString())
                break
            }
            n++
        }
    }

    bw.write(stringBuilder.toString())
    bw.flush()
    bw.close()
    br.close()
}

// 쉽게 2 .. sqrt(n.toDouble()).toLong().none 으로 확인해도 된다
private fun isPrime(n: Long): Boolean {
    if (n <= 1) return false
    if (n <= 3L) return true
    if (n % 2L == 0L) return false  // 2로 나눠지는 짝수들은 소수가 아니므로 걸러냄

    val sqrt = sqrt(n.toDouble()).toLong()
    for (i in 3..sqrt step 2) {
        if (n % i == 0L) return false
    }
    return true
}