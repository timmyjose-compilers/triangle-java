package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class VarActualParameter extends ActualParameter {
  public Vname V;

  public VarActualParameter(final Vname V, final SourcePosition position) {
    super(position);
    this.V = V;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof VarActualParameter)) {
      return false;
    }

    VarActualParameter other = (VarActualParameter)o;
    return this.V.equals(other.V);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "VarActualParameter { V = " + this.V + ", position = " + this.position + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
