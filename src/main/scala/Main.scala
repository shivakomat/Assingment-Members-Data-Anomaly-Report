import reports.MembersAnalysisReportsGenerator

object Main extends App {
    val parser = new scopt.OptionParser[Config]("Members Report Analysis APP") {
      head("---------------Members Report App------------------------------")
      opt[String]('m', "membersDataset") required() action { (x, c) =>
        c.copy(membersDataSetFileUrl = x) } text("members data set input path")
      opt[String]('i', "invalidMembersOutput") required() action { (x, c) =>
        c.copy(invalidMembersOutputFilUrl = x) } text("invalid members output path")
      opt[String]('d', "deletedRecordsOutput") required() action { (x, c) =>
        c.copy(deletedMembersOutputFile = x) } text("deleted records output path")
    }

  parser.parse(args, Config()) map { config =>
      println("APP STARTED --------------Processing...---------------------")

      val startTime = System.currentTimeMillis

      MembersAnalysisReportsGenerator(
        membersSrcFileUrl = config.membersDataSetFileUrl,
        memberInvalidReportOutputFileUrl = config.invalidMembersOutputFilUrl,
        deletedRecordsReportOutputFileUrl = config.deletedMembersOutputFile)
      .run()

      println("End of Process")
      println("Output Files : ")
      println("  Invalid Records File : " + config.invalidMembersOutputFilUrl)
      println("  Deleted Records File : " + config.deletedMembersOutputFile)
      println("Completed")

      val endTime = System.currentTimeMillis
      println("End Time: " + endTime)
      val duration = endTime - startTime //Total execution time in nano seconds

      println("Total time it took to complete process. -->  " + duration + " ms" + " or " +  (duration / 1000) + " seconds")
  }

}
