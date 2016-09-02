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

    "checkout must price one apple" in {
      route(app, FakeRequest(GET, "/price/apple")).map(contentAsString(_)) mustBe Some("0.60")
    }

  }
}

