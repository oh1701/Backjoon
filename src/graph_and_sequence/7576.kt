package graph_and_sequence

import java.util.StringTokenizer

/**
 * 철수의 토마토 농장에서는 토마토를 보관하는 큰 창고를 가지고 있다.
 * 토마토는 아래의 그림과 같이 격자 모양 상자의 칸에 하나씩 넣어서 창고에 보관한다.
 *
 *
 *
 * 창고에 보관되는 토마토들 중에는 잘 익은 것도 있지만, 아직 익지 않은 토마토들도 있을 수 있다.
 * 보관 후 하루가 지나면, 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 된다.
 * 하나의 토마토의 인접한 곳은 왼쪽, 오른쪽, 앞, 뒤 네 방향에 있는 토마토를 의미한다.
 * 대각선 방향에 있는 토마토들에게는 영향을 주지 못하며, 토마토가 혼자 저절로 익는 경우는 없다고 가정한다.
 * 철수는 창고에 보관된 토마토들이 며칠이 지나면 다 익게 되는지, 그 최소 일수를 알고 싶어 한다.
 *
 * 토마토를 창고에 보관하는 격자모양의 상자들의 크기와 익은 토마토들과 익지 않은 토마토들의 정보가 주어졌을 때,
 * 며칠이 지나면 토마토들이 모두 익는지, 그 최소 일수를 구하는 프로그램을 작성하라.
 * 단, 상자의 일부 칸에는 토마토가 들어있지 않을 수도 있다.
 *
 * 입력
 * 첫 줄에는 상자의 크기를 나타내는 두 정수 M,N이 주어진다.
 * M은 상자의 가로 칸의 수, N은 상자의 세로 칸의 수를 나타낸다.
 * 단, 2 ≤ M,N ≤ 1,000 이다. 둘째 줄부터는 하나의 상자에 저장된 토마토들의 정보가 주어진다.
 * 즉, 둘째 줄부터 N개의 줄에는 상자에 담긴 토마토의 정보가 주어진다.
 * 하나의 줄에는 상자 가로줄에 들어있는 토마토의 상태가 M개의 정수로 주어진다.
 * 정수 1은 익은 토마토, 정수 0은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸을 나타낸다.
 *
 * 토마토가 하나 이상 있는 경우만 입력으로 주어진다.
 *
 * 출력
 * 여러분은 토마토가 모두 익을 때까지의 최소 날짜를 출력해야 한다.
 * 만약, 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력해야 하고, 토마토가 모두 익지는 못하는 상황이면 -1을 출력해야 한다.
 *
 * 예제 입력 1
 * 6 4
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 1
 * 예제 출력 1
 * 8
 * 예제 입력 2
 * 6 4
 * 0 -1 0 0 0 0
 * -1 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 1
 * 예제 출력 2
 * -1
 * */

/*
* 상하좌우 영향을 주니 x +- 1, y +- 1
* 다 익게 되는 최소 일 수를 구해야함 (상자 일부 칸에는 토마토가 없을 수도 있다.)
*
* 첫 줄 상자크기 M * N. 각각 2 이상 1000 이하
* 둘째 줄부터 N개의 줄에는 토마토의 정보.
* 하나의 줄에는 토마토의 상태가 M 개의 정수로 주어진다. 정수 1 = 익은토마토, 정수 0 = 안익은 토마토, 정수 -1 = 안들어가있는 토마토
*
* 각 토마토에 접근할때 소모된 날을 넣어줘야한다.
* -1 이거나 이미 익은 경우는 들어가지 않는다.
*
* 출력의 경우 처음부터 모두 익었으면 0, 모두 익히지 못하는 상황이면 -1을 출력.
* 그게 아니라면 최소 날짜를 출력
*
* 1인 경우 모두 한 번에 출발해야함.
*
* */

private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)
private var row = 0
private var col = 0
private val arrayDeque = ArrayDeque<Pair<Int, Int>>()
private lateinit var tomatoBoxArr: Array<IntArray>

private var printDay = 0
// 박스에 들어간 토마토 개수
private var boxInTomatoCount = 0

private const val IS_RIPE = 1
private const val IS_NOT_RIPE = 0
private const val IS_NOT_EXIST = -1

fun `7576-토마토`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val boxSizeToken = StringTokenizer(br.readLine())
    col = boxSizeToken.nextToken().toInt()
    row = boxSizeToken.nextToken().toInt()

    // 토마토가 익은 상태인경우 초기화함과 동시에 arrayDeque 에 넣어주기
    tomatoBoxArr = Array(row){ i ->
        val tomatoToken = StringTokenizer(br.readLine())
        IntArray(col){ j ->
            // 익은 토마토가 존재하면  arrayDeque 에도 추가.
            val tomato = tomatoToken.nextToken().toInt()
            if(tomato == IS_RIPE) arrayDeque.addLast(i to j)
            if(tomato != IS_NOT_EXIST) boxInTomatoCount++
            tomato
        }
    }

    // 만약 arrayDeque 사이즈와 boxInTomatoCount 가 같으면 모두 익은 상태.
    if(arrayDeque.size == boxInTomatoCount){
        bw.write("0")
        bw.flush()
        bw.close()
        br.close()
        return
    }


    while (arrayDeque.isNotEmpty()){
        val location = arrayDeque.removeFirst()
        val x = location.first
        val y = location.second
        // 익은 것들은 박스에서 빼내줬다고 가정하여 -1 해주기
        boxInTomatoCount--

        for(i in 0 until 4){
            val nx = x + dx[i]
            val ny = y + dy[i]

            if(nx in 0 until row && ny in 0 until col && tomatoBoxArr[nx][ny] == IS_NOT_RIPE){
                /*
                * 익은 상태로 변했으므로 날짜 증가, 배열[nx][ny] 의 ripe 및 day 변경 후 printDay 에 넣어주기
                * */
                val tomorrow = tomatoBoxArr[x][y] + 1
                tomatoBoxArr[nx][ny] = tomorrow
                // -1, 0, 1 은 ripe, 2부터는 날짜이므로 - 1 해줘야 실제 날짜가 출력된다.
                printDay = maxOf(printDay, tomorrow - 1)
                arrayDeque.addLast(nx to ny)
            }
        }
    }

    // 박스 안의 남아있는 토마토가 0개면 모두 익어서 출하된 상태이다.
    if(boxInTomatoCount == 0){
        bw.write(printDay.toString())
    } else {
        // 박스 안의 남아있는 토마토가 0개를 넘으면 모두 익히지는 못한 상태이다.
        bw.write("-1")
    }

    bw.flush()
    bw.close()
    br.close()
}