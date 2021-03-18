package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class ArrayExpression extends Expression {
  public ArrayAggregate AA;
  public int elemCount;

  public ArrayExpression(final ArrayAggregate AA, final SourcePosition position) {
    super(position);
    this.AA = AA;
    this.elemCount = 0;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ArrayExpression)) {
      return false;
    }

    ArrayExpression other = (ArrayExpression)o;
    return this.AA.equals(other.AA);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "ArrayExpression { AA = " + this.AA + ", position = " + this.position + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
