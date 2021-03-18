package com.z0ltan.compilers.triangle.parser;

import com.z0ltan.compilers.triangle.scanner.Scanner;
import com.z0ltan.compilers.triangle.scanner.Token;
import com.z0ltan.compilers.triangle.scanner.TokenType;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;
import com.z0ltan.compilers.triangle.ast.Program;
import com.z0ltan.compilers.triangle.ast.Command;
import com.z0ltan.compilers.triangle.ast.EmptyCommand;
import com.z0ltan.compilers.triangle.ast.AssignCommand;
import com.z0ltan.compilers.triangle.ast.LetCommand;
import com.z0ltan.compilers.triangle.ast.IfCommand;
import com.z0ltan.compilers.triangle.ast.WhileCommand;
import com.z0ltan.compilers.triangle.ast.CallCommand;
import com.z0ltan.compilers.triangle.ast.SequentialCommand;
import com.z0ltan.compilers.triangle.ast.FormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.EmptyFormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.SingleFormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.MultipleFormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.FormalParameter;
import com.z0ltan.compilers.triangle.ast.ConstFormalParameter;
import com.z0ltan.compilers.triangle.ast.VarFormalParameter;
import com.z0ltan.compilers.triangle.ast.ProcFormalParameter;
import com.z0ltan.compilers.triangle.ast.FuncFormalParameter;
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
import com.z0ltan.compilers.triangle.ast.VarDeclaration;
import com.z0ltan.compilers.triangle.ast.ConstDeclaration;
import com.z0ltan.compilers.triangle.ast.ProcDeclaration;
import com.z0ltan.compilers.triangle.ast.FuncDeclaration;
import com.z0ltan.compilers.triangle.ast.TypeDeclaration;
import com.z0ltan.compilers.triangle.ast.SequentialDeclaration;
import com.z0ltan.compilers.triangle.ast.Vname;
import com.z0ltan.compilers.triangle.ast.SimpleVname;
import com.z0ltan.compilers.triangle.ast.DotVname;
import com.z0ltan.compilers.triangle.ast.SubscriptVname;
import com.z0ltan.compilers.triangle.ast.Expression;
import com.z0ltan.compilers.triangle.ast.IntegerExpression;
import com.z0ltan.compilers.triangle.ast.CharacterExpression;
import com.z0ltan.compilers.triangle.ast.LetExpression;
import com.z0ltan.compilers.triangle.ast.IfExpression;
import com.z0ltan.compilers.triangle.ast.CallExpression;
import com.z0ltan.compilers.triangle.ast.VnameExpression;
import com.z0ltan.compilers.triangle.ast.UnaryExpression;
import com.z0ltan.compilers.triangle.ast.BinaryExpression;
import com.z0ltan.compilers.triangle.ast.ArrayExpression;
import com.z0ltan.compilers.triangle.ast.RecordExpression;
import com.z0ltan.compilers.triangle.ast.ArrayAggregate;
import com.z0ltan.compilers.triangle.ast.SingleArrayAggregate;
import com.z0ltan.compilers.triangle.ast.MultipleArrayAggregate;
import com.z0ltan.compilers.triangle.ast.RecordAggregate;
import com.z0ltan.compilers.triangle.ast.SingleRecordAggregate;
import com.z0ltan.compilers.triangle.ast.MultipleRecordAggregate;
import com.z0ltan.compilers.triangle.ast.TypeDenoter;
import com.z0ltan.compilers.triangle.ast.SimpleTypeDenoter;
import com.z0ltan.compilers.triangle.ast.ArrayTypeDenoter;
import com.z0ltan.compilers.triangle.ast.RecordTypeDenoter;
import com.z0ltan.compilers.triangle.ast.FieldTypeDenoter;
import com.z0ltan.compilers.triangle.ast.SingleFieldTypeDenoter;
import com.z0ltan.compilers.triangle.ast.MultipleFieldTypeDenoter;
import com.z0ltan.compilers.triangle.ast.Identifier;
import com.z0ltan.compilers.triangle.ast.Operator;
import com.z0ltan.compilers.triangle.ast.CharacterLiteral;
import com.z0ltan.compilers.triangle.ast.IntegerLiteral;
import com.z0ltan.compilers.triangle.error.SyntaxError;

public class Parser {
  private Scanner scanner;
  private Token currentToken;

