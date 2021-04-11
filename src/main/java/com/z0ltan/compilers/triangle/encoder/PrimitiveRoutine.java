package com.z0ltan.compilers.triangle.encoder;

public class PrimitiveRoutine extends RuntimeEntity {
  public int primOffset;

  public PrimitiveRoutine(final int size, final int primOffset) {
    super(size);
    this.primOffset = primOffset;
  }
}
