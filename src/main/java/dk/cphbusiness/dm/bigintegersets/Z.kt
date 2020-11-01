package dk.cphbusiness.dm.bigintegersets

import java.math.BigInteger

abstract sealed class Z : Comparable<Z> {
    abstract fun succ(): Z
    abstract operator fun unaryMinus(): Z
    abstract operator fun plus(other: Z): Z
    abstract operator fun minus(other: Z): Z

    object Undefined : Z() {
        override fun toString() = "?"
        override operator fun unaryMinus() = Undefined
        override fun succ() = Undefined

        override operator fun plus(other: Z) = Undefined
        override operator fun minus(other: Z) = Undefined
        }

    object NegativeInfinity : Z() {
        override fun toString() = "-∞"
        override operator fun unaryMinus() = PositiveInfinity
        override fun succ() = NegativeInfinity

        override operator fun plus(other: Z) = if (other is PositiveInfinity) Undefined else NegativeInfinity
        override operator fun minus(other: Z) = NegativeInfinity

        }
    class Value(val value: BigInteger) : Z() {
        constructor(stringValue: String) : this(BigInteger(stringValue))
        constructor(longValue: Long) : this(BigInteger.valueOf(longValue))
        constructor(intValue: Int) : this(BigInteger.valueOf(intValue.toLong()))

        override fun toString() = value.toString()
        override fun succ() = Value(value + BigInteger.ONE)

        override operator fun plus(other: Z) = when (other) {
            is Undefined -> Undefined
            is Value -> Value(this.value + other.value)
            is NegativeInfinity -> NegativeInfinity
            is PositiveInfinity -> PositiveInfinity
            }

        override operator fun minus(other: Z) = when (other) {
            is Undefined -> Undefined
            is Value -> Z.Value(this.value - other.value)
            is NegativeInfinity -> PositiveInfinity
            is PositiveInfinity -> NegativeInfinity
            }

        override operator fun unaryMinus() = Value(-value)

        }
    object PositiveInfinity : Z() {
        override fun toString() = "∞"
        override operator fun unaryMinus() = NegativeInfinity
        override fun succ() = PositiveInfinity

        override operator fun plus(other: Z) = when (other) {
            is Undefined -> Undefined
            is NegativeInfinity -> Undefined
            else -> PositiveInfinity
            }
        override operator fun minus(other: Z) = when (other) {
            is Undefined -> Undefined
            is PositiveInfinity -> Undefined
            else -> PositiveInfinity
            }
        }

    override fun compareTo(other: Z): Int =
        when (this) {
            is Undefined -> throw UndefinedIntegerException()
            is NegativeInfinity -> if (other is NegativeInfinity) 0 else -1
            is Value -> when (other) {
                is Undefined -> throw UndefinedIntegerException()
                is NegativeInfinity -> 1
                is Value -> this.value.compareTo(other.value)
                is PositiveInfinity -> -1
                }
            is PositiveInfinity -> if (other is PositiveInfinity) 0 else 1
            }

    operator fun rangeTo(endInclusive: Int) = IntegerRangeSet(this, Value(endInclusive))
    operator fun rangeTo(endInclusive: Long) = IntegerRangeSet(this, Value(endInclusive))
    operator fun rangeTo(endInclusive: String) = IntegerRangeSet(this, Value(endInclusive))

    operator fun rangeTo(endInclusive: Z) = IntegerRangeSet(this, endInclusive)

    }

operator fun Int.rangeTo(endInclusive: Z) = IntegerRangeSet(Z.Value(this), endInclusive)
operator fun Long.rangeTo(endInclusive: Z) = IntegerRangeSet(Z.Value(this), endInclusive)
operator fun String.rangeTo(endInclusive: Z) = IntegerRangeSet(Z.Value(this), endInclusive)

fun Int.toZ() = Z.Value(this)
fun Long.toZ() = Z.Value(this)
fun String.toZ() = Z.Value(this)

val inf = Z.PositiveInfinity
