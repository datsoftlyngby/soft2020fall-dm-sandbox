package dk.cphbusiness.dm.sets

abstract class IntegerSet : MathSet {
    abstract operator fun contains(value: Long): Boolean

    override fun contains(element: Any) =
            when (element) {
                is Int -> contains(element.toLong())
                is Long -> contains(element)
                else -> false
            }

    }

class IntegerRangeSet(val start: Long?, val end: Long?) : IntegerSet() {
    override fun contains(value: Long) = value in (start ?: Long.MIN_VALUE)..(end ?: Long.MAX_VALUE - 1)

    override val size: Long? = if (start == null || end == null) null else end - start
    }
