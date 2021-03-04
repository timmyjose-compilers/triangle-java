package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class EmptyActualParameterSequence extends ActualParameterSequence {
  public EmptyActualParameterSequence(final SourcePosition position) {
    super(position);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ActualParameterSequence)) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "EmptyActualParameterSequence { position = " + this.position + " }";
  }
}
