package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class VarDeclaration extends Declaration {
  public Identifier I;
  public TypeDenoter T;

  public VarDeclaration(final Identifier I, final TypeDenoter T, final SourcePosition position) {
    super(position);
    this.I = I;
    this.T = T;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof VarDeclaration)) {
      return false;
    }

    VarDeclaration other = (VarDeclaration)o;
    return this.I.equals(other.I) && this.T.equals(other.T);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "VarDeclaration { I = " + this.I + ", T = " + this.T + ", position = " + this.position + " }";
  }
}
