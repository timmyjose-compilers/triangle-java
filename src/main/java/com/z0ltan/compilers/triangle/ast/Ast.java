package com.z0ltan.compilers.triangle.ast;

import com.z0ltan.compilers.triangle.scanner.SourcePosition;
import com.z0ltan.compilers.triangle.encoder.RuntimeEntity;

public abstract class Ast {
  public SourcePosition position;
  public RuntimeEntity entity;

  public Ast(final SourcePosition position) {
    this.position = position;
    this.entity = null;
  }

  protected abstract Object accept(final Visitor visitor, final Object arg);
}
