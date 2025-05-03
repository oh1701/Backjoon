package recursion

// 시작 문자, 끝 문자 동시에 2개를 주면 되고, 재귀를 통해 가운데까지 이어가면 될듯

/*
* 1. 문자열이 주어지면
* 2. 시작 문자 및 끝 문자가 같은지 판단 후 이를 기존 문자열에서 삭제
* 3. 같지 않으면 재귀를 멈추고 return. 같으면 문자열이 남지 않거나 하나만 남을때까지 만복
* 4. 몇 번을 실행했는지도 기록
* 5
AAA
ABBA
ABABA
ABCA
PALINDROME
* */
fun `25501-재귀의 귀재`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val t = br.readLine().toInt()
    val stringBuilder = StringBuilder()

    repeat(t){
        val s = br.readLine()
        val palindromePair = isPalindrome(s, 0, s.length - 1)
        stringBuilder.appendLine("${palindromePair.first} ${palindromePair.second}")
    }

    bw.write(stringBuilder.toString())
    bw.flush()
    bw.close()
    br.close()
}

private tailrec fun isPalindrome(s: String, l: Int, r: Int, recursionCnt: Int = 1): Pair<Int, Int> {
    return when {
        l >= r -> Pair(1, recursionCnt)
        s[l] == s[r] -> isPalindrome(s, l + 1, r - 1,recursionCnt + 1)
        else -> Pair(0, recursionCnt)
    }
}