package com.z0ltan.compilers.triangle.checker;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.z0ltan.compilers.triangle.scanner.Scanner;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;
import com.z0ltan.compilers.triangle.parser.Parser;
import com.z0ltan.compilers.triangle.ast.Program;

import static com.z0ltan.compilers.triangle.scanner.SourcePosition.dummyPosition;
import static com.z0ltan.compilers.triangle.checker.matchers.IsEmptyCommand.emptycommand;
import static com.z0ltan.compilers.triangle.checker.matchers.IsHello.hello;
import static com.z0ltan.compilers.triangle.checker.matchers.IsInc.inc;
import static com.z0ltan.compilers.triangle.checker.matchers.IsEcho.echo;
import static com.z0ltan.compilers.triangle.checker.matchers.IsOdd.odd;
import static com.z0ltan.compilers.triangle.checker.matchers.IsSumProc.sumProc;
import static com.z0ltan.compilers.triangle.checker.matchers.IsPower.power;
import static com.z0ltan.compilers.triangle.checker.matchers.IsFactorial.factorial;
import static com.z0ltan.compilers.triangle.checker.matchers.IsRecord.record;
import static com.z0ltan.compilers.triangle.checker.matchers.IsLeapYear.leapYear;

public class CheckerTest extends TestCase {
  public CheckerTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(CheckerTest.class);
  }

  public void testEmptyCommandEot() {
    String filename = "samples/emptycommandeot.t";
    Parser parser = new Parser(new Scanner(filename));
    Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    assertThat(program, is(emptycommand()));
  }

  public void testEmptyCommandSemicolon() {
    String filename = "samples/emptycommandsemicolon.t";
    Parser parser = new Parser(new Scanner(filename));
    Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    assertThat(program, is(emptycommand()));
  }

  public void testHello() {
    String filename = "samples/hello.t";
    Parser parser = new Parser(new Scanner(filename));
    Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    assertThat(program, is(hello()));
  }

  public void testInc() {
    String filename = "samples/inc.t";
    Parser parser = new Parser(new Scanner(filename));
    Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    assertThat(program, is(inc()));
  }

  public void testEcho() {
    String filename = "samples/echo.t";
    Parser parser = new Parser(new Scanner(filename));
    Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    assertThat(program, is(echo()));
  }

  public void testOdd() {
    String filename = "samples/odd.t";
    Parser parser = new Parser(new Scanner(filename));
    Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    assertThat(program, is(odd()));
  }

  public void testSumProc() {
    String filename = "samples/sum_proc.t";
    Parser parser = new Parser(new Scanner(filename));
    Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    assertThat(program, is(sumProc()));
  }

  public void testPower() {
    String filename = "samples/power.t";
    Parser parser = new Parser(new Scanner(filename));
    Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    assertThat(program, is(power()));
  }

  public void testFactorial() {
    String filename = "samples/factorial.t";
    Parser parser = new Parser(new Scanner(filename));
    Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    assertThat(program, is(factorial()));
  }

  public void testRecord() {
    String filename = "samples/record.t";
    Parser parser = new Parser(new Scanner(filename));
    Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    assertThat(program, is(record()));
  }

  public void testLeapYear() {
    String filename = "samples/leapyear.t";
    Parser parser = new Parser(new Scanner(filename));
    Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    //assertThat(program, is(leapYear()));
  }

  //@TODO
  public void testPrintArray() {
    String filename = "samples/print_array.t";
    Parser parser = new Parser(new Scanner(filename));
    Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    //assertThat(program, is(leapYear()));
  }

  //@TODO
  public void testDate() {
    String filename = "samples/date.t";
    Parser parser = new Parser(new Scanner(filename));
    Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    //assertThat(program, is(leapYear()));
  }

  //@TODO
  public void testString() {
    String filename = "samples/string.t";
    Parser parser = new Parser(new Scanner(filename));
    Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    //assertThat(program, is(leapYear()));
  }

  //@TODO
  public void testIteratively() {
    String filename = "samples/iteratively.t";
    Parser parser = new Parser(new Scanner(filename));
    Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    //assertThat(program, is(leapYear()));
  }

  //@TODO
  public void testReverseLine() {
    String filename = "samples/reverse_line.t";
    Parser parser = new Parser(new Scanner(filename));
    Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    //assertThat(program, is(leapYear()));
  }

  //@TODO
  public void testLine() {
    String filename = "samples/line.t";
    Parser parser = new Parser(new Scanner(filename));
    Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    //assertThat(program, is(leapYear()));
  }

  //@TODO
  public void testDates() {
    String filename = "samples/dates.t";
    Parser parser = new Parser(new Scanner(filename));
    Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    //assertThat(program, is(leapYear()));
  }

  //@TODO
  public void testMonthsOfYear() {
    String filename = "samples/monthsofyear.t";
    Parser parser = new Parser(new Scanner(filename));
    Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    //assertThat(program, is(leapYear()));
  }

  //@TODO
  public void testCapitalise() {
    String filename = "samples/capitalise.t";
    Parser parser = new Parser(new Scanner(filename));
    Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    //assertThat(program, is(leapYear()));
  }

  //@TODO
  public void testFreq() {
    String filename = "samples/freq.t";
    Parser parser = new Parser(new Scanner(filename));
    Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    //assertThat(program, is(leapYear()));
  }

  //@TODO
  public void testInsertionSort() {
    String filename = "samples/insertion_sort.t";
    Parser parser = new Parser(new Scanner(filename));
    Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    //assertThat(program, is(leapYear()));
  }

  public void testRationals() {
    String filename = "samples/rationals.t";
    Parser parser = new Parser(new Scanner(filename));
    Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    //assertThat(program, is(leapYear()));
  }
}
