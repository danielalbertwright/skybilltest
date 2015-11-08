package services

import com.typesafe.config.{ConfigFactory, Config}
import models._
import play.Logger
import play.api.libs.json.Json

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * BillingService provides an interface to the Api class.
 */
class BillingService {
  val config: Config = ConfigFactory.load()
  val api = new Api

  /**
   * Get a bill from the api.
   * @return A Future which will resolve to a Bill served by the API.
   */
  def getBillFromApi: Future[Bill] = {
    Logger.debug("Getting bill from API")
    api.get(config.getString("skybaps.api")).map {
      wsresponse => wsresponse.json.as[Bill]
    }
  }

  /**
   * Get a bill.
   * To be used in testing.
   * @return A Future which will resolve to a Bill parsed from the string below.
   */
  def getBillFromLocal: Future[Bill] = Future {
    Logger.debug("Getting test bill from local")
    Json.parse("""{
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
  }

}
