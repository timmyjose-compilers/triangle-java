package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class FuncFormalParameter extends FormalParameter {
  public Identifier I;
  public FormalParameterSequence FPS;
  public TypeDenoter T;

  public FuncFormalParameter(final Identifier I, final FormalParameterSequence FPS, final TypeDenoter T, final SourcePosition position) {
    super(position);
    this.I = I;
    this.FPS = FPS;
    this.T = T;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof FuncFormalParameter)) {
      return false;
    }

    FuncFormalParameter other = (FuncFormalParameter)o;
    return this.I.equals(other.I) && this.FPS.equals(other.FPS) && this.T.equals(other.T);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "FuncFormalParameter { I = " + this.I + ", FPS = " + this.FPS + ", T = " + this.T + ", position = " + this.position + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
