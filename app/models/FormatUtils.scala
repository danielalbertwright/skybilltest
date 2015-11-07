package models

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object FormatUtils {

  def formatDateForUser(string: String): String = {
    val fmt = DateTimeFormat.forPattern("yyyy-MM-dd")
    val dateTime = DateTime.parse(string, fmt)
    dateTime.toString("dd/MM/yyyy")
  }

  def formatCostForUser(cost: Double): String = {
    f"$cost%2.2f"
  }

}
