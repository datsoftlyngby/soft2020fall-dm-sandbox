package dk.cphbusiness.dm.bigintegersets

import java.math.BigInteger

abstract class IntegerSet : MathSet {
    abstract operator fun contains(value: Z): Boolean

    override fun contains(element: Any) =
            when (element) {
                is Int -> contains(element.toZ())
                is Long -> contains(element.toZ())
                is String -> contains(element.toZ())
                is BigInteger -> contains(Z.Value(element))
                is Z -> contains(element)
                else -> false
            }

    }

fun integerSetOf(vararg elements: Z) = if (elements.size == 0) EmptySet else IntegerCollectionSet(setOf(*elements))
fun integerSetOf(vararg elements: Int) = if (elements.size == 0) EmptySet else IntegerCollectionSet(elements.map { it.toZ() }.toSet())
fun integerSetOf(vararg elements: Long) = if (elements.size == 0) EmptySet else IntegerCollectionSet(elements.map { it.toZ() }.toSet())

fun integerSetOf(predicate: (Z) -> Boolean) = IntegerBuilderSet(predicate)

