package week2

import org.junit.jupiter.api.Test
import kotlin.system.measureNanoTime

class ClosestPointsTest {

    @Test
    fun t1() {
        val p1Expected = Point(1.5, 2.5)
        val p2Expected = Point(2.0, 2.0)
        val (p1, p2) = ClosestPoints.closest(
            arrayOf(
                Point(5.0, 6.0),
                Point(8.0, 9.0),
                Point(1.0, 1.0),
                p2Expected,
                Point(20.0, 8.0),
                p1Expected
            )
        )

        assert(p1 == p1Expected)
        assert(p2 == p2Expected)
    }

    @Test
    fun fasterAlg() {
        val pointsList = mutableListOf<Point>()
        var found = false
        var limit = 10
        for (i in 0..limit) {
            pointsList.add(Point(i.toDouble(), i.toDouble()))
        }
        while (!found) {
            val points = pointsList.toTypedArray()
            val lemmanTime = measureNanoTime {
                ClosestPoints.closest(points)
            }
            val bruteForceTime = measureNanoTime {
                ClosestPointsBruteForce.closest(points)
            }

            if (lemmanTime < bruteForceTime) {
                found = true
            }
            limit++
            pointsList.add(Point(limit.toDouble(), limit.toDouble()))
        }

        println("Iteration brake: $limit")
    }
}