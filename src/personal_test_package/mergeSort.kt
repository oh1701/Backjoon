package personal_test_package

/**
 * 가장 작은 단위까지 분해하고 다시 병합하여 정렬을 진행하는 알고리즘
 * */

fun mergeSort(){
    val arr = intArrayOf(8, 7, 6, 5, 4, 3, 2, 1)
    val sortedArr = IntArray(arr.size)
    doMergeSort(arr, sortedArr, 0, arr.size - 1)
    println(arr.contentToString())
}

/*
* 재귀를 통해 최소한의 사이즈까지 쪼개고 다시 병합해야한다
* 단, 배열을 직접 쪼개는 방식이 아닌 시작점과 끝점을 지정해주어 제한을 주는 방식
* */
private fun doMergeSort(arr: IntArray, sortedArr: IntArray, left: Int, right: Int){
    if(left < right){
        val mid = (left + right) / 2
        doMergeSort(arr, sortedArr, left, mid)
        doMergeSort(arr, sortedArr, mid + 1, right)
        merge(arr, sortedArr, left, right, mid)
    }
}

private fun merge(arr: IntArray, sortedArr: IntArray, left: Int, right: Int, mid: Int){
    var leftIdx = left
    var rightIdx = mid + 1
    var sortedIdx = left

    // 두 부분 배열 중 하나가 끝날 때까지, 작은 값을 임시 배열에 복사
    while (leftIdx <= mid && rightIdx <= right) {
        if (arr[leftIdx] <= arr[rightIdx]) {
            sortedArr[sortedIdx++] = arr[leftIdx++]
        } else {
            sortedArr[sortedIdx++] = arr[rightIdx++]
        }
    }

    // 왼쪽 부분 배열에 원소가 남아있다면, 모두 복사
    while (leftIdx <= mid) {
        sortedArr[sortedIdx++] = arr[leftIdx++]
    }

    // 오른쪽 부분 배열에 원소가 남아있다면, 모두 복사
    while (rightIdx <= right) {
        sortedArr[sortedIdx++] = arr[rightIdx++]
    }

    for(i in left .. right){
        arr[i] = sortedArr[i]
    }
}