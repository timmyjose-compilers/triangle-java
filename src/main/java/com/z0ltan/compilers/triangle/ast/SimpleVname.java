package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class SimpleVname extends Vname {
  public Identifier I;

  public SimpleVname(final Identifier I, final SourcePosition position) {
    super(position);
    this.I = I;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof SimpleVname)) {
      return false;
    }

    SimpleVname other = (SimpleVname)o;
    return this.I.equals(other.I);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }
  
  @Override
  public String toString() {
    return "SimpleVname { I = " + this.I + ", position = " + this.position + " }";
  }
}
