package com.z0ltan.compilers.triangle.encoder;

public class Frame {
  public int level;
  public int size;

  public Frame(final int level, final int size) {
    this.level = level;
    this.size = size;
  }

  public Frame(final Frame frame, final int extraSize) {
    this.level = frame.level;
    this.size = frame.size + extraSize;
  }

  @Override
  public String toString() {
    return "Frame { level = " + this.level + ", size = " + this.size + " }";
  }
}
