package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class SequentialDeclaration extends Declaration {
  public Declaration D1;
  public Declaration D2;

  public SequentialDeclaration(final Declaration D1, final Declaration D2, final SourcePosition position) {
    super(position);
    this.D1 = D1;
    this.D2 = D2;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof SequentialDeclaration)) {
      return false;
    }

    SequentialDeclaration other = (SequentialDeclaration)o;
    return this.D1.equals(other.D1) && this.D2.equals(other.D2);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "SequentialDeclaration { D1 = " + this.D1 + ", D2 = " + this.D2 + ", position = " + this.position + " }";
  }
}
