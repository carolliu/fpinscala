//package fpinscala.datastructures

sealed trait List[+A] // `List` data type, parameterized on a type, `A`
case object Nil extends List[Nothing] // A `List` data constructor representing the empty list
case class Cons[+A](head: A, tail: List[A]) extends List[A] // Another data constructor, representing nonempty lists. Note that `tail` is another `List[A]`, which may be `Nil` or another `Cons`.

object List { // `List` companion object. Contains functions for creating and working with lists.
  def sum(ints: List[Int]): Int = ints match { // A function that uses pattern matching to add up a list of integers
    case Nil => 0 // The sum of the empty list is 0.
    case Cons(x,xs) => x + sum(xs) // The sum of a list starting with `x` is `x` plus the sum of the rest of the list.
  } 
  
  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x,xs) => x * product(xs)
  }
  
  def apply[A](as: A*): List[A] = // Variadic function syntax
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  val x = List(1,2,3,4,5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42 
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t) => h + sum(t)
    case _ => 101 
  }

  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h,t) => Cons(h, append(t, a2))
    }

  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B = // Utility functions
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }
  
  def sum2(ns: List[Int]) = 
    foldRight(ns, 0)((x,y) => x + y)
  
  def product2(ns: List[Double]) = 
    foldRight(ns, 1.0)(_ * _) // `_ * _` is more concise notation for `(x,y) => x * y`; see sidebar


  def tail[A](l: List[A]): List[A] = {
    l match {
      case Nil => sys.error("trying to get tail of empty List")
      case Cons(_, t) => t
    }
  }

  def setHead[A](l: List[A], h: A): List[A] = {
    l match {
      case Nil => sys.error("trying to set head of empty List")
      case Cons(_, t) => Cons(h, t)
    }
  }

  def drop[A](l: List[A], n: Int): List[A] = {
    l match {
      case Nil => Nil
      case Cons(_, t) if n > 1 => drop(t, n-1)
      case Cons(_, t) => t
    }
  }

//  exercise 3.5
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = {
    l match {
      case Nil => Nil
      case Cons(h, t) if f(h) => dropWhile(t, f)
      case Cons(_, t) => t
    }
  }
//  scala> dropWhile[Int](List(1,2,3,-4,5),(x:Int) => x > 0)
//  res21: List[Int] = Cons(5,Nil)

//  exercise 3.6
  def init[A](l: List[A]): List[A] = {
    def compose(rl: List[A], il: List[A]): List[A] = {
      rl match {
        case Cons(h, Nil) => il
        case Cons(h, t) => compose(t, append(il, List(h)))
      }
    }
    compose(l, List())
  }

//  exercise 3.7, will re-visit in Chapter 5
//  Note: function 'f' will be evalutated firstly and traverse all the way to the end of the list.
//  So it can't be terminated earlier when foldRight() is called.
//  We need to inspect the list firstly before calling foldRight for early termination.
  def product3(ns: List[Double]): Double = ns match {
    case Cons(0.0, xs) => 0.0
    case _ => foldRight(ns, 1.0)(_ * _)
  }

//  exercise 3.8
  def length[A](l: List[A]): Int = foldRight(l, 0)((_, count) => count + 1)

  def foldLeft[A,B](l: List[A], z: B)(f: (B, A) => B): B = sys.error("todo")

  def map[A,B](l: List[A])(f: A => B): List[B] = sys.error("todo")
}