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
import com.z0ltan.compilers.triangle.ast.EmptyFormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.SingleFormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.MultipleFormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.EmptyActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.SingleActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.MultipleActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.ConstFormalParameter;
import com.z0ltan.compilers.triangle.ast.VarFormalParameter;
import com.z0ltan.compilers.triangle.ast.ConstActualParameter;
import com.z0ltan.compilers.triangle.ast.VarActualParameter;
import com.z0ltan.compilers.triangle.ast.ProcActualParameter;
import com.z0ltan.compilers.triangle.ast.FuncActualParameter;
import com.z0ltan.compilers.triangle.ast.VarDeclaration;
import com.z0ltan.compilers.triangle.ast.ProcDeclaration;
import com.z0ltan.compilers.triangle.ast.FuncDeclaration;
import com.z0ltan.compilers.triangle.ast.SequentialDeclaration;
import com.z0ltan.compilers.triangle.ast.SimpleTypeDenoter;
import com.z0ltan.compilers.triangle.ast.SimpleVname;
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

  }

  public void testPowerDegenerate() {
    String filename = "samples/power_degenerate.t";

  }

  public void testFactorial() {
    String filename = "samples/factorial.t";

  }

  public void testFactorialDegenerate() {
    String filename = "samples/factorial_degenerate.t";

  }

  public void testRecord() {
    String filename = "samples/record.t";

  }

  public void testRecordDegenerate() {
    String filename = "samples/record_degenerate.t";

  }

  public void testLeapYear() {
    String filename = "samples/leapyear.t";

  }

  public void testLeapYearDegenerate() {
    String filename = "samples/leapyear_degenerate.t";

  }

  public void testPrintArray() {
    String filename = "samples/print_array.t";

  }

  public void testPrintArrayDegenerate() {
    String filename = "samples/print_array_degenerate.t";

  }

  public void testDate() {
    String filename = "samples/date.t";

  }

  public void testDateDegenerate() {
    String filename = "samples/date_degenerate.t";

  }

  public void testString() {
    String filename = "samples/string.t";

  }

  public void testStringDegenerate() {
    String filename = "samples/string_degenerate.t";

  }

  public void testIteratively() {
    String filename = "samples/iteratively.t";

  }

  public void testIterativelyDegenerate() {
    String filename = "samples/iteratively_degenerate.t";

  }

  public void testReverseLine() {
    String filename = "samples/reverse_line.t";

  }

  public void testReverseLineDegenerate() {
    String filename = "samples/reverse_line_degenerate.t";

  }

  public void testLine() {
    String filename = "samples/line.t";

  }

  public void testLineDegenerate() {
    String filename = "samples/line_degenerate.t";

  }

  public void testDates() {
    String filename = "samples/dates.t";

  }

  public void testDatesDegenerate() {
    String filename = "samples/dates_degenerate.t";

  }

  public void testMonthsOfYear() {
    String filename = "samples/monthsofyear.t";

  }

  public void testMonthsOfYearDegenerate() {
    String filename = "samples/monthsofyear_degenerate.t";

  }


  public void testCapitalise() {
    String filename = "samples/capitalise.t";

  }

  public void testCapitaliseDegenerate() {
    String filename = "samples/capitalise_degenerate.t";

  }


  public void testFreq() {
    String filename = "samples/capitalise.t";

  }

  public void testFreqDegenerate() {
    String filename = "samples/capitalise_degenerate.t";

  }

  public void testInsertionSort() {
    String filename = "samples/capitalise.t";

  }

  public void testInsertionSortDegenerate() {
    String filename = "samples/capitalise_degenerate.t";

  }

  public void testRationals() {
    String filename = "samples/capitalise.t";

  }

  public void testRationalsDegenerate() {
    String filename = "samples/capitalise_degenerate.t";

  }
}