  public Parser(final Scanner scanner) {
    this.scanner = scanner;
    this.currentToken = scanner.scan();
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

  /**
   * Declaration ::= singleDeclaration
   *              | Declaration ; singleDeclaration
   */
  Declaration parseDeclaration() {
    SourcePosition declPos = new SourcePosition();
    start(declPos);
    Declaration decl = parseSingleDeclaration();

    while (currentToken.kind == TokenType.SEMICOLON) {
      acceptIt();
      final Declaration decl1 = parseSingleDeclaration();
      finish(declPos);
      decl = new SequentialDeclaration(decl, decl1, declPos);
    }

    return decl;
  }

  /**
   * singleDeclaration ::= ConstDeclaration
   *                    | VarDeclaration
   *                    | ProcDeclaration
   *                    | FuncDeclaration
   *                    | TypeDeclaration
   */
  Declaration parseSingleDeclaration() {
    SourcePosition declPos = new SourcePosition();
    start(declPos);

    switch (currentToken.kind) {
      case CONST:
        {
          acceptIt();
          final Identifier id = parseIdentifier();
          accept(TokenType.IS);
          final Expression expr = parseExpression();
          finish(declPos);
          return new ConstDeclaration(id, expr, declPos);
        }

      case VAR:
        {
          acceptIt();
          final Identifier id = parseIdentifier();
          accept(TokenType.COLON);
          final TypeDenoter td = parseTypeDenoter();
          finish(declPos);
          return new VarDeclaration(id, td, declPos);
        }

      case PROCEDURE:
        {
          acceptIt();
          final Identifier id = parseIdentifier();
          accept(TokenType.LEFT_PAREN);
          final FormalParameterSequence fps = parseFormalParameterSequence();
          accept(TokenType.RIGHT_PAREN);
          accept(TokenType.IS);
          final Command cmd = parseSingleCommand();
          finish(declPos);
          return new ProcDeclaration(id, fps, cmd, declPos);
        }

      case FUNCTION:
        {
          acceptIt();
          final Identifier id = parseIdentifier();
          accept(TokenType.LEFT_PAREN);
          final FormalParameterSequence fps = parseFormalParameterSequence();
          accept(TokenType.RIGHT_PAREN);
          accept(TokenType.COLON);
          final TypeDenoter td = parseTypeDenoter();
          accept(TokenType.IS);
          final Expression expr = parseExpression();
          finish(declPos);
          return new FuncDeclaration(id, fps, td, expr, declPos);
        }

      case TYPE:
        {
          acceptIt();
          final Identifier id = parseIdentifier();
          accept(TokenType.IS);
          final TypeDenoter td = parseTypeDenoter();
          finish(declPos);
          return new TypeDeclaration(id, td, declPos);
        }

      default:
        throw new SyntaxError(currentToken.kind + " cannot start a declaration");
    }
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
      final Expression expr1 = parseSecondaryExpression(); 
      //final Expression expr1 = parsePrimaryExpression(); @TODO
      finish(exprPos);
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

      case IDENTIFIER:
        {
          final Identifier id = parseIdentifier();
          if (currentToken.kind == TokenType.LEFT_PAREN) {
            accept(TokenType.LEFT_PAREN);
            final ActualParameterSequence aps = parseActualParameterSequence();
            accept(TokenType.RIGHT_PAREN);
            finish(exprPos);
            return new CallExpression(id, aps, exprPos);
          } else {
            finish(exprPos);
            final Vname vname = parseVname(id);
            return new VnameExpression(vname, exprPos);
          }
        }

      case OPERATOR:
        {
          final Operator op = parseOperator();
          final Expression expr1 = parsePrimaryExpression();
          finish(exprPos);
          return new UnaryExpression(op, expr1, exprPos);
        }

      case LEFT_PAREN:
        {
          acceptIt();
          final Expression expr = parseExpression();
          accept(TokenType.RIGHT_PAREN);
          return expr;
        }

      case LEFT_BRACKET:
        {
          acceptIt();
          final ArrayAggregate aa = parseArrayAggregate();
          accept(TokenType.RIGHT_BRACKET);
          finish(exprPos);
          return new ArrayExpression(aa, exprPos);
        }

      case LEFT_CURLY:
        {
          acceptIt();
          final RecordAggregate ra = parseRecordAggregate();
          accept(TokenType.RIGHT_CURLY);
          finish(exprPos);
          return new RecordExpression(ra, exprPos);
        }

      default:
        throw new SyntaxError(currentToken.kind + " cannot form an expression");
    }
  }

