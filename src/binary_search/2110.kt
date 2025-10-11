package binary_search

import java.util.StringTokenizer

/**
 * 도현이의 집 N개가 수직선 위에 있다.
 * 각각의 집의 좌표는 x1, ..., xN이고, 집 여러개가 같은 좌표를 가지는 일은 없다.
 *
 * 도현이는 언제 어디서나 와이파이를 즐기기 위해서 집에 공유기 C개를 설치하려고 한다.
 * 최대한 많은 곳에서 와이파이를 사용하려고 하기 때문에, 한 집에는 공유기를 하나만 설치할 수 있고,
 * 가장 인접한 두 공유기 사이의 거리를 가능한 크게 하여 설치하려고 한다.
 *
 * C개의 공유기를 N개의 집에 적당히 설치해서, 가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 집의 개수 N (2 ≤ N ≤ 200,000)과 공유기의 개수 C (2 ≤ C ≤ N)이 하나 이상의 빈 칸을 사이에 두고 주어진다.
 * 둘째 줄부터 N개의 줄에는 집의 좌표를 나타내는 xi (0 ≤ xi ≤ 1,000,000,000)가 한 줄에 하나씩 주어진다.
 *
 * 출력
 * 첫째 줄에 가장 인접한 두 공유기 사이의 최대 거리를 출력한다.
 *
 * 예제 입력 1
 * 5 3
 * 1
 * 2
 * 8
 * 4
 * 9
 * 예제 출력 1
 * 3
 * */

fun `2110-공유기 설치`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val token = StringTokenizer(br.readLine())
    val n = token.nextToken().toInt()
    val c = token.nextToken().toInt()
    val xArr = IntArray(n){ br.readLine().toInt() }

    /*
    * 파라메트릭 서치를 진행하기 위해 정렬
    * */
    xArr.sort()
    var low = 1

    /*
    * 마지막 집의 좌표 - 첫 번째 집의 좌표로 설정
    * A, B 집 사이의 공유기 거리는 B - A 가 최대이므로.
    * */
    var high = xArr.last() - xArr.first()
    var result = 0

    while (low <= high){
        val mid = (high + low) / 2
        /*
        * 첫 시작점을 포지션으로 잡고, 이를 잡았으니 count를 1로 시작한다.
        * (check) mid 거리로 C개의 공유기를 설치할 수 있는지 확인
        * */
        var lastPosition = xArr[0]
        var count = 1

        for(i in 1 until xArr.size){
            /*
            * 현재 좌표 - 마지막 포지션이 너비보다 크거나 같으면
            * */
            if(xArr[i] - lastPosition >= mid){
                count++
                lastPosition = xArr[i]
            }
        }

        /*
        * 1. 성공하였으나, mid 거리를 더 늘려도 될 수 있음
        * 2. 실패하였으니, mid 거리를 줄이기
        * */
        if(count >= c){
            result = mid
            low = mid + 1
        } else {
            high = mid - 1
        }
    }

    bw.write(result.toString())
    bw.flush()
    bw.close()
    br.close()
}