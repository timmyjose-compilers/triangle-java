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
  }

  public void testEcho() {
  }

  public void testOdd() {
  }

  public void testSumProc() {
  }

  public void testPower() {
  }

  public void testFactorial() {
  }

  public void testRecord() {
  }

  public void testLeapYear() {
  }

  public void testPrintArray() {
  }

  public void testDate() {
  }

  public void testString() {
  }

  public void testIteratively() {
  }

  public void testReverseLine() {
  }

  public void testLine() {
  }

  public void testDates() {
  }

  public void testMonthsOfYear() {
  }

  public void testCapitalise() {
  }

  public void testFreq() {
  }

  public void testInsertionSort() {
  }

  public void testRationals() {
  }
}
