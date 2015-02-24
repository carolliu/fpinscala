object MyModule {
  def abs(n: Int): Int =
    if (n < 0) -n
    else n

  private def formatAbs(x: Int) = {
    val msg = "The absolute value of %d is %d"
    msg.format(x, abs(x))
  }

  def main(args: Array[String]): Unit =
    println(formatAbs(-42))

  def fib(n: Int): Int = {
    @annotation.tailrec
    def loop(list: List[Int], current: Int): List[Int] = current match {
      case _ if current > n => list
      case 0 | 1 => loop(current :: list, current+1)
      case _ => loop(list.take(2).foldLeft(0) { (m: Int, n: Int) => m + n} :: list, current+1)
    }

    loop(List(), 0).head
  }

  def fib2(n: Int): Int = {
    @annotation.tailrec
    def loop(current: Int, prev: Int, sum: Int): Int = current match {
      case 0 => loop(1, 0, 1)
      case _ if (current > n) => prev
      case _ => loop(current+1, sum, prev+sum)
    }

    loop(0, 0, 1)
  }

  def findFirst(ss: Array[String], key: String): Int = {
    def loop(i: Int): Int = {
      if (i >= ss.length) -1
      else if (ss(i) == key) i
      else loop(i+1)
    }
    loop(0)
  }

  def findFirst2[T](ss: Array[T], f:T => Boolean): Int = {
    def loop(i: Int): Int = {
      if (i >= ss.length) -1
      else if (f(ss(i))) i
      else loop(i+1)
    }
    loop(0)
  }
//  scala> findFirst2[Int](Array(100, 2, 3, 5), (x: Int) => x == 9)
//  res71: Int = -1
//
//  scala> findFirst2[Int](Array(100, 2, 3, 5), (x: Int) => x == 5)
//  res72: Int = 3
}