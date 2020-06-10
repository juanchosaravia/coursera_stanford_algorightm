package week3

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class QuickSortTest {

    @Test
    fun fourItemsToSort() {
        val result = QuickSort().sort(intArrayOf(4, 3, 2, 1))
        assertTrue(intArrayOf(1, 2, 3, 4) contentEquals result.first)
        assertEquals(6, result.second)
    }

    @Test
    fun fiveItemsToSort() {
        val result = QuickSort().sort(intArrayOf(5, 1, 2, 3, 4))
        assertTrue(intArrayOf(1, 2, 3, 4, 5) contentEquals result.first)
        assertEquals(10, result.second)
    }

    @Test
    fun multipleItemsToSort() {
        val result = QuickSort().sort(intArrayOf(90, 22, 4, 3, 2, 1, 5, 8, 7, 6, 10, 23))
        assertTrue(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 10, 22, 23, 90) contentEquals result.first)
    }
}