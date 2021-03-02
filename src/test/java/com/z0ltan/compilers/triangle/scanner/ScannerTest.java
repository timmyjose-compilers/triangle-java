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

  public void testEmptyCommandEot() {

  }

  public void testEmptyCommandEotDegenerate() {

  }

  public void testEmptyCommandSemicolon() {

  }

  public void testEmptyCommandSemicolonDegenerate() {
  }

  public void testHello() {
    String filename = "samples/hello.t";
    Iterator<Char> file = new SourceFile(filename).iterator();
    while (file.hasNext()) {
      System.out.println(file.next());
    }
  }

  public void testHelloDegenerate() {

  }


  public void testInc() {

  }

  public void testIncDegenerate() {

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
