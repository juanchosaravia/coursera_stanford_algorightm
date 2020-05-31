package week1

object MergeSort {
    fun sort(list: List<Int>): List<Int> {
        if (list.size <= 1) return list

        val midPosition = list.size / 2
        return merge(
            sort(list.subList(0, midPosition)),
            sort(list.subList(midPosition, list.size))
        )
    }

    fun merge(leftList: List<Int>, rightList: List<Int>): List<Int> {
        var leftListIndex = 0
        var rightListIndex = 0
        val mergedList = mutableListOf<Int>()

        while (leftListIndex < leftList.size && rightListIndex < rightList.size) {
            if (leftList[leftListIndex] <= rightList[rightListIndex]) {
                mergedList.add(leftList[leftListIndex])
                leftListIndex++
            } else {
                mergedList.add(rightList[rightListIndex])
                rightListIndex++
            }
        }

        while (leftListIndex < leftList.size) {
            mergedList.add(leftList[leftListIndex])
            leftListIndex++
        }

        while (rightListIndex < rightList.size) {
            mergedList.add(rightList[rightListIndex])
            rightListIndex++
        }

        return mergedList
    }
}