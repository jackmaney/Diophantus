Factorization
=======

In order to learn Java, I'm reconstructing some (long since lost) code that I wrote in Mathematica as a graduate student.

Back when you memorized your multiplication tables as a kid, something that made that task easy was the [Fundamental Theorem of Arithmetic](http://en.wikipedia.org/wiki/Fundamental_theorem_of_arithmetic), which states that every natural number larger than 1 factors uniquely into prime numbers.

However, there are other, stranger algebraic structures where factorizations need not be unique.

Let <code>d</code> denote a negative, square-free integer. If we consider the set

<center><pre>Z[sqrt(d)] = { a + b * sqrt(d) | a,b are integers }</pre></center>

then, it turns out that irreducible factorizations need not be unique. In particular, if <code>d = -5</code>, then we have

<center><pre>6 = 2 * 3 = (1 + sqrt(-5))(1 - sqrt(-5))</pre></center>

and it can be shown that each of <code>2, 3, 1+sqrt(-5), 1-sqrt(-5)</code> are irreducible (ie they "can't be broken down anymore" under multiplication).

The ultimate aim of this software distribution is to compute, for given <code>a</code>,<code>b</code>, and <code>d</code>, all of the irreducible factorizations of <code>a + b * sqrt(d)</code> in <code>Z[sqrt(d)]</code>.

Note that this is a work in progress.