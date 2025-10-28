package priority_queue

import java.util.PriorityQueue
import java.util.StringTokenizer

/**
 * 문제
 * 세계적인 도둑 상덕이는 보석점을 털기로 결심했다.
 *
 * 상덕이가 털 보석점에는 보석이 총 N개 있다.
 * 각 보석은 무게 Mi와 가격 Vi를 가지고 있다.
 * 상덕이는 가방을 K개 가지고 있고, 각 가방에 담을 수 있는 최대 무게는 Ci이다.
 * 가방에는 최대 한 개의 보석만 넣을 수 있다.
 *
 * 상덕이가 훔칠 수 있는 보석의 최대 가격을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N과 K가 주어진다. (1 ≤ N, K ≤ 300,000)
 *
 * 다음 N개 줄에는 각 보석의 정보 Mi와 Vi가 주어진다. (0 ≤ Mi, Vi ≤ 1,000,000)
 *
 * 다음 K개 줄에는 가방에 담을 수 있는 최대 무게 Ci가 주어진다. (1 ≤ Ci ≤ 100,000,000)
 *
 * 모든 숫자는 양의 정수이다.
 *
 * 출력
 * 첫째 줄에 상덕이가 훔칠 수 있는 보석 가격의 합의 최댓값을 출력한다.
 *
 * 예제 입력 1
 * 2 1
 * 5 10
 * 100 100
 * 11
 * 예제 출력 1
 * 10
 *
 * 예제 입력 2
 * 3 2
 * 1 65
 * 5 23
 * 2 99
 * 10
 * 2
 * 예제 출력 2
 * 164
 * */

/*
* 첫째줄 1 <= N(보석 개수), K(가방 개수) <= 300_000
* 그다음 N개줄 각각 보석정보 0 <= Mi (보석 무게) Vi(보석 가격) <= 1_000_000
* 다음 K개줄 1 <= Ci (가방 최대 무게) <= 100_000_000
* 모두 양의 정수
*
* 출력 - 상덕이가 훔칠 수 있는 보석  가격의 합
*
* 가방에는 최대 1개만 가능
* 시간 제한 1초 (연산 1억번)
* 메모리 256MB
*
* 커스텀 클래스 Jewel 구현 후 무게, 가격 내림차순 설정
*
* 보석 무게 - 최소 (오름차)
* 보석 가격 - 최대 (내림차)
* 가방 무게 - 최소 (오름차)
*
*
* 보석 Root 의 무게와 가방 적재 가능무게 비교. 보석이 더 크면 poll 로 삭제
* */

data class Jewel(val weight: Int, val price: Long)

fun `1202-보석 도둑`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val (n, k) = br.readLine().split(" ").map{ it.toInt() }
    // 무게가 작은 보석을 우선으로 한다
    val jewelHeap = PriorityQueue<Jewel>(compareBy { it.weight })
    var sum = 0L

    repeat(n){
        val token = StringTokenizer(br.readLine())
        val weight = token.nextToken().toInt()
        val price = token.nextToken().toLong()

        jewelHeap.offer(Jewel(weight, price))
    }

    // 가방 무게 오름차 순
    val bagArr = IntArray(k){ br.readLine().toInt() }.apply { sort() }

    // 같은 무게에서 가격 내림차 순 설정하기 위해 등록
    val priceJewelHeap = PriorityQueue<Jewel>(compareByDescending { it.price })

    /*
    * 적재 무게가 작은 가방부터 순회하며, 최소 힙인 보석을 꺼낸 후 저장한다.
    * 저장하는 PriorityQueue 는 가격 순인 최대힙이므로 큰 가격이 우선된다.
    *
    * 다음 진행 시에도 반복하며 진행한다.
    * */
    for(bag in bagArr){
        while (jewelHeap.isNotEmpty() && jewelHeap.peek().weight <= bag){
            priceJewelHeap.offer(jewelHeap.poll())
        }

        if(priceJewelHeap.isNotEmpty()){
            sum += priceJewelHeap.poll().price
        }
    }

    bw.write(sum.toString())
    bw.flush()
    bw.close()
    br.close()
}