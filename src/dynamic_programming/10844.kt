package dynamic_programming

import kotlin.math.max
import kotlin.math.min

/**
 * 45656이란 수를 보자.
 *
 * 이 수는 인접한 모든 자리의 차이가 1이다. 이런 수를 계단 수라고 한다.
 *
 * N이 주어질 때, 길이가 N인 계단 수가 총 몇 개 있는지 구해보자. 0으로 시작하는 수는 계단수가 아니다.
 *
 * 입력
 * 첫째 줄에 N이 주어진다. N은 1보다 크거나 같고, 100보다 작거나 같은 자연수이다.
 *
 * 출력
 * 첫째 줄에 정답을 1,000,000,000으로 나눈 나머지를 출력한다.
 *
 * 예제 입력 1
 * 1
 * 예제 출력 1
 * 9
 * 예제 입력 2
 * 2
 * 예제 출력 2
 * 17
 * */

fun `10844-쉬운 계단 수`(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val mod = 1_000_000_000 // 나머지 연산을 위한 값

    // dp[i][j]: 길이가 i이고, 마지막 숫자가 j인 계단 수의 개수
    val dp = Array(n + 1) { LongArray(10) }

    // 1. 초기값(Base Case) 설정: 길이가 1인 경우
    for (j in 1..9) {
        dp[1][j] = 1L
    }

    // 2. 점화식을 이용해 DP 테이블 채우기 (길이가 2부터 n까지)
    for (i in 2..n) {
        for (j in 0..9) {
            when (j) {
                0 -> { // 마지막 숫자가 0인 경우, 이전 숫자는 1이어야 함
                    dp[i][0] = dp[i - 1][1] % mod
                }
                9 -> { // 마지막 숫자가 9인 경우, 이전 숫자는 8이어야 함
                    dp[i][9] = dp[i - 1][8] % mod
                }
                else -> { // 그 외의 경우
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % mod
                }
            }
        }
    }

    // 3. 최종 결과 계산: 길이가 n인 모든 계단 수의 합
    var result = 0L
    for (j in 0..9) {
        result = (result + dp[n][j]) % mod
    }

    println(result)
}