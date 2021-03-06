package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class FuncActualParameter extends ActualParameter {
  public Identifier I;

  public FuncActualParameter(final Identifier I, final SourcePosition position) {
    super(position);
    this.I = I;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof FuncActualParameter)) {
      return false;
    }

    FuncActualParameter other = (FuncActualParameter)o;
    return this.I.equals(other.I);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "FuncActualParameter { I = " + this.I + ", position = " + this.position + " }";
  }
}
