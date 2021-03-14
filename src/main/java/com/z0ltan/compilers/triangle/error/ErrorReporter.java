package com.z0ltan.compilers.triangle.error;

public class ErrorReporter {
  public static String reportError(final SourcePosition position, final String... message) {
    StringBuffer sb = new StringBuffer();
    for (String message : messages) {
      sb.append(message);
      sb.append(" ");
    }
    return String.format("[Line %d, column %d] %s\n", position.start.line, position.start.column, sb.toString());
  }
}
