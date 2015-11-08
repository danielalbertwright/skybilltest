package integration

import org.specs2.mutable._
import play.api.test.{TestBrowser, WithBrowser}

import scala.util.Try

object Bill3Spec {
  def loadPage(browser : TestBrowser, port: scala.Int) = browser.goTo("http://localhost:" + port + "/bill3")
}

class Bill3Spec extends Specification {
  import integration.Bill3Spec._
  "Bill3" should {
    "Contain the expected statement sections" in new WithBrowser {
      // When a user navigates to the bill3 page.
      loadPage(browser,port)

      // Then they should see the statement sections on the page.
      Try(browser.findFirst(".statement")) must beSuccessfulTry
      Try(browser.findFirst(".statement-total")) must beSuccessfulTry
      Try(browser.findFirst(".package")) must beSuccessfulTry
      Try(browser.findFirst(".call-charges")) must beSuccessfulTry
      Try(browser.findFirst(".sky-store")) must beSuccessfulTry
    }

    "The sections should be collapsed" in new WithBrowser {
      // When a user navigates to the bill3 page.
      loadPage(browser,port)

      // Then they shouldn't be able to see the details of the sections.
      browser.findFirst(".package").isDisplayed must_== false
      browser.findFirst(".call-charges").isDisplayed must_== false
      browser.findFirst(".sky-store").isDisplayed must_== false
    }
  }
}
