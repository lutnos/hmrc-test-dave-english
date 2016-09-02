import org.scalatestplus.play._
import play.api.libs.ws.WSClient
import play.api.test.Helpers._
import scala.concurrent.Await
import scala.concurrent.duration._

class IntegrationSpec extends PlaySpec with OneServerPerTest {

  "Application" should {

    "be able to process a checkout list" in {
      val wsClient = app.injector.instanceOf[WSClient]
      val result = Await.result(wsClient.url(s"http://localhost:$port/price/Apple").get(), 5.seconds)

      result.status mustBe OK
    }
  }
}

