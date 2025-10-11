package personal_test_package

/**
 * 모든 요소를 앞에서부터 이미 차례대로 정렬된 배열과 비교하여 자신의 위치를 찾아 삽입하는 알고리즘.
 * 정렬 배열을 순회하면서 현재 원소보다 크거나 작은지 확인하고, 순회를 벗어나면 현재 원소를 자리에 넣어준다.
 * */
fun insertionSort(){
    val arr = intArrayOf(4, 5, 2, 1, 3)

    for(i in 1 until arr.size){
        val key = arr[i]
        var sortedLastIndex = i - 1

        /*
        정렬된 배열의 인덱스가 key 보다 크거나, j 가 0 이상인지 확인하고 반복
        조건에 해당되면 arr[j] 를 한 칸 밀어주고 j 를 줄여준다.
        * */
        while(sortedLastIndex >= 0 && arr[sortedLastIndex] > key){
            arr[sortedLastIndex + 1] = arr[sortedLastIndex]
            sortedLastIndex--
        }

        // while 에서 벗어나면 sortedLastIndex + 1 이 key 의 자리가 된다.
        arr[sortedLastIndex + 1] = key
    }

    println(arr.contentToString())
}