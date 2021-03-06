import org.scalatestplus.play._
import play.api.test.Helpers._
import play.api.test._

import services.Pricing._


class ApplicationSpec extends PlaySpec with OneAppPerTest {

  "Application" should {

    "send 404 on a bad request" in  {
      route(app, FakeRequest(GET, "/haha")).map(status(_)) mustBe Some(NOT_FOUND)
    }

    "service price one apple" in {
      totalPrice("Apple") mustBe 0.60
    }

    "checkout must price one apple correct pence format" in {
      route(app, FakeRequest(GET, "/price/apple")).map(contentAsString(_)) mustBe Some("0.60")
    }

    "checkout must price two apples for the price of one" in {
      route(app, FakeRequest(GET, "/price/apple,apple,apple")).map(contentAsString(_)) mustBe Some("1.20")
    }

    "checkout must price one orange" in {
      route(app, FakeRequest(GET, "/price/orange")).map(contentAsString(_)) mustBe Some("0.25")
    }

    "checkout must price three oranges for the price of two" in {
      route(app, FakeRequest(GET, "/price/orange,orange,orange,orange,orange")).map(contentAsString(_)) mustBe Some("1.00")
    }

    "checkout must price specific example" in {
      route(app, FakeRequest(GET, "/price/Apple,Apple,Orange,Apple")).map(contentAsString(_)) mustBe Some("1.45")
    }

  }
}