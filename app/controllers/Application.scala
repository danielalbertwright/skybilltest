package controllers

import play.api.mvc._
import services.BillingService

import scala.concurrent.ExecutionContext.Implicits.global

class Application extends Controller {
  val billingService = new BillingService

  def index = Action {
    Ok(views.html.index())
  }

  def bill1 = Action.async {
    billingService.getBillFromLocal.map {
      bill => Ok(views.html.bill1(bill))
    }
  }

  def bill2 = Action.async {
    billingService.getBillFromLocal.map {
      bill => Ok(views.html.bill2(bill))
    }
  }

}
