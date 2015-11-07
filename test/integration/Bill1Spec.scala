package integration

import org.specs2.mutable._
import play.api.test.{TestBrowser, WithBrowser}

object Bill1Spec {
  def loadPage(browser : TestBrowser, port: scala.Int) = browser.goTo("http://localhost:" + port + "/bill1")
}

class Bill1Spec extends Specification {
  import integration.Bill1Spec._
  "Bill1" should {
    "Contain the correct heading" in new WithBrowser {
      // When a user navigates to the home page.
      loadPage(browser,port)

      // They should see the correct heading.
      browser.$("h1").first().getText must_== "Your Sky Bill"
    }



  }
}
