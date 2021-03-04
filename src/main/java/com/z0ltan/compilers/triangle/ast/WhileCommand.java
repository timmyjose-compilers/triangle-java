package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class WhileCommand extends Command {
  public Expression E;
  public Command C;

  public WhileCommand(final Expression E, final Command C, final SourcePosition position) {
    super(position);
    this.E = E;
    this.C = C;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof WhileCommand)) {
      return false;
    }

    WhileCommand other = (WhileCommand)o;
    return this.E.equals(other.E) && this.C.equals(other.C);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "WhileCommand { E = " + this.E + ", C = " + this.C + " }";
  }
}
