package priority_queue

import java.util.PriorityQueue

/**
 * 널리 잘 알려진 자료구조 중 최대 힙이 있다. 최대 힙을 이용하여 다음과 같은 연산을 지원하는 프로그램을 작성하시오.
 *
 * 배열에 자연수 x를 넣는다.
 * 배열에서 가장 큰 값을 출력하고, 그 값을 배열에서 제거한다.
 * 프로그램은 처음에 비어있는 배열에서 시작하게 된다.
 *
 * 입력
 * 첫째 줄에 연산의 개수 N(1 ≤ N ≤ 100,000)이 주어진다. 다음 N개의 줄에는 연산에 대한 정보를 나타내는 정수 x가 주어진다.
 * 만약 x가 자연수라면 배열에 x라는 값을 넣는(추가하는) 연산이고,
 * x가 0이라면 배열에서 가장 큰 값을 출력하고 그 값을 배열에서 제거하는 경우이다.
 * 입력되는 자연수는 2^31보다 작다.
 *
 * 출력
 * 입력에서 0이 주어진 횟수만큼 답을 출력한다. 만약 배열이 비어 있는 경우인데 가장 큰 값을 출력하라고 한 경우에는 0을 출력하면 된다.
 *
 * 예제 입력 1
 * 13
 * 0
 * 1
 * 2
 * 0
 * 0
 * 3
 * 2
 * 1
 * 0
 * 0
 * 0
 * 0
 * 0
 * 예제 출력 1
 * 0
 * 2
 * 1
 * 3
 * 2
 * 1
 * 0
 * 0
 * */

private val heap = mutableListOf<Int>()

fun `11279-최대 힙-PriorityQueue 구현`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt()
    val stringBuilder = StringBuilder()
    val priorityQueue = PriorityQueue<Int>(compareByDescending { it })

    repeat(n){
        val num = br.readLine().toInt()

        if(num == 0){
            stringBuilder.appendLine(priorityQueue.poll() ?: 0)
        } else {
            priorityQueue.add(num)
        }
    }

    bw.write(stringBuilder.trim().toString())
    bw.flush()
    bw.close()
    br.close()
}

fun `11279-최대 힙-List 구현`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt()
    val stringBuilder = StringBuilder()

    repeat(n){
        val num = br.readLine().toInt()

        if(num > 0) {
            heapInsert(num)
        } else {
            stringBuilder.appendLine(heapDeleteFirst())
        }
    }

    bw.write(stringBuilder.trim().toString())
    bw.flush()
    bw.close()
    br.close()

}

/*
* 힙에 데이터 추가
* 부모와 현재 넣은 값을 비교하여 가장
* */
private fun heapInsert(num: Int){
    heap.add(num)
    siftUp()
}

/*
* 힙 데이터 삭제.
* */
private fun heapDeleteFirst(): Int{
    return if(heap.isEmpty()) {
        0
    } else {
        /*
        * 최대 힙이므로 가장 큰 값을 제거하려는 경우 heap[0] 가 된다.
        * 이 경우 heap[0] 를 마지막과 교환하고 제거한 후 재정렬한다.
        * */
        val first = heap[0]
        heap[0] = heap[heap.lastIndex]
        heap[heap.lastIndex] = first
        heap.removeLast()
        siftDown()
        first
    }
}

/*
* 새로운 원소를 삽입하였으므로, 시프트업을 통해 원소를 올바른 위치로 올리고 힙 자료구조 유지
* 아래 -> 위
* 부모와 자신을 비교해야한다.
* */
private fun siftUp(){
    var currentIdx = heap.size - 1

    while (currentIdx > 0){
        val parentIdx = (currentIdx - 1) / 2

        /*
        * 부모와만 비교하면 되므로 else 를 타면 break 처리
        * 최대, 최소 힙에서 자식의 순서는 상관이 없음.
        * */
        if(heap[parentIdx] < heap[currentIdx]){
            val temp = heap[currentIdx]
            heap[currentIdx] = heap[parentIdx]
            heap[parentIdx] = temp
        } else {
            break
        }

        currentIdx = parentIdx
    }
}

/*
* 힙에서 루트 값을 삭제하여 힙이 망가졌으므로, 임시로 올라온 원소를 제자리로 구성시키켜 자료구조 유지.
* 위 -> 아래.
* 부모와 자식들을 비교해야한다.
* */
private fun siftDown(){
    var currentRootIdx = 0
    val lastIdx = heap.size - 1

    while (true){
        val leftChildIdx = currentRootIdx * 2 + 1
        val rightChildIdx = currentRootIdx * 2 + 2
        /*
        * 가장 큰 Index 는 현재(루트) 인덱스
        * */
        var largestIdx = currentRootIdx

        /*
        * 왼, 오른쪽 자식과 비교하여 큰 인덱스를 가져옴
        * */
        if(leftChildIdx <= lastIdx && heap[leftChildIdx] > heap[largestIdx]){
            largestIdx = leftChildIdx
        }

        if(rightChildIdx <= lastIdx && heap[rightChildIdx] > heap[largestIdx]){
            largestIdx = rightChildIdx
        }

        /*
        largestIdx 가 바뀌었다면 자식중에서 큰 것이 있다는 것이므로 교환
        이후 currentRootIdx 를 largestIdx 로 변경하여 교환된 자식 인덱스부터 siftDown 재진행
        * */
        if(largestIdx != currentRootIdx){
            val temp = heap[currentRootIdx]
            heap[currentRootIdx] = heap[largestIdx]
            heap[largestIdx] = temp

            currentRootIdx = largestIdx
        } else {
            break
        }
    }
}