  // ArrayAggregate ::= Expression | Expression , ArrayAggregate
  ArrayAggregate parseArrayAggregate() {
    SourcePosition aaPos = new SourcePosition();
    final Expression expr = parseExpression();

    if (currentToken.kind == TokenType.COMMA) {
      acceptIt();
      final ArrayAggregate aa = parseArrayAggregate();
      finish(aaPos);
      return new MultipleArrayAggregate(expr, aa, aaPos);
    } else {
      finish(aaPos);
      return new SingleArrayAggregate(expr, aaPos);
    }
  }

  // RecordAggregate ::= Identifier ~ Expression | Identifier ~ Expression , RecordAggregate
  RecordAggregate parseRecordAggregate() {
    SourcePosition raPos = new SourcePosition();
    start(raPos);
    final Identifier id = parseIdentifier();
    accept(TokenType.IS);
    final Expression expr = parseExpression();

    if (currentToken.kind == TokenType.COMMA) {
      acceptIt();
      final RecordAggregate ra = parseRecordAggregate();
      finish(raPos);
      return new MultipleRecordAggregate(id, expr, ra, raPos);
    } else {
      finish(raPos);
      return new SingleRecordAggregate(id, expr, raPos);
    }
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

  /**
   * Vname ::= SimpleVname
   *          | DotVname
   *          | SubscriptVname
   *
   * SimpleVname ::= Identifier
   * DotVname ::= Vname . Identifier
   * SubscriptVname ::= Vname [Expression]
   */
  Vname parseVname(Identifier id) {
    SourcePosition vnPos = new SourcePosition();
    start(vnPos);

    SourcePosition idPos = new SourcePosition();
    if (id == null) {
      start(idPos);
      id = parseIdentifier();
      finish(idPos);
    }

    Vname vname = new SimpleVname(id, idPos);

    while (currentToken.kind == TokenType.DOT || currentToken.kind == TokenType.LEFT_BRACKET) {
      if (currentToken.kind == TokenType.DOT) {
        acceptIt();
        final Identifier id1 = parseIdentifier();
        finish(vnPos);
        vname = new DotVname(vname, id1, vnPos);
      } else {
        acceptIt();
        final Expression expr = parseExpression();
        finish(vnPos);
        vname = new SubscriptVname(vname, expr, vnPos);
        accept(TokenType.RIGHT_BRACKET);
      }
    }

    return vname;
  }

  /**
   * TypeDenoter ::= SimpleTypeDenoter
   *              | ArrayTypeDenoter
   *              | RecordTypeDenoter
   *
   * SimpleTypeDenoter ::=  Identifier
   * ArrayTypeDenoter ::= array IntegerLiteral of TypeDenoter
   * RecordTypeDenoter ::= Identifier : TypeDenoter
   *                    | Identifier : TypeDenoter , RecordTypeDenoter
   */
  TypeDenoter parseTypeDenoter() {
    SourcePosition tdPos = new SourcePosition();
    start(tdPos);

    switch (currentToken.kind) {
      case IDENTIFIER:
        {
          final Identifier id = parseIdentifier();
          finish(tdPos);
          return new SimpleTypeDenoter(id, tdPos);
        }

      case ARRAY:
        {
          acceptIt();
          final IntegerLiteral il = parseIntegerLiteral();
          accept(TokenType.OF);
          final TypeDenoter td = parseTypeDenoter();
          finish(tdPos);
          return new ArrayTypeDenoter(il, td, tdPos);
        }

      case RECORD:
        {
          acceptIt();
          final FieldTypeDenoter ftd = parseFieldTypeDenoter();
          accept(TokenType.END);
          finish(tdPos);
          return new RecordTypeDenoter(ftd, tdPos);
        }

      default:
        throw new SyntaxError(currentToken.kind + " cannot start a type denoter");
    }
  }

  /**
   * FieldTypeDenoter ::= SingleFieldTypeDenoter | MultipleFieldTypeDenoter
   * SingleFieldTypeDenoter ::= Identifier : TypeDenoter
   * MultipleFieldTypeDenoter ::= Identifier : TypeDenoter , RecordTypeDenoter
   */
  FieldTypeDenoter parseFieldTypeDenoter() {
    SourcePosition ftdPos = new SourcePosition();
    start(ftdPos);
    final Identifier id = parseIdentifier();
    accept(TokenType.COLON);
    final TypeDenoter td = parseTypeDenoter();

    if (currentToken.kind == TokenType.COMMA) {
      acceptIt();
      final FieldTypeDenoter ftd1 = parseFieldTypeDenoter();
      finish(ftdPos);
      return new MultipleFieldTypeDenoter(id, td, ftd1, ftdPos);
    } else {
      finish(ftdPos);
      return new SingleFieldTypeDenoter(id, td, ftdPos);
    }
  }

  /**
   * FormalParameterSequence ::= EmptyFormalParameterSequence
   *                          | properFormalParameterSequence
   */
  FormalParameterSequence parseFormalParameterSequence() {
    if (currentToken.kind == TokenType.RIGHT_PAREN) {
      SourcePosition fpPos = new SourcePosition();
      start(fpPos);
      finish(fpPos);
      return new EmptyFormalParameterSequence(fpPos);
    } else {
      return parseProperFormalParameterSequence();
    }
  }

  /**
   * properFormalParameterSequence ::= SingleFormalParameterSequence
   *                                | MultipleFormalParameterSequence
   */
  FormalParameterSequence parseProperFormalParameterSequence() {
    SourcePosition fpsPos = new SourcePosition();
    start(fpsPos);

    FormalParameter fp = parseFormalParameter();
    if (currentToken.kind == TokenType.COMMA) {
      acceptIt();
      FormalParameterSequence fps = parseProperFormalParameterSequence();
      finish(fpsPos);
      return new MultipleFormalParameterSequence(fp, fps, fpsPos);
    } else {
      finish(fpsPos);
      return new SingleFormalParameterSequence(fp, fpsPos);
    }
  }

  /**
   * FormalParameter ::= ConstFormalParameter
   *                  | VarFormalParameter
   *                  | ProcFormalParameter
   *                  | FuncFormalParameter
   *
   * ConstFormalParameter ::= Identifier : TypeDenoter
   * VarFormalParameter ::= var Identifier : TypeDenoter
   * ProcFormalParameter ::= proc Identifier (FPS)
   * FuncFormalParameter ::= func Identifier (FPS): TypeDenoter
   */
   FormalParameter parseFormalParameter() {
     SourcePosition fpPos = new SourcePosition();
     start(fpPos);

     switch (currentToken.kind) {
       case IDENTIFIER:
         {
           final Identifier id = parseIdentifier();
           accept(TokenType.COLON);
           final TypeDenoter td = parseTypeDenoter();
           finish(fpPos);

           return new ConstFormalParameter(id, td, fpPos);
         }

       case VAR:
         {
           acceptIt();
           final Identifier id = parseIdentifier();
           accept(TokenType.COLON);
           final TypeDenoter td = parseTypeDenoter();
           finish(fpPos);

           return new VarFormalParameter(id, td, fpPos);
         }

       case PROCEDURE:
         {
           acceptIt();
           final Identifier id = parseIdentifier();
           accept(TokenType.LEFT_PAREN);
           final FormalParameterSequence fps = parseFormalParameterSequence();
           accept(TokenType.RIGHT_PAREN);
           finish(fpPos);

           return new ProcFormalParameter(id, fps, fpPos);
         }

       case FUNCTION:
         {
           acceptIt();
           final Identifier id = parseIdentifier();
           accept(TokenType.LEFT_PAREN);
           final FormalParameterSequence fps = parseFormalParameterSequence();
           accept(TokenType.RIGHT_PAREN);
           accept(TokenType.COLON);
           final TypeDenoter td = parseTypeDenoter();
           finish(fpPos);

           return new FuncFormalParameter(id, fps, td, fpPos);
         }

       default:
         throw new SyntaxError(currentToken.kind + " cannot start a formal parameter");
     }
   }

  /**
   * ActualParameterSequence ::= EmptyActualParameterSequence 
   *                          | properActualParameterSequence
   */
  ActualParameterSequence parseActualParameterSequence() {
    if (currentToken.kind == TokenType.RIGHT_PAREN) {
      SourcePosition eapsPos = new SourcePosition();
      start(eapsPos);
      finish(eapsPos);
      return new EmptyActualParameterSequence(eapsPos);
    } else {
      return parseProperActualParameterSequence();
    }
  }

  /** 
   * properActualParameterSequence ::= SingleActualParameterSequence
   *                                | MultipleActualParameterSequence
   *
   * SingleActualParameterSequence ::= ActualParameter
   * MultipleActualParameterSequence :: = ActualParameter , ActualParameterSequence
   */
  ActualParameterSequence parseProperActualParameterSequence() {
    SourcePosition apsPos = new SourcePosition();
    start(apsPos);

    ActualParameter ap = parseActualParameter();
    if (currentToken.kind == TokenType.COMMA) {
      acceptIt();
      ActualParameterSequence aps = parseProperActualParameterSequence();
      finish(apsPos);
      return new MultipleActualParameterSequence(ap, aps, apsPos);
    } else {
      finish(apsPos);
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
          final Vname vname = parseVname(null);
          finish(apPos);
          return new VarActualParameter(vname, apPos);
        }

      case PROCEDURE:
        {
          acceptIt();
          final Identifier id = parseIdentifier();
          finish(apPos);
          return new ProcActualParameter(id, apPos);
        }

      case FUNCTION:
        {
          acceptIt();
          final Identifier id = parseIdentifier();
          finish(apPos);
          return new FuncActualParameter(id, apPos);
        }

      default:
        {
          final Expression expr = parseExpression();
          finish(apPos);
          return new ConstActualParameter(expr, apPos);
        }
    }
  }

