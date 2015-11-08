package services

import play.api.Logger
import play.api.Play.current
import play.api.libs.ws.{WS, WSRequest, WSResponse}

import scala.concurrent.Future

/**
 * Simple service class used to make Api Calls.
 */
class Api {

  /**
   * Asynchronously make an API GET request.
   *
   * @param url The url of the API endpoint.
   * @return A websocket response future.
   */
  def get(url: String): Future[WSResponse] = {
    Logger.debug(s"Get request to API: [$url]")

    val holder: WSRequest = WS.url(url)
      .withHeaders("Accept" -> "application/json")
      .withRequestTimeout(10000)

    holder.get()
  }

}
