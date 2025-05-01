package recursion

import java.math.BigInteger

/**
 * 0보다 크거나 같은 정수 N이 주어진다. 이때, N!을 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 정수 N(0 ≤ N ≤ 20)이 주어진다.
 *
 * 출력
 * 첫째 줄에 N!을 출력한다.
 *
 * 예제 입력 1
 * 10
 * 예제 출력 1
 * 3628800
 * 예제 입력 2
 * 0
 * 예제 출력 2
 * 1
 * */
fun `27433-팩토리얼 2`(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    println(factorial(n, BigInteger.valueOf(1L)))
}

private tailrec fun factorial(i: Int, acc: BigInteger): BigInteger{
    return if(i == 0) acc else factorial(i - 1, acc * BigInteger.valueOf(i.toLong()))
}