package com.z0ltan.compilers.triangle.error;

import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class ErrorReporter {
  public static String reportError(final SourcePosition position, final String... messages) {
    StringBuffer sb = new StringBuffer();
    for (String message : messages) {
      sb.append(message);
      sb.append(" ");
    }
    return String.format("[Line %d, column %d] %s\n", position.start.line, position.start.column, sb.toString());
  }
}
