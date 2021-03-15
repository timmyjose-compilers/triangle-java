package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class BinaryOperatorDeclaration extends Declaration {
  public TypeDenoter ARG1TYPE;
  public Operator O;
  public TypeDenoter ARG2TYPE;
  public TypeDenoter RESTYPE;

  public BinaryOperatorDeclaration(final TypeDenoter ARG1TYPE, final Operator O, final TypeDenoter ARG2TYPE, final TypeDenoter RESTYPE, final SourcePosition position) {
    super(position);
    this.ARG1TYPE = ARG1TYPE;
    this.O = O;
    this.ARG2TYPE = ARG2TYPE;
    this.RESTYPE = RESTYPE;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof BinaryOperatorDeclaration)) {
      return false;
    }

    BinaryOperatorDeclaration other = (BinaryOperatorDeclaration)o;
    return this.ARG1TYPE.equals(other.ARG1TYPE) && this.O.equals(other.O) && this.ARG2TYPE.equals(other.ARG2TYPE) && this.RESTYPE.equals(other.RESTYPE);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "BinaryOperatorDeclaration { ARG1TYPE = " + this.ARG1TYPE + ", O = " + this.O + ", ARG2TYPE = " + this.ARG2TYPE + ", RESTYPE = " + this.RESTYPE + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
