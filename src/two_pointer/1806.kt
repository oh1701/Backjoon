package two_pointer

/**
 * 문제
 * 10,000 이하의 자연수로 이루어진 길이 N짜리 수열이 주어진다.
 * 이 수열에서 연속된 수들의 부분합 중에 그 합이 S 이상이 되는 것 중, 가장 짧은 것의 길이를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N (10 ≤ N < 100,000)과 S (0 < S ≤ 100,000,000)가 주어진다.
 * 둘째 줄에는 수열이 주어진다.
 * 수열의 각 원소는 공백으로 구분되어져 있으며, 10,000이하의 자연수이다.
 *
 * 출력
 * 첫째 줄에 구하고자 하는 최소의 길이를 출력한다. 만일 그러한 합을 만드는 것이 불가능하다면 0을 출력하면 된다.
 *
 * 예제 입력 1
 * 10 15
 * 5 1 3 5 10 7 4 9 2 8
 * 예제 출력 1
 * 2
 * */

/*
* 0.5초 이므로 5천만까지
* 연속적인 부분합이므로 슬라이딩 윈도우를 활용하여 O(n) 만에 완성시키기
* */
fun `1806 - 부분합`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val (n, s) = br.readLine().split(" ").map { it.toInt() }
    val token = java.util.StringTokenizer(br.readLine())
    val arr = IntArray(n){ token.nextToken().toInt() }

    var start = 0
    var end = 0
    var currentSum = 0
    var minLength = Int.MAX_VALUE

    while(true){
        when {
            // 현재 합이 S 보다 크거나 같은 경우 minLength, currentSum 을 조정하고 start 늘려주기
            currentSum >= s -> {
                minLength = kotlin.math.min(minLength, end - start)
                currentSum -= arr[start]
                start++
            }
            // currenSum 이 s 이상이 아니고 end 가 n 과 같다면 종료
            end == n -> break
            // currentSum 이 s 미만이므로 end 를 늘리면서 currentSum 합해주기.
            currentSum < s -> {
                currentSum += arr[end]
                end++
            }
        }
    }

    bw.write(if(minLength == Int.MAX_VALUE) "0" else minLength.toString())
    bw.flush()
    bw.close()
    br.close()
}
