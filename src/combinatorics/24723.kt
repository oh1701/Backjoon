package combinatorics

import kotlin.math.pow

/*
* https://www.acmicpc.net/problem/24723
* */
fun `24723-녹색거탑`(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    println(2.0.pow(n).toInt())
}