  // Command ::= single-Command | Command ; single-Command
  Command parseCommand() {
    SourcePosition cmdPos = new SourcePosition();
    start(cmdPos);
    Command cmd = parseSingleCommand();

    while (currentToken.kind == TokenType.SEMICOLON) {
      acceptIt();
      final Command cmd1 = parseSingleCommand();
      finish(cmdPos);
      cmd = new SequentialCommand(cmd, cmd1, cmdPos);
    }

    return cmd;
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

    switch (currentToken.kind) {
      case IDENTIFIER:
        {
          Identifier id = parseIdentifier();
          if (currentToken.kind == TokenType.LEFT_PAREN) {
            acceptIt();
            ActualParameterSequence seq = parseActualParameterSequence();
            accept(TokenType.RIGHT_PAREN);
            finish(cmdPos);
            return new CallCommand(id, seq, cmdPos);
          } else {
            final Vname vname = parseVname(id);
            accept(TokenType.BECOMES);
            final Expression expr = parseExpression();
            finish(cmdPos);
            return new AssignCommand(vname, expr, cmdPos);
          }
        }

      case LET:
        {
          acceptIt();
          final Declaration decl = parseDeclaration();
          accept(TokenType.IN);
          final Command cmd = parseSingleCommand();
          finish(cmdPos);
          return new LetCommand(decl, cmd, cmdPos);
        }

      case IF:
        {
          acceptIt();
          final Expression expr = parseExpression();
          accept(TokenType.THEN);
          final Command cmd1 = parseSingleCommand();
          accept(TokenType.ELSE);
          final Command cmd2 = parseSingleCommand();
          finish(cmdPos);
          return new IfCommand(expr, cmd1, cmd2, cmdPos);
        }

      case WHILE:
        {
          acceptIt();
          final Expression expr = parseExpression();
          accept(TokenType.DO);
          final Command cmd = parseSingleCommand();
          finish(cmdPos);
          return new WhileCommand(expr, cmd, cmdPos);
        }

      case BEGIN:
        {
          acceptIt();
          final Command cmd = parseCommand();
          accept(TokenType.END);
          return cmd;
        }

      case END:
        {
          finish(cmdPos);
          return new EmptyCommand(cmdPos);
        }

      case SEMICOLON:
      case EOT:
        {
          acceptIt();
          finish(cmdPos);
          return new EmptyCommand(cmdPos);
        }

      default:
        throw new SyntaxError(currentToken.kind + " cannot start a command");
    }
  }

  // Program ::= Command <EOT>
  public Program parseProgram() {
    SourcePosition programPos = new SourcePosition();
    start(programPos);
    Command cmd = parseCommand();
    finish(programPos);
    accept(TokenType.EOT);

    return new Program(cmd, programPos);
  }
}
