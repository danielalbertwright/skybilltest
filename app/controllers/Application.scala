package controllers

import play.api.mvc._
import services.{CacheService, BillingService}

import scala.concurrent.ExecutionContext.Implicits.global

class Application extends Controller {
  val billingService = new BillingService
  val cacheService = new CacheService

  def index = Action {
    Ok(views.html.index())
  }

  def bill1 = Action.async {
    billingService.getBillFromLocal.map {
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

}
