package backtracking

import java.util.StringTokenizer

/**
 * 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 *
 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
 * 입력
 * 첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
 *
 * 출력
 * 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
 *
 * 수열은 사전 순으로 증가하는 순서로 출력해야 한다.
 *
 * 예제 입력 1
 * 3 1
 * 예제 출력 1
 * 1
 * 2
 * 3
 * 예제 입력 2
 * 4 2
 * 예제 출력 2
 * 1 2
 * 1 3
 * 1 4
 * 2 1
 * 2 3
 * 2 4
 * 3 1
 * 3 2
 * 3 4
 * 4 1
 * 4 2
 * 4 3
 *
 * 예제 입력 3
 * 4 4
 * 예제 출력 3
 * 1 2 3 4
 * 1 2 4 3
 * 1 3 2 4
 * 1 3 4 2
 * 1 4 2 3
 * 1 4 3 2
 * 2 1 3 4
 * 2 1 4 3
 * 2 3 1 4
 * 2 3 4 1
 * 2 4 1 3
 * 2 4 3 1
 * 3 1 2 4
 * 3 1 4 2
 * 3 2 1 4
 * 3 2 4 1
 * 3 4 1 2
 * 3 4 2 1
 * 4 1 2 3
 * 4 1 3 2
 * 4 2 1 3
 * 4 2 3 1
 * 4 3 1 2
 * 4 3 2 1
 * */

// TODO 다시 풀기
fun `15649-N과 M(1)`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val arr = IntArray(m) // 선택한 숫자를 담을 배열
    val visited = BooleanArray(n) // 방문 여부를 저장할 배열
    val sb = StringBuilder()

    fun dfs(depth: Int) {
        if(depth == m) {
            arr.forEach { sb.append("$it ") }
            sb.append('\n')
            return
        }

        for(i in 0 until n) {
            if(!visited[i]) {
                visited[i] = true // 숫자를 선택한 상태로 변경
                arr[depth] = i + 1 // 배열에 값 저장
                dfs(depth + 1) // 다음 숫자 선택을 위한 재귀 호출
                visited[i] = false // 숫자를 선택하지 않은 상태로 변경 (백트래킹)
            }
        }
    }

    dfs(0)

    bw.write("$sb")
    bw.flush()
    bw.close()
    br.close()
}