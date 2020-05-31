package week2

/**
 * O(n^2)
 */
object MatrixMultiplicationBruteForce {

    /**
     * m1 and m2 are square and same size
     */
    fun multiply(m1: Array<IntArray>, m2: Array<IntArray>): Array<IntArray> {
        val n = if (m1.size < m2.size) m1.size else m2.size
        val (m, l) = if (m1[0].size < m2[0].size) m1[0].size to m2[0].size else m2[0].size to m1[0].size
        val product = Array(n) { IntArray(m) }

        for (i in 0 until n) {
            for (j in 0 until m) {
                for (k in 0 until l) {
                    product[i][j] += m1[i][k] * m2[k][j]
                }
            }
        }

        println("Product of two matrices is: ")
        for (row in product) {
            for (column in row) {
                print("$column    ")
            }
            println()
        }
        return product
    }
}