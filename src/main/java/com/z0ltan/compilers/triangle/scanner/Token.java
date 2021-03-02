package com.z0ltan.compilers.triangle.scanner;

public class Token {
  public TokenType kind;
  public String spelling;
  public SourcePosition position;

  public Token(final TokenType kind, final String spelling, SourcePosition position) {
    this.spelling = spelling;
    if (TokenType.isKeyword(spelling)) {
      this.kind = TokenType.getKeywordTokenType(spelling);
    } else {
      this.kind = kind;
    }
    this.position = position;
  }

  @Override 
  public String toString() {
    return "<" + this.kind + ", " + this.spelling + ">";
  }
}
