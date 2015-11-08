import models.Bill
import org.specs2.mock.Mockito
import play.api.Application
import play.api.libs.json.{Json, JsValue}
import play.api.libs.ws.{WSCookie, WSResponse}
import play.api.test.{FakeApplication, PlaySpecification}
import services.{Api, BillingService}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.xml.Elem

class BillingServiceSpec extends PlaySpecification with Mockito {

  "BillingService" should {
    "return a bill when getBill is called" in {
      running(FakeApplication()) {
        val response = new WSResponse {override def statusText: String = ???

          override def underlying[T]: T = ???

          override def xml: Elem = ???

          override def body: String = ???

          override def header(key: String): Option[String] = ???

          override def cookie(name: String): Option[WSCookie] = ???

          override def bodyAsBytes: Array[Byte] = ???

          override def cookies: Seq[WSCookie] = ???

          override def status: Int = ???

          override def json: JsValue = TestModels.sampleJson

          override def allHeaders: Map[String, Seq[String]] = ???
        }
        val mockApi = mock[Api]
        mockApi.get(anyString) returns Future(response)

        class TestBillingService extends BillingService {
          override val api = mockApi
        }

        val billingService = new TestBillingService
        val billFuture: Future[Bill] = billingService.getBillFromApi
        val bill = Await.result(billFuture, 1 second)
        bill.total must_== 136.03
      }
    }
  }

}
