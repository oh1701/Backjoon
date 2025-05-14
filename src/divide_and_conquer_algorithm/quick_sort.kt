package divide_and_conquer_algorithm

fun quickSortPractice(){
    val arr = intArrayOf(4,1,2,3,6,5)
    quickSort(arr, start = 0, end = arr.lastIndex)
    println("확인 : arr = ${arr.joinToString()}")
}

private fun quickSort(arr: IntArray, start: Int, end: Int){
    if(start >= end) return

    /**
    * partition for문 0,1,2,3,4 진행 -> 412365
    * i 는 6을 제외한 4번 증가하였으니 3
    * arr[i(3) + 1] 와 arr[end] 는 서로 바뀌어야하므로 4,1,2,3,5,6 으로 변경
    * 이후 pivot 4로 quickSort 재귀
     * */
    val pivot = partition(arr, start, end)
    quickSort(arr, start, pivot - 1)
    quickSort(arr, pivot + 1, end)
}

private fun partition(arr: IntArray, start: Int, end: Int): Int {
    // pivot 을 마지막 요소로 설정
    val pivot = arr[end]
    var i = start - 1

    for (j in start until end) {
        if (arr[j] <= pivot) {
            i++
            // arr[i] 에 arr[j] 를 대입하면서 arr[j] 를 이전 arr[i] 값으로 변환
            arr[i] = arr[j].also { arr[j] = arr[i] }
        }
    }
    // i 는 pivot 보다 작은 값들이 있을때만 증가하므로 최종적으로 i + 1 은 pivot 의 index 가 된다.
    arr[i + 1] = arr[end].also { arr[end] = arr[i + 1] }
    return i + 1
}