package integration

import org.specs2.mutable._
import play.api.test.{TestBrowser, WithBrowser}

object HomepageSpec {
  def loadPage(browser : TestBrowser, port: scala.Int) = browser.goTo("http://localhost:" + port)
}

class HomepageSpec extends Specification {
  import integration.HomepageSpec._
  "Homepage" should {
    "Contain the correct heading" in new WithBrowser {
      // When a user navigates to the home page.
      loadPage(browser,port)

      // They should see the correct heading.
      browser.$("h1").first().getText must_== "Sky BAPS Project Submission"
    }

    "A user should be able to get to the bill1 page" in new WithBrowser {
      // When a user navigates to the home page.
      loadPage(browser,port)

      // And then click on the bill1 button.
      browser.click("#bill1link")

      // Then they should be on the bill1 page.
      browser.pageSource must contain("PageId:bill1")
    }
  }
}
