package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class ConstActualParameter extends ActualParameter {
  public Expression E;

  public ConstActualParameter(final Expression E, final SourcePosition position) {
    super(position);
    this.E = E;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ConstActualParameter)) {
      return false;
    }

    ConstActualParameter other = (ConstActualParameter)o;
    return this.E.equals(other.E);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "ConstActualParameter { E = " + this.E + ", position = " + this.position + "}";
  }
}
