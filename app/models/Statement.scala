package models

import play.api.libs.json.Json

// http://json2caseclass.cleverapps.io/ is very cool!

case class Period(
                   from: String,
                   to: String
                   )

case class Statement(
                      generated: String,
                      due: String,
                      period: Period
                      )

case class Subscription(

                          `type`: String,
                          name: String,
                          cost: Double
                          )

case class Package(
                    subscriptions: List[Subscription],
                    total: Double
                    )

case class Call(
                  called: String,
                  duration: String,
                  cost: Double
                  )

case class CallCharges(
                        calls: List[Call],
                        total: Double
                        )

case class Rental(
                    title: String,
                    cost: Double
                    )

case class BuyAndKeep(
                    title: String,
                    cost: Double
                    )

case class SkyStore(
                     rentals: List[Rental],
                     buyAndKeep: List[BuyAndKeep],
                     total: Double
                     )

case class Bill(
                 statement: Statement,
                 total: Double,
                 `package`: Package,
                 callCharges: CallCharges,
                 skyStore: SkyStore
                 )

object BuyAndKeep {implicit val formatter = Json.format[BuyAndKeep]}
object Rental {implicit val formatter = Json.format[Rental]}
object Call {implicit val formatter = Json.format[Call]}
object Subscription {implicit val formatter = Json.format[Subscription]}
object Period {implicit val formatter = Json.format[Period]}
object SkyStore {implicit val formatter = Json.format[SkyStore]}
object CallCharges {implicit val formatter = Json.format[CallCharges]}
object Package {implicit val formatter = Json.format[Package]}
object Statement {implicit val formatter = Json.format[Statement]}
object Bill {implicit val formatter = Json.format[Bill]}