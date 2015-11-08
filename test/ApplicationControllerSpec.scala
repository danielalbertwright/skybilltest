package views

import play.api.mvc.Results
import play.api.test.{FakeRequest, PlaySpecification}


class ApplicationControllerSpec extends PlaySpecification with Results {

  "The Application Controller" should {
    "serve the index page" in {
      val controller = new controllers.Application
      val result = controller.index().apply(FakeRequest())
      contentAsString(result) must contain("PageId:index")
    }
    "serve the bill1 page" in {
      val controller = new controllers.Application
      val result = controller.bill1().apply(FakeRequest())
      contentAsString(result) must contain("PageId:bill1")
    }
    "serve the bill2 page" in {
      val controller = new controllers.Application
      val result = controller.bill2().apply(FakeRequest())
      contentAsString(result) must contain("PageId:bill2")
    }
  }
}
