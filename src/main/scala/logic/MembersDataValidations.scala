package logic

import org.joda.time.format.DateTimeFormat
import org.joda.time.{DateTime, Years}

object MembersDataValidations {

  def validateFor18OrOlder(id: Int, birthDate: String, createdDate: String): Boolean = {
    val createDateFmt = DateTimeFormat.forPattern("yyyy-MM-dd")
    val createdDateParsed = DateTime.parse(createdDate.split('T').head, createDateFmt)
    val fmt = DateTimeFormat.forPattern("MM/dd/yyyy")
    val birthDateParsed = DateTime.parse(birthDate, fmt)
    val age: Years = Years.yearsBetween(birthDateParsed, createdDateParsed)
    !(age.getYears < 18)
  }

  def isValidEmail(id: Int, email: String): Boolean = {
    val emailRegex =
      """^[a-zA-Z0-9\.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$""".r

    email match{
      case null                                           => false
      case e if e.trim.isEmpty                            => false
      case e if emailRegex.findFirstMatchIn(e).isDefined  => true
      case _                                              => false
    }
  }

  def isValidZip5(id: Int, zip5: String): Boolean =
    zip5.matches("\\d{5}")

  def isValidPhoneNumber(id: Int, phoneNumber: String): Boolean =
    phoneNumber.matches("\\d{10}")

}
