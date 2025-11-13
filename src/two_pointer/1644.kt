package two_pointer

import kotlin.system.measureTimeMillis

/**
 * 문제
 * 하나 이상의 연속된 소수의 합으로 나타낼 수 있는 자연수들이 있다. 몇 가지 자연수의 예를 들어 보면 다음과 같다.
 *
 * 3 : 3 (한 가지)
 * 41 : 2+3+5+7+11+13 = 11+13+17 = 41 (세 가지)
 * 53 : 5+7+11+13+17 = 53 (두 가지)
 * 하지만 연속된 소수의 합으로 나타낼 수 없는 자연수들도 있는데, 20이 그 예이다.
 * 7+13을 계산하면 20이 되기는 하나 7과 13이 연속이 아니기에 적합한 표현이 아니다.
 * 또한 한 소수는 반드시 한 번만 덧셈에 사용될 수 있기 때문에, 3+5+5+7과 같은 표현도 적합하지 않다.
 *
 * 자연수가 주어졌을 때, 이 자연수를 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 자연수 N이 주어진다. (1 ≤ N ≤ 4,000,000)
 *
 * 출력
 * 첫째 줄에 자연수 N을 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 출력한다.
 *
 * 예제 입력 1
 * 20
 * 예제 출력 1
 * 0
 * 예제 입력 2
 * 3
 * 예제 출력 2
 * 1
 * 예제 입력 3
 * 41
 * 예제 출력 3
 * 3
 * 예제 입력 4
 * 53
 * 예제 출력 4
 * 2
 * */

fun `1644-소수의 연속합 - 에라토스테네스의 체`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt()
    val primeList = mutableListOf<Int>()
    val isPrime = BooleanArray(n + 1){ true }

    isPrime[0] = false
    isPrime[1] = false

    for(i in 2 .. kotlin.math.sqrt(n.toDouble()).toInt()){
        if(isPrime[i]){
            for(j in i + i .. n step i){
                isPrime[j] = false
            }
        }
    }

    for(i in isPrime.indices){ if(isPrime[i]) primeList.add(i) }

    var start = 0
    var end = 0
    var currentSum = 0
    var count = 0
    val limit = primeList.size - 1

    while (true){
        when {
            // 오름차순인 소수 배열이므로 currentSum 이 N 보다 크거나 같으면 start 만큼 빼주고 start 를 늘려준다.
            currentSum >= n -> {
                if(currentSum == n) count++
                currentSum -= primeList[start]
                start++
            }
            // currentSum 이 n 보다 작고 end 가 limit 보다 큰 경우는 이미 끝까지 순회하였다는 의미이다.
            end > limit -> break
            // N 보다 작으므로 currentSum 에 prime[end] 를 추가해주고 end 를 늘려준다.
            else -> {
                currentSum += primeList[end]
                end++
            }
        }
    }

    bw.write(count.toString())
    bw.flush()
    bw.close()
    br.close()
}

fun `1644-소수의 연속합 - 오일러의 체 `(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt()
    val primeList = mutableListOf<Int>()
    val numberArr = IntArray(n + 1){ it }

    // n 이하의 모든 소수를 확인하기 위해 순회한다.
    for(i in 2 .. n){
        // numberArr[i] 가 i 라면 이전 소수값 판별에서 처리되지 않은 값이므로 소수가 맞다.
        if(numberArr[i] == i) primeList.add(i)

        // 소수로 판별된 리스트를 순회한다.
        for(p in primeList){
            // 소수로 등록된 값이 현재 값보다 크거나, p * i 가 N 보다 크면 값을 벗어난 것이므로 종료
            if(p > numberArr[i] || p * i > n) break
            // i * p = 소인수 (가장 작게 나눠지는 값) 이다.
            numberArr[i * p] = p
        }
    }

    var start = 0
    var end = 0
    var currentSum = 0
    var count = 0
    val limit = primeList.size - 1

    while (true){
        when {
            currentSum >= n -> {
                if(currentSum == n) count++
                currentSum -= primeList[start]
                start++
            }
            end > limit -> break
            else -> {
                currentSum += primeList[end]
                end++
            }
        }
    }

    bw.write(count.toString())
    bw.flush()
    bw.close()
    br.close()
}