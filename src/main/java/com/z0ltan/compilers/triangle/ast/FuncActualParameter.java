package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class FuncActualParameter extends ActualParameter {
  public Identifier I;
  public ActualParameterSequence APS;
  public TypeDenoter T;

  public FuncActualParameter(final Identifier I, final ActualParameterSequence APS, final TypeDenoter T, final SourcePosition position) {
    super(position);
    this.I = I;
    this.APS = APS;
    this.T = T;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof FuncActualParameter)) {
      return false;
    }

    FuncActualParameter other = (FuncActualParameter)o;
    return this.I.equals(other.I) && this.APS.equals(other.APS) && this.T.equals(other.T);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "FuncActualParameter { I = " + this.I + ", APS = " + this.APS + ", T = " + this.T + ", position = " + this.position + " }";
  }
}
