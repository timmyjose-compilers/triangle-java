package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class ProcFormalParameter extends FormalParameter {
  public Identifier I;
  public FormalParameterSequence FPS;

  public ProcFormalParameter(final Identifier I, final FormalParameterSequence FPS, final SourcePosition position) {
    super(position);
    this.I = I;
    this.FPS = FPS;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ProcFormalParameter)) {
      return false;
    }

    ProcFormalParameter other = (ProcFormalParameter)o;
    return this.FPS.equals(other.FPS);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "ProcFormalParameter { I = " + this.I + ", FPS = " + this.FPS + ", position = " + this.position + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
