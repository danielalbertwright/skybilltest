import models.Bill
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import play.api.cache.Cache
import play.api.libs.json.Json
import play.api.test.{WithApplication, PlaySpecification}
import services.CacheService

class CacheServiceSpec extends PlaySpecification with Mockito {

  "CacheService" should {
    "When the cache contains the bill, it should return a bill." in new WithApplication {
      val billCacheService = new CacheService

      val bill = Json.parse("""{
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
                              }""").as[Bill]

      Cache.set("bill", bill)
      billCacheService.getBill must_== bill
    }

    "When the cache doesn't contain a bill, it should get the bill from the API, update the cache and return a bill." in {
      success
    }
  }
}
