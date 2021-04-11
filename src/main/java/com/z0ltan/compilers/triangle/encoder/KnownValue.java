package com.z0ltan.compilers.triangle.encoder;

public class KnownValue extends RuntimeEntity {
  public int value;

  public KnownValue(final int size, final int value) {
    super(size);
    this.value = value;
  }
}
