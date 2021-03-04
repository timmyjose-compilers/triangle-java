package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class Identifier extends Terminal {
  public Identifier(final String spelling, SourcePosition position) {
    super(spelling, position);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Identifier)) {
      return false;
    }

    Identifier other = (Identifier)o;
    return this.spelling.equals(other.spelling);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "Identifier { spelling = " + this.spelling + ", position = " + this.position + " }";
  }
}
