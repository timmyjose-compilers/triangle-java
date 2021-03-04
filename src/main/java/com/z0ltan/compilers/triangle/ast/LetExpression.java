package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class LetExpression extends Expression {
  public Declaration D;
  public Expression E;

  public LetExpression(final Declaration D, final Expression E, final SourcePosition position) {
    super(position);
    this.D = D;
    this.E = E;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof LetExpression)) {
      return false;
    }

    LetExpression other = (LetExpression)o;
    return this.D.equals(other.D) && this.E.equals(other.E);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "LetExpression { D = " + this.D + ", E = " + this.E + ", position = " + this.position + " }";
  }
}
