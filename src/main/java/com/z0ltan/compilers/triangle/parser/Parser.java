package com.z0ltan.compilers.triangle.parser;

import com.z0ltan.compilers.triangle.scanner.Scanner;
import com.z0ltan.compilers.triangle.scanner.Token;
import com.z0ltan.compilers.triangle.scanner.TokenType;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;
import com.z0ltan.compilers.triangle.ast.Program;
import com.z0ltan.compilers.triangle.ast.Command;
import com.z0ltan.compilers.triangle.ast.EmptyCommand;
import com.z0ltan.compilers.triangle.ast.SequentialCommand;
import com.z0ltan.compilers.triangle.ast.CallCommand;
import com.z0ltan.compilers.triangle.ast.ActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.EmptyActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.SingleActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.MultipleActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.ActualParameter;
import com.z0ltan.compilers.triangle.ast.ConstActualParameter;
import com.z0ltan.compilers.triangle.ast.VarActualParameter;
import com.z0ltan.compilers.triangle.ast.ProcActualParameter;
import com.z0ltan.compilers.triangle.ast.FuncActualParameter;
import com.z0ltan.compilers.triangle.ast.Declaration;
import com.z0ltan.compilers.triangle.ast.Vname;
import com.z0ltan.compilers.triangle.ast.Expression;
import com.z0ltan.compilers.triangle.ast.IntegerExpression;
import com.z0ltan.compilers.triangle.ast.CharacterExpression;
import com.z0ltan.compilers.triangle.ast.LetExpression;
import com.z0ltan.compilers.triangle.ast.IfExpression;
import com.z0ltan.compilers.triangle.ast.UnaryExpression;
import com.z0ltan.compilers.triangle.ast.BinaryExpression;
import com.z0ltan.compilers.triangle.ast.TypeDenoter;
import com.z0ltan.compilers.triangle.ast.Identifier;
import com.z0ltan.compilers.triangle.ast.Operator;
import com.z0ltan.compilers.triangle.ast.CharacterLiteral;
import com.z0ltan.compilers.triangle.ast.IntegerLiteral;
import com.z0ltan.compilers.triangle.error.SyntaxError;

public class Parser {
  private Scanner scanner;
  private Token currentToken;
  private SourcePosition currentPosition;

  public Parser(final Scanner scanner) {
    this.scanner = scanner;
    this.currentToken = scanner.scan();
    this.currentPosition = currentToken.position;
  }

  void accept(final TokenType expectedKind) {
    if (currentToken.kind != expectedKind) {
      throw new SyntaxError("expected to accept token of kind " + expectedKind + ", found token of kind " + currentToken.kind);
    }
    currentToken = scanner.scan();
  }

  void acceptIt() {
    currentToken = scanner.scan();
  }

  void start(SourcePosition position) {
    position.start.line = currentToken.position.start.line;
    position.start.column = currentToken.position.start.column;
  }

  void finish(SourcePosition position) {
    position.finish.line = currentToken.position.finish.line;
    position.finish.column = currentToken.position.finish.column;
  }

  Identifier parseIdentifier() {
    SourcePosition idPos = new SourcePosition();
    start(idPos);
    finish(idPos);
    Identifier id = new Identifier(currentToken.spelling, idPos);
    acceptIt();

    return id;
  }

  Operator parseOperator() {
    SourcePosition opPos = new SourcePosition();
    start(opPos);
    finish(opPos);
    Operator op = new Operator(currentToken.spelling, opPos);
    acceptIt();

    return op;
  }

  IntegerLiteral parseIntegerLiteral() {
    SourcePosition ilPos = new SourcePosition();
    start(ilPos);
    finish(ilPos);
    IntegerLiteral il = new IntegerLiteral(currentToken.spelling, ilPos);
    acceptIt();

    return il;
  }

  CharacterLiteral parseCharacterLiteral() {
    SourcePosition chPos = new SourcePosition();
    start(chPos);
    finish(chPos);
    CharacterLiteral ch = new CharacterLiteral(currentToken.spelling, chPos);
    acceptIt();

    return ch;
  }

  Declaration parseDeclaration() {
    return null;
  }

