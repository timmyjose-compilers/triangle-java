package com.z0ltan.compilers.triangle.checker;

import com.z0ltan.compilers.triangle.ast.Visitor;
import com.z0ltan.compilers.triangle.ast.Program;
import com.z0ltan.compilers.triangle.ast.VarDeclaration;
import com.z0ltan.compilers.triangle.ast.ConstDeclaration;
import com.z0ltan.compilers.triangle.ast.TypeDeclaration;
import com.z0ltan.compilers.triangle.ast.ProcDeclaration;
import com.z0ltan.compilers.triangle.ast.FuncDeclaration;
import com.z0ltan.compilers.triangle.ast.UnaryOperatorDeclaration;
import com.z0ltan.compilers.triangle.ast.BinaryOperatorDeclaration;
import com.z0ltan.compilers.triangle.ast.SequentialDeclaration;
import com.z0ltan.compilers.triangle.ast.EmptyCommand;
import com.z0ltan.compilers.triangle.ast.AssignCommand;
import com.z0ltan.compilers.triangle.ast.CallCommand;
import com.z0ltan.compilers.triangle.ast.LetCommand;
import com.z0ltan.compilers.triangle.ast.WhileCommand;
import com.z0ltan.compilers.triangle.ast.IfCommand;
import com.z0ltan.compilers.triangle.ast.SequentialCommand;
import com.z0ltan.compilers.triangle.ast.EmptyExpression;
import com.z0ltan.compilers.triangle.ast.IntegerExpression;
import com.z0ltan.compilers.triangle.ast.CallExpression;
import com.z0ltan.compilers.triangle.ast.LetExpression;
import com.z0ltan.compilers.triangle.ast.IfExpression;
import com.z0ltan.compilers.triangle.ast.VnameExpression;
import com.z0ltan.compilers.triangle.ast.CharacterExpression;
import com.z0ltan.compilers.triangle.ast.ArrayExpression;
import com.z0ltan.compilers.triangle.ast.RecordExpression;
import com.z0ltan.compilers.triangle.ast.UnaryExpression;
import com.z0ltan.compilers.triangle.ast.BinaryExpression;
import com.z0ltan.compilers.triangle.ast.SingleArrayAggregate;
import com.z0ltan.compilers.triangle.ast.MultipleArrayAggregate;
import com.z0ltan.compilers.triangle.ast.SingleRecordAggregate;
import com.z0ltan.compilers.triangle.ast.MultipleRecordAggregate;
import com.z0ltan.compilers.triangle.ast.SimpleVname;
import com.z0ltan.compilers.triangle.ast.DotVname;
import com.z0ltan.compilers.triangle.ast.SubscriptVname;
import com.z0ltan.compilers.triangle.ast.TypeDenoter;
import com.z0ltan.compilers.triangle.ast.SimpleTypeDenoter;
import com.z0ltan.compilers.triangle.ast.AnyTypeDenoter;
import com.z0ltan.compilers.triangle.ast.ErrorTypeDenoter;
import com.z0ltan.compilers.triangle.ast.IntTypeDenoter;
import com.z0ltan.compilers.triangle.ast.CharTypeDenoter;
import com.z0ltan.compilers.triangle.ast.BoolTypeDenoter;
import com.z0ltan.compilers.triangle.ast.ArrayTypeDenoter;
import com.z0ltan.compilers.triangle.ast.RecordTypeDenoter;
import com.z0ltan.compilers.triangle.ast.SingleFieldTypeDenoter;
import com.z0ltan.compilers.triangle.ast.MultipleFieldTypeDenoter;
import com.z0ltan.compilers.triangle.ast.FormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.EmptyFormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.SingleFormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.MultipleFormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.VarFormalParameter;
import com.z0ltan.compilers.triangle.ast.ConstFormalParameter;
import com.z0ltan.compilers.triangle.ast.ProcFormalParameter;
import com.z0ltan.compilers.triangle.ast.FuncFormalParameter;
import com.z0ltan.compilers.triangle.ast.EmptyActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.SingleActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.MultipleActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.VarActualParameter;
import com.z0ltan.compilers.triangle.ast.ConstActualParameter;
import com.z0ltan.compilers.triangle.ast.ProcActualParameter;
import com.z0ltan.compilers.triangle.ast.FuncActualParameter;
import com.z0ltan.compilers.triangle.ast.IntegerLiteral;
import com.z0ltan.compilers.triangle.ast.CharacterLiteral;
import com.z0ltan.compilers.triangle.ast.Identifier;
import com.z0ltan.compilers.triangle.ast.Operator;

