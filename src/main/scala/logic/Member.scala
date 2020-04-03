package logic

import play.api.libs.json.Json

case class Member(id: Int, first_name: String, last_name: String, status: String,
                  email: String, phone: String, birthDate: String, zip5: String,
                  created_at: String, update_at: String)

object Member {

  def apply(jsonRow: String): Member = {
    rowToMember(jsonRow)
  }

  def rowToMember(row: String): Member = {
    val jsonNode = Json.parse(row)
      Member(id = (jsonNode \ "id").as[String].toInt,
        first_name = (jsonNode \ "first_name").as[String],
        last_name = (jsonNode \ "last_name").as[String],
        status = (jsonNode \ "status").as[String],
        email = (jsonNode \ "email").as[String],
        phone = (jsonNode \ "phone").as[String],
        birthDate =(jsonNode \ "birth_date").as[String],
        zip5 =(jsonNode \ "zip5").as[String],
        created_at = (jsonNode \ "created_at").as[String],
        update_at = (jsonNode \ "updated_at").as[String])
  }

}