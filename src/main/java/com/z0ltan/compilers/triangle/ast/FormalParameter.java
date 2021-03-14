package com.z0ltan.compilers.triangle.ast;

import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public abstract class FormalParameter extends Declaration {
  public FormalParameter(final SourcePosition position) {
    super(position);
  }
}

