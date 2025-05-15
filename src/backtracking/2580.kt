package backtracking

import java.util.StringTokenizer

/**
 * 아홉 줄에 걸쳐 한 줄에 9개씩 게임 시작 전 스도쿠판 각 줄에 쓰여 있는 숫자가 한 칸씩 띄워서 차례로 주어진다.
 * 스도쿠 판의 빈 칸의 경우에는 0이 주어진다. 스도쿠 판을 규칙대로 채울 수 없는 경우의 입력은 주어지지 않는다.
 *
 * 출력
 * 모든 빈 칸이 채워진 스도쿠 판의 최종 모습을 아홉 줄에 걸쳐 한 줄에 9개씩 한 칸씩 띄워서 출력한다.
 *
 * 스도쿠 판을 채우는 방법이 여럿인 경우는 그 중 하나만을 출력한다.
 * */
private val board = Array(9) { IntArray(9) }
private val rowCheck = Array(9) { BooleanArray(10) }
private val colCheck = Array(9) { BooleanArray(10) }
private val boxCheck = Array(9) { BooleanArray(10) }
private val sb = StringBuilder()

fun `2580-스도쿠`() {
    val br = System.`in`.bufferedReader()

    for (i in 0 until 9) {
        val line = br.readLine().split(" ").map { it.toInt() }
        for (j in 0 until 9) {
            val num = line[j]
            board[i][j] = num

            if (num != 0) {
                rowCheck[i][num] = true
                colCheck[j][num] = true
                boxCheck[getBoxIndex(i, j)][num] = true
            }
        }
    }

    solve(0, 0)

    // 출력
    board.forEach { row ->
        sb.appendLine(row.joinToString(" "))
    }
    println(sb)
}

fun solve(row: Int, col: Int): Boolean {
    if (row == 9) return true
    if (col == 9) return solve(row + 1, 0)
    if (board[row][col] != 0) return solve(row, col + 1)

    for (num in 1..9) {
        val boxIdx = getBoxIndex(row, col)
        if (!rowCheck[row][num] && !colCheck[col][num] && !boxCheck[boxIdx][num]) {
            // 배치
            board[row][col] = num
            rowCheck[row][num] = true
            colCheck[col][num] = true
            boxCheck[boxIdx][num] = true

            if (solve(row, col + 1)) return true // 성공 시 종료

            // 백트래킹
            board[row][col] = 0
            rowCheck[row][num] = false
            colCheck[col][num] = false
            boxCheck[boxIdx][num] = false
        }
    }

    return false // 가능한 수가 없음 → 백트래킹
}

fun getBoxIndex(row: Int, col: Int): Int {
    return (row / 3) * 3 + (col / 3)
}