package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class SubscriptVname extends Vname {
  public Vname V;
  public Expression E;

  public SubscriptVname(final Vname V, final Expression E, final SourcePosition position) {
    super(position);
    this.V = V;
    this.E = E;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof SubscriptVname)) {
      return false;
    }

    SubscriptVname other = (SubscriptVname)o;
    return this.V.equals(other.V) && this.E.equals(other.E);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "SubscriptVname { V = " + this.V + ", E = " + this.E + ", position = " + this.position + " }";
  }
}
