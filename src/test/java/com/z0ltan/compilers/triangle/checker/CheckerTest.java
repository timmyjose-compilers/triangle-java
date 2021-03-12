package com.z0ltan.compilers.triangle.checker;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import com.z0ltan.compilers.triangle.scanner.Scanner;
import com.z0ltan.compilers.triangle.parser.Parser;
import com.z0ltan.compilers.triangle.ast.Program;
import com.z0ltan.compilers.triangle.ast.EmptyCommand;
import com.z0ltan.compilers.triangle.ast.AssignCommand;
import com.z0ltan.compilers.triangle.ast.CallCommand;
import com.z0ltan.compilers.triangle.ast.LetCommand;
import com.z0ltan.compilers.triangle.ast.SequentialCommand;
import com.z0ltan.compilers.triangle.ast.IntegerExpression;
import com.z0ltan.compilers.triangle.ast.VnameExpression;
import com.z0ltan.compilers.triangle.ast.BinaryExpression;
import com.z0ltan.compilers.triangle.ast.VarDeclaration;
import com.z0ltan.compilers.triangle.ast.ProcDeclaration;
import com.z0ltan.compilers.triangle.ast.SequentialDeclaration;
import com.z0ltan.compilers.triangle.ast.SingleFormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.VarFormalParameter;
import com.z0ltan.compilers.triangle.ast.SingleActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.ConstActualParameter;
import com.z0ltan.compilers.triangle.ast.VarActualParameter;
import com.z0ltan.compilers.triangle.ast.SimpleTypeDenoter;
import com.z0ltan.compilers.triangle.ast.IntTypeDenoter;
import com.z0ltan.compilers.triangle.ast.SimpleVname;
import com.z0ltan.compilers.triangle.ast.Identifier;
import com.z0ltan.compilers.triangle.ast.Operator;
import com.z0ltan.compilers.triangle.ast.IntegerLiteral;

import static com.z0ltan.compilers.triangle.scanner.SourcePosition.dummyPosition;
import static com.z0ltan.compilers.triangle.checker.matchers.IsHello.hello;
import static com.z0ltan.compilers.triangle.checker.matchers.IsInc.inc;

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
    final Program expectedProgram = new Program(new EmptyCommand(dummyPosition()), dummyPosition());
    final Program actualProgram = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(actualProgram);
    assertThat(expectedProgram, is(equalTo(actualProgram)));
  }

  public void testEmptyCommandSemicolon() {
    String filename = "samples/emptycommandsemicolon.t";
    Parser parser = new Parser(new Scanner(filename));
    final Program expectedProgram = new Program(new EmptyCommand(dummyPosition()), dummyPosition());
    final Program actualProgram = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(actualProgram);
    assertThat(expectedProgram, is(equalTo(actualProgram)));
  }

  public void testHello() {
    String filename = "samples/hello.t";
    Parser parser = new Parser(new Scanner(filename));
    final Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    assertThat(program, is(hello()));
  }

  public void testInc() {
    String filename = "samples/inc.t";
    Parser parser = new Parser(new Scanner(filename));
    final Program program = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(program);
    assertThat(program, is(inc()));
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
