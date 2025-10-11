package personal_test_package

/**
* 인접한 두 원소 (왼쪽, 오른쪽) 을 비교해서 큰 값을 오른쪽으로 위치하게하는 정렬법.
* 2중 for 문을 돌려야해서 O(n^2) 의 복잡도를 가진다.
* */
fun bubbleSort(){
    val arr = intArrayOf(4, 5, 2, 1, 3)
    val n = arr.size

    for(i in 0 until n - 1){
        for(j in 0 until  n - 1 - i){
            val left = arr[j]
            val right = arr[j + 1]

            if(left > right){
                arr[j + 1] = left
                arr[j] = right
            }
            println("${arr.contentToString()}")
        }
    }

    println(arr.contentToString())
}