package week1

import java.math.BigDecimal
import kotlin.math.max
import kotlin.math.pow

object Karatsuba {
    private val ten = 10.toBigDecimal()
    fun multiply(x: BigDecimal, y: BigDecimal): BigDecimal {
        if (x < ten || y < ten) {
            return x * y
        }
        var xs = x.toString()
        var ys = y.toString()
        var n = max(xs.length, ys.length)
        if (n % 2 != 0) {
            n++
        }
        val n2 = (n / 2)

        xs = xs.padStart(n, '0')
        val xh = xs.substring(0, n2)
        val xl = xs.substring(n2, xs.length)

        ys = ys.padStart(n, '0')
        val yh = ys.substring(0, n2)
        val yl = ys.substring(n2, ys.length)

        val a = multiply(xh.toBigDecimal(), yh.toBigDecimal())
        val d = multiply(xl.toBigDecimal(), yl.toBigDecimal())
        val e: BigDecimal = multiply(
            (xh.toBigDecimal() + xl.toBigDecimal()),
            (yh.toBigDecimal() + yl.toBigDecimal())
        ) - a - d

        return (a * (10.0.pow(n.toDouble())).toBigDecimal()) +
                (e * (10.0.pow((n2).toDouble())).toBigDecimal()) + d
    }
}