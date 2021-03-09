package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class IfExpression extends Expression {
  public Expression E1;
  public Expression E2;
  public Expression E3;

  public IfExpression(final Expression E1, final Expression E2, final Expression E3, final SourcePosition position) {
    super(position);
    this.E1 = E1;
    this.E2 = E2;
    this.E3 = E3;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof IfExpression)) {
      return false;
    }

    IfExpression other = (IfExpression)o;
    return this.E1.equals(other.E1) && this.E2.equals(other.E2) && this.E3.equals(other.E3);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "IfExpression { E1 = " + this.E1 + ", E2 = " + this.E2 + ", E3 = " + this.E3 + ", position = " + this.position + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
