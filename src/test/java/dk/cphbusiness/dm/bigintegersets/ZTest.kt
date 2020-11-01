package dk.cphbusiness.dm.bigintegersets

import org.junit.jupiter.api.Test
import kotlin.test.expect
import dk.cphbusiness.dm.bigintegersets.Z.*
import java.math.BigInteger

/*
class IntegerBoundaryProvider : ArgumentsProvider {
    override fun provideArguments(contex: ExtensionContext?) =
            Stream.of()
    }
*/

class ZTest {

    @Test
    fun `secondary constructors should work on positive numbers`() {
        val twoHundred = BigInteger.valueOf(200)
        expect(twoHundred) { Value(200).value }
        expect(twoHundred) { Value(200L).value }
        expect(twoHundred) { Value("200").value }
        }

    @Test
    fun `secondary constructors should work on negative numbers`() {
        val minusThreeHundred = BigInteger.valueOf(-300)
        expect(minusThreeHundred) { Value(-300).value }
        expect(minusThreeHundred) { Value(-300L).value }
        expect(minusThreeHundred) { Value("-300").value }
        }


    @Test
    fun `negative infinity should be smaller anything else than negative infinity`() {
        expect(true) { NegativeInfinity == NegativeInfinity }
        expect(true) { NegativeInfinity < Value(BigInteger("-1000000000000000000000000000"))}
        expect(true) { NegativeInfinity < Value(BigInteger("1000000000000000000000000000"))}
        }
    }