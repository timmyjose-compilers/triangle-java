package com.z0ltan.compilers.triangle.scanner;

public class SourcePosition {
  public Position start, end;

  public SourcePosition() {
    this.start = null;
    this.end = null;
  }
}

class Position {
  public int line, column;

  public Position(int line, int column) {
    this.line = line;
    this.column = column;
  }
}
