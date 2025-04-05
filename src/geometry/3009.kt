package geometry

import java.util.StringTokenizer

/**
 * 문제
 * 세 점이 주어졌을 때, 축에 평행한 직사각형을 만들기 위해서 필요한 네 번째 점을 찾는 프로그램을 작성하시오.
 *
 * 입력
 * 세 점의 좌표가 한 줄에 하나씩 주어진다. 좌표는 1보다 크거나 같고, 1000보다 작거나 같은 정수이다.
 *
 * 출력
 * 직사각형의 네 번째 점의 좌표를 출력한다.
 *
 * 예제 입력 1
 * 5 5
 * 5 7
 * 7 5
 * 예제 출력 1
 * 7 7
 *
 * 예제 입력 2
 * 30 20
 * 10 10
 * 10 20
 * 예제 출력 2
 * 30 10
 * */
fun `3009-네번째점`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val xMap = mutableMapOf<Int, Int>()
    val yMap = mutableMapOf<Int, Int>()

    repeat(3){
        val token = StringTokenizer(br.readLine())
        val x = token.nextToken().toInt()
        val y = token.nextToken().toInt()
        xMap[x] = (xMap[x] ?: 0) + 1
        yMap[y] = (yMap[y] ?: 0) + 1
    }

    val printX = xMap.filterValues { it == 1 }.keys.first()
    val printY = yMap.filterValues { it == 1 }.keys.first()

    bw.write("$printX $printY")
    bw.close()
    br.close()
}