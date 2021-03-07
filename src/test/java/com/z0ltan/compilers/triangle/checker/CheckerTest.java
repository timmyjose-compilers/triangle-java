package com.z0ltan.compilers.triangle.checker;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.junit.Assert.*;

public class CheckerTest extends TestCase {
  public CheckerTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(CheckerTest.class);
  }

  static class ScannerTestCase {
    TokenType kind;
    String spelling;

    public CheckerTest(final TokenType kind, final String spelling) {
      this.kind = kind;
      this.spelling = spelling;
    }
  }
}
