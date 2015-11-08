package services

import play.api.Logger
import play.api.Play.current
import play.api.libs.ws.{WS, WSRequest, WSResponse}

import scala.concurrent.Future


class Api {

  def get(url: String): Future[WSResponse] = {
    Logger.debug(s"Get request to API: [$url]")

    val holder: WSRequest = WS.url(url)
      .withHeaders("Accept" -> "application/json")
      .withRequestTimeout(10000)

    holder.get()
  }

}
