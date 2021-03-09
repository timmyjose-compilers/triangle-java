package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class UnaryOperatorDeclaration extends Declaration {
  public Operator O;
  public TypeDenoter ARGTYPE;
  public TypeDenoter RESTYPE;

  public UnaryOperatorDeclaration(final Operator O, final TypeDenoter ARGTYPE, final TypeDenoter RESTYPE, final SourcePosition position) {
    super(position);
    this.O = O;
    this.ARGTYPE = ARGTYPE;
    this.RESTYPE = RESTYPE;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof UnaryOperatorDeclaration)) {
      return false;
    }

    UnaryOperatorDeclaration other = (UnaryOperatorDeclaration)o;
    return this.O.equals(other.O) && this.ARGTYPE.equals(other.ARGTYPE) && this.RESTYPE.equals(other.RESTYPE);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "UnaryOperatorDeclaration { O = " + this.O + ", ARGTYPE = " + this.ARGTYPE + ", RESTYPE = " + this.RESTYPE + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
