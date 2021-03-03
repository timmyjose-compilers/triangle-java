package com.z0ltan.compilers.triangle.ast;

import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class EmptyCommand extends Command {
  public EmptyCommand(final SourcePosition position) {
    super(position);
  }

  @Override
  public String toString() {
    return "EmptyCommand { position = " + this.position + " }";
  }
}
