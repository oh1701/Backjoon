package graph_and_sequence

/**
 * 문제
 * 체스판 위에 한 나이트가 놓여져 있다.
 * 나이트가 한 번에 이동할 수 있는 칸은 아래 그림에 나와있다.
 * 나이트가 이동하려고 하는 칸이 주어진다.
 * 나이트는 몇 번 움직이면 이 칸으로 이동할 수 있을까?
 *
 * 입력
 * 입력의 첫째 줄에는 테스트 케이스의 개수가 주어진다.
 *
 * 각 테스트 케이스는 세 줄로 이루어져 있다.
 * 첫째 줄에는 체스판의 한 변의 길이 l(4 ≤ l ≤ 300)이 주어진다.
 * 체스판의 크기는 l × l이다. 체스판의 각 칸은 두 수의 쌍 {0, ..., l-1} × {0, ..., l-1}로 나타낼 수 있다.
 * 둘째 줄과 셋째 줄에는 나이트가 현재 있는 칸, 나이트가 이동하려고 하는 칸이 주어진다.
 *
 * 출력
 * 각 테스트 케이스마다 나이트가 최소 몇 번만에 이동할 수 있는지 출력한다.
 *
 * 예제 입력 1
 * 3
 * 8
 * 0 0
 * 7 0
 * 100
 * 0 0
 * 30 50
 * 10
 * 1 1
 * 1 1
 * 예제 출력 1
 * 5
 * 28
 * 0
 * */

/*
* 나이트가 움직이는 방향은 x +- 2, y +- 1 이거나 x +- 1, y +- 2 이다.
*
* 첫째줄 - 테스트 케이스 개수
* 첫째 줄 - 체스판 한 변의 길이 l
* 둘째 줄 - 나이트가 현재 있는 칸
* 셋째 줄 - 나이트가 이동하려는 칸
* 체스판의 크기는 l * l, 각 칸은 0 .. l - 1
* */

private val dx = intArrayOf(-2, -2, +2, +2, -1, +1, -1, +1)
private val dy = intArrayOf(-1, +1, -1, +1, -2, -2, +2, +2)
private var l = 0
private lateinit var chessBoard: Array<IntArray>
private var stringBuilder = StringBuilder()

fun `7562-나이트의 이동`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val t = br.readLine().toInt()

    repeat(t){
        l = br.readLine().toInt()
        // 방문했는지 이것으로 확인
        chessBoard = Array(l){ IntArray(l){ -1 } }
        val (currentX, currentY) = br.readLine().split(" ").map { it.toInt() }
        val (goalX, goalY) = br.readLine().split(" ").map { it.toInt() }

        bfs(currentX, currentY, goalX, goalY)
    }

    bw.write(stringBuilder.toString())
    bw.flush()
    bw.close()
    br.close()
}

private fun bfs(startX: Int, startY: Int, goalX: Int, goalY: Int){
    val arrayDeque = ArrayDeque<Pair<Int, Int>>()

    arrayDeque.add(startX to startY)
    /*
    * 첫 시작은 0번 움직였으므로 0을 넣어준다.
    * */
    chessBoard[startX][startY] = 0

    while(arrayDeque.isNotEmpty()){
        val currentLocation = arrayDeque.removeFirst()
        val x = currentLocation.first
        val y = currentLocation.second

        if(x == goalX && y == goalY){
            if(stringBuilder.isNotEmpty()) stringBuilder.append("\n")
            stringBuilder.append("${chessBoard[x][y]}")
            break
        }

        for(i in 0 until 8){
            val nx = x + dx[i]
            val ny = y + dy[i]

            /*
            * 이동한 칸이 아닌 경우에만 진행
            * */
            if(nx in 0 until l && ny in 0 until l && chessBoard[nx][ny] == -1){
                chessBoard[nx][ny] = chessBoard[x][y] + 1
                arrayDeque.addLast(nx to ny)
            }
        }
    }
}