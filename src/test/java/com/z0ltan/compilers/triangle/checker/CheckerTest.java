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
    final Program expectedProgram =
      new Program(
          new CallCommand(
            new Identifier("putint", dummyPosition()),
            new SingleActualParameterSequence(
              new ConstActualParameter(new IntegerExpression(new IntegerLiteral("42", dummyPosition()), dummyPosition()), dummyPosition()),
              dummyPosition()),
            dummyPosition()),
          dummyPosition());
    final Program actualProgram = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(actualProgram);
    assertThat(actualProgram, is(hello()));
  }

  public void testInc() {
    String filename = "samples/inc.t";
    Parser parser = new Parser(new Scanner(filename));
    final Program expectedProgram = 
      new Program(
          new LetCommand(
            new SequentialDeclaration(
              new VarDeclaration(
                new Identifier("x", dummyPosition()),
                new SimpleTypeDenoter(new Identifier("Integer", dummyPosition()), dummyPosition()),
                dummyPosition()),
              new ProcDeclaration(
                new Identifier("inc", dummyPosition()),
                new SingleFormalParameterSequence(
                  new VarFormalParameter(new Identifier("n", dummyPosition()), 
                    new SimpleTypeDenoter(new Identifier("Integer", dummyPosition()), dummyPosition()),
                    dummyPosition()),
                  dummyPosition()),
                new AssignCommand(
                  new SimpleVname(new Identifier("n", dummyPosition()), dummyPosition()),
                  new BinaryExpression(
                    new VnameExpression(new SimpleVname(new Identifier("n", dummyPosition()), dummyPosition()), dummyPosition()),
                    new Operator("+", dummyPosition()),
                    new IntegerExpression(new IntegerLiteral("1", dummyPosition()), dummyPosition()),
                    dummyPosition()),
                  dummyPosition()),
                dummyPosition()),
              dummyPosition()),
              new SequentialCommand(
                  new SequentialCommand(
                    new CallCommand(
                      new Identifier("getint", dummyPosition()),
                      new SingleActualParameterSequence(
                        new VarActualParameter(new SimpleVname(new Identifier("x", dummyPosition()), dummyPosition()), dummyPosition()),
                        dummyPosition()),
                      dummyPosition()),
                    new CallCommand(
                      new Identifier("inc", dummyPosition()),
                      new SingleActualParameterSequence(
                        new VarActualParameter(new SimpleVname(new Identifier("x", dummyPosition()), dummyPosition()), dummyPosition()),
                        dummyPosition()),
                      dummyPosition()),
                    dummyPosition()),
                  new CallCommand(
                    new Identifier("putint", dummyPosition()),
                    new SingleActualParameterSequence(
                      new ConstActualParameter(new VnameExpression(new SimpleVname(new Identifier("x", dummyPosition()), dummyPosition()), dummyPosition()),
                        dummyPosition()),
                      dummyPosition()), dummyPosition()),
                  dummyPosition()),
                  dummyPosition()),
                  dummyPosition());
    final Program actualProgram = parser.parseProgram();
    Checker checker = new Checker();
    checker.check(actualProgram);
    assertThat(expectedProgram, is(equalTo(actualProgram)));
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
