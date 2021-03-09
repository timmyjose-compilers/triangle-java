package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class ArrayTypeDenoter extends TypeDenoter {
  public IntegerLiteral IL;
  public TypeDenoter T;

  public ArrayTypeDenoter(final IntegerLiteral IL, final TypeDenoter T, final SourcePosition position) {
    super(position);
    this.IL = IL;
    this.T = T;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ArrayTypeDenoter)) {
      return false;
    }

    ArrayTypeDenoter other = (ArrayTypeDenoter)o;
    return this.IL.equals(other.IL) && this.T.equals(other.T);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "ArrayTypeDenoter { IL = " + this.IL + ", T = " + this.T + ", position = " + this.position + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
