package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class UnaryExpression extends Expression {
  public Operator O;
  public Expression E;

  public UnaryExpression(final Operator O, final Expression E, final SourcePosition position) {
    super(position);
    this.O = O;
    this.E = E;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof UnaryExpression)) {
      return false;
    }

    UnaryExpression other = (UnaryExpression)o;
    return this.O.equals(other.O) && this.E.equals(other.E);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "UnaryExpression { O = " + this.O + ", E = " + this.E + ", position = " + this.position + " }";
  }
}
