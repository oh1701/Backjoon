package personal_test_package

/**
 * 자료구조 힙(Heap) 의 특성을 활용해서 정렬하는 알고리즘.
 * 최대 힙 트리, 최소 힙 트리와 같이 부모 노드가 자식 노드보다 크거나 같음 혹은 작거나 같은 특징을 활용한다.
 *
 *
 * 2 / n - 1 이 마지막 부모 노드 인덱스인 것을 활용한다.
 * */
fun heapSort(){
    val arr = intArrayOf(4, 3, 2, 1, 5)
    val n = arr.size

    /*
    * 마지막 부모 노드부터 전체트리의 루트까지 진행하여 최대 힙 or 최소 힙으로 구성하기
    * 여기서는 최대힙
    * */
    for(i in n / 2 - 1 downTo 0){
        heapify(arr, n, i)
    }

    /*
    * 구성된 힙을 정렬하기
    * */
    for(i in n - 1 downTo 1){
        val temp = arr[i]
        // arr[0] 은 최대힙의 Root 이므로 최대 크기이다. 정렬을 위해 i로 옮겨준다.
        arr[i] = arr[0]
        arr[0] = temp

        // 순서가 바뀌었으므로, 힙 구성을 재진행한다.
        heapify(arr, i, 0)
    }

    println(arr.contentToString())
}

/**
* 주어진 배열(리스트)이나 이진트리를 힙 구조로 재배열하는 과정을 의미
 * @param i 서브트리의 루트 인덱스
* */
private fun heapify(arr: IntArray, n: Int, i: Int){
    // 서브트리의 루트 인덱스
    var largest = i
    // 서브트리의 왼쪽 자식 인덱스
    val leftIdx = 2 * i + 1
    // 서브트리의 오른쪽 자식 인덱스
    val rightIdx = 2 * i + 2

    // 왼쪽 자식 인덱스가 Array 의 사이즈보다 크지 않고 서브트리의 루트. 즉, 부모보다 큰 경우
    if(leftIdx < n && arr[leftIdx] > arr[largest]){
        largest = leftIdx
    }

    // 오른쪽 자식 인덱스가 Array 의 사이즈보다 크지 않고 서브트리의 루트. 즉, 부모보다 큰 경우
    if(rightIdx < n && arr[rightIdx] > arr[largest]){
        largest = rightIdx
    }

    if(largest != i){
        val temp = arr[i]
        arr[i] = arr[largest]
        arr[largest] = temp

        heapify(arr, n, largest)
    }
}