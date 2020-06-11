package week3

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.system.measureNanoTime
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
        val result = QuickSort().sort(array)
        assertEquals(162085, result.second)
    }

    @Test
    fun problem3PivotLastItem() {
        val array = getProblem3Array()
        val result = QuickSort(QuickSort.PivotType.LAST).sort(array)
        assertEquals(164123, result.second)
    }

    @Test
    fun problem3PivotMedianItem() {
        val array = getProblem3Array()
        val time = measureNanoTime {
            val result = QuickSort(QuickSort.PivotType.MEDIAN).sort(array)
            assertEquals(138382, result.second)
        }

        println("Median: $time") // 9384766
    }

    @Test
    fun problem3PivotRandomItem() {
        val array = getProblem3Array()
        val time = measureNanoTime {
            val result = QuickSort(QuickSort.PivotType.RANDOM).sort(array)
            println(result.second)
        }

        println("Random: $time") // 21033333
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