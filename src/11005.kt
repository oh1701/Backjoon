import java.util.StringTokenizer

/*
10진법 수 N이 주어진다. 이 수를 B진법으로 바꿔 출력하는 프로그램을 작성하시오.

10진법을 넘어가는 진법은 숫자로 표시할 수 없는 자리가 있다. 이런 경우에는 다음과 같이 알파벳 대문자를 사용한다.

A: 10, B: 11, ..., F: 15, ..., Y: 34, Z: 35

입력
첫째 줄에 N과 B가 주어진다. (2 ≤ B ≤ 36) N은 10억보다 작거나 같은 자연수이다.

출력
첫째 줄에 10진법 수 N을 B진법으로 출력한다.

예제 입력 1
60466175 36
예제 출력 1
ZZZZZ
*/

fun `11005-진법변환2`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val token = StringTokenizer(br.readLine())
    val n = token.nextToken().toInt()
    val b = token.nextToken().toInt()

    // Integer.toString 을 통해 10진법 n 을 b 진법으로 변환 후 대문자로 변환
    bw.write(Integer.toString(n, b).uppercase())
    bw.flush()
    bw.close()
    br.close()
}