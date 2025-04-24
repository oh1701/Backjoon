package divisors_multiples_primes

import java.util.StringTokenizer
import kotlin.math.sqrt

/**
 * M이상 N이하의 소수를 모두 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 자연수 M과 N이 빈 칸을 사이에 두고 주어진다. (1 ≤ M ≤ N ≤ 1,000,000) M이상 N이하의 소수가 하나 이상 있는 입력만 주어진다.
 *
 * 출력
 * 한 줄에 하나씩, 증가하는 순서대로 소수를 출력한다.
 *
 * 예제 입력 1
 * 3 16
 * 예제 출력 1
 * 3
 * 5
 * 7
 * 11
 * 13
 * */
fun `1929-소수 구하기`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val token = StringTokenizer(br.readLine())
    val m = token.nextToken().toInt()
    val n = token.nextToken().toInt()
    val stringbuilder = StringBuilder()

    // 0, 3
    for(i in m .. n){
        if(isPrime(i)){
            stringbuilder.appendLine(i.toString())
        }
    }

    bw.write(stringbuilder.toString())
    bw.flush()
    bw.close()
    br.close()
}

private fun isPrime(value: Int): Boolean {
    return when {
        value == 1-> false
        value <= 3 -> true
        value % 2 == 0 -> false
        else -> {
            (3 .. sqrt(value.toDouble()).toInt()).step(2).none { value % it == 0 }
        }
    }
}