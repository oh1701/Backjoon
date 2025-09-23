package divide_and_conquer_algorithm

/**
 * 피보나치 수는 0과 1로 시작한다. 0번째 피보나치 수는 0이고, 1번째 피보나치 수는 1이다
 * 그 다음 2번째 부터는 바로 앞 두 피보나치 수의 합이 된다.
 *
 * 이를 식으로 써보면 Fn = Fn-1 + Fn-2 (n ≥ 2)가 된다.
 *
 * n=17일때 까지 피보나치 수를 써보면 다음과 같다.
 *
 * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597
 *
 * n이 주어졌을 때, n번째 피보나치 수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 n이 주어진다. n은 1,000,000,000,000,000,000보다 작거나 같은 자연수이다.
 *
 * 출력
 * 첫째 줄에 n번째 피보나치 수를 1,000,000,007으로 나눈 나머지를 출력한다.
 *
 * 예제 입력 1
 * 1000
 * 예제 출력 1
 * 517691607
 */

/*
* 1. N 을 지수로 생각하자.
* 2. 피보나치 수열의 점화식을 행렬로 변환하자.
* 3. 거듭제곱 분할 알고리즘 + 선형 변환를 통해 계산하면 O log n 이 나온다.
* */

private const val DIV_NUM: Long = 1_000_000_007L

fun `11444-피보나치 수 6`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toLong()
    val matrix = arrayOf(longArrayOf(1,1), longArrayOf(1, 0))

    when(n){
        0L -> bw.write("0")
        1L -> bw.write("1")
        else -> {
            // 시작점을 V(1)로 햇으므로 지수는 n - 1
            val result = power(matrix, n - 1)
            // V(n) = M^(n-1) * V(1) -> V(1) 은 [[1], [0]] 이므로 첫 번째 원소는 result[0][0]
            bw.write(result[0][0].toString())
        }
    }
    bw.flush()
    bw.close()
    br.close()
}

/*
F(n) = 1 * F(n - 1) + 1 * F(n - 2) -> F(n) = 1 1
F(n - 1) = 1 * F(n - 1) + 0 * F(n - 2) -> 1 0
F(n) = 1 1, F(n - 1) = 1 0

Output V(F(n)) = M([1, 1][1, 0]) * Input V (F(n-1) + F(n-2))
*/

private fun multiplication(m1: Array<LongArray>, m2: Array<LongArray>): Array<LongArray> {
    val arr = Array(2){ LongArray(2) }

    /*
    * k 는 시작부터 끝
    * arr[i][j] = m1[i][k] + m2[k][j] 이므로
    * */
    arr[0][0] = (m1[0][0] * m2[0][0] + m1[0][1] * m2[1][0]) % DIV_NUM
    arr[0][1] = (m1[0][0] * m2[0][1] + m1[0][1] * m2[1][1]) % DIV_NUM
    arr[1][0] = (m1[1][0] * m2[0][0] + m1[1][1] * m2[1][0]) % DIV_NUM
    arr[1][1] = (m1[1][0] * m2[0][1] + m1[1][1] * m2[1][1]) % DIV_NUM

    return arr
}

private fun power(matrix: Array<LongArray>, exponent: Long): Array<LongArray> {
    if(exponent == 1L){
        return matrix
    }

    val half = power(matrix, exponent / 2)
    val squaredHalf = multiplication(half, half)

    return if(exponent % 2 == 1L){
        multiplication(matrix, squaredHalf)
    } else {
        squaredHalf
    }
}