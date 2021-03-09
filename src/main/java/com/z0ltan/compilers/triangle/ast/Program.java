package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class Program extends Ast {
  public Command C;
  
  public Program(final Command C, final SourcePosition position) {
    super(position);
    this.C = C;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Program)) {
      return false;
    }
    Program other = (Program)o;
    return this.C.equals(other.C);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "Program { C = " + this.C + ", position = " + this.position + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
