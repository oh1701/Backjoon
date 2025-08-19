package dynamic_programming

/**
 * LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는 두 수열이 주어졌을 때,
 * 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.
 *
 * 예를 들어, ACAYKP와 CAPCAK의 LCS는 ACAK가 된다.
 *
 * 입력
 * 첫째 줄과 둘째 줄에 두 문자열이 주어진다. 문자열은 알파벳 대문자로만 이루어져 있으며, 최대 1000글자로 이루어져 있다.
 *
 * 출력
 * 첫째 줄에 입력으로 주어진 두 문자열의 LCS의 길이를 출력한다.
 *
 * 예제 입력 1
 * ACAYKP
 * CAPCAK
 * 예제 출력 1
 * 4
 * */
fun `9251-LCS`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val str1 = br.readLine()
    val str2 = br.readLine()
    // 1-based Indexing 으로 변경
    val dp = Array(str1.length + 1){ IntArray(str2.length + 1) }

    for(i in 1 .. str1.length){
        for(j in 1 .. str2.length){
            /*
            * 1. 같은 글자인 경우 이전 글자까지의 중복값 + 1 (현재 같은 글자이므로 1 더함)
            * 2. 같은 글자가 아닌 경우 이전 i 나 j 글자까지 최대 중복 값을 유지
            * -> 현재 i 를 포기한 최장 or 현재 j 를 포기한 최장 중 max 값
            * */
            dp[i][j] = if(str1[i - 1] == str2[j - 1]){
                dp[i - 1][j - 1] + 1
            } else {
                kotlin.math.max(dp[i - 1][j], dp[i][j - 1])
            }
        }
    }

    bw.write(dp[str1.length][str2.length].toString())
    bw.flush()
    bw.close()
    br.close()
}
