package week3

import kotlin.random.Random

object RandomPivotProcessor {

    fun create(): (IntArray, Int, Int) -> Unit = { array, firstIndex, untilIndex ->
        val randomIndex = Random.nextInt(firstIndex, untilIndex)

        val firstValue = array[firstIndex]
        array[firstIndex] = array[randomIndex]
        array[randomIndex] = firstValue
    }
}