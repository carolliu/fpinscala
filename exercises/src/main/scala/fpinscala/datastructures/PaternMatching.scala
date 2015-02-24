
//val x = List(1, 2, 3, 4, 5) match {
//  case Cons(x, Cons(2, Cons(4, _))) => x                     // 1
//  case Nil => 42                                             // 42
//  case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y        // 3
//  case Cons(h, t) => h + sum(t)                              // 1+14 = 15
//  case _ => 101                                              // 101
//}

//Should be match case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
// answer: 3

//scala> :load List.scalaLoading List.scala...
//defined trait List
//defined module Nil
//defined class Cons
//defined module List
//warning: previously defined trait List is not a companion to object List.
//Companions must be defined together; you may wish to use :paste mode for this.
//
//  scala> import List._
//import List._
//
//scala>   val l = List(1,2,3,4,5) match {
//|     case Cons(x, Cons(2, Cons(4, _))) => x
//    |     case Nil => 42
//    |     case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y case Cons(h, t) => h + sum(t)
//    |     case _ => 101
//    |   }
//l: Int = 3