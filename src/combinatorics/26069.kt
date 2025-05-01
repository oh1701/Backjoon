package combinatorics

// 총총이가 나온 이후를 기록해야한다
fun `26069-붙임성 좋은 총총이`(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val set = hashSetOf("ChongChong")

    repeat(n){
        val (i, j) = br.readLine().split(" ")

        if(i in set || j in set){
            set.add(i)
            set.add(j)
        }
    }

    println(set.size)
}