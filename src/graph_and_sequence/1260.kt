package graph_and_sequence

/**
 * 문제
 * 그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오.
 * 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다.
 * 정점 번호는 1번부터 N번까지이다.
 *
 * 입력
 * 첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다.
 * 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다.
 * 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다.
 * 입력으로 주어지는 간선은 양방향이다.
 *
 * 출력
 * 첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다.
 * V부터 방문된 점을 순서대로 출력하면 된다.
 *
 * 예제 입력 1
 * 4 5 1
 * 1 2
 * 1 3
 * 1 4
 * 2 4
 * 3 4
 * 예제 출력 1
 * 1 2 4 3
 * 1 2 3 4
 *
 * 예제 입력 2
 * 5 5 3
 * 5 4
 * 5 2
 * 1 2
 * 3 4
 * 3 1
 * 예제 출력 2
 * 3 1 2 5 4
 * 3 1 4 2 5
 * 예제 입력 3
 * 1000 1 1000
 * 999 1000
 * 예제 출력 3
 * 1000 999
 * 1000 999
 * */

/*
* 정점 번호가 작은 것부터 방문이므로 오름차순
* 양방향 그래프.
* 정점 번호는 1 ~ N
*
* 첫째줄 DFS, 둘째줄 BFS.
* 정점 N, 간선 M, 시작점 V
*
* 방문하는 정점을 순서대로 출력해야한다.
* */

private lateinit var adjacencyList: Array<MutableList<Int>>
private lateinit var visitedArr: BooleanArray
private var stringBuilder = StringBuilder()
private val dequeue = ArrayDeque<Int>()

fun `1260-DFS와 BFS`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val (n, m, v) = br.readLine().split(" ").map { it.toInt() }

    adjacencyList = Array(n + 1){ mutableListOf()}
    visitedArr = BooleanArray(n + 1)

    repeat(m){
        val token = java.util.StringTokenizer(br.readLine())
        val from = token.nextToken().toInt()
        val to = token.nextToken().toInt()

        adjacencyList[from].add(to)
        adjacencyList[to].add(from)
    }

    // 인접리스트 오름차순 정렬 후 dfs 진행
    adjacencyList.forEach { it.sort() }

    dfs(v)

    // 초기화 및 줄넘김 처리
    dequeue.fill(0)
    visitedArr.fill(false)
    stringBuilder.append("\n")

    bfs(v)

    bw.write(stringBuilder.toString())
    bw.flush()
    bw.close()
    br.close()
}

private fun dfs(from: Int){
    dequeue.addLast(from)

    while (dequeue.isNotEmpty()){
        val from = dequeue.removeLast()

        if(visitedArr[from]) continue

        visitedArr[from] = true

        if(stringBuilder.isNotEmpty()){
            stringBuilder.append(" ")
        }
        stringBuilder.append("${from}")

        for(to in adjacencyList[from].asReversed()){
            if(!visitedArr[to]){
                dequeue.addLast(to)
            }
        }
    }
}

private fun bfs(from: Int){
    dequeue.addFirst(from)
    visitedArr[from] = true
    stringBuilder.append("${from}")

    while (dequeue.isNotEmpty()){
        val from = dequeue.removeFirst()

        for(to in adjacencyList[from]){
            if(!visitedArr[to]){
                visitedArr[to] = true
                stringBuilder.append(" ${to}")
                dequeue.addLast(to)
            }
        }
    }
}