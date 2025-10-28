package priority_queue

import java.util.PriorityQueue
import java.util.StringTokenizer

fun `2075-N번째 큰 수-Heap`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt()
    val heap = PriorityQueue<Int>(n)

    repeat(n){
        val token = StringTokenizer(br.readLine())

        repeat(n){
            val num = token.nextToken().toInt()

            when {
                heap.size >= n && heap.peek() >= num -> {}
                heap.size >= n && heap.peek() < num -> {
                    heap.poll()
                    heap.offer(num)
                }
                else -> heap.offer(num)
            }
        }
    }

    bw.write(heap.peek().toString())
    bw.flush()
    bw.close()
    br.close()
}

fun `2075-N번째 큰 수-Parametric Search`(){
    var result = 0
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt()
    var low = -1_000_000_000
    var high = 1_000_000_000
    val arr = Array(n){
        val token = StringTokenizer(br.readLine())
        IntArray(n){ token.nextToken().toInt() }
    }

    while (low <= high){
        var count = 0
        val mid = low + (high - low) / 2

        loop@
        for(j in 0 until n){
            var iLow = 0
            var iHigh = n - 1
            var firstIndex = n

            while (iLow <= iHigh) {
                val iMid = iLow + (iHigh - iLow) / 2

                if (arr[iMid][j] >= mid) {
                    firstIndex = iMid
                    iHigh = iMid - 1
                } else {
                    iLow = iMid + 1
                }
            }

            val remainder = n - firstIndex
            count += remainder

            if(count > n){
                break@loop
            }
        }

        if(count >= n){
            low = mid + 1
            result = mid
        } else {
            high = mid - 1
        }
    }

    bw.write(result.toString())
    bw.flush()
    bw.close()
    br.close()
}