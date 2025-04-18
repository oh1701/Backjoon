package sort

import java.util.*

/**
 * 2차원 평면 위의 점 N개가 주어진다.
 * 좌표를 y좌표가 증가하는 순으로, y좌표가 같으면 x좌표가 증가하는 순서로 정렬한 다음 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 점의 개수 N (1 ≤ N ≤ 100,000)이 주어진다.
 * 둘째 줄부터 N개의 줄에는 i번점의 위치 xi와 yi가 주어진다.
 * (-100,000 ≤ xi, yi ≤ 100,000) 좌표는 항상 정수이고, 위치가 같은 두 점은 없다.
 *
 * 출력
 * 첫째 줄부터 N개의 줄에 점을 정렬한 결과를 출력한다.
 *
 * 예제 입력 1
 * 5
 * 0 4
 * 1 2
 * 1 -1
 * 2 2
 * 3 3
 * 예제 출력 1
 * 1 -1
 * 1 2
 * 2 2
 * 3 3
 * 0 4
 * */
fun `11651-좌표 정렬하기`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt()
    val arr = Array(n){0 to 0}

    repeat(n){ i ->
        val token = StringTokenizer(br.readLine())
        val x = token.nextToken().toInt()
        val y = token.nextToken().toInt()

        arr[i] = x to y
    }

    arr.sortedWith(compareBy({it.second}, {it.first}))
        .forEach { bw.write("${it.first} ${it.second}\n") }
    bw.flush()
    bw.close()
    br.close()
}