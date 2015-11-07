package controllers

import scala.concurrent.ExecutionContext.Implicits.global
import play.api.mvc._
import services.BillingService

class Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  def bill1 = Action.async {
    BillingService.getBillFromLocal.map{
      bill => Ok(views.html.bill1(bill))
    }
  }
}
