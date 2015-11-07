package controllers

import play.api.mvc._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  def bill1 = Action {
    Ok(views.html.bill1())
  }

}
