package com.z0ltan.compilers.triangle.scanner;

public class SourcePosition {
  static public class Position {
    public int line;
    public int column;

    public Position() {
      this.line = -1;
      this.column = -1;
    }

    public Position(int line, int column) {
      this.line = line;
      this.column = column;
    }

    @Override
    public String toString() {
      return "Position { line = " + this.line + ", column = " + this.column + " }";
    }
  }

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

  public static SourcePosition dummyPosition() {
    return new SourcePosition();
  }

  @Override
  public String toString() {
    return "SourcePosition { start = " + this.start + ", finish = " + this.finish + " }";
  }
}

