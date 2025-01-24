import java.util.StringTokenizer

/*
N*M크기의 두 행렬 A와 B가 주어졌을 때, 두 행렬을 더하는 프로그램을 작성하시오.

첫째 줄에 행렬의 크기 N 과 M이 주어진다.
둘째 줄부터 N개의 줄에 행렬 A의 원소 M개가 차례대로 주어진다.
이어서 N개의 줄에 행렬 B의 원소 M개가 차례대로 주어진다.
N과 M은 100보다 작거나 같고, 행렬의 원소는 절댓값이 100보다 작거나 같은 정수이다.

첫째 줄부터 N개의 줄에 행렬 A와 B를 더한 행렬을 출력한다. 행렬의 각 원소는 공백으로 구분한다.

입력
3 3

1 1 1
2 2 2
0 1 0

3 3 3
4 4 4
5 5 100

출력
4 4 4
6 6 6
5 6 100
*/

fun `2738번-행렬덧셈`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val baseToken = StringTokenizer(br.readLine())
    val n = baseToken.nextToken().toInt()
    val m = baseToken.nextToken().toInt()
    val arr = Array(n){ IntArray(m) }

    repeat(2){
        for(i in 0..< n){
            val token = StringTokenizer(br.readLine())

            for(j in 0..< m){
                arr[i][j] += token.nextToken().toInt()
            }
        }
    }

    val sb = StringBuilder()

    for(i in 0..< n){
        for(j in 0..< m){
            sb.append(arr[i][j].toString() + " ")
        }
        sb.append("\n")
    }

    bw.write(sb.toString())
    bw.flush()
    bw.close()
    br.close()
}