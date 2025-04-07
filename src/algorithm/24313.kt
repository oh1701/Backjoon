package algorithm

import java.util.StringTokenizer

/**
 * 오늘도 서준이는 점근적 표기 수업 조교를 하고 있다. 아빠가 수업한 내용을 학생들이 잘 이해했는지 문제를 통해서 확인해보자.
 *
 * 알고리즘의 소요 시간을 나타내는 O-표기법(빅-오)을 다음과 같이 정의하자.
 *
 * O(g(n)) = {f(n) | 모든 n ≥ n0에 대하여 f(n) ≤ c × g(n)인 양의 상수 c와 n0가 존재한다}
 *
 * 이 정의는 실제 O-표기법(https://en.wikipedia.org/wiki/Big_O_notation)과 다를 수 있다.
 *
 * 함수 f(n) = a1n + a0, 양의 정수 c, n0가 주어질 경우 O(n) 정의를 만족하는지 알아보자.
 *
 * 입력
 * 첫째 줄에 함수 f(n)을 나타내는 정수 a1, a0가 주어진다. (0 ≤ |ai| ≤ 100)
 *
 * 다음 줄에 양의 정수 c가 주어진다. (1 ≤ c ≤ 100)
 *
 * 다음 줄에 양의 정수 n0가 주어진다. (1 ≤ n0 ≤ 100)
 *
 * 출력
 * f(n), c, n0가 O(n) 정의를 만족하면 1, 아니면 0을 출력한다.
 *
 * 예제 입력 1
 * 7 7
 * 8
 * 1
 * 예제 출력 1
 * 0
 * f(n) = 7n + 7, g(n) = n, c = 8, n0 = 1이다. f(1) = 14, c × g(1) = 8이므로 O(n) 정의를 만족하지 못한다.
 *
 * 예제 입력 2
 * 7 7
 * 8
 * 10
 * 예제 출력 2
 * 1
 * f(n) = 7n + 7, g(n) = n, c = 8, n0 = 10이다. 모든 n ≥ 10에 대하여 7n + 7 ≤ 8n 이므로 O(n) 정의를 만족한다.
 *
 */

/*
* 찾아야 하는 것이 O(n) 이므로 g(n) 은 n
* 첫줄 입력은 f(n) 을 구하는 공식. x(n) + x
* 둘째줄은 c
* 셋째줄은 n0
* */

fun `24313-알고리즘 수업 - 점근적 표기 1`(){
    val bw = System.out.bufferedWriter()
    val token = StringTokenizer(readln())

    val a1 = token.nextToken().toInt()
    val a0 = token.nextToken().toInt()
    val c = readln().toInt()
    val n0 = readln().toInt()

    val fn = a1 * n0 + a0 // 모든 n 은 n0 보다 크거나 같으므로 n 을 n0 로 대입
    val cn = c * n0

    if(fn <= cn && a1 <= c){
        bw.write("1")
    } else {
        bw.write("0")
    }

    bw.close()
}