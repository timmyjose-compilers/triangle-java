package com.z0ltan.compilers.triangle.ast;

import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public abstract class Terminal extends Ast {
  public String spelling;

  public Terminal(final String spelling, final SourcePosition position) {
    super(position);
    this.spelling = spelling;
  }
}
