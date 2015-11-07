import org.specs2.mutable._
import play.api.test.Helpers._
import play.api.test._

class RoutesSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication {
      route(FakeRequest(GET, "/foo")) must beSome.which(status(_) == NOT_FOUND)
    }

    "render the home page" in new WithApplication {
      val home = route(FakeRequest(GET, "/")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain("PageId:index")
    }

    "render the bill1 page" in new WithApplication {
      val home = route(FakeRequest(GET, "/bill1")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain("PageId:bill1")
    }
  }
}
