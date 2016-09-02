package controllers

import javax.inject.{Inject, Singleton}

import play.api.mvc.{Action, Controller}
import services.Pricing._

@Singleton
class Checkout @Inject() extends Controller {

  def price(items: String) = Action {
    val itemsList = items.toLowerCase.split(",").toList
    Ok(totalPrice(items).formatted("%.2f"))

  }

}

