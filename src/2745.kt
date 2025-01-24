import java.util.StringTokenizer
import kotlin.math.pow

/*
B진법 수 N이 주어진다. 이 수를 10진법으로 바꿔 출력하는 프로그램을 작성하시오.

10진법을 넘어가는 진법은 숫자로 표시할 수 없는 자리가 있다. 이런 경우에는 다음과 같이 알파벳 대문자를 사용한다.

A: 10, B: 11, ..., F: 15, ..., Y: 34, Z: 35

입력
첫째 줄에 N과 B가 주어진다. (2 ≤ B ≤ 36)

B진법 수 N을 10진법으로 바꾸면, 항상 10억보다 작거나 같다.

출력
첫째 줄에 B진법 수 N을 10진법으로 출력한다.

예제 입력 1
ZZZZZ 36
예제 출력 1
60466175
*/

// 88ms 소모
fun `2745-진법변환`(){
    /*
    기존코드. 코드가 쓸데없이 길다.
    채점 시간을 더 빠르게 나옴. 76ms 소모
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val token = StringTokenizer(br.readLine())
    val n = token.nextToken().toString()
    val b = token.nextToken().toString()
    var sum = 0

    n.forEachIndexed { index, char ->
        // c 아스키코드에서 A 를 빼면 알파벳 순서가 나옴. 이곳에 숫자 10을 더해 A 가 10 부터 시작인것을 알려줌
        val num: Int = if(char in 'A'..'Z'){
            char - 'A' + 10
        } else {
            // char.code 는 ASCII 코드로 변환하기 때문에 아래처럼 설정해줘야 숫자값으로 나온다.
            char - '0'
        }

        // 0승부터 시작하므로 -1 추가
        val multiple = n.length - index - 1

        // 35 * 36의 4승, 35 * 36의 3승 ..
        sum += num * b.toDouble().pow(multiple.toDouble()).toInt()
    }

    bw.write(sum.toString())
    bw.flush()
    bw.close()
    br.close()
    * */

    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val token = StringTokenizer(br.readLine())
    val n = token.nextToken().toString()
    val b = token.nextToken().toInt()

    // String.toInt() 에 radix. 즉 진법을 넣어 바로 변환하도록 설정
    bw.write(n.toInt(b).toString())
    bw.flush()
    bw.close()
    br.close()
}
