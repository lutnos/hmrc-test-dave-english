package services

object Pricing {

  def totalPrice(items : String) : BigDecimal =
    totalPrice(items.toLowerCase.split(",").collect {
      case Apple.name => Apple
      case Orange.name => Orange
    }.toSeq)


  def totalPrice(items : Seq[Item]) : BigDecimal = {
    val uniqueItems = items.toSet
    val countedItems = uniqueItems.map( i => (i, items.count( _ == i))).toSeq
    val prices = countedItems.map( t => t._1.price(t._2))
    prices.sum
  }

}

sealed trait Item {
  val unitPrice : BigDecimal
  val name : String
  def price(count : Int) : BigDecimal
  def nForThePriceOfm(n : Int, m : Int)(count : Int) : BigDecimal = unitPrice * (count / n * m + count % n)
  def buyOneGetOneFree = nForThePriceOfm(2, 1) _
  def threeForThePriceOfTwo = nForThePriceOfm(3, 2) _
}

case object Apple extends Item {
  val unitPrice : BigDecimal = 0.60
  val name = "apple"
  def price(count : Int) : BigDecimal = buyOneGetOneFree(count)
}

case object Orange extends Item {
  val unitPrice : BigDecimal = 0.25
  val name = "orange"
  def price(count : Int) : BigDecimal = threeForThePriceOfTwo(count)
}

