package com.z0ltan.compilers.triangle.encoder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.z0ltan.compilers.triangle.scanner.Scanner;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;
import com.z0ltan.compilers.triangle.parser.Parser;
import com.z0ltan.tam.Interpreter;
import com.z0ltan.compilers.triangle.ast.Program;

public class EncoderTest {
  public void testEmptyCommandEot() {
    String filename = "samples/source/emptycommandeot.t";
    final Interpreter interpreter = new Interpreter();
  }

  public void testEmptyCommandSemicolon() {
    String filename = "samples/source/emptycommandsemicolon.t";
  }

  public void testHello() {
    String filename = "samples/source/hello.t";
  }

  public void testInc() {
    String filename = "samples/source/inc.t";
  }

  public void testEcho() {
    String filename = "samples/source/echo.t";
  }

  public void testOdd() {
    String filename = "samples/source/odd.t";
  }

  public void testSumProc() {
    String filename = "samples/source/sum_proc.t";
  }

  public void testPower() {
    String filename = "samples/source/power.t";
  }

  public void testFactorial() {
    String filename = "samples/source/factorial.t";
  }

  public void testRecord() {
    String filename = "samples/source/record.t";
  }

  public void testLeapYear() {
    String filename = "samples/source/leapyear.t";
  }

  public void testPrintArray() {
    String filename = "samples/source/print_array.t";
  }

  public void testDate() {
    String filename = "samples/source/date.t";
  }

  public void testString() {
    String filename = "samples/source/string.t";
  }

  public void testIteratively() {
    String filename = "samples/source/iteratively.t";
  }

  public void testReverseLine() {
    String filename = "samples/source/reverse_line.t";
  }

  public void testLine() {
    String filename = "samples/source/line.t";
  }

  public void testDates() {
    String filename = "samples/source/dates.t";
  }

  public void testMonthsOfYear() {
    String filename = "samples/source/monthsofyear.t";
  }

  public void testCapitalise() {
    String filename = "samples/source/capitalise.t";
  }

  public void testFreq() {
    String filename = "samples/source/freq.t";
  }

  public void testInsertionSort() {
    String filename = "samples/source/insertion_sort.t";
  }

  public void testRationals() {
    String filename = "samples/source/rationals.t";
  }

  public void testNestedRecords() {
    String filename = "samples/source/nestedrecords.t";
  }

  public void testNestedArrays() {
    String filename = "samples/source/nestedarrays.t";
  }
}

