package dk.cphbusiness.dm.bigintegersets

interface MathSet {
    operator fun contains(element: Any): Boolean

    val size: Z get() = inf

    infix fun union(other: MathSet): MathSet = Union(this, other)
    infix fun intersection(other: MathSet): MathSet = Intersection(this, other)
    infix fun difference(other: MathSet): MathSet = Difference(this, other)
    fun complement(): MathSet = Complement(this)

    infix fun isSubsetOf(other: MathSet) = this in other
    operator fun contains(other: MathSet): Boolean = false

    fun toBracketedString(): String = "($this)"
    }

/*
public class Union implements MathSet {
    private final MathSet a;
    private fanial MathSet b;

    public Union(MathSet a, MathSet b) {
      this.a = a;
      this.b = b;
      }

    // getters for a and b

 */

class Union(val a: MathSet, val b: MathSet) : MathSet {
    override operator fun contains(element: Any) = element in a || element in b

    override fun toString() = "${a.toBracketedString()} ∪ ${b.toBracketedString()}"
    }

class Intersection(val a: MathSet, val b: MathSet): MathSet {
    override operator fun contains(element: Any) = element in a && element in b

    override fun toString() = "${a.toBracketedString()} ∩ ${b.toBracketedString()}"
    }

class Difference(val a: MathSet, val b: MathSet): MathSet {
    override operator fun contains(element: Any) = element in a && element !in b
    }

class Complement(val a: MathSet): MathSet {
    override operator fun contains(element: Any) = element !in a
    }

object UniversalSet : MathSet {
    override operator fun contains(element: Any) = true
    override fun union(other: MathSet) = UniversalSet
    override fun intersection(other: MathSet) = other
    override fun difference(other: MathSet) = other.complement()
    override fun complement() = EmptySet
    }

object EmptySet : MathSet {
    override operator fun contains(element: Any) = false
    override fun union(other: MathSet) = other
    override fun intersection(other: MathSet) = EmptySet
    override fun difference(other: MathSet) = EmptySet
    override fun complement() = UniversalSet
    }

