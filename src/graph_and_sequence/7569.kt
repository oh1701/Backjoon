package graph_and_sequence

import java.util.StringTokenizer

/**
 *
 * 문제
 * 철수의 토마토 농장에서는 토마토를 보관하는 큰 창고를 가지고 있다.
 * 토마토는 아래의 그림과 같이 격자모양 상자의 칸에 하나씩 넣은 다음, 상자들을 수직으로 쌓아 올려서 창고에 보관한다.
 *
 * 창고에 보관되는 토마토들 중에는 잘 익은 것도 있지만, 아직 익지 않은 토마토들도 있을 수 있다.
 * 보관 후 하루가 지나면, 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 된다.
 * 하나의 토마토에 인접한 곳은 위, 아래, 왼쪽, 오른쪽, 앞, 뒤 여섯 방향에 있는 토마토를 의미한다.
 * 대각선 방향에 있는 토마토들에게는 영향을 주지 못하며, 토마토가 혼자 저절로 익는 경우는 없다고 가정한다.
 * 철수는 창고에 보관된 토마토들이 며칠이 지나면 다 익게 되는지 그 최소 일수를 알고 싶어 한다.
 *
 * 토마토를 창고에 보관하는 격자모양의 상자들의 크기와 익은 토마토들과 익지 않은 토마토들의 정보가 주어졌을 때,
 * 며칠이 지나면 토마토들이 모두 익는지, 그 최소 일수를 구하는 프로그램을 작성하라.
 * 단, 상자의 일부 칸에는 토마토가 들어있지 않을 수도 있다.
 *
 * 입력
 * 첫 줄에는 상자의 크기를 나타내는 두 정수 M,N과 쌓아올려지는 상자의 수를 나타내는 H가 주어진다.
 * M은 상자의 가로 칸의 수, N은 상자의 세로 칸의 수를 나타낸다.
 * 단, 2 ≤ M ≤ 100, 2 ≤ N ≤ 100, 1 ≤ H ≤ 100 이다.
 *
 * 둘째 줄부터는 가장 밑의 상자부터 가장 위의 상자까지에 저장된 토마토들의 정보가 주어진다.
 * 즉, 둘째 줄부터 N개의 줄에는 하나의 상자에 담긴 토마토의 정보가 주어진다.
 * 각 줄에는 상자 가로줄에 들어있는 토마토들의 상태가 M개의 정수로 주어진다.
 * 정수 1은 익은 토마토, 정수 0 은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸을 나타낸다.
 * 이러한 N개의 줄이 H번 반복하여 주어진다.
 *
 * 토마토가 하나 이상 있는 경우만 입력으로 주어진다.
 *
 * 출력
 * 여러분은 토마토가 모두 익을 때까지 최소 며칠이 걸리는지를 계산해서 출력해야 한다.
 * 만약, 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력해야 하고, 토마토가 모두 익지는 못하는 상황이면 -1을 출력해야 한다.
 *
 * 예제 입력 1
 * 5 3 1
 * 0 -1 0 0 0
 * -1 -1 0 1 1
 * 0 0 0 1 1
 * 예제 출력 1
 * -1
 * 예제 입력 2
 * 5 3 2
 * 0 0 0 0 0
 * 0 0 0 0 0
 * 0 0 0 0 0
 * 0 0 0 0 0
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 예제 출력 2
 * 4
 * */

/*
* 인접 = dx, dy, dz 3가지 변수 사용
*
* 첫 줄에 상자의 크기 M, N 과 상자의 수 H
* M = 가로 칸 수 (col), N = 세로 칸 수 (row)
* M,N,H = 2 ~ 100
*
* 1 - 익토, 0 - 안익토, -1 - 안넣음
*
* 상자에 저장되자마자 모든 토마토가 익어있으면 0.
* 나중에 모두 익히지 못하면 -1
* 아니면 걸리는 시간 출력
* */
private const val IS_RIPE = 1
private const val IS_NOT_RIPE = 0
private const val IS_NOT_EXIST = -1
private val arrayDeque = ArrayDeque<Triple<Int, Int, Int>>()
private lateinit var tomatoBoxArr: Array<Array<IntArray>>
private var boxInTomatoCount = 0
private val dz = intArrayOf(-1, 1, 0, 0, 0, 0)
private val dx = intArrayOf(0, 0, -1, 1, 0, 0)
private val dy = intArrayOf(0, 0, 0, 0, -1, 1)
private var col = 0
private var row = 0
private var zIndex = 0
private var printDay = 0

fun `7569-토마토`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val directToken = StringTokenizer(br.readLine())
    col = directToken.nextToken().toInt()
    row = directToken.nextToken().toInt()
    zIndex = directToken.nextToken().toInt()

    tomatoBoxArr = Array(zIndex){ z ->
        Array(row){ i ->
            val tomatoToken = StringTokenizer(br.readLine())
            IntArray(col){ j ->
                val tomato = tomatoToken.nextToken().toInt()
                if(tomato == IS_RIPE) arrayDeque.addLast(Triple(z, i, j))
                if(tomato != IS_NOT_EXIST) boxInTomatoCount++
                tomato
            }
        }
    }

    if(arrayDeque.size == boxInTomatoCount){
        bw.write("0")
        bw.flush()
        bw.close()
        br.close()
        return
    }

    while(arrayDeque.isNotEmpty()){
        val location = arrayDeque.removeFirst()
        val z = location.first
        val x = location.second
        val y = location.third
        // 익었으므로 출하했다고 가정
        boxInTomatoCount--

        for(i in 0 until 6){
            val nz = z + dz[i]
            val nx = x + dx[i]
            val ny = y + dy[i]

            if(nz !in 0 until zIndex || nx !in 0 until row || ny !in 0 until col) continue
            if(tomatoBoxArr[nz][nx][ny] == IS_NOT_RIPE){
                tomatoBoxArr[nz][nx][ny] = tomatoBoxArr[z][x][y] + 1
                // 1이 익은것. 안익은것 0을 1 + 1 해서 2로 만들었으니, 날짜는 -1 해야 정상이다.
                printDay = maxOf(printDay, tomatoBoxArr[nz][nx][ny] - 1)
                arrayDeque.addLast(Triple(nz, nx, ny))
            }
        }
    }

    // 출하되지 못한 토마토가 존재하므로 -1 출력해야한다.
    if(boxInTomatoCount > 0){
        bw.write("-1")
    } else {
        bw.write("$printDay")
    }

    bw.flush()
    bw.close()
    br.close()
}