package binary_search

import kotlin.math.min

/**
 * 세준이는 크기가 N×N인 배열 A를 만들었다.
 * 배열에 들어있는 수 A[i][j] = i×j 이다.
 * 이 수를 일차원 배열 B에 넣으면 B의 크기는 N×N이 된다.
 * B를 오름차순 정렬했을 때, B[k]를 구해보자.
 *
 * 배열 A와 B의 인덱스는 1부터 시작한다.
 *
 * 입력
 * 첫째 줄에 배열의 크기 N이 주어진다.
 * N은 10^5보다 작거나 같은 자연수이다
 * 둘째 줄에 k가 주어진다.
 * k는 min(10^9, N^2)보다 작거나 같은 자연수이다.
 *
 * 출력
 * B[k]를 출력한다.
 *
 * 예제 입력 1
 * 3
 * 7
 * 예제 출력 1
 * 6
 * */

/*
* a, b 모두 1-based Index
* A 는 2차월 배열
* B 는 1차원 배열
*
* A 가 3 x 3 이라면
* [1, 2, 3, 2, 4, 6, 3, 6, 9 ...]
* B 는 9
* [1, 2, 2, 3, 3, 4, 6, 6, 9 ...]
*
* A[i][j] = i * j
* 즉, X = i * j
* 이항하면 j = X / i
*
* */
fun `1300-K번째 수`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt()
    val k = br.readLine().toLong()
    var low = 1L
    var high = n.toLong() * n
    var result = 0L

    while(low <= high){
        /*
        * 파라메트릭 서치를 사용하므로 최대 값 + 최소 값 / 2
        * */
        val mid = low + (high - low) / 2
        var count = 0L

        /*
        * 1 인덱스부터 n 까지 반복 계산해야하므로 i .. n
        * check(mid) = mid 보다 작거나 같은 숫자가 n * n 에 몇 개가 있는지 확인.
        * i 행에서는 n 보다 작거나 같게 존재해야한다. (n 이 최대치이므로)
        * */
        for(i in 1 .. n){
            count += min(n.toLong(), (mid / i))
        }

        /*
        * mid = B[k] 일때, mid 보다 작거나 같은 수들은 k 보다 크거나 같은 개수이다.
        * count(mid) >= k 를 만족하는 가장 작은 수를 찾으면 B[k] 가 되므로, count < k 로 조건을 설정해준다.
        * */
        if(count < k){
            low = mid + 1
        } else {
            result = mid
            high = mid - 1
        }
    }

    bw.write(result.toString())
    bw.flush()
    bw.close()
    br.close()
}