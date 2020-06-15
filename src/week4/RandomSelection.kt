package week4

import kotlin.random.Random

object RandomSelection {

    /**
     * Finds the median value for the given array.
     */
    fun median(array: IntArray): Int {
        val size = array.size
        val medianIndex = if (size % 2 == 0) {
            (size / 2) - 1
        } else {
            size / 2
        }
        return find(array, medianIndex)
    }

    /**
     * Finds the value of given index in this array.
     */
    fun find(array: IntArray, index: Int): Int {
        println("Index to find: $index")
        return findInSubArray(
            array = array,
            lIndex = 0,
            rIndex = array.size,
            index = index
        )
    }

    /**
     * lIndex included
     * rIndex excluded
     */
    private fun findInSubArray(array: IntArray, lIndex: Int, rIndex: Int, index: Int): Int {
        val size = rIndex - lIndex
        if (size == 1) {
            println("last index available: $lIndex")
            return array[lIndex]
        }

        val pivotIndex = Random.nextInt(lIndex, rIndex)
        val pivot = array[pivotIndex]

        // swap pivot with first item
        if (pivotIndex != lIndex) {
            array[pivotIndex] = array[lIndex]
            array[lIndex] = pivot
        }

        var i = lIndex + 1
        for (j in (lIndex + 1) until rIndex) {
            if (array[j] < pivot) {
                array[i] = array[j].also { array[j] = array[i] }
                i++
            }
        }

        // swap pivot to the right position
        val newPivotIndex = i - 1
        println(newPivotIndex)
        array[lIndex] = array[newPivotIndex].also { array[newPivotIndex] = array[lIndex] }

        // divide
        return when {
            index == newPivotIndex -> {
                println("found index: $index")
                pivot
            }
            index < newPivotIndex -> {
                findInSubArray(array, lIndex, newPivotIndex, index)
            }
            else -> {
                findInSubArray(array, i, rIndex, index)
            }
        }
    }
}