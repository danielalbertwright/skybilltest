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
    "Contain the correct heading" in new WithBrowser {
      // When a user navigates to the bill3 page.
      loadPage(browser,port)

      // They should see the correct heading.
      browser.$("h1").first().getText must_== "Your Sky Bill"
    }

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


    "The user should be able to expand sections" in new WithBrowser {
      // When a user navigates to the bill3 page.
      // And clicks on each of the section show links.
      loadPage(browser,port)
      browser.click("#package-toggle")
      browser.click("#call-charges-toggle")
      browser.click("#sky-store-toggle")

      // Then they should be able to see the sections on the page.
      browser.findFirst(".package").isDisplayed must_== true
      browser.findFirst(".call-charges").isDisplayed must_== true
      browser.findFirst(".sky-store").isDisplayed must_== true
    }

  }
}
