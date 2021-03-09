package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class MultipleFieldTypeDenoter extends FieldTypeDenoter {
  public Identifier I;
  public TypeDenoter T;
  public FieldTypeDenoter FTD;

  public MultipleFieldTypeDenoter(final Identifier I, final TypeDenoter T, final FieldTypeDenoter FTD, final SourcePosition position) {
    super(position);
    this.I = I;
    this.T = T;
    this.FTD = FTD;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof MultipleFieldTypeDenoter)) {
      return false;
    }

    MultipleFieldTypeDenoter other = (MultipleFieldTypeDenoter)o;
    return this.I.equals(other.I) && this.T.equals(other.T) && this.FTD.equals(other.FTD);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "MultipleFieldTypeDenoter { I = " + this.I + ", T = " + this.T + ", FTD = " + this.FTD + ", position = " + this.position + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
