package com.z0ltan.compilers.triangle.scanner;

import java.util.Map;
import java.util.HashMap;

public enum TokenType {
  ARRAY("ARRAY"),
  BECOMES("BECOMES"),
  BEGIN("BEGIN"),
  CHARACTER_LITERAL("CHARACTER_LITERAL"),
  COLON("COLON"),
  COMMA("COMMA"),
  CONST("CONST"),
  DO("DO"),
  DOT("DOT"),
  ELSE("ELSE"),
  END("END"),
  EOT("EOT"),
  FUNCTION("FUNCTION"),
  IDENTIFIER("IDENTIFIER"),
  IF("IF"),
  ILLEGAL("ILLEGAL"),
  IN("IN"),
  IS("IS"),
  LEFT_BRACKET("LEFT_BRACKET"),
  LEFT_CURLY("LEFT_CURLY"),
  LEFT_PAREN("LEFT_PAREN"),
  LET("LET"),
  INTEGER_LITERAL("INTEGER_LITERAL"),
  OF("OF"),
  OPERATOR("OPERATOR"),
  PROCEDURE("PROCEDURE"),
  RECORD("RECORD"),
  RIGHT_BRACKET("RIGHT_BRACKET"),
  RIGHT_CURLY("RIGHT_CURLY"),
  RIGHT_PAREN("RIGHT_PAREN"),
  SEMICOLON("SEMICOLON"),
  THEN("THEN"),
  TYPE("TYPE"),
  VAR("VAR"),
  WHILE("WHILE");

  private String description;

  private TokenType(final String description) {
    this.description = description;
  }

  public static boolean isKeyword(final String spelling) {
    return keywords.containsKey(spelling);
  }

  public static TokenType getKeywordTokenType(final String spelling) {
    return keywords.get(spelling);
  }

  @Override
  public String toString() {
    return this.description;
  }

  private static final Map<String, TokenType> keywords;

  static {
    keywords = new HashMap<>();

    keywords.put("(", TokenType.LEFT_PAREN);
    keywords.put(")", TokenType.RIGHT_PAREN);
    keywords.put(",", TokenType.COMMA);
    keywords.put(":", TokenType.COLON);
    keywords.put(":=", TokenType.BECOMES);
    keywords.put(";", TokenType.SEMICOLON);
    keywords.put("[", TokenType.LEFT_BRACKET);
    keywords.put("]", TokenType.RIGHT_BRACKET);
    keywords.put("array", TokenType.ARRAY);
    keywords.put("begin", TokenType.BEGIN);
    keywords.put("const", TokenType.CONST);
    keywords.put("do", TokenType.DO);
    keywords.put(".", TokenType.DOT);
    keywords.put("else", TokenType.ELSE);
    keywords.put("end", TokenType.END);
    keywords.put("func", TokenType.FUNCTION);
    keywords.put("if", TokenType.IF);
    keywords.put("in", TokenType.IN);
    keywords.put("let", TokenType.LET);
    keywords.put("of", TokenType.OF);
    keywords.put("proc", TokenType.PROCEDURE);
    keywords.put("record", TokenType.RECORD);
    keywords.put("then", TokenType.THEN);
    keywords.put("type", TokenType.TYPE);
    keywords.put("var", TokenType.VAR);
    keywords.put("while", TokenType.WHILE);
    keywords.put("{", TokenType.LEFT_CURLY);
    keywords.put("}", TokenType.RIGHT_CURLY);
    keywords.put("~", TokenType.IS);
  }
}
