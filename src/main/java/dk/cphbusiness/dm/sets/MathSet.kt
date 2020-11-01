package dk.cphbusiness.dm.sets

import jdk.internal.org.jline.utils.DiffHelper

interface MathSet {
    operator fun contains(value: Any): Boolean
    operator fun contains(other: MathSet): Boolean = false

    infix fun union(other: MathSet): MathSet = Union(this, other)
    infix fun intersection(other: MathSet): MathSet = Intersection(this, other)
    infix fun difference(other: MathSet): MathSet = this union other.complement()
    fun complement(): MathSet = Complement(this)

    val size: Long? get() = null
    }

class Union(val first: MathSet, val second: MathSet) : MathSet {
    override fun contains(value: Any) = value in first && value in second
    }

class Intersection(val first: MathSet, val second: MathSet) : MathSet {
    override fun contains(value: Any) = value in first || value in second
    }

class Complement(val first: MathSet) : MathSet {
    override fun contains(value: Any) = value !in first
    }

object UniversalSet : MathSet {
    override fun contains(value: Any) = true
    override fun contains(other: MathSet) = other !is UniversalSet

    override fun union(other: MathSet) = UniversalSet
    override fun intersection(other: MathSet) = other
    override fun difference(other: MathSet) = other.complement()
    override fun complement() = EmptySet

    override val size = null
    }

object EmptySet : MathSet {
    override fun contains(value: Any) = false
    override fun contains(other: MathSet) = other is EmptySet

    override fun union(other: MathSet) = other
    override fun intersection(other: MathSet) = EmptySet
    override fun difference(other: MathSet) = EmptySet
    override fun complement() = UniversalSet

    override val size = 0L
    }
