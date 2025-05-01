package combinatorics

/*
* 1. 자주 나오면 앞에 배치
* 2. 단어가 길수록 앞에 배치
* 3. 사전 순으로 앞에 배치
*
* m 이상인거만 외운다
* */
//7 4
/*
* appearance
append
attendance
swim
swift
swift
swift
mouse
wallet
mouse
ice
age
* */
fun `20920-영단어 암기는 괴로워`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val wordMap = HashMap<String, Int>()

    repeat(n){
        val word = br.readLine()

        if(word.length >= m){
            wordMap[word] = (wordMap[word] ?: 0) + 1
        }
    }

    /*
    * 많이 나온게 앞에, 글자가 긴 게 앞에, 사전순으로 먼저 나오는게 앞이므로
    * 숫자가 큰게 앞 (내림차), 글자가 긴게 앞(내림차), a ~ z 로 이어지는 사전순(오름차) 로 정렬해야함
    * */
    val list = wordMap.keys.sortedWith(
        compareByDescending<String> { wordMap[it] }
            .thenByDescending { it.length }
            .thenBy{ it })

    println(list.joinToString("\n") { it })
}