package com.z0ltan.compilers.triangle.ast;

import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public abstract class FieldTypeDenoter extends TypeDenoter {
  public FieldTypeDenoter(final SourcePosition position) {
    super(position);
  }
}
