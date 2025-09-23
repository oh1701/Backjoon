package divide_and_conquer_algorithm

import backtracking.solve
import java.util.StringTokenizer
import kotlin.math.min

/**
 * N×N크기의 행렬로 표현되는 종이가 있다.
 * 종이의 각 칸에는 -1, 0, 1 중 하나가 저장되어 있다.
 * 우리는 이 행렬을 다음과 같은 규칙에 따라 적절한 크기로 자르려고 한다.
 *
 * (1) 만약 종이가 모두 같은 수로 되어 있다면 이 종이를 그대로 사용한다.
 * (2) (1)이 아닌 경우에는 종이를 같은 크기의 종이 9개로 자르고, 각각의 잘린 종이에 대해서 (1)의 과정을 반복한다.
 *
 * 이와 같이 종이를 잘랐을 때, -1로만 채워진 종이의 개수, 0으로만 채워진 종이의 개수, 1로만 채워진 종이의 개수를 구해내는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 3의 7승, N은 3k 꼴)이 주어진다. 다음 N개의 줄에는 N개의 정수로 행렬이 주어진다.
 *
 * 출력
 * 첫째 줄에 -1로만 채워진 종이의 개수를, 둘째 줄에 0으로만 채워진 종이의 개수를, 셋째 줄에 1로만 채워진 종이의 개수를 출력한다.
 *
 * 예제 입력 1
 * 9
 * 0 0 0 1 1 1 -1 -1 -1
 * 0 0 0 1 1 1 -1 -1 -1
 * 0 0 0 1 1 1 -1 -1 -1
 * 1 1 1 0 0 0 0 0 0
 * 1 1 1 0 0 0 0 0 0
 * 1 1 1 0 0 0 0 0 0
 * 0 1 -1 0 1 -1 0 1 -1
 * 0 -1 1 0 1 -1 0 1 -1
 * 0 1 -1 1 0 -1 0 1 -1
 * 예제 출력 1
 * 10
 * 12
 * 11
 * */

private var n = 0
private lateinit var arr: Array<IntArray>
private var minusCount = 0
private var zeroCount = 0
private var oneCount = 0

fun `1780-종이의 개수`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    n = br.readLine().toInt() // n 은 3의 K 승. 즉, 3의 배수이다.
    arr = Array(n){
        val lineToken = StringTokenizer(br.readLine())
        IntArray(n){ lineToken.nextToken().toInt() }
    }

    solve(0, 0, n)

    bw.write("${minusCount}\n${zeroCount}\n${oneCount}")
    bw.flush()
    bw.close()
    br.close()
}

private fun solve(x: Int, y: Int, size: Int){
    val startNum = arr[x][y]
    var isAllSame = true

    forStart@for(i in x until x + size) {
        for (j in y until y + size) {
            if(arr[i][j] != startNum){
                isAllSame = false
                break@forStart
            }
        }
    }

    if(isAllSame){
        when(startNum){
            -1 -> minusCount++
            0 -> zeroCount++
            else -> oneCount++
        }
    } else {
        val newSize = size / 3

        for(i in 0 .. 2){
            for(j in 0 .. 2){
                solve(x + (newSize * i), y + (newSize * j), newSize)
            }
        }
    }
}