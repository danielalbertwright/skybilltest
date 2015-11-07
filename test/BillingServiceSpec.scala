import models.Bill
import org.specs2.mock.Mockito
import play.api.Application
import play.api.libs.ws.WSResponse
import play.api.test.{FakeApplication, PlaySpecification}
import services.{Api, BillingService}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class BillingServiceSpec extends PlaySpecification with Mockito {

  "BillingService" should {
    "return a bill when getBill is called" in {
      running(FakeApplication()) {
        val response = mock[WSResponse]
        response.body returns """{
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
                              }"""
        val api = mock[Api]
        api.get(anyString) returns Future(response)
        val billFuture: Future[Bill] = BillingService.getBill
        val bill = Await.result(billFuture, 1 second)
        bill.total must_== 136.03
      }
    }
  }

}
