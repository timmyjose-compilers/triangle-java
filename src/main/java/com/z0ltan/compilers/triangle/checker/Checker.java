package com.z0ltan.compilers.triangle.checker;

import com.z0ltan.compilers.triangle.error.CheckerError;
import com.z0ltan.compilers.triangle.ast.Program;
import com.z0ltan.compilers.triangle.ast.Visitor;
import com.z0ltan.compilers.triangle.ast.Program;
import com.z0ltan.compilers.triangle.ast.Command;
import com.z0ltan.compilers.triangle.ast.EmptyCommand;
import com.z0ltan.compilers.triangle.ast.AssignCommand;
import com.z0ltan.compilers.triangle.ast.CallCommand;
import com.z0ltan.compilers.triangle.ast.LetCommand;
import com.z0ltan.compilers.triangle.ast.IfCommand;
import com.z0ltan.compilers.triangle.ast.WhileCommand;
import com.z0ltan.compilers.triangle.ast.SequentialCommand;
import com.z0ltan.compilers.triangle.ast.Expression;
import com.z0ltan.compilers.triangle.ast.EmptyExpression;
import com.z0ltan.compilers.triangle.ast.IntegerExpression;
import com.z0ltan.compilers.triangle.ast.CharacterExpression;
import com.z0ltan.compilers.triangle.ast.VnameExpression;
import com.z0ltan.compilers.triangle.ast.LetExpression;
import com.z0ltan.compilers.triangle.ast.CallExpression;
import com.z0ltan.compilers.triangle.ast.IfExpression;
import com.z0ltan.compilers.triangle.ast.UnaryExpression;
import com.z0ltan.compilers.triangle.ast.BinaryExpression;
import com.z0ltan.compilers.triangle.ast.ArrayExpression;
import com.z0ltan.compilers.triangle.ast.RecordExpression;
import com.z0ltan.compilers.triangle.ast.SingleArrayAggregate;
import com.z0ltan.compilers.triangle.ast.MultipleArrayAggregate;
import com.z0ltan.compilers.triangle.ast.SingleRecordAggregate;
import com.z0ltan.compilers.triangle.ast.MultipleRecordAggregate;
import com.z0ltan.compilers.triangle.ast.Declaration;
import com.z0ltan.compilers.triangle.ast.ConstDeclaration;
import com.z0ltan.compilers.triangle.ast.VarDeclaration;
import com.z0ltan.compilers.triangle.ast.ProcDeclaration;
import com.z0ltan.compilers.triangle.ast.FuncDeclaration;
import com.z0ltan.compilers.triangle.ast.TypeDeclaration;
import com.z0ltan.compilers.triangle.ast.UnaryOperatorDeclaration;
import com.z0ltan.compilers.triangle.ast.BinaryOperatorDeclaration;
import com.z0ltan.compilers.triangle.ast.SequentialDeclaration;
import com.z0ltan.compilers.triangle.ast.TypeDenoter;
import com.z0ltan.compilers.triangle.ast.AnyTypeDenoter;
import com.z0ltan.compilers.triangle.ast.ErrorTypeDenoter;
import com.z0ltan.compilers.triangle.ast.BoolTypeDenoter;
import com.z0ltan.compilers.triangle.ast.CharTypeDenoter;
import com.z0ltan.compilers.triangle.ast.IntTypeDenoter;
import com.z0ltan.compilers.triangle.ast.ArrayTypeDenoter;
import com.z0ltan.compilers.triangle.ast.SimpleTypeDenoter;
import com.z0ltan.compilers.triangle.ast.SingleFieldTypeDenoter;
import com.z0ltan.compilers.triangle.ast.MultipleFieldTypeDenoter;
import com.z0ltan.compilers.triangle.ast.RecordTypeDenoter;
import com.z0ltan.compilers.triangle.ast.FormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.EmptyFormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.SingleFormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.MultipleFormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.ConstFormalParameter;
import com.z0ltan.compilers.triangle.ast.VarFormalParameter;
import com.z0ltan.compilers.triangle.ast.ProcFormalParameter;
import com.z0ltan.compilers.triangle.ast.FuncFormalParameter;
import com.z0ltan.compilers.triangle.ast.EmptyActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.SingleActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.MultipleActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.ConstActualParameter;
import com.z0ltan.compilers.triangle.ast.VarActualParameter;
import com.z0ltan.compilers.triangle.ast.ProcActualParameter;
import com.z0ltan.compilers.triangle.ast.FuncActualParameter;
import com.z0ltan.compilers.triangle.ast.SimpleVname;
import com.z0ltan.compilers.triangle.ast.DotVname;
import com.z0ltan.compilers.triangle.ast.SubscriptVname;
import com.z0ltan.compilers.triangle.ast.Identifier;
import com.z0ltan.compilers.triangle.ast.IntegerLiteral;
import com.z0ltan.compilers.triangle.ast.CharacterLiteral;
import com.z0ltan.compilers.triangle.ast.Operator;

