import models.FormatUtils
import org.specs2.mutable.Specification

class FormatUtilsSpec extends Specification {

  "FormatUnits" should {
    "Correctly format dates" in {
      FormatUtils.formatDateForUser("2015-01-01") must_== "01/01/2015"
    }
    "Correctly format British currency" in {
      FormatUtils.formatCostForUser(1.0) must_== "1.00"
      FormatUtils.formatCostForUser(0.0) must_== "0.00"
      FormatUtils.formatCostForUser(0.99) must_== "0.99"
      FormatUtils.formatCostForUser(123456.0) must_== "123456.00"
    }
  }
}