import static com.z0ltan.compilers.triangle.scanner.SourcePosition.dummyPosition;
import static com.z0ltan.compilers.triangle.error.ErrorReporter.reportError;

public class Checker implements Visitor {
  private IdentificationTable idTable;

  public Checker() {
    this.idTable = new IdentificationTable();
    establishStdEnvironment();
  }

  void establishStdEnvironment() {
    StdEnvironment.anyType = new AnyTypeDenoter(dummyPosition());
    StdEnvironment.errorType = new ErrorTypeDenoter(dummyPosition());
    StdEnvironment.intType = new IntTypeDenoter(dummyPosition());
    StdEnvironment.charType = new CharTypeDenoter(dummyPosition());
    StdEnvironment.boolType = new BoolTypeDenoter(dummyPosition());

    StdEnvironment.intDecl = new TypeDeclaration(new Identifier("Integer", dummyPosition()), StdEnvironment.intType, dummyPosition());
    StdEnvironment.charDecl = new TypeDeclaration(new Identifier("Char", dummyPosition()), StdEnvironment.charType, dummyPosition());
    StdEnvironment.boolDecl = new TypeDeclaration(new Identifier("Boolean", dummyPosition()), StdEnvironment.boolType, dummyPosition());

    StdEnvironment.falseDecl = declareStdConst("false");
    StdEnvironment.trueDecl = declareStdConst("true");

    StdEnvironment.notDecl = declareStdUnaryOp("\\", StdEnvironment.boolType, StdEnvironment.boolType);
    StdEnvironment.andDecl = declareStdBinaryOp("/\\", StdEnvironment.boolType, StdEnvironment.boolType, StdEnvironment.boolType);
    StdEnvironment.orDecl= declareStdBinaryOp("\\/", StdEnvironment.boolType, StdEnvironment.boolType, StdEnvironment.boolType);
    StdEnvironment.addDecl = declareStdBinaryOp("+", StdEnvironment.intType, StdEnvironment.intType, StdEnvironment.intType);
    StdEnvironment.subDecl = declareStdBinaryOp("-", StdEnvironment.intType, StdEnvironment.intType, StdEnvironment.intType);
    StdEnvironment.multDecl = declareStdBinaryOp("*", StdEnvironment.intType, StdEnvironment.intType, StdEnvironment.intType);
    StdEnvironment.divDecl = declareStdBinaryOp("/", StdEnvironment.intType, StdEnvironment.intType, StdEnvironment.intType);
    StdEnvironment.modDecl = declareStdBinaryOp("//", StdEnvironment.intType, StdEnvironment.intType, StdEnvironment.intType);
    StdEnvironment.lessThanDecl = declareStdBinaryOp("<", StdEnvironment.intType, StdEnvironment.intType, StdEnvironment.intType);
    StdEnvironment.lessThanOrEqualToDecl= declareStdBinaryOp("<=", StdEnvironment.intType, StdEnvironment.intType, StdEnvironment.intType);
    StdEnvironment.greaterThanDecl = declareStdBinaryOp(">", StdEnvironment.intType, StdEnvironment.intType, StdEnvironment.intType);
    StdEnvironment.greaterThanOrEqualToDecl = declareStdBinaryOp(">=", StdEnvironment.intType, StdEnvironment.intType, StdEnvironment.intType);

    final Identifier dummyId = new Identifier("", dummyPosition());

    StdEnvironment.getDecl = 
      declareStdProc("get", new SingleFormalParameterSequence(new VarFormalParameter(dummyId, StdEnvironment.charType,  dummyPosition()), dummyPosition()));

    StdEnvironment.getintDecl =
      declareStdProc("getint", new SingleFormalParameterSequence(new VarFormalParameter(dummyId, StdEnvironment.intType, dummyPosition()), dummyPosition()));

    StdEnvironment.geteolDecl = 
      declareStdProc("geteol", new EmptyFormalParameterSequence(dummyPosition()));

    StdEnvironment.putDecl = 
      declareStdProc("put", new SingleFormalParameterSequence(new ConstFormalParameter(dummyId, StdEnvironment.charType, dummyPosition()), dummyPosition()));

    StdEnvironment.putintDecl = 
      declareStdProc("putint", new SingleFormalParameterSequence(new ConstFormalParameter(dummyId, StdEnvironment.intType, dummyPosition()), dummyPosition()));

    StdEnvironment.puteolDecl =
      declareStdProc("puteol", new EmptyFormalParameterSequence(dummyPosition()));

    StdEnvironment.chrDecl = 
      declareStdFunc("chr", 
          new SingleFormalParameterSequence(new ConstFormalParameter(dummyId, StdEnvironment.intType, dummyPosition()), dummyPosition()),
          StdEnvironment.charType);

    StdEnvironment.ordDecl = 
      declareStdFunc("ord", 
          new SingleFormalParameterSequence(new ConstFormalParameter(dummyId, StdEnvironment.charType, dummyPosition()), dummyPosition()),
          StdEnvironment.intType);

    StdEnvironment.eolDecl = declareStdFunc("eol", new EmptyFormalParameterSequence(dummyPosition()), StdEnvironment.boolType);
    StdEnvironment.eofDecl = declareStdFunc("eof", new EmptyFormalParameterSequence(dummyPosition()), StdEnvironment.boolType);
  }

