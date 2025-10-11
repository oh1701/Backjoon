package binary_search

import java.util.StringTokenizer

/**
 * 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
 *
 * 예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에
 * 가장 긴 증가하는 부분 수열은 A = {10, 20, 30, 50} 이고, 길이는 4이다.
 *
 * 입력
 * 첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다.
 *
 * 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000,000)
 *
 * 출력
 * 첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.
 *
 * 예제 입력 1
 * 6
 * 10 20 10 30 20 50
 * 예제 출력 1
 * 4
 * */
fun `12015-가장 긴 증가하는 부분 수열 2`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt()
    val token = StringTokenizer(br.readLine())
    val arr = IntArray(n){ token.nextToken().toInt() }
    val list = mutableListOf<Int>()

    for(target in arr){
        var targetIdx = list.binarySearch(target)

        /*
        * 만약 키를 찾지 못해서 -(low + 1) 로 리턴되는 경우
        * 최대 -1 이므로 +1 하고 - 를 넣어주어 최소 0으로 만들어준다.
        * */
        if(targetIdx < 0){
            targetIdx = -(targetIdx + 1)
        }

        /*
        * 만약 list 에 1, 2, 3이 존재하고 찾는 값이 4라면
        * (1) low 0, high 2 이므로 mid = 1, midval = 2.
        * 2 compareTo 4 는 2 < 4 이기에 -1 이므로 low = mid + 1
        *
        * (2) low 2, high 2 이므로 Mid = 2, midval = 3
        * 3 compareTo 4 는 3 < 4 이기에 -1 이므로 low = mid + 1
        *
        * (3) low 3, high 2 종료
        * -> return -(3 + 1) 이므로 -4로 리턴.
        *
        * (4) -4 이므로 targetIdx < 0을 조건을 타고 3 으로 변경됨.
        * list[3] = 4
        *
        * 결과 list = [1, 2, 3, 4]
        * */
        if(targetIdx == list.size){
            list.add(target)
        } else {
            list[targetIdx] = target
        }
    }

    bw.write(list.size.toString())
    bw.flush()
    bw.close()
    br.close()
}