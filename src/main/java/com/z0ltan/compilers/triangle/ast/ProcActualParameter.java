package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class ProcActualParameter extends ActualParameter {
  public Identifier I;
  public ActualParameterSequence APS;

  public ProcActualParameter(final Identifier I, final ActualParameterSequence APS, final SourcePosition position) {
    super(position);
    this.I = I;
    this.APS = APS;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ProcActualParameter)) {
      return false;
    }

    ProcActualParameter other = (ProcActualParameter)o;
    return this.I.equals(other.I) && this.APS.equals(other.APS);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "ProcActualParameter { I = " + this.I + ", APS = " + this.APS + ", position = " + this.position + " }";
  }
}
