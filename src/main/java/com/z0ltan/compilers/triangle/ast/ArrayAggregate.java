package com.z0ltan.compilers.triangle.ast;

import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public abstract class ArrayAggregate extends Ast {
  public TypeDenoter elemType;
  public int elemCount;

  public ArrayAggregate(final SourcePosition position) {
    super(position);
    this.elemCount = 0;
  }
}
