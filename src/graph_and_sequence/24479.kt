package graph_and_sequence

import java.util.*

/**
 * 문제
 * 오늘도 서준이는 깊이 우선 탐색(DFS) 수업 조교를 하고 있다.
 * 아빠가 수업한 내용을 학생들이 잘 이해했는지 문제를 통해서 확인해보자.
 *
 * N개의 정점과 M개의 간선으로 구성된 무방향 그래프(undirected graph)가 주어진다.
 * 정점 번호는 1번부터 N번이고 모든 간선의 가중치는 1이다.
 * 정점 R에서 시작하여 깊이 우선 탐색으로 노드를 방문할 경우 노드의 방문 순서를 출력하자.
 *
 * 깊이 우선 탐색 의사 코드는 다음과 같다. 인접 정점은 오름차순으로 방문한다.
 *
 * dfs(V, E, R) {  # V : 정점 집합, E : 간선 집합, R : 시작 정점
 *     visited[R] <- YES;  # 시작 정점 R을 방문 했다고 표시한다.
 *     for each x ∈ E(R)  # E(R) : 정점 R의 인접 정점 집합.(정점 번호를 오름차순으로 방문한다)
 *         if (visited[x] = NO) then dfs(V, E, x);
 * }
 *
 * 입력
 * 첫째 줄에 정점의 수 N (5 ≤ N ≤ 100,000), 간선의 수 M (1 ≤ M ≤ 200,000), 시작 정점 R (1 ≤ R ≤ N)이 주어진다.
 *
 * 다음 M개 줄에 간선 정보 u v가 주어지며 정점 u와 정점 v의 가중치 1인 양방향 간선을 나타낸다.
 * (1 ≤ u < v ≤ N, u ≠ v) 모든 간선의 (u, v) 쌍의 값은 서로 다르다.
 *
 * 출력
 * 첫째 줄부터 N개의 줄에 정수를 한 개씩 출력한다.
 * i번째 줄에는 정점 i의 방문 순서를 출력한다.
 * 시작 정점의 방문 순서는 1이다.
 * 시작 정점에서 방문할 수 없는 경우 0을 출력한다.
 *
 * 예제 입력 1
 * 5 5 1
 * 1 4
 * 1 2
 * 2 3
 * 2 4
 * 3 4
 * 예제 출력 1
 * 1
 * 2
 * 3
 * 4
 * 0
 * 정점 1번에서 정점 2번을 방문한다.
 * 정점 2번에서 정점 3번을 방문한다.
 * 정점 3번에서 정점 4번을 방문한다.
 * 정점 5번은 정점 1번에서 방문할 수 없다.
 * */


/*
* 무방향 비가중치 그래프
* u 와 v 의 값은 다르고, 모든 간선의 쌍 값도 다르다.
* 시작 정점은 1
* */

private lateinit var adjList: Array<MutableList<Int>>
private lateinit var visitedArr: IntArray
private var stringBuilder = StringBuilder()
private var visitCount = 0

fun `24479-알고리즘 수업 - 깊이 우선 탐색 1`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val graphToken = StringTokenizer(br.readLine())
    val n = graphToken.nextToken().toInt()
    val m = graphToken.nextToken().toInt()
    val r = graphToken.nextToken().toInt()

    /*
    * 인접 리스트 생성. 비가중치이므로 간선 가중치는 넣지 않음
    * 1-based Index 로 설정
    * */
    visitedArr = IntArray(n + 1){ visitCount }
    adjList = Array(n + 1){ mutableListOf() }

    repeat(m){
        val token = StringTokenizer(br.readLine())
        val u = token.nextToken().toInt()
        val v = token.nextToken().toInt()

        // 무방향이므로 양방향 추가
        adjList[u].add(v)
        adjList[v].add(u)
    }

    for(i in adjList.indices) adjList[i].sort()

    visit(r)

    for(i in 1 until visitedArr.size){
        stringBuilder.appendLine("${ visitedArr[i] }")
    }

    bw.write(stringBuilder.trim().toString())
    bw.flush()
    bw.close()
    br.close()
}

/*
* if(visitedArr[to] == 0) return 에서 for 문 내부에서 확인하는 거로 변경하였음.
* 함수 호출을 적게하여 시간을 더 빠르게 하기 위해 변경.
* */
private fun visit(from: Int){
    visitedArr[from] = ++visitCount
    for(to in adjList[from]){
        if(visitedArr[to] == 0) {
            visit(to)
        }
    }
}

private fun visitIterative(startNode: Int) {
    val stack = ArrayDeque<Int>()
    stack.addLast(startNode)

    while (stack.isNotEmpty()) {
        val from = stack.removeLast()

        if (visitedArr[from] != 0) {
            continue
        }

        visitedArr[from] = ++visitCount

        // ★★★ 핵심: 역순으로 스택에 넣어야 오름차순으로 방문 가능 ★★★
        for (i in adjList[from].indices.reversed()) {
            val to = adjList[from][i]
            if (visitedArr[to] == 0) {
                stack.addLast(to)
            }
        }
    }
}