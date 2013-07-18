[![Stories in Ready](http://badge.waffle.io/jackmaney/Diophantus.png)](http://waffle.io/jackmaney/Diophantus)  
Diophantus
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

Example
-------

Take a look at the file [Diophantus.java in com.jackmaney.Diophantus](https://github.com/jackmaney/Diophantus/blob/master/src/com/jackmaney/Diophantus/Diophantus.java). The source of that file (as of this writing) is:

<pre>
	package com.jackmaney.Diophantus;


	import com.jackmaney.Diophantus.element.Element;


	public class Diophantus {

		public static void main(String[] args) {
			Element e = new Element(6,0,-5);
			
			System.out.println(e.getIrreducibleFactorizations());
			

		}

	}
</pre>

Note that we're creating a new `Element` that corresponds to `6 = 6 + 0 * sqrt(-5)`. The output is a `Vector` of [`Factorizations`](https://github.com/jackmaney/Diophantus/blob/master/src/com/jackmaney/Diophantus/Factorization.java) that, when printed, looks like

<pre>[(1 - 1 * sqrt(-5))*(1 + 1 * sqrt(-5)), 2*3]</pre>

conforming to our expectations above. Of course, feel free to tinker around with the parameters in this class. For example:

* The irreducible factorizations of `81` in `Z[sqrt(-14)]` are `[(5 - 2 * sqrt(-14))*(5 + 2 * sqrt(-14)), 3^4]`.
* There is only one irreducible factorization of `1024 + 768 * sqrt(-39)` in `Z[sqrt(-39)]`, namely `2^8*(4 + 3 * sqrt(-39))`.
* That doesn't mean that every element of `Z[sqrt(-39)]` enjoys unique factorization! The factorizations of `1000 + 1000 * sqrt(-39)` are:
<pre>[5*(19 + 1 * sqrt(-39))*(29 + 9 * sqrt(-39)), 2*5*(7 + 3 * sqrt(-39))*(31 + 1 * sqrt(-39)), 2^3*5^3*(1 + 1 * sqrt(-39))]</pre>
* There are two factorizations of `1024 + 768 * sqrt(-191)` in `Z[sqrt(-191)]`:

<pre>[(33 + 1 * sqrt(-191))*(141 + 19 * sqrt(-191)), 2^8*(4 + 3 * sqrt(-191))]</pre>


Why "Diophantus"?
-------

[Diophantus of Alexandria](http://en.wikipedia.org/wiki/Diophantus) was an ancient Greek mathematician and philosopher after whom [Diophantine equations](http://en.wikipedia.org/wiki/Diophantine_equation) are named. Finding irreducible factors of a given element of <code>Z[sqrt(d)]</code> hinges upon finding integer solutions for <code>x</code> and <code>y</code> to the following Diophantine equation:

<center><pre>x^2 - d * y^2 = n</pre></center>

Hence, the name.

Contact
-------

Jack Maney
* [http://jackmaney.com](jackmaney.com)
* [mailto:jackmaney@gmail.com](jackmaney@gmail.com)