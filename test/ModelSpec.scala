import models.Bill
import org.specs2.mutable.Specification
import play.api.libs.json.Json

class ModelSpec extends Specification {
  "Billing Models" should {
    "Correctly parse the test JSON I was provided for this project." in {
      val bill = Json.parse(TestModels.sampleJsonString).as[Bill]
      bill.statement.generated must_== "2015-01-11"
      bill.statement.due must_== "2015-01-25"
      bill.statement.period.from must_== "2015-01-26"
      bill.statement.period.to must_== "2015-02-25"
      bill.total must_== 136.03
      bill.`package`.subscriptions.head.`type` must_== "tv"
      bill.`package`.subscriptions.head.name must_== "Variety with Movies HD"
      bill.`package`.subscriptions.head.cost must_== 50.00
      bill.`package`.subscriptions.reverse.head.`type` must_== "broadband"
      bill.`package`.subscriptions.reverse.head.name must_== "Fibre Unlimited"
      bill.`package`.subscriptions.reverse.head.cost must_== 16.40
      bill.`package`.total must_== 71.40
      bill.callCharges.calls.head.called must_== "07716393769"
      bill.callCharges.calls.head.duration must_== "00:23:03"
      bill.callCharges.calls.head.cost must_== 2.13
      bill.callCharges.calls.reverse.head.called must_== "02074351359"
      bill.callCharges.calls.reverse.head.duration must_== "00:23:03"
      bill.callCharges.calls.reverse.head.cost must_== 2.13
      bill.callCharges.total must_== 59.64
      bill.skyStore.rentals.head.title must_== "50 Shades of Grey"
      bill.skyStore.rentals.head.cost must_== 4.99
      bill.skyStore.buyAndKeep.head.title must_== "That's what she said"
      bill.skyStore.buyAndKeep.head.cost must_== 9.99
      bill.skyStore.buyAndKeep.reverse.head.title must_== "Broke back mountain"
      bill.skyStore.buyAndKeep.reverse.head.cost must_== 9.99
      bill.skyStore.total must_== 24.97
    }
  }
}
