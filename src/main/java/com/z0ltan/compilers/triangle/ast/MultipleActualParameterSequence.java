package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class MultipleActualParameterSequence extends ActualParameterSequence {
  public ActualParameter AP;
  public ActualParameterSequence APS;

  public MultipleActualParameterSequence(final ActualParameter AP, final ActualParameterSequence APS, final SourcePosition position) {
    super(position);
    this.AP = AP;
    this.APS = APS;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof MultipleActualParameterSequence)) {
      return false;
    }

    MultipleActualParameterSequence other = (MultipleActualParameterSequence)o;
    return this.AP.equals(other.AP) && this.APS.equals(other.APS);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "MultipleActualParameterSequence { AP = " + this.AP + ", APS = " + this.APS + ", position = " + this.position + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
