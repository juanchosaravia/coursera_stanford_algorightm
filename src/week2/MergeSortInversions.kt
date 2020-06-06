package week2

object MergeSortInversions {
    fun sort(list: List<Int>): Pair<List<Int>, Double> {
        if (list.size <= 1) return list to 0.0
        val halfIndex = list.size / 2
        return merge(
            sort(list.subList(0, halfIndex)),
            sort(list.subList(halfIndex, list.size))
        )
    }

    private fun merge(leftPair: Pair<List<Int>, Double>, rightPair: Pair<List<Int>, Double>): Pair<List<Int>, Double> {
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
                inversionsCounter += leftList.size.toDouble() - leftIndex
            }
        }

        while (leftIndex < leftList.size) {
            mergedList.add(leftList[leftIndex])
            leftIndex++
        }

        while (rightIndex < rightList.size) {
            mergedList.add(rightList[rightIndex])
            rightIndex++
        }

        return mergedList to inversionsCounter
    }
}