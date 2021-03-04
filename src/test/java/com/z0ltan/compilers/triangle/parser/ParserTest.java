package com.z0ltan.compilers.triangle.parser;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.junit.Assert.*;

import com.z0ltan.compilers.triangle.scanner.Scanner;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;
import com.z0ltan.compilers.triangle.parser.Parser;
import com.z0ltan.compilers.triangle.ast.Program;
import com.z0ltan.compilers.triangle.ast.Command;
import com.z0ltan.compilers.triangle.ast.EmptyCommand;
import com.z0ltan.compilers.triangle.ast.CallCommand;
import com.z0ltan.compilers.triangle.ast.Expression;
import com.z0ltan.compilers.triangle.ast.IntegerExpression;
import com.z0ltan.compilers.triangle.ast.ActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.SingleActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.MultipleActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.ActualParameter;
import com.z0ltan.compilers.triangle.ast.ConstActualParameter;
import com.z0ltan.compilers.triangle.ast.VarActualParameter;
import com.z0ltan.compilers.triangle.ast.ProcActualParameter;
import com.z0ltan.compilers.triangle.ast.FuncActualParameter;
import com.z0ltan.compilers.triangle.ast.Identifier;
import com.z0ltan.compilers.triangle.ast.Operator;
import com.z0ltan.compilers.triangle.ast.IntegerLiteral;
import com.z0ltan.compilers.triangle.ast.CharacterLiteral;

import static com.z0ltan.compilers.triangle.scanner.SourcePosition.dummyPosition;

public class ParserTest extends TestCase {
  public ParserTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(ParserTest.class);
  }

  public void testEmptyCommandEot() {
    String filename = "samples/emptycommandeot.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = new Program(new EmptyCommand(dummyPosition()), dummyPosition());
    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testEmptyCommandEotDegenerate() {
    String filename = "samples/emptycommandeot_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = new Program(new EmptyCommand(dummyPosition()), dummyPosition());
    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testEmptyCommandSemicolon() {
    String filename = "samples/emptycommandsemicolon.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = new Program(new EmptyCommand(dummyPosition()), dummyPosition());
    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testEmptyCommandSemicolonDegenerate() {
    String filename = "samples/emptycommandsemicolon_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = new Program(new EmptyCommand(dummyPosition()), dummyPosition());
    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testHello() {
    String filename = "samples/hello.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = 
      new Program(new CallCommand(new Identifier("putint", dummyPosition()), 
                                  new SingleActualParameterSequence(new ConstActualParameter(new IntegerExpression(new IntegerLiteral("42", dummyPosition()), dummyPosition()), 
                                                                                             dummyPosition()), dummyPosition()), 
                                  dummyPosition()),
                  dummyPosition());
    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void xtestHelloDegenerate() {
    String filename = "samples/hello_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = 
      new Program(new CallCommand(new Identifier("putint", dummyPosition()), new ActualParameterSequence(dummyPosition()), dummyPosition()), dummyPosition());
    Program actualProgram = parser.parseProgram();
    //assertEquals(expectedProgram, actualProgram);
  }

  public void testInc() {
    String filename = "samples/inc.t";

  }

  public void testIncDegenerate() {
    String filename = "samples/inc_degenerate.t";

  }


  public void testEcho() {
    String filename = "samples/echo.t";

  }

  public void testEchoDegenerate() {
    String filename = "samples/echo_degenerate.t";

  }

  public void testOdd() {
    String filename = "samples/odd.t";

  }

  public void testOddDegenerate() {
    String filename = "samples/odd_degenerate.t";

  }

  public void testSumProc() {
    String filename = "samples/sum_proc.t";

  }

  public void testSumProcDegenerate() {
    String filename = "samples/sum_proc_degenerate.t";

  }

  public void testPower() {
    String filename = "samples/power.t";

  }

  public void testPowerDegenerate() {
    String filename = "samples/power_degenerate.t";

  }

  public void testFactorial() {
    String filename = "samples/factorial.t";

  }

  public void testFactorialDegenerate() {
    String filename = "samples/factorial_degenerate.t";

  }

  public void testRecord() {
    String filename = "samples/record.t";

  }

  public void testRecordDegenerate() {
    String filename = "samples/record_degenerate.t";

  }

  public void testLeapYear() {
    String filename = "samples/leapyear.t";

  }

  public void testLeapYearDegenerate() {
    String filename = "samples/leapyear_degenerate.t";

  }

  public void testPrintArray() {
    String filename = "samples/print_array.t";

  }

  public void testPrintArrayDegenerate() {
    String filename = "samples/print_array_degenerate.t";

  }

  public void testDate() {
    String filename = "samples/date.t";

  }

  public void testDateDegenerate() {
    String filename = "samples/date_degenerate.t";

  }

  public void testString() {
    String filename = "samples/string.t";

  }

  public void testStringDegenerate() {
    String filename = "samples/string_degenerate.t";

  }

  public void testIteratively() {
    String filename = "samples/iteratively.t";

  }

  public void testIterativelyDegenerate() {
    String filename = "samples/iteratively_degenerate.t";

  }

  public void testReverseLine() {
    String filename = "samples/reverse_line.t";

  }

  public void testReverseLineDegenerate() {
    String filename = "samples/reverse_line_degenerate.t";

  }

  public void testLine() {
    String filename = "samples/line.t";

  }

  public void testLineDegenerate() {
    String filename = "samples/line_degenerate.t";

  }

  public void testDates() {
    String filename = "samples/dates.t";

  }

  public void testDatesDegenerate() {
    String filename = "samples/dates_degenerate.t";

  }

  public void testMonthsOfYear() {
    String filename = "samples/monthsofyear.t";

  }

  public void testMonthsOfYearDegenerate() {
    String filename = "samples/monthsofyear_degenerate.t";

  }


  public void testCapitalise() {
    String filename = "samples/capitalise.t";

  }

  public void testCapitaliseDegenerate() {
    String filename = "samples/capitalise_degenerate.t";

  }


  public void testFreq() {
    String filename = "samples/capitalise.t";

  }

  public void testFreqDegenerate() {
    String filename = "samples/capitalise_degenerate.t";

  }

  public void testInsertionSort() {
    String filename = "samples/capitalise.t";

  }

  public void testInsertionSortDegenerate() {
    String filename = "samples/capitalise_degenerate.t";

  }

  public void testRationals() {
    String filename = "samples/capitalise.t";

  }

  public void testRationalsDegenerate() {
    String filename = "samples/capitalise_degenerate.t";

  }
}

