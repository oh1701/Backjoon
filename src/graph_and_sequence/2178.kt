package graph_and_sequence

/**
 * 문제
 * N×M크기의 배열로 표현되는 미로가 있다.
 *
 * 1	0	1	1	1	1
 * 1	0	1	0	1	0
 * 1	0	1	0	1	1
 * 1	1	1	0	1	1
 *
 * 미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다.
 * 이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오.
 * 한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.
 *
 * 위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.
 *
 * 입력
 * 첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다.
 * 다음 N개의 줄에는 M개의 정수로 미로가 주어진다.
 * 각각의 수들은 붙어서 입력으로 주어진다.
 *
 * 출력
 * 첫째 줄에 지나야 하는 최소의 칸 수를 출력한다. 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.
 *
 * 예제 입력 1
 * 4 6
 * 101111
 * 101010
 * 101011
 * 111011
 * 예제 출력 1
 * 15
 * 예제 입력 2
 * 4 6
 * 110110
 * 110110
 * 111111
 * 111101
 * 예제 출력 2
 * 9
 * 예제 입력 3
 * 2 25
 * 1011101110111011101110111
 * 1110111011101110111011101
 * 예제 출력 3
 * 38
 * */

/*
* 인접한 칸으로 이동. 최소 칸을 찾아야 함. 최소칸을 찾으므로 BFS 사용.
* 인접한 곳으로 움직여야하므로 dx, dy 변수 설정.
* 1, 1(0, 0) 에서 출발. 출발도 1칸으로 침.
*
* 움직인 최소 칸을 찾아야하므로 visited 에 count 를 넣어주고 움직이면 이전 카운트 + 1 해준다.
* */
private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)
private lateinit var visitedArr: Array<IntArray>
private lateinit var wayArr: Array<CharArray>
private var n = 0
private var m = 0

fun `2178-미로 탐색`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val token = java.util.StringTokenizer(br.readLine())

    n = token.nextToken().toInt()
    m = token.nextToken().toInt()

    // 0-based Index
    wayArr = Array(n){ br.readLine().toCharArray() }
    visitedArr = Array(n){ IntArray(m) }

    bfs()

    bw.write(visitedArr[n - 1][m - 1].toString())
    bw.flush()
    bw.close()
    br.close()
}

private fun bfs(){
    val arrayDeque = ArrayDeque<Pair<Int, Int>>()
    val startX = 0
    val startY = 0

    arrayDeque.add(startX to startY)
    visitedArr[startX][startY] = 1

    while(arrayDeque.isNotEmpty()){
        val from = arrayDeque.removeFirst()
        val x = from.first
        val y = from.second

        // 도착했으므로 종료
        if(x == n && y == m) break

        for(i in 0 until 4){
            val nx = x + dx[i]
            val ny = y + dy[i]

            if(nx in 0 until n && ny in 0 until m && wayArr[nx][ny] == '1' && visitedArr[nx][ny] == 0){
                /*
                * 움직였으므로 이전 Count + 1 을 해준다.
                * */
                visitedArr[nx][ny] = visitedArr[x][y] + 1
                arrayDeque.addLast(nx to ny)
            }
        }
    }
}