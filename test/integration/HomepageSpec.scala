package integration

import java.util.concurrent.TimeUnit
import org.specs2.mutable._
import play.api.test.WithBrowser

class HomepageSpec extends Specification {

  "Homepage" should {

    "A user should be able to get to the bill1 page" in new WithBrowser {
      // When a user navigates to the home page.
      browser.goTo("http://localhost:" + port)

      // And then click on the bill1 button.
      browser.click("#bill1link")

      // Then they should be on the bill1 page.
      browser.pageSource must contain("PageId:bill1")
    }
  }
}
