package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class CharacterLiteral extends Terminal {
  public CharacterLiteral(final String spelling, final SourcePosition position) {
    super(spelling, position);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof CharacterLiteral)) {
      return false;
    }

    CharacterLiteral other = (CharacterLiteral)o;
    return this.spelling.equals(other.spelling);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "CharacterLiteral { spelling = " + this.spelling + ", position = " + this.position + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