import static com.z0ltan.compilers.triangle.scanner.SourcePosition.dummyPosition;

public class Checker implements Visitor {
  private IdentificationTable idTable;

  public Checker() {
    this.idTable = new IdentificationTable();
    establishStdEnvironment();
  }

  void establishStdEnvironment() {
    StdEnvironment.anyType = new AnyTypeDenoter(dummyPosition());
    StdEnvironment.errorType = new ErrorTypeDenoter(dummyPosition());
    StdEnvironment.boolType = new BoolTypeDenoter(dummyPosition());
    StdEnvironment.intType = new IntTypeDenoter(dummyPosition());
    StdEnvironment.charType = new CharTypeDenoter(dummyPosition());

    StdEnvironment.falseDecl = declareStdConst("false", StdEnvironment.boolType);
    StdEnvironment.trueDecl = declareStdConst("true", StdEnvironment.boolType);

    StdEnvironment.boolDecl = declareStdType("Boolean", StdEnvironment.boolType);
    StdEnvironment.intDecl = declareStdType("Integer", StdEnvironment.intType);
    StdEnvironment.charDecl = declareStdType("Char", StdEnvironment.charType);

    StdEnvironment.notDecl = declareStdUnaryOperator("\\", StdEnvironment.intType, StdEnvironment.intType);
    StdEnvironment.andDecl = declareStdBinaryOperator("/\\", StdEnvironment.boolType, StdEnvironment.boolType, StdEnvironment.boolType);
    StdEnvironment.orDecl = declareStdBinaryOperator("\\/", StdEnvironment.boolType, StdEnvironment.boolType, StdEnvironment.boolType);
    StdEnvironment.addDecl = declareStdBinaryOperator("+", StdEnvironment.intType, StdEnvironment.intType, StdEnvironment.intType);
    StdEnvironment.subDecl = declareStdBinaryOperator("-", StdEnvironment.intType, StdEnvironment.intType, StdEnvironment.intType);
    StdEnvironment.multDecl = declareStdBinaryOperator("*", StdEnvironment.intType, StdEnvironment.intType, StdEnvironment.intType);
    StdEnvironment.divDecl = declareStdBinaryOperator("/", StdEnvironment.intType, StdEnvironment.intType, StdEnvironment.intType);
    StdEnvironment.modDecl = declareStdBinaryOperator("//", StdEnvironment.intType, StdEnvironment.intType, StdEnvironment.intType);
    StdEnvironment.lessThanDecl = declareStdBinaryOperator("<", StdEnvironment.intType, StdEnvironment.intType, StdEnvironment.intType);
    StdEnvironment.lessThanOrEqualToDecl = declareStdBinaryOperator("<=", StdEnvironment.intType, StdEnvironment.intType, StdEnvironment.intType);
    StdEnvironment.greaterThanDecl = declareStdBinaryOperator(">", StdEnvironment.intType, StdEnvironment.intType, StdEnvironment.intType);
    StdEnvironment.greaterThanOrEqualToDecl = declareStdBinaryOperator(">=", StdEnvironment.intType, StdEnvironment.intType, StdEnvironment.intType);

    final Identifier dummyId = new Identifier("", dummyPosition());

    StdEnvironment.getDecl = 
      declareStdProc("get", 
          new SingleFormalParameterSequence(new VarFormalParameter(dummyId, StdEnvironment.charType, dummyPosition()), dummyPosition()), 
          new EmptyCommand(dummyPosition()));

    StdEnvironment.putDecl = 
      declareStdProc("put",
          new SingleFormalParameterSequence(new ConstFormalParameter(dummyId, StdEnvironment.charType, dummyPosition()), dummyPosition()),
          new EmptyCommand(dummyPosition()));

    StdEnvironment.getintDecl = 
      declareStdProc("getint",
          new SingleFormalParameterSequence(new VarFormalParameter(dummyId, StdEnvironment.charType, dummyPosition()), dummyPosition()),
          new EmptyCommand(dummyPosition()));

    StdEnvironment.putintDecl = 
      declareStdProc("putint",
          new SingleFormalParameterSequence(new ConstFormalParameter(dummyId, StdEnvironment.intType, dummyPosition()), dummyPosition()),
          new EmptyCommand(dummyPosition()));

    StdEnvironment.geteolDecl = 
      declareStdProc("geteol",
          new EmptyFormalParameterSequence(dummyPosition()),
          new EmptyCommand(dummyPosition()));

    StdEnvironment.puteolDecl = 
      declareStdProc("puteol",
          new EmptyFormalParameterSequence(dummyPosition()),
          new EmptyCommand(dummyPosition()));

    StdEnvironment.eolDecl = 
      declareStdFunc("eol",
          new EmptyFormalParameterSequence(dummyPosition()),
          StdEnvironment.boolType,
          new EmptyExpression(dummyPosition()));

    StdEnvironment.eofDecl  =
      declareStdFunc("eof",
          new EmptyFormalParameterSequence(dummyPosition()),
          StdEnvironment.boolType,
          new EmptyExpression(dummyPosition()));

    StdEnvironment.chrDecl = 
      declareStdFunc("chr",
          new SingleFormalParameterSequence(new ConstFormalParameter(dummyId, StdEnvironment.intType, dummyPosition()), dummyPosition()),
          StdEnvironment.charType,
          new EmptyExpression(dummyPosition()));

    StdEnvironment.ordDecl = 
      declareStdFunc("ord",
          new SingleFormalParameterSequence(new ConstFormalParameter(dummyId, StdEnvironment.charType, dummyPosition()), dummyPosition()),
          StdEnvironment.intType,
          new EmptyExpression(dummyPosition()));
  }

