package com.z0ltan.compilers.triangle.scanner;

public class Token {
  public TokenType kind;
  public String spelling;
  public SourcePosition position;

  public Token(final TokenType kind, final String spelling, SourcePosition position) {
    this.position = position;
    this.spelling = spelling;
    if (kind == TokenType.IDENTIFIER && TokenType.isKeyword(spelling )) {
      this.kind = TokenType.getKeywordTokenType(spelling);
    } else {
      this.kind = kind;
    }
  }

  @Override 
  public String toString() {
    return "<" + this.kind + ", " + this.spelling + ">";
  }
}
