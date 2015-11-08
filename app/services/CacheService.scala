package services

import models.Bill
import play.api.cache.Cache
import play.api.Play.current

class CacheService {

  def getBill: Bill = Cache.get("bill").get.asInstanceOf[Bill]

}
