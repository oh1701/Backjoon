package combinatorics

/**
 * 베라는 상의 N 벌과 하의 N 벌이 있다. i 번째 상의와 i 번째 하의는 모두 색상 i를 가진다. N 개의 색상은 모두 서로 다르다.
 *
 * 상의와 하의가 서로 다른 색상인 조합은 총 몇 가지일까?
 *
 * 입력
 * 입력은 아래와 같이 주어진다.
 *
 * N
 * 출력
 * 상의와 하의가 서로 다른 색상인 조합의 가짓수를 출력한다.
 *
 *
 * */
fun `15439-베라의 패션`(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    println(n * n - n)
}