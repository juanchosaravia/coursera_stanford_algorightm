package week2

import org.junit.jupiter.api.Test
import java.io.File

class MergeSortInversionsTest {

    @Test
    fun basicTest() {
        val unsortedList = listOf(6, 5, 4, 3, 2, 1)
        val (_, inversionCounts) = MergeSortInversions.sort(unsortedList)
        assert(inversionCounts == 15.0)
    }

    @Test
    fun basicTest2() {
        val unsortedList = listOf(8, 4, 2, 1)
        val (_, inversionCounts) = MergeSortInversions.sort(unsortedList)
        assert(inversionCounts == 6.0)
    }

    @Test
    fun basicTest3() {
        val unsortedList = listOf(1, 2, 3, 4, 5, 11, 10, 9, 8, 7, 6, 12, 13)
        val (_, inversionCounts) = MergeSortInversions.sort(unsortedList)
        assert(inversionCounts == 15.0)
    }

    @Test
    fun problem2() {
        val input = mutableListOf<Int>()
        val path = this::class.java.classLoader.getResource("week2/int_array_problem_2.txt")
        File(path.toURI()).forEachLine {
            input.add(it.toInt())
        }

        val (_, inversionCounts) = MergeSortInversions.sort(input)
        println(inversionCounts)
    }
}