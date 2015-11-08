package controllers

import play.api.mvc._
import services.BillingService

import scala.concurrent.ExecutionContext.Implicits.global

class Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  def bill1 = Action.async {
    BillingService.getBillFromApi.map {
      bill => Ok(views.html.bill1(bill))
    }
  }

  def bill1jasmine = Action {
    Ok(views.html.bill1jasmine())
  }

}
