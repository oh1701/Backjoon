package divisors_multiples_primes

import kotlin.math.sqrt

/**
 * 베르트랑 공준은 임의의 자연수 n에 대하여, n보다 크고, 2n보다 작거나 같은 소수는 적어도 하나 존재한다는 내용을 담고 있다.
 *
 * 이 명제는 조제프 베르트랑이 1845년에 추측했고, 파프누티 체비쇼프가 1850년에 증명했다.
 *
 * 예를 들어, 10보다 크고, 20보다 작거나 같은 소수는 4개가 있다.
 * (11, 13, 17, 19) 또, 14보다 크고, 28보다 작거나 같은 소수는 3개가 있다. (17,19, 23)
 *
 * 자연수 n이 주어졌을 때, n보다 크고, 2n보다 작거나 같은 소수의 개수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 케이스는 n을 포함하는 한 줄로 이루어져 있다.
 *
 * 입력의 마지막에는 0이 주어진다.
 *
 * 출력
 * 각 테스트 케이스에 대해서, n보다 크고, 2n보다 작거나 같은 소수의 개수를 출력한다.
 *
 * 제한
 * 1 ≤ n ≤ 123,456
 *
 * 예제 입력 1
 * 1
 * 10
 * 13
 * 100
 * 1000
 * 10000
 * 100000
 * 0
 * 예제 출력 1
 * 1
 * 4
 * 3
 * 21
 * 135
 * 1033
 * 8392
 *
 */

/*
* 에라토스체네스의 체를 이용하면 훨씬 빨라진다.
*
* 예) 2는 소수이므로 2의 배수는 모두 제거한다.
* 3은 소수이므로 3의 배수도 모두 제거한다.
* 5도 소수이므로 5의 배수도 모두 제거한다.. 반복
* */
fun `4948-베르트랑 공준`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val size = 123456 * 2
    val arr = IntArray(size + 1){ 1 }
    val stringBuilder = StringBuilder()

    arr[0] = 0
    arr[1] = 0

    // 에라토스테네스의 체 사용하여 배수들 0으로 변경
    for (i in 2..sqrt(size.toDouble()).toInt()) {
        if (arr[i] == 1) {
            for (j in i * i..size step i) {
                arr[j] = 0
            }
        }
    }

    while (true) {
        val n = br.readLine().toInt()
        if (n == 0) break

        val nn = 2 * n
        var count = 0

        for (i in (n + 1)..nn) {
            if (arr[i] == 1) count++
        }

        stringBuilder.appendLine(count)
    }

    bw.write(stringBuilder.toString())
    bw.flush()
    bw.close()
    br.close()
}

// 에라토스테네스의 체 사용이 훨씬 빠르기에 폐기
//private fun isPrime(n: Int): Boolean {
//    return when {
//        n <= 1 -> false
//        n <= 3 -> true
//        n % 2 == 0 -> false
//        else -> {
//            (3 .. sqrt(n.toDouble()).toInt()).step(2).none { n % it == 0 }
//        }
//    }
//}