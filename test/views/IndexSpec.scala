package views

import org.specs2.mutable._
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}

class IndexSpec extends Specification {

  "The index page" should {
    "Display a heading" in new WithApplication {
      val home = route(FakeRequest(GET, "/")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain("<h1>Sky BAPS Project Submission</h1>")
    }
    "Contain a link to bill1" in new WithApplication {
      val home = route(FakeRequest(GET, "/")).get
      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain("id=\"bill1link\"")
    }
  }
}
