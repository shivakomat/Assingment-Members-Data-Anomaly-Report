package logic

class CalculateForDeletedRecords(var currentId: Int = 0) {

  def incrementCurrentIdBy(increments: Int): Unit =
    currentId = currentId + increments

  def evaluateForDeletedRows(nextId: Int): List[Int] = {
    val expectedNextId = currentId + 1
    if(currentId != 0 && (expectedNextId < nextId)) {
        val missingIds = nextId - currentId
        val ids = (expectedNextId until nextId).map(id => id).toList
        incrementCurrentIdBy(missingIds)
        ids
    }
    else {
      incrementCurrentIdBy(1);
      List.empty[Int]
    }
  }

}