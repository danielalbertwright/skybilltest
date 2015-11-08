package integration

import org.specs2.mutable._
import play.api.test.{TestBrowser, WithBrowser}

import scala.util.Try

object Bill2Spec {
  def loadPage(browser : TestBrowser, port: scala.Int) = browser.goTo("http://localhost:" + port + "/bill2")
}

class Bill2Spec extends Specification {
  import integration.Bill2Spec._
  "Bill2" should {
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
      Try(browser.findFirst(".statement-total")) must beSuccessfulTry
      Try(browser.findFirst(".package")) must beSuccessfulTry
      Try(browser.findFirst(".call-charges")) must beSuccessfulTry
      Try(browser.findFirst(".sky-store")) must beSuccessfulTry
    }

    "The sections should be collapsed" in new WithBrowser {
      // When a user navigates to the home page.
      loadPage(browser,port)

      // Then they shouldn't be able to see the details of the sections.
      browser.findFirst(".package").isDisplayed must_== false
      browser.findFirst(".call-charges").isDisplayed must_== false
      browser.findFirst(".sky-store").isDisplayed must_== false
    }


  }
}
