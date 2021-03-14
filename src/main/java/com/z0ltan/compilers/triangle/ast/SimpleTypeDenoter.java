package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class SimpleTypeDenoter extends TypeDenoter {
  public Identifier I;

  public SimpleTypeDenoter(final Identifier I, final SourcePosition position) {
    super(position);
    this.I = I;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof SimpleTypeDenoter)) {
      return false;
    }

    SimpleTypeDenoter other = (SimpleTypeDenoter)o;
    return this.I.equals(other.I);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "SimpleTypeDenoter { I = " + this.I + ", position = " + this.position + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
