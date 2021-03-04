package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class BinaryExpression extends Expression {
  public Expression E1;
  public Operator O;
  public Expression E2;

  public BinaryExpression(final Expression E1, final Operator O, final Expression E2, final SourcePosition position) {
    super(position);
    this.E1 = E1;
    this.O = O;
    this.E2 = E2;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof BinaryExpression)) {
      return false;
    }

    BinaryExpression other = (BinaryExpression)o;
    return this.E1.equals(other.E1) && this.O.equals(other.O) && this.E2.equals(other.E2);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "BinaryExpression { E1 = " + this.E1 + ", O = " + this.O + ", E2 = " + this.E2 + ", position = " + this.position + " }";
  }
}
