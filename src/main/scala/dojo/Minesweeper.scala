package dojo

object Minesweeper {

  case class Field(rows: Int, cols: Int, bombs: Set[(Int,Int)])

  def parse(in: List[String]): Field = {
    val a = in.head.split(" ")
    Field(a(0).toInt,a(1).toInt,buildSet(in.tail))
  }

  def buildSet(in:List[String]) :Set[(Int,Int)]= (for {
    (line, rowIndex:Int ) <- in.zipWithIndex
    (char, columnIndex:Int) <- line.zipWithIndex
    if(char == '*')
   } yield (rowIndex, columnIndex)).toSet

  def evaluate(in:List[String]) :List[String] = List(
    "1*",
    "22",
    "*1"
  )

  def neighbours(row: Int, col: Int) : Seq[(Int,Int)] = {
    for {
      x <- row - 1 to row + 1
      y <- col -1 to col+1
    } yield {
      Seq.empty
    }
    Seq((-1, -1), (-1, 0), (-1, 1), (0, -1), (0 ,1), (1, -1), (1, 0), (1, 1))
  }
}