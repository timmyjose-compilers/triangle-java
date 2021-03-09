package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class SingleFormalParameterSequence extends FormalParameterSequence {
  public FormalParameter FP;

  public SingleFormalParameterSequence(final FormalParameter FP, final SourcePosition position) {
    super(position);
    this.FP = FP;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof SingleFormalParameterSequence)) {
      return false;
    }

    SingleFormalParameterSequence other = (SingleFormalParameterSequence)o;
    return this.FP.equals(other.FP);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "SingleFormalParameterSequence { FP = " + this.FP + ", position = " + this.position + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
