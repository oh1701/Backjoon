package divide_and_conquer_algorithm

import java.util.StringTokenizer

/**
 * 자연수 A를 B번 곱한 수를 알고 싶다.
 * 단 구하려는 수가 매우 커질 수 있으므로 이를 C로 나눈 나머지를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 A, B, C가 빈 칸을 사이에 두고 순서대로 주어진다. A, B, C는 모두 2,147,483,647 이하의 자연수이다.
 *
 * 출력
 * 첫째 줄에 A를 B번 곱한 수를 C로 나눈 나머지를 출력한다.
 *
 * 예제 입력 1
 * 10 11 12
 * 예제 출력 1
 * 4
 * */

/*
* A^B 일때
* B 가 짝수인경우 -> A^B/2 * A^B/2
* B 가 홀수인경우 -> A * A^(B-1) -> A * A^B/2 * A^B/2
*
* 추가로, 지수법칙 및 모듈러 분배법칙 사용하면 쉽게 구해줄 수 있다.
* A^B % C -> A * A^(B-1) % C -> ((A % C) * (A^B-1 %)) % C
* */


fun `1629-곱셈`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val token = StringTokenizer(br.readLine())
    val a = token.nextToken().toLong()
    val b = token.nextToken().toLong()
    val c = token.nextToken().toLong()

    bw.write(power(a, b, c).toString())
    bw.flush()
    bw.close()
    br.close()
}

private fun power(a: Long, b: Long, c: Long): Long {
    if(b == 1L){
        return a % c
    }
    // B를 반으로 나눈 문제부터 푼다
    val half = power(a, b / 2, c)

    // 그 결과를 제곱하여 짝수 경우의 답을 미리 계산해 둔다.
    val temp = (half * half) % c

    // 홀수면 a * 짝수경우 이므로 * a 를 추가해준다.
    // * a 를 해서 c 보다 커질 수 있으므로 % c 도 다시 진행
    return if (b % 2 == 1L) {
        (temp * a) % c
    } else {
        temp
    }
}