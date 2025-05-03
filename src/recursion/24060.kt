package recursion

import java.util.StringTokenizer

private var count = 0
private var result = -1
lateinit var tmp: IntArray

/**
 * 재귀함수와 분할정복을 사용하여 구현
 * 각 분할 단계 별 시간 복잡도는 O(n)을 가짐.
 * 단계는 항상 log n만큼 만들어지므로 시간 복잡도는 O(n log n)
 *
 * 가장 작은 묶음 단위 (2개) 부터 분할 후 정렬 -> 이후 2개씩 묶어 4개로 병합 후 정렬, 4개씩 묶어 병합 후 정렬 반복.
 * */

// k번째에 저장되는 수를 구해야함. 만약 저장 횟수가 k 보다 작으면  -1
fun `24060-알고리즘 수업 - 병합 정렬1`(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val (n, k) = br.readLine().split(" ").map { it.toInt() }
    val elementToken = StringTokenizer(br.readLine())
    val arr = IntArray(n){ elementToken.nextToken().toInt() }
    tmp = IntArray(n)

    mergeSort(arr, 0, arr.size - 1, k)
    bw.write(result.toString())
    bw.flush()
    bw.close()
    br.close()
}

private fun mergeSort(arr: IntArray, start: Int, end: Int, k: Int){
    if(start < end){
        val middle = (start + end) / 2
        mergeSort(arr, start, middle, k)
        mergeSort(arr, middle + 1, end, k)
        merge(arr, start, middle, end, k)
    }
}

private fun merge(arr: IntArray, start: Int, middle: Int, end: Int, k: Int){
    var left = start
    var right = middle + 1
    var t = 0

    /*
    * 왼쪽 배열과 오른쪽 배열 모두 존재하는 경우 실행.
    * 왼쪽 배열의 left 번째 요소가 오른쪽 배열의 right 번째 요소보다 작으면 tmp[t] 에 left 를 넣고 t 와 left 를 증가.
    * */
    while (left <= middle && right <= end){ tmp[t++] = if(arr[left] <= arr[right]) arr[left++] else arr[right++] }

    /*
    * 왼쪽 배열만 존재하는 경우 실행.
    * tmp[t] 에 left 를 넣고 t 와 left 를 증가.
    * */
    while (left <= middle){ tmp[t++] = arr[left++]}

    /*
    * 오른쪽 배열만 존재하는 경우 실행.
    * tmp[t] 에 right 를 넣고 t 와 right 를 증가.
    * */
    while (right <= end){ tmp[t++] = arr[right++] }

    left = start
    t = 0

    while (left <= end){
        arr[left++] = tmp[t++]
        count++

        if(count == k){
            result = tmp[t - 1]
            break
        }
    }
}