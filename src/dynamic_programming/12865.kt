package dynamic_programming

import java.util.StringTokenizer

/**
 * 이 문제는 아주 평범한 배낭에 관한 문제이다.
 *
 * 한 달 후면 국가의 부름을 받게 되는 준서는 여행을 가려고 한다.
 * 세상과의 단절을 슬퍼하며 최대한 즐기기 위한 여행이기 때문에, 가지고 다닐 배낭 또한 최대한 가치 있게 싸려고 한다.
 *
 * 준서가 여행에 필요하다고 생각하는 N개의 물건이 있다.
 * 각 물건은 무게 W와 가치 V를 가지는데, 해당 물건을 배낭에 넣어서 가면 준서가 V만큼 즐길 수 있다.
 * 아직 행군을 해본 적이 없는 준서는 최대 K만큼의 무게만을 넣을 수 있는 배낭만 들고 다닐 수 있다.
 * 준서가 최대한 즐거운 여행을 하기 위해 배낭에 넣을 수 있는 물건들의 가치의 최댓값을 알려주자.
 *
 * 입력
 * 첫 줄에 물품의 수 N(1 ≤ N ≤ 100)과 준서가 버틸 수 있는 무게 K(1 ≤ K ≤ 100,000)가 주어진다.
 * 두 번째 줄부터 N개의 줄에 거쳐 각 물건의 무게 W(1 ≤ W ≤ 100,000)와 해당 물건의 가치 V(0 ≤ V ≤ 1,000)가 주어진다.
 *
 * 입력으로 주어지는 모든 수는 정수이다.
 *
 * 출력
 * 한 줄에 배낭에 넣을 수 있는 물건들의 가치합의 최댓값을 출력한다.
 *
 * 예제 입력 1
 * 4 7
 * 6 13
 * 4 8
 * 3 6
 * 5 12
 * 예제 출력 1
 * 14
 * */
/*
weight / value
3 / 6
4 / 8
5 / 12
6 / 13

최대무게 7. 결과값 14

* 가방에 넣을 수 있는 물건 가치의 최대값을 구해야함.
* 즉, DP 문제이다.

1. weight 기준으로 정렬 진행하면 더 쉬울듯?
2. 동일한 weight 이 있으면, value 가 더 높은것만 남기면 되지 않을까. (x) -> 동일한 weight 도 계산할 수 있어야함.

각 weight, value 를 정렬한 뒤 Pair Array 로 등록.
Dp[i][j] 에 PairArray[i][j] 값과 PairArray[i-1][j-1] 을 더한 값을 저장하기?

* */
fun `12865-평범한 배낭`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    /*
    * n : 준서가 여행에 필요하다고 생각하는 물건의 개수
    * k : 배낭의 최대 적재 가능 무게
    * */
    val (n, k) = br.readLine().split(" ").map { it.toInt() }
    val itemsArr = Array(n + 1){ IntArray(2) }
    for(i in 1 .. n){
        val token = StringTokenizer(br.readLine())
        val weight = token.nextToken().toInt()
        val value = token.nextToken().toInt()

        itemsArr[i][0] = weight
        itemsArr[i][1] = value
    }

    val dp = Array(n + 1){ IntArray(k + 1) }

    for(i in 1 .. n){
        val weight = itemsArr[i][0]
        val value = itemsArr[i][1]

        for(j in 1 .. k){
            if(j < weight){
                dp[i][j] = dp[i - 1][j]
            } else {
                /*
                * 1. 안담은 경우 = dp[i - 1][j]
                * 2. 담은 경우 = value + dp[i - 1][j - weight]
                * 담은 경우 j - weight 로 설정되는 이유는 현재 value 및 weight 를 사용함으로서, 해당 무게를 빼줘야함
                * */
                dp[i][j] = kotlin.math.max(dp[i - 1][j], value + dp[i - 1][j - weight])
            }
        }
    }

    bw.write(dp[n][k].toString())
    bw.flush()
    bw.close()
    br.close()
}