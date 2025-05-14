package recursion

/**
 * 재귀적인 패턴으로 별을 찍어 보자. N이 3의 거듭제곱(3, 9, 27, ...)이라고 할 때, 크기 N의 패턴은 N×N 정사각형 모양이다.
 *
 * 크기 3의 패턴은 가운데에 공백이 있고, 가운데를 제외한 모든 칸에 별이 하나씩 있는 패턴이다.
 *
 * ***
 * * *
 * ***
 * N이 3보다 클 경우, 크기 N의 패턴은 공백으로 채워진 가운데의 (N/3)×(N/3) 정사각형을 크기 N/3의 패턴으로 둘러싼 형태이다.
 * 예를 들어 크기 27의 패턴은 예제 출력 1과 같다.
 *
 * 입력
 * 첫째 줄에 N이 주어진다. N은 3의 거듭제곱이다. 즉 어떤 정수 k에 대해 N=3k이며, 이때 1 ≤ k < 8이다.
 *
 * 출력
 * 첫째 줄부터 N번째 줄까지 별을 출력한다.
 *
 * TODO 추후 다시 풀기. GPT 도움 받아 풀었음
 * */
/*
* n 이 3일때 1, 1 은 비어있고 n 이 9일때 1, 1 및 3, 3 이, 27일 때 [1, 1] [3, 3] [9, 9] 비었음
* */
fun `2447-별 찍기 - 10`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt()
    val arr = Array(n){ CharArray(n){' '} }

    fill(arr, 0, 0, n)

    for (row in arr) {
        bw.write(row.concatToString())
        bw.newLine()
    }

    bw.flush()
    bw.close()
    br.close()
}

/*
* (x, y)에서 size×size 영역을 채우는 함수
*
* 1. n x n 에서 가운데 1/3 만 빈칸
* 2. 전체 패턴이 3 x 3 으로 나뉘며 가운데를 제외한 전체가 *
* 3. 3으로 나뉠 수 없으면 * 처리하여 return 해서 재귀 종료
* */
fun fill(arr: Array<CharArray>, x: Int, y: Int, size: Int) {
    if (size == 1) {
        arr[x][y] = '*'
        return
    }

    val step = size / 3

    for (i in 0 until 3) {
        for (j in 0 until 3) {
            if (i == 1 && j == 1) continue  // 가운데 구역은 빈 칸

            fill(arr, x + i * step, y + j * step, step)
        }
    }
}