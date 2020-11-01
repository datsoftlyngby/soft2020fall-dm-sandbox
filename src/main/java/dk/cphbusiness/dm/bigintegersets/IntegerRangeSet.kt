package dk.cphbusiness.dm.bigintegersets


class IntegerRangeSet(
        override val start: Z,
        override val endInclusive: Z
        ) : IntegerSet(), ClosedRange<Z> {
    constructor(range: IntRange) : this(Z.Value(range.first), Z.Value(range.last))
    constructor(range: LongRange) : this(Z.Value(range.first), Z.Value(range.last))

    override val size: Z by lazy { endInclusive - start }

    override fun toString() = "{$start...$endInclusive}"

    override fun toBracketedString() = toString()

    private fun min(a: Z, b: Z) = if (a < b) a else b
    private fun max(a: Z, b: Z) = if (a > b) a else b

    infix fun overlaps(other: IntegerRangeSet) =
        this.endInclusive.succ() >= other.start && other.endInclusive.succ() >= this.start

    override fun union(other: MathSet): MathSet =
        when (other) {
            is Union -> {
                (this union other.a) union (this union other.b)
                }
            is IntegerRangeSet ->
                if (this overlaps other) IntegerRangeSet(min(this.start, other.start), max(this.endInclusive, other.endInclusive))
                else super.union(other)
            else -> super.union(other)
        }

    override fun intersection(other: MathSet) =
            if (other is IntegerRangeSet && this overlaps other)
                IntegerRangeSet(max(this.start, other.start), min(this.endInclusive, other.endInclusive))
            else super.union(other)

    override fun contains(value: Z): Boolean {
        return super<ClosedRange>.contains(value)
        }

    override fun contains(other: MathSet) = compareTo(other) == 1

    fun compareTo(other: MathSet): Int {
        if (other !is IntegerRangeSet) return 2
        if (this.start == other.start && other.endInclusive == this.endInclusive) return 0
        if (this.start <= other.start && other.endInclusive <= this.endInclusive) return 1
        if (other.start <= this.start && this.endInclusive <= other.endInclusive) return -1
        return -2
        }

    }