package prefix_sum

import java.util.StringTokenizer

/**
 * 수 N개가 주어졌을 때, i번째 수부터 j번째 수까지 합을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 수의 개수 N과 합을 구해야 하는 횟수 M이 주어진다.
 * 둘째 줄에는 N개의 수가 주어진다.
 * 수는 1,000보다 작거나 같은 자연수이다.
 * 셋째 줄부터 M개의 줄에는 합을 구해야 하는 구간 i와 j가 주어진다.
 *
 * 출력
 * 총 M개의 줄에 입력으로 주어진 i번째 수부터 j번째 수까지 합을 출력한다.
 *
 * 제한
 * 1 ≤ N ≤ 100,000
 * 1 ≤ M ≤ 100,000
 * 1 ≤ i ≤ j ≤ N
 * 예제 입력 1
 * 5 3
 * 5 4 3 2 1
 * 1 3
 * 2 4
 * 5 5
 * 예제 출력 1
 * 12
 * 9
 * 1
 * */
fun `11659-누적 합 구하기4`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val numbers = br.readLine().split(" ").map { it.toInt() }
    val prefixSum = IntArray(n + 1)
    val str = StringBuilder()

    for (i in 1..n) {
        /*
        * 0번째에서는 i - 1 이 불가능하므로 넘기기
        * 1번째 = 이전 prefixSum(누적합) + numbers[현재인 1번째값] 을 더함
        * 2번째 = 이전 prefixSum(누적합) + numbers[현재인 2번째값] 을 더함. 즉, 2번째는 1 + 2 임.
        * */
        prefixSum[i] = prefixSum[i - 1] + numbers[i - 1]
    }

    repeat(m) {
        /*
        * start 가 2 고 end 가 4라면
        * prefix[4] 가 필요. 하지만, 2 이전의 값들은 쓸모가 없으므로 prefixSum[start - 1] 을 추가로 빼주어 제거.
        * */
        val (start, end) = br.readLine().split(" ").map { it.toInt() }
        str.appendLine("${prefixSum[end] - prefixSum[start - 1]}")
    }

    bw.write(str.toString())
    bw.flush()
    bw.close()
    br.close()
}