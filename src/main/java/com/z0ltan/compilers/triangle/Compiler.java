package com.z0ltan.compilers.triangle;

import com.z0ltan.compilers.triangle.scanner.Scanner;
import com.z0ltan.compilers.triangle.parser.Parser;
import com.z0ltan.compilers.triangle.checker.Checker;
import com.z0ltan.compilers.triangle.encoder.Encoder;
import com.z0ltan.compilers.triangle.ast.Program;

public class Compiler {
  private String sourceFile;
  private String binaryFile;

  public Compiler(final String sourceFile, final String objectName) {
    this.sourceFile = sourceFile;
    this.binaryFile = binaryFile;
  }

  public static void main(String[] args) {
    System.out.println("Triangle Compiler v1.0");
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
}
