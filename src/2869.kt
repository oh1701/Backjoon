import java.util.StringTokenizer

/*
땅 위에 달팽이가 있다. 이 달팽이는 높이가 V미터인 나무 막대를 올라갈 것이다.

달팽이는 낮에 A미터 올라갈 수 있다. 하지만, 밤에 잠을 자는 동안 B미터 미끄러진다. 또, 정상에 올라간 후에는 미끄러지지 않는다.

달팽이가 나무 막대를 모두 올라가려면, 며칠이 걸리는지 구하는 프로그램을 작성하시오.

입력
첫째 줄에 세 정수 A, B, V가 공백으로 구분되어서 주어진다. (1 ≤ B < A ≤ V ≤ 1,000,000,000)

출력
첫째 줄에 달팽이가 나무 막대를 모두 올라가는데 며칠이 걸리는지 출력한다.

예제 입력 1
2 1 5
예제 출력 1
4
예제 입력 2
5 1 6
예제 출력 2
2
예제 입력 3
100 99 1000000000
예제 출력 3
999999901
*/

fun `2869-달팽이는 올라가고 싶다`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val token = StringTokenizer(br.readLine())
    val a = token.nextToken().toInt()
    val b = token.nextToken().toInt()
    val v = token.nextToken().toInt()
    val subtractGoal = (v - a)
    val dayUp = a - b
    val n = when {
        subtractGoal % dayUp == 0 -> subtractGoal / dayUp + 1
        else -> subtractGoal / dayUp + 2
    }

    bw.write(n.toString())
    bw.flush()
    bw.close()
    br.close()
}