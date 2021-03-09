package com.z0ltan.compilers.triangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class CallCommand extends Command {
  public Identifier I;
  public ActualParameterSequence APS;

  public CallCommand(final Identifier I, final ActualParameterSequence APS, final SourcePosition position) {
    super(position);
    this.I = I;
    this.APS = APS;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof CallCommand)) {
      return false;
    }

    CallCommand other = (CallCommand)o;
    return this.I.equals(other.I) && this.APS.equals(other.APS);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public String toString() {
    return "CallCommand { I = " + this.I + ", APS = " + this.APS + ", position = " + this.position + " }";
  }

  @Override
  public Object accept(final Visitor visitor, final Object arg) {
    return visitor.visit(this, arg);
  }
}
