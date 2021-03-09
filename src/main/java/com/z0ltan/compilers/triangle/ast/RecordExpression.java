package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class RecordExpression extends Expression {
  public RecordAggregate RA;

  public RecordExpression(final RecordAggregate RA, final SourcePosition position) {
    super(position);
    this.RA = RA;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof RecordExpression)) {
      return false;
    }

    RecordExpression other = (RecordExpression)o;
    return this.RA.equals(other.RA);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "RecordExpression { RA = " + this.RA + ", position = " + this.position + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
