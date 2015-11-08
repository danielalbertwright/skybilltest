package services

import models.Bill
import play.api.Logger
import play.api.Play.current
import play.api.cache.Cache
import play.api.libs.json.JsValue

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._

class CacheService {

  val api = new Api

  def getJson: Future[JsValue] = {
    Logger.debug("Getting json from cache")
    Cache.getAs[JsValue]("bill") match {
      case Some(bill: JsValue) =>
        Logger.debug("Cache hit")
        Future.successful(bill)
      case None =>
        Logger.debug("Cache miss")
        api.get("http://safe-plains-5453.herokuapp.com/bill.json").map {
          wsresponse =>
            Cache.set("bill", wsresponse.json, 5 minutes)
            wsresponse.json
        }
    }
  }

  def getBill: Future[Bill] = getJson.map(json => json.as[Bill])

}
