package reports

import fileinterfaces.CSVFileWriter


class Report(outputCSVFileUrl: String) {
  val outputCSVFile = CSVFileWriter(outputCSVFileUrl)
}
