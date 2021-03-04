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
import com.z0ltan.compilers.triangle.ast.Vname;
import com.z0ltan.compilers.triangle.ast.Expression;
import com.z0ltan.compilers.triangle.ast.IntegerExpression;
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
    acceptIt();
    SourcePosition idPos = new SourcePosition();
    start(idPos);
    finish(idPos);
    Identifier id = new Identifier(currentToken.spelling, idPos);

    return id;
  }

  Operator parseOperator() {
    acceptIt();
    SourcePosition opPos = new SourcePosition();
    start(opPos);
    finish(opPos);
    Operator op = new Operator(currentToken.spelling, opPos);

    return op;
  }

  IntegerLiteral parseIntegerLiteral() {
    acceptIt();
    SourcePosition ilPos = new SourcePosition();
    start(ilPos);
    finish(ilPos);
    IntegerLiteral il = new IntegerLiteral(currentToken.spelling, ilPos);

    return il;
  }

  CharacterLiteral parseCharacterLiteral() {
    acceptIt();
    SourcePosition chPos = new SourcePosition();
    start(chPos);
    finish(chPos);
    CharacterLiteral ch = new CharacterLiteral(currentToken.spelling, chPos);

    return ch;
  }

  Expression parseExpression() {
    return null;
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