  FuncDeclaration declareStdFunc(final String id, final FormalParameterSequence fps, final TypeDenoter retType, final Expression body) {
    FuncDeclaration decl = new FuncDeclaration(new Identifier(id, dummyPosition()), fps, retType, body, dummyPosition());
    // enter into idTable
    return decl;
  }

  ProcDeclaration declareStdProc(final String id, final FormalParameterSequence fps, final Command cmd) {
    ProcDeclaration decl = new ProcDeclaration(new Identifier(id, dummyPosition()), fps, cmd, dummyPosition());
    this.idTable.save(id, decl);
    return decl;
  }

  BinaryOperatorDeclaration declareStdBinaryOperator(final String id, final TypeDenoter arg1Type, final TypeDenoter arg2Type, final TypeDenoter resType) {
    BinaryOperatorDeclaration decl = new BinaryOperatorDeclaration(arg1Type, new Operator(id, dummyPosition()), arg2Type, resType, dummyPosition());
    this.idTable.save(id, decl);
    return decl;
  }

  UnaryOperatorDeclaration declareStdUnaryOperator(final String id, final TypeDenoter argType, final TypeDenoter resType) {
    UnaryOperatorDeclaration decl = new UnaryOperatorDeclaration(new Operator(id, dummyPosition()), argType, resType, dummyPosition());
    // enter into idTable
    return decl;
  }

