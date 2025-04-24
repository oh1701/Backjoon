package divisors_multiples_primes

import java.util.StringTokenizer

/**
 * 두 자연수 A와 B에 대해서, A의 배수이면서 B의 배수인 자연수를 A와 B의 공배수라고 한다.
 * 이런 공배수 중에서 가장 작은 수를 최소공배수라고 한다.
 *
 * 예를 들어, 6과 15의 공배수는 30, 60, 90등이 있으며, 최소 공배수는 30이다.
 *
 * 두 자연수 A와 B가 주어졌을 때, A와 B의 최소공배수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 테스트 케이스의 개수 T(1 ≤ T ≤ 1,000)가 주어진다. 둘째 줄부터 T개의 줄에 걸쳐서 A와 B가 주어진다. (1 ≤ A, B ≤ 45,000)
 *
 * 출력
 * 첫째 줄부터 T개의 줄에 A와 B의 최소공배수를 입력받은 순서대로 한 줄에 하나씩 출력한다.
 *
 * 예제 입력 1
 * 3
 * 1 45000
 * 6 10
 * 13 17
 * 예제 출력 1
 * 45000
 * 30
 * 221
 * */
fun `1934-최소공배수`(){
    // 소인수 분해 방식과 유클리드 호제법으로 최대공약수 구한 후 최소공배수 구하기 방식 두 가지가 존재
    // 소인수 분해 -> 최소값으로 계속 나누고 나눈값들 및 몫을 곱합
    // 유클리드 호제법 -> a, b = b, a % b 무한 반복하여 우항이 0이 될때 좌항이 최대공약수. 이후 a * b / 최대공약수
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val repeatSize = br.readLine().toInt()
    val stringBuilder = StringBuilder()

    repeat(repeatSize){
        val token = StringTokenizer(br.readLine())
        val x = token.nextToken().toInt()
        val y = token.nextToken().toInt()
        var divisorX = x
        var divisorY = y

        while (divisorY != 0){
            val previousY = divisorY
            divisorY = divisorX % divisorY
            divisorX = previousY
        }

        val z = divisorX
        stringBuilder.appendLine(x * y / z)
    }

    bw.write(stringBuilder.toString())
    bw.flush()
    bw.close()
    br.close()
}