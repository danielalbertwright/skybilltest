package integration

import org.specs2.mutable._
import play.api.test.{TestBrowser, WithBrowser}

import scala.util.Try

object Bill2Spec {
  def loadPage(browser : TestBrowser, port: scala.Int) = browser.goTo("http://localhost:" + port + "/bill2")
}

class Bill2Spec extends Specification {
  import integration.Bill1Spec._
  "Bill1" should {
    "Contain the correct heading" in new WithBrowser {
      // When a user navigates to the home page.
      loadPage(browser,port)

      // They should see the correct heading.
      browser.$("h1").first().getText must_== "Your Sky Bill"
    }

    "Contain the expected statement sections" in new WithBrowser {
      // When a user navigates to the home page.
      loadPage(browser,port)

      // Then they should see the statement sections on the page.
      Try(browser.findFirst(".statement")) must beSuccessfulTry
      Try(browser.findFirst(".statementTotal")) must beSuccessfulTry
      Try(browser.findFirst(".package")) must beSuccessfulTry
      Try(browser.findFirst(".callCharges")) must beSuccessfulTry
      Try(browser.findFirst(".skyStore")) must beSuccessfulTry
    }
  }
}
