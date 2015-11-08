package services

import models.Bill
import play.api.Logger
import play.api.Play.current
import play.api.cache.Cache
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class CacheService {

  val api = new Api

  def getBill: Future[Bill] = {
    Logger.debug("Getting bill from cache")
    Cache.getAs[Bill]("bill") match {
      case Some(bill: Bill) =>
        Logger.debug("Cache hit")
        Future.successful(bill)
      case None =>
        Logger.debug("Cache miss")
        api.get("http://safe-plains-5453.herokuapp.com/bill.json").map {
          wsresponse =>
            Cache.set("bill", wsresponse.json.as[Bill], 5 minutes)
            wsresponse.json.as[Bill]
        }
    }
  }

}
