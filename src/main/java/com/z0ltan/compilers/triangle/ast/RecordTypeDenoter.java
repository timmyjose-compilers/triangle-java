package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class RecordTypeDenoter extends TypeDenoter {
  public FieldTypeDenoter FTD;

  public RecordTypeDenoter(final FieldTypeDenoter FTD, final SourcePosition position) {
    super(position);
    this.FTD = FTD;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof RecordTypeDenoter)) {
      return false;
    }

    RecordTypeDenoter other = (RecordTypeDenoter)o;
    return this.FTD.equals(other.FTD);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "RecordTypeDenoter { FTD = " + this.FTD + ", position = " + this.position + " }";
  }
}
