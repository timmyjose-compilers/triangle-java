package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class MultipleRecordAggregate extends RecordAggregate {
  public Identifier I;
  public Expression E;
  public RecordAggregate RA;

  public MultipleRecordAggregate(final Identifier I, final Expression E, final RecordAggregate RA, final SourcePosition position) {
    super(position);
    this.I = I;
    this.E = E;
    this.RA = RA;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof MultipleRecordAggregate)) {
      return false;
    }

    MultipleRecordAggregate other = (MultipleRecordAggregate)o;
    return this.I.equals(other.I) && this.E.equals(other.E) && this.RA.equals(other.RA);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "MultipleRecordAggregate { I = " + this.I + ", E = " + this.E + ", RA = " + this.RA + ", position = " + this.position + " }";
  }
}
