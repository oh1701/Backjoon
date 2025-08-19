package dynamic_programming

import java.util.StringTokenizer
import kotlin.math.min

/**
 * RGB거리에는 집이 N개 있다. 거리는 선분으로 나타낼 수 있고, 1번 집부터 N번 집이 순서대로 있다.
 *
 * 집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다.
 * 각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때, 아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.
 *
 * 1번 집의 색은 2번 집의 색과 같지 않아야 한다.
 * N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
 * i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.
 *
 * 입력
 * 첫째 줄에 집의 수 N(2 ≤ N ≤ 1,000)이 주어진다.
 * 둘째 줄부터 N개의 줄에는 각 집을 빨강, 초록, 파랑으로 칠하는 비용이 1번 집부터 한 줄에 하나씩 주어진다.
 * 집을 칠하는 비용은 1,000보다 작거나 같은 자연수이다.
 *
 * 출력
 * 첫째 줄에 모든 집을 칠하는 비용의 최솟값을 출력한다.
 *
 * 예제 입력 1
 * 3
 * 26 40 83
 * (89)->49 (86)->60 (83)->57
 * (99)->13 (162)->89 (185)->99
 * 예제 출력 1
 * 99
 *
 * 예제 입력 2
 * 3
 * 1 100 100
 * 100 1 100
 * 100 100 1
 * 예제 출력 2
 * 3
 * 예제 입력 3
 * 3
 * 1 100 100
 * 100 100 100
 * 1 100 100
 * 예제 출력 3
 * 102
 * */

/*
*  * 각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때, 아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.
 *
 * 1번 집의 색은 2번 집의 색과 같지 않아야 한다.
 * N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
 * i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.
 *
 * Dp 문제이므로 [i][0] 번을 기록할때는 [i][0] + min([i][1], [i][2]) 로 설정하여 최종까지 진행.
* */

fun `1149-RGB거리`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt() // 집의 개수
    val dp = Array(n) { IntArray(3) }
    val housesArr = Array(n){
        val houseToken = StringTokenizer(br.readLine())
        IntArray(3){ houseToken.nextToken().toInt() }
    }

    dp[0][0] = housesArr[0][0]
    dp[0][1] = housesArr[0][1]
    dp[0][2] = housesArr[0][2]

    for(i in 1 until n){
        dp[i][0] = housesArr[i][0] + min(dp[i - 1][1], dp[i - 1][2])
        dp[i][1] = housesArr[i][1] + min(dp[i - 1][0], dp[i - 1][2])
        dp[i][2] = housesArr[i][2] + min(dp[i - 1][0], dp[i - 1][1])
    }

    val result = min(dp[n - 1][0], min(dp[n - 1][1], dp[n - 1][2]))
    bw.write(result.toString())
    bw.flush()
    bw.close()
    br.close()
}