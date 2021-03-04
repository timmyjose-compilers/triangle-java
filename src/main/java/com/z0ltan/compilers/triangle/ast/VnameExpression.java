package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class VnameExpression extends Expression {
  public Vname V;

  public VnameExpression(final Vname V, final SourcePosition position) {
    super(position);
    this.V = V;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof VnameExpression)) {
      return false;
    }

    VnameExpression other = (VnameExpression)o;
    return this.V.equals(other.V);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "VnameExpression { V = " + this.V + ", position = " + this.position + " }";
  }
}
