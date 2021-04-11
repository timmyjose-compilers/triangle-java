package com.z0ltan.compilers.triangle.encoder;

public class Field extends RuntimeEntity {
  public int fieldOffset;

  public Field(final int size, final int fieldOffset) {
    super(size);
    this.fieldOffset = fieldOffset;
  }
}
