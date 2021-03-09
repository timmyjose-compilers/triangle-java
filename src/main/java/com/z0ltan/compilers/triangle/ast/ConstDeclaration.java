package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class ConstDeclaration extends Declaration {
  public Identifier I;
  public Expression E;

  public ConstDeclaration(final Identifier I, final Expression E, final SourcePosition position) {
    super(position);
    this.I = I;
    this.E = E;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ConstDeclaration)) {
      return false;
    }

    ConstDeclaration other = (ConstDeclaration)o;
    return this.I.equals(other.I) && this.E.equals(other.E);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "ConstDeclaration { I = " + this.I + ", E = " + this.E + ", position = " + this.position + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
