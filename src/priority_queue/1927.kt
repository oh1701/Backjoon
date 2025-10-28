package priority_queue

import java.util.PriorityQueue

/**
 * 널리 잘 알려진 자료구조 중 최소 힙이 있다. 최소 힙을 이용하여 다음과 같은 연산을 지원하는 프로그램을 작성하시오.
 *
 * 배열에 자연수 x를 넣는다.
 * 배열에서 가장 작은 값을 출력하고, 그 값을 배열에서 제거한다.
 * 프로그램은 처음에 비어있는 배열에서 시작하게 된다.
 *
 * 입력
 * 첫째 줄에 연산의 개수 N(1 ≤ N ≤ 100,000)이 주어진다. 다음 N개의 줄에는 연산에 대한 정보를 나타내는 정수 x가 주어진다.
 * 만약 x가 자연수라면 배열에 x라는 값을 넣는(추가하는) 연산이고, x가 0이라면 배열에서 가장 작은 값을 출력하고 그 값을 배열에서 제거하는 경우이다.
 * x는 2^31보다 작은 자연수 또는 0이고, 음의 정수는 입력으로 주어지지 않는다.
 *
 * 출력
 * 입력에서 0이 주어진 횟수만큼 답을 출력한다. 만약 배열이 비어 있는 경우인데 가장 작은 값을 출력하라고 한 경우에는 0을 출력하면 된다.
 *
 * 예제 입력 1
 * 9
 * 0
 * 12345678
 * 1
 * 2
 * 0
 * 0
 * 0
 * 0
 * 32
 * 예제 출력 1
 * 0
 * 1
 * 2
 * 12345678
 * 0
 * */

private val stringBuilder = StringBuilder()
private val heap = mutableListOf<Int>()

fun `1927-최소 힙-List 구현`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt()

    repeat(n){
        val num = br.readLine().toInt()

        if(num == 0){
            stringBuilder.appendLine(heapDelete())
        } else {
            heapInsert(num)
        }
    }

    bw.write(stringBuilder.trim().toString())
    bw.flush()
    bw.close()
    br.close()
}

private fun heapInsert(num: Int){
    heap.add(num)
    siftUp()
}

private fun heapDelete(): Int {
    return if(heap.isEmpty()) {
        0
    } else {
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
    var currentIdx = heap.lastIndex

    while (currentIdx > 0){
        val parentIdx = (currentIdx - 1) / 2

        if(heap[currentIdx] < heap[parentIdx]){
            val temp = heap[parentIdx]
            heap[parentIdx] = heap[currentIdx]
            heap[currentIdx] = temp
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
    var currentIdx = 0
    val lastIdx = heap.lastIndex

    while (true){
        val leftChildIdx = currentIdx * 2 + 1
        val rightChildIdx = currentIdx * 2 + 2
        var smallestIdx = currentIdx

        if(leftChildIdx <= lastIdx && heap[leftChildIdx] < heap[smallestIdx]){
            smallestIdx = leftChildIdx
        }

        if(rightChildIdx <= lastIdx && heap[rightChildIdx] < heap[smallestIdx]){
            smallestIdx = rightChildIdx
        }

        if(smallestIdx != currentIdx){
            val temp = heap[currentIdx]
            heap[currentIdx] = heap[smallestIdx]
            heap[smallestIdx] = temp
        } else {
            break
        }

        currentIdx = smallestIdx
    }
}


fun `1927-최소 힙-PriorityQueue 구현`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt()
    val priorityQueue = PriorityQueue<Int>()

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