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
    assert(Seq((-1, -1), (-1, 0), (-1, 1), (0, -1), (0 ,1), (1, -1), (1, 0), (1, 1)) === neighbours(0,0))
  }

  test("test evaluation"){
    val in = List(
      "3 2",
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

  test("test evaluation 1 bomb"){
    val in = List(
      "3 2",
      ".*",
      "..",
      ".."
    )
    val out = List(
      "1*",
      "11",
      "00"
    )
    assert(out === evaluate(in))
  }

  test("neighbours have 0 bombs") {
    assert(0 === neighbouringBombs((0, 0), Set()))
  }

  test("neighbours have 1 bombs") {
    assert(1 === neighbouringBombs((0, 0), Set((1, 1))))
  }

  test("neighbours have 2 bombs") {
    assert(2 === neighbouringBombs((0, 0), Set((1, 1),(1,0),(0,100))))
  }

  test("build output 0"){
    assert("0" === createLine(Field(1,1,Set()),0))
  }

  test("build output 1 bomb"){
    assert("1*10" === createLine(Field(1,4,Set( (0,1) )),0))
  }

}
