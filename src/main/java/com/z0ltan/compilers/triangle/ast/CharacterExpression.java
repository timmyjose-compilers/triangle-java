package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class CharacterExpression extends Expression {
  public CharacterLiteral CL;

  public CharacterExpression(final CharacterLiteral CL, final SourcePosition position) {
    super(position);
    this.CL = CL;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof CharacterExpression)) {
      return false;
    }

    CharacterExpression other = (CharacterExpression)o;
    return this.CL.equals(other.CL);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "CharacterExpression { CL = " + this.CL + ", position = " + this.position + " }";
  }
}
