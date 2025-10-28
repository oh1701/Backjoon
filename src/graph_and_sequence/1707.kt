package graph_and_sequence

/**
 * 문제
 * 그래프의 정점의 집합을 둘로 분할하여, 각 집합에 속한 정점끼리는 서로 인접하지 않도록 분할할 수 있을 때,
 * 그러한 그래프를 특별히 이분 그래프 (Bipartite Graph) 라 부른다.
 *
 * 그래프가 입력으로 주어졌을 때, 이 그래프가 이분 그래프인지 아닌지 판별하는 프로그램을 작성하시오.
 *
 * 입력
 * 입력은 여러 개의 테스트 케이스로 구성되어 있는데, 첫째 줄에 테스트 케이스의 개수 K가 주어진다.
 * 각 테스트 케이스의 첫째 줄에는 그래프의 정점의 개수 V와 간선의 개수 E가 빈 칸을 사이에 두고 순서대로 주어진다.
 * 각 정점에는 1부터 V까지 차례로 번호가 붙어 있다.
 * 이어서 둘째 줄부터 E개의 줄에 걸쳐 간선에 대한 정보가 주어지는데,
 * 각 줄에 인접한 두 정점의 번호 u, v (u ≠ v)가 빈 칸을 사이에 두고 주어진다.
 *
 * 출력
 * K개의 줄에 걸쳐 입력으로 주어진 그래프가 이분 그래프이면 YES, 아니면 NO를 순서대로 출력한다.
 *
 * 제한
 * 2 ≤ K ≤ 5
 * 1 ≤ V ≤ 20,000
 * 1 ≤ E ≤ 200,000
 * 예제 입력 1
 * 2
 * 3 2
 * 1 3
 * 2 3
 * 4 4
 * 1 2
 * 2 3
 * 3 4
 * 4 2
 * 예제 출력 1
 * YES
 * NO
 * */

/*
* 정점 - 1 ~ V 차례대로
* 각 테스트 케이스 첫줄 정점의 개수 V, 간선의 개수 E
* */

private const val IS_NOT_SET = -1
private lateinit var state: IntArray
private lateinit var graph: Array<MutableList<Int>>

fun `1707-이분 그래프`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val k = br.readLine().toInt()
    val stringBuilder = StringBuilder()

    repeat(k){
        val (vertex, edge) = br.readLine().split(" ").map { it.toInt() }
        var result = "YES"

        graph = Array(vertex + 1){ mutableListOf() }
        state = IntArray(vertex + 1){ IS_NOT_SET }

        repeat(edge){
            val token = java.util.StringTokenizer(br.readLine())
            val from = token.nextToken().toInt()
            val to = token.nextToken().toInt()

            graph[from].add(to)
            graph[to].add(from)
        }

        // 미연결 그래프일 수 있으므로 bfs 를 반복 실행
        for(i in 1 .. vertex){
            // 시작 정점이 아직 설정되지 않았따면
            if(state[i] == IS_NOT_SET) {
                if(!bfs(i)){
                    result = "NO"
                    break
                }
            }
        }

        if(stringBuilder.isNotEmpty()) stringBuilder.append("\n")
        stringBuilder.append(result)
    }

    bw.write(stringBuilder.toString())
    bw.flush()
    bw.close()
    br.close()
}

private fun bfs(start: Int): Boolean {
    val arrayDeque = ArrayDeque<Int>()

    arrayDeque.addLast(start)
    state[start] = 0

    while(arrayDeque.isNotEmpty()){
        val from = arrayDeque.removeFirst()

        for(to in graph[from]){
            // 대상 정점이 설정되지 않은 상태라면 이분 그래프이므로 현재 정점의 값과 다른 값을 준다.
            if(state[to] == IS_NOT_SET){
                state[to] = if(state[from] == 1) 0 else 1
                arrayDeque.addLast(to)
            }
            // 현재 정점과 대상 정점이 같다면 이분 그래프가 아니다.
            else if(state[from] == state[to]) return false
        }
    }

    return true
}