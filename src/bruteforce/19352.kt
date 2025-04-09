package bruteforce

/**
 * 문제의 답인
 * $x$와
 * $y$를 공백으로 구분해 출력한다.
 *
 * 예제 입력 1
 * 1 3 -1 4 1 7
 * 예제 출력 1
 * 2 -1
 * 예제 입력 2
 * 2 5 8 3 -4 -11
 * 예제 출력 2
 * -1 2
* */
// 연립방정식이므로 크래머의 공식을 활용하여 분모, 분자를 구하고 x, y 값을 추출
fun `19352-수학은 비대면강의입니다`(){
    val list = readln().split(" ").map { it.toInt() }
    val a = list[0]
    val b = list[1]
    val c = list[2]
    val d = list[3]
    val e = list[4]
    val f = list[5]

    /*
    * ax + by = c
    * dx + ey = f
    *
    * ->
    *
    * 분모 = a * e - b * d
    * x 분자 = c * e - b * f // x 항 위치에 상수항 옮김
    * y 분자 = a * f - c * d // y 항 위치에 상수항 옮김
    * x = x분자 / 분모
    * y = y분자 / 분모
    * */
    val denominator = a * e - b * d
    val xNumerator = c * e - b * f
    val yNumerator = a * f - c * d
    val x = xNumerator / denominator
    val y = yNumerator / denominator

    println("$x $y")
}