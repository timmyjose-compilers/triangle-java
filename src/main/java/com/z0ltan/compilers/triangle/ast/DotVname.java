package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class DotVname extends Vname {
  public Vname V;
  public Identifier I;

  public DotVname(final Vname V, final Identifier I, final SourcePosition position) {
    super(position);
    this.V = V;
    this.I = I;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof DotVname)) {
      return false;
    }

    DotVname other = (DotVname)o;
    return this.V.equals(other.V) && this.I.equals(other.I);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "DotVname { V = " + this.V + ", I = " + this.I + ", position = " + this.position + " }";
  }
}

