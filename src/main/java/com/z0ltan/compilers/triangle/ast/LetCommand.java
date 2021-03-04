package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class LetCommand extends Command {
  public Declaration D;
  public Command C;

  public LetCommand(final Declaration D, final Command C, final SourcePosition position) {
    super(position);
    this.D = D;
    this.C = C;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof LetCommand)) {
      return false;
    }

    LetCommand other = (LetCommand)o;
    return this.D.equals(other.D) && this.C.equals(other.C);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "LetCommand { D = " + this.D + ", C = " + this.C + ", position = " + this.position + " }";
  }
}
