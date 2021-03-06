package com.z0ltan.compilers.triangle.parser;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.junit.Assert.*;

import com.z0ltan.compilers.triangle.scanner.Scanner;
import com.z0ltan.compilers.triangle.scanner.SourcePosition;
import com.z0ltan.compilers.triangle.parser.Parser;
import com.z0ltan.compilers.triangle.ast.Program;
import com.z0ltan.compilers.triangle.ast.EmptyCommand;
import com.z0ltan.compilers.triangle.ast.AssignCommand;
import com.z0ltan.compilers.triangle.ast.LetCommand;
import com.z0ltan.compilers.triangle.ast.CallCommand;
import com.z0ltan.compilers.triangle.ast.IfCommand;
import com.z0ltan.compilers.triangle.ast.WhileCommand;
import com.z0ltan.compilers.triangle.ast.SequentialCommand;
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
import com.z0ltan.compilers.triangle.ast.SingleArrayAggregate;
import com.z0ltan.compilers.triangle.ast.MultipleArrayAggregate;
import com.z0ltan.compilers.triangle.ast.SingleRecordAggregate;
import com.z0ltan.compilers.triangle.ast.MultipleRecordAggregate;
import com.z0ltan.compilers.triangle.ast.EmptyFormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.SingleFormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.MultipleFormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.EmptyActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.SingleActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.MultipleActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.ConstFormalParameter;
import com.z0ltan.compilers.triangle.ast.VarFormalParameter;
import com.z0ltan.compilers.triangle.ast.ProcFormalParameter;
import com.z0ltan.compilers.triangle.ast.ConstActualParameter;
import com.z0ltan.compilers.triangle.ast.VarActualParameter;
import com.z0ltan.compilers.triangle.ast.ProcActualParameter;
import com.z0ltan.compilers.triangle.ast.FuncActualParameter;
import com.z0ltan.compilers.triangle.ast.VarDeclaration;
import com.z0ltan.compilers.triangle.ast.ProcDeclaration;
import com.z0ltan.compilers.triangle.ast.FuncDeclaration;
import com.z0ltan.compilers.triangle.ast.TypeDeclaration;
import com.z0ltan.compilers.triangle.ast.SequentialDeclaration;
import com.z0ltan.compilers.triangle.ast.SimpleTypeDenoter;
import com.z0ltan.compilers.triangle.ast.ArrayTypeDenoter;
import com.z0ltan.compilers.triangle.ast.RecordTypeDenoter;
import com.z0ltan.compilers.triangle.ast.SingleFieldTypeDenoter;
import com.z0ltan.compilers.triangle.ast.MultipleFieldTypeDenoter;
import com.z0ltan.compilers.triangle.ast.SimpleVname;
import com.z0ltan.compilers.triangle.ast.DotVname;
import com.z0ltan.compilers.triangle.ast.SubscriptVname;
import com.z0ltan.compilers.triangle.ast.Identifier;
import com.z0ltan.compilers.triangle.ast.Operator;
import com.z0ltan.compilers.triangle.ast.IntegerLiteral;
import com.z0ltan.compilers.triangle.ast.CharacterLiteral;

import static com.z0ltan.compilers.triangle.scanner.SourcePosition.dummyPosition;

