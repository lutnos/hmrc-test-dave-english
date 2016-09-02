import org.scalatestplus.play._
import play.api.test.Helpers._
import play.api.test._


class ApplicationSpec extends PlaySpec with OneAppPerTest {

  "Application" should {

    "send 404 on a bad request" in  {
      route(app, FakeRequest(GET, "/haha")).map(status(_)) mustBe Some(NOT_FOUND)
    }

  }
}

