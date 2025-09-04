package prefix_sum

import java.util.StringTokenizer

/**
 * 수 N개 A1, A2, ..., AN이 주어진다.
 * 이때, 연속된 부분 구간의 합이 M으로 나누어 떨어지는 구간의 개수를 구하는 프로그램을 작성하시오.
 *
 * 즉, Ai + ... + Aj (i ≤ j) 의 합이 M으로 나누어 떨어지는 (i, j) 쌍의 개수를 구해야 한다.
 *
 *
 * 입력
 * 첫째 줄에 N과 M이 주어진다. (1 ≤ N ≤ 106, 2 ≤ M ≤ 103)
 *
 * 둘째 줄에 N개의 수 A1, A2, ..., AN이 주어진다. (0 ≤ Ai ≤ 109)
 *
 * 출력
 * 첫째 줄에 연속된 부분 구간의 합이 M으로 나누어 떨어지는 구간의 개수를 출력한다.
 *
 * 예제 입력 1
 * 5 3
 * 1 2 3 1 2
 * 예제 출력 1
 * 7
 * */

/*
* Ai .. Aj 구간. 즉, i = 1, j = 4 라면 이 구간의 누적합을 구해야함
* 누적합을 구하기 위해서는 1 ~ 4번째의 경우 A[j] - A[i - 1] 을 하면 누적합이 나온다.
* 1, 2, 3, 4 였다면 누적합은 1, 3, 6, 10
* A[4] - A[0] = 10 - 0 = 10
* */

fun `10986-나머지 합`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val numToken = StringTokenizer(br.readLine())
    val remainderArr = LongArray(m)
    var sum = 0L
    var totalCount = 0L

    // 누적합을 구할때 0번째는 무조건 있어야함. 0 % M = 0 이므로 remainderArr[0] 은 1개가 존재하는채 시작
    remainderArr[0] = 1

    repeat(n){
        sum += numToken.nextToken().toLong()
        val remainder = (sum % m).toInt()
        remainderArr[remainder]++
    }

    for(remainder in remainderArr){
        if(remainder > 1){
            totalCount += remainder * (remainder - 1) / 2
        }
    }

    bw.write(totalCount.toString())
    bw.flush()
    bw.close()
    br.close()
}
// 1 0 0 1 0 1 2개 0 3개