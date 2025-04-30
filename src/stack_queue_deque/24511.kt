package stack_queue_deque

import java.io.BufferedReader
import java.util.*

/*
* X(N - 1) 를 N 번 자료구조에 삽입한 뒤 N번 자료구조에서 pop 하고 X(N) 을 return 하는 문제
* 단, 스택과 큐가 존재하므로 선입선출 or 후입선출 방식이 나뉘어진다
*
* 도현이는 길이 M의 수열 C를 가져와서 수열의 원소를 앞에서부터 차례대로 queuestack에 삽입할 것이다.
* 이전에 삽입한 결과는 남아 있다.
*
* 예제로
* 초기값 1, 2, 3, 4
* 넣는거 2, 4, 7
* */
fun `24511-queuestack`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt()
    val a = br.getSequence() // 0 = 큐, 1 = 스택
    val b = br.getSequence() // 초기값
    val m = br.readLine().toInt()
    val c = br.getSequence() // 삽입 값들
    val stringBuilder = StringBuilder()

    // 큐만 모아서 하나의 덱으로 관리
    // X0 -> X1 -> X2 -> Xn .. 순으로 가지만 스택의 경우 큐에서 Deque 된 값을 그대로 Pop 하기 때문에 Queue 만 기록
    val queue = ArrayDeque<Int>()
    for (i in 0..< n) {
        // 큐라면 queue 에 해당 큐의 초기값들을 저장
        if (a[i] == 0) queue.addLast(b[i])
    }

    for(x in c){
        queue.addFirst(x)
        stringBuilder.append("${queue.removeLast()} ")
    }

    bw.write(stringBuilder.toString())
    bw.flush()
    bw.close()
    br.close()
}

private fun BufferedReader.getSequence() = readLine().split(" ").map { it.toInt() }