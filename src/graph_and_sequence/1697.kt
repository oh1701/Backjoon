package graph_and_sequence

/**
 * 문제
 * 수빈이는 동생과 숨바꼭질을 하고 있다.
 * 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다.
 * 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다.
 * 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
 *
 * 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
 *
 * 출력
 * 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
 *
 * 예제 입력 1
 * 5 17
 * 예제 출력 1
 * 4
 * 힌트
 * 수빈이가 5-10-9-18-17 순으로 가면 4초만에 동생을 찾을 수 있다.
 * */

/*
* 사이클이 존재하므로 그래프. 가장 빠른 시간 출력이므로 BFS.
* 루트 - N
* 노드1 - X-1
* 노드2 - X+1
* 노드3 - 2*X
* */
fun `1697-숨바꼭질`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val (n, k) = br.readLine().split(" ").map { it.toInt() }
    // first = 점, second = 시간
    val arrayDeque = ArrayDeque<Pair<Int, Int>>()
    var result = 0
    val visitedArr = BooleanArray(100_001)

    arrayDeque.add(n to result)
    visitedArr[n] = true

    while(arrayDeque.isNotEmpty()){
        val from = arrayDeque.removeFirst()
        val x = from.first
        val time = from.second

        if(x == k){
            result = time
            break
        }

        val node1 = x - 1
        val node2 = x + 1
        val node3 = 2 * x

        arrayDeque.addNode(node1, time, visitedArr)
        arrayDeque.addNode(node2, time, visitedArr)
        arrayDeque.addNode(node3, time, visitedArr)
    }

    bw.write(result.toString())
    bw.flush()
    bw.close()
    br.close()
}

/*
* k 는 0 ~ 10만이므로 제한을 준다.
* x - 1, x + 1 등에서 재방문 할 수 있기 때문에 visited 설정
* */
private fun ArrayDeque<Pair<Int, Int>>.addNode(node: Int, time: Int, visitedArr: BooleanArray){
    if(node in 0 .. 100_000 && !visitedArr[node]){
        visitedArr[node] = true
        this.addLast(node to time + 1)
    }
}