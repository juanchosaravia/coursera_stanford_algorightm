package week2

import kotlin.math.pow
import kotlin.math.sqrt

/**
 * O(n^2)
 */
object ClosestPointsBruteForce {

    fun closest(points: Array<Point>): Pair<Point, Point>? {
        val n = points.size
        var shortestDistance = 0.0
        var closestPoints: Pair<Point, Point>? = null

        for (i in 0 until n - 1) {
            val nextI = i + 1
            for (j in nextI until n) {
                val distance = getDistance(points[i], points[j])
                if (i == 0 && j == 1) {
                    shortestDistance = distance
                    closestPoints = points[i] to points[j]
                } else if (distance < shortestDistance) {
                    shortestDistance = distance
                    closestPoints = points[i] to points[j]
                }
            }
        }
        return closestPoints
    }

    private fun getDistance(p1: Point, p2: Point): Double {
        return sqrt(
            (p1.x - p2.x).pow(2.0) +
                    (p1.y - p2.y).pow(2.0)
        )
    }
}
