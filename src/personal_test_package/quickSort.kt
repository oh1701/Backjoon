package personal_test_package

/**
* 기준점 (Pivot) 을 정해서 해당 Pivot 을 기준으로 작은 값은 왼쪽, 큰 값은 오른쪽으로 두는 등의 방식으로 정렬하는 알고리즘
* */
fun quickSort(){
    val arr = intArrayOf(4, 3, 2, 1, 5)
    doQuickSort(arr, 0, arr.size - 1)
    println(arr.contentToString())
}

private fun doQuickSort(arr: IntArray, low: Int, high: Int){
    if(low < high){
        val pivotIndex = partition(arr, low, high)
        // partition 을 통해 pivot 을 정하고 정렬을 한 번 진행했으므로 pivotIndex - 1 을 high 로 지정
        doQuickSort(arr, low, pivotIndex - 1)
        // partition 을 통해 pivot 을 정하고 정렬을 한 번 진행했으므로 pivotIndex + 1 을 low 로 지정
        doQuickSort(arr, pivotIndex + 1, high)
    }
}

private fun partition(arr: IntArray, low: Int, high: Int): Int {
    // high Index 를 통해 pivot 가져오기
    val pivot = arr[high]
    var i = low - 1

    for(j in low until high){
        // arr[j] 가 pivot 보다 작은 경우 i 를 증가시키고 arr[i] 와 arr[j] 를 교환
        if(arr[j] < pivot){
            i++
            val temp = arr[i]
            arr[i] = arr[j]
            arr[j] = temp
        }
    }

    // 왼쪽, 오른쪽 정렬을 마쳤으므로 pivot 을 최종 위치에 기록
    val temp = arr[i + 1]
    arr[i + 1] = pivot
    arr[high] = temp

    return i + 1
}