  TypeDeclaration declareStdType(final String id, final TypeDenoter baseType) {
    TypeDeclaration decl = new TypeDeclaration(new Identifier(id, dummyPosition()), baseType, dummyPosition());
    this.idTable.save(id, decl);
    return decl;
  }

  ConstDeclaration declareStdConst(final String id, final TypeDenoter constType) {
    IntegerExpression iexpr = new IntegerExpression(null, dummyPosition());
    iexpr.type = constType;
    ConstDeclaration decl = new ConstDeclaration(new Identifier(id, dummyPosition()), iexpr, dummyPosition());
    this.idTable.save(id, decl);
    return decl;
  }

  public void check(final Program program) {
    program.accept(this, null);
  }

  @Override
  public Object visit(final Program program, final Object arg) {
    return program.C.accept(this, null);
  }

  @Override
  public Object visit(final EmptyCommand cmd, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final AssignCommand cmd, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final CallCommand cmd, final Object arg) {
    cmd.I.accept(this, null);
    cmd.APS.accept(this, null);
    return null;
  }

  @Override
  public Object visit(final LetCommand cmd, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final IfCommand cmd, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final WhileCommand cmd, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final SequentialCommand cmd, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final EmptyExpression expr, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final IntegerExpression expr, final Object arg) {
    final IntegerLiteral il = (IntegerLiteral) expr.IL.accept(this, null);
    expr.type = StdEnvironment.intType;
    return expr.type;
  }

  @Override
  public Object visit(final CharacterExpression expr, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final VnameExpression expr, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final LetExpression expr, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final CallExpression expr, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final IfExpression expr, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final UnaryExpression expr, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final BinaryExpression expr, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final ArrayExpression expr, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final RecordExpression expr, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final SingleArrayAggregate agg, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final MultipleArrayAggregate agg, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final SingleRecordAggregate agg, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final MultipleRecordAggregate agg, final Object arg) {
    return null; // TODO
  }


  @Override
  public Object visit(final ConstDeclaration decl, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final VarDeclaration decl, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final ProcDeclaration decl, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final FuncDeclaration decl, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final TypeDeclaration decl, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final UnaryOperatorDeclaration decl, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final BinaryOperatorDeclaration decl, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final SequentialDeclaration decl, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final AnyTypeDenoter td, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final ErrorTypeDenoter td, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final BoolTypeDenoter td, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final CharTypeDenoter td, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final IntTypeDenoter td, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final ArrayTypeDenoter td, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final SimpleTypeDenoter td, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final SingleFieldTypeDenoter td, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final MultipleFieldTypeDenoter td, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final RecordTypeDenoter td, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final EmptyFormalParameterSequence fps, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final SingleFormalParameterSequence fps, final Object args) {
    return null; // TODO
  }

  @Override
  public Object visit(final MultipleFormalParameterSequence fps, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final ConstFormalParameter param, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final VarFormalParameter param, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final ProcFormalParameter param, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final FuncFormalParameter param, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final EmptyActualParameterSequence aps, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final SingleActualParameterSequence aps, final Object arg) {
    aps.AP.accept(this, null);
    return null;
  }

  @Override
  public Object visit(final MultipleActualParameterSequence aps, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final ConstActualParameter param, final Object arg) {
    param.E.accept(this, null);
    return null;
  }

  @Override
  public Object visit(final VarActualParameter param, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final ProcActualParameter param, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final FuncActualParameter param, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final SimpleVname vname, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final DotVname vname, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final SubscriptVname vname, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final Identifier id, final Object arg) {
    final Declaration binding = (Declaration) this.idTable.retrieve(id.spelling);
    id.decl = binding;
    return binding;
  }

  @Override
  public Object visit(final IntegerLiteral il, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final CharacterLiteral cl, final Object arg) {
    return null; // TODO
  }

  @Override
  public Object visit(final Operator op, final Object arg) {
    return null; // TODO
  }
}
