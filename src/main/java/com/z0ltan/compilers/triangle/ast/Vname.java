package com.z0ltan.compilers.triangle.ast;

import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public abstract class Vname extends Ast {
  public Vname(final SourcePosition position) {
    super(position);
  }
}
