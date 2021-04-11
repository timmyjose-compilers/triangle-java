package com.z0ltan.compilers.triangle.encoder;

public class EqualityRoutine extends RuntimeEntity {
  public EntityAddress address;

  public EqualityRoutine(final int size, final int level, final int displacement) {
    super(size);
    this.address = new EntityAddress(level, displacement);
  }
}
