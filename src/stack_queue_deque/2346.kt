package stack_queue_deque

import java.util.StringTokenizer

/**
 * 1번부터 N번까지 N개의 풍선이 원형으로 놓여 있고.
 * i번 풍선의 오른쪽에는 i+1번 풍선이 있고, 왼쪽에는 i-1번 풍선이 있다.
 * 단, 1번 풍선의 왼쪽에 N번 풍선이 있고, N번 풍선의 오른쪽에 1번 풍선이 있다.
 * 각 풍선 안에는 종이가 하나 들어있고, 종이에는 -N보다 크거나 같고, N보다 작거나 같은 정수가 하나 적혀있다.
 * 이 풍선들을 다음과 같은 규칙으로 터뜨린다.
 *
 * 우선, 제일 처음에는 1번 풍선을 터뜨린다.
 * 다음에는 풍선 안에 있는 종이를 꺼내어 그 종이에 적혀있는 값만큼 이동하여 다음 풍선을 터뜨린다.
 * 양수가 적혀 있을 경우에는 오른쪽으로, 음수가 적혀 있을 때는 왼쪽으로 이동한다.
 * 이동할 때에는 이미 터진 풍선은 빼고 이동한다.
 *
 * 예를 들어 다섯 개의 풍선 안에 차례로 3, 2, 1, -3, -1이 적혀 있었다고 하자.
 * 이 경우 3이 적혀 있는 1번 풍선, -3이 적혀 있는 4번 풍선, -1이 적혀 있는 5번 풍선,
 * 1이 적혀 있는 3번 풍선, 2가 적혀 있는 2번 풍선의 순서대로 터지게 된다.
 *
 * 입력
 * 첫째 줄에 자연수 N(1 ≤ N ≤ 1,000)이 주어진다.
 * 다음 줄에는 차례로 각 풍선 안의 종이에 적혀 있는 수가 주어진다.
 * 종이에 0은 적혀있지 않다.
 *
 * 출력
 * 첫째 줄에 터진 풍선의 번호를 차례로 나열한다.
 *
 * 예제 입력 1
 * 5
 * 3 2 1 -3 -1
 *
 * 예제 출력 1
 * 1 4 5 3 2
 * */

// 해당 문제는 코틀린에서는 ArrayDeque 를 사용하면 메모리 초과가 발생하여 MutableList 로 사용해야한다고 한다..
fun `2346-풍선 터뜨리기`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt()
    val token = StringTokenizer(br.readLine())
    val deque = MutableList(n){ it + 1 to token.nextToken().toInt()}
    val stringBuilder = StringBuilder()

    while (true){
        val (index, move) = deque.removeFirst()
        stringBuilder.append("$index ")

        if(deque.isEmpty()) break

        /*
        * [1, 3], [2, 2], [3, 1], [4, -3], [5, -1]
        *
        * 1번째 순서 (1)
        * [1, 3] 제거 -> [2, 2], [3, 1], [4, -3], [5, -1]. 앞에서 뒤로
        * 1번 이동 -> [3, 1], [4, -3], [5, -1], [2, 2]
        * 2번 이동 -> [4, -3], [5, -1], [2, 2], [3, 1]
        *
        * 2번째 순서 (4)
        * [4, -3] 제거 -> [5, -1], [2, 2], [3, 1]. 역순이니 뒤에서 앞으로
        * 1번 이동 -> [3, 1], [5, -1], [2, 2]
        * 2번 이동 -> [2, 2], [3, 1], [5, -1]
        * 3번 이동 -> [5, -1], [2, 2], [3, 1] 순서
        * */

        if(move > 0){
            // 첫 순번 풍선은 무조건 터지므로 removeFirst() 는 move - 1번만 실행
            repeat(move - 1){ deque.add(deque.removeFirst()) }
        } else {
            // 첫 순번 풍선이 무조건 터지지만 removeFirst 가 아닌 removeLast() 이므로 move 만큼 실행
            repeat(-move){ deque.add(0, deque.removeLast() )}
        }
    }

    bw.write(stringBuilder.toString())
    bw.flush()
    bw.close()
    br.close()
}