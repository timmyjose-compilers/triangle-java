package com.z0ltan.compilers.triangle.checker;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.junit.Assert.*;

import com.z0ltan.compilers.triangle.scanner.Scanner;
import com.z0ltan.compilers.triangle.parser.Parser;
import com.z0ltan.compilers.triangle.ast.Program;
import com.z0ltan.compilers.triangle.ast.EmptyCommand;
import com.z0ltan.compilers.triangle.ast.CallCommand;
import com.z0ltan.compilers.triangle.ast.IntegerExpression;
import com.z0ltan.compilers.triangle.ast.SingleActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.ConstActualParameter;
import com.z0ltan.compilers.triangle.ast.Identifier;
import com.z0ltan.compilers.triangle.ast.IntegerLiteral;

import static com.z0ltan.compilers.triangle.scanner.SourcePosition.dummyPosition;

public class CheckerTest extends TestCase {
  public CheckerTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(CheckerTest.class);
  }

  public void testEmptyCommandEot() {
    String filename = "samples/emptycommandeot.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    final Program actualProgram = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(actualProgram);
    final Program expectedProgram = new Program(new EmptyCommand(dummyPosition()), dummyPosition());
    assertEquals(expectedProgram, actualProgram);
  }

  public void testEmptyCommandSemicolon() {
    String filename = "samples/emptycommandsemicolon.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    final Program actualProgram = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(actualProgram);
    final Program expectedProgram = new Program(new EmptyCommand(dummyPosition()), dummyPosition());
    assertEquals(expectedProgram, actualProgram);
  }

  public void testHello() {
    String filename = "samples/hello.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    final Program actualProgram = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(actualProgram);
    final Program expectedProgram =
      new Program(
          new CallCommand(
            new Identifier("putint", dummyPosition()),
            new SingleActualParameterSequence(
              new ConstActualParameter(new IntegerExpression(new IntegerLiteral("42", dummyPosition()), dummyPosition()), dummyPosition()),
              dummyPosition()),
            dummyPosition()),
          dummyPosition());
    assertEquals(expectedProgram, actualProgram);
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
