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
    String filename = "samples/echo.t";
    ScannerTestCase testCases[] = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "ch"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Char"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.PROCEDURE, "proc"),
          new ScannerTestCase(TokenType.IDENTIFIER, "echo"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.WHILE, "while"),
          new ScannerTestCase(TokenType.OPERATOR, "\\"),
          new ScannerTestCase(TokenType.IDENTIFIER, "eol"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.DO, "do"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "get"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "ch"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "put"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "ch"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.END, "end"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.END, "end"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.IDENTIFIER, "echo"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
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

  public void testEchoDegenerate() {
    String filename = "samples/echo_degenerate.t";
    ScannerTestCase testCases[] = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "ch"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Char"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.PROCEDURE, "proc"),
          new ScannerTestCase(TokenType.IDENTIFIER, "echo"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.WHILE, "while"),
          new ScannerTestCase(TokenType.OPERATOR, "\\"),
          new ScannerTestCase(TokenType.IDENTIFIER, "eol"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.DO, "do"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "get"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "ch"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "put"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "ch"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.END, "end"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.END, "end"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.IDENTIFIER, "echo"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
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

  public void testOdd() {
    String filename = "samples/odd.t";
    ScannerTestCase testCases[] = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.FUNCTION, "func"),
          new ScannerTestCase(TokenType.IDENTIFIER, "odd"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Boolean"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.OPERATOR, "/"),
          new ScannerTestCase(TokenType.OPERATOR, "/"),
          new ScannerTestCase(TokenType.NUMBER, "2"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.OPERATOR, "\\"),
          new ScannerTestCase(TokenType.OPERATOR, "="),
          new ScannerTestCase(TokenType.NUMBER, "0"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "getint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IF, "if"),
          new ScannerTestCase(TokenType.IDENTIFIER, "odd"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.THEN, "then"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.NUMBER, "1"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.ELSE, "else"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.NUMBER, "2"),
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

  public void testOddDegenerate() {
    String filename = "samples/odd_degenerate.t";
    ScannerTestCase testCases[] = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.FUNCTION, "func"),
          new ScannerTestCase(TokenType.IDENTIFIER, "odd"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Boolean"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.OPERATOR, "/"),
          new ScannerTestCase(TokenType.OPERATOR, "/"),
          new ScannerTestCase(TokenType.NUMBER, "2"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.OPERATOR, "\\"),
          new ScannerTestCase(TokenType.OPERATOR, "="),
          new ScannerTestCase(TokenType.NUMBER, "0"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "getint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IF, "if"),
          new ScannerTestCase(TokenType.IDENTIFIER, "odd"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.THEN, "then"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.NUMBER, "1"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.ELSE, "else"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.NUMBER, "2"),
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

  public void testSumProc() {
    String filename = "samples/sum_proc.t";
    ScannerTestCase testCases[] = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "s"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.PROCEDURE, "proc"),
          new ScannerTestCase(TokenType.IDENTIFIER, "add"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "a"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "b"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "r"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.IDENTIFIER, "r"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER, "a"),
          new ScannerTestCase(TokenType.OPERATOR, "+"),
          new ScannerTestCase(TokenType.IDENTIFIER, "b"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "getint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "getint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "add"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "s"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "s"),
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

  public void testSumProcDegenerate() {
    String filename = "samples/sum_proc_degenerate.t";
    ScannerTestCase testCases[] = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "s"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.PROCEDURE, "proc"),
          new ScannerTestCase(TokenType.IDENTIFIER, "add"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "a"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "b"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "r"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.IDENTIFIER, "r"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER, "a"),
          new ScannerTestCase(TokenType.OPERATOR, "+"),
          new ScannerTestCase(TokenType.IDENTIFIER, "b"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "getint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "getint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "add"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "s"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "s"),
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

  public void testPower() {
    String filename = "samples/power.t";
    ScannerTestCase testCases[] = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "m"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.FUNCTION, "func"),
          new ScannerTestCase(TokenType.IDENTIFIER, "power"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "a"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "b"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.IF, "if"),
          new ScannerTestCase(TokenType.IDENTIFIER, "b"),
          new ScannerTestCase(TokenType.OPERATOR, "="),
          new ScannerTestCase(TokenType.NUMBER, "0"),
          new ScannerTestCase(TokenType.THEN, "then"),
          new ScannerTestCase(TokenType.NUMBER, "1"),
          new ScannerTestCase(TokenType.ELSE, "else"),
          new ScannerTestCase(TokenType.IDENTIFIER, "a"),
          new ScannerTestCase(TokenType.OPERATOR, "*"),
          new ScannerTestCase(TokenType.IDENTIFIER, "power"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "a"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "b"),
          new ScannerTestCase(TokenType.OPERATOR, "-"),
          new ScannerTestCase(TokenType.NUMBER, "1"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "getint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "m"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "getint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "power"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "m"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
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

  public void testPowerDegenerate() {
    String filename = "samples/power_degenerate.t";
    ScannerTestCase testCases[] = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "m"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.FUNCTION, "func"),
          new ScannerTestCase(TokenType.IDENTIFIER, "power"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "a"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "b"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.IF, "if"),
          new ScannerTestCase(TokenType.IDENTIFIER, "b"),
          new ScannerTestCase(TokenType.OPERATOR, "="),
          new ScannerTestCase(TokenType.NUMBER, "0"),
          new ScannerTestCase(TokenType.THEN, "then"),
          new ScannerTestCase(TokenType.NUMBER, "1"),
          new ScannerTestCase(TokenType.ELSE, "else"),
          new ScannerTestCase(TokenType.IDENTIFIER, "a"),
          new ScannerTestCase(TokenType.OPERATOR, "*"),
          new ScannerTestCase(TokenType.IDENTIFIER, "power"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "a"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "b"),
          new ScannerTestCase(TokenType.OPERATOR, "-"),
          new ScannerTestCase(TokenType.NUMBER, "1"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "getint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "m"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "getint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "power"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "m"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
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

  public void testFactorial() {
    String filename = "samples/factorial.t";
    ScannerTestCase testCases[] = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.FUNCTION, "func"),
          new ScannerTestCase(TokenType.IDENTIFIER, "factorial"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.IF, "if"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.OPERATOR, "<"),
          new ScannerTestCase(TokenType.OPERATOR, "="),
          new ScannerTestCase(TokenType.NUMBER, "0"),
          new ScannerTestCase(TokenType.THEN, "then"),
          new ScannerTestCase(TokenType.NUMBER, "1"),
          new ScannerTestCase(TokenType.ELSE, "else"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.OPERATOR, "*"),
          new ScannerTestCase(TokenType.IDENTIFIER, "factorial"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.OPERATOR, "-"),
          new ScannerTestCase(TokenType.NUMBER, "1"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.PROCEDURE, "proc"),
          new ScannerTestCase(TokenType.IDENTIFIER, "readnumber"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.IDENTIFIER, "getint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "readnumber"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "puteol"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "puteol"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "factorial"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
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

  public void testFactorialDegenerate() {
    String filename = "samples/factorial_degenerate.t";
    ScannerTestCase testCases[] = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.FUNCTION, "func"),
          new ScannerTestCase(TokenType.IDENTIFIER, "factorial"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.IF, "if"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.OPERATOR, "<"),
          new ScannerTestCase(TokenType.OPERATOR, "="),
          new ScannerTestCase(TokenType.NUMBER, "0"),
          new ScannerTestCase(TokenType.THEN, "then"),
          new ScannerTestCase(TokenType.NUMBER, "1"),
          new ScannerTestCase(TokenType.ELSE, "else"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.OPERATOR, "*"),
          new ScannerTestCase(TokenType.IDENTIFIER, "factorial"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.OPERATOR, "-"),
          new ScannerTestCase(TokenType.NUMBER, "1"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.PROCEDURE, "proc"),
          new ScannerTestCase(TokenType.IDENTIFIER, "readnumber"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.IDENTIFIER, "getint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "readnumber"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "puteol"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "puteol"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "factorial"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
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

  public void testRecord() {
    String filename = "samples/record.t";
    ScannerTestCase testCases[] = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.TYPE, "type"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Date"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.RECORD, "record"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "m"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "d"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.END, "end"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "today"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Date"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "tomorrow"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Date"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "today"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.LEFT_CURLY, "{"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.NUMBER, "2021"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "m"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.NUMBER, "1"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "d"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.NUMBER, "12"),
          new ScannerTestCase(TokenType.RIGHT_CURLY, "}"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "today"),
          new ScannerTestCase(TokenType.DOT, "."),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "today"),
          new ScannerTestCase(TokenType.DOT, "."),
          new ScannerTestCase(TokenType.IDENTIFIER, "m"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "today"),
          new ScannerTestCase(TokenType.DOT, "."),
          new ScannerTestCase(TokenType.IDENTIFIER, "d"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "tomorrow"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.LEFT_CURLY, "{"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.IDENTIFIER, "today"),
          new ScannerTestCase(TokenType.DOT, "."),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "m"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.IDENTIFIER, "today"),
          new ScannerTestCase(TokenType.DOT, "."),
          new ScannerTestCase(TokenType.IDENTIFIER, "m"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "d"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.IDENTIFIER, "today"),
          new ScannerTestCase(TokenType.DOT, "."),
          new ScannerTestCase(TokenType.IDENTIFIER, "d"),
          new ScannerTestCase(TokenType.OPERATOR, "+"),
          new ScannerTestCase(TokenType.NUMBER, "1"),
          new ScannerTestCase(TokenType.RIGHT_CURLY, "}"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "tomorrow"),
          new ScannerTestCase(TokenType.DOT, "."),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "tomorrow"),
          new ScannerTestCase(TokenType.DOT, "."),
          new ScannerTestCase(TokenType.IDENTIFIER, "m"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "tomorrow"),
          new ScannerTestCase(TokenType.DOT, "."),
          new ScannerTestCase(TokenType.IDENTIFIER, "d"),
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

  public void testRecordDegenerate() {
    String filename = "samples/record_degenerate.t";
    ScannerTestCase testCases[] = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.TYPE, "type"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Date"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.RECORD, "record"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "m"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "d"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.END, "end"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "today"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Date"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "tomorrow"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Date"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "today"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.LEFT_CURLY, "{"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.NUMBER, "2021"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "m"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.NUMBER, "1"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "d"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.NUMBER, "12"),
          new ScannerTestCase(TokenType.RIGHT_CURLY, "}"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "today"),
          new ScannerTestCase(TokenType.DOT, "."),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "today"),
          new ScannerTestCase(TokenType.DOT, "."),
          new ScannerTestCase(TokenType.IDENTIFIER, "m"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "today"),
          new ScannerTestCase(TokenType.DOT, "."),
          new ScannerTestCase(TokenType.IDENTIFIER, "d"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "tomorrow"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.LEFT_CURLY, "{"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.IDENTIFIER, "today"),
          new ScannerTestCase(TokenType.DOT, "."),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "m"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.IDENTIFIER, "today"),
          new ScannerTestCase(TokenType.DOT, "."),
          new ScannerTestCase(TokenType.IDENTIFIER, "m"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "d"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.IDENTIFIER, "today"),
          new ScannerTestCase(TokenType.DOT, "."),
          new ScannerTestCase(TokenType.IDENTIFIER, "d"),
          new ScannerTestCase(TokenType.OPERATOR, "+"),
          new ScannerTestCase(TokenType.NUMBER, "1"),
          new ScannerTestCase(TokenType.RIGHT_CURLY, "}"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "tomorrow"),
          new ScannerTestCase(TokenType.DOT, "."),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "tomorrow"),
          new ScannerTestCase(TokenType.DOT, "."),
          new ScannerTestCase(TokenType.IDENTIFIER, "m"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "tomorrow"),
          new ScannerTestCase(TokenType.DOT, "."),
          new ScannerTestCase(TokenType.IDENTIFIER, "d"),
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
