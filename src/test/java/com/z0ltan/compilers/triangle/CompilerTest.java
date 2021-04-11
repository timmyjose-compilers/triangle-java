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
  public void testEmptyCommandEot() throws Exception {
    final String sourceFile = "samples/source/emptycommandeot.t";
    final String binaryFile = Files.createTempFile("emptycommandeot", ".tam").toString();
    final String outputFile = "samples/output/emptycommandeot.out";

    final Compiler compiler = new Compiler(sourceFile, binaryFile);
    compiler.compile();

    final String expectedOutput = Files.readString(Paths.get(outputFile));
    final String generatedOutput = tapSystemOut(() -> {
      Interpreter.main(new String[] { binaryFile });
    });

    assertThat(generatedOutput, contentIsSame(expectedOutput));
  }

  public void testEmptyCommandSemicolon() throws Exception {
    final String sourceFile = "samples/source/emptycommandsemicolon.t";
    final String binaryFile = Files.createTempFile("emptycommandsemicolon", ".tam").toString();
    final String outputFile = "samples/output/emptycommandsemicolon.out";

    final Compiler compiler = new Compiler(sourceFile, binaryFile);
    compiler.compile();

    final String expectedOutput = Files.readString(Paths.get(outputFile));
    final String generatedOutput = tapSystemOut(() -> {
      Interpreter.main(new String[] { binaryFile });
    });

    //assertThat(generatedOutput, contentIsSame(expectedOutput));
  }

  public void testHello() throws Exception {
    final String sourceFile = "samples/source/hello.t";
    final String binaryFile = Files.createTempFile("hello", ".tam").toString();
    final String outputFile = "samples/output/hello.out";

    final Compiler compiler = new Compiler(sourceFile, binaryFile);
    compiler.compile();

    final String expectedOutput = Files.readString(Paths.get(outputFile));
    final String generatedOutput = tapSystemOut(() -> {
      Interpreter.main(new String[] { binaryFile });
    });

    //assertThat(generatedOutput, contentIsSame(expectedOutput));
  }

  public void testInc() throws Exception {
    final String sourceFile = "samples/source/inc.t";
    final String binaryFile = Files.createTempFile("inc", ".tam").toString();
    final String outputFile = "samples/output/inc.out";

    final Compiler compiler = new Compiler(sourceFile, binaryFile);
    compiler.compile();

    final String expectedOutput = Files.readString(Paths.get(outputFile));
    final String generatedOutput = tapSystemOut(() -> {
      Interpreter.main(new String[] { binaryFile });
    });

    //assertThat(generatedOutput, contentIsSame(expectedOutput));
  }

  public void testEcho() throws Exception {
    final String sourceFile = "samples/source/echo.t";
    final String binaryFile = Files.createTempFile("echo", ".tam").toString();
    final String outputFile = "samples/output/echo.out";

    final Compiler compiler = new Compiler(sourceFile, binaryFile);
    compiler.compile();

    final String expectedOutput = Files.readString(Paths.get(outputFile));
    final String generatedOutput = tapSystemOut(() -> {
      Interpreter.main(new String[] { binaryFile });
    });

    //assertThat(generatedOutput, contentIsSame(expectedOutput));
  }

  public void testOdd() throws Exception {
    final String sourceFile = "samples/source/odd.t";
    final String binaryFile = Files.createTempFile("odd", ".tam").toString();
    final String outputFile = "samples/output/odd.out";

    final Compiler compiler = new Compiler(sourceFile, binaryFile);
    compiler.compile();

    final String expectedOutput = Files.readString(Paths.get(outputFile));
    final String generatedOutput = tapSystemOut(() -> {
      Interpreter.main(new String[] { binaryFile });
    });

    //assertThat(generatedOutput, contentIsSame(expectedOutput));
  }

  public void testSumProc() throws Exception {
    final String sourceFile = "samples/source/sum_proc.t";
    final String binaryFile = Files.createTempFile("sum_proc", ".tam").toString();
    final String outputFile = "samples/output/sum_proc.out";

    final Compiler compiler = new Compiler(sourceFile, binaryFile);
    compiler.compile();

    final String expectedOutput = Files.readString(Paths.get(outputFile));
    final String generatedOutput = tapSystemOut(() -> {
      Interpreter.main(new String[] { binaryFile });
    });

    //assertThat(generatedOutput, contentIsSame(expectedOutput));
  }

  public void testPower() throws Exception {
    final String sourceFile = "samples/source/power.t";
    final String binaryFile = Files.createTempFile("power", ".tam").toString();
    final String outputFile = "samples/output/power.out";

    final Compiler compiler = new Compiler(sourceFile, binaryFile);
    compiler.compile();

    final String expectedOutput = Files.readString(Paths.get(outputFile));
    final String generatedOutput = tapSystemOut(() -> {
      Interpreter.main(new String[] { binaryFile });
    });

    //assertThat(generatedOutput, contentIsSame(expectedOutput));
  }

  public void testFactorial() throws Exception {
    final String sourceFile = "samples/source/factorial.t";
    final String binaryFile = Files.createTempFile("factorial", ".tam").toString();
    final String outputFile = "samples/output/factorial.out";

    final Compiler compiler = new Compiler(sourceFile, binaryFile);
    compiler.compile();

    final String expectedOutput = Files.readString(Paths.get(outputFile));
    final String generatedOutput = tapSystemOut(() -> {
      Interpreter.main(new String[] { binaryFile });
    });

    //assertThat(generatedOutput, contentIsSame(expectedOutput));
  }

  public void testRecord() throws Exception {
    final String sourceFile = "samples/source/record.t";
    final String binaryFile = Files.createTempFile("record", ".tam").toString();
    final String outputFile = "samples/output/record.out";

    final Compiler compiler = new Compiler(sourceFile, binaryFile);
    compiler.compile();

    final String expectedOutput = Files.readString(Paths.get(outputFile));
    final String generatedOutput = tapSystemOut(() -> {
      Interpreter.main(new String[] { binaryFile });
    });

    //assertThat(generatedOutput, contentIsSame(expectedOutput));
  }

  public void testLeapYear() throws Exception {
    final String sourceFile = "samples/source/leapyear.t";
    final String binaryFile = Files.createTempFile("leapyear", ".tam").toString();
    final String outputFile = "samples/output/leapyear.out";

    final Compiler compiler = new Compiler(sourceFile, binaryFile);
    compiler.compile();

    final String expectedOutput = Files.readString(Paths.get(outputFile));
    final String generatedOutput = tapSystemOut(() -> {
      Interpreter.main(new String[] { binaryFile });
    });

    //assertThat(generatedOutput, contentIsSame(expectedOutput));
  }

  public void testPrintArray() throws Exception {
    final String sourceFile = "samples/source/print_array.t";
    final String binaryFile = Files.createTempFile("print_array", ".tam").toString();
    final String outputFile = "samples/output/print_array.out";

    final Compiler compiler = new Compiler(sourceFile, binaryFile);
    compiler.compile();

    final String expectedOutput = Files.readString(Paths.get(outputFile));
    final String generatedOutput = tapSystemOut(() -> {
      Interpreter.main(new String[] { binaryFile });
    });

    //assertThat(generatedOutput, contentIsSame(expectedOutput));
  }

  public void testDate() throws Exception {
    final String sourceFile = "samples/source/date.t";
    final String binaryFile = Files.createTempFile("date", ".tam").toString();
    final String outputFile = "samples/output/date.out";

    final Compiler compiler = new Compiler(sourceFile, binaryFile);
    compiler.compile();

    final String expectedOutput = Files.readString(Paths.get(outputFile));
    final String generatedOutput = tapSystemOut(() -> {
      Interpreter.main(new String[] { binaryFile });
    });

    //assertThat(generatedOutput, contentIsSame(expectedOutput));
  }

  public void testString() throws Exception {
    final String sourceFile = "samples/source/string.t";
    final String binaryFile = Files.createTempFile("string", ".tam").toString();
    final String outputFile = "samples/output/string.out";

    final Compiler compiler = new Compiler(sourceFile, binaryFile);
    compiler.compile();

    final String expectedOutput = Files.readString(Paths.get(outputFile));
    final String generatedOutput = tapSystemOut(() -> {
      Interpreter.main(new String[] { binaryFile });
    });

    //assertThat(generatedOutput, contentIsSame(expectedOutput));
  }

  public void testIteratively() throws Exception {
    final String sourceFile = "samples/source/iteratively.t";
    final String binaryFile = Files.createTempFile("iteratively", ".tam").toString();
    final String outputFile = "samples/output/iteratively.out";

    final Compiler compiler = new Compiler(sourceFile, binaryFile);
    compiler.compile();

    final String expectedOutput = Files.readString(Paths.get(outputFile));
    final String generatedOutput = tapSystemOut(() -> {
      Interpreter.main(new String[] { binaryFile });
    });

    //assertThat(generatedOutput, contentIsSame(expectedOutput));
  }

  public void testLine() throws Exception {
    final String sourceFile = "samples/source/line.t";
    final String binaryFile = Files.createTempFile("line", ".tam").toString();
    final String outputFile = "samples/output/line.out";

    final Compiler compiler = new Compiler(sourceFile, binaryFile);
    compiler.compile();

    final String expectedOutput = Files.readString(Paths.get(outputFile));
    final String generatedOutput = tapSystemOut(() -> {
      Interpreter.main(new String[] { binaryFile });
    });

    //assertThat(generatedOutput, contentIsSame(expectedOutput));
  }

  public void testDates() throws Exception {
    final String sourceFile = "samples/source/dates.t";
    final String binaryFile = Files.createTempFile("dates", ".tam").toString();
    final String outputFile = "samples/output/dates.out";

    final Compiler compiler = new Compiler(sourceFile, binaryFile);
    compiler.compile();

    final String expectedOutput = Files.readString(Paths.get(outputFile));
    final String generatedOutput = tapSystemOut(() -> {
      Interpreter.main(new String[] { binaryFile });
    });

    //assertThat(generatedOutput, contentIsSame(expectedOutput));
  }

  public void testMonthsOfYear() throws Exception {
    final String sourceFile = "samples/source/monthsofyear.t";
    final String binaryFile = Files.createTempFile("monthsofyear", ".tam").toString();
    final String outputFile = "samples/output/monthsofyear.out";

    final Compiler compiler = new Compiler(sourceFile, binaryFile);
    compiler.compile();

    final String expectedOutput = Files.readString(Paths.get(outputFile));
    final String generatedOutput = tapSystemOut(() -> {
      Interpreter.main(new String[] { binaryFile });
    });

    //assertThat(generatedOutput, contentIsSame(expectedOutput));
  }

  public void testCapitalise() throws Exception {
    final String sourceFile = "samples/source/capitalise.t";
    final String binaryFile = Files.createTempFile("capitalise", ".tam").toString();
    final String outputFile = "samples/output/capitalise.out";

    final Compiler compiler = new Compiler(sourceFile, binaryFile);
    compiler.compile();

    final String expectedOutput = Files.readString(Paths.get(outputFile));
    final String generatedOutput = tapSystemOut(() -> {
      Interpreter.main(new String[] { binaryFile });
    });

    //assertThat(generatedOutput, contentIsSame(expectedOutput));
  }

  public void testFreq() throws Exception {
    final String sourceFile = "samples/source/freq.t";
    final String binaryFile = Files.createTempFile("freq", ".tam").toString();
    final String outputFile = "samples/output/freq.out";

    final Compiler compiler = new Compiler(sourceFile, binaryFile);
    compiler.compile();

    final String expectedOutput = Files.readString(Paths.get(outputFile));
    final String generatedOutput = tapSystemOut(() -> {
      Interpreter.main(new String[] { binaryFile });
    });

    //assertThat(generatedOutput, contentIsSame(expectedOutput));
  }

  public void testInsertionSort() throws Exception {
    final String sourceFile = "samples/source/insertion_sort.t";
    final String binaryFile = Files.createTempFile("insertion_sort", ".tam").toString();
    final String outputFile = "samples/output/insertion_sort.out";

    final Compiler compiler = new Compiler(sourceFile, binaryFile);
    compiler.compile();

    final String expectedOutput = Files.readString(Paths.get(outputFile));
    final String generatedOutput = tapSystemOut(() -> {
      Interpreter.main(new String[] { binaryFile });
    });

    //assertThat(generatedOutput, contentIsSame(expectedOutput));
  }

  public void testRationals() throws Exception {
    final String sourceFile = "samples/source/rationals.t";
    final String binaryFile = Files.createTempFile("rationals", ".tam").toString();
    final String outputFile = "samples/output/rationals.out";

    final Compiler compiler = new Compiler(sourceFile, binaryFile);
    compiler.compile();

    final String expectedOutput = Files.readString(Paths.get(outputFile));
    final String generatedOutput = tapSystemOut(() -> {
      Interpreter.main(new String[] { binaryFile });
    });

    //assertThat(generatedOutput, contentIsSame(expectedOutput));
  }

  public void testNestedRecords() throws Exception {
    final String sourceFile = "samples/source/nestedrecords.t";
    final String binaryFile = Files.createTempFile("nestedrecords", ".tam").toString();
    final String outputFile = "samples/output/nestedrecords.out";

    final Compiler compiler = new Compiler(sourceFile, binaryFile);
    compiler.compile();

    final String expectedOutput = Files.readString(Paths.get(outputFile));
    final String generatedOutput = tapSystemOut(() -> {
      Interpreter.main(new String[] { binaryFile });
    });

    //assertThat(generatedOutput, contentIsSame(expectedOutput));
  }

  public void testNestedArrays() throws Exception {
    final String sourceFile = "samples/source/nestedarrays.t";
    final String binaryFile = Files.createTempFile("nestedarrays", ".tam").toString();
    final String outputFile = "samples/output/nestedarrays.out";

    final Compiler compiler = new Compiler(sourceFile, binaryFile);
    compiler.compile();

    final String expectedOutput = Files.readString(Paths.get(outputFile));
    final String generatedOutput = tapSystemOut(() -> {
      Interpreter.main(new String[] { binaryFile });
    });

    //assertThat(generatedOutput, contentIsSame(expectedOutput));
  }
}