  FuncDeclaration declareStdFunc(final String id, final FormalParameterSequence fps, final TypeDenoter resType) {
    final FuncDeclaration decl = new FuncDeclaration(new Identifier(id, dummyPosition()), fps, resType, new EmptyExpression(dummyPosition()), dummyPosition());
    return decl;
  }

  ProcDeclaration declareStdProc(final String id, final FormalParameterSequence fps) {
    final ProcDeclaration decl = new ProcDeclaration(new Identifier(id, dummyPosition()), fps, new EmptyCommand(dummyPosition()), dummyPosition());
    return decl;
  }

  BinaryOperatorDeclaration declareStdBinaryOp(final String id, final TypeDenoter arg1Type, final TypeDenoter arg2Type, final TypeDenoter resType) {
    final BinaryOperatorDeclaration decl = new BinaryOperatorDeclaration(arg1Type, new Operator(id, dummyPosition()), arg2Type, resType, dummyPosition());
    return decl;
  }

  UnaryOperatorDeclaration declareStdUnaryOp(final String id, final TypeDenoter argType, final TypeDenoter resType) {
    final UnaryOperatorDeclaration decl = new UnaryOperatorDeclaration(new Operator(id, dummyPosition()), argType, resType, dummyPosition());
    return decl;
  }

  ConstDeclaration declareStdConst(final String id) {
    final IntegerExpression il = new IntegerExpression(new IntegerLiteral("0", dummyPosition()), dummyPosition());
    final ConstDeclaration decl = new ConstDeclaration(new Identifier(id, dummyPosition()), il, dummyPosition());
    return decl;
  }

  public void check(final Program program) {
  }

  @Override
  public Object visit(final Program prog, Object arg) {
    return null;
  }

  @Override
  public Object visit(final EmptyCommand cmd, Object arg) {
    return null;
  }

  @Override
  public Object visit(final AssignCommand cmd, Object arg) {
    return null;
  }

  @Override
  public Object visit(final CallCommand cmd, Object arg) {
    return null;
  }

  @Override
  public Object visit(final LetCommand cmd, Object arg) {
    return null;
  }

  @Override
  public Object visit(final IfCommand cmd, Object arg) {
    return null;
  }
  @Override
  public Object visit(final WhileCommand cmd, Object arg) {
    return null;
  }
  @Override
  public Object visit(final SequentialCommand cmd, Object arg) {
    return null;
  }

