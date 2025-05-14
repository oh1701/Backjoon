package divide_and_conquer_algorithm

/*
* left[i] 가 right[j] 보다 작은지 판단하며 쪼개진 부분부터 재귀적으로 재정렬
* left 크기를 우선적으로 배열 / 2 해주고, 남은 크기를 right i 의 최종값 지정
*
* 왼쪽 [2][3] -> [2,3] -> [2,3,4]
* 오른쪽 [5][6] -> [5,6] -> [1,5,6]
*
* i = 0, j = 0
* 2는 1보다 크니 1 우선 배치 [1]
* i = 0, j = 1
* 2는 5보다 작으니 2 배치 [1,2]
* i = 1, j = 1
* 3은 5보다 작으니 3 배치 [1,2,3]
* i = 2, j = 1
* 4는 5보다 작으니 4 배치 [1,2,3,4]
* i 가 배열 / 2 에 도달하였으니 더 비교하지 않고 나머지 배치 [1,2,3,4,5,6]
* */
lateinit var temp: IntArray

fun mergeSortPractice() {
    val arr = intArrayOf(4, 2, 3, 1, 5, 6)
    temp = IntArray(arr.size)
    mergeSort(arr, 0, arr.lastIndex)
}

private fun mergeSort(arr: IntArray, start: Int, end: Int){
    if(start >= end) return

    val mid = (start + end) / 2
    mergeSort(arr, start, mid)
    mergeSort(arr, mid + 1, end)
    merge(arr, start, mid, end)
}

private fun merge(array: IntArray, start: Int, mid:Int, end: Int){
    var left = start
    var right = mid + 1
    var t = start

    while (left <= mid && right <= end){
        temp[t++] = if(array[left] <= array[right]) array[left++] else array[right++]
    }

    while(left <= mid){ temp[t++] = array[left++] }
    while(right <= end){ temp[t++] = array[right++] }

    // 이것을 진행해줘야 재귀할때 array 에 값이 정상 반영된다.
    for (i in start until right) {
        array[i] = temp[i]
    }

    println("확인 : arr = ${array.joinToString()}, start = $start, mid = $mid, end = $end, left = $left, right = $right")
}