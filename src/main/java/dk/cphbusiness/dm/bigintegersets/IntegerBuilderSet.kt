package dk.cphbusiness.dm.bigintegersets

class IntegerBuilderSet(val text: String, val predicate: (Z) -> Boolean) : IntegerSet() {
    constructor(predicate: (Z) -> Boolean): this("P(n)", predicate)

    override fun contains(element: Z) = predicate(element)

    override fun toString() = "{ n ∈ Z | $text }"
    }
