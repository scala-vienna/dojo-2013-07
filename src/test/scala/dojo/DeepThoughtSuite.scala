package dojo

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

import dojo.Minesweeper._

@RunWith(classOf[JUnitRunner])
class MinesweeperSuite extends FunSuite {


  test("parse line into") {
    val in = List(
      "1 1",
      "."
    )
    assert(Field(1, 1, Set()) === parse(in))
  }

  test("parse three lines into") {
    val in = List(
      "2 2",
      "*.",
      ".*"
    )
    assert(Field(2, 2, Set((0, 0), (1,1))) === parse(in))
  }

  test("parse two columns into") {
    val in = List(
      "1 2",
      ".."
    )
    assert(Field(1,2, Set()) === parse(in))
  }

  test("test info line to set"){
    val in = List(
            ".*"
    )
    assert(Set((0,1)) === buildSet(in))
  }

  test("test info lines to set"){
    val in = List(
      ".*",
      "..",
      "*."
    )
    assert(Set((0,1),(2,0)) === buildSet(in))
  }
  test("test neighbours"){
    val in = Field(1, 1, Set())
    assert(Seq.empty === neighbours(in))
  }

  test("test evaluation"){
    val in = List(
      ".*",
      "..",
      "*."
    )
    val out = List(
      "1*",
      "22",
      "*1"
    )
    assert(out === evaluate(in))

  }
}
