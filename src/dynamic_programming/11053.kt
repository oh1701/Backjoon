package dynamic_programming

import kotlin.math.abs
import kotlin.math.min

/**
 * 문제
 * 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
 *
 * 예를 들어
 * 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에
 * 가장 긴 증가하는 부분수열은 A = {(10), (20), 10, (30), 20, (50)} 이고, 길이는 4이다.
 *
 *
 *
 * 입력
 * 첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.
 *
 * 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)
 *
 * 출력
 * 첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.
 *
 * 예제 입력 1
 * 6
 * 10 20 10 30 20 50
 * 예제 출력 1
 * 4
 *
 * 예제 입력 2
 * 9
 * 10 20 10 30 25 27 40 30 50
 * 예제 출력 2
 * 5
 * */
fun `11053-가장 긴 증가하는 부분 수열`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt()
    val token = java.util.StringTokenizer(br.readLine())
    val nArr = IntArray(n){ token.nextToken().toInt() }
    val tails = mutableListOf<Int>()

    for(target in nArr){
        // 찾으려는 인덱스
        var index = tails.gyuBinarySearch(target)

        if(index < 0){
            index = -(index + 1)
        }
        // 만약 삽입점이 리스트의 끝이라면, num이 가장 크다는 의미.
        // LIS의 길이가 1 늘어난다.
        if (index == tails.size) {
            tails.add(target)
        } else {
            // 그렇지 않다면, 기존의 값을 더 작은 num으로 교체하여
            // 미래에 더 긴 수열을 만들 수 있는 "잠재력"을 높인다.
            tails[index] = target
        }
    }

    bw.write(tails.size.toString())
    bw.flush()
    bw.close()
    br.close()
}

private fun List<Int>.gyuBinarySearch(target: Int): Int {
    var start = 0
    var end = this.lastIndex

    while(start <= end){
        val mid = start + (end - start) / 2

        when {
            this[mid] == target -> return mid
            this[mid] < target -> start = mid + 1
            else -> end = mid - 1
        }
    }

    return -(start + 1)
}