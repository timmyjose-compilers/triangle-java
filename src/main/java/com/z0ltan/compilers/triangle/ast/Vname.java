package com.z0ltan.compilers.triangle.ast;

import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public abstract class Vname extends Ast {
  public TypeDenoter type;
  public boolean variable;
  public boolean indexed;
  public int offset;

  public Vname(final SourcePosition position) {
    super(position);
    this.offset = 0;
  }
}
