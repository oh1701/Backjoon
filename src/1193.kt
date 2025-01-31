/*
이와 같이 나열된 분수들을 1/1 → 1/2 → 2/1 → 3/1 → 2/2 → …
과 같은 지그재그 순서로 차례대로 1번, 2번, 3번, 4번, 5번, … 분수라고 하자.

X가 주어졌을 때, X번째 분수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 X(1 ≤ X ≤ 10,000,000)가 주어진다.

출력
첫째 줄에 분수를 출력한다.
*/

fun `1193-분수찾기`(){
    /**
     * 1. 등차수열의 합을 구하는 공식 n * (n + 1) / 2 를 우선 사용하여 n 번째 그룹을 구한다.
     * 2. n 번째 그룹이 짝수인경우 마지막 포지션 ~ 첫번째 포지션, 홀수인경우 첫번째 포지션 ~ 마지막 포지션
     * 3. 현재 그룹에서의 포지션을 구하기 위해 이전 그룹의 마지막 숫자를 구한다.
     * */
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val input = br.readLine().toInt() // 6
    var n = 1

    while (input > n * (n + 1) / 2) { // n = 3
        n++
    }

    val position = input - (n * (n - 1) / 2) // 3
    val numerator: Int
    val denominator: Int

    if (n % 2 == 1) {  // 홀수 번째 그룹 (아래에서 위로)
        numerator = n - position + 1
        denominator = position
    } else {           // 짝수 번째 그룹 (위에서 아래로)
        numerator = position
        denominator = n - position + 1
    }

    bw.write("$numerator/$denominator")
    bw.flush()
    bw.close()
    br.close()
}
