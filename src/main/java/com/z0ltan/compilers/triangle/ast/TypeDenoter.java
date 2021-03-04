package com.z0ltan.compilers.triangle.ast;

import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public abstract class TypeDenoter extends Ast {
  public TypeDenoter(final SourcePosition position) {
    super(position);
  }
}
