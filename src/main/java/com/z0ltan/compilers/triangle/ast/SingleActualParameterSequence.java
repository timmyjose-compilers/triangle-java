package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class SingleActualParameterSequence extends ActualParameterSequence {
  public ActualParameter AP;

  public SingleActualParameterSequence(final ActualParameter AP, final SourcePosition position) {
    super(position);
    this.AP = AP;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof SingleActualParameterSequence)) {
      return false;
    }

    SingleActualParameterSequence other = (SingleActualParameterSequence)o;
    return this.AP.equals(other.AP);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "SingleActualParameterSequence { AP = " + this.AP + ", position = " + this.position + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
