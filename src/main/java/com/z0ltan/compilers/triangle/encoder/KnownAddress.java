package com.z0ltan.compilers.triangle.encoder;

public class KnownAddress extends RuntimeEntity {
  public EntityAddress address;

  public KnownAddress(int size, int level, int displacement) {
    super(size);
    this.address = new EntityAddress(level, displacement);
  }
}
