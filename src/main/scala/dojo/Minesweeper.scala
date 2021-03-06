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

  def evaluate(in:List[String]) :List[String] = {
    val field = parse(in)
    (0 until field.rows).map(row => createLine(field, row)).toList
  }

  def neighbours(row: Int, col: Int) : Seq[(Int,Int)] = {
    for {
      rowIndex <- row-1 to row+1
      colIndex <- col-1 to col+1
      if(rowIndex != row || colIndex != col)
    } yield
      (rowIndex, colIndex)
  }

  def isBomb(coord: (Int, Int), bombs: Set[(Int, Int)]) =
    bombs.contains(coord)

  def neighbouringBombs(coord: (Int, Int), bombs: Set[(Int, Int)]): Int = {
    neighbours(coord._1, coord._2) count(isBomb(_, bombs))
  }

  def createLine(f:Field,lineNumber:Int)= {

    def checkField(col:Int):String =
      if(isBomb( (lineNumber, col), f.bombs )) "*"
      else neighbouringBombs( (lineNumber, col), f.bombs ).toString

    (0 until f.cols).map( checkField ).mkString
  }
}