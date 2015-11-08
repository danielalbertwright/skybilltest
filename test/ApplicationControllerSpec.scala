import org.specs2.mock.Mockito
import play.api.libs.json.JsValue
import play.api.mvc.Results
import play.api.test.{FakeApplication, FakeRequest, PlaySpecification}
import services.CacheService
import scala.concurrent.Future

class ApplicationControllerSpec extends PlaySpecification with Results with Mockito {

  "The Application Controller" should {
    "serve the index page" in {
      running(FakeApplication()) {
        val controller = new controllers.Application
        val result = controller.index().apply(FakeRequest())
        contentAsString(result) must contain("PageId:index")
      }
    }
    "serve the bill1 page" in {
      running(FakeApplication()) {
        val controller = new controllers.Application
        val result = controller.bill1().apply(FakeRequest())
        contentAsString(result) must contain("PageId:bill1")
      }
    }
    "serve the bill2 page" in {
      running(FakeApplication()) {
        val controller = new controllers.Application
        val result = controller.bill2().apply(FakeRequest())
        contentAsString(result) must contain("PageId:bill2")
      }
    }
    "serve the bill3 page" in {
      running(FakeApplication()) {
        val controller = new controllers.Application
        val result = controller.bill3().apply(FakeRequest())
        contentAsString(result) must contain("PageId:bill3")
      }
    }

    "serve bills as json" in {
      running(FakeApplication()) {
        val mockCacheService = mock[CacheService]
        mockCacheService.getJson returns Future.successful(TestModels.sampleJson)
        class testController extends controllers.Application {
          override val cacheService = mockCacheService
        }

        val controller = new testController
        val result = controller.billAsJson().apply(FakeRequest())
        val json : JsValue = contentAsJson(result)
        (json \ "total").as[Double] must_== 136.03
      }
    }

  }
}
