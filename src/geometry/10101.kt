package geometry
/**
 * 창영이는 삼각형의 종류를 잘 구분하지 못한다. 따라서 프로그램을 이용해 이를 외우려고 한다.
 *
 * 삼각형의 세 각을 입력받은 다음,
 *
 * 세 각의 크기가 모두 60이면, Equilateral
 * 세 각의 합이 180이고, 두 각이 같은 경우에는 Isosceles
 * 세 각의 합이 180이고, 같은 각이 없는 경우에는 Scalene
 * 세 각의 합이 180이 아닌 경우에는 Error
 * 를 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 총 3개의 줄에 걸쳐 삼각형의 각의 크기가 주어진다. 모든 정수는 0보다 크고, 180보다 작다.
 *
 * 출력
 * 문제의 설명에 따라 Equilateral, Isosceles, Scalene, Error 중 하나를 출력한다.
 *
 * 예제 입력 1
 * 60
 * 70
 * 50
 * 예제 출력 1
 * Scalene
 *
 * */
fun `10101-삼각형 외우기`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val degreeList = mutableListOf<Int>()

    repeat(3){ i ->
        degreeList.add(br.readLine().toInt())
    }

    val msg: String = when {
        degreeList.all { it == 60 } -> "Equilateral"
        degreeList.sum() != 180 -> "Error"
        degreeList.distinct().size == 2 -> "Isosceles"
        else -> "Scalene"
    }

    bw.write(msg)
    bw.close()
    br.close()
}