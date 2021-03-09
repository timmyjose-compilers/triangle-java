package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class IntegerExpression extends Expression {
  public IntegerLiteral IL;

  public IntegerExpression(final IntegerLiteral IL, final SourcePosition position) {
    super(position);
    this.IL = IL;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof IntegerExpression)) {
      return false;
    }

    IntegerExpression other = (IntegerExpression)o;
    return this.IL.equals(other.IL);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "IntegerExpression { IL = " + this.IL + ", position = " + this.position + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
