package reports

import logic.Member

sealed class InvalidMembersReport(outputCSVFileUrl: String) extends Report(outputCSVFileUrl) {

  protected def initialize(): Unit = {
    addHeader()
  }

  private def addHeader(): Unit = {
    outputCSVFile.writeHeader(List("id", "first_name", "last_name", "email", "birth_date", "phone_number", "status",
      "created_date", "modified_date", "underAge", "validEmail", "validZip5", "validPhoneNumber")
    )
  }

  def addAnalysis(member: Member, validations: Map[String, String]): Unit = {
    outputCSVFile.write(
    List(member.id.toString, member.first_name, member.last_name, member.email, member.birthDate,
        member.phone.toString, member.status, member.created_at, member.update_at, validations("underAge"),
        validations("validEmail"), validations("validZip5"), validations("validPhoneNumber"))
    )
  }

}


object InvalidMembersReport {

  def apply(outputCSVFileUrl: String): InvalidMembersReport = {
    val report = new InvalidMembersReport(outputCSVFileUrl)
    report.initialize()
    report
  }
}
