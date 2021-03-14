package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class Operator extends Terminal {
  public Declaration decl;

  public Operator(final String spelling, final SourcePosition position) {
    super(spelling, position);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Operator)) {
      return false;
    }

    Operator other = (Operator)o;
    return this.spelling.equals(other.spelling);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "Operator { spelling = " + this.spelling + ", position = " + this.position  + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
