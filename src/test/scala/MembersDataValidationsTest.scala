import collection.mutable.Stack
import org.scalatest._
import logic.MembersDataValidations

class MembersDataValidationsTest extends FlatSpec {

  "validateFor18OrOlder" should "return true if the birth date is older than 18 in conjunction to record create date" in {
    val is18OrOlder =
      MembersDataValidations.validateFor18OrOlder(id = 1, birthDate = "07/12/1999", createdDate = "2020-01-22")
      assert(is18OrOlder)
  }

  it should "equal to false if age below 18 years" in {
    val is18OrOlder =
      MembersDataValidations.validateFor18OrOlder(id = 1, birthDate = "07/12/2017", createdDate = "2020-01-22")
    assert(!is18OrOlder)
  }

  it should "equal to true if age is exactly 18 years" in {
    val is18OrOlder =
      MembersDataValidations.validateFor18OrOlder(id = 1, birthDate = "01/22/2002", createdDate = "2020-01-22")
    assert(is18OrOlder)
  }


  "isValidEmail" should "equal to true if there is a valid email used in the member's info" in {
    val email = "shiva.uoit@live.ca"
    val isValidEmail =MembersDataValidations.isValidEmail(1, email)
    assert(isValidEmail)
  }

  it should "equal to false if there is an invalid email used" in {
    val email = "123. gmail.com"
    val isValidEmail =MembersDataValidations.isValidEmail(1, email)
    assert(!isValidEmail)
  }

  it should "equal to false if there is space present in the email" in {
    val email = "shiva@ gmail.com"
    val isValidEmail =MembersDataValidations.isValidEmail(1, email)
    assert(!isValidEmail)
  }

  "isValidZip" should "equal to true if there is a valid zip with 5 digit format used in the member's info" in {
    val zip5 = "94103"
    val isValidZip = MembersDataValidations.isValidZip5(1, zip5)
    assert(isValidZip)
  }

  it should "equal to false if there is alphabets in the zip" in {
    val zip5 = "L7A3P6"
    val isValidZip = MembersDataValidations.isValidZip5(1, zip5)
    assert(!isValidZip)
  }

  it should "equal to false if there is more than 5 digits in the zip" in {
    val zip5 = "9458812"
    val isValidZip = MembersDataValidations.isValidZip5(1, zip5)
    assert(!isValidZip)
  }

  "isValidPhoneNumber" should "equal to true if there is valid if there is a 10 digit North American phone number" in {
    val phoneNumber = "4158890586"
    val isValidPhone = MembersDataValidations.isValidPhoneNumber(1, phoneNumber)
    assert(isValidPhone)
  }

  it should "equal to false if there is more digits in the phone number" in {
    val phoneNumber = "415889058612"
    val isValidPhone = MembersDataValidations.isValidPhoneNumber(1, phoneNumber)
    assert(!isValidPhone)
  }

  it should "equal to false if there are any alpha and special characters in the phone number" in {
    val phoneNumber = "415-889-0586"
    val isValidPhone = MembersDataValidations.isValidPhoneNumber(1, phoneNumber)
    assert(!isValidPhone)
  }

}