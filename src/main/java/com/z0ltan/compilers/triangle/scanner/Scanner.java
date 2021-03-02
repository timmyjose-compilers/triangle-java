package com.z0ltan.compilers.triangle.scanner;

import java.util.Iterator;
import com.z0ltan.compilers.triangle.error.SyntaxError;

public class Scanner {
  private Iterator<Char> source;
  private Char currentChar;
  private StringBuffer currentSpelling;
  private SourcePosition currentPosition;

  public Scanner(String filename) {
    this.source = new SourceFile(filename).iterator();
    this.currentChar = source.next();
  }

  void skipIt() {
    if (source.hasNext()) {
      currentChar = source.next();
    }
  }

  void skip(char c) {
    if (currentChar.c == c) {
      currentChar = source.next();
    }
  }

  void eatIt() {
    if (source.hasNext()) {
      currentSpelling.append(currentChar.c);
      currentChar = source.next();
    } 
  }

  void skipWhitespace() {
    switch (currentChar.c) {
      case ' ':
      case '\n':
      case '\r':
      case '\t':
        skipIt();
        break;

      case '!':
        while (currentChar.c != SourceFile.EOL && currentChar.c != SourceFile.EOT) {
          skipIt();
        }

        if (currentChar.c == SourceFile.EOL) {
          skip(SourceFile.EOL);
        }
    }
  }

  TokenType scanToken() {
    TokenType kind = TokenType.ILLEGAL;

    currentPosition.start.line = currentChar.line;
    currentPosition.start.column = currentChar.column;

    switch (currentChar.c) {
      case '.':
        {
          eatIt();
          kind =  TokenType.DOT;
        }
        break;

      case ';':
        {
          eatIt();
          kind = TokenType.SEMICOLON;
        }
        break;

      case ':':
        {
          eatIt();
          kind = TokenType.SEMICOLON;

          if (currentChar.c == '=') {
            eatIt();
            kind = TokenType.BECOMES;
          }
        }
        break;

      case '~':
        {
          eatIt();
          kind = TokenType.IS;
        }
        break;

      case '\'':
        {
          skipIt();
          eatIt();
          skip('\'');

          kind = TokenType.CHARACTER;
        }
        break;

      case '0':
      case '1':
      case '2':
      case '3':
      case '4':
      case '5':
      case '6':
      case '7':
      case '8':
      case '9':
        {
          eatIt();
          while (Character.isDigit(currentChar.c)) {
            eatIt();
          }
          kind = TokenType.NUMBER;
        }
        break;

      case 'a':
      case 'b':
      case 'c':
      case 'd':
      case 'e':
      case 'f':
      case 'g':
      case 'h':
      case 'i':
      case 'j':
      case 'l':
      case 'm':
      case 'n':
      case 'o':
      case 'p':
      case 'q':
      case 'r':
      case 's':
      case 't':
      case 'u':
      case 'v':
      case 'w':
      case 'x':
      case 'y':
      case 'z':
      case 'A':
      case 'B':
      case 'C':
      case 'D':
      case 'E':
      case 'F':
      case 'G':
      case 'H':
      case 'I':
      case 'J':
      case 'L':
      case 'M':
      case 'N':
      case 'O':
      case 'P':
      case 'Q':
      case 'R':
      case 'S':
      case 'T':
      case 'U':
      case 'V':
      case 'W':
      case 'X':
      case 'Y':
      case 'Z':
        {
          eatIt();
          while (Character.isLetterOrDigit(currentChar.c) || currentChar.c == '_') {
            eatIt();
          }

          kind = TokenType.IDENTIFIER;
        }
        break;

      case '+' :
      case '-':
      case '*':
      case '/':
      case '=':
      case '\\':
        {
          eatIt();
          kind = TokenType.OPERATOR;
        }
        break;

      case '(':
        {
          eatIt();
          kind = TokenType.LEFT_PAREN;
        }
        break;

      case ')':
        {
          eatIt();
          kind = TokenType.RIGHT_PAREN;
        }
        break;

      case '{':
        {
          eatIt();
          kind = TokenType.LEFT_CURLY;
        }
        break;

      case '}':
        {
          eatIt();
          kind = TokenType.RIGHT_CURLY;
        }
        break;

      case '[':
        {
          eatIt();
          kind = TokenType.LEFT_BRACKET;
        }
        break;

      case ']':
        {
          eatIt();
          kind = TokenType.RIGHT_BRACKET;
        }
        break;

      case '\u0000':
        {
          eatIt();
          kind = TokenType.EOT;
        }
        break;

      default:
        throw new SyntaxError("unsupported character found while scanning: " + currentChar.c);
    }

    currentPosition.finish.line = currentChar.line;
    currentPosition.finish.column = currentChar.column;

    return kind;
  }

  public Token scan() {
    while (Character.isWhitespace(currentChar.c) || currentChar.c == '!') {
      skipWhitespace();
    }

    currentPosition = new SourcePosition();
    currentSpelling = new StringBuffer();
    TokenType currentKind = scanToken();
    return new Token(currentKind, currentSpelling.toString(), currentPosition);
  }
}
