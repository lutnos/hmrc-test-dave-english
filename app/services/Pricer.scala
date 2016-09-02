package services

object Pricing {

  def totalPrice(items : String) : BigDecimal =
    totalPrice(items.toLowerCase.split(",").collect {
      case Apple.name => Apple
      case Orange.name => Orange
    }.toSeq)


  def totalPrice(items : Seq[Item]) : BigDecimal = items.map(_.price).sum

}

sealed trait Item {
  val price : BigDecimal
  val name : String
}

case object Apple extends Item {
  val price : BigDecimal = 0.60
  val name = "apple"
}

case object Orange extends Item {
  val price : BigDecimal = 0.25
  val name = "orange"
}

