import java.util.StringTokenizer
import kotlin.math.sqrt

/*
주어진 수 N개 중에서 소수가 몇 개인지 찾아서 출력하는 프로그램을 작성하시오.

입력
첫 줄에 수의 개수 N이 주어진다. N은 100이하이다. 다음으로 N개의 수가 주어지는데 수는 1,000 이하의 자연수이다.

출력
주어진 수들 중 소수의 개수를 출력한다.

예제 입력 1
4
1 3 5 7
예제 출력 1
3
*/

fun `1978-소수찾기`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt()
    val token = StringTokenizer(br.readLine())
    var count = 0

    repeat(n){
        val num = token.nextToken().toInt()

        if(num == 1) return@repeat

        for(i in 2 .. sqrt(num.toDouble()).toInt()){
            if(num % i == 0){
                return@repeat
            }
        }

        count++
    }

    bw.write(count.toString())
    bw.flush()
    bw.close()
    br.close()
}

interface A {
    fun a() = 1
}

class B : A {


}