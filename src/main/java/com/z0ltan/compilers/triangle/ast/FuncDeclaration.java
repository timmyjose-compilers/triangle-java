package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class FuncDeclaration extends Declaration {
  public Identifier I;
  public FormalParameterSequence FPS;
  public TypeDenoter T;
  public Expression E;

  public FuncDeclaration(final Identifier I, final FormalParameterSequence FPS, final TypeDenoter T, final Expression E, final SourcePosition position) {
    super(position);
    this.I = I;
    this.FPS = FPS;
    this.T = T;
    this.E = E;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof FuncDeclaration)) {
      return false;
    }

    FuncDeclaration other = (FuncDeclaration)o;
    return this.I.equals(other.I) && this.FPS.equals(other.FPS) && this.T.equals(other.T) && this.E.equals(other.E);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "FuncDeclaration { I = " + this.I + ", FPS = " + this.FPS + ", T = " + this.T + ", E = " + this.E + ", position = " + this.position + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