public class ParserTest extends TestCase {
  public ParserTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(ParserTest.class);
  }

  public void testEmptyCommandEot() {
    String filename = "samples/emptycommandeot.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = new Program(new EmptyCommand(dummyPosition()), dummyPosition());
    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testEmptyCommandEotDegenerate() {
    String filename = "samples/emptycommandeot_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = new Program(new EmptyCommand(dummyPosition()), dummyPosition());
    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testEmptyCommandSemicolon() {
    String filename = "samples/emptycommandsemicolon.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = new Program(new EmptyCommand(dummyPosition()), dummyPosition());
    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testEmptyCommandSemicolonDegenerate() {
    String filename = "samples/emptycommandsemicolon_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = new Program(new EmptyCommand(dummyPosition()), dummyPosition());
    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testHello() {
    String filename = "samples/hello.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = 
      new Program(new CallCommand(new Identifier("putint", dummyPosition()), 
            new SingleActualParameterSequence(new ConstActualParameter(new IntegerExpression(new IntegerLiteral("42", dummyPosition()), dummyPosition()), 
                dummyPosition()), dummyPosition()), 
            dummyPosition()),
          dummyPosition());
    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testHelloDegenerate() {
    String filename = "samples/hello_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = 
      new Program(new CallCommand(new Identifier("putint", dummyPosition()), 
            new SingleActualParameterSequence(new ConstActualParameter(new IntegerExpression(new IntegerLiteral("42", dummyPosition()), dummyPosition()), 
                dummyPosition()), dummyPosition()), 
            dummyPosition()),
          dummyPosition());
    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testInc() {
    String filename = "samples/inc.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram =
      new Program(
          new LetCommand(
            new SequentialDeclaration(
              new VarDeclaration(
                new Identifier("x", dummyPosition()),
                new SimpleTypeDenoter(new Identifier("Integer", dummyPosition()), dummyPosition()),
                dummyPosition()),
              new ProcDeclaration(
                new Identifier("inc", dummyPosition()),
                new SingleFormalParameterSequence(
                  new VarFormalParameter(new Identifier("n", dummyPosition()), 
                    new SimpleTypeDenoter(new Identifier("Integer", dummyPosition()), dummyPosition()),
                    dummyPosition()),
                  dummyPosition()),
                new AssignCommand(
                  new SimpleVname(new Identifier("n", dummyPosition()), dummyPosition()),
                  new BinaryExpression(
                    new VnameExpression(new SimpleVname(new Identifier("n", dummyPosition()), dummyPosition()), dummyPosition()),
                    new Operator("+", dummyPosition()),
                    new IntegerExpression(new IntegerLiteral("1", dummyPosition()), dummyPosition()),
                    dummyPosition()),
                  dummyPosition()),
                dummyPosition()),
              dummyPosition()),
              new SequentialCommand(
                  new SequentialCommand(
                    new CallCommand(
                      new Identifier("getint", dummyPosition()),
                      new SingleActualParameterSequence(
                        new VarActualParameter(new SimpleVname(new Identifier("x", dummyPosition()), dummyPosition()), dummyPosition()),
                        dummyPosition()),
                      dummyPosition()),
                    new CallCommand(
                      new Identifier("inc", dummyPosition()),
                      new SingleActualParameterSequence(
                        new VarActualParameter(new SimpleVname(new Identifier("x", dummyPosition()), dummyPosition()), dummyPosition()),
                        dummyPosition()),
                      dummyPosition()),
                    dummyPosition()),
                  new CallCommand(
                    new Identifier("putint", dummyPosition()),
                    new SingleActualParameterSequence(
                      new ConstActualParameter(new VnameExpression(new SimpleVname(new Identifier("x", dummyPosition()), dummyPosition()), dummyPosition()),
                        dummyPosition()),
                      dummyPosition()), dummyPosition()),
                  dummyPosition()),
                  dummyPosition()),
                  dummyPosition());
    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testIncDegenerate() {
    String filename = "samples/inc_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram =
      new Program(
          new LetCommand(
            new SequentialDeclaration(
              new VarDeclaration(
                new Identifier("x", dummyPosition()),
                new SimpleTypeDenoter(new Identifier("Integer", dummyPosition()), dummyPosition()),
                dummyPosition()),
              new ProcDeclaration(
                new Identifier("inc", dummyPosition()),
                new SingleFormalParameterSequence(
                  new VarFormalParameter(new Identifier("n", dummyPosition()), 
                    new SimpleTypeDenoter(new Identifier("Integer", dummyPosition()), dummyPosition()),
                    dummyPosition()),
                  dummyPosition()),
                new AssignCommand(
                  new SimpleVname(new Identifier("n", dummyPosition()), dummyPosition()),
                  new BinaryExpression(
                    new VnameExpression(new SimpleVname(new Identifier("n", dummyPosition()), dummyPosition()), dummyPosition()),
                    new Operator("+", dummyPosition()),
                    new IntegerExpression(new IntegerLiteral("1", dummyPosition()), dummyPosition()),
                    dummyPosition()),
                  dummyPosition()),
                dummyPosition()),
              dummyPosition()),
              new SequentialCommand(
                  new SequentialCommand(
                    new CallCommand(
                      new Identifier("getint", dummyPosition()),
                      new SingleActualParameterSequence(
                        new VarActualParameter(new SimpleVname(new Identifier("x", dummyPosition()), dummyPosition()), dummyPosition()),
                        dummyPosition()),
                      dummyPosition()),
                    new CallCommand(
                      new Identifier("inc", dummyPosition()),
                      new SingleActualParameterSequence(
                        new VarActualParameter(new SimpleVname(new Identifier("x", dummyPosition()), dummyPosition()), dummyPosition()),
                        dummyPosition()),
                      dummyPosition()),
                    dummyPosition()),
                  new CallCommand(
                    new Identifier("putint", dummyPosition()),
                    new SingleActualParameterSequence(
                      new ConstActualParameter(new VnameExpression(new SimpleVname(new Identifier("x", dummyPosition()), dummyPosition()), dummyPosition()),
                        dummyPosition()),
                      dummyPosition()), dummyPosition()),
                  dummyPosition()),
                  dummyPosition()),
                  dummyPosition());
    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testEcho() {
    String filename = "samples/echo.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = 
      new Program(
          new LetCommand(
            new SequentialDeclaration(
              new VarDeclaration(new Identifier("ch", dummyPosition()), new SimpleTypeDenoter(new Identifier("Char", dummyPosition()), dummyPosition()), dummyPosition()),
              new ProcDeclaration(new Identifier("echo", dummyPosition()), new EmptyFormalParameterSequence(dummyPosition()),
                new WhileCommand(new UnaryExpression(new Operator("\\", dummyPosition()), 
                    new CallExpression(new Identifier("eol", dummyPosition()), new EmptyActualParameterSequence(dummyPosition()), dummyPosition()),
                    dummyPosition()),
                  new SequentialCommand(
                    new CallCommand(new Identifier("get", dummyPosition()), new SingleActualParameterSequence(
                        new VarActualParameter(new SimpleVname(new Identifier("ch", dummyPosition()), dummyPosition()), dummyPosition()),
                        dummyPosition()), dummyPosition()),
                    new CallCommand(
                      new Identifier("put", dummyPosition()),
                      new SingleActualParameterSequence(
                        new ConstActualParameter(
                          new VnameExpression(
                            new SimpleVname(new Identifier("ch", dummyPosition()), dummyPosition()), dummyPosition()), dummyPosition())
                        ,dummyPosition())
                      ,dummyPosition()) 
                    ,dummyPosition()), dummyPosition()), 
                dummyPosition()), dummyPosition()), 
                new CallCommand(new Identifier("echo", dummyPosition()), new EmptyActualParameterSequence(dummyPosition()), dummyPosition()), dummyPosition()),
                dummyPosition());
    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testEchoDegenerate() {
    String filename = "samples/echo_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = 
      new Program(
          new LetCommand(
            new SequentialDeclaration(
              new VarDeclaration(new Identifier("ch", dummyPosition()), new SimpleTypeDenoter(new Identifier("Char", dummyPosition()), dummyPosition()), dummyPosition()),
              new ProcDeclaration(new Identifier("echo", dummyPosition()), new EmptyFormalParameterSequence(dummyPosition()),
                new WhileCommand(new UnaryExpression(new Operator("\\", dummyPosition()), 
                    new CallExpression(new Identifier("eol", dummyPosition()), new EmptyActualParameterSequence(dummyPosition()), dummyPosition()),
                    dummyPosition()),
                  new SequentialCommand(
                    new CallCommand(new Identifier("get", dummyPosition()), new SingleActualParameterSequence(
                        new VarActualParameter(new SimpleVname(new Identifier("ch", dummyPosition()), dummyPosition()), dummyPosition()),
                        dummyPosition()), dummyPosition()),
                    new CallCommand(
                      new Identifier("put", dummyPosition()),
                      new SingleActualParameterSequence(
                        new ConstActualParameter(
                          new VnameExpression(
                            new SimpleVname(new Identifier("ch", dummyPosition()), dummyPosition()), dummyPosition()), dummyPosition())
                        ,dummyPosition())
                      ,dummyPosition()) 
                    ,dummyPosition()), dummyPosition()), 
                dummyPosition()), dummyPosition()), 
                new CallCommand(new Identifier("echo", dummyPosition()), new EmptyActualParameterSequence(dummyPosition()), dummyPosition()), dummyPosition()),
                dummyPosition());
    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testOdd() {
    String filename = "samples/odd.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = 
      new Program(new LetCommand(new SequentialDeclaration(
              new VarDeclaration(
                new Identifier("n", dummyPosition()),
                new SimpleTypeDenoter(new Identifier("Integer", dummyPosition()), dummyPosition()),
                dummyPosition()),
              new FuncDeclaration(
                new Identifier("odd", dummyPosition()),
                new SingleFormalParameterSequence(new ConstFormalParameter(new Identifier("n", dummyPosition()), 
                    new SimpleTypeDenoter(new Identifier("Integer", dummyPosition()), dummyPosition()), dummyPosition()), dummyPosition()),
                new SimpleTypeDenoter(new Identifier("Boolean", dummyPosition()), dummyPosition()),
                new BinaryExpression(
                  new BinaryExpression(
                    new VnameExpression(new SimpleVname(new Identifier("n", dummyPosition()), dummyPosition()), dummyPosition()),
                    new Operator("//", dummyPosition()),
                    new IntegerExpression(new IntegerLiteral("2", dummyPosition()), dummyPosition()),
                    dummyPosition()),
                  new Operator("\\=", dummyPosition()),
                  new IntegerExpression(new IntegerLiteral("0", dummyPosition()), dummyPosition()),
                  dummyPosition()),
                dummyPosition()),
              dummyPosition()),
              new SequentialCommand(
                  new CallCommand(
                    new Identifier("getint", dummyPosition()),
                    new SingleActualParameterSequence(
                      new VarActualParameter(
                        new SimpleVname(
                          new Identifier("n", dummyPosition()), dummyPosition()), dummyPosition()), dummyPosition()),
                    dummyPosition()),
                  new IfCommand(
                    new CallExpression(
                      new Identifier("odd", dummyPosition()),
                      new SingleActualParameterSequence(
                        new ConstActualParameter(
                          new VnameExpression(
                            new SimpleVname(
                              new Identifier("n", dummyPosition()), dummyPosition()), dummyPosition()), dummyPosition()), dummyPosition()),
                      dummyPosition()),
                    new CallCommand(
                      new Identifier("putint", dummyPosition()),
                      new SingleActualParameterSequence(
                        new ConstActualParameter(new IntegerExpression(new IntegerLiteral("1", dummyPosition()), dummyPosition()), dummyPosition()), dummyPosition()),
                      dummyPosition()),
                    new CallCommand(
                      new Identifier("putint", dummyPosition()),
                      new SingleActualParameterSequence(new ConstActualParameter(new IntegerExpression(new IntegerLiteral("2", dummyPosition()), dummyPosition()), dummyPosition()), dummyPosition()),
                      dummyPosition()),
                    dummyPosition()),
                  dummyPosition()),
                  dummyPosition()),
                  dummyPosition());
    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testOddDegenerate() {
    String filename = "samples/odd_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = 
      new Program(new LetCommand(new SequentialDeclaration(
              new VarDeclaration(
                new Identifier("n", dummyPosition()),
                new SimpleTypeDenoter(new Identifier("Integer", dummyPosition()), dummyPosition()),
                dummyPosition()),
              new FuncDeclaration(
                new Identifier("odd", dummyPosition()),
                new SingleFormalParameterSequence(new ConstFormalParameter(new Identifier("n", dummyPosition()), 
                    new SimpleTypeDenoter(new Identifier("Integer", dummyPosition()), dummyPosition()), dummyPosition()), dummyPosition()),
                new SimpleTypeDenoter(new Identifier("Boolean", dummyPosition()), dummyPosition()),
                new BinaryExpression(
                  new BinaryExpression(
                    new VnameExpression(new SimpleVname(new Identifier("n", dummyPosition()), dummyPosition()), dummyPosition()),
                    new Operator("//", dummyPosition()),
                    new IntegerExpression(new IntegerLiteral("2", dummyPosition()), dummyPosition()),
                    dummyPosition()),
                  new Operator("\\=", dummyPosition()),
                  new IntegerExpression(new IntegerLiteral("0", dummyPosition()), dummyPosition()),
                  dummyPosition()),
                dummyPosition()),
              dummyPosition()),
              new SequentialCommand(
                  new CallCommand(
                    new Identifier("getint", dummyPosition()),
                    new SingleActualParameterSequence(
                      new VarActualParameter(
                        new SimpleVname(
                          new Identifier("n", dummyPosition()), dummyPosition()), dummyPosition()), dummyPosition()),
                    dummyPosition()),
                  new IfCommand(
                    new CallExpression(
                      new Identifier("odd", dummyPosition()),
                      new SingleActualParameterSequence(
                        new ConstActualParameter(
                          new VnameExpression(
                            new SimpleVname(
                              new Identifier("n", dummyPosition()), dummyPosition()), dummyPosition()), dummyPosition()), dummyPosition()),
                      dummyPosition()),
                    new CallCommand(
                      new Identifier("putint", dummyPosition()),
                      new SingleActualParameterSequence(
                        new ConstActualParameter(new IntegerExpression(new IntegerLiteral("1", dummyPosition()), dummyPosition()), dummyPosition()), dummyPosition()),
                      dummyPosition()),
                    new CallCommand(
                      new Identifier("putint", dummyPosition()),
                      new SingleActualParameterSequence(new ConstActualParameter(new IntegerExpression(new IntegerLiteral("2", dummyPosition()), dummyPosition()), dummyPosition()), dummyPosition()),
                      dummyPosition()),
                    dummyPosition()),
                  dummyPosition()),
                  dummyPosition()),
                  dummyPosition());
    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testSumProc() {
    String filename = "samples/sum_proc.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram =
      new Program(
          new LetCommand(
            new SequentialDeclaration(
              new SequentialDeclaration(
                new SequentialDeclaration(
                  new VarDeclaration(
                    new Identifier(
                      "x",
                      dummyPosition()
                      ),
                    new SimpleTypeDenoter(
                      new Identifier(
                        "Integer",
                        dummyPosition()
                        ),
                      dummyPosition()
                      ),
                    dummyPosition()
                    ),
                  new VarDeclaration(
                    new Identifier(
                      "y",
                      dummyPosition()
                      ),
                    new SimpleTypeDenoter(
                      new Identifier(
                        "Integer",
                        dummyPosition()
                        ),
                      dummyPosition()
                      ),
                    dummyPosition()
                    ),
                  dummyPosition()
                    ),
                  new VarDeclaration(
                      new Identifier(
                        "s",
                        dummyPosition()
                        ),
                      new SimpleTypeDenoter(
                        new Identifier(
                          "Integer",
                          dummyPosition()
                          ),
                        dummyPosition()
                        ),
                      dummyPosition()
                      ),
                  dummyPosition()
                    ),
                  new ProcDeclaration(
                      new Identifier(
                        "add",
                        dummyPosition()
                        ),
                      new MultipleFormalParameterSequence(
                        new ConstFormalParameter(
                          new Identifier(
                            "a",
                            dummyPosition()
                            ),
                          new SimpleTypeDenoter(
                            new Identifier(
                              "Integer",
                              dummyPosition()
                              ),
                            dummyPosition()
                            ),
                          dummyPosition()
                          ),
                        new MultipleFormalParameterSequence(
                          new ConstFormalParameter(
                            new Identifier(
                              "b",
                              dummyPosition()
                              ),
                            new SimpleTypeDenoter(
                              new Identifier(
                                "Integer",
                                dummyPosition()
                                ),
                              dummyPosition()
                              ),
                            dummyPosition()
                            ),
                          new SingleFormalParameterSequence(
                            new VarFormalParameter(
                              new Identifier(
                                "r",
                                dummyPosition()
                                ),
                              new SimpleTypeDenoter(
                                new Identifier(
                                  "Integer",
                                  dummyPosition()
                                  ),
                                dummyPosition()
                                ),
                              dummyPosition()
                              ),
                            dummyPosition()
                            ),
                          dummyPosition()
                            ),
                          dummyPosition()
                            ),
                          new AssignCommand(
                              new SimpleVname(
                                new Identifier(
                                  "r",
                                  dummyPosition()
                                  ),
                                dummyPosition()
                                ),
                              new BinaryExpression(
                                new VnameExpression(
                                  new SimpleVname(
                                    new Identifier(
                                      "a",
                                      dummyPosition()
                                      ),
                                    dummyPosition()
                                    ),
                                  dummyPosition()
                                  ),
                                new Operator(
                                  "+",
                                  dummyPosition()
                                  ),
                                new VnameExpression(
                                  new SimpleVname(
                                    new Identifier(
                                      "b",
                                      dummyPosition()
                                      ),
                                    dummyPosition()
                                    ),
                                  dummyPosition()
                                  ),
                                dummyPosition()
                                  ),
                                dummyPosition()
                                  ),
                                dummyPosition()
                                  ),
                                dummyPosition()
                                  ),
                                new SequentialCommand(
                                    new SequentialCommand(
                                      new SequentialCommand(
                                        new CallCommand(
                                          new Identifier(
                                            "getint",
                                            dummyPosition()
                                            ),
                                          new SingleActualParameterSequence(
                                            new VarActualParameter(
                                              new SimpleVname(
                                                new Identifier(
                                                  "x",
                                                  dummyPosition()
                                                  ),
                                                dummyPosition()
                                                ),
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                          dummyPosition()
                                          ),
                                new CallCommand(
                                    new Identifier(
                                      "getint",
                                      dummyPosition()
                                      ),
                                    new SingleActualParameterSequence(
                                      new VarActualParameter(
                                        new SimpleVname(
                                          new Identifier(
                                            "y",
                                            dummyPosition()
                                            ),
                                          dummyPosition()
                                          ),
                                        dummyPosition()
                                        ),
                                      dummyPosition()
                                      ),
                                    dummyPosition()
                                    ),
                                dummyPosition()
                                  ),
                                new CallCommand(
                                    new Identifier(
                                      "add",
                                      dummyPosition()
                                      ),
                                    new MultipleActualParameterSequence(
                                      new ConstActualParameter(
                                        new VnameExpression(
                                          new SimpleVname(
                                            new Identifier(
                                              "x",
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                          dummyPosition()
                                          ),
                                        dummyPosition()
                                        ),
                                      new MultipleActualParameterSequence(
                                        new ConstActualParameter(
                                          new VnameExpression(
                                            new SimpleVname(
                                              new Identifier(
                                                "y",
                                                dummyPosition()
                                                ),
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                          dummyPosition()
                                          ),
                                        new SingleActualParameterSequence(
                                          new VarActualParameter(
                                            new SimpleVname(
                                              new Identifier(
                                                "s",
                                                dummyPosition()
                                                ),
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                          dummyPosition()
                                          ),
                                        dummyPosition()
                                          ),
                                        dummyPosition()
                                          ),
                                        dummyPosition()
                                          ),
                                        dummyPosition()
                                          ),
                                        new CallCommand(
                                            new Identifier(
                                              "putint",
                                              dummyPosition()
                                              ),
                                            new SingleActualParameterSequence(
                                              new ConstActualParameter(
                                                new VnameExpression(
                                                  new SimpleVname(
                                                    new Identifier(
                                                      "s",
                                                      dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                  ),
                                                dummyPosition()
                                                ),
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                              ),
                                            dummyPosition()
                                              ),
                                            dummyPosition()
                                              ),
                                            dummyPosition()
                                              );


    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testSumProcDegenerate() {
    String filename = "samples/sum_proc_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram =
      new Program(
          new LetCommand(
            new SequentialDeclaration(
              new SequentialDeclaration(
                new SequentialDeclaration(
                  new VarDeclaration(
                    new Identifier(
                      "x",
                      dummyPosition()
                      ),
                    new SimpleTypeDenoter(
                      new Identifier(
                        "Integer",
                        dummyPosition()
                        ),
                      dummyPosition()
                      ),
                    dummyPosition()
                    ),
                  new VarDeclaration(
                    new Identifier(
                      "y",
                      dummyPosition()
                      ),
                    new SimpleTypeDenoter(
                      new Identifier(
                        "Integer",
                        dummyPosition()
                        ),
                      dummyPosition()
                      ),
                    dummyPosition()
                    ),
                  dummyPosition()
                    ),
                  new VarDeclaration(
                      new Identifier(
                        "s",
                        dummyPosition()
                        ),
                      new SimpleTypeDenoter(
                        new Identifier(
                          "Integer",
                          dummyPosition()
                          ),
                        dummyPosition()
                        ),
                      dummyPosition()
                      ),
                  dummyPosition()
                    ),
                  new ProcDeclaration(
                      new Identifier(
                        "add",
                        dummyPosition()
                        ),
                      new MultipleFormalParameterSequence(
                        new ConstFormalParameter(
                          new Identifier(
                            "a",
                            dummyPosition()
                            ),
                          new SimpleTypeDenoter(
                            new Identifier(
                              "Integer",
                              dummyPosition()
                              ),
                            dummyPosition()
                            ),
                          dummyPosition()
                          ),
                        new MultipleFormalParameterSequence(
                          new ConstFormalParameter(
                            new Identifier(
                              "b",
                              dummyPosition()
                              ),
                            new SimpleTypeDenoter(
                              new Identifier(
                                "Integer",
                                dummyPosition()
                                ),
                              dummyPosition()
                              ),
                            dummyPosition()
                            ),
                          new SingleFormalParameterSequence(
                            new VarFormalParameter(
                              new Identifier(
                                "r",
                                dummyPosition()
                                ),
                              new SimpleTypeDenoter(
                                new Identifier(
                                  "Integer",
                                  dummyPosition()
                                  ),
                                dummyPosition()
                                ),
                              dummyPosition()
                              ),
                            dummyPosition()
                            ),
                          dummyPosition()
                            ),
                          dummyPosition()
                            ),
                          new AssignCommand(
                              new SimpleVname(
                                new Identifier(
                                  "r",
                                  dummyPosition()
                                  ),
                                dummyPosition()
                                ),
                              new BinaryExpression(
                                new VnameExpression(
                                  new SimpleVname(
                                    new Identifier(
                                      "a",
                                      dummyPosition()
                                      ),
                                    dummyPosition()
                                    ),
                                  dummyPosition()
                                  ),
                                new Operator(
                                  "+",
                                  dummyPosition()
                                  ),
                                new VnameExpression(
                                  new SimpleVname(
                                    new Identifier(
                                      "b",
                                      dummyPosition()
                                      ),
                                    dummyPosition()
                                    ),
                                  dummyPosition()
                                  ),
                                dummyPosition()
                                  ),
                                dummyPosition()
                                  ),
                                dummyPosition()
                                  ),
                                dummyPosition()
                                  ),
                                new SequentialCommand(
                                    new SequentialCommand(
                                      new SequentialCommand(
                                        new CallCommand(
                                          new Identifier(
                                            "getint",
                                            dummyPosition()
                                            ),
                                          new SingleActualParameterSequence(
                                            new VarActualParameter(
                                              new SimpleVname(
                                                new Identifier(
                                                  "x",
                                                  dummyPosition()
                                                  ),
                                                dummyPosition()
                                                ),
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                          dummyPosition()
                                          ),
                                new CallCommand(
                                    new Identifier(
                                      "getint",
                                      dummyPosition()
                                      ),
                                    new SingleActualParameterSequence(
                                      new VarActualParameter(
                                        new SimpleVname(
                                          new Identifier(
                                            "y",
                                            dummyPosition()
                                            ),
                                          dummyPosition()
                                          ),
                                        dummyPosition()
                                        ),
                                      dummyPosition()
                                      ),
                                    dummyPosition()
                                    ),
                                dummyPosition()
                                  ),
                                new CallCommand(
                                    new Identifier(
                                      "add",
                                      dummyPosition()
                                      ),
                                    new MultipleActualParameterSequence(
                                      new ConstActualParameter(
                                        new VnameExpression(
                                          new SimpleVname(
                                            new Identifier(
                                              "x",
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                          dummyPosition()
                                          ),
                                        dummyPosition()
                                        ),
                                      new MultipleActualParameterSequence(
                                        new ConstActualParameter(
                                          new VnameExpression(
                                            new SimpleVname(
                                              new Identifier(
                                                "y",
                                                dummyPosition()
                                                ),
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                          dummyPosition()
                                          ),
                                        new SingleActualParameterSequence(
                                          new VarActualParameter(
                                            new SimpleVname(
                                              new Identifier(
                                                "s",
                                                dummyPosition()
                                                ),
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                          dummyPosition()
                                          ),
                                        dummyPosition()
                                          ),
                                        dummyPosition()
                                          ),
                                        dummyPosition()
                                          ),
                                        dummyPosition()
                                          ),
                                        new CallCommand(
                                            new Identifier(
                                              "putint",
                                              dummyPosition()
                                              ),
                                            new SingleActualParameterSequence(
                                              new ConstActualParameter(
                                                new VnameExpression(
                                                  new SimpleVname(
                                                    new Identifier(
                                                      "s",
                                                      dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                  ),
                                                dummyPosition()
                                                ),
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                              ),
                                            dummyPosition()
                                              ),
                                            dummyPosition()
                                              ),
                                            dummyPosition()
                                              );

    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testPower() {
    String filename = "samples/power.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram =
      new Program(
          new LetCommand(
            new SequentialDeclaration(
              new SequentialDeclaration(
                new VarDeclaration(
                  new Identifier(
                    "m",
                    dummyPosition()
                    ),
                  new SimpleTypeDenoter(
                    new Identifier(
                      "Integer",
                      dummyPosition()
                      ),
                    dummyPosition()
                    ),
                  dummyPosition()
                  ),
                new VarDeclaration(
                  new Identifier(
                    "n",
                    dummyPosition()
                    ),
                  new SimpleTypeDenoter(
                    new Identifier(
                      "Integer",
                      dummyPosition()
                      ),
                    dummyPosition()
                    ),
                  dummyPosition()
                  ),
                dummyPosition()
                  ),
                new FuncDeclaration(
                    new Identifier(
                      "power",
                      dummyPosition()
                      ),
                    new MultipleFormalParameterSequence(
                      new ConstFormalParameter(
                        new Identifier(
                          "a",
                          dummyPosition()
                          ),
                        new SimpleTypeDenoter(
                          new Identifier(
                            "Integer",
                            dummyPosition()
                            ),
                          dummyPosition()
                          ),
                        dummyPosition()
                        ),
                      new SingleFormalParameterSequence(
                        new ConstFormalParameter(
                          new Identifier(
                            "b",
                            dummyPosition()
                            ),
                          new SimpleTypeDenoter(
                            new Identifier(
                              "Integer",
                              dummyPosition()
                              ),
                            dummyPosition()
                            ),
                          dummyPosition()
                          ),
                        dummyPosition()
                        ),
                      dummyPosition()
                        ),
                      new SimpleTypeDenoter(
                          new Identifier(
                            "Integer",
                            dummyPosition()
                            ),
                          dummyPosition()
                          ),
                      new IfExpression(
                          new BinaryExpression(
                            new VnameExpression(
                              new SimpleVname(
                                new Identifier(
                                  "b",
                                  dummyPosition()
                                  ),
                                dummyPosition()
                                ),
                              dummyPosition()
                              ),
                            new Operator(
                              "=",
                              dummyPosition()
                              ),
                            new IntegerExpression(
                              new IntegerLiteral(
                                "0",
                                dummyPosition()
                                ),
                              dummyPosition()
                              ),
                            dummyPosition()
                              ),
                            new IntegerExpression(
                                new IntegerLiteral(
                                  "1",
                                  dummyPosition()
                                  ),
                                dummyPosition()
                                ),
                            new BinaryExpression(
                                new VnameExpression(
                                  new SimpleVname(
                                    new Identifier(
                                      "a",
                                      dummyPosition()
                                      ),
                                    dummyPosition()
                                    ),
                                  dummyPosition()
                                  ),
                                new Operator(
                                  "*",
                                  dummyPosition()
                                  ),
                                new CallExpression(
                                  new Identifier(
                                    "power",
                                    dummyPosition()
                                    ),
                                  new MultipleActualParameterSequence(
                                    new ConstActualParameter(
                                      new VnameExpression(
                                        new SimpleVname(
                                          new Identifier(
                                            "a",
                                            dummyPosition()
                                            ),
                                          dummyPosition()
                                          ),
                                        dummyPosition()
                                        ),
                                      dummyPosition()
                                      ),
                                    new SingleActualParameterSequence(
                                      new ConstActualParameter(
                                        new BinaryExpression(
                                          new VnameExpression(
                                            new SimpleVname(
                                              new Identifier(
                                                "b",
                                                dummyPosition()
                                                ),
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                          new Operator(
                                            "-",
                                            dummyPosition()
                                            ),
                                          new IntegerExpression(
                                            new IntegerLiteral(
                                              "1",
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                          dummyPosition()
                                            ),
                                          dummyPosition()
                                            ),
                                          dummyPosition()
                                            ),
                                          dummyPosition()
                                            ),
                                          dummyPosition()
                                            ),
                                          dummyPosition()
                                            ),
                                          dummyPosition()
                                            ),
                                          dummyPosition()
                                            ),
                                          dummyPosition()
                                            ),
                                          new SequentialCommand(
                                              new SequentialCommand(
                                                new CallCommand(
                                                  new Identifier(
                                                    "getint",
                                                    dummyPosition()
                                                    ),
                                                  new SingleActualParameterSequence(
                                                    new VarActualParameter(
                                                      new SimpleVname(
                                                        new Identifier(
                                                          "m",
                                                          dummyPosition()
                                                          ),
                                                        dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                  ),
                                          new CallCommand(
                                              new Identifier(
                                                "getint",
                                                dummyPosition()
                                                ),
                                              new SingleActualParameterSequence(
                                                new VarActualParameter(
                                                  new SimpleVname(
                                                    new Identifier(
                                                      "n",
                                                      dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                  ),
                                                dummyPosition()
                                                ),
                                              dummyPosition()
                                              ),
                                          dummyPosition()
                                            ),
                                          new CallCommand(
                                              new Identifier(
                                                "putint",
                                                dummyPosition()
                                                ),
                                              new SingleActualParameterSequence(
                                                new ConstActualParameter(
                                                  new CallExpression(
                                                    new Identifier(
                                                      "power",
                                                      dummyPosition()
                                                      ),
                                                    new MultipleActualParameterSequence(
                                                      new ConstActualParameter(
                                                        new VnameExpression(
                                                          new SimpleVname(
                                                            new Identifier(
                                                              "m",
                                                              dummyPosition()
                                                              ),
                                                            dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                          ),
                                                        dummyPosition()
                                                        ),
                                                      new SingleActualParameterSequence(
                                                        new ConstActualParameter(
                                                          new VnameExpression(
                                                            new SimpleVname(
                                                              new Identifier(
                                                                "n",
                                                                dummyPosition()
                                                                ),
                                                              dummyPosition()
                                                              ),
                                                            dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                          ),
                                                        dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                        );

    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testPowerDegenerate() {
    String filename = "samples/power_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram =
      new Program(
          new LetCommand(
            new SequentialDeclaration(
              new SequentialDeclaration(
                new VarDeclaration(
                  new Identifier(
                    "m",
                    dummyPosition()
                    ),
                  new SimpleTypeDenoter(
                    new Identifier(
                      "Integer",
                      dummyPosition()
                      ),
                    dummyPosition()
                    ),
                  dummyPosition()
                  ),
                new VarDeclaration(
                  new Identifier(
                    "n",
                    dummyPosition()
                    ),
                  new SimpleTypeDenoter(
                    new Identifier(
                      "Integer",
                      dummyPosition()
                      ),
                    dummyPosition()
                    ),
                  dummyPosition()
                  ),
                dummyPosition()
                  ),
                new FuncDeclaration(
                    new Identifier(
                      "power",
                      dummyPosition()
                      ),
                    new MultipleFormalParameterSequence(
                      new ConstFormalParameter(
                        new Identifier(
                          "a",
                          dummyPosition()
                          ),
                        new SimpleTypeDenoter(
                          new Identifier(
                            "Integer",
                            dummyPosition()
                            ),
                          dummyPosition()
                          ),
                        dummyPosition()
                        ),
                      new SingleFormalParameterSequence(
                        new ConstFormalParameter(
                          new Identifier(
                            "b",
                            dummyPosition()
                            ),
                          new SimpleTypeDenoter(
                            new Identifier(
                              "Integer",
                              dummyPosition()
                              ),
                            dummyPosition()
                            ),
                          dummyPosition()
                          ),
                        dummyPosition()
                        ),
                      dummyPosition()
                        ),
                      new SimpleTypeDenoter(
                          new Identifier(
                            "Integer",
                            dummyPosition()
                            ),
                          dummyPosition()
                          ),
                      new IfExpression(
                          new BinaryExpression(
                            new VnameExpression(
                              new SimpleVname(
                                new Identifier(
                                  "b",
                                  dummyPosition()
                                  ),
                                dummyPosition()
                                ),
                              dummyPosition()
                              ),
                            new Operator(
                              "=",
                              dummyPosition()
                              ),
                            new IntegerExpression(
                              new IntegerLiteral(
                                "0",
                                dummyPosition()
                                ),
                              dummyPosition()
                              ),
                            dummyPosition()
                              ),
                            new IntegerExpression(
                                new IntegerLiteral(
                                  "1",
                                  dummyPosition()
                                  ),
                                dummyPosition()
                                ),
                            new BinaryExpression(
                                new VnameExpression(
                                  new SimpleVname(
                                    new Identifier(
                                      "a",
                                      dummyPosition()
                                      ),
                                    dummyPosition()
                                    ),
                                  dummyPosition()
                                  ),
                                new Operator(
                                  "*",
                                  dummyPosition()
                                  ),
                                new CallExpression(
                                  new Identifier(
                                    "power",
                                    dummyPosition()
                                    ),
                                  new MultipleActualParameterSequence(
                                    new ConstActualParameter(
                                      new VnameExpression(
                                        new SimpleVname(
                                          new Identifier(
                                            "a",
                                            dummyPosition()
                                            ),
                                          dummyPosition()
                                          ),
                                        dummyPosition()
                                        ),
                                      dummyPosition()
                                      ),
                                    new SingleActualParameterSequence(
                                      new ConstActualParameter(
                                        new BinaryExpression(
                                          new VnameExpression(
                                            new SimpleVname(
                                              new Identifier(
                                                "b",
                                                dummyPosition()
                                                ),
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                          new Operator(
                                            "-",
                                            dummyPosition()
                                            ),
                                          new IntegerExpression(
                                            new IntegerLiteral(
                                              "1",
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                          dummyPosition()
                                            ),
                                          dummyPosition()
                                            ),
                                          dummyPosition()
                                            ),
                                          dummyPosition()
                                            ),
                                          dummyPosition()
                                            ),
                                          dummyPosition()
                                            ),
                                          dummyPosition()
                                            ),
                                          dummyPosition()
                                            ),
                                          dummyPosition()
                                            ),
                                          new SequentialCommand(
                                              new SequentialCommand(
                                                new CallCommand(
                                                  new Identifier(
                                                    "getint",
                                                    dummyPosition()
                                                    ),
                                                  new SingleActualParameterSequence(
                                                    new VarActualParameter(
                                                      new SimpleVname(
                                                        new Identifier(
                                                          "m",
                                                          dummyPosition()
                                                          ),
                                                        dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                  ),
                                          new CallCommand(
                                              new Identifier(
                                                "getint",
                                                dummyPosition()
                                                ),
                                              new SingleActualParameterSequence(
                                                new VarActualParameter(
                                                  new SimpleVname(
                                                    new Identifier(
                                                      "n",
                                                      dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                  ),
                                                dummyPosition()
                                                ),
                                              dummyPosition()
                                              ),
                                          dummyPosition()
                                            ),
                                          new CallCommand(
                                              new Identifier(
                                                "putint",
                                                dummyPosition()
                                                ),
                                              new SingleActualParameterSequence(
                                                new ConstActualParameter(
                                                  new CallExpression(
                                                    new Identifier(
                                                      "power",
                                                      dummyPosition()
                                                      ),
                                                    new MultipleActualParameterSequence(
                                                      new ConstActualParameter(
                                                        new VnameExpression(
                                                          new SimpleVname(
                                                            new Identifier(
                                                              "m",
                                                              dummyPosition()
                                                              ),
                                                            dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                          ),
                                                        dummyPosition()
                                                        ),
                                                      new SingleActualParameterSequence(
                                                        new ConstActualParameter(
                                                          new VnameExpression(
                                                            new SimpleVname(
                                                              new Identifier(
                                                                "n",
                                                                dummyPosition()
                                                                ),
                                                              dummyPosition()
                                                              ),
                                                            dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                          ),
                                                        dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                        );

    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);

  }

  public void testFactorial() {
    String filename = "samples/factorial.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram =
      new Program(
          new LetCommand(
            new SequentialDeclaration(
              new SequentialDeclaration(
                new VarDeclaration(
                  new Identifier(
                    "n",
                    dummyPosition()
                    ),
                  new SimpleTypeDenoter(
                    new Identifier(
                      "Integer",
                      dummyPosition()
                      ),
                    dummyPosition()
                    ),
                  dummyPosition()
                  ),
                new FuncDeclaration(
                  new Identifier(
                    "factorial",
                    dummyPosition()
                    ),
                  new SingleFormalParameterSequence(
                    new ConstFormalParameter(
                      new Identifier(
                        "n",
                        dummyPosition()
                        ),
                      new SimpleTypeDenoter(
                        new Identifier(
                          "Integer",
                          dummyPosition()
                          ),
                        dummyPosition()
                        ),
                      dummyPosition()
                      ),
                    dummyPosition()
                    ),
                  new SimpleTypeDenoter(
                      new Identifier(
                        "Integer",
                        dummyPosition()
                        ),
                      dummyPosition()
                      ),
                  new IfExpression(
                      new BinaryExpression(
                        new VnameExpression(
                          new SimpleVname(
                            new Identifier(
                              "n",
                              dummyPosition()
                              ),
                            dummyPosition()
                            ),
                          dummyPosition()
                          ),
                        new Operator(
                          "<=",
                          dummyPosition()
                          ),
                        new IntegerExpression(
                          new IntegerLiteral(
                            "0",
                            dummyPosition()
                            ),
                          dummyPosition()
                          ),
                        dummyPosition()
                          ),
                        new IntegerExpression(
                            new IntegerLiteral(
                              "1",
                              dummyPosition()
                              ),
                            dummyPosition()
                            ),
                        new BinaryExpression(
                            new VnameExpression(
                              new SimpleVname(
                                new Identifier(
                                  "n",
                                  dummyPosition()
                                  ),
                                dummyPosition()
                                ),
                              dummyPosition()
                              ),
                            new Operator(
                              "*",
                              dummyPosition()
                              ),
                            new CallExpression(
                              new Identifier(
                                "factorial",
                                dummyPosition()
                                ),
                              new SingleActualParameterSequence(
                                new ConstActualParameter(
                                  new BinaryExpression(
                                    new VnameExpression(
                                      new SimpleVname(
                                        new Identifier(
                                          "n",
                                          dummyPosition()
                                          ),
                                        dummyPosition()
                                        ),
                                      dummyPosition()
                                      ),
                                    new Operator(
                                      "-",
                                      dummyPosition()
                                      ),
                                    new IntegerExpression(
                                      new IntegerLiteral(
                                        "1",
                                        dummyPosition()
                                        ),
                                      dummyPosition()
                                      ),
                                    dummyPosition()
                                      ),
                                    dummyPosition()
                                      ),
                                    dummyPosition()
                                      ),
                                    dummyPosition()
                                      ),
                                    dummyPosition()
                                      ),
                                    dummyPosition()
                                      ),
                                    dummyPosition()
                                      ),
                                    dummyPosition()
                                      ),
                                    new ProcDeclaration(
                                        new Identifier(
                                          "readnumber",
                                          dummyPosition()
                                          ),
                                        new SingleFormalParameterSequence(
                                          new VarFormalParameter(
                                            new Identifier(
                                              "n",
                                              dummyPosition()
                                              ),
                                            new SimpleTypeDenoter(
                                              new Identifier(
                                                "Integer",
                                                dummyPosition()
                                                ),
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                          dummyPosition()
                                          ),
                                        new CallCommand(
                                            new Identifier(
                                              "getint",
                                              dummyPosition()
                                              ),
                                            new SingleActualParameterSequence(
                                              new VarActualParameter(
                                                new SimpleVname(
                                                  new Identifier(
                                                    "n",
                                                    dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                  ),
                                                dummyPosition()
                                                ),
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                        dummyPosition()
                                          ),
                                        dummyPosition()
                                          ),
                                        new SequentialCommand(
                                            new SequentialCommand(
                                              new SequentialCommand(
                                                new CallCommand(
                                                  new Identifier(
                                                    "readnumber",
                                                    dummyPosition()
                                                    ),
                                                  new SingleActualParameterSequence(
                                                    new VarActualParameter(
                                                      new SimpleVname(
                                                        new Identifier(
                                                          "n",
                                                          dummyPosition()
                                                          ),
                                                        dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                  ),
                                        new CallCommand(
                                            new Identifier(
                                              "puteol",
                                              dummyPosition()
                                              ),
                                            new EmptyActualParameterSequence(
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                        dummyPosition()
                                          ),
                                        new CallCommand(
                                            new Identifier(
                                              "puteol",
                                              dummyPosition()
                                              ),
                                            new EmptyActualParameterSequence(
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                        dummyPosition()
                                          ),
                                        new CallCommand(
                                            new Identifier(
                                              "putint",
                                              dummyPosition()
                                              ),
                                            new SingleActualParameterSequence(
                                              new ConstActualParameter(
                                                new CallExpression(
                                                  new Identifier(
                                                    "factorial",
                                                    dummyPosition()
                                                    ),
                                                  new SingleActualParameterSequence(
                                                    new ConstActualParameter(
                                                      new VnameExpression(
                                                        new SimpleVname(
                                                          new Identifier(
                                                            "n",
                                                            dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                          ),
                                                        dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                    );

    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testFactorialDegenerate() {
    String filename = "samples/factorial_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram =
      new Program(
          new LetCommand(
            new SequentialDeclaration(
              new SequentialDeclaration(
                new VarDeclaration(
                  new Identifier(
                    "n",
                    dummyPosition()
                    ),
                  new SimpleTypeDenoter(
                    new Identifier(
                      "Integer",
                      dummyPosition()
                      ),
                    dummyPosition()
                    ),
                  dummyPosition()
                  ),
                new FuncDeclaration(
                  new Identifier(
                    "factorial",
                    dummyPosition()
                    ),
                  new SingleFormalParameterSequence(
                    new ConstFormalParameter(
                      new Identifier(
                        "n",
                        dummyPosition()
                        ),
                      new SimpleTypeDenoter(
                        new Identifier(
                          "Integer",
                          dummyPosition()
                          ),
                        dummyPosition()
                        ),
                      dummyPosition()
                      ),
                    dummyPosition()
                    ),
                  new SimpleTypeDenoter(
                      new Identifier(
                        "Integer",
                        dummyPosition()
                        ),
                      dummyPosition()
                      ),
                  new IfExpression(
                      new BinaryExpression(
                        new VnameExpression(
                          new SimpleVname(
                            new Identifier(
                              "n",
                              dummyPosition()
                              ),
                            dummyPosition()
                            ),
                          dummyPosition()
                          ),
                        new Operator(
                          "<=",
                          dummyPosition()
                          ),
                        new IntegerExpression(
                          new IntegerLiteral(
                            "0",
                            dummyPosition()
                            ),
                          dummyPosition()
                          ),
                        dummyPosition()
                          ),
                        new IntegerExpression(
                            new IntegerLiteral(
                              "1",
                              dummyPosition()
                              ),
                            dummyPosition()
                            ),
                        new BinaryExpression(
                            new VnameExpression(
                              new SimpleVname(
                                new Identifier(
                                  "n",
                                  dummyPosition()
                                  ),
                                dummyPosition()
                                ),
                              dummyPosition()
                              ),
                            new Operator(
                              "*",
                              dummyPosition()
                              ),
                            new CallExpression(
                              new Identifier(
                                "factorial",
                                dummyPosition()
                                ),
                              new SingleActualParameterSequence(
                                new ConstActualParameter(
                                  new BinaryExpression(
                                    new VnameExpression(
                                      new SimpleVname(
                                        new Identifier(
                                          "n",
                                          dummyPosition()
                                          ),
                                        dummyPosition()
                                        ),
                                      dummyPosition()
                                      ),
                                    new Operator(
                                      "-",
                                      dummyPosition()
                                      ),
                                    new IntegerExpression(
                                      new IntegerLiteral(
                                        "1",
                                        dummyPosition()
                                        ),
                                      dummyPosition()
                                      ),
                                    dummyPosition()
                                      ),
                                    dummyPosition()
                                      ),
                                    dummyPosition()
                                      ),
                                    dummyPosition()
                                      ),
                                    dummyPosition()
                                      ),
                                    dummyPosition()
                                      ),
                                    dummyPosition()
                                      ),
                                    dummyPosition()
                                      ),
                                    new ProcDeclaration(
                                        new Identifier(
                                          "readnumber",
                                          dummyPosition()
                                          ),
                                        new SingleFormalParameterSequence(
                                          new VarFormalParameter(
                                            new Identifier(
                                              "n",
                                              dummyPosition()
                                              ),
                                            new SimpleTypeDenoter(
                                              new Identifier(
                                                "Integer",
                                                dummyPosition()
                                                ),
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                          dummyPosition()
                                          ),
                                        new CallCommand(
                                            new Identifier(
                                              "getint",
                                              dummyPosition()
                                              ),
                                            new SingleActualParameterSequence(
                                              new VarActualParameter(
                                                new SimpleVname(
                                                  new Identifier(
                                                    "n",
                                                    dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                  ),
                                                dummyPosition()
                                                ),
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                        dummyPosition()
                                          ),
                                        dummyPosition()
                                          ),
                                        new SequentialCommand(
                                            new SequentialCommand(
                                              new SequentialCommand(
                                                new CallCommand(
                                                  new Identifier(
                                                    "readnumber",
                                                    dummyPosition()
                                                    ),
                                                  new SingleActualParameterSequence(
                                                    new VarActualParameter(
                                                      new SimpleVname(
                                                        new Identifier(
                                                          "n",
                                                          dummyPosition()
                                                          ),
                                                        dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                  ),
                                        new CallCommand(
                                            new Identifier(
                                              "puteol",
                                              dummyPosition()
                                              ),
                                            new EmptyActualParameterSequence(
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                        dummyPosition()
                                          ),
                                        new CallCommand(
                                            new Identifier(
                                              "puteol",
                                              dummyPosition()
                                              ),
                                            new EmptyActualParameterSequence(
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                        dummyPosition()
                                          ),
                                        new CallCommand(
                                            new Identifier(
                                              "putint",
                                              dummyPosition()
                                              ),
                                            new SingleActualParameterSequence(
                                              new ConstActualParameter(
                                                new CallExpression(
                                                  new Identifier(
                                                    "factorial",
                                                    dummyPosition()
                                                    ),
                                                  new SingleActualParameterSequence(
                                                    new ConstActualParameter(
                                                      new VnameExpression(
                                                        new SimpleVname(
                                                          new Identifier(
                                                            "n",
                                                            dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                          ),
                                                        dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                    );

    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testRecord() {
    String filename = "samples/record.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = 
      new Program(
          new LetCommand(
            new SequentialDeclaration(
              new SequentialDeclaration(
                new TypeDeclaration(
                  new Identifier(
                    "Date",
                    dummyPosition()
                    ),
                  new RecordTypeDenoter(
                    new MultipleFieldTypeDenoter(
                      new Identifier(
                        "y",
                        dummyPosition()
                        ),
                      new SimpleTypeDenoter(
                        new Identifier(
                          "Integer",
                          dummyPosition()
                          ),
                        dummyPosition()
                        ),
                      new MultipleFieldTypeDenoter(
                        new Identifier(
                          "m",
                          dummyPosition()
                          ),
                        new SimpleTypeDenoter(
                          new Identifier(
                            "Integer",
                            dummyPosition()
                            ),
                          dummyPosition()
                          ),
                        new SingleFieldTypeDenoter(
                          new Identifier(
                            "d",
                            dummyPosition()
                            ),
                          new SimpleTypeDenoter(
                            new Identifier(
                              "Integer",
                              dummyPosition()
                              ),
                            dummyPosition()
                            ),
                          dummyPosition()
                          ),
                        dummyPosition()
                          ),
                        dummyPosition()
                          ),
                        dummyPosition()
                          ),
                        dummyPosition()
                          ),
                        new VarDeclaration(
                            new Identifier(
                              "today",
                              dummyPosition()
                              ),
                            new SimpleTypeDenoter(
                              new Identifier(
                                "Date",
                                dummyPosition()
                                ),
                              dummyPosition()
                              ),
                            dummyPosition()
                            ),
                        dummyPosition()
                          ),
                        new VarDeclaration(
                            new Identifier(
                              "tomorrow",
                              dummyPosition()
                              ),
                            new SimpleTypeDenoter(
                              new Identifier(
                                "Date",
                                dummyPosition()
                                ),
                              dummyPosition()
                              ),
                            dummyPosition()
                            ),
                        dummyPosition()
                          ),
                        new SequentialCommand(
                            new SequentialCommand(
                              new SequentialCommand(
                                new SequentialCommand(
                                  new SequentialCommand(
                                    new SequentialCommand(
                                      new SequentialCommand(
                                        new AssignCommand(
                                          new SimpleVname(
                                            new Identifier(
                                              "today",
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                          new RecordExpression(
                                            new MultipleRecordAggregate(
                                              new Identifier(
                                                "y",
                                                dummyPosition()
                                                ),
                                              new IntegerExpression(
                                                new IntegerLiteral(
                                                  "2021",
                                                  dummyPosition()
                                                  ),
                                                dummyPosition()
                                                ),
                                              new MultipleRecordAggregate(
                                                new Identifier(
                                                  "m",
                                                  dummyPosition()
                                                  ),
                                                new IntegerExpression(
                                                  new IntegerLiteral(
                                                    "1",
                                                    dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                  ),
                                                new SingleRecordAggregate(
                                                  new Identifier(
                                                    "d",
                                                    dummyPosition()
                                                    ),
                                                  new IntegerExpression(
                                                    new IntegerLiteral(
                                                      "12",
                                                      dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                  ),
                                                dummyPosition()
                                                  ),
                                                dummyPosition()
                                                  ),
                                                dummyPosition()
                                                  ),
                                                dummyPosition()
                                                  ),
                                                new CallCommand(
                                                    new Identifier(
                                                      "putint",
                                                      dummyPosition()
                                                      ),
                                                    new SingleActualParameterSequence(
                                                      new ConstActualParameter(
                                                        new VnameExpression(
                                                          new DotVname(
                                                            new SimpleVname(
                                                              new Identifier(
                                                                "today",
                                                                dummyPosition()
                                                                ),
                                                              dummyPosition()
                                                              ),
                                                            new Identifier(
                                                              "y",
                                                              dummyPosition()
                                                              ),
                                                            dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                          ),
                                                        dummyPosition()
                                                        ),
                                                dummyPosition()
                                                  ),
                                                dummyPosition()
                                                  ),
                                                dummyPosition()
                                                  ),
                                                new CallCommand(
                                                    new Identifier(
                                                      "putint",
                                                      dummyPosition()
                                                      ),
                                                    new SingleActualParameterSequence(
                                                      new ConstActualParameter(
                                                        new VnameExpression(
                                                          new DotVname(
                                                            new SimpleVname(
                                                              new Identifier(
                                                                "today",
                                                                dummyPosition()
                                                                ),
                                                              dummyPosition()
                                                              ),
                                                            new Identifier(
                                                              "m",
                                                              dummyPosition()
                                                              ),
                                                            dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                          ),
                                                        dummyPosition()
                                                        ),
                                                dummyPosition()
                                                  ),
                                                dummyPosition()
                                                  ),
                                                dummyPosition()
                                                  ),
                                                new CallCommand(
                                                    new Identifier(
                                                      "putint",
                                                      dummyPosition()
                                                      ),
                                                    new SingleActualParameterSequence(
                                                      new ConstActualParameter(
                                                        new VnameExpression(
                                                          new DotVname(
                                                            new SimpleVname(
                                                              new Identifier(
                                                                "today",
                                                                dummyPosition()
                                                                ),
                                                              dummyPosition()
                                                              ),
                                                            new Identifier(
                                                              "d",
                                                              dummyPosition()
                                                              ),
                                                            dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                          ),
                                                        dummyPosition()
                                                        ),
                                                dummyPosition()
                                                  ),
                                                dummyPosition()
                                                  ),
                                                dummyPosition()
                                                  ),
                                                new AssignCommand(
                                                    new SimpleVname(
                                                      new Identifier(
                                                        "tomorrow",
                                                        dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                      ),
                                                    new RecordExpression(
                                                      new MultipleRecordAggregate(
                                                        new Identifier(
                                                          "y",
                                                          dummyPosition()
                                                          ),
                                                        new VnameExpression(
                                                          new DotVname(
                                                            new SimpleVname(
                                                              new Identifier(
                                                                "today",
                                                                dummyPosition()
                                                                ),
                                                              dummyPosition()
                                                              ),
                                                            new Identifier(
                                                              "y",
                                                              dummyPosition()
                                                              ),
                                                            dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                          ),
                                                        new MultipleRecordAggregate(
                                                            new Identifier(
                                                              "m",
                                                              dummyPosition()
                                                              ),
                                                            new VnameExpression(
                                                              new DotVname(
                                                                new SimpleVname(
                                                                  new Identifier(
                                                                    "today",
                                                                    dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                  ),
                                                                new Identifier(
                                                                  "m",
                                                                  dummyPosition()
                                                                  ),
                                                                dummyPosition()
                                                                ),
                                                              dummyPosition()
                                                              ),
                                                            new SingleRecordAggregate(
                                                                new Identifier(
                                                                  "d",
                                                                  dummyPosition()
                                                                  ),
                                                                new BinaryExpression(
                                                                  new VnameExpression(
                                                                    new DotVname(
                                                                      new SimpleVname(
                                                                        new Identifier(
                                                                          "today",
                                                                          dummyPosition()
                                                                          ),
                                                                        dummyPosition()
                                                                        ),
                                                                      new Identifier(
                                                                        "d",
                                                                        dummyPosition()
                                                                        ),
                                                                      dummyPosition()
                                                                      ),
                                                                    dummyPosition()
                                                                    ),
                                                                  new Operator(
                                                                    "+",
                                                                    dummyPosition()
                                                                    ),
                                                                  new IntegerExpression(
                                                                      new IntegerLiteral(
                                                                        "1",
                                                                        dummyPosition()
                                                                        ),
                                                                      dummyPosition()
                                                                      ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  new CallCommand(
                                                                      new Identifier(
                                                                        "putint",
                                                                        dummyPosition()
                                                                        ),
                                                                      new SingleActualParameterSequence(
                                                                        new ConstActualParameter(
                                                                          new VnameExpression(
                                                                            new DotVname(
                                                                              new SimpleVname(
                                                                                new Identifier(
                                                                                  "tomorrow",
                                                                                  dummyPosition()
                                                                                  ),
                                                                                dummyPosition()
                                                                                ),
                                                                              new Identifier(
                                                                                "y",
                                                                                dummyPosition()
                                                                                ),
                                                                              dummyPosition()
                                                                              ),
                                                                            dummyPosition()
                                                                            ),
                                                                          dummyPosition()
                                                                          ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  new CallCommand(
                                                                      new Identifier(
                                                                        "putint",
                                                                        dummyPosition()
                                                                        ),
                                                                      new SingleActualParameterSequence(
                                                                        new ConstActualParameter(
                                                                          new VnameExpression(
                                                                            new DotVname(
                                                                              new SimpleVname(
                                                                                new Identifier(
                                                                                  "tomorrow",
                                                                                  dummyPosition()
                                                                                  ),
                                                                                dummyPosition()
                                                                                ),
                                                                              new Identifier(
                                                                                "m",
                                                                                dummyPosition()
                                                                                ),
                                                                              dummyPosition()
                                                                              ),
                                                                            dummyPosition()
                                                                            ),
                                                                          dummyPosition()
                                                                          ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  new CallCommand(
                                                                      new Identifier(
                                                                        "putint",
                                                                        dummyPosition()
                                                                        ),
                                                                      new SingleActualParameterSequence(
                                                                        new ConstActualParameter(
                                                                          new VnameExpression(
                                                                            new DotVname(
                                                                              new SimpleVname(
                                                                                new Identifier(
                                                                                  "tomorrow",
                                                                                  dummyPosition()
                                                                                  ),
                                                                                dummyPosition()
                                                                                ),
                                                                              new Identifier(
                                                                                "d",
                                                                                dummyPosition()
                                                                                ),
                                                                              dummyPosition()
                                                                              ),
                                                                            dummyPosition()
                                                                            ),
                                                                          dummyPosition()
                                                                          ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    );
    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testRecordDegenerate() {
    String filename = "samples/record_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = 
      new Program(
          new LetCommand(
            new SequentialDeclaration(
              new SequentialDeclaration(
                new TypeDeclaration(
                  new Identifier(
                    "Date",
                    dummyPosition()
                    ),
                  new RecordTypeDenoter(
                    new MultipleFieldTypeDenoter(
                      new Identifier(
                        "y",
                        dummyPosition()
                        ),
                      new SimpleTypeDenoter(
                        new Identifier(
                          "Integer",
                          dummyPosition()
                          ),
                        dummyPosition()
                        ),
                      new MultipleFieldTypeDenoter(
                        new Identifier(
                          "m",
                          dummyPosition()
                          ),
                        new SimpleTypeDenoter(
                          new Identifier(
                            "Integer",
                            dummyPosition()
                            ),
                          dummyPosition()
                          ),
                        new SingleFieldTypeDenoter(
                          new Identifier(
                            "d",
                            dummyPosition()
                            ),
                          new SimpleTypeDenoter(
                            new Identifier(
                              "Integer",
                              dummyPosition()
                              ),
                            dummyPosition()
                            ),
                          dummyPosition()
                          ),
                        dummyPosition()
                          ),
                        dummyPosition()
                          ),
                        dummyPosition()
                          ),
                        dummyPosition()
                          ),
                        new VarDeclaration(
                            new Identifier(
                              "today",
                              dummyPosition()
                              ),
                            new SimpleTypeDenoter(
                              new Identifier(
                                "Date",
                                dummyPosition()
                                ),
                              dummyPosition()
                              ),
                            dummyPosition()
                            ),
                        dummyPosition()
                          ),
                        new VarDeclaration(
                            new Identifier(
                              "tomorrow",
                              dummyPosition()
                              ),
                            new SimpleTypeDenoter(
                              new Identifier(
                                "Date",
                                dummyPosition()
                                ),
                              dummyPosition()
                              ),
                            dummyPosition()
                            ),
                        dummyPosition()
                          ),
                        new SequentialCommand(
                            new SequentialCommand(
                              new SequentialCommand(
                                new SequentialCommand(
                                  new SequentialCommand(
                                    new SequentialCommand(
                                      new SequentialCommand(
                                        new AssignCommand(
                                          new SimpleVname(
                                            new Identifier(
                                              "today",
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                          new RecordExpression(
                                            new MultipleRecordAggregate(
                                              new Identifier(
                                                "y",
                                                dummyPosition()
                                                ),
                                              new IntegerExpression(
                                                new IntegerLiteral(
                                                  "2021",
                                                  dummyPosition()
                                                  ),
                                                dummyPosition()
                                                ),
                                              new MultipleRecordAggregate(
                                                new Identifier(
                                                  "m",
                                                  dummyPosition()
                                                  ),
                                                new IntegerExpression(
                                                  new IntegerLiteral(
                                                    "1",
                                                    dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                  ),
                                                new SingleRecordAggregate(
                                                  new Identifier(
                                                    "d",
                                                    dummyPosition()
                                                    ),
                                                  new IntegerExpression(
                                                    new IntegerLiteral(
                                                      "12",
                                                      dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                  ),
                                                dummyPosition()
                                                  ),
                                                dummyPosition()
                                                  ),
                                                dummyPosition()
                                                  ),
                                                dummyPosition()
                                                  ),
                                                new CallCommand(
                                                    new Identifier(
                                                      "putint",
                                                      dummyPosition()
                                                      ),
                                                    new SingleActualParameterSequence(
                                                      new ConstActualParameter(
                                                        new VnameExpression(
                                                          new DotVname(
                                                            new SimpleVname(
                                                              new Identifier(
                                                                "today",
                                                                dummyPosition()
                                                                ),
                                                              dummyPosition()
                                                              ),
                                                            new Identifier(
                                                              "y",
                                                              dummyPosition()
                                                              ),
                                                            dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                          ),
                                                        dummyPosition()
                                                        ),
                                                dummyPosition()
                                                  ),
                                                dummyPosition()
                                                  ),
                                                dummyPosition()
                                                  ),
                                                new CallCommand(
                                                    new Identifier(
                                                      "putint",
                                                      dummyPosition()
                                                      ),
                                                    new SingleActualParameterSequence(
                                                      new ConstActualParameter(
                                                        new VnameExpression(
                                                          new DotVname(
                                                            new SimpleVname(
                                                              new Identifier(
                                                                "today",
                                                                dummyPosition()
                                                                ),
                                                              dummyPosition()
                                                              ),
                                                            new Identifier(
                                                              "m",
                                                              dummyPosition()
                                                              ),
                                                            dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                          ),
                                                        dummyPosition()
                                                        ),
                                                dummyPosition()
                                                  ),
                                                dummyPosition()
                                                  ),
                                                dummyPosition()
                                                  ),
                                                new CallCommand(
                                                    new Identifier(
                                                      "putint",
                                                      dummyPosition()
                                                      ),
                                                    new SingleActualParameterSequence(
                                                      new ConstActualParameter(
                                                        new VnameExpression(
                                                          new DotVname(
                                                            new SimpleVname(
                                                              new Identifier(
                                                                "today",
                                                                dummyPosition()
                                                                ),
                                                              dummyPosition()
                                                              ),
                                                            new Identifier(
                                                              "d",
                                                              dummyPosition()
                                                              ),
                                                            dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                          ),
                                                        dummyPosition()
                                                        ),
                                                dummyPosition()
                                                  ),
                                                dummyPosition()
                                                  ),
                                                dummyPosition()
                                                  ),
                                                new AssignCommand(
                                                    new SimpleVname(
                                                      new Identifier(
                                                        "tomorrow",
                                                        dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                      ),
                                                    new RecordExpression(
                                                      new MultipleRecordAggregate(
                                                        new Identifier(
                                                          "y",
                                                          dummyPosition()
                                                          ),
                                                        new VnameExpression(
                                                          new DotVname(
                                                            new SimpleVname(
                                                              new Identifier(
                                                                "today",
                                                                dummyPosition()
                                                                ),
                                                              dummyPosition()
                                                              ),
                                                            new Identifier(
                                                              "y",
                                                              dummyPosition()
                                                              ),
                                                            dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                          ),
                                                        new MultipleRecordAggregate(
                                                            new Identifier(
                                                              "m",
                                                              dummyPosition()
                                                              ),
                                                            new VnameExpression(
                                                              new DotVname(
                                                                new SimpleVname(
                                                                  new Identifier(
                                                                    "today",
                                                                    dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                  ),
                                                                new Identifier(
                                                                  "m",
                                                                  dummyPosition()
                                                                  ),
                                                                dummyPosition()
                                                                ),
                                                              dummyPosition()
                                                              ),
                                                            new SingleRecordAggregate(
                                                                new Identifier(
                                                                  "d",
                                                                  dummyPosition()
                                                                  ),
                                                                new BinaryExpression(
                                                                  new VnameExpression(
                                                                    new DotVname(
                                                                      new SimpleVname(
                                                                        new Identifier(
                                                                          "today",
                                                                          dummyPosition()
                                                                          ),
                                                                        dummyPosition()
                                                                        ),
                                                                      new Identifier(
                                                                        "d",
                                                                        dummyPosition()
                                                                        ),
                                                                      dummyPosition()
                                                                      ),
                                                                    dummyPosition()
                                                                    ),
                                                                  new Operator(
                                                                    "+",
                                                                    dummyPosition()
                                                                    ),
                                                                  new IntegerExpression(
                                                                      new IntegerLiteral(
                                                                        "1",
                                                                        dummyPosition()
                                                                        ),
                                                                      dummyPosition()
                                                                      ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  new CallCommand(
                                                                      new Identifier(
                                                                        "putint",
                                                                        dummyPosition()
                                                                        ),
                                                                      new SingleActualParameterSequence(
                                                                        new ConstActualParameter(
                                                                          new VnameExpression(
                                                                            new DotVname(
                                                                              new SimpleVname(
                                                                                new Identifier(
                                                                                  "tomorrow",
                                                                                  dummyPosition()
                                                                                  ),
                                                                                dummyPosition()
                                                                                ),
                                                                              new Identifier(
                                                                                "y",
                                                                                dummyPosition()
                                                                                ),
                                                                              dummyPosition()
                                                                              ),
                                                                            dummyPosition()
                                                                            ),
                                                                          dummyPosition()
                                                                          ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  new CallCommand(
                                                                      new Identifier(
                                                                        "putint",
                                                                        dummyPosition()
                                                                        ),
                                                                      new SingleActualParameterSequence(
                                                                        new ConstActualParameter(
                                                                          new VnameExpression(
                                                                            new DotVname(
                                                                              new SimpleVname(
                                                                                new Identifier(
                                                                                  "tomorrow",
                                                                                  dummyPosition()
                                                                                  ),
                                                                                dummyPosition()
                                                                                ),
                                                                              new Identifier(
                                                                                "m",
                                                                                dummyPosition()
                                                                                ),
                                                                              dummyPosition()
                                                                              ),
                                                                            dummyPosition()
                                                                            ),
                                                                          dummyPosition()
                                                                          ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  new CallCommand(
                                                                      new Identifier(
                                                                        "putint",
                                                                        dummyPosition()
                                                                        ),
                                                                      new SingleActualParameterSequence(
                                                                        new ConstActualParameter(
                                                                          new VnameExpression(
                                                                            new DotVname(
                                                                              new SimpleVname(
                                                                                new Identifier(
                                                                                  "tomorrow",
                                                                                  dummyPosition()
                                                                                  ),
                                                                                dummyPosition()
                                                                                ),
                                                                              new Identifier(
                                                                                "d",
                                                                                dummyPosition()
                                                                                ),
                                                                              dummyPosition()
                                                                              ),
                                                                            dummyPosition()
                                                                            ),
                                                                          dummyPosition()
                                                                          ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                    );
    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testLeapYear() {
    String filename = "samples/leapyear.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram =
      new Program(
          new LetCommand(
            new SequentialDeclaration(
              new SequentialDeclaration(
                new SequentialDeclaration(
                  new TypeDeclaration(
                    new Identifier(
                      "Date",
                      dummyPosition()
                      ),
                    new RecordTypeDenoter(
                      new MultipleFieldTypeDenoter(
                        new Identifier(
                          "y",
                          dummyPosition()
                          ),
                        new SimpleTypeDenoter(
                          new Identifier(
                            "Integer",
                            dummyPosition()
                            ),
                          dummyPosition()
                          ),
                        new MultipleFieldTypeDenoter(
                          new Identifier(
                            "m",
                            dummyPosition()
                            ),
                          new SimpleTypeDenoter(
                            new Identifier(
                              "Integer",
                              dummyPosition()
                              ),
                            dummyPosition()
                            ),
                          new SingleFieldTypeDenoter(
                            new Identifier(
                              "d",
                              dummyPosition()
                              ),
                            new SimpleTypeDenoter(
                              new Identifier(
                                "Integer",
                                dummyPosition()
                                ),
                              dummyPosition()
                              ),
                            dummyPosition()
                            ),
                          dummyPosition()
                            ),
                          dummyPosition()
                            ),
                          dummyPosition()
                            ),
                          dummyPosition()
                            ),
                          new VarDeclaration(
                              new Identifier(
                                "d",
                                dummyPosition()
                                ),
                              new SimpleTypeDenoter(
                                new Identifier(
                                  "Date",
                                  dummyPosition()
                                  ),
                                dummyPosition()
                                ),
                              dummyPosition()
                              ),
                          dummyPosition()
                            ),
                          new ProcDeclaration(
                              new Identifier(
                                "readdate",
                                dummyPosition()
                                ),
                              new SingleFormalParameterSequence(
                                new VarFormalParameter(
                                  new Identifier(
                                    "d",
                                    dummyPosition()
                                    ),
                                  new SimpleTypeDenoter(
                                    new Identifier(
                                      "Date",
                                      dummyPosition()
                                      ),
                                    dummyPosition()
                                    ),
                                  dummyPosition()
                                  ),
                                dummyPosition()
                                ),
                              new SequentialCommand(
                                  new SequentialCommand(
                                    new CallCommand(
                                      new Identifier(
                                        "getint",
                                        dummyPosition()
                                        ),
                                      new SingleActualParameterSequence(
                                        new VarActualParameter(
                                          new DotVname(
                                            new SimpleVname(
                                              new Identifier(
                                                "d",
                                                dummyPosition()
                                                ),
                                              dummyPosition()
                                              ),
                                            new Identifier(
                                              "y",
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                          dummyPosition()
                                          ),
                                        dummyPosition()
                                        ),
                                      dummyPosition()
                                        ),
                                      new CallCommand(
                                          new Identifier(
                                            "getint",
                                            dummyPosition()
                                            ),
                                          new SingleActualParameterSequence(
                                            new VarActualParameter(
                                              new DotVname(
                                                new SimpleVname(
                                                  new Identifier(
                                                    "d",
                                                    dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                  ),
                                                new Identifier(
                                                  "m",
                                                  dummyPosition()
                                                  ),
                                                dummyPosition()
                                                ),
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                          dummyPosition()
                                            ),
                                          dummyPosition()
                                            ),
                                          new CallCommand(
                                              new Identifier(
                                                "getint",
                                                dummyPosition()
                                                ),
                                              new SingleActualParameterSequence(
                                                new VarActualParameter(
                                                  new DotVname(
                                                    new SimpleVname(
                                                      new Identifier(
                                                        "d",
                                                        dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                      ),
                                                    new Identifier(
                                                      "d",
                                                      dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                  ),
                                                dummyPosition()
                                                ),
                                              dummyPosition()
                                                ),
                                              dummyPosition()
                                                ),
                                              dummyPosition()
                                                ),
                                              dummyPosition()
                                                ),
                                              new FuncDeclaration(
                                                  new Identifier(
                                                    "leapyear",
                                                    dummyPosition()
                                                    ),
                                                  new SingleFormalParameterSequence(
                                                    new ConstFormalParameter(
                                                      new Identifier(
                                                        "d",
                                                        dummyPosition()
                                                        ),
                                                      new SimpleTypeDenoter(
                                                        new Identifier(
                                                          "Date",
                                                          dummyPosition()
                                                          ),
                                                        dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                    ),
                                                  new SimpleTypeDenoter(
                                                      new Identifier(
                                                        "Boolean",
                                                        dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                      ),
                                                  new IfExpression(
                                                      new BinaryExpression(
                                                        new BinaryExpression(
                                                          new BinaryExpression(
                                                            new VnameExpression(
                                                              new DotVname(
                                                                new SimpleVname(
                                                                  new Identifier(
                                                                    "d",
                                                                    dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                  ),
                                                                new Identifier(
                                                                  "y",
                                                                  dummyPosition()
                                                                  ),
                                                                dummyPosition()
                                                                ),
                                                              dummyPosition()
                                                              ),
                                                            new Operator(
                                                              "//",
                                                              dummyPosition()
                                                              ),
                                                            new BinaryExpression(
                                                                new IntegerExpression(
                                                                  new IntegerLiteral(
                                                                    "100",
                                                                    dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                  ),
                                                                new Operator(
                                                                  "=",
                                                                  dummyPosition()
                                                                  ),
                                                                new IntegerExpression(
                                                                  new IntegerLiteral(
                                                                    "0",
                                                                    dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                  ),
                                                                dummyPosition()
                                                                ),
                                                                dummyPosition()
                                                                  ),
                                                                new Operator(
                                                                    "/\\",
                                                                    dummyPosition()
                                                                    ),
                                                                new BinaryExpression(
                                                                    new VnameExpression(
                                                                      new DotVname(
                                                                        new SimpleVname(
                                                                          new Identifier(
                                                                            "d",
                                                                            dummyPosition()
                                                                            ),
                                                                          dummyPosition()
                                                                          ),
                                                                        new Identifier(
                                                                          "y",
                                                                          dummyPosition()
                                                                          ),
                                                                        dummyPosition()
                                                                        ),
                                                                      dummyPosition()
                                                                      ),
                                                                    new Operator(
                                                                      "//",
                                                                      dummyPosition()
                                                                      ),
                                                                    new BinaryExpression(
                                                                        new IntegerExpression(
                                                                          new IntegerLiteral(
                                                                            "400",
                                                                            dummyPosition()
                                                                            ),
                                                                          dummyPosition()
                                                                          ),
                                                                        new Operator(
                                                                          "=",
                                                                          dummyPosition()
                                                                          ),
                                                                        new IntegerExpression(
                                                                          new IntegerLiteral(
                                                                            "0",
                                                                            dummyPosition()
                                                                            ),
                                                                          dummyPosition()
                                                                          ),
                                                                        dummyPosition()
                                                                        ),
                                                                        dummyPosition()
                                                                          ),
                                                                        dummyPosition()
                                                                          ),
                                                                        new Operator(
                                                                            "\\/",
                                                                            dummyPosition()
                                                                            ),
                                                                        new BinaryExpression(
                                                                            new VnameExpression(
                                                                              new DotVname(
                                                                                new SimpleVname(
                                                                                  new Identifier(
                                                                                    "d",
                                                                                    dummyPosition()
                                                                                    ),
                                                                                  dummyPosition()
                                                                                  ),
                                                                                new Identifier(
                                                                                  "y",
                                                                                  dummyPosition()
                                                                                  ),
                                                                                dummyPosition()
                                                                                ),
                                                                              dummyPosition()
                                                                              ),
                                                                            new Operator(
                                                                              "//",
                                                                              dummyPosition()
                                                                              ),
                                                                            new BinaryExpression(
                                                                                new IntegerExpression(
                                                                                  new IntegerLiteral(
                                                                                    "4",
                                                                                    dummyPosition()
                                                                                    ),
                                                                                  dummyPosition()
                                                                                  ),
                                                                                new Operator(
                                                                                  "=",
                                                                                  dummyPosition()
                                                                                  ),
                                                                                new IntegerExpression(
                                                                                  new IntegerLiteral(
                                                                                    "0",
                                                                                    dummyPosition()
                                                                                    ),
                                                                                  dummyPosition()
                                                                                  ),
                                                                                dummyPosition()
                                                                                ),
                                                                                dummyPosition()
                                                                                  ),
                                                                                dummyPosition()
                                                                                  ),
                                                                                new VnameExpression(
                                                                                    new SimpleVname(
                                                                                      new Identifier(
                                                                                        "true",
                                                                                        dummyPosition()
                                                                                        ),
                                                                                      dummyPosition()
                                                                                      ),
                                                                                    dummyPosition()
                                                                                    ),
                                                                                new VnameExpression(
                                                                                    new SimpleVname(
                                                                                      new Identifier(
                                                                                        "false",
                                                                                        dummyPosition()
                                                                                        ),
                                                                                      dummyPosition()
                                                                                      ),
                                                                                    dummyPosition()
                                                                                    ),
                                                                                dummyPosition()
                                                                                  ),
                                                                                dummyPosition()
                                                                                  ),
                                                                                dummyPosition()
                                                                                  ),
                                                                                new SequentialCommand(
                                                                                    new CallCommand(
                                                                                      new Identifier(
                                                                                        "readdate",
                                                                                        dummyPosition()
                                                                                        ),
                                                                                      new SingleActualParameterSequence(
                                                                                        new VarActualParameter(
                                                                                          new SimpleVname(
                                                                                            new Identifier(
                                                                                              "d",
                                                                                              dummyPosition()
                                                                                              ),
                                                                                            dummyPosition()
                                                                                            ),
                                                                                          dummyPosition()
                                                                                          ),
                                                                                        dummyPosition()
                                                                                        ),
                                                                                      dummyPosition()
                                                                                      ),
                                                                                new IfCommand(
                                                                                    new CallExpression(
                                                                                      new Identifier(
                                                                                        "leapyear",
                                                                                        dummyPosition()
                                                                                        ),
                                                                                      new SingleActualParameterSequence(
                                                                                        new ConstActualParameter(
                                                                                          new VnameExpression(
                                                                                            new SimpleVname(
                                                                                              new Identifier(
                                                                                                "d",
                                                                                                dummyPosition()
                                                                                                ),
                                                                                              dummyPosition()
                                                                                              ),
                                                                                            dummyPosition()
                                                                                            ),
                                                                                          dummyPosition()
                                                                                          ),
                                                                                        dummyPosition()
                                                                                        ),
                                                                                      dummyPosition()
                                                                                        ),
                                                                                      new CallCommand(
                                                                                          new Identifier(
                                                                                            "putint",
                                                                                            dummyPosition()
                                                                                            ),
                                                                                          new SingleActualParameterSequence(
                                                                                            new ConstActualParameter(
                                                                                              new IntegerExpression(
                                                                                                new IntegerLiteral(
                                                                                                  "1",
                                                                                                  dummyPosition()
                                                                                                  ),
                                                                                                dummyPosition()
                                                                                                ),
                                                                                              dummyPosition()
                                                                                              ),
                                                                                            dummyPosition()
                                                                                            ),
                                                                                          dummyPosition()
                                                                                          ),
                                                                                      new CallCommand(
                                                                                          new Identifier(
                                                                                            "putint",
                                                                                            dummyPosition()
                                                                                            ),
                                                                                          new SingleActualParameterSequence(
                                                                                            new ConstActualParameter(
                                                                                              new IntegerExpression(
                                                                                                new IntegerLiteral(
                                                                                                  "0",
                                                                                                  dummyPosition()
                                                                                                  ),
                                                                                                dummyPosition()
                                                                                                ),
                                                                                              dummyPosition()
                                                                                              ),
                                                                                            dummyPosition()
                                                                                            ),
                                                                                          dummyPosition()
                                                                                          ),
                                                                                      dummyPosition()
                                                                                        ),
                                                                                      dummyPosition()
                                                                                        ),
                                                                                      dummyPosition()
                                                                                        ),
                                                                                      dummyPosition()
                                                                                        );

    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testLeapYearDegenerate() {
    String filename = "samples/leapyear_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram =
      new Program(
          new LetCommand(
            new SequentialDeclaration(
              new SequentialDeclaration(
                new SequentialDeclaration(
                  new TypeDeclaration(
                    new Identifier(
                      "Date",
                      dummyPosition()
                      ),
                    new RecordTypeDenoter(
                      new MultipleFieldTypeDenoter(
                        new Identifier(
                          "y",
                          dummyPosition()
                          ),
                        new SimpleTypeDenoter(
                          new Identifier(
                            "Integer",
                            dummyPosition()
                            ),
                          dummyPosition()
                          ),
                        new MultipleFieldTypeDenoter(
                          new Identifier(
                            "m",
                            dummyPosition()
                            ),
                          new SimpleTypeDenoter(
                            new Identifier(
                              "Integer",
                              dummyPosition()
                              ),
                            dummyPosition()
                            ),
                          new SingleFieldTypeDenoter(
                            new Identifier(
                              "d",
                              dummyPosition()
                              ),
                            new SimpleTypeDenoter(
                              new Identifier(
                                "Integer",
                                dummyPosition()
                                ),
                              dummyPosition()
                              ),
                            dummyPosition()
                            ),
                          dummyPosition()
                            ),
                          dummyPosition()
                            ),
                          dummyPosition()
                            ),
                          dummyPosition()
                            ),
                          new VarDeclaration(
                              new Identifier(
                                "d",
                                dummyPosition()
                                ),
                              new SimpleTypeDenoter(
                                new Identifier(
                                  "Date",
                                  dummyPosition()
                                  ),
                                dummyPosition()
                                ),
                              dummyPosition()
                              ),
                          dummyPosition()
                            ),
                          new ProcDeclaration(
                              new Identifier(
                                "readdate",
                                dummyPosition()
                                ),
                              new SingleFormalParameterSequence(
                                new VarFormalParameter(
                                  new Identifier(
                                    "d",
                                    dummyPosition()
                                    ),
                                  new SimpleTypeDenoter(
                                    new Identifier(
                                      "Date",
                                      dummyPosition()
                                      ),
                                    dummyPosition()
                                    ),
                                  dummyPosition()
                                  ),
                                dummyPosition()
                                ),
                              new SequentialCommand(
                                  new SequentialCommand(
                                    new CallCommand(
                                      new Identifier(
                                        "getint",
                                        dummyPosition()
                                        ),
                                      new SingleActualParameterSequence(
                                        new VarActualParameter(
                                          new DotVname(
                                            new SimpleVname(
                                              new Identifier(
                                                "d",
                                                dummyPosition()
                                                ),
                                              dummyPosition()
                                              ),
                                            new Identifier(
                                              "y",
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                          dummyPosition()
                                          ),
                                        dummyPosition()
                                        ),
                                      dummyPosition()
                                        ),
                                      new CallCommand(
                                          new Identifier(
                                            "getint",
                                            dummyPosition()
                                            ),
                                          new SingleActualParameterSequence(
                                            new VarActualParameter(
                                              new DotVname(
                                                new SimpleVname(
                                                  new Identifier(
                                                    "d",
                                                    dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                  ),
                                                new Identifier(
                                                  "m",
                                                  dummyPosition()
                                                  ),
                                                dummyPosition()
                                                ),
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                          dummyPosition()
                                            ),
                                          dummyPosition()
                                            ),
                                          new CallCommand(
                                              new Identifier(
                                                "getint",
                                                dummyPosition()
                                                ),
                                              new SingleActualParameterSequence(
                                                new VarActualParameter(
                                                  new DotVname(
                                                    new SimpleVname(
                                                      new Identifier(
                                                        "d",
                                                        dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                      ),
                                                    new Identifier(
                                                      "d",
                                                      dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                  ),
                                                dummyPosition()
                                                ),
                                              dummyPosition()
                                                ),
                                              dummyPosition()
                                                ),
                                              dummyPosition()
                                                ),
                                              dummyPosition()
                                                ),
                                              new FuncDeclaration(
                                                  new Identifier(
                                                    "leapyear",
                                                    dummyPosition()
                                                    ),
                                                  new SingleFormalParameterSequence(
                                                    new ConstFormalParameter(
                                                      new Identifier(
                                                        "d",
                                                        dummyPosition()
                                                        ),
                                                      new SimpleTypeDenoter(
                                                        new Identifier(
                                                          "Date",
                                                          dummyPosition()
                                                          ),
                                                        dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                    ),
                                                  new SimpleTypeDenoter(
                                                      new Identifier(
                                                        "Boolean",
                                                        dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                      ),
                                                  new IfExpression(
                                                      new BinaryExpression(
                                                        new BinaryExpression(
                                                          new BinaryExpression(
                                                            new VnameExpression(
                                                              new DotVname(
                                                                new SimpleVname(
                                                                  new Identifier(
                                                                    "d",
                                                                    dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                  ),
                                                                new Identifier(
                                                                  "y",
                                                                  dummyPosition()
                                                                  ),
                                                                dummyPosition()
                                                                ),
                                                              dummyPosition()
                                                              ),
                                                            new Operator(
                                                              "//",
                                                              dummyPosition()
                                                              ),
                                                            new BinaryExpression(
                                                                new IntegerExpression(
                                                                  new IntegerLiteral(
                                                                    "100",
                                                                    dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                  ),
                                                                new Operator(
                                                                  "=",
                                                                  dummyPosition()
                                                                  ),
                                                                new IntegerExpression(
                                                                  new IntegerLiteral(
                                                                    "0",
                                                                    dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                  ),
                                                                dummyPosition()
                                                                ),
                                                                dummyPosition()
                                                                  ),
                                                                new Operator(
                                                                    "/\\",
                                                                    dummyPosition()
                                                                    ),
                                                                new BinaryExpression(
                                                                    new VnameExpression(
                                                                      new DotVname(
                                                                        new SimpleVname(
                                                                          new Identifier(
                                                                            "d",
                                                                            dummyPosition()
                                                                            ),
                                                                          dummyPosition()
                                                                          ),
                                                                        new Identifier(
                                                                          "y",
                                                                          dummyPosition()
                                                                          ),
                                                                        dummyPosition()
                                                                        ),
                                                                      dummyPosition()
                                                                      ),
                                                                    new Operator(
                                                                      "//",
                                                                      dummyPosition()
                                                                      ),
                                                                    new BinaryExpression(
                                                                        new IntegerExpression(
                                                                          new IntegerLiteral(
                                                                            "400",
                                                                            dummyPosition()
                                                                            ),
                                                                          dummyPosition()
                                                                          ),
                                                                        new Operator(
                                                                          "=",
                                                                          dummyPosition()
                                                                          ),
                                                                        new IntegerExpression(
                                                                          new IntegerLiteral(
                                                                            "0",
                                                                            dummyPosition()
                                                                            ),
                                                                          dummyPosition()
                                                                          ),
                                                                        dummyPosition()
                                                                        ),
                                                                        dummyPosition()
                                                                          ),
                                                                        dummyPosition()
                                                                          ),
                                                                        new Operator(
                                                                            "\\/",
                                                                            dummyPosition()
                                                                            ),
                                                                        new BinaryExpression(
                                                                            new VnameExpression(
                                                                              new DotVname(
                                                                                new SimpleVname(
                                                                                  new Identifier(
                                                                                    "d",
                                                                                    dummyPosition()
                                                                                    ),
                                                                                  dummyPosition()
                                                                                  ),
                                                                                new Identifier(
                                                                                  "y",
                                                                                  dummyPosition()
                                                                                  ),
                                                                                dummyPosition()
                                                                                ),
                                                                              dummyPosition()
                                                                              ),
                                                                            new Operator(
                                                                              "//",
                                                                              dummyPosition()
                                                                              ),
                                                                            new BinaryExpression(
                                                                                new IntegerExpression(
                                                                                  new IntegerLiteral(
                                                                                    "4",
                                                                                    dummyPosition()
                                                                                    ),
                                                                                  dummyPosition()
                                                                                  ),
                                                                                new Operator(
                                                                                  "=",
                                                                                  dummyPosition()
                                                                                  ),
                                                                                new IntegerExpression(
                                                                                  new IntegerLiteral(
                                                                                    "0",
                                                                                    dummyPosition()
                                                                                    ),
                                                                                  dummyPosition()
                                                                                  ),
                                                                                dummyPosition()
                                                                                ),
                                                                                dummyPosition()
                                                                                  ),
                                                                                dummyPosition()
                                                                                  ),
                                                                                new VnameExpression(
                                                                                    new SimpleVname(
                                                                                      new Identifier(
                                                                                        "true",
                                                                                        dummyPosition()
                                                                                        ),
                                                                                      dummyPosition()
                                                                                      ),
                                                                                    dummyPosition()
                                                                                    ),
                                                                                new VnameExpression(
                                                                                    new SimpleVname(
                                                                                      new Identifier(
                                                                                        "false",
                                                                                        dummyPosition()
                                                                                        ),
                                                                                      dummyPosition()
                                                                                      ),
                                                                                    dummyPosition()
                                                                                    ),
                                                                                dummyPosition()
                                                                                  ),
                                                                                dummyPosition()
                                                                                  ),
                                                                                dummyPosition()
                                                                                  ),
                                                                                new SequentialCommand(
                                                                                    new CallCommand(
                                                                                      new Identifier(
                                                                                        "readdate",
                                                                                        dummyPosition()
                                                                                        ),
                                                                                      new SingleActualParameterSequence(
                                                                                        new VarActualParameter(
                                                                                          new SimpleVname(
                                                                                            new Identifier(
                                                                                              "d",
                                                                                              dummyPosition()
                                                                                              ),
                                                                                            dummyPosition()
                                                                                            ),
                                                                                          dummyPosition()
                                                                                          ),
                                                                                        dummyPosition()
                                                                                        ),
                                                                                      dummyPosition()
                                                                                      ),
                                                                                new IfCommand(
                                                                                    new CallExpression(
                                                                                      new Identifier(
                                                                                        "leapyear",
                                                                                        dummyPosition()
                                                                                        ),
                                                                                      new SingleActualParameterSequence(
                                                                                        new ConstActualParameter(
                                                                                          new VnameExpression(
                                                                                            new SimpleVname(
                                                                                              new Identifier(
                                                                                                "d",
                                                                                                dummyPosition()
                                                                                                ),
                                                                                              dummyPosition()
                                                                                              ),
                                                                                            dummyPosition()
                                                                                            ),
                                                                                          dummyPosition()
                                                                                          ),
                                                                                        dummyPosition()
                                                                                        ),
                                                                                      dummyPosition()
                                                                                        ),
                                                                                      new CallCommand(
                                                                                          new Identifier(
                                                                                            "putint",
                                                                                            dummyPosition()
                                                                                            ),
                                                                                          new SingleActualParameterSequence(
                                                                                            new ConstActualParameter(
                                                                                              new IntegerExpression(
                                                                                                new IntegerLiteral(
                                                                                                  "1",
                                                                                                  dummyPosition()
                                                                                                  ),
                                                                                                dummyPosition()
                                                                                                ),
                                                                                              dummyPosition()
                                                                                              ),
                                                                                            dummyPosition()
                                                                                            ),
                                                                                          dummyPosition()
                                                                                          ),
                                                                                      new CallCommand(
                                                                                          new Identifier(
                                                                                            "putint",
                                                                                            dummyPosition()
                                                                                            ),
                                                                                          new SingleActualParameterSequence(
                                                                                            new ConstActualParameter(
                                                                                              new IntegerExpression(
                                                                                                new IntegerLiteral(
                                                                                                  "0",
                                                                                                  dummyPosition()
                                                                                                  ),
                                                                                                dummyPosition()
                                                                                                ),
                                                                                              dummyPosition()
                                                                                              ),
                                                                                            dummyPosition()
                                                                                            ),
                                                                                          dummyPosition()
                                                                                          ),
                                                                                      dummyPosition()
                                                                                        ),
                                                                                      dummyPosition()
                                                                                        ),
                                                                                      dummyPosition()
                                                                                        ),
                                                                                      dummyPosition()
                                                                                        );


    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testPrintArray() {
    String filename = "samples/print_array.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram =
      new Program(
          new LetCommand(
            new SequentialDeclaration(
              new SequentialDeclaration(
                new VarDeclaration(
                  new Identifier(
                    "arr",
                    dummyPosition()
                    ),
                  new ArrayTypeDenoter(
                    new IntegerLiteral(
                      "5",
                      dummyPosition()
                      ),
                    new SimpleTypeDenoter(
                      new Identifier(
                        "Integer",
                        dummyPosition()
                        ),
                      dummyPosition()
                      ),
                    dummyPosition()
                    ),
                  dummyPosition()
                  ),
                  new ProcDeclaration(
                      new Identifier(
                        "println",
                        dummyPosition()
                        ),
                      new SingleFormalParameterSequence(
                        new ConstFormalParameter(
                          new Identifier(
                            "x",
                            dummyPosition()
                            ),
                          new SimpleTypeDenoter(
                            new Identifier(
                              "Integer",
                              dummyPosition()
                              ),
                            dummyPosition()
                            ),
                          dummyPosition()
                          ),
                        dummyPosition()
                        ),
                      new SequentialCommand(
                          new CallCommand(
                            new Identifier(
                              "putint",
                              dummyPosition()
                              ),
                            new SingleActualParameterSequence(
                              new ConstActualParameter(
                                new VnameExpression(
                                  new SimpleVname(
                                    new Identifier(
                                      "x",
                                      dummyPosition()
                                      ),
                                    dummyPosition()
                                    ),
                                  dummyPosition()
                                  ),
                                dummyPosition()
                                ),
                              dummyPosition()
                              ),
                            dummyPosition()
                              ),
                            new CallCommand(
                                new Identifier(
                                  "puteol",
                                  dummyPosition()
                                  ),
                                new EmptyActualParameterSequence(
                                  dummyPosition()
                                  ),
                                dummyPosition()
                                ),
                            dummyPosition()
                              ),
                            dummyPosition()
                              ),
                            dummyPosition()
                              ),
                            new ProcDeclaration(
                                new Identifier(
                                  "iterate",
                                  dummyPosition()
                                  ),
                                new MultipleFormalParameterSequence(
                                  new ProcFormalParameter(
                                    new Identifier(
                                      "f",
                                      dummyPosition()
                                      ),
                                    new SingleFormalParameterSequence(
                                      new ConstFormalParameter(
                                        new Identifier(
                                          "n",
                                          dummyPosition()
                                          ),
                                        new SimpleTypeDenoter(
                                          new Identifier(
                                            "Integer",
                                            dummyPosition()
                                            ),
                                          dummyPosition()
                                          ),
                                        dummyPosition()
                                        ),
                                      dummyPosition()
                                      ),
                                    dummyPosition()
                                      ),
                                    new SingleFormalParameterSequence(
                                        new ConstFormalParameter(
                                          new Identifier(
                                            "arr",
                                            dummyPosition()
                                            ),
                                          new ArrayTypeDenoter(
                                            new IntegerLiteral(
                                              "5",
                                              dummyPosition()
                                              ),
                                            new SimpleTypeDenoter(
                                              new Identifier(
                                                "Integer",
                                                dummyPosition()
                                                ),
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                          dummyPosition()
                                          ),
                                          dummyPosition()
                                            ),
                                          dummyPosition()
                                            ),
                                          new LetCommand(
                                              new VarDeclaration(
                                                new Identifier(
                                                  "i",
                                                  dummyPosition()
                                                  ),
                                                new SimpleTypeDenoter(
                                                  new Identifier(
                                                    "Integer",
                                                    dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                  ),
                                                dummyPosition()
                                                ),
                                              new SequentialCommand(
                                                new AssignCommand(
                                                  new SimpleVname(
                                                    new Identifier(
                                                      "i",
                                                      dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                    ),
                                                  new IntegerExpression(
                                                    new IntegerLiteral(
                                                      "0",
                                                      dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                  ),
                                                new WhileCommand(
                                                  new BinaryExpression(
                                                    new VnameExpression(
                                                      new SimpleVname(
                                                        new Identifier(
                                                          "i",
                                                          dummyPosition()
                                                          ),
                                                        dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                      ),
                                                    new Operator(
                                                      "<",
                                                      dummyPosition()
                                                      ),
                                                    new IntegerExpression(
                                                      new IntegerLiteral(
                                                        "5",
                                                        dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                      ),
                                                    new SequentialCommand(
                                                        new CallCommand(
                                                          new Identifier(
                                                            "f",
                                                            dummyPosition()
                                                            ),
                                                          new SingleActualParameterSequence(
                                                            new ConstActualParameter(
                                                              new VnameExpression(
                                                                new SubscriptVname(
                                                                  new SimpleVname(
                                                                    new Identifier(
                                                                      "arr",
                                                                      dummyPosition()
                                                                      ),
                                                                    dummyPosition()
                                                                    ),
                                                                  new VnameExpression(
                                                                    new SimpleVname(
                                                                      new Identifier(
                                                                        "i",
                                                                        dummyPosition()
                                                                        ),
                                                                      dummyPosition()
                                                                      ),
                                                                    dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                  ),
                                                    dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                      ),
                                                    new AssignCommand(
                                                        new SimpleVname(
                                                          new Identifier(
                                                            "i",
                                                            dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                          ),
                                                        new BinaryExpression(
                                                          new VnameExpression(
                                                            new SimpleVname(
                                                              new Identifier(
                                                                "i",
                                                                dummyPosition()
                                                                ),
                                                              dummyPosition()
                                                              ),
                                                            dummyPosition()
                                                            ),
                                                          new Operator(
                                                            "+",
                                                            dummyPosition()
                                                            ),
                                                          new IntegerExpression(
                                                            new IntegerLiteral(
                                                              "1",
                                                              dummyPosition()
                                                              ),
                                                            dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                            ),
                                                          new LetCommand(
                                                              new VarDeclaration(
                                                                new Identifier(
                                                                  "i",
                                                                  dummyPosition()
                                                                  ),
                                                                new SimpleTypeDenoter(
                                                                  new Identifier(
                                                                    "Integer",
                                                                    dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                  ),
                                                                dummyPosition()
                                                                ),
                                                              new SequentialCommand(
                                                                new SequentialCommand(
                                                                  new AssignCommand(
                                                                    new SimpleVname(
                                                                      new Identifier(
                                                                        "i",
                                                                        dummyPosition()
                                                                        ),
                                                                      dummyPosition()
                                                                      ),
                                                                    new IntegerExpression(
                                                                      new IntegerLiteral(
                                                                        "0",
                                                                        dummyPosition()
                                                                        ),
                                                                      dummyPosition()
                                                                      ),
                                                                    dummyPosition()
                                                                    ),
                                                                  new WhileCommand(
                                                                    new BinaryExpression(
                                                                      new VnameExpression(
                                                                        new SimpleVname(
                                                                          new Identifier(
                                                                            "i",
                                                                            dummyPosition()
                                                                            ),
                                                                          dummyPosition()
                                                                          ),
                                                                        dummyPosition()
                                                                        ),
                                                                      new Operator(
                                                                        "<",
                                                                        dummyPosition()
                                                                        ),
                                                                      new IntegerExpression(
                                                                        new IntegerLiteral(
                                                                          "5",
                                                                          dummyPosition()
                                                                          ),
                                                                        dummyPosition()
                                                                        ),
                                                                      dummyPosition()
                                                                        ),
                                                                      new SequentialCommand(
                                                                          new AssignCommand(
                                                                            new SubscriptVname(
                                                                              new SimpleVname(
                                                                                new Identifier(
                                                                                  "arr",
                                                                                  dummyPosition()
                                                                                  ),
                                                                                dummyPosition()
                                                                                ),
                                                                              new VnameExpression(
                                                                                new SimpleVname(
                                                                                  new Identifier(
                                                                                    "i",
                                                                                    dummyPosition()
                                                                                    ),
                                                                                  dummyPosition()
                                                                                  ),
                                                                                dummyPosition()
                                                                                ),
                                                                              dummyPosition()
                                                                              ),
                                                                      new BinaryExpression(
                                                                          new BinaryExpression(
                                                                            new VnameExpression(
                                                                              new SimpleVname(
                                                                                new Identifier(
                                                                                  "i",
                                                                                  dummyPosition()
                                                                                  ),
                                                                                dummyPosition()
                                                                                ),
                                                                              dummyPosition()
                                                                              ),
                                                                            new Operator(
                                                                              "+",
                                                                              dummyPosition()
                                                                              ),
                                                                            new IntegerExpression(
                                                                              new IntegerLiteral(
                                                                                "1",
                                                                                dummyPosition()
                                                                                ),
                                                                              dummyPosition()
                                                                              ),
                                                                            dummyPosition()
                                                                              ),
                                                                            new Operator(
                                                                                "*",
                                                                                dummyPosition()
                                                                                ),
                                                                            new IntegerExpression(
                                                                                new IntegerLiteral(
                                                                                  "100",
                                                                                  dummyPosition()
                                                                                  ),
                                                                                dummyPosition()
                                                                                ),
                                                                            dummyPosition()
                                                                              ),
                                                                            dummyPosition()
                                                                              ),
                                                                            new AssignCommand(
                                                                                new SimpleVname(
                                                                                  new Identifier(
                                                                                    "i",
                                                                                    dummyPosition()
                                                                                    ),
                                                                                  dummyPosition()
                                                                                  ),
                                                                                new BinaryExpression(
                                                                                  new VnameExpression(
                                                                                    new SimpleVname(
                                                                                      new Identifier(
                                                                                        "i",
                                                                                        dummyPosition()
                                                                                        ),
                                                                                      dummyPosition()
                                                                                      ),
                                                                                    dummyPosition()
                                                                                    ),
                                                                                  new Operator(
                                                                                    "+",
                                                                                    dummyPosition()
                                                                                    ),
                                                                                  new IntegerExpression(
                                                                                    new IntegerLiteral(
                                                                                      "1",
                                                                                      dummyPosition()
                                                                                      ),
                                                                                    dummyPosition()
                                                                                    ),
                                                                                  dummyPosition()
                                                                                    ),
                                                                                  dummyPosition()
                                                                                    ),
                                                                                  dummyPosition()
                                                                                    ),
                                                                                  dummyPosition()
                                                                                    ),
                                                                                  dummyPosition()
                                                                                    ),
                                                                                  new CallCommand(
                                                                                      new Identifier(
                                                                                        "iterate",
                                                                                        dummyPosition()
                                                                                        ),
                                                                                      new MultipleActualParameterSequence(
                                                                                        new ProcActualParameter(
                                                                                          new Identifier(
                                                                                            "println",
                                                                                            dummyPosition()
                                                                                            ),
                                                                                          dummyPosition()
                                                                                          ),
                                                                                        new SingleActualParameterSequence(
                                                                                          new ConstActualParameter(
                                                                                            new VnameExpression(
                                                                                              new SimpleVname(
                                                                                                new Identifier(
                                                                                                  "arr",
                                                                                                  dummyPosition()
                                                                                                  ),
                                                                                                dummyPosition()
                                                                                                ),
                                                                                              dummyPosition()
                                                                                              ),
                                                                                            dummyPosition()
                                                                                            ),
                                                                                          dummyPosition()
                                                                                          ),
                                                                                        dummyPosition()
                                                                                          ),
                                                                                        dummyPosition()
                                                                                          ),
                                                                                        dummyPosition()
                                                                                          ),
                                                                                        dummyPosition()
                                                                                          ),
                                                                                        dummyPosition()
                                                                                          ),
                                                                                        dummyPosition()
                                                                                          );

    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testPrintArrayDegenerate() {
    String filename = "samples/print_array_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram =
      new Program(
          new LetCommand(
            new SequentialDeclaration(
              new SequentialDeclaration(
                new VarDeclaration(
                  new Identifier(
                    "arr",
                    dummyPosition()
                    ),
                  new ArrayTypeDenoter(
                    new IntegerLiteral(
                      "5",
                      dummyPosition()
                      ),
                    new SimpleTypeDenoter(
                      new Identifier(
                        "Integer",
                        dummyPosition()
                        ),
                      dummyPosition()
                      ),
                    dummyPosition()
                    ),
                  dummyPosition()
                  ),
                  new ProcDeclaration(
                      new Identifier(
                        "println",
                        dummyPosition()
                        ),
                      new SingleFormalParameterSequence(
                        new ConstFormalParameter(
                          new Identifier(
                            "x",
                            dummyPosition()
                            ),
                          new SimpleTypeDenoter(
                            new Identifier(
                              "Integer",
                              dummyPosition()
                              ),
                            dummyPosition()
                            ),
                          dummyPosition()
                          ),
                        dummyPosition()
                        ),
                      new SequentialCommand(
                          new CallCommand(
                            new Identifier(
                              "putint",
                              dummyPosition()
                              ),
                            new SingleActualParameterSequence(
                              new ConstActualParameter(
                                new VnameExpression(
                                  new SimpleVname(
                                    new Identifier(
                                      "x",
                                      dummyPosition()
                                      ),
                                    dummyPosition()
                                    ),
                                  dummyPosition()
                                  ),
                                dummyPosition()
                                ),
                              dummyPosition()
                              ),
                            dummyPosition()
                              ),
                            new CallCommand(
                                new Identifier(
                                  "puteol",
                                  dummyPosition()
                                  ),
                                new EmptyActualParameterSequence(
                                  dummyPosition()
                                  ),
                                dummyPosition()
                                ),
                            dummyPosition()
                              ),
                            dummyPosition()
                              ),
                            dummyPosition()
                              ),
                            new ProcDeclaration(
                                new Identifier(
                                  "iterate",
                                  dummyPosition()
                                  ),
                                new MultipleFormalParameterSequence(
                                  new ProcFormalParameter(
                                    new Identifier(
                                      "f",
                                      dummyPosition()
                                      ),
                                    new SingleFormalParameterSequence(
                                      new ConstFormalParameter(
                                        new Identifier(
                                          "n",
                                          dummyPosition()
                                          ),
                                        new SimpleTypeDenoter(
                                          new Identifier(
                                            "Integer",
                                            dummyPosition()
                                            ),
                                          dummyPosition()
                                          ),
                                        dummyPosition()
                                        ),
                                      dummyPosition()
                                      ),
                                    dummyPosition()
                                      ),
                                    new SingleFormalParameterSequence(
                                        new ConstFormalParameter(
                                          new Identifier(
                                            "arr",
                                            dummyPosition()
                                            ),
                                          new ArrayTypeDenoter(
                                            new IntegerLiteral(
                                              "5",
                                              dummyPosition()
                                              ),
                                            new SimpleTypeDenoter(
                                              new Identifier(
                                                "Integer",
                                                dummyPosition()
                                                ),
                                              dummyPosition()
                                              ),
                                            dummyPosition()
                                            ),
                                          dummyPosition()
                                          ),
                                          dummyPosition()
                                            ),
                                          dummyPosition()
                                            ),
                                          new LetCommand(
                                              new VarDeclaration(
                                                new Identifier(
                                                  "i",
                                                  dummyPosition()
                                                  ),
                                                new SimpleTypeDenoter(
                                                  new Identifier(
                                                    "Integer",
                                                    dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                  ),
                                                dummyPosition()
                                                ),
                                              new SequentialCommand(
                                                new AssignCommand(
                                                  new SimpleVname(
                                                    new Identifier(
                                                      "i",
                                                      dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                    ),
                                                  new IntegerExpression(
                                                    new IntegerLiteral(
                                                      "0",
                                                      dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                    ),
                                                  dummyPosition()
                                                  ),
                                                new WhileCommand(
                                                  new BinaryExpression(
                                                    new VnameExpression(
                                                      new SimpleVname(
                                                        new Identifier(
                                                          "i",
                                                          dummyPosition()
                                                          ),
                                                        dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                      ),
                                                    new Operator(
                                                      "<",
                                                      dummyPosition()
                                                      ),
                                                    new IntegerExpression(
                                                      new IntegerLiteral(
                                                        "5",
                                                        dummyPosition()
                                                        ),
                                                      dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                      ),
                                                    new SequentialCommand(
                                                        new CallCommand(
                                                          new Identifier(
                                                            "f",
                                                            dummyPosition()
                                                            ),
                                                          new SingleActualParameterSequence(
                                                            new ConstActualParameter(
                                                              new VnameExpression(
                                                                new SubscriptVname(
                                                                  new SimpleVname(
                                                                    new Identifier(
                                                                      "arr",
                                                                      dummyPosition()
                                                                      ),
                                                                    dummyPosition()
                                                                    ),
                                                                  new VnameExpression(
                                                                    new SimpleVname(
                                                                      new Identifier(
                                                                        "i",
                                                                        dummyPosition()
                                                                        ),
                                                                      dummyPosition()
                                                                      ),
                                                                    dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                  ),
                                                    dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                      ),
                                                    dummyPosition()
                                                      ),
                                                    new AssignCommand(
                                                        new SimpleVname(
                                                          new Identifier(
                                                            "i",
                                                            dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                          ),
                                                        new BinaryExpression(
                                                          new VnameExpression(
                                                            new SimpleVname(
                                                              new Identifier(
                                                                "i",
                                                                dummyPosition()
                                                                ),
                                                              dummyPosition()
                                                              ),
                                                            dummyPosition()
                                                            ),
                                                          new Operator(
                                                            "+",
                                                            dummyPosition()
                                                            ),
                                                          new IntegerExpression(
                                                            new IntegerLiteral(
                                                              "1",
                                                              dummyPosition()
                                                              ),
                                                            dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                            ),
                                                          dummyPosition()
                                                            ),
                                                          new LetCommand(
                                                              new VarDeclaration(
                                                                new Identifier(
                                                                  "i",
                                                                  dummyPosition()
                                                                  ),
                                                                new SimpleTypeDenoter(
                                                                  new Identifier(
                                                                    "Integer",
                                                                    dummyPosition()
                                                                    ),
                                                                  dummyPosition()
                                                                  ),
                                                                dummyPosition()
                                                                ),
                                                              new SequentialCommand(
                                                                new SequentialCommand(
                                                                  new AssignCommand(
                                                                    new SimpleVname(
                                                                      new Identifier(
                                                                        "i",
                                                                        dummyPosition()
                                                                        ),
                                                                      dummyPosition()
                                                                      ),
                                                                    new IntegerExpression(
                                                                      new IntegerLiteral(
                                                                        "0",
                                                                        dummyPosition()
                                                                        ),
                                                                      dummyPosition()
                                                                      ),
                                                                    dummyPosition()
                                                                    ),
                                                                  new WhileCommand(
                                                                    new BinaryExpression(
                                                                      new VnameExpression(
                                                                        new SimpleVname(
                                                                          new Identifier(
                                                                            "i",
                                                                            dummyPosition()
                                                                            ),
                                                                          dummyPosition()
                                                                          ),
                                                                        dummyPosition()
                                                                        ),
                                                                      new Operator(
                                                                        "<",
                                                                        dummyPosition()
                                                                        ),
                                                                      new IntegerExpression(
                                                                        new IntegerLiteral(
                                                                          "5",
                                                                          dummyPosition()
                                                                          ),
                                                                        dummyPosition()
                                                                        ),
                                                                      dummyPosition()
                                                                        ),
                                                                      new SequentialCommand(
                                                                          new AssignCommand(
                                                                            new SubscriptVname(
                                                                              new SimpleVname(
                                                                                new Identifier(
                                                                                  "arr",
                                                                                  dummyPosition()
                                                                                  ),
                                                                                dummyPosition()
                                                                                ),
                                                                              new VnameExpression(
                                                                                new SimpleVname(
                                                                                  new Identifier(
                                                                                    "i",
                                                                                    dummyPosition()
                                                                                    ),
                                                                                  dummyPosition()
                                                                                  ),
                                                                                dummyPosition()
                                                                                ),
                                                                              dummyPosition()
                                                                              ),
                                                                      new BinaryExpression(
                                                                          new BinaryExpression(
                                                                            new VnameExpression(
                                                                              new SimpleVname(
                                                                                new Identifier(
                                                                                  "i",
                                                                                  dummyPosition()
                                                                                  ),
                                                                                dummyPosition()
                                                                                ),
                                                                              dummyPosition()
                                                                              ),
                                                                            new Operator(
                                                                              "+",
                                                                              dummyPosition()
                                                                              ),
                                                                            new IntegerExpression(
                                                                              new IntegerLiteral(
                                                                                "1",
                                                                                dummyPosition()
                                                                                ),
                                                                              dummyPosition()
                                                                              ),
                                                                            dummyPosition()
                                                                              ),
                                                                            new Operator(
                                                                                "*",
                                                                                dummyPosition()
                                                                                ),
                                                                            new IntegerExpression(
                                                                                new IntegerLiteral(
                                                                                  "100",
                                                                                  dummyPosition()
                                                                                  ),
                                                                                dummyPosition()
                                                                                ),
                                                                            dummyPosition()
                                                                              ),
                                                                            dummyPosition()
                                                                              ),
                                                                            new AssignCommand(
                                                                                new SimpleVname(
                                                                                  new Identifier(
                                                                                    "i",
                                                                                    dummyPosition()
                                                                                    ),
                                                                                  dummyPosition()
                                                                                  ),
                                                                                new BinaryExpression(
                                                                                  new VnameExpression(
                                                                                    new SimpleVname(
                                                                                      new Identifier(
                                                                                        "i",
                                                                                        dummyPosition()
                                                                                        ),
                                                                                      dummyPosition()
                                                                                      ),
                                                                                    dummyPosition()
                                                                                    ),
                                                                                  new Operator(
                                                                                    "+",
                                                                                    dummyPosition()
                                                                                    ),
                                                                                  new IntegerExpression(
                                                                                    new IntegerLiteral(
                                                                                      "1",
                                                                                      dummyPosition()
                                                                                      ),
                                                                                    dummyPosition()
                                                                                    ),
                                                                                  dummyPosition()
                                                                                    ),
                                                                                  dummyPosition()
                                                                                    ),
                                                                                  dummyPosition()
                                                                                    ),
                                                                                  dummyPosition()
                                                                                    ),
                                                                                  dummyPosition()
                                                                                    ),
                                                                                  new CallCommand(
                                                                                      new Identifier(
                                                                                        "iterate",
                                                                                        dummyPosition()
                                                                                        ),
                                                                                      new MultipleActualParameterSequence(
                                                                                        new ProcActualParameter(
                                                                                          new Identifier(
                                                                                            "println",
                                                                                            dummyPosition()
                                                                                            ),
                                                                                          dummyPosition()
                                                                                          ),
                                                                                        new SingleActualParameterSequence(
                                                                                          new ConstActualParameter(
                                                                                            new VnameExpression(
                                                                                              new SimpleVname(
                                                                                                new Identifier(
                                                                                                  "arr",
                                                                                                  dummyPosition()
                                                                                                  ),
                                                                                                dummyPosition()
                                                                                                ),
                                                                                              dummyPosition()
                                                                                              ),
                                                                                            dummyPosition()
                                                                                            ),
                                                                                          dummyPosition()
                                                                                          ),
                                                                                        dummyPosition()
                                                                                          ),
                                                                                        dummyPosition()
                                                                                          ),
                                                                                        dummyPosition()
                                                                                          ),
                                                                                        dummyPosition()
                                                                                          ),
                                                                                        dummyPosition()
                                                                                          ),
                                                                                        dummyPosition()
                                                                                          );

    Program actualProgram = parser.parseProgram();
    assertEquals(expectedProgram, actualProgram);
  }

  public void testDate() {
    String filename = "samples/date.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = null;
    //Program actualProgram = parser.parseProgram();
    //assertEquals(expectedProgram, actualProgram);

  }

  public void testDateDegenerate() {
    String filename = "samples/date_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = null;
    //Program actualProgram = parser.parseProgram();
    //assertEquals(expectedProgram, actualProgram);

  }

  public void testString() {
    String filename = "samples/string.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = null;
    //Program actualProgram = parser.parseProgram();
    //assertEquals(expectedProgram, actualProgram);

  }

  public void testStringDegenerate() {
    String filename = "samples/string_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = null;
    //Program actualProgram = parser.parseProgram();
    //assertEquals(expectedProgram, actualProgram);

  }

  public void testIteratively() {
    String filename = "samples/iteratively.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = null;
    //Program actualProgram = parser.parseProgram();
    //assertEquals(expectedProgram, actualProgram);

  }

  public void testIterativelyDegenerate() {
    String filename = "samples/iteratively_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = null;
    //Program actualProgram = parser.parseProgram();
    //assertEquals(expectedProgram, actualProgram);

  }

  public void testReverseLine() {
    String filename = "samples/reverse_line.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = null;
    //Program actualProgram = parser.parseProgram();
    //assertEquals(expectedProgram, actualProgram);

  }

  public void testReverseLineDegenerate() {
    String filename = "samples/reverse_line_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = null;
    //Program actualProgram = parser.parseProgram();
    //assertEquals(expectedProgram, actualProgram);

  }

  public void testLine() {
    String filename = "samples/line.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = null;
    //Program actualProgram = parser.parseProgram();
    //assertEquals(expectedProgram, actualProgram);

  }

  public void testLineDegenerate() {
    String filename = "samples/line_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = null;
    //Program actualProgram = parser.parseProgram();
    //assertEquals(expectedProgram, actualProgram);

  }

  public void testDates() {
    String filename = "samples/dates.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = null;
    //Program actualProgram = parser.parseProgram();
    //assertEquals(expectedProgram, actualProgram);

  }

  public void testDatesDegenerate() {
    String filename = "samples/dates_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = null;
    //Program actualProgram = parser.parseProgram();
    //assertEquals(expectedProgram, actualProgram);

  }

  public void testMonthsOfYear() {
    String filename = "samples/monthsofyear.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = null;
    //Program actualProgram = parser.parseProgram();
    //assertEquals(expectedProgram, actualProgram);

  }

  public void testMonthsOfYearDegenerate() {
    String filename = "samples/monthsofyear_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = null;
    //Program actualProgram = parser.parseProgram();
    //assertEquals(expectedProgram, actualProgram);

  }


  public void testCapitalise() {
    String filename = "samples/capitalise.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = null;
    //Program actualProgram = parser.parseProgram();
    //assertEquals(expectedProgram, actualProgram);

  }

  public void testCapitaliseDegenerate() {
    String filename = "samples/capitalise_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = null;
    //Program actualProgram = parser.parseProgram();
    //assertEquals(expectedProgram, actualProgram);

  }


  public void testFreq() {
    String filename = "samples/capitalise.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = null;
    //Program actualProgram = parser.parseProgram();
    //assertEquals(expectedProgram, actualProgram);

  }

  public void testFreqDegenerate() {
    String filename = "samples/capitalise_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = null;
    //Program actualProgram = parser.parseProgram();
    //assertEquals(expectedProgram, actualProgram);

  }

  public void testInsertionSort() {
    String filename = "samples/capitalise.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = null;
    //Program actualProgram = parser.parseProgram();
    //assertEquals(expectedProgram, actualProgram);

  }

  public void testInsertionSortDegenerate() {
    String filename = "samples/capitalise_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = null;
    //Program actualProgram = parser.parseProgram();
    //assertEquals(expectedProgram, actualProgram);

  }

  public void testRationals() {
    String filename = "samples/capitalise.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = null;
    //Program actualProgram = parser.parseProgram();
    //assertEquals(expectedProgram, actualProgram);

  }

  public void testRationalsDegenerate() {
    String filename = "samples/capitalise_degenerate.t";
    Scanner scanner = new Scanner(filename);
    Parser parser = new Parser(scanner);
    Program expectedProgram = null;
    //Program actualProgram = parser.parseProgram();
    //assertEquals(expectedProgram, actualProgram);
  }
}

