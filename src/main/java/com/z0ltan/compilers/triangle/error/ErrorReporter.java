package com.z0ltan.compilers.triangle.error;

import com.z0ltan.compilers.triangle.scanner.SourcePosition;

public class ErrorReporter {
  public static String reportError(final SourcePosition position, final Object... messages) {
    StringBuffer sb = new StringBuffer();
    for (Object message : messages) {
      sb.append(message.toString());
      sb.append(" ");
    }
    return String.format("[Line %d, column %d] %s\n", position.start.line, position.start.column, sb.toString());
  }

  public static void reportError(final String message) {
    System.err.println(message);
    System.exit(1);
  }
}
