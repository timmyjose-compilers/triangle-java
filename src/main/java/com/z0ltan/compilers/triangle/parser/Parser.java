package com.z0ltan.compilers.triangle.parser;

import com.z0ltan.compilers.triangle.scanner.Scanner;
import com.z0ltan.compilers.triangle.scanner.Token;
import com.z0ltan.compilers.triangle.scanner.TokenType;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;
import com.z0ltan.compilers.triangle.ast.Program;
import com.z0ltan.compilers.triangle.ast.Command;
import com.z0ltan.compilers.triangle.ast.SequentialCommand;
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

  // single-Command ::= EmptyCommand | AssignCommand | CallCommand | BracketedCommand | LetCommand | IfCommand | WhileCommand
  Command parseSingleCommand() {
    return null;
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
