import models._
import play.api.libs.json.{Json, JsValue}


object TestModels {

  val sampleBill: Bill = Bill(
    Statement("2015-01-11", "2015-01-25",
      Period("2015-01-26", "2015-02-25")
    ),
    136.03,
    Package(
      List(
        Subscription("tv", "Variety with Movies HD", 50.0),
        Subscription("talk", "Sky Talk Anytime", 5.0),
        Subscription("broadband", "Fibre Unlimited", 16.4)
      ), 71.4),
    CallCharges(
      List(
        Call("07716393769", "00:23:03", 2.13),
        Call("07716393769", "00:23:03", 2.13),
        Call("07716393769", "00:23:03", 2.13),
        Call("07716393769", "00:23:03", 2.13),
        Call("07716393769", "00:23:03", 2.13),
        Call("07716393769", "00:23:03", 2.13),
        Call("07716393769", "00:23:03", 2.13),
        Call("07716393769", "00:23:03", 2.13),
        Call("07716393769", "00:23:03", 2.13),
        Call("07716393769", "00:23:03", 2.13),
        Call("07716393769", "00:23:03", 2.13),
        Call("07716393769", "00:23:03", 2.13),
        Call("07716393769", "00:23:03", 2.13),
        Call("07716393769", "00:23:03", 2.13),
        Call("07716393769", "00:23:03", 2.13),
        Call("07716393769", "00:23:03", 2.13),
        Call("07716393769", "00:23:03", 2.13),
        Call("07716393769", "00:23:03", 2.13),
        Call("02074351359", "00:23:03", 2.13),
        Call("02074351359", "00:23:03", 2.13),
        Call("02074351359", "00:23:03", 2.13),
        Call("02074351359", "00:23:03", 2.13),
        Call("02074351359", "00:23:03", 2.13),
        Call("02074351359", "00:23:03", 2.13),
        Call("02074351359", "00:23:03", 2.13),
        Call("02074351359", "00:23:03", 2.13),
        Call("02074351359", "00:23:03", 2.13),
        Call("02074351359", "00:23:03", 2.13)),
      59.64),
    SkyStore(
      List(
        Rental("50 Shades of Grey", 4.99)
      ),
      List(
        BuyAndKeep("That's what she said", 9.99),
        BuyAndKeep("Broke back mountain", 9.99)
      ),
      24.97)
  )

  val sampleJsonString: String = """{
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

val sampleJson : JsValue = Json.parse(sampleJsonString)

}
