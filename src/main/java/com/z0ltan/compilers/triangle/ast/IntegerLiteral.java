package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class IntegerLiteral extends Terminal {
  public IntegerLiteral(final String spelling, final SourcePosition position) {
    super(spelling, position);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof IntegerLiteral)) {
      return false;
    }

    IntegerLiteral other = (IntegerLiteral)o;
    return this.spelling.equals(other.spelling);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "IntegerLiteral { spelling = " + this.spelling + ", position = " + this.position + " }";
  }
}
