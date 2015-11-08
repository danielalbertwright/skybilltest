package services

import com.typesafe.config.{ConfigFactory, Config}
import models.Bill
import play.api.Logger
import play.api.Play.current
import play.api.cache.Cache
import play.api.libs.json.JsValue

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._

/**
 * An advanced version of the BillingService, this class caches responses from the API for up to 5 minutes.
 */
class CacheService {
  val config: Config = ConfigFactory.load()
  val api = new Api

  /**
   * Check the cache for the bill.
   * Cache hit => Return the value from the cache to the user.
   * Cache miss => Get the bill from the API, update the cache and return the value to the user.
   * @return A Future which resolves to a Json representation of a bill from the Api/Cache.
   */
  def getJson: Future[JsValue] = {
    Logger.debug("Getting json from cache")
    Cache.getAs[JsValue]("bill") match {
      case Some(bill: JsValue) =>
        Logger.debug("Cache hit")
        Future.successful(bill)
      case None =>
        Logger.debug("Cache miss")
        api.get(config.getString("skybaps.api")).map {
          wsresponse =>
            Cache.set("bill", wsresponse.json, 5 minutes)
            wsresponse.json
        }
    }
  }

  /**
   * Converting wrapper for the getJson method.
   * @return Returns a Bill representation of what the getJson method returns.
   */
  def getBill: Future[Bill] = getJson.map(json => json.as[Bill])

}
