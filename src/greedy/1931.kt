package greedy

import java.util.StringTokenizer

/**
 * 한 개의 회의실이 있는데 이를 사용하고자 하는 N개의 회의에 대하여 회의실 사용표를 만들려고 한다.
 * 각 회의 I에 대해 시작시간과 끝나는 시간이 주어져 있고, 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수를 찾아보자.
 * 단, 회의는 한번 시작하면 중간에 중단될 수 없으며 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다.
 * 회의의 시작시간과 끝나는 시간이 같을 수도 있다.
 * 이 경우에는 시작하자마자 끝나는 것으로 생각하면 된다.
 *
 * 입력
 * 첫째 줄에 회의의 수 N(1 ≤ N ≤ 100,000)이 주어진다.
 * 둘째 줄부터 N+1 줄까지 각 회의의 정보가 주어지는데 이것은 공백을 사이에 두고 회의의 시작시간과 끝나는 시간이 주어진다.
 * 시작 시간과 끝나는 시간은 231-1보다 작거나 같은 자연수 또는 0이다.
 *
 * 출력
 * 첫째 줄에 최대 사용할 수 있는 회의의 최대 개수를 출력한다.
 *
 * 예제 입력 1
 * 11
 * 1 4
 * 3 5
 * 0 6
 * 5 7
 * 3 8
 * 5 9
 * 6 10
 * 8 11
 * 8 12
 * 2 13
 * 12 14
 * 예제 출력 1
 * 4
 *
 * 힌트
 * (1,4), (5,7), (8,11), (12,14) 를 이용할 수 있다.
 * */

/*
* [0, 6], [1, 4], [2, 13], [3, 5], [5, 7], [6, 10], [8, 11], [12, 14]
*
 * 1. 회의 시작과 끝 시간은 같을 수 있으며, 이것은 시작하자마자 끝나는 것으로 간주
 * 2. 끝남과 동시에 다음 회의가 시작될 수 있고, 도중에 중단될 수 없음
 * 3. 회의의 수 N 은 1 - 100_000 사이
 * 4. 시작 시간과 끝나는 시간은 2의 31승 - 1보다 작거나 같은 자연수 또는 0
 *
 * 1. 시작 시간 Grouping 하고 끝나는 시간이 가장 적은 것만 뽑기
 * 2. 현재 value 가 다음 key 보다 이상이면 (value - key) 와 다음 (value - key) 를 비교하고, 현재가 더 적으면 현재를 진행하며 다음것은 폐기
 *
 * 최악은 10만 곱하기 10만?
 * IntArray 사이즈를 2의 31승 번 하기에는 너무 많다.
 *
 * 끝나는 시간보다 크거나 같은 시작 시간이 몇개있는지 파악하기?
* */

fun `1931-회의실 배정`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt()
    // 끝나는 시간 오름차순 정렬 후 시작 시간 오름차 순 정렬
    val meetingArr = Array(n){
        val token = StringTokenizer(br.readLine())
        val start = token.nextToken().toInt()
        val end = token.nextToken().toInt()

        start to end
    }.sortedWith(compareBy({ it.second }, { it.first }))
    var count = 0
    var lastEndTime = 0

    /*
    * 마지막 끝난 시간보다 현재 미팅 시작 시간이 크거나 같은 경우 진행
    * */
    for(meeting in meetingArr){
        if(meeting.first >= lastEndTime){
            count++
            lastEndTime = meeting.second
        }
    }

    bw.write(count.toString())
    bw.flush()
    bw.close()
    br.close()
}