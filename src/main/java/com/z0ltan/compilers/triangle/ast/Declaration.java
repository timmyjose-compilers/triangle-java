package com.z0ltan.compilers.triangle.ast;

import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public abstract class Declaration extends Ast {
  public Declaration(final SourcePosition position) {
    super(position);
  }
}
