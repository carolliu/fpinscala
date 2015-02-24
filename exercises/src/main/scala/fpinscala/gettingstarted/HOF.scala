object HOF {
  def isSorted[A](as: Array[A], ordered: (A,A) => Boolean): Boolean = {
    def loop(ar: Array[A], ordered: (A,A) => Boolean): Boolean = {
      if (ar.size < 2) true
      else {
        if (ordered(ar(0), ar(1))) loop(ar.slice(1, ar.size), ordered)
        else false
      }
    }
    loop(as, ordered)

  }
//  scala> isSorted[Int](Array(1, 2, 3, 5), (x:Int, y:Int) => x<y)
//  res77: Boolean = true
//
//  scala> isSorted[Int](Array(1, 2, 6, 5), (x:Int, y:Int) => x<y)
//  res78: Boolean = false
//  scala> isSorted[String](Array("ab","be","fa"), (x:String, y:String) => x<y)
//  res79: Boolean = true
//
//  scala> isSorted[String](Array("wb","be","fa"), (x:String, y:String) => x<y)
//  res81: Boolean = false
}