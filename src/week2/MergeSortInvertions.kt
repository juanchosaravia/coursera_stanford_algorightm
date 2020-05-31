package week2

object MergeSortInvertions {
    fun sort(list: List<Int>): Pair<List<Int>, Int> {
        if (list.size <= 1) return list to 0
        val halfIndex = list.size / 2
        return merge(
            sort(list.subList(0, halfIndex)),
            sort(list.subList(halfIndex, list.size))
        )
    }

    fun merge(leftPair: Pair<List<Int>, Int>, rightPair: Pair<List<Int>, Int>): Pair<List<Int>, Int> {
        var inversionsCounter = leftPair.second + rightPair.second
        var leftIndex = 0
        var rightIndex = 0
        val mergedList = mutableListOf<Int>()

        val leftList = leftPair.first
        val rightList = rightPair.first

        while (leftIndex < leftList.size && rightIndex < rightList.size) {
            if (leftList[leftIndex] <= rightList[rightIndex]) {
                mergedList.add(leftList[leftIndex])
                leftIndex++
            } else {
                mergedList.add(rightList[rightIndex])
                rightIndex++
                inversionsCounter++
            }
        }

        val nextLeftIndex = leftIndex + 1
        if (nextLeftIndex < leftList.size) {
            inversionsCounter += (leftList.size - nextLeftIndex) * rightList.size
        }
        println(inversionsCounter)

        while (leftIndex < leftList.size) {
            // TODO improvement: we can add the entire remaining leftList items into the mergedList
            mergedList.add(leftList[leftIndex])
            leftIndex++
        }

        while (rightIndex < rightList.size) {
            // TODO improvement: we can add the entire remaining rightList items into the mergedList
            mergedList.add(rightList[rightIndex])
            rightIndex++
        }

        val result = mergedList to inversionsCounter
        println(result)
        return result
    }
}