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

          override def json: JsValue = Json.parse("""{
                                "statement": {
                                  "generated": "2015-01-11",
                                  "due": "2015-01-25",
                                  "period": {
                                    "from": "2015-01-26",
                                    "to": "2015-02-25"
                                  }
                                },
                                "total": 136.03,
                                "package": {
                                  "subscriptions": [
                                    { "type": "tv", "name": "Variety with Movies HD", "cost": 50.00 },
                                    { "type": "talk", "name": "Sky Talk Anytime", "cost": 5.00 },
                                    { "type": "broadband", "name": "Fibre Unlimited", "cost": 16.40 }
                                  ],
                                  "total": 71.40
                                },
                                "callCharges": {
                                  "calls": [
                                    { "called": "07716393769", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "07716393769", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "07716393769", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "07716393769", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "07716393769", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "07716393769", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "07716393769", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "07716393769", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "07716393769", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "07716393769", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "07716393769", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "07716393769", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "07716393769", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "07716393769", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "07716393769", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "07716393769", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "07716393769", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "07716393769", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "02074351359", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "02074351359", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "02074351359", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "02074351359", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "02074351359", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "02074351359", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "02074351359", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "02074351359", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "02074351359", "duration": "00:23:03", "cost": 2.13 },
                                    { "called": "02074351359", "duration": "00:23:03", "cost": 2.13 }
                                  ],
                                  "total": 59.64
                                },
                                "skyStore": {
                                  "rentals": [
                                    { "title": "50 Shades of Grey", "cost": 4.99 }
                                  ],
                                  "buyAndKeep": [
                                    { "title": "That's what she said", "cost": 9.99 },
                                    { "title": "Broke back mountain", "cost": 9.99 }
                                  ],
                                  "total": 24.97
                                }
                              }""")

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
