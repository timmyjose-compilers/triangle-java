package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class CallExpression extends Expression {
  public Identifier I;
  public ActualParameterSequence APS;

  public CallExpression(final Identifier I, final ActualParameterSequence APS, final SourcePosition position) {
    super(position);
    this.I = I;
    this.APS = APS;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof CallExpression)) {
      return false;
    }

    CallExpression other = (CallExpression)o;
    return this.I.equals(other.I) && this.APS.equals(other.APS);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "CallExpression { I = " + this.I + ", APS = " + this.APS + ", position = " + this.position + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
