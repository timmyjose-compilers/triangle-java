package com.z0ltan.compilers.triangle.scanner;

public class SourcePosition {
  public Position start;
  public Position finish;

  public SourcePosition() {
    this.start = new Position();
    this.finish = new Position();
  }

  public SourcePosition(int startLine, int startColumn, int finishLine, int finishColumn) {
    this.start = new Position(startLine, startColumn);
    this.finish = new Position(finishLine, finishColumn);
  }
}

class Position {
  int line;
  int column;

  public Position() {
    this.line = -1;
    this.column = -1;
  }

  public Position(int line, int column) {
    this.line = line;
    this.column = column;
  }
}
