package com.z0ltan.compilers.triangle;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.z0ltan.tam.Interpreter;
import com.z0ltan.compilers.triangle.scanner.Scanner;
import com.z0ltan.compilers.triangle.parser.Parser;
import com.z0ltan.compilers.triangle.checker.Checker;
import com.z0ltan.compilers.triangle.encoder.Encoder;

import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.hamcrest.MatcherAssert.assertThat;
import static com.z0ltan.compilers.triangle.matchers.ContentMatcher.contentIsSame;

public class CompilerTest {
  private void compareContents(final String sourceFile, final String binaryFile, final String outputFile) {
    try {
      final String expectedOutput = Files.readString(Paths.get(outputFile));
      final String generatedOutput = tapSystemOut(() -> {
        Compiler.main(new String[] { sourceFile, binaryFile });
        Interpreter.main(new String[] { binaryFile });
        Files.deleteIfExists(Paths.get(binaryFile));
      });

      assertThat(generatedOutput, contentIsSame(expectedOutput));
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  public void testEmptyCommandEot() throws Exception {
    withTextFromSystemIn("")
      .execute(() -> {
        final String sourceFile = "samples/source/emptycommandeot.t";
        final String binaryFile = "emptycommandeot.tam";
        final String outputFile = "samples/output/emptycommandeot.out";

        compareContents(sourceFile, binaryFile, outputFile);
      });
  }

  public void testEmptyCommandSemicolon() throws Exception {
    withTextFromSystemIn("")
      .execute(() -> {
        final String sourceFile = "samples/source/emptycommandsemicolon.t";
        final String binaryFile = "emptycommandsemicolon.tam";
        final String outputFile = "samples/output/emptycommandsemicolon.out";

        compareContents(sourceFile, binaryFile, outputFile);
      });
  }

  public void testHello() throws Exception {
    withTextFromSystemIn("")
      .execute(() -> {
        final String sourceFile = "samples/source/hello.t";
        final String binaryFile = "hello.tam";
        final String outputFile = "samples/output/hello.out";

        compareContents(sourceFile, binaryFile, outputFile);
      });
  }

  public void testInc() throws Exception {
    withTextFromSystemIn("99")
      .execute(() -> {
        final String sourceFile = "samples/source/inc.t";
        final String binaryFile = "inc.tam";
        final String outputFile = "samples/output/inc.out";

        compareContents(sourceFile, binaryFile, outputFile);
      });
  }

  public void testEcho() throws Exception {
    withTextFromSystemIn("Hello, world! Nice to meet you!")
      .execute(() -> {
        final String sourceFile = "samples/source/echo.t";
        final String binaryFile = "echo.tam";
        final String outputFile = "samples/output/echo.out";

        compareContents(sourceFile, binaryFile, outputFile);
      });
  }

  public void testOdd() throws Exception {
    withTextFromSystemIn("11")
      .execute(() -> {
        final String sourceFile = "samples/source/odd.t";
        final String binaryFile = "odd.tam";
        final String outputFile = "samples/output/odd.out";

        compareContents(sourceFile, binaryFile, outputFile);
      });
  }

  public void testSumProc() throws Exception {
    withTextFromSystemIn("12\n13\n")
      .execute(() -> {
        final String sourceFile = "samples/source/sum_proc.t";
        final String binaryFile = "sum_proc.tam";
        final String outputFile = "samples/output/sum_proc.out";

        compareContents(sourceFile, binaryFile, outputFile);
      });
  }

  public void testPower() throws Exception {
    withTextFromSystemIn("11\n3\n")
      .execute(() -> {
        final String sourceFile = "samples/source/power.t";
        final String binaryFile = "power.tam";
        final String outputFile = "samples/output/power.out";

        compareContents(sourceFile, binaryFile, outputFile);
      });
  }

  public void testFactorial() throws Exception {
    withTextFromSystemIn("5\n")
      .execute(() -> {
        final String sourceFile = "samples/source/factorial.t";
        final String binaryFile = "factorial.tam";
        final String outputFile = "samples/output/factorial.out";

        compareContents(sourceFile, binaryFile, outputFile);
      });
  }

  public void testRecord() throws Exception {
    withTextFromSystemIn("")
      .execute(() -> {
        final String sourceFile = "samples/source/record.t";
        final String binaryFile = "record.tam";
        final String outputFile = "samples/output/record.out";

        compareContents(sourceFile, binaryFile, outputFile);
      });
  }

  public void testLeapYear() throws Exception {
    withTextFromSystemIn("2020\n11\n01\n")
      .execute(() -> {
        final String sourceFile = "samples/source/leapyear.t";
        final String binaryFile = "leapyear.tam";
        final String outputFile = "samples/output/leapyear.out";

        compareContents(sourceFile, binaryFile, outputFile);
      });
  }

  public void testPrintArray() throws Exception {
    withTextFromSystemIn("")
      .execute(() -> {
        final String sourceFile = "samples/source/print_array.t";
        final String binaryFile = "print_array.tam";
        final String outputFile = "samples/output/print_array.out";

        compareContents(sourceFile, binaryFile, outputFile);
      });
  }

  public void testDate() throws Exception {
    withTextFromSystemIn("2021\n04\n06\n")
      .execute(() -> {
        final String sourceFile = "samples/source/date.t";
        final String binaryFile = "date.tam";
        final String outputFile = "samples/output/date.out";

        compareContents(sourceFile, binaryFile, outputFile);
      });
  }

  public void testString() throws Exception {
    withTextFromSystemIn("Hello, world!\n")
      .execute(() -> {
        final String sourceFile = "samples/source/string.t";
        final String binaryFile = "string.tam";
        final String outputFile = "samples/output/string.out";

        compareContents(sourceFile, binaryFile, outputFile);
      });
  }

  public void testIteratively() throws Exception {
    withTextFromSystemIn("1\n2\n11\n22\n9\n0\n78\n23\n45\n233\n")
      .execute(() -> {
        final String sourceFile = "samples/source/iteratively.t";
        final String binaryFile = "iteratively.tam";
        final String outputFile = "samples/output/iteratively.out";

        compareContents(sourceFile, binaryFile, outputFile);
      });
  }

  public void testLine() throws Exception {
    withTextFromSystemIn("hello\n")
      .execute(() -> {
        final String sourceFile = "samples/source/line.t";
        final String binaryFile = "line.tam";
        final String outputFile = "samples/output/line.out";

        compareContents(sourceFile, binaryFile, outputFile);
      });
  }

  public void testDates() throws Exception {
    withTextFromSystemIn("04\n04\n11\n22\n01\n28\n12\n31\n")
      .execute(() -> {
        final String sourceFile = "samples/source/dates.t";
        final String binaryFile = "dates.tam";
        final String outputFile = "samples/output/dates.out";

        compareContents(sourceFile, binaryFile, outputFile);
      });
  }

  public void testMonthsOfYear() throws Exception {
    withTextFromSystemIn("2021\n04\n06\n")
      .execute(() -> {
        final String sourceFile = "samples/source/monthsofyear.t";
        final String binaryFile = "monthsofyear.tam";
        final String outputFile = "samples/output/monthsofyear.out";

        compareContents(sourceFile, binaryFile, outputFile);
      });
  }

  public void testCapitalise() throws Exception {
    withTextFromSystemIn("Hello\n")
      .execute(() -> {
        final String sourceFile = "samples/source/capitalise.t";
        final String binaryFile = "capitalise.tam";
        final String outputFile = "samples/output/capitalise.out";

        compareContents(sourceFile, binaryFile, outputFile);
      });
  }

  public void testFreq() throws Exception {
    withTextFromSystemIn("hello world\n")
      .execute(() -> {
        final String sourceFile = "samples/source/freq.t";
        final String binaryFile = "freq.tam";
        final String outputFile = "samples/output/freq.out";

        compareContents(sourceFile, binaryFile, outputFile);
      });
  }

  public void testInsertionSort() throws Exception {
    withTextFromSystemIn("1\n2\n3\n5\n4\n34\n2\n1\n2\n3\n")
      .execute(() -> {
        final String sourceFile = "samples/source/insertion_sort.t";
        final String binaryFile = "insertion_sort.tam";
        final String outputFile = "samples/output/insertion_sort.out";

        compareContents(sourceFile, binaryFile, outputFile);
      });
  }

  public void testRationals() throws Exception {
    withTextFromSystemIn("2\n3\n3\n4\n")
      .execute(() -> {
        final String sourceFile = "samples/source/rationals.t";
        final String binaryFile = "rationals.tam";
        final String outputFile = "samples/output/rationals.out";

        compareContents(sourceFile, binaryFile, outputFile);
      });
  }

  public void testNestedRecords() throws Exception {
    withTextFromSystemIn("")
      .execute(() -> {
        final String sourceFile = "samples/source/nestedrecords.t";
        final String binaryFile = "nestedrecords.tam";
        final String outputFile = "samples/output/nestedrecords.out";

        compareContents(sourceFile, binaryFile, outputFile);
      });
  }

  public void testNestedArrays() throws Exception {
    withTextFromSystemIn("1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n12\n")
      .execute(() -> {
        final String sourceFile = "samples/source/nestedarrays.t";
        final String binaryFile = "nestedarrays.tam";
        final String outputFile = "samples/output/nestedarrays.out";

        compareContents(sourceFile, binaryFile, outputFile);
      });
  }
}
