package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class SingleFieldTypeDenoter extends FieldTypeDenoter {
  public Identifier I;
  public TypeDenoter T;

  public SingleFieldTypeDenoter(final Identifier I, final TypeDenoter T, final SourcePosition position) {
    super(position);
    this.I = I;
    this.T = T;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof SingleFieldTypeDenoter)) {
      return false;
    }

    SingleFieldTypeDenoter other = (SingleFieldTypeDenoter)o;
    return this.I.equals(other.I) && this.T.equals(other.T);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "SingleFieldTypeDenoter { I = " + this.I + ", T = " + this.T + ", position = " + this.position + " }";
  }
}
