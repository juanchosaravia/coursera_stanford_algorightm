import week1.Karatsuba
import week1.MergeSort
import week2.ClosestPointsBruteForce
import week2.MatrixMultiplicationBruteForce
import week2.MergeSortInvertions
import java.math.BigDecimal

fun main() {


    // runClosestPointsBruteForce()
    //runMatrixMultiplication()
    //runMergeSortInversion()
    //runKaratsuba()
}

/**
 * Result must be:
 * (1.714, 0) and (2.0, 0.5)
 */
fun runClosestPointsBruteForce() {
    val points: Array<Pair<Double, Double>> = arrayOf(
        0.0 to -1.3,
        1.714 to 0.0, 4.0 to 6.0, 2.0 to 0.5, 4.0 to 3.0, -1.0 to 1.0
    )
    //val result = ClosestPointsBruteForce.closest(points)
}

fun runMatrixMultiplication() {
    val result = MatrixMultiplicationBruteForce.multiply(
        arrayOf(intArrayOf(3, -2, 5), intArrayOf(3, 0, 4)),
        arrayOf(intArrayOf(2, 3), intArrayOf(-9, 0), intArrayOf(0, 4))
    )
}

private fun runMergeSort() {
    val unsortedList = listOf(11, 5, 4, 3, 2, 1, 22, 98, 0, 3, 2)
    val sortedList = MergeSort.sort(unsortedList)
    println(unsortedList)
    println(sortedList)
}

private fun runMergeSortInversion() {
    //val unsortedList = listOf(11, 5, 4, 3, 2, 1, 22, 98, 0, 3, 2)
    // val unsortedList = listOf(4,3,2,1)
    val unsortedList = listOf(7, 6, 5, 4, 3, 2, 1)
    val sortedList = MergeSortInvertions.sort(unsortedList)
    println(unsortedList)
    println(sortedList)
}
