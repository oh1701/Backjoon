package algorithm

fun `24266-알고리즘 수업 - 알고리즘의 수행 시간 5`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toLong()

    bw.write("${n * n * n}\n3")
    bw.close()
    br.close()
}