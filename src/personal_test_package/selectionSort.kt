package personal_test_package

/**
* 아직 정렬되지 않은 배열에서 가장 작거나 큰 것을 선택해서,
* 정렬되지 않은 부분의 가장 왼쪽이나 오른쪽에 배치시킨다.
* */
fun selectionSort(){
    val arr = intArrayOf(4, 5, 2, 1, 3)
    val n = arr.size

    for(i in 0 until n - 1){
        var min = arr[i]
        var switchingIdx = i

        for(j in i + 1 until n){
            val next = arr[j]

            if(min > next){
                min = next
                switchingIdx = j
            }
        }

        val temp = arr[i]
        arr[i] = min
        arr[switchingIdx] = temp
    }

    println(arr.contentToString())
}