package com.z0ltan.compilers.triangle.scanner;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.junit.Assert.*;

import java.util.Iterator;

public class ScannerTest extends TestCase {
  public ScannerTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(ScannerTest.class);
  }

  static class ScannerTestCase {
    TokenType kind;
    String spelling;

    public ScannerTestCase(final TokenType kind, final String spelling) {
      this.kind = kind;
      this.spelling = spelling;
    }
  }

  public void testEmptyCommandEot() {
    String filename = "samples/emptycommandeot.t";
    ScannerTestCase testCases[] = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.EOT, "")
    };

    Scanner scanner = new Scanner(filename);
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].kind, token.kind);
      assertEquals(testCases[i].spelling, token.spelling);
    }
  }

  public void testEmptyCommandEotDegenerate() {
    String filename = "samples/emptycommandeot_degenerate.t";
    ScannerTestCase testCases[] = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.EOT, "")
    };

    Scanner scanner = new Scanner(filename);
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].kind, token.kind);
      assertEquals(testCases[i].spelling, token.spelling);
    }
  }

  public void testEmptyCommandSemicolon() {
    String filename = "samples/emptycommandsemicolon.t";
    ScannerTestCase testCases[] = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.SEMICOLON, ";"),
      new ScannerTestCase(TokenType.EOT, "")
    };

    Scanner scanner = new Scanner(filename);
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].kind, token.kind);
      assertEquals(testCases[i].spelling, token.spelling);
    }
  }

  public void testEmptyCommandSemicolonDegenerate() {
    String filename = "samples/emptycommandsemicolon_degenerate.t";
    ScannerTestCase testCases[] = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.SEMICOLON, ";"),
      new ScannerTestCase(TokenType.EOT, "")
    };

    Scanner scanner = new Scanner(filename);
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].kind, token.kind);
      assertEquals(testCases[i].spelling, token.spelling);
    }
  }

  public void testHello() {
    String filename = "samples/hello.t";
    ScannerTestCase testCases[] = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
      new ScannerTestCase(TokenType.LEFT_PAREN, "("),
      new ScannerTestCase(TokenType.NUMBER, "42"),
      new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
      new ScannerTestCase(TokenType.EOT, "")
    };

    Scanner scanner = new Scanner(filename);
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].kind, token.kind);
      assertEquals(testCases[i].spelling, token.spelling);
    }
  }

  public void testHelloDegenerate() {
    String filename = "samples/hello_degenerate.t";
    ScannerTestCase testCases[] = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
      new ScannerTestCase(TokenType.LEFT_PAREN, "("),
      new ScannerTestCase(TokenType.NUMBER, "42"),
      new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
      new ScannerTestCase(TokenType.EOT, "")
    };

    Scanner scanner = new Scanner(filename);
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].kind, token.kind);
      assertEquals(testCases[i].spelling, token.spelling);
    }
  }

  public void testInc() {
    String filename = "samples/inc.t";
    ScannerTestCase testCases[] = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
      new ScannerTestCase(TokenType.VAR, "var"),
      new ScannerTestCase(TokenType.IDENTIFIER, "x"),
      new ScannerTestCase(TokenType.COLON, ":"),
      new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
      new ScannerTestCase(TokenType.SEMICOLON, ";"),
      new ScannerTestCase(TokenType.PROCEDURE, "proc"),
      new ScannerTestCase(TokenType.IDENTIFIER, "inc"),
      new ScannerTestCase(TokenType.LEFT_PAREN, "("),
      new ScannerTestCase(TokenType.VAR, "var"),
      new ScannerTestCase(TokenType.IDENTIFIER, "n"),
      new ScannerTestCase(TokenType.COLON, ":"),
      new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
      new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
      new ScannerTestCase(TokenType.IS, "~"),
      new ScannerTestCase(TokenType.IDENTIFIER, "n"),
      new ScannerTestCase(TokenType.BECOMES, ":="),
      new ScannerTestCase(TokenType.IDENTIFIER, "n"),
      new ScannerTestCase(TokenType.OPERATOR, "+"),
      new ScannerTestCase(TokenType.NUMBER, "1"),
      new ScannerTestCase(TokenType.IN, "in"),
      new ScannerTestCase(TokenType.BEGIN, "begin"),
      new ScannerTestCase(TokenType.IDENTIFIER, "getint"),
      new ScannerTestCase(TokenType.LEFT_PAREN, "("),
      new ScannerTestCase(TokenType.VAR, "var"),
      new ScannerTestCase(TokenType.IDENTIFIER, "x"),
      new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
      new ScannerTestCase(TokenType.SEMICOLON, ";"),
      new ScannerTestCase(TokenType.IDENTIFIER, "inc"),
      new ScannerTestCase(TokenType.LEFT_PAREN, "("),
      new ScannerTestCase(TokenType.VAR, "var"),
      new ScannerTestCase(TokenType.IDENTIFIER, "x"),
      new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
      new ScannerTestCase(TokenType.SEMICOLON, ";"),
      new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
      new ScannerTestCase(TokenType.LEFT_PAREN, "("),
      new ScannerTestCase(TokenType.IDENTIFIER, "x"),
      new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
      new ScannerTestCase(TokenType.END, "end"),
      new ScannerTestCase(TokenType.EOT, "")
    };

    Scanner scanner = new Scanner(filename);
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].kind, token.kind);
      assertEquals(testCases[i].spelling, token.spelling);
    }
  }

  public void testIncDegenerate() {
    String filename = "samples/inc_degenerate.t";
    ScannerTestCase testCases[] = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
      new ScannerTestCase(TokenType.VAR, "var"),
      new ScannerTestCase(TokenType.IDENTIFIER, "x"),
      new ScannerTestCase(TokenType.COLON, ":"),
      new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
      new ScannerTestCase(TokenType.SEMICOLON, ";"),
      new ScannerTestCase(TokenType.PROCEDURE, "proc"),
      new ScannerTestCase(TokenType.IDENTIFIER, "inc"),
      new ScannerTestCase(TokenType.LEFT_PAREN, "("),
      new ScannerTestCase(TokenType.VAR, "var"),
      new ScannerTestCase(TokenType.IDENTIFIER, "n"),
      new ScannerTestCase(TokenType.COLON, ":"),
      new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
      new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
      new ScannerTestCase(TokenType.IS, "~"),
      new ScannerTestCase(TokenType.IDENTIFIER, "n"),
      new ScannerTestCase(TokenType.BECOMES, ":="),
      new ScannerTestCase(TokenType.IDENTIFIER, "n"),
      new ScannerTestCase(TokenType.OPERATOR, "+"),
      new ScannerTestCase(TokenType.NUMBER, "1"),
      new ScannerTestCase(TokenType.IN, "in"),
      new ScannerTestCase(TokenType.BEGIN, "begin"),
      new ScannerTestCase(TokenType.IDENTIFIER, "getint"),
      new ScannerTestCase(TokenType.LEFT_PAREN, "("),
      new ScannerTestCase(TokenType.VAR, "var"),
      new ScannerTestCase(TokenType.IDENTIFIER, "x"),
      new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
      new ScannerTestCase(TokenType.SEMICOLON, ";"),
      new ScannerTestCase(TokenType.IDENTIFIER, "inc"),
      new ScannerTestCase(TokenType.LEFT_PAREN, "("),
      new ScannerTestCase(TokenType.VAR, "var"),
      new ScannerTestCase(TokenType.IDENTIFIER, "x"),
      new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
      new ScannerTestCase(TokenType.SEMICOLON, ";"),
      new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
      new ScannerTestCase(TokenType.LEFT_PAREN, "("),
      new ScannerTestCase(TokenType.IDENTIFIER, "x"),
      new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
      new ScannerTestCase(TokenType.END, "end"),
      new ScannerTestCase(TokenType.EOT, "")
    };

    Scanner scanner = new Scanner(filename);
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].kind, token.kind);
      assertEquals(testCases[i].spelling, token.spelling);
    }
  }


  public void testEcho() {

  }

  public void testEchoDegenerate() {

  }


  public void testOdd() {

  }

  public void testOddDegenerate() {

  }


  public void testSumProc() {

  }

  public void testSumProcDegenerate() {

  }


  public void testPower() {

  }

  public void testPowerDegenerate() {

  }


  public void testFactorial() {

  }

  public void testFactorialDegenerate() {

  }


  public void testRecord() {

  }

  public void testRecordDegenerate() {

  }


  public void testLeapYear() {

  }

  public void testLeapYearDegenerate() {

  }


  public void testPrintArray() {

  }

  public void testPrintArrayDegenerate() {

  }


  public void testDate() {

  }

  public void testDateDegenerate() {

  }


  public void testString() {

  }

  public void testStringDegenerate() {

  }


  public void testIteratively() {

  }

  public void testIterativelyDegenerate() {

  }


  public void testReverseLine() {

  }

  public void testReverseLineDegenerate() {

  }


  public void testLine() {

  }

  public void testLineDegenerate() {

  }


  public void testDates() {

  }

  public void testDatesDegenerate() {

  }


  public void testMonthsOfYear() {

  }

  public void testMonthsOfYearDegenerate() {

  }


  public void testCapitalise() {

  }

  public void testCapitaliseDegenerate() {

  }


  public void testFreq() {

  }

  public void testFreqDegenerate() {

  }


  public void testInsertionSort() {

  }

  public void testInsertionSortDegenerate() {

  }


  public void testRationals() {

  }

  public void testRationalsDegenerate() {

  }
}
