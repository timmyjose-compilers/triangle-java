package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class AssignCommand extends Command {
  public Vname V;
  public Expression E;

  public AssignCommand(final Vname V, final Expression E, final SourcePosition position) {
    super(position);
    this.V = V;
    this.E = E;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof AssignCommand)) {
      return false;
    }

    AssignCommand other = (AssignCommand)o;
    return this.V.equals(other.V) && this.E.equals(other.E);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "AssignCommand { V = " + this.V + ", E = " + this.E + ", position = " + this.position + " }";
  }
}
