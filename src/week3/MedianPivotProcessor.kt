package week3

object MedianPivotProcessor {

    fun create(): (IntArray, Int, Int) -> Unit = { array, lIndex, rIndex ->
        val size = rIndex - lIndex
        val midIndex = if (size % 2 == 0) {
            (size / 2) - 1
        } else {
            size / 2
        } + lIndex

        val leftValue = array[lIndex]
        val midValue = array[midIndex]
        val rightValue = array[rIndex - 1]

        if (isMedian(midValue, leftValue, rightValue)) {
            swapItemsInArray(array, lIndex, midIndex + 1)
        } else if (isMedian(rightValue, leftValue, midValue)) {
            swapItemsInArray(array, lIndex, rIndex)
        }
    }

    private fun swapItemsInArray(array: IntArray, lIndex: Int, rIndex: Int) {
        val lastValue = array[rIndex - 1]
        array[rIndex - 1] = array[lIndex]
        array[lIndex] = lastValue
    }

    private fun isMedian(item: Int, first: Int, second: Int): Boolean {
        return (item <= first && item >= second) ||
                (item >= first && item <= second)
    }
}