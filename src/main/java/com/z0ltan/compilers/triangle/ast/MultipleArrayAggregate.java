package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class MultipleArrayAggregate extends ArrayAggregate {
  public Expression E;
  public ArrayAggregate AA;

  public MultipleArrayAggregate(final Expression E, final ArrayAggregate AA, final SourcePosition position) {
    super(position);
    this.E = E;
    this.AA = AA;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof MultipleArrayAggregate)) {
      return false;
    }

    MultipleArrayAggregate other = (MultipleArrayAggregate)o;
    return this.E.equals(other.E) && this.AA.equals(other.AA);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "MultipleArrayAggregate { E = " + this.E + ", AA = " + this.AA + ", position = " + this.position + " }";
  }
}
