package backtracking

/**
 * N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.
 *
 * N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N이 주어진다. (1 ≤ N < 15)
 *
 * 출력
 * 첫째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.
 *
 * 예제 입력 1
 * 8
 * 예제 출력 1
 * 92
 * */

/*
* 오른쪽 사선 대각선은 row + col, 왼쪽 사선 대각선은 row - col + n
* */
private lateinit var diag1Arr: BooleanArray
private lateinit var diag2Arr: BooleanArray
private lateinit var columnArr: BooleanArray
private var n: Int = 0
private var count: Int = 0

fun `9663-N-Queen`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    n = br.readLine().toInt()
    columnArr = BooleanArray(n)
    diag1Arr = BooleanArray(n * 2)
    diag2Arr = BooleanArray(n * 2)

    solve(0)

    bw.write(count.toString())
    bw.flush()
    bw.close()
    br.close()
}

private fun solve(row: Int){
    if(row == n){
        count++
        return
    }

    for(col in 0 until n){
        // + n 은 음수를 방지하기 위해 사용
        if(columnArr[col] || diag1Arr[row + col] || diag2Arr[row - col + n]) continue

        columnArr[col] = true
        diag1Arr[row + col] = true
        diag2Arr[row - col + n] = true

        solve(row + 1)

        columnArr[col] = false
        diag1Arr[row + col] = false
        diag2Arr[row - col + n] = false
    }
}