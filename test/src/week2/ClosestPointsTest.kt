package week2

import org.junit.jupiter.api.Test
import kotlin.system.measureNanoTime

class ClosestPointsTest {

    @Test
    fun splitPoints() {
        val p1Expected = Point(1.5, 2.5)
        val p2Expected = Point(2.0, 2.0)
        val (p1, p2) = ClosestPoints.closest(
            arrayOf(
                Point(10.0, 11.0),
                Point(5.0, 6.0),
                Point(8.0, 9.0),
                p2Expected,
                p1Expected,
                Point(1.0, 1.0),
                Point(20.0, 8.0),
                Point(23.0, 18.0)
            )
        )

        assert(p1 == p1Expected || p1 == p2Expected)
        assert(p2 == p2Expected || p2 == p1Expected)
    }

    @Test
    fun bigList(){
        val pointsList = mutableListOf<Point>()
        for (i in 0..1_000_000) {
            pointsList.add(Point(i.toDouble(), i.toDouble()))
        }
        val lemmanTime = measureNanoTime {
            ClosestPoints.closest(pointsList.toTypedArray())
        }
        println(lemmanTime)
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