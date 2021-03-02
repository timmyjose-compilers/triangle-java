package com.z0ltan.compilers.triangle.scanner;

public class Token {
  public TokenType kind;
  public String spelling;

  public Token(final TokenType kind, final String spelling) {
    this.kind = kind;
    this.spelling = spelling;
  }

  @Override 
  public String toString() {
    return "<" + this.kind + ", " + this.spelling + ">";
  }
}
