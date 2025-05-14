package divide_and_conquer_algorithm

fun binary_search_practice(){
    val arr = intArrayOf(1, 2, 4, 10, 15, 18, 21, 29)
    val target = 18

    println("타겟 인덱스 = ${binarySearch(arr, target)}")
}

fun binarySearch(arr: IntArray, target: Int): Int {
    var start = 0
    var end = arr.lastIndex

    while (start <= end) {
        val mid = (start + end) / 2
        when {
            arr[mid] == target -> return mid
            target < arr[mid] -> end = mid - 1
            else -> start = mid + 1
        }
    }
    return -1
}

