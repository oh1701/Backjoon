package combinatorics

import java.util.StringTokenizer

/**
 * 예제 입력 1
 * 5 2
 * 예제 출력 1
 * 10
 *
 */
fun `11050-이항 계수`(){
    val br = System.`in`.bufferedReader()
    val token = StringTokenizer(br.readLine())
    val n = token.nextToken().toInt()
    val k = token.nextToken().toInt()
    // 이항 계수 공식 사용. n! / (k! * (n - k)!)
    println(factorial(n) / (factorial(k) * factorial(n - k)))
}

private tailrec fun factorial(n: Int, acc: Int = 1): Int {
    return if (n == 0) acc
    else factorial(n - 1, acc * n)
}