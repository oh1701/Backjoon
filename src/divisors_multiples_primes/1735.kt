package divisors_multiples_primes

import java.util.StringTokenizer

/**
 * 분수 A/B는 분자가 A, 분모가 B인 분수를 의미한다. A와 B는 모두 자연수라고 하자.
 *
 * 두 분수의 합 또한 분수로 표현할 수 있다. 두 분수가 주어졌을 때, 그 합을 기약분수의 형태로 구하는 프로그램을 작성하시오.
 * 기약분수란 더 이상 약분되지 않는 분수를 의미한다.
 *
 * 입력
 * 첫째 줄과 둘째 줄에, 각 분수의 분자와 분모를 뜻하는 두 개의 자연수가 순서대로 주어진다. 입력되는 네 자연수는 모두 30,000 이하이다.
 *
 * 출력
 * 첫째 줄에 구하고자 하는 기약분수의 분자와 분모를 뜻하는 두 개의 자연수를 빈 칸을 사이에 두고 순서대로 출력한다.
 *
 * 예제 입력 1
 * 2 7
 * 3 5
 * 예제 출력 1
 * 31 35
 * */
fun `1735-분수합`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val xToken = StringTokenizer(br.readLine())
    val yToken = StringTokenizer(br.readLine())
    val xNumerator = xToken.nextToken().toInt()
    val xDenominator = xToken.nextToken().toInt()

    val yNumerator = yToken.nextToken().toInt()
    val yDenominator = yToken.nextToken().toInt()

    val numerator = xNumerator * yDenominator + xDenominator * yNumerator
    val denominator = xDenominator * yDenominator
    val gcd = gcd(numerator, denominator)

    bw.write("${numerator / gcd} ${denominator / gcd}")
    bw.flush()
    bw.close()
    br.close()
}

private tailrec fun gcd(x: Int, y: Int): Int = if(y == 0) x else gcd(y, x % y)