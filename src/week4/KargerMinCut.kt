package week4

object KargerMinCut {

    fun minCut(graph: Graph): Graph {
        var updatedGraph = graph.copy()

        while (updatedGraph.vertexCount > 2) {
            updatedGraph = contractGraph(updatedGraph)
        }
        return updatedGraph
    }

    private fun contractGraph(graph: Graph): Graph {
        // pick random edge:
        val randomEdge = graph.edges.random()
        val (v1, v2) = randomEdge

        val newEdgeList = mutableListOf<Edge>()
        // merge v2 into v1
        graph.edges.forEach { edge ->
            val newEdge: Edge = when {
                edge.from == v2 -> {
                    edge.copy(from = v1)
                }
                edge.to == v2 -> {
                    edge.copy(to = v1)
                }
                else -> {
                    edge
                }
            }

            // ignore self-loops
            if (newEdge.from != newEdge.to) {
                newEdgeList.add(newEdge)
            }
        }

        newEdgeList.remove(randomEdge)

        return Graph(graph.vertices, newEdgeList, graph.vertexCount - 1)
    }

    data class Vertex(val id: Int)
    data class Edge(val from: Int, val to: Int)
    data class Graph(val vertices: List<Vertex>, val edges: List<Edge>, val vertexCount: Int)
}