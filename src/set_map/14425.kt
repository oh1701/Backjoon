package set_map

import java.util.StringTokenizer

/**
 * 총 N개의 문자열로 이루어진 집합 S가 주어진다.
 *
 * 입력으로 주어지는 M개의 문자열 중에서 집합 S에 포함되어 있는 것이 총 몇 개인지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 문자열의 개수 N과 M (1 ≤ N ≤ 10,000, 1 ≤ M ≤ 10,000)이 주어진다.
 *
 * 다음 N개의 줄에는 집합 S에 포함되어 있는 문자열들이 주어진다.
 *
 * 다음 M개의 줄에는 검사해야 하는 문자열들이 주어진다.
 *
 * 입력으로 주어지는 문자열은 알파벳 소문자로만 이루어져 있으며, 길이는 500을 넘지 않는다. 집합 S에 같은 문자열이 여러 번 주어지는 경우는 없다.
 *
 * 출력
 * 첫째 줄에 M개의 문자열 중에 총 몇 개가 집합 S에 포함되어 있는지 출력한다.
 *
 * 예제 입력 1
 * 5 11
 * baekjoononlinejudge
 * startlink
 * codeplus
 * sundaycoding
 * codingsh
 * baekjoon
 * codeplus
 * codeminus
 * startlink
 * starlink
 * sundaycoding
 * codingsh
 * codinghs
 * sondaycoding
 * startrink
 * icerink
 * 예제 출력 1
 * 4
 * */
fun `14425-문자열집합`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val token = StringTokenizer(br.readLine())
    val n = token.nextToken().toInt()
    val m = token.nextToken().toInt()
    val setS = LinkedHashSet<String>(n)
    var cnt = 0

    repeat(n){ setS.add(br.readLine()) }

    // HashSet 에서 contains 를 하면 HashCode 기반으로 찾기 때문에 O(1) 의 상수시간 소모
    repeat(m){
        if(br.readLine() in setS){
            cnt++
        }
    }

    bw.write(cnt.toString())
    bw.flush()
    bw.close()
    br.close()
}