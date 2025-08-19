package dynamic_programming

/**
 * 오른쪽 그림과 같이 삼각형이 나선 모양으로 놓여져 있다.
 * 첫 삼각형은 정삼각형으로 변의 길이는 1이다.
 * 그 다음에는 다음과 같은 과정으로 정삼각형을 계속 추가한다.
 * 나선에서 가장 긴 변의 길이를 k라 했을 때, 그 변에 길이가 k인 정삼각형을 추가한다.
 *
 * 파도반 수열 P(N)은 나선에 있는 정삼각형의 변의 길이이다.
 * P(1)부터 P(10)까지 첫 10개 숫자는 1, 1, 1, 2, 2, 3, 4, 5, 7, 9이다.
 *
 * N이 주어졌을 때, P(N)을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있고, N이 주어진다. (1 ≤ N ≤ 100)
 *
 * 출력
 * 각 테스트 케이스마다 P(N)을 출력한다.
 *
 * 예제 입력 1
 * 2
 * 6
 * 12
 * 예제 출력 1
 * 3
 * 16
 * */

/*
* n <= 3 -> 1
* n <= 5 -> 2
* n == 6 -> 3
* n > 7 -> p(n) = p(n - 1) + p(n - 5)
* 1 1 1(3) 2 2(5) 3(6) 4(7) 5(8) 7(9) 9(10)
*
* n == 9 -> 5 + 2
* n == 10 = 7 + 2
* */

private val pArr = LongArray(101)
private var registeredIndex = 0

fun `9461-파도반 수열`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val t = br.readLine().toInt()

    for(i in 1 .. 6){
        when {
            i <= 3 -> pArr[i] = 1
            i <= 5 -> pArr[i] = 2
            else -> pArr[i] = 3
        }
    }
    registeredIndex = 6
    repeat(t){
        val n = br.readLine().toInt()
        bw.write("${dp(n)}\n")
    }

    bw.flush()
    bw.close()
    br.close()
}

private fun dp(n: Int): Long {
    if(registeredIndex >= n){
        return pArr[n]
    }

    for(i in registeredIndex + 1 .. n){
        pArr[i] = pArr[i - 1] + pArr[i - 5]
        registeredIndex = i
    }

    return pArr[n]
}