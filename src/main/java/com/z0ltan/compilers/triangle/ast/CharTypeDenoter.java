package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class CharTypeDenoter extends TypeDenoter {
  public CharTypeDenoter(final SourcePosition position) {
    super(position);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof CharTypeDenoter)) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "CharTypeDenoter { position = " + this.position + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
