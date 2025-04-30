package combinatorics

import java.math.BigInteger
import java.util.StringTokenizer

/**
 * 재원이는 한 도시의 시장이 되었다.
 * 이 도시에는 도시를 동쪽과 서쪽으로 나누는 큰 일직선 모양의 강이 흐르고 있다.
 * 하지만 재원이는 다리가 없어서 시민들이 강을 건너는데 큰 불편을 겪고 있음을 알고 다리를 짓기로 결심하였다.
 * 강 주변에서 다리를 짓기에 적합한 곳을 사이트라고 한다.
 * 재원이는 강 주변을 면밀히 조사해 본 결과 강의 서쪽에는 N개의 사이트가 있고 동쪽에는 M개의 사이트가 있다는 것을 알았다. (N ≤ M)
 *
 * 재원이는 서쪽의 사이트와 동쪽의 사이트를 다리로 연결하려고 한다.
 * (이때 한 사이트에는 최대 한 개의 다리만 연결될 수 있다.)
 * 재원이는 다리를 최대한 많이 지으려고 하기 때문에 서쪽의 사이트 개수만큼 (N개) 다리를 지으려고 한다.
 * 다리끼리는 서로 겹쳐질 수 없다고 할 때 다리를 지을 수 있는 경우의 수를 구하는 프로그램을 작성하라.
 *
 *
 *
 * 입력
 * 입력의 첫 줄에는 테스트 케이스의 개수 T가 주어진다.
 * 그 다음 줄부터 각각의 테스트케이스에 대해 강의 서쪽과 동쪽에 있는 사이트의 개수 정수 N, M (0 < N ≤ M < 30)이 주어진다.
 *
 * 출력
 * 각 테스트 케이스에 대해 주어진 조건하에 다리를 지을 수 있는 경우의 수를 출력한다.
 *
 * 예제 입력 1
 * 3
 * 2 2
 * 1 5
 * 13 29
 * 예제 출력 1
 * 1
 * 5
 * 67863915
 * */

// 파스칼의 삼각형 공식을 사용. mCn = factorial(m) / (factorial(n) * factorial(m - n))
fun `1010-다리놓기`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val stringBuilder = StringBuilder()

    // 다리는 겹쳐질 수 없다.
    repeat(br.readLine().toInt()){
        val token = StringTokenizer(br.readLine())
        val n = token.nextToken().toInt() // 강의 서쪽
        val m = token.nextToken().toInt() // 강의 동쪽
        stringBuilder.appendLine(factorial(m) / (factorial(n) * factorial(m - n)))
    }

    bw.write(stringBuilder.toString())
    bw.flush()
    bw.close()
    br.close()
}

// 29! 가 Int, Long 타입을 넘기 때문에 Double 이나 BigInteger 로 선언
private tailrec fun factorial(n: Int, acc: BigInteger = BigInteger.ONE): BigInteger {
    return if (n == 0) acc
    else factorial(n - 1, acc * BigInteger.valueOf(n.toLong()))
}