  @Override
  public Object visit(final EmptyExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(final IntegerExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(final CharacterExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(final VnameExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(final LetExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(final CallExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(final IfExpression expr, Object arg) {
    return null;
  }


  @Override
  public Object visit(final UnaryExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(final BinaryExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(final ArrayExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(final RecordExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(final SingleArrayAggregate agg, Object arg) {
    return null;
  }

  @Override
  public Object visit(final MultipleArrayAggregate agg, Object arg) {
    return null;
  }

  @Override
  public Object visit(final SingleRecordAggregate agg, Object arg) {
    return null;
  }

  @Override
  public Object visit(final MultipleRecordAggregate agg, Object arg) {
    return null;
  }

  @Override
  public Object visit(final ConstDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(final VarDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(final ProcDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(final FuncDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(final TypeDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(final UnaryOperatorDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(final BinaryOperatorDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(final SequentialDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(final AnyTypeDenoter td, Object arg) {
    return null;
  }

  @Override
  public Object visit(final ErrorTypeDenoter td, Object arg) {
    return null;
  }

  @Override
  public Object visit(final BoolTypeDenoter td, Object arg) {
    return null;
  }

  @Override
  public Object visit(final CharTypeDenoter td, Object arg) {
    return null;
  }

  @Override
  public Object visit(final IntTypeDenoter td, Object arg) {
    return null;
  }

  @Override
  public Object visit(final ArrayTypeDenoter td, Object arg) {
    return null;
  }

  @Override
  public Object visit(final SimpleTypeDenoter td, Object arg) {
    return null;
  }

  @Override
  public Object visit(final SingleFieldTypeDenoter td, Object arg) {
    return null;
  }

  @Override
  public Object visit(final MultipleFieldTypeDenoter td, Object arg) {
    return null;
  }

  @Override
  public Object visit(final RecordTypeDenoter td, Object arg) {
    return null;
  }

  @Override
  public Object visit(final EmptyFormalParameterSequence fps, Object arg) {
    return null;
  }

  @Override
  public Object visit(final SingleFormalParameterSequence fps, Object args) {
    return null;
  }

  @Override
  public Object visit(final MultipleFormalParameterSequence fps, Object arg) {
    return null;
  }

  @Override
  public Object visit(final ConstFormalParameter param, Object arg) {
    return null;
  }

  @Override
  public Object visit(final VarFormalParameter param, Object arg) {
    return null;
  }

  @Override
  public Object visit(final ProcFormalParameter param, Object arg) {
    return null;
  }

  @Override
  public Object visit(final FuncFormalParameter param, Object arg) {
    return null;
  }

  @Override
  public Object visit(final EmptyActualParameterSequence aps, Object arg) {
    return null;
  }

  @Override
  public Object visit(final SingleActualParameterSequence aps, Object arg) {
    return null;
  }

  @Override
  public Object visit(final MultipleActualParameterSequence aps, Object arg) {
    return null;
  }

  @Override
  public Object visit(final ConstActualParameter param, Object arg) {
    return null;
  }

  @Override
  public Object visit(final VarActualParameter param, Object arg) {
    return null;
  }

  @Override
  public Object visit(final ProcActualParameter param, Object arg) {
    return null;
  }

  @Override
  public Object visit(final FuncActualParameter param, Object arg) {
    return null;
  }

  @Override
  public Object visit(final SimpleVname vname, Object arg) {
    return null;
  }

  @Override
  public Object visit(final DotVname vname, Object arg) {
    return null;
  }

  @Override
  public Object visit(final SubscriptVname vname, Object arg) {
    return null;
  }

  @Override
  public Object visit(final Identifier id, Object arg) {
    return null;
  }

  @Override
  public Object visit(final IntegerLiteral il, Object arg) {
    return null;
  }

  @Override
  public Object visit(final CharacterLiteral cl, Object arg) {
    return null;
  }

  @Override
  public Object visit(final Operator op, Object arg) {
    return null;
  }
}
