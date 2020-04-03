package reports

import fileinterfaces.FileHandler
import logic.{CalculateForDeletedRecords, Member}
import logic.MembersDataValidations._

class MembersAnalysisReportsGenerator(membersSrcFileUrl: String, memberInvalidReportOutputFileUrl: String,
                                      deletedRecordsReportOutputFileUrl: String) {

  def run(): Unit = {

    val memberValidationsReport = InvalidMembersReport(memberInvalidReportOutputFileUrl)
    val deletedRecordsReport = DeletedMembersReport(deletedRecordsReportOutputFileUrl)
    val membersDataFile =  FileHandler(membersSrcFileUrl)

    val deletedRecordsCalculator = new CalculateForDeletedRecords()
    val dataStream = membersDataFile.getStream


    def executeStream(): Unit = {
      dataStream
        .foreach(row => {
          val member = Member(row)
          val deletedRecords = deletedRecordsCalculator.evaluateForDeletedRows(member.id)
          if (deletedRecords.nonEmpty) deletedRecordsReport.addAnalysis(deletedRecords)
          val validations = dataQualityValidationsByMember(member)
          if (validations.nonEmpty) memberValidationsReport.addAnalysis(member, validations)
        })
    }

    def closeAllFilesHandlers(): Unit = {
      deletedRecordsReport.outputCSVFile.close
      memberValidationsReport.outputCSVFile.close
      membersDataFile.closeHandler()
    }

    try {
      executeStream()
    }
    catch {
      case e: Throwable => println(e.printStackTrace())
    } finally {
      closeAllFilesHandlers()
    }

  }


  private def dataQualityValidationsByMember(member: Member): Map[String, String] = {
    val age18 = validateFor18OrOlder(member.id, member.birthDate, member.created_at)
    val emailCheck = isValidEmail(member.id, member.email)
    val zip5Check = isValidZip5(member.id, member.zip5)
    val phoneNumberCheck = isValidPhoneNumber(member.id, member.phone)
    val results = List(age18, emailCheck, zip5Check, phoneNumberCheck)

    if (results.contains(false))
      Map("underAge" -> validateFor18OrOlder(member.id, member.birthDate, member.created_at).toString,
          "validEmail" -> isValidEmail(member.id, member.email).toString,
          "validZip5" -> isValidZip5(member.id, member.zip5).toString,
          "validPhoneNumber" -> isValidPhoneNumber(member.id, member.phone).toString)
    else
      Map.empty[String, String]
  }

}

object MembersAnalysisReportsGenerator {

  def apply(membersSrcFileUrl: String, memberInvalidReportOutputFileUrl: String, deletedRecordsReportOutputFileUrl: String): MembersAnalysisReportsGenerator =
    new MembersAnalysisReportsGenerator(membersSrcFileUrl, memberInvalidReportOutputFileUrl, deletedRecordsReportOutputFileUrl)
}