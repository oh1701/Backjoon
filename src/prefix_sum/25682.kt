package prefix_sum

import java.util.StringTokenizer
import kotlin.math.min

/**
 * 지민이는 자신의 저택에서 MN개의 단위 정사각형으로 나누어져 있는 M×N 크기의 보드를 찾았다.
 * 어떤 정사각형은 검은색으로 칠해져 있고, 나머지는 흰색으로 칠해져 있다.
 * 지민이는 이 보드를 잘라서 K×K 크기의 체스판으로 만들려고 한다.
 *
 * 체스판은 검은색과 흰색이 번갈아서 칠해져 있어야 한다.
 * 구체적으로, 각 칸이 검은색과 흰색 중 하나로 색칠되어 있고, 변을 공유하는 두 개의 사각형은 다른 색으로 칠해져 있어야 한다.
 * 따라서 이 정의를 따르면 체스판을 색칠하는 경우는 두 가지뿐이다. 하나는 맨 왼쪽 위 칸이 흰색인 경우, 하나는 검은색인 경우이다.
 *
 * 보드가 체스판처럼 칠해져 있다는 보장이 없어서, 지민이는 K×K 크기의 체스판으로 잘라낸 후에 몇 개의 정사각형을 다시 칠해야겠다고 생각했다.
 * 당연히 K×K 크기는 아무데서나 골라도 된다.
 * 지민이가 다시 칠해야 하는 정사각형의 최소 개수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 정수 N, M, K가 주어진다.
 * 둘째 줄부터 N개의 줄에는 보드의 각 행의 상태가 주어진다. B는 검은색이며, W는 흰색이다.
 *
 * 출력
 * 첫째 줄에 지민이가 잘라낸 K×K 보드를 체스판으로 만들기 위해 다시 칠해야 하는 정사각형 개수의 최솟값을 출력한다.
 *
 * 제한
 * 1 ≤ N, M ≤ 2000
 * 1 ≤ K ≤ min(N, M)
 *
 * 예제 입력 1
 * 4 4 3
 * BBBB 0 0 0 0
 * BBBB 0 0 0 0
 * BBBW 0 0 0 1
 * BBWB 0 0 1 0
 * 예제 출력 1
 * 2
 *
 * 예제 입력 2
 * 8 8 8
 * WBWBWBWB 1 0 1 0 1 0 1 0 -> 0 0 0 0 0 0 0 0
 * BWBWBWBW 0 1 0 1 0 1 0 1 -> 0 0 0 0 0 0 0 0
 * WBWBWBWB 1 0 1 0 1 0 1 0 -> 0 0 0 0 0 0 0 0
 * BWBBBWBW 0 1 0 0 0 1 0 1 -> 0 0 0 1 0 0 0 0
 * WBWBWBWB 1 0 1 0 1 0 1 0 -> 0 0 0 0 0 0 0 0
 * BWBWBWBW 0 1 0 1 0 1 0 1 -> 0 0 0 0 0 0 0 0
 * WBWBWBWB 1 0 1 0 1 0 1 0 -> 0 0 0 0 0 0 0 0
 * BWBWBWBW 0 1 0 1 0 1 0 1 -> 0 0 0 0 0 0 0 0
 * 예제 출력 2
 * 1
 * */

fun `25682-체스판 다시 칠하기2`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val token = StringTokenizer(br.readLine())
    val n = token.intToken()
    val m = token.intToken()
    val k = token.intToken() // k x k 크기의 체스판 1개를 만들고 그곳에서 정사각형 크기 색칠
    val board = Array(n) { br.readLine() } // M x N 크기의 체스판이라고 했지만 입력 설명을 보면 N 개의 줄에 행이 나온다고 됨.
    val diffW = Array(n){ IntArray(m) } // W 가 첫 시작인데 다른 경우 저장용
    val diffB = Array(n){ IntArray(m) } // B 가 첫 시작인데 다른 경우 저장용

    for(i in 0 until n){
        for(j in 0 until m){
            val currentCharacter = board[i][j]
            val isEven = (i + j) % 2 == 0

            // 짝수칸이 B 이거나 홀수칸이 W 라면
            if((isEven && currentCharacter.isBlack()) || (!isEven && !currentCharacter.isBlack())){
                // W 가 시작인 경우 틀린 상황이니 1로 설정
                diffW[i][j] = 1
            }

            // 짝수칸이 W 이거나 홀수칸이 B 라면
            if((isEven && !currentCharacter.isBlack()) || (!isEven && currentCharacter.isBlack())){
                // B 가 시작인 경우 틀린 상황이니 1로 설정
                diffB[i][j] = 1
            }
        }
    }

    val prefixSumW = getPrefixSumTable(diffW, n, m)
    val prefixSumB = getPrefixSumTable(diffB, n, m)
    var minPaints = Int.MAX_VALUE

    for(i in k .. n){
        for(j in k .. m){
            minPaints = min(calculateRangeSum(prefixSumW, i, j, k), min(minPaints, calculateRangeSum(prefixSumB, i, j, k)))
        }
    }

    bw.write(minPaints.toString())
    bw.flush()
    bw.close()
    br.close()
}

private fun Char.isBlack() = if(this == 'B') true else false
private fun StringTokenizer.intToken() = this.nextToken().toInt()

private fun getPrefixSumTable(diff: Array<IntArray>, n: Int, m: Int): Array<IntArray> {
    val prefixSum = Array(n + 1) { IntArray(m + 1) }
    for (i in 1..n) {
        for (j in 1..m) {
            prefixSum[i][j] = diff[i - 1][j - 1] + prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1]
        }
    }
    return prefixSum
}


private fun calculateRangeSum(prefixSum: Array<IntArray>, i: Int, j: Int, k: Int): Int {
    return prefixSum[i][j] - prefixSum[i - k][j] - prefixSum[i][j - k] + prefixSum[i - k][j - k]
}