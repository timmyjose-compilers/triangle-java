package com.z0ltan.compilers.triangle.ast;

import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public abstract class Command extends Ast {
  public Command(final SourcePosition position) {
    super(position);
  }
}
