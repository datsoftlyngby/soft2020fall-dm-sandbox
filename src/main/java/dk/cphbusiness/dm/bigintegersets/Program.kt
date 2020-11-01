package dk.cphbusiness.dm.bigintegersets

fun memberPrint(M: MathSet) {
    println(M)
    println("-----------------")
    fun check(M: MathSet, v: Any) {
        println("$v is member: ${v in M}")
        }
    check(M, -20)
    check(M, 5)
    check(M, 8)
    check(M, 20)
    check(M, 70)
    check(M, 105)
    check(M, 200)
    println()
    }

fun main() {
    // val r2 = IntegerBoundary.IntegerValue("300")..IntegerBoundary.PositiveInfinity

    val A = 20..inf
    memberPrint(A)
    val B = (-inf)..30
    memberPrint(B)
    val C = A union B
    memberPrint(C)
    val D = A intersection B
    memberPrint(D)

    val S1 = 10..20.toZ()
    val S2 = 30..40.toZ()
    val S3 = 20..29.toZ()

    println("S1: $S1, S2: $S2, S3: $S3")

    val U1 = S1 union S2
    println(U1)

    val U2 = S3 union U1
    println(U2)
    println(S1 union S3 union S2)

    val a: Z = 8.toZ()
    val b: Z = 9.toZ()

    println(integerSetOf(7, 9, 13))

    // val F = integerSetOf { n -> n mod 2 == 0 }

    }