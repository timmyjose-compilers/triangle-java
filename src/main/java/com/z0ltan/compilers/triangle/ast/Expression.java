package com.z0ltan.compilers.triangle.ast;

import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public abstract class Expression extends Ast {
  public Expression(final SourcePosition position) {
    super(position);
  }

  public TypeDenoter type;
}
