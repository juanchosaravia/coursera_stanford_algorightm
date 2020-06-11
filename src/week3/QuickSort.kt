package week3

import kotlin.random.Random

class QuickSort(pivotType: PivotType = PivotType.FIRST) {

    private var pivotTypeSolver: (IntArray, Int, Int) -> Unit

    private val onLastPivot: (IntArray, Int, Int) -> Unit = { array, lIndex, rIndex ->
        val lastValue = array[rIndex - 1]
        array[rIndex - 1] = array[lIndex]
        array[lIndex] = lastValue
    }

    private val onMedianPivot: (IntArray, Int, Int) -> Unit = MedianPivotProcessor.create()

    private val onRandomPivot: (IntArray, Int, Int) -> Unit = { array, firstIndex, untilIndex ->
        val randomIndex = Random.nextInt(firstIndex, untilIndex)
        val firstValue = array[firstIndex]
        array[firstIndex] = array[randomIndex]
        array[randomIndex] = firstValue
    }

    init {
        pivotTypeSolver = when (pivotType) {
            PivotType.FIRST -> { _, _, _ -> }
            PivotType.LAST -> onLastPivot
            PivotType.MEDIAN -> onMedianPivot
            PivotType.RANDOM -> onRandomPivot
        }
    }

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
        FIRST, LAST, MEDIAN, RANDOM
    }
}