  /**
   * Expression ::= secondaryExpression
   *             | LetExpression
   *             | IfExpression
   */
  Expression parseExpression() {
    if (currentToken.kind == TokenType.LET) {
      return parseLetExpression();
    } else if (currentToken.kind == TokenType.IF) {
      return parseIfExpression();
    } else {
      return parseSecondaryExpression();
    }
  }

  /**
   * secondaryExpression ::= primaryExpression
   *                       | primaryExpression Operator secondaryExpression (BinaryExpression)
   */
  Expression parseSecondaryExpression() {
    SourcePosition exprPos = new SourcePosition();
    start(exprPos);
    Expression expr = parsePrimaryExpression();

    while (currentToken.kind == TokenType.OPERATOR) {
      final Operator op = parseOperator();
      finish(exprPos);
      final Expression expr1 = parseSecondaryExpression(); 
      expr = new BinaryExpression(expr, op, expr1, exprPos);
    }

    return expr;
  }

  /**
   * primaryExpression ::= IntegerExpression
   *                   | CharacterExpression
   *                   | VnameExpression
   *                   | CallExpression
   *                   | UnaryExpression
   *                   | ParenthesisedExpression
   *                   | RecordExpression
   *                   | ArrayExpression
   *
   * IntegerExpression ::= IntegerLiteral
   * CharacterExpression ::= CharacterLiteral
   * VnameExpression ::= Vname
   * CallExpression ::= Identifier (ActualParameterSequence)
   * UnaryExpression ::= Operator Expression
   */
  Expression parsePrimaryExpression() {
    SourcePosition exprPos = new SourcePosition();
    start(exprPos);

    switch (currentToken.kind) {
      case INTEGER_LITERAL:
        {
          final IntegerLiteral il = parseIntegerLiteral();
          finish(exprPos);

          return new IntegerExpression(il, exprPos);
        }

      case CHARACTER_LITERAL:
        {
          final CharacterLiteral cl = parseCharacterLiteral();
          finish(exprPos);

          return new CharacterExpression(cl, exprPos);
          }

      case OPERATOR:
        {

        }

      case LEFT_PAREN:
        {

        }

      case LEFT_BRACKET:
        {

        }

      case LEFT_CURLY:
        {

        }

      default:
        {

        }
    }

    return null;
  }

  // LetExpression ::= let Declaration in Expression
  LetExpression parseLetExpression() {
    SourcePosition lexprPos = new SourcePosition();
    start(lexprPos);
    acceptIt();
    final Declaration decl = parseDeclaration();
    accept(TokenType.IN);
    final Expression expr = parseExpression();
    finish(lexprPos);

    return new LetExpression(decl, expr, lexprPos);
  }

  // IfExpression ::= if Expression then Expression else Expression
  IfExpression parseIfExpression() {
    SourcePosition iexprPos = new SourcePosition();
    start(iexprPos);
    acceptIt();
    final Expression expr1 = parseExpression();
    accept(TokenType.THEN);
    final Expression expr2 = parseExpression();
    accept(TokenType.ELSE);
    final Expression expr3 = parseExpression();
    finish(iexprPos);

    return new IfExpression(expr1, expr2, expr3, iexprPos);
  }

  Vname parseVname() {
    return null;
  }

  TypeDenoter parseTypeDenoter() {
    return null;
  }

  /**
   * ActualParameterSequence ::= EmptyActualParameterSequence 
   *                          | MultipleActualParameterSequence
   */
  ActualParameterSequence parseActualParameterSequence() {
    if (currentToken.kind == TokenType.RIGHT_PAREN) {
      return parseEmptyActualParameterSequence();
    } else {
      return parseMultipleActualParameterSequence();
    }
  }

  // EmptyActualParameterSequence ::= epsilon
  ActualParameterSequence parseEmptyActualParameterSequence() {
    SourcePosition eapsPos = new SourcePosition();
    start(eapsPos);
    finish(eapsPos);

    return new EmptyActualParameterSequence(eapsPos);
  }

