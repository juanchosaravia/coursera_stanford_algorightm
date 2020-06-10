package week3

class QuickSort(private val pivotType: PivotType = PivotType.FIRST) {

    enum class PivotType {
        FIRST, LAST, MEDIAN
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

        //val pivot = getPivotByType(array, lIndex, rIndex)
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

    private fun getPivotByType(array: IntArray, lIndex: Int, rIndex: Int): Int {
        return when (pivotType) {
            PivotType.FIRST -> array[lIndex]
            PivotType.LAST -> getLastPivotAndSwap(array, lIndex, rIndex)
            PivotType.MEDIAN -> getMedianPivotAndSwap(array, lIndex, rIndex)
        }
    }

    private fun getLastPivotAndSwap(array: IntArray, lIndex: Int, rIndex: Int): Int {
        val lastValue = array[rIndex - 1]
        array[rIndex - 1] = array[lIndex]
        array[lIndex] = lastValue
        return lastValue
    }

    private fun getMedianPivotAndSwap(array: IntArray, lIndex: Int, rIndex: Int): Int {
        //array[lIndex]
        return 0 // TODO
    }
}