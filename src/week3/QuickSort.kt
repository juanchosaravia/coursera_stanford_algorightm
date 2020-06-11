package week3

class QuickSort(pivotType: PivotType = PivotType.FIRST) {

    private var pivotTypeSolver: (IntArray, Int, Int) -> Unit

    private val onLastPivot: (IntArray, Int, Int) -> Unit = { array, lIndex, rIndex ->
        val lastValue = array[rIndex - 1]
        array[rIndex - 1] = array[lIndex]
        array[lIndex] = lastValue
    }

    /**
     * public method just to add unit tests.
     */
    val onMedianPivot: (IntArray, Int, Int) -> Unit = { array, lIndex, rIndex ->
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

    init {
        pivotTypeSolver = when (pivotType) {
            PivotType.FIRST -> { _, _, _ -> }
            PivotType.LAST -> onLastPivot
            PivotType.MEDIAN -> onMedianPivot
        }
    }

    /**
     * Sorts using as pivot the first item of the range.
     */
    fun sort(array: IntArray): Pair<IntArray, Int> {
        if (array.size <= 1) return array to 0
        return quickSort(array, 0, array.size)
    }

    /**
     * lIndex included
     * rIndex excluded
     */
    private fun quickSort(array: IntArray, lIndex: Int, rIndex: Int): Pair<IntArray, Int> {
        val subArraySize = rIndex - lIndex
        if (subArraySize <= 1) return array to 0
        var comparisonsCounter = subArraySize - 1

        pivotTypeSolver(array, lIndex, rIndex)
        val pivot = array[lIndex]
        var i = lIndex + 1

        for (j in (lIndex + 1) until rIndex) {
            if (array[j] < pivot) {
                // swap
                val tempI = array[i]
                array[i] = array[j]
                array[j] = tempI
                i++
            }
        }

        val pivotFinalIndex = i - 1
        // swap pivot
        array[lIndex] = array[pivotFinalIndex]
        array[pivotFinalIndex] = pivot

        /**
        if (rIndex - lIndex < 4) {
        // we are done
        return array
        }*/

        if (pivotFinalIndex == lIndex) {
            // sort only right side
            val (_, comparisons) = quickSort(array, i, rIndex)
            comparisonsCounter += comparisons
        } else if (i == rIndex) {
            // sort only left side
            val (_, comparisons) = quickSort(array, lIndex, pivotFinalIndex)
            comparisonsCounter += comparisons
        } else {
            val (_, comparisonsLeft) = quickSort(array, lIndex, pivotFinalIndex)
            val (_, comparisonsRight) = quickSort(array, pivotFinalIndex + 1, rIndex)
            comparisonsCounter += comparisonsLeft + comparisonsRight
        }

        return array to comparisonsCounter
    }

    enum class PivotType {
        FIRST, LAST, MEDIAN
    }
}