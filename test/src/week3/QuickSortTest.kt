package week3

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class QuickSortTest {

    private val quickSortFirst = QuickSort(QuickSort.PivotType.FIRST)
    private val quickSortLast = QuickSort(QuickSort.PivotType.LAST)
    private val quickSortMedian = QuickSort(QuickSort.PivotType.MEDIAN)

    @Test
    fun fourItemsToSort() {
        val expectedArray = intArrayOf(1, 2, 3, 4)
        val array = intArrayOf(4, 3, 2, 1)

        assertAllAlgorithms(array, expectedArray)

        val result = quickSortFirst.sort(array)
        assertEquals(6, result.second)
    }

    @Test
    fun fiveItemsToSort() {
        val expectedArray = intArrayOf(1, 2, 3, 4, 5)
        val array = intArrayOf(5, 1, 2, 3, 4)

        assertAllAlgorithms(array, expectedArray)

        val result = quickSortFirst.sort(array)
        assertEquals(10, result.second)
    }

    @Test
    fun multipleItemsToSort() {
        val expectedArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 10, 22, 23, 90)
        val array = intArrayOf(90, 22, 4, 3, 2, 1, 5, 8, 7, 6, 10, 23)
        assertAllAlgorithms(array, expectedArray)
    }

    @Test
    fun compareAllAlgorithms() {
        val expectedArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 10, 22, 23, 90)
        val array = intArrayOf(90, 22, 4, 3, 2, 1, 5, 8, 7, 6, 10, 23)
        assertAllAlgorithms(array, expectedArray)
    }

    private fun assertAllAlgorithms(array: IntArray, expectedArray: IntArray) {
        val resultFirst = quickSortFirst.sort(array)
        val resultLast = quickSortLast.sort(array)
        val resultMedian = quickSortMedian.sort(array)

        assertTrue(expectedArray contentEquals resultFirst.first)
        assertTrue(expectedArray contentEquals resultLast.first)
        assertTrue(expectedArray contentEquals resultMedian.first)
        assertTrue(resultLast.first contentEquals resultMedian.first)
    }

    @Test
    fun problem3PivotFirstItem() {
        val array = getProblem3Array()
        val resultFirst = QuickSort().sort(array)
        println(resultFirst.second) // 162085
    }

    @Test
    fun problem3PivotLastItem() {
        val array = getProblem3Array()
        val result = QuickSort(QuickSort.PivotType.LAST).sort(array)
        println(result.second) // 164123
    }

    @Test
    fun problem3PivotMedianItem() {
        val array = getProblem3Array()
        val result = QuickSort(QuickSort.PivotType.MEDIAN).sort(array)
        println(result.second) // 151001 / 138382
    }

    @Test
    fun onMedianPivotOddTest() {
        val array = intArrayOf(1, 2, 3)
        QuickSort().onMedianPivot(array, 0, array.size)
        assertTrue(intArrayOf(2, 1, 3) contentEquals array)
    }

    @Test
    fun onMedianPivotLongerOddTest() {
        val array = intArrayOf(1, 2, 3, 4, 5)
        QuickSort().onMedianPivot(array, 0, array.size)
        assertTrue(intArrayOf(3, 2, 1, 4, 5) contentEquals array)
    }

    @Test
    fun onMedianPivotTwoElements() {
        val array = intArrayOf(1, 2)
        QuickSort().onMedianPivot(array, 0, array.size)
        assertTrue(intArrayOf(1, 2) contentEquals array)
    }

    @Test
    fun onMedianPivotTwoElementsDecrement() {
        val array = intArrayOf(2, 1)
        QuickSort().onMedianPivot(array, 0, array.size)
        assertTrue(intArrayOf(2, 1) contentEquals array)
    }

    @Test
    fun onMedianPivotEvenTest() {
        val array = intArrayOf(1, 2, 3, 4)
        QuickSort().onMedianPivot(array, 0, array.size)
        assertTrue(intArrayOf(2, 1, 3, 4) contentEquals array)
    }

    @Test
    fun onMedianPivotLongerEvenTest() {
        val array = intArrayOf(1, 2, 3, 4, 5, 6)
        QuickSort().onMedianPivot(array, 0, array.size)
        assertTrue(intArrayOf(3, 2, 1, 4, 5, 6) contentEquals array)
    }

    @Test
    fun onMedianPivotUnsortedEvenTest() {
        val array = intArrayOf(6, 1, 2, 3, 4, 5)
        QuickSort().onMedianPivot(array, 0, array.size)
        assertTrue(intArrayOf(5, 1, 2, 3, 4, 6) contentEquals array)
    }

    @Test
    fun onMedianPivotSubArrayEvenTest() {
        val array = intArrayOf(1, 2, 3, 4, 5, 6)
        QuickSort().onMedianPivot(array, 3, array.size)
        assertTrue(intArrayOf(1, 2, 3, 5, 4, 6) contentEquals array)
    }

    @Test
    fun onMedianPivotSubArrayOddTest() {
        val array = intArrayOf(1, 2, 3, 4, 5)
        QuickSort().onMedianPivot(array, 2, array.size)
        assertTrue(intArrayOf(1, 2, 4, 3, 5) contentEquals array)
    }

    @Test
    fun onMedianPivotSubArrayTwoElements() {
        val array = intArrayOf(1, 2, 3, 4, 5)
        QuickSort().onMedianPivot(array, 3, array.size)
        assertTrue(intArrayOf(1, 2, 3, 4, 5) contentEquals array)
    }

    @Test
    fun onMedianPivotSubArrayTwoElementsMiddle() {
        val array = intArrayOf(1, 2, 4, 3, 5)
        QuickSort().onMedianPivot(array, 2, 4)
        assertTrue(intArrayOf(1, 2, 4, 3, 5) contentEquals array)
    }

    @Test
    fun onMedianPivotSubArrayEvenFirst() {
        val array = intArrayOf(10, 2, 20, 15)
        QuickSort().onMedianPivot(array, 0, 3)
        assertTrue(intArrayOf(10, 2, 20, 15) contentEquals array)
    }

    private fun getProblem3Array(): IntArray {
        val input = mutableListOf<Int>()
        val path = this::class.java.classLoader.getResource("week3/problem3.txt")
        File(path.toURI()).forEachLine {
            input.add(it.toInt())
        }
        return input.toIntArray()
    }
}