package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class ActualParameterSequence extends Ast {
  public ActualParameterSequence(final SourcePosition position) {
    super(position);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ActualParameterSequence)) {
      return false;
    }

    ActualParameterSequence other = (ActualParameterSequence)o;
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "ActualParameterSequence {  }";
  }
}
