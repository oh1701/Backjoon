package stack_queue_deque

import java.util.*
import kotlin.collections.ArrayDeque

/**
 * 정수를 저장하는 덱을 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램을 작성하시오.
 *
 * 명령은 총 여덟 가지이다.
 *
 * 1 X: 정수 X를 덱의 앞에 넣는다. (1 ≤ X ≤ 100,000)
 * 2 X: 정수 X를 덱의 뒤에 넣는다. (1 ≤ X ≤ 100,000)
 * 3: 덱에 정수가 있다면 맨 앞의 정수를 빼고 출력한다. 없다면 -1을 대신 출력한다.
 * 4: 덱에 정수가 있다면 맨 뒤의 정수를 빼고 출력한다. 없다면 -1을 대신 출력한다.
 * 5: 덱에 들어있는 정수의 개수를 출력한다.
 * 6: 덱이 비어있으면 1, 아니면 0을 출력한다.
 * 7: 덱에 정수가 있다면 맨 앞의 정수를 출력한다. 없다면 -1을 대신 출력한다.
 * 8: 덱에 정수가 있다면 맨 뒤의 정수를 출력한다. 없다면 -1을 대신 출력한다.
 * 입력
 * 첫째 줄에 명령의 수 N이 주어진다. (1 ≤ N ≤ 1,000,000)
 *
 * 둘째 줄부터 N개 줄에 명령이 하나씩 주어진다.
 *
 * 출력을 요구하는 명령은 하나 이상 주어진다.
 *
 * 출력
 * 출력을 요구하는 명령이 주어질 때마다 명령의 결과를 한 줄에 하나씩 출력한다.
 *
 * 예제 입력 1
 * 11
 * 6
 * 1 3
 * 1 8
 * 7
 * 8
 * 3
 * 2 5
 * 1 2
 * 5
 * 4
 * 4
 * 예제 출력 1
 * 1
 * 8
 * 3
 * 8
 * 3
 * 5
 * 3
 * */
fun `28279-덱2`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt()
    val deque = ArrayDeque<String>()
    val stringBuilder = StringBuilder()

    repeat(n){
        val token = StringTokenizer(br.readLine())

        when (token.nextToken()){
            "1" -> deque.addFirst(token.nextToken())
            "2" -> deque.addLast(token.nextToken())
            "3" -> stringBuilder.appendLine(if(deque.isNotEmpty()) deque.removeFirst() else "-1")
            "4" -> stringBuilder.appendLine(if(deque.isNotEmpty()) deque.removeLast() else "-1")
            "5" -> stringBuilder.appendLine(deque.size)
            "6" -> stringBuilder.appendLine(if(deque.isNotEmpty()) "0" else "1")
            "7" -> stringBuilder.appendLine(if(deque.isNotEmpty()) deque.first() else "-1")
            else -> stringBuilder.appendLine(if(deque.isNotEmpty()) deque.last() else "-1")
        }
    }

    bw.write(stringBuilder.toString())
    bw.flush()
    bw.close()
    br.close()
}