package com.z0ltan.compilers.triangle;

import com.z0ltan.compilers.triangle.scanner.Scanner;
import com.z0ltan.compilers.triangle.parser.Parser;
import com.z0ltan.compilers.triangle.checker.Checker;
import com.z0ltan.compilers.triangle.encoder.Encoder;
import com.z0ltan.compilers.triangle.ast.Program;

public class Compiler {
  private String sourceFile;
  private String binaryFile;

  private static final String DEFAULT_BINARY_FILE = "obj.tam";

  public Compiler(final String sourceFile, final String binaryFile) {
    this.sourceFile = sourceFile;
    this.binaryFile = binaryFile;
  }

  public void compile() {
    final Parser parser = new Parser(new Scanner(this.sourceFile));
    final Program ast = parser.parseProgram();

    final Checker checker = new Checker();
    checker.check(ast);

    final Encoder encoder = new Encoder();
    encoder.encodeRun(ast);
    encoder.saveBinary(this.binaryFile);
  }

  public static void main(String[] args) {
    if (args == null || args.length == 0 || args.length > 2) {
      usage();
    }

    Compiler compiler = null;
    if (args.length == 2) {
      compiler = new Compiler(args[0], args[1]);
    } else {
      compiler = new Compiler(args[0], DEFAULT_BINARY_FILE);
    }

    compiler.compile();
  }

  private static void usage() {
    System.err.println("Usage: tc SOURCE-FILE [OUTPUT-FILE]");
    System.exit(1);
  }
}
