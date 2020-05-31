package week1

import org.junit.jupiter.api.Test
import java.math.BigDecimal

class KaratsubaTests {
    @Test
    fun `multiplySingleDigitNumbers`() {
        val result = Karatsuba.multiply(1.toBigDecimal(), 2.toBigDecimal())
        assert(result == 2.toBigDecimal())
    }

    @Test
    fun `multiplySampleNumbers`() {
        val result = Karatsuba.multiply(1234.toBigDecimal(), 4321.toBigDecimal())
        assertBigDecimalResult(result, 5332114.toBigDecimal())
    }

    @Test
    fun exercise() {
        val expectedResult =
            "8539734222673567065463550869546574495034888535765114961879601127067743044893204848617875072216249073013374895871952806582723184"

        val x = BigDecimal("3141592653589793238462643383279502884197169399375105820974944592")
        val y = BigDecimal("2718281828459045235360287471352662497757247093699959574966967627")

        val result2 = Karatsuba.multiply(x, y)
        assertBigDecimalResult(expectedResult.toBigDecimal(), result2)
    }

    /**
     * This is to avoid failures when the result is 0.000 and comparing against 0 fails
     */
    private fun assertBigDecimalResult(x: BigDecimal, y: BigDecimal) {
        assert(((x - y) <= 0.toBigDecimal()) && ((x - y) >= 0.toBigDecimal()))
    }
}