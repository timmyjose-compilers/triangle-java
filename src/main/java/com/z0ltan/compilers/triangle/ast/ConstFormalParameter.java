package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class ConstFormalParameter extends FormalParameter {
  public Identifier I;
  public TypeDenoter T;

  public ConstFormalParameter(final Identifier I, final TypeDenoter T, final SourcePosition position) {
    super(position);
    this.I = I;
    this.T = T;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ConstFormalParameter)) {
      return false;
    }

    ConstFormalParameter other = (ConstFormalParameter)o;
    return this.I.equals(other.I) && this.T.equals(other.T);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "ConstFormalParameter { I = " + this.I + ", T = " + this.T + ", position = " + this.position + " }";
  }
}
