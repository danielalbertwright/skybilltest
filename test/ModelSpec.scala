import models.Bill
import org.specs2.mutable.Specification
import play.api.libs.json.Json

class ModelSpec extends Specification {
  "Billing Models" should {
    "Correctly parse the test JSON I was provided for this project." in {
      val testJson = """{
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
      val json = Json.parse(testJson).as[Bill]
      json.statement.generated must_== "2015-01-11"
      json.statement.due must_== "2015-01-25"
      json.statement.period.from must_== "2015-01-26"
      json.statement.period.to must_== "2015-02-25"
      json.total must_== 136.03
      json.`package`.subscriptions.head.`type` must_== "tv"
      json.`package`.subscriptions.head.name must_== "Variety with Movies HD"
      json.`package`.subscriptions.head.cost must_== 50.00
      json.`package`.subscriptions.reverse.head.`type` must_== "broadband"
      json.`package`.subscriptions.reverse.head.name must_== "Fibre Unlimited"
      json.`package`.subscriptions.reverse.head.cost must_== 16.40
      json.`package`.total must_== 71.40
      json.callCharges.calls.head.called must_== "07716393769"
      json.callCharges.calls.head.duration must_== "00:23:03"
      json.callCharges.calls.head.cost must_== 2.13
      json.callCharges.calls.reverse.head.called must_== "02074351359"
      json.callCharges.calls.reverse.head.duration must_== "00:23:03"
      json.callCharges.calls.reverse.head.cost must_== 2.13
      json.callCharges.total must_== 59.64
      json.skyStore.rentals.head.title must_== "50 Shades of Grey"
      json.skyStore.rentals.head.cost must_== 4.99
      json.skyStore.buyAndKeep.head.title must_== "That's what she said"
      json.skyStore.buyAndKeep.head.cost must_== 9.99
      json.skyStore.buyAndKeep.reverse.head.title must_== "Broke back mountain"
      json.skyStore.buyAndKeep.reverse.head.cost must_== 9.99
      json.skyStore.total must_== 24.97
    }
  }
}
