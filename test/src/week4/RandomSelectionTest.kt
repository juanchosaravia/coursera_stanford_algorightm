package week4

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class RandomSelectionTest {

    @Test
    fun findIndexInSmallArray() {
        val result = RandomSelection.find(intArrayOf(4, 2, 3, 1, 5), 1)
        assertEquals(2, result)
    }

    @Test
    fun findIndexInSmallArray2() {
        val result = RandomSelection.find(intArrayOf(4, 2, 3, 1, 5), 4)
        assertEquals(5, result)
    }

    @Test
    fun findIndexInLargeArray() {
        val result = RandomSelection.find(randomIntArray(10_000), 2)
        assertEquals(3, result)
    }

    @Test
    fun medianSmallArray() {
        val result = RandomSelection.median(intArrayOf(4, 2, 3, 1, 5))
        assertEquals(3, result)
    }

    @Test
    fun medianSmallArray2() {
        val result = RandomSelection.median(intArrayOf(7, 4, 2, 9, 3, 6, 8, 1, 5, 10))
        assertEquals(5, result)
    }

    @Test
    fun medianLargeArray() {
        val result = RandomSelection.median(randomIntArray(10_000))
        assertEquals(5000, result)
    }

    @Test
    fun medianLargeArray2() {
        val result = RandomSelection.median(randomIntArray(10_000_000))
        assertEquals(5_000_000, result)
    }

    private fun randomIntArray(size: Int): IntArray = (1..size).shuffled().toIntArray()
}