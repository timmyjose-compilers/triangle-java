package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class MultipleFormalParameterSequence extends FormalParameterSequence {
  public FormalParameter FP;
  public FormalParameterSequence FPS;

  public MultipleFormalParameterSequence(final FormalParameter FP, final FormalParameterSequence FPS, final SourcePosition position) {
    super(position);
    this.FP = FP;
    this.FPS = FPS;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof MultipleFormalParameterSequence)) {
      return false;
    }

    MultipleFormalParameterSequence other = (MultipleFormalParameterSequence)o;
    return this.FP.equals(other.FP) && this.FPS.equals(other.FPS);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "MultipleFormalParameterSequence { FP = " + this.FP + ", FPS = " + this.FPS + ", position = " + this.position + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
