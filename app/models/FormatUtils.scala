package models

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object FormatUtils {

  /**
   * Converts a date from the style received in the JSON value to a standard UK date format.
   * @param string String containing a date in the (yyyy-MM-dd) format.
   * @return Date in the dd/MM/yyyy format.
   */
  def formatDateForUser(string: String): String = {
    val fmt = DateTimeFormat.forPattern("yyyy-MM-dd")
    val dateTime = DateTime.parse(string, fmt)
    dateTime.toString("dd/MM/yyyy")
  }

  /**
   * Converts a double to a string with 2 decimal places.
   * @param cost A double representing a
   * @return A string representation of the cost parameter with 2 decimal places.
   */
  def formatCostForUser(cost: Double): String = {
    f"$cost%2.2f"
  }

}
