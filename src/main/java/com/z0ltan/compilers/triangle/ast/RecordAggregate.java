package com.z0ltan.compilers.triangle.ast;

import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public abstract class RecordAggregate extends Ast {
  public TypeDenoter type;

  public RecordAggregate(final SourcePosition position) {
    super(position);
  }
}
