package priority_queue

import java.util.PriorityQueue
import java.util.StringTokenizer

/**
 * 문제
 * 어떤 수열을 읽고, 홀수번째 수를 읽을 때 마다, 지금까지 입력받은 값의 중앙값을 출력하는 프로그램을 작성하시오.
 *
 * 예를 들어, 수열이 1, 5, 4, 3, 2 이면,
 * 홀수번째 수는 1번째 수, 3번째 수, 5번째 수이고, 1번째 수를 읽었을 때
 * 중앙값은 1, 3번째 수를 읽었을 때는 4, 5번째 수를 읽었을 때는 3이다.
 *
 * 입력
 * 첫째 줄에 테스트 케이스의 개수 T(1 ≤ T ≤ 1,000)가 주어진다.
 * 각 테스트 케이스의 첫째 줄에는 수열의 크기 M(1 ≤ M ≤ 9999, M은 홀수)이 주어지고,
 * 그 다음 줄부터 이 수열의 원소가 차례대로 주어진다.
 * 원소는 한 줄에 10개씩 나누어져있고, 32비트 부호있는 정수이다.
 *
 * 출력
 * 각 테스트 케이스에 대해 첫째 줄에 출력하는 중앙값의 개수를 출력하고,
 * 둘째 줄에는 홀수 번째 수를 읽을 때 마다 구한 중앙값을 차례대로 공백으로 구분하여 출력한다.
 * 이때, 한 줄에 10개씩 출력해야 한다.
 *
 * 예제 입력 1
 * 3
 * 9
 * 1 2 3 4 5 6 7 8 9
 * 9
 * 9 8 7 6 5 4 3 2 1
 * 23
 * 23 41 13 22 -3 24 -31 -11 -8 -7
 * 3 5 103 211 -311 -45 -67 -73 -81 -99
 * -33 24 56
 *
 * 예제 출력 1
 * 5
 * 1 2 3 4 5
 * 5
 * 9 8 7 6 5
 * 12
 * 23 23 22 22 13 3 5 5 3 -3
 * -7 -3
 * */

/*
* [입력]
* 홀 수 번째 읽는 경우 내용의 중앙 값을 출력.
* 첫째줄 - 테이스 케이스 개수 T
*
* 각 테스트 케이스 별
* 첫째 줄 - 수열의 크기 1 <= M(홀수) <= 9999
* 둘째 줄 - 수열의 원소 (한 줄에 최대 10개, 32비트 부호있는 정수)
*
* [출력]
* 첫째 줄 - 출력하는 중앙값의 개수
* 둘째 줄 - 홀수 번째 읽을 때마다 구한 중앙값 차례대로 공백으로 구분 출력 (최대 10줄)
*
* */
fun `2696-중앙값 구하기`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val t = br.readLine().toInt()
    val stringBuilder = StringBuilder()

    repeat(t){ repeat ->
        val m = br.readLine().toInt()
        val maxHeap = PriorityQueue<Int>(compareByDescending { it })
        val minHeap = PriorityQueue<Int>()
        var appendedCount = 0
        var stringToken: StringTokenizer? = null

        if(repeat > 0) stringBuilder.appendLine()
        stringBuilder.appendLine("${m / 2 + 1}")

        for(i in 0 until m){
            if(i % 10 == 0) stringToken = StringTokenizer(br.readLine())
            if(stringToken == null) break

            val num = stringToken.nextToken().toInt()

            if(maxHeap.size == minHeap.size){
                maxHeap.offer(num)
            } else {
                minHeap.offer(num)
            }

            if(minHeap.isNotEmpty() && maxHeap.peek() > minHeap.peek()){
                val swapMax = maxHeap.poll()
                val swapMin = minHeap.poll()

                maxHeap.offer(swapMin)
                minHeap.offer(swapMax)
            }

            // 0 based Index 이므로 0이 남으면 홀수임
            if(i % 2 == 0){
                stringBuilder.append("${maxHeap.peek()} ")
                appendedCount++
            }

            // 추가가 10번 되었으면 한 라인 띄우기
            if(appendedCount == 10){
                stringBuilder.appendLine()
                appendedCount = 0
            }
        }
    }

    bw.write(stringBuilder.toString())
    bw.flush()
    bw.close()
    br.close()
}