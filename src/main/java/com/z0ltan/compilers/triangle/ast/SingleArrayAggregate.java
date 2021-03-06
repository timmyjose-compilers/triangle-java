package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class SingleArrayAggregate extends ArrayAggregate {
  public Expression E;

  public SingleArrayAggregate(final Expression E, final SourcePosition position) {
    super(position);
    this.E = E;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof SingleArrayAggregate)) {
      return false;
    }

    SingleArrayAggregate other = (SingleArrayAggregate)o;
    return this.E.equals(other.E);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "SingleArrayAggregate { E = " + this.E + ", position = " + this.position + " }";
  }
}
