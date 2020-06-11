package week3

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class MedianPivotTest {

    private val quickSort = QuickSort()

    @Test
    fun onMedianPivotOddTest() {
        val array = intArrayOf(1, 2, 3)
        quickSort.onMedianPivot(array, 0, array.size)
        assertTrue(intArrayOf(2, 1, 3) contentEquals array)
    }

    @Test
    fun onMedianPivotLongerOddTest() {
        val array = intArrayOf(1, 2, 3, 4, 5)
        quickSort.onMedianPivot(array, 0, array.size)
        assertTrue(intArrayOf(3, 2, 1, 4, 5) contentEquals array)
    }

    @Test
    fun onMedianPivotTwoElements() {
        val array = intArrayOf(1, 2)
        quickSort.onMedianPivot(array, 0, array.size)
        assertTrue(intArrayOf(1, 2) contentEquals array)
    }

    @Test
    fun onMedianPivotTwoElementsDecrement() {
        val array = intArrayOf(2, 1)
        quickSort.onMedianPivot(array, 0, array.size)
        assertTrue(intArrayOf(2, 1) contentEquals array)
    }

    @Test
    fun onMedianPivotEvenTest() {
        val array = intArrayOf(1, 2, 3, 4)
        quickSort.onMedianPivot(array, 0, array.size)
        assertTrue(intArrayOf(2, 1, 3, 4) contentEquals array)
    }

    @Test
    fun onMedianPivotLongerEvenTest() {
        val array = intArrayOf(1, 2, 3, 4, 5, 6)
        quickSort.onMedianPivot(array, 0, array.size)
        assertTrue(intArrayOf(3, 2, 1, 4, 5, 6) contentEquals array)
    }

    @Test
    fun onMedianPivotUnsortedEvenTest() {
        val array = intArrayOf(6, 1, 2, 3, 4, 5)
        quickSort.onMedianPivot(array, 0, array.size)
        assertTrue(intArrayOf(5, 1, 2, 3, 4, 6) contentEquals array)
    }

    @Test
    fun onMedianPivotSubArrayEvenTest() {
        val array = intArrayOf(1, 2, 3, 4, 5, 6)
        quickSort.onMedianPivot(array, 3, array.size)
        assertTrue(intArrayOf(1, 2, 3, 5, 4, 6) contentEquals array)
    }

    @Test
    fun onMedianPivotSubArrayOddTest() {
        val array = intArrayOf(1, 2, 3, 4, 5)
        quickSort.onMedianPivot(array, 2, array.size)
        assertTrue(intArrayOf(1, 2, 4, 3, 5) contentEquals array)
    }

    @Test
    fun onMedianPivotSubArrayTwoElements() {
        val array = intArrayOf(1, 2, 3, 4, 5)
        quickSort.onMedianPivot(array, 3, array.size)
        assertTrue(intArrayOf(1, 2, 3, 4, 5) contentEquals array)
    }

    @Test
    fun onMedianPivotSubArrayTwoElementsMiddle() {
        val array = intArrayOf(1, 2, 4, 3, 5)
        quickSort.onMedianPivot(array, 2, 4)
        assertTrue(intArrayOf(1, 2, 4, 3, 5) contentEquals array)
    }

    @Test
    fun onMedianPivotSubArrayEvenFirst() {
        val array = intArrayOf(10, 2, 20, 15)
        quickSort.onMedianPivot(array, 0, 3)
        assertTrue(intArrayOf(10, 2, 20, 15) contentEquals array)
    }
}