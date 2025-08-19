package dynamic_programming

import java.util.StringTokenizer

/**
 * 수열 S가 어떤 수 Sk를 기준으로 S1 < S2 < ... Sk-1 < Sk > Sk+1 > ... SN-1 > SN을 만족한다면, 그 수열을 바이토닉 수열이라고 한다.
 *
 * 예를 들어, {10, 20, 30, 25, 20}과 {10, 20, 30, 40}, {50, 40, 25, 10} 은 바이토닉 수열이지만,
 * {1, 2, 3, 2, 1, 2, 3, 2, 1}과 {10, 20, 30, 40, 20, 30} 은 바이토닉 수열이 아니다.
 *
 * 수열 A가 주어졌을 때, 그 수열의 부분 수열 중 바이토닉 수열이면서 가장 긴 수열의 길이를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 수열 A의 크기 N이 주어지고, 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ N ≤ 1,000, 1 ≤ Ai ≤ 1,000)
 *
 * 출력
 * 첫째 줄에 수열 A의 부분 수열 중에서 가장 긴 바이토닉 수열의 길이를 출력한다.
 *
 * 예제 입력 1
 * 10
 * 1 5 2 1 4 3 4 5 2 1
 * 예제 출력 1
 * 7
 * */
fun `11054-가장 긴 바이토닉 수열`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt()
    val nToken = StringTokenizer(br.readLine())
    val arr = IntArray(n){ nToken.nextToken().toInt() }
    val increaseDp = getLIS(arr, n)
    val decreaseDp = getLIS(arr.reversedArray(), n).reversedArray()

    var maxLen = 0
    for (i in 0 until n) {
        maxLen = kotlin.math.max(maxLen, increaseDp[i] + decreaseDp[i] - 1)
    }

    bw.write(maxLen.toString())
    bw.flush()
    bw.close()
    br.close()
}

private fun getLIS(arr: IntArray, n: Int): IntArray {
    val dp = IntArray(n)
    val tails = mutableListOf<Int>()

    for(i in arr.indices){
        val target = arr[i]
        var index = tails.binarySearch(target)

        if(index < 0){
            index = -(index + 1)
        }

        if(tails.size == index){
            tails.add(target)
        } else {
            tails[index] = target
        }

        dp[i] = index + 1
    }

    return dp
}