package dk.cphbusiness.dm.bigintegersets

class IntegerCollectionSet(private val elements: Set<Z>): IntegerSet() {
    companion object {
        var showLimit = 10
        }

    override fun contains(value: Z) = elements.contains(value)

    override val size: Z by lazy { elements.size.toZ() }

    override fun toString() = elements.joinToString(separator = ", ", prefix = "{", postfix = "}") { it.toString() }

    }
