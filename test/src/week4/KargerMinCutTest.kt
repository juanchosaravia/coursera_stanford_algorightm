package week4

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class KargerMinCutTest {

    @Test
    fun loopMinCutTest() {
        val graph = KargerMinCut.Graph(
            vertices = listOf(
                KargerMinCut.Vertex(1),
                KargerMinCut.Vertex(2),
                KargerMinCut.Vertex(3),
                KargerMinCut.Vertex(4)
            ),
            edges = listOf(
                KargerMinCut.Edge(1, 2),
                KargerMinCut.Edge(1, 3),

                KargerMinCut.Edge(2, 1),
                KargerMinCut.Edge(2, 3),
                KargerMinCut.Edge(2, 4),

                KargerMinCut.Edge(3, 1),
                KargerMinCut.Edge(3, 2),

                KargerMinCut.Edge(4, 2)
            ),
            vertexCount = 4
        )

        val minCut = min(graph = graph)
        assertEquals(1, minCut)
    }

    @Test
    fun minCutMediumSize() {
        val vertices = listOf(
            KargerMinCut.Vertex(1),
            KargerMinCut.Vertex(2),
            KargerMinCut.Vertex(3),
            KargerMinCut.Vertex(4),
            KargerMinCut.Vertex(5),
            KargerMinCut.Vertex(6),
            KargerMinCut.Vertex(7),
            KargerMinCut.Vertex(8)
        )

        val edges = listOf(
            KargerMinCut.Edge(1, 2),
            KargerMinCut.Edge(1, 3),
            KargerMinCut.Edge(1, 4),

            KargerMinCut.Edge(2, 1),
            KargerMinCut.Edge(2, 3),
            KargerMinCut.Edge(2, 4),

            KargerMinCut.Edge(3, 1),
            KargerMinCut.Edge(3, 2),
            KargerMinCut.Edge(3, 4),
            KargerMinCut.Edge(3, 5),

            KargerMinCut.Edge(4, 1),
            KargerMinCut.Edge(4, 2),
            KargerMinCut.Edge(4, 3),
            KargerMinCut.Edge(4, 6),

            KargerMinCut.Edge(5, 3),
            KargerMinCut.Edge(5, 6),
            KargerMinCut.Edge(5, 7),
            KargerMinCut.Edge(5, 8),

            KargerMinCut.Edge(6, 4),
            KargerMinCut.Edge(6, 5),
            KargerMinCut.Edge(6, 7),
            KargerMinCut.Edge(6, 8),

            KargerMinCut.Edge(7, 5),
            KargerMinCut.Edge(7, 6),
            KargerMinCut.Edge(7, 8),

            KargerMinCut.Edge(8, 5),
            KargerMinCut.Edge(8, 6),
            KargerMinCut.Edge(8, 7)
        )

        val graph = KargerMinCut.Graph(
            vertices = vertices,
            edges = edges,
            vertexCount = vertices.size
        )

        val minCut = min(graph = graph)
        assertEquals(2, minCut)
    }

    @Test
    fun longTest() {
        val graph = loadFile()
        val minCut = min(graph = graph)

        assertEquals(17, minCut)
    }

    private fun min(loop: Int = 1000, graph: KargerMinCut.Graph): Int {
        var currentMinCut = 0
        for (i in 1..loop) {
            val minCut = KargerMinCut.minCut(graph).edges.size / 2
            if (i == 1) {
                currentMinCut = minCut
            } else if (minCut < currentMinCut) {
                currentMinCut = minCut
            }
        }

        return currentMinCut
    }

    private val lineRegex = "\\s+".toRegex()

    private fun loadFile(): KargerMinCut.Graph {
        val edges = mutableListOf<KargerMinCut.Edge>()
        val vertices = mutableListOf<KargerMinCut.Vertex>()

        val path = this::class.java.classLoader.getResource("week4/karger_min_cut.txt")
        File(path.toURI()).forEachLine { line ->
            val columns = line.split(lineRegex).filter { it.isNotEmpty() }
            val v1 = columns[0].toInt()
            vertices.add(KargerMinCut.Vertex(v1))
            for ((i, c) in columns.withIndex()) {
                if (i != 0) {
                    edges.add(KargerMinCut.Edge(v1, c.toInt()))
                }
            }
        }
        return KargerMinCut.Graph(vertices, edges, vertexCount = vertices.size)
    }
}