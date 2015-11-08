package controllers

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

}
