package graph_and_sequence

/**
 * 문제
 * 신종 바이러스인 웜 바이러스는 네트워크를 통해 전파된다.
 * 한 컴퓨터가 웜 바이러스에 걸리면 그 컴퓨터와 네트워크 상에서 연결되어 있는 모든 컴퓨터는 웜 바이러스에 걸리게 된다.
 *
 * 예를 들어 7대의 컴퓨터가 <그림 1>과 같이 네트워크 상에서 연결되어 있다고 하자.
 * 1번 컴퓨터가 웜 바이러스에 걸리면 웜 바이러스는 2번과 5번 컴퓨터를 거쳐
 * 3번과 6번 컴퓨터까지 전파되어 2, 3, 5, 6 네 대의 컴퓨터는 웜 바이러스에 걸리게 된다.
 * 하지만 4번과 7번 컴퓨터는 1번 컴퓨터와 네트워크상에서 연결되어 있지 않기 때문에 영향을 받지 않는다.
 *
 * 어느 날 1번 컴퓨터가 웜 바이러스에 걸렸다.
 * 컴퓨터의 수와 네트워크 상에서 서로 연결되어 있는 정보가 주어질 때,
 * 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에는 컴퓨터의 수가 주어진다.
 * 컴퓨터의 수는 100 이하인 양의 정수이고 각 컴퓨터에는 1번 부터 차례대로 번호가 매겨진다.
 * 둘째 줄에는 네트워크 상에서 직접 연결되어 있는 컴퓨터 쌍의 수가 주어진다.
 * 이어서 그 수만큼 한 줄에 한 쌍씩 네트워크 상에서 직접 연결되어 있는 컴퓨터의 번호 쌍이 주어진다.
 *
 * 출력
 * 1번 컴퓨터가 웜 바이러스에 걸렸을 때, 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 첫째 줄에 출력한다.
 *
 * 예제 입력 1
 * 7
 * 6
 * 1 2
 * 2 3
 * 1 5
 * 5 2
 * 5 6
 * 4 7
 * 예제 출력 1
 * 4
 * */

/*
* 무방향 비연결 그래프
*
* 첫째 줄 - 컴퓨터 수
* 컴퓨터의 수는 1 ~ 100.
* 각 컴퓨터에는 1번부터 차례대로 번호가 매겨짐.
*
* 둘째 줄 - 컴퓨터 쌍의 수
* 쌍의 수만큼의 줄에서 번호쌍 주어짐
*
* 1번 컴퓨터가 걸리면 걸리게 되는것. 즉, visited 인 것들만 찾으면 된다.
*
* bfs, dfs 아무렇게나 풀어도 상관 없을듯
* */
fun `2606-바이러스`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val computerCount = br.readLine().toInt()
    val pairCount = br.readLine().toInt()
    val visitedArr = BooleanArray(computerCount + 1)
    val adjacencyList = Array(computerCount + 1){ mutableListOf<Int>() }
    val queue = ArrayDeque<Int>()
    var count = 0

    repeat(pairCount){
        val token = java.util.StringTokenizer(br.readLine())
        val from = token.nextToken().toInt()
        val to = token.nextToken().toInt()

        adjacencyList[from].add(to)
        adjacencyList[to].add(from)
    }

    queue.add(1)
    visitedArr[1] = true

    while (queue.isNotEmpty()){
        val from = queue.removeFirst()

        for(to in adjacencyList[from]){
            if(!visitedArr[to]){
                count++
                visitedArr[to] = true
                queue.add(to)
            }
        }
    }

    bw.write(count.toString())
    bw.flush()
    bw.close()
    br.close()
}