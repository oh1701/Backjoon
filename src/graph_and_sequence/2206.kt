package graph_and_sequence

/**
 * 문제
 * N×M의 행렬로 표현되는 맵이 있다.
 * 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다.
 * 당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다.
 * 최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝나는 칸도 포함해서 센다.
 *
 * 만약에 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동하여도 된다.
 *
 * 한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다.
 *
 * 맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)이 주어진다.
 * 다음 N개의 줄에 M개의 숫자로 맵이 주어진다.
 * (1, 1)과 (N, M)은 항상 0이라고 가정하자.
 *
 * 출력
 * 첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.
 *
 * 예제 입력 1
 * 6 4
 * 0100
 * 1110
 * 1000
 * 0000
 * 0111
 * 0000
 * 예제 출력 1
 * 15
 * 예제 입력 2
 * 4 4
 * 0111
 * 1111
 * 1111
 * 1110
 * 예제 출력 2
 * -1
 * */

/*
* 0 -> 이동 가능, 1 -> 이동할 수 없는 벽이 존재
* (1, 1) 에서 (N, M) 까지 이동. 최단 경로로
* 시작하는 칸과 끝나는 칸도 포함해서 세야한다.
* 이동하는 도중, 한 개의 벽을 부수고 이동하는 것이 짧아지는거라면 한 개까지는 부수고 이동해도 된다.
*
* 1 <= N, M <= 1000. 입력값은 1000 * 1000 = 1_000_000
* */

/**
* @param wallBroken 이전에 벽을 파괴했는지 판단
* */
private data class State(
    val x: Int,
    val y: Int,
    var wallBroken: Int,
)

private var row = 0
private var col = 0
private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)
private const val IS_NOT_VISIT = -1
// 0-based Index
private lateinit var map: Array<String>
private lateinit var visited: Array<Array<IntArray>>

fun `2206 - 벽 부수고 이동하기`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val input = br.readLine().split(" ").map { it.toInt() }
    row = input[0]
    col = input[1]
    map = Array(row){ br.readLine() }
    visited = Array(row){ Array(col){ IntArray(2){ IS_NOT_VISIT } } }

    bw.write(bfs().toString())
    bw.flush()
    bw.close()
    br.close()
}

private fun bfs(): Int {
    val arrayDeque = ArrayDeque<State>()

    arrayDeque.addLast(State(0, 0,  0))
    visited[0][0][0] = 1

    while(arrayDeque.isNotEmpty()){
        val current = arrayDeque.removeFirst()
        val x = current.x
        val y = current.y
        val broken = current.wallBroken

        // x, y 가 도착 지점이면
        if(x == row - 1 && y == col - 1){
            return visited[x][y][broken]
        }

        for(i in 0 until 4){
            val nx = x + dx[i]
            val ny = y + dy[i]

            when {
                // nx 나 ny 가 제한구역에 포함되지 않았다면 다음 순번으로 넘기기
                nx !in 0 until row || ny !in 0 until col -> continue
                // 다음 칸이 벽이고, 이미 벽을 깬 전적이 없다면
                map[nx][ny] == '1' && broken == 0 -> {
                    // 부순 상태로 다음 칸에 방문한 적이 없다면
                    if(visited[nx][ny][1] == IS_NOT_VISIT){
                        val state = State(nx, ny, 1)
                        visited[nx][ny][1] = visited[x][y][broken] + 1
                        arrayDeque.addLast(state)
                    }
                }
                // 다음 칸이 길이고, 방문한 적이 없을때
                map[nx][ny] == '0' && visited[nx][ny][broken] == IS_NOT_VISIT -> {
                    val state = State(nx, ny, broken)
                    visited[nx][ny][broken] = visited[x][y][broken] + 1
                    arrayDeque.addLast(state)
                }
            }
        }
    }

    return -1
}