package com.z0ltan.compilers.triangle.encoder;

public class KnownRoutine extends RuntimeEntity {
  public EntityAddress address;

  public KnownRoutine(final int size, final int level, final int displacement) {
    super(size);
    this.address = new EntityAddress(level, displacement);
  }
}
