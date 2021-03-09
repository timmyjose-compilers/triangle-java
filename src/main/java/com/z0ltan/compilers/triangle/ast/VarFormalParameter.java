package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class VarFormalParameter extends FormalParameter {
  public Identifier I;
  public TypeDenoter T;

  public VarFormalParameter(final Identifier I, final TypeDenoter T, final SourcePosition position) {
    super(position);
    this.I = I;
    this.T = T;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof VarFormalParameter)) {
      return false;
    }

    VarFormalParameter other = (VarFormalParameter)o;
    return this.I.equals(other.I) && this.T.equals(other.T);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "VarFormalParameter { I = "+ this.I + ", T = " + this.T + ", position = " + this.position + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
