package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class IfCommand extends Command {
  public Expression E;
  public Command C1;
  public Command C2;

  public IfCommand(final Expression E, final Command C1, final Command C2, final SourcePosition position) {
    super(position);
    this.E = E;
    this.C1 = C1;
    this.C2 = C2;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof IfCommand)) {
      return false;
    }

    IfCommand other = (IfCommand)o;
    return this.E.equals(other.E) && this.C1.equals(other.C1) && this.C2.equals(other.C2);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "IfCommand { E = " + this.E + ", C1 = " + this.C1 + ", C2 = " + this.C2 + ", position = " + this.position + " }";
  }
}
