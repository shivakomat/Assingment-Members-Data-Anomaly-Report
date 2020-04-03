package reports


sealed class DeletedMembersReport(outputCSVFileUrl: String) extends Report(outputCSVFileUrl) {

  protected def initialize(): Unit =
    addHeader()

  private def addHeader(): Unit = outputCSVFile.writeHeader(List("id"))

  def addAnalysis(deletedMemberIds: List[Int]): Unit =
    deletedMemberIds.foreach(id => outputCSVFile.write(List(id.toString)))
}

object DeletedMembersReport {
  def apply(outputCSVFileUrl: String): DeletedMembersReport = {
    val report = new DeletedMembersReport(outputCSVFileUrl)
    report.initialize()
    report
  }
}
