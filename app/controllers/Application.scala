package controllers

import play.api.libs.json.Json
import play.api.mvc._
import services.{BillingService, CacheService}

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Application controller.
 * Due to the small size of this project I decided not to break this apart.
 */
class Application extends Controller {
  val billingService = new BillingService
  val cacheService = new CacheService

  def index = Action {
    Ok(views.html.index())
  }

  def bill1 = Action.async {
    billingService.getBillFromApi.map {
      bill => Ok(views.html.bill1(bill))
    }
  }

  def bill2 = Action.async {
    cacheService.getBill.map {
      bill => Ok(views.html.bill2(bill))
    }
  }

  def bill3 = Action {
    Ok(views.html.bill3())
  }

  def billAsJson = Action.async {
    cacheService.getJson.map {
      bill => Ok(bill)
    }
  }

  def jstests = Action {
    Ok(views.html.jstests())
  }

  def testJson = Action {
    Ok(Json.parse( """{
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
                       }"""))
  }

}
