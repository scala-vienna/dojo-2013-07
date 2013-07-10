package dojo

object Minesweeper {

  case class Field(rows: Int, cols: Int, bombs: Set[(Int,Int)])

  def parse(in: List[String]): Field = {
    val a = in.head.split(" ")
    Field(a(0).toInt,a(1).toInt,Set())
  }
}