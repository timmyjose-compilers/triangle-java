package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class SingleRecordAggregate extends RecordAggregate {
  public Identifier I;
  public Expression E;

  public SingleRecordAggregate(final Identifier I, final Expression E, final SourcePosition position) {
    super(position);
    this.I = I;
    this.E = E;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof SingleRecordAggregate)) {
      return false;
    }

    SingleRecordAggregate other = (SingleRecordAggregate)o;
    return this.I.equals(other.I) && this.E.equals(other.E);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "SingleRecordAggregate { I = " + this.I + ", E = " + this.E + ", position = " + this.position + " }";
  }
}