  /** 
   * MultipleActualParameterSequence ::= SingleActualParameterSequence
   *                                | MultipleActualParameterSequence
   *
   * SingleActualParameterSequence ::= ActualParameter
   * MultipleActualParamaterSequence :: = ActualParameter , ActualParameterSequence
   */
  ActualParameterSequence parseMultipleActualParameterSequence() {
    SourcePosition apsPos = new SourcePosition();
    start(apsPos);

    ActualParameter ap = parseActualParameter();
    if (currentToken.kind == TokenType.COMMA) {
      acceptIt();
      ActualParameterSequence aps = parseActualParameterSequence();
      finish(apsPos);
      return new MultipleActualParameterSequence(ap, aps, apsPos);
    } else {
      return new SingleActualParameterSequence(ap, apsPos);
    }
  }

  /*
   * ActualParameter ::= ConstActualParameter 
   *                  | VarActualParameter 
   *                  | ProcActualParameter 
   *                  | FuncActualParameter
   *
   * ConstActualParameter ::= Expression
   * VarActualParameter ::= var Vname
   * ProcActualParameter ::= proc Identifier (APS)
   * FuncActualParameter ::= func Identifier (APS): Type-Denoter
   */
  ActualParameter parseActualParameter() {
    SourcePosition apPos = new SourcePosition();
    start(apPos);

    switch (currentToken.kind) {
      case VAR:
        {
          acceptIt();
          final Vname vname = parseVname();
          finish(apPos);

          return new VarActualParameter(vname, apPos);
        }

      case PROCEDURE:
        {
          acceptIt();
          final Identifier id = parseIdentifier();
          accept(TokenType.LEFT_PAREN);
          final ActualParameterSequence aps = parseActualParameterSequence();
          accept(TokenType.RIGHT_PAREN);
          finish(apPos);

          return new ProcActualParameter(id, aps, apPos);
        }

      case FUNCTION:
        {
          acceptIt();
          final Identifier id = parseIdentifier();
          accept(TokenType.LEFT_PAREN);
          final ActualParameterSequence aps = parseActualParameterSequence();
          accept(TokenType.RIGHT_PAREN);
          accept(TokenType.COLON);
          final TypeDenoter td = parseTypeDenoter();
          finish(apPos);

          return new FuncActualParameter(id, aps, td, apPos);
        }

      default:
        {
          final Expression expr = parseExpression();
          finish(apPos);

          return new ConstActualParameter(expr, apPos);
        }
    }
  }

  /**
   * singleCommand ::= EmptyCommand 
   *              | AssignCommand 
   *              | CallCommand 
   *              | BracketedCommand
   *              | LetCommand 
   *              | IfCommand 
   *              | WhileCommand
   */
  Command parseSingleCommand() {
    SourcePosition cmdPos = new SourcePosition();
    start(cmdPos);
    Command cmd = null;

    switch (currentToken.kind) {
      case IDENTIFIER:
        {
          Identifier id = parseIdentifier();
          if (currentToken.kind == TokenType.LEFT_PAREN) {
            acceptIt();
            ActualParameterSequence seq = parseActualParameterSequence();
            accept(TokenType.RIGHT_PAREN);
            finish(cmdPos);
            cmd = new CallCommand(id, seq, cmdPos);
          } else {
            // assigncommand
          }
        }
        break;

      case SEMICOLON:
      case EOT:
        {
          acceptIt();
          finish(cmdPos);
          cmd = new EmptyCommand(cmdPos);
        }
        break;

      default:
        throw new SyntaxError(currentToken.kind + " cannot start a command");
    }

    return cmd;
  }

  // Command ::= single-Command | Command ; single-Command
  Command parseCommand() {
    SourcePosition cmdPos = new SourcePosition();
    start(cmdPos);
    Command cmd1 = parseSingleCommand();
    while (currentToken.kind == TokenType.COMMA) {
      acceptIt();
      final Command cmd2 = parseSingleCommand();
      finish(cmdPos);
      cmd1 = new SequentialCommand(cmd1, cmd2, cmdPos);
    }

    return cmd1;
  }

  // Program ::= Command <EOT>
  Program parseProgram() {
    SourcePosition programPos = new SourcePosition();
    start(programPos);
    Command cmd = parseCommand();
    finish(programPos);
    accept(TokenType.EOT);

    return new Program(cmd, programPos);
  }
}
