package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public abstract class ActualParameter extends Ast {
  public ActualParameter(final SourcePosition position) {
    super(position);
  }
}
