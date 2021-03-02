package com.z0ltan.compilers.triangle.scanner;

public class Char {
  char c;
  int line, column;

  public Char(char c, int line, int column) {
    this.c = c;
    this.line = line;
    this.column = column;
  }

  @Override
  public String toString() {
    return "Char { c = " + this.c + ", line = " + this.line + ", column = " + this.column + " }";
  }
}

