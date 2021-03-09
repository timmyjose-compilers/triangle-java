package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class ProcDeclaration extends Declaration {
  public Identifier I;
  public FormalParameterSequence FPS;
  public Command C;

  public ProcDeclaration(final Identifier I, final FormalParameterSequence FPS, final Command C, final SourcePosition position) {
    super(position);
    this.I = I;
    this.FPS = FPS;
    this.C = C;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ProcDeclaration)) {
      return false;
    }

    ProcDeclaration other = (ProcDeclaration)o;
    return this.I.equals(other.I) && this.FPS.equals(other.FPS) && this.C.equals(other.C);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "ProcDeclaration { I = " + this.I + ", FPS = " + this.FPS + ", C = " + this.C + ", position = " + this.position + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
