package divisors_multiples_primes

import kotlin.math.sqrt


/**
 * 골드바흐의 추측: 2보다 큰 짝수는 두 소수의 합으로 나타낼 수 있다.
 * 짝수 N을 두 소수의 합으로 나타내는 표현을 골드바흐 파티션이라고 한다.
 * 짝수 N이 주어졌을 때, 골드바흐 파티션의 개수를 구해보자. 두 소수의 순서만 다른 것은 같은 파티션이다.
 *
 * 입력
 * 첫째 줄에 테스트 케이스의 개수 T (1 ≤ T ≤ 100)가 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있고, 정수 N은 짝수이고, 2 < N ≤ 1,000,000을 만족한다.
 *
 * 출력
 * 각각의 테스트 케이스마다 골드바흐 파티션의 수를 출력한다.
 *
 * 예제 입력 1
 * 5
 * 6
 * 8
 * 10
 * 12
 * 100
 * 예제 출력 1
 * 1
 * 1
 * 2
 * 1
 * 6
 * */
/*
* 에라토스테네스의 체를 이용 (n 과 n - i 가 소수인지 판단하면 된다.)
* */
fun `17103-골드바흐 파티션`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val t = br.readLine().toInt()
    val stringBuilder = StringBuilder()
    val maxSize = 1_000_001
    val sieve = BooleanArray(maxSize) { true }
    sieve[0] = false
    sieve[1] = false

    // 에라토스테네스의 체 이용
    for (i in 2..sqrt(maxSize.toDouble()).toInt()) {
        if (sieve[i]) {
            // i 는 true, i * i 및 i * i 부터 이어지는 i step 들은 false 처리.,
            for (j in i * i..<maxSize step i) {
                sieve[j] = false
            }
        }
    }

    repeat(t){
        val n = br.readLine().toInt()
        var count = 0

        // 중복되지 않은 두 수의 합을 구하므로 n / 2 만 하여 중복값 제거
        for (i in 2..n / 2) {
            if (sieve[i] && sieve[n - i]) {
                count++
            }
        }

        stringBuilder.appendLine(count)
    }

    bw.write(stringBuilder.toString())
    bw.flush()
    bw.close()
    br.close()
}