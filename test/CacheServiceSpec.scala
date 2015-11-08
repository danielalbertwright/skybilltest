import models.Bill
import org.specs2.mock.Mockito
import play.api.cache.Cache
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.{WSCookie, WSResponse}
import play.api.test.{PlaySpecification, WithApplication}
import services.{Api, CacheService}
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.xml.Elem
import scala.concurrent.ExecutionContext.Implicits.global

class CacheServiceSpec extends PlaySpecification with Mockito {

  val testJson = TestModels.sampleJson

  "CacheService" should {
    "When the cache contains the bill, it should return a bill." in new WithApplication {

      val bill = testJson.as[Bill]

      Cache.set("bill", bill)

      val mockApi = mock[Api]
      class TestCacheService extends CacheService {
        override val api = mockApi
      }

      val billCacheService = new TestCacheService

      val billResponse: Bill = Await.result(billCacheService.getBill, 1 second)
      billResponse must_== testJson.as[Bill]
      there was no(mockApi).get(anyString)
    }

    "When the cache doesn't contain a bill, it should get the bill from the API, update the cache and return a bill." in new WithApplication {
      Cache.remove("bill")
      val response = new WSResponse {
        override def statusText: String = ???

        override def underlying[T]: T = ???

        override def xml: Elem = ???

        override def body: String = ???

        override def header(key: String): Option[String] = ???

        override def cookie(name: String): Option[WSCookie] = ???

        override def bodyAsBytes: Array[Byte] = ???

        override def cookies: Seq[WSCookie] = ???

        override def status: Int = ???

        override def json: JsValue = testJson

        override def allHeaders: Map[String, Seq[String]] = ???
      }

      val mockApi = mock[Api]
      mockApi.get(anyString) returns Future(response)

      class TestCacheService extends CacheService {
        override val api = mockApi
      }

      val testCacheService = new TestCacheService

      val billResponse: Bill = Await.result(testCacheService.getBill, 1 second)
      billResponse must_== testJson.as[Bill]
      there was one(mockApi).get(anyString)
    }
  }
}
