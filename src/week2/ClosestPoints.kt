package week2

import kotlin.math.pow
import kotlin.math.sqrt

/**
 * O(n*log(n))
 */
object ClosestPoints {

    fun closest(points: Array<Point>): Pair<Point, Point> {
        val (px, py) = sort(points)
        return closestPair(px, py)
    }

    private fun closestPair(
        px: Array<Point>,
        py: Array<Point>
    ): Pair<Point, Point> {
        if (px.size < 4) {
            return closestPairSmallList(px)
        }

        val n2 = px.size / 2
        val (p1, q1) = closestPair(px.copyOfRange(0, n2), py.copyOfRange(0, n2))
        val (p2, q2) = closestPair(px.copyOfRange(n2, px.size), py.copyOfRange(n2, py.size))
        val pq1Distance = getDistance(p1, q1)
        val pq2Distance = getDistance(p2, q2)
        val delta = if (pq1Distance < pq2Distance) pq1Distance else pq2Distance
        val min12Pair = if (pq1Distance < pq2Distance) p1 to q1 else p2 to q2

        val pair3: Pair<Point, Point> = closestSplitPoints(px, py, delta)
            ?: return min12Pair

        return if (getDistance(min12Pair.first, min12Pair.second) < getDistance(pair3.first, pair3.second)) {
            min12Pair
        } else {
            pair3
        }
    }

    private fun closestPairSmallList(p: Array<Point>): Pair<Point, Point> {
        if (p.size < 2) throw IllegalArgumentException("Set at least 2 points to compare")
        if (p.size == 2) return p[0] to p[1]

        var closestPair: Pair<Point, Point>? = null
        var closestDistance = 0.0

        for (i in 0 until p.lastIndex) {
            if (i == 0) {
                closestPair = p[i] to p[i + 1]
                closestDistance = getDistance(closestPair.first, closestPair.second)
            } else {
                val pi = p[i]
                val pj = p[i + 1]
                val newDistance = getDistance(pi, pj)
                if (newDistance < closestDistance) {
                    closestPair = pi to pj
                    closestDistance = newDistance
                }
            }
        }
        return closestPair ?: throw IllegalStateException("There must be at least a new pair")
    }

    private fun closestSplitPoints(
        px: Array<Point>,
        py: Array<Point>,
        delta: Double
    ): Pair<Point, Point>? {
        val xMid = px[px.size / 2].x
        val xLeftDelta = xMid - delta
        val xRightDelta = xMid + delta
        var closestDistance = delta
        var closestPair: Pair<Point, Point>? = null

        val sy = py.filter { it.x in xLeftDelta..xRightDelta }
        if (sy.isEmpty()) return null

        val limit = if (sy.size < 7) sy.size else 7
        for (i in 0 until sy.lastIndex) { // ignore last point
            var j = i + 1
            val pi = sy[i]
            while (j < limit) {
                val pj = sy[j]
                val newDistance = getDistance(pi, pj)
                if (newDistance < closestDistance) {
                    closestDistance = newDistance
                    closestPair = pi to pj
                }
                j++
            }
        }

        return closestPair
    }

    private fun sort(points: Array<Point>)
            : Pair<Array<Point>, Array<Point>> {
        if (points.size < 2) {
            return points to points
        }

        val n2 = points.size / 2
        return merge(sort(points.copyOfRange(0, n2)), sort(points.copyOfRange(n2, points.size)))
    }

    private fun merge(
        p: Pair<Array<Point>, Array<Point>>,
        q: Pair<Array<Point>, Array<Point>>
    ): Pair<Array<Point>, Array<Point>> {
        val px = p.first
        val py = p.second

        val qx = q.first
        val qy = q.second

        // join px with qx
        val pqx = mergeTwoLists(px, qx) { it.x }

        // join py with qy
        val pqy = mergeTwoLists(py, qy) { it.y }
        return pqx to pqy
    }

    private fun mergeTwoLists(
        leftList: Array<Point>,
        rightList: Array<Point>,
        comparison: (Point) -> Double
    ): Array<Point> {
        var leftIndex = 0
        var rightIndex = 0

        val sortedList = mutableListOf<Point>()
        while (leftIndex < leftList.size && rightIndex < rightList.size) {
            if (comparison(leftList[leftIndex]) < comparison(rightList[rightIndex])) {
                sortedList.add(leftList[leftIndex])
                leftIndex++
            } else {
                sortedList.add(rightList[rightIndex])
                rightIndex++
            }
        }

        while (leftIndex < leftList.size) {
            sortedList.add(leftList[leftIndex])
            leftIndex++
        }

        while (rightIndex < rightList.size) {
            sortedList.add(rightList[rightIndex])
            rightIndex++
        }

        return sortedList.toTypedArray()
    }

    private fun getDistance(p1: Point, p2: Point): Double {
        return sqrt(
            (p1.x - p2.x).pow(2.0) +
                    (p1.y - p2.y).pow(2.0)
        )
    }
}

data class Point(val x: Double, val y: Double)