package com.z0ltan.compilers.triangle.checker;

import com.z0ltan.compilers.triangle.ast.Visitor;
import com.z0ltan.compilers.triangle.ast.Program;
import com.z0ltan.compilers.triangle.ast.Declaration;
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
import com.z0ltan.compilers.triangle.ast.FormalParameter;
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

import com.z0ltan.compilers.triangle.error.CheckerError;

import static com.z0ltan.compilers.triangle.scanner.SourcePosition.dummyPosition;
import static com.z0ltan.compilers.triangle.error.ErrorReporter.reportError;

public class Checker implements Visitor {
  private IdentificationTable idTable;

  public Checker() {
    this.idTable = new IdentificationTable();
    establishStdEnvironment();
  }

  void establishStdEnvironment() {
    idTable.openScope();
    StdEnvironment.anyType = new AnyTypeDenoter(dummyPosition());
    StdEnvironment.errorType = new ErrorTypeDenoter(dummyPosition());
    StdEnvironment.intType = new IntTypeDenoter(dummyPosition());
    StdEnvironment.charType = new CharTypeDenoter(dummyPosition());
    StdEnvironment.boolType = new BoolTypeDenoter(dummyPosition());

    StdEnvironment.intDecl = declareStdType("Integer", StdEnvironment.intType);
    StdEnvironment.charDecl = declareStdType("Char", StdEnvironment.charType);
    StdEnvironment.boolDecl = declareStdType("Boolean", StdEnvironment.boolType);

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
    StdEnvironment.eqDecl = declareStdBinaryOp("=", StdEnvironment.anyType, StdEnvironment.anyType, StdEnvironment.boolType);
    StdEnvironment.noteqDecl = declareStdBinaryOp("\\=", StdEnvironment.anyType, StdEnvironment.anyType, StdEnvironment.boolType);
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
    this.idTable.save(id, decl);

    return decl;
  }

  ProcDeclaration declareStdProc(final String id, final FormalParameterSequence fps) {
    final ProcDeclaration decl = new ProcDeclaration(new Identifier(id, dummyPosition()), fps, new EmptyCommand(dummyPosition()), dummyPosition());
    this.idTable.save(id, decl);

    return decl;
  }

  BinaryOperatorDeclaration declareStdBinaryOp(final String id, final TypeDenoter arg1Type, final TypeDenoter arg2Type, final TypeDenoter resType) {
    final BinaryOperatorDeclaration decl = new BinaryOperatorDeclaration(arg1Type, new Operator(id, dummyPosition()), arg2Type, resType, dummyPosition());
    this.idTable.save(id, decl);

    return decl;
  }

  UnaryOperatorDeclaration declareStdUnaryOp(final String id, final TypeDenoter argType, final TypeDenoter resType) {
    final UnaryOperatorDeclaration decl = new UnaryOperatorDeclaration(new Operator(id, dummyPosition()), argType, resType, dummyPosition());
    this.idTable.save(id, decl);

    return decl;
  }

  ConstDeclaration declareStdConst(final String id) {
    final IntegerExpression il = new IntegerExpression(new IntegerLiteral("0", dummyPosition()), dummyPosition());
    final ConstDeclaration decl = new ConstDeclaration(new Identifier(id, dummyPosition()), il, dummyPosition());
    this.idTable.save(id, decl);

    return decl;
  }

  TypeDeclaration declareStdType(final String spelling, final TypeDenoter td) {
    final TypeDeclaration decl = new TypeDeclaration(new Identifier(spelling, dummyPosition()), td, dummyPosition());
    this.idTable.save(spelling, decl);

    return decl;
  }

  public void check(final Program program) {
    program.accept(this, null);
  }

  @Override
  public Object visit(final Program prog, Object arg) {
    prog.C.accept(this, null);
    return null;
  }

  @Override
  public Object visit(final EmptyCommand cmd, Object arg) {
    return null;
  }

  @Override
  public Object visit(final AssignCommand cmd, Object arg) {
    final TypeDenoter vType = (TypeDenoter)cmd.V.accept(this, null);
    final TypeDenoter eType = (TypeDenoter)cmd.E.accept(this, null);

    if (!cmd.V.variable) {
      throw new CheckerError(reportError(cmd.position, "can assign only to a variable.", cmd.V, "is not a variable"));
    }

    if (!vType.equals(eType)) {
      throw new CheckerError(reportError(cmd.position, "type mismatch in assignment. lhs has type", vType, ", rhs has type", eType));
    }

    return null;
  }

  @Override
  public Object visit(final CallCommand cmd, Object arg) {
    final Declaration binding = (Declaration)cmd.I.accept(this, null);
    if (binding == null) {
      throw new CheckerError(reportError(cmd.position, cmd.I.spelling, "is not a declared procedure"));
    }

    if (binding instanceof ProcDeclaration) {
      cmd.APS.accept(this, ((ProcDeclaration)binding).FPS);
    } else if (binding instanceof ProcFormalParameter) {
      cmd.APS.accept(this, ((ProcFormalParameter)binding).FPS);
    } else {
      throw new CheckerError(reportError(cmd.position, cmd.I.spelling, " is not a declared procedure"));
    }

    return null;
  }

  @Override
  public Object visit(final LetCommand cmd, Object arg) {
    this.idTable.openScope();
    cmd.D.accept(this, null);
    cmd.C.accept(this, null);
    this.idTable.closeScope();

    return null;
  }

  @Override
  public Object visit(final IfCommand cmd, Object arg) {
    final TypeDenoter eType = (TypeDenoter)cmd.E.accept(this, null);
    if (!eType.equals(StdEnvironment.boolType)) {
      throw new CheckerError(reportError(cmd.position, "the conditional of an if command must be a booolean, found", eType));
    }

    cmd.C1.accept(this, null);
    cmd.C2.accept(this, null);

    return null;
  }

  @Override
  public Object visit(final WhileCommand cmd, Object arg) {
    final TypeDenoter eType = (TypeDenoter)cmd.E.accept(this, null);

    if (!eType.equals(StdEnvironment.boolType)) {
      throw new CheckerError(reportError(cmd.position, "check for a while command must be a boolean, got", eType));
    }

    cmd.C.accept(this, null);

    return null;
  }
  @Override
  public Object visit(final SequentialCommand cmd, Object arg) {
    cmd.C1.accept(this, null);
    cmd.C2.accept(this, null);

    return null;
  }

  @Override
  public Object visit(final EmptyExpression expr, Object arg) {
    expr.type = null;
    return expr.type;
  }

  @Override
  public Object visit(final IntegerExpression expr, Object arg) {
    expr.type = (TypeDenoter)expr.IL.accept(this, null);
    return expr.type;
  }

  @Override
  public Object visit(final CharacterExpression expr, Object arg) {
    expr.type = (TypeDenoter)expr.CL.accept(this, null);
    return expr.type;
  }

  @Override
  public Object visit(final VnameExpression expr, Object arg) {
    expr.type = (TypeDenoter)expr.V.accept(this, null);
    return expr.type;
  }

  @Override
  public Object visit(final LetExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(final CallExpression expr, Object arg) {
    final Declaration binding = (Declaration)expr.I.accept(this, null);

    if (binding == null) {
      throw new CheckerError(reportError(expr.position, expr.I, "is not a declared function"));
    }

    if (binding instanceof FuncDeclaration) {
      expr.APS.accept(this, ((FuncDeclaration)binding).FPS);
      final FuncDeclaration fd = (FuncDeclaration)binding;
      expr.type = ((FuncDeclaration)binding).T;
    } else if (binding instanceof FuncFormalParameter) {
      expr.APS.accept(this, ((FuncFormalParameter)binding).FPS);
      expr.type = ((FuncFormalParameter)binding).T;
    } else {
      throw new CheckerError(reportError(expr.position, expr.I, "is not a valid function identifier"));
    }

    return expr.type;
  }

  @Override
  public Object visit(final IfExpression expr, Object arg) {
    final TypeDenoter e1Type = (TypeDenoter)expr.E1.accept(this, null);
    final TypeDenoter e2Type = (TypeDenoter)expr.E2.accept(this, null);
    final TypeDenoter e3Type = (TypeDenoter)expr.E3.accept(this, null);

    if (!e1Type.equals(StdEnvironment.boolType)) {
      throw new CheckerError(reportError(expr.position, "the condition of an if expression must be boolean, got", e1Type));
    }

    if (!e2Type.equals(e3Type)) {
      throw new CheckerError(reportError(expr.position, 
            "both arms of an if expression must have the same type, but arm 1 has type",
            e2Type,
            "and arm 2 has type",
            e3Type));
    }

    expr.type = e2Type;

    return expr.type;
  }


  @Override
  public Object visit(final UnaryExpression expr, Object arg) {
    final Declaration binding = (Declaration)expr.O.accept(this, null);
    final TypeDenoter eType = (TypeDenoter)expr.E.accept(this, null);

    if (binding == null) {
      throw new CheckerError(reportError(expr.position, expr.O, "is not a declared operator"));
    }

    if (binding instanceof BinaryOperatorDeclaration) {
      throw new CheckerError(reportError(expr.position, "expected a unary operator, but", expr.O, "is a binary operator"));
    } else if (binding instanceof UnaryOperatorDeclaration) {
      final UnaryOperatorDeclaration unopDecl = (UnaryOperatorDeclaration)binding;

      if (!eType.equals(unopDecl.ARGTYPE)) {
        throw new CheckerError(reportError(expr.position, 
              "mismatched argument type for unary operator", expr.O, "expected", unopDecl.ARGTYPE, "got", eType));
      }
      expr.type = unopDecl.RESTYPE;
    } else {
      throw new CheckerError(reportError(expr.position, expr.O, "is not a valid declared operator"));
    }

    return expr.type;
  }

  @Override
  public Object visit(final BinaryExpression expr, Object arg) {
    final TypeDenoter e1Type = (TypeDenoter)expr.E1.accept(this, null);
    final TypeDenoter e2Type = (TypeDenoter)expr.E2.accept(this, null);

    if (!e1Type.equals(e2Type)) {
      throw new CheckerError(reportError(expr.position, "mismatched types: first expression has type", e1Type, ", and second expression has type", e2Type));
    }

    final Declaration binding = (Declaration)expr.O.accept(this, null);
    if (binding == null) {
      throw new CheckerError(reportError(expr.position, "operator", expr.O.spelling, "is not declared"));
    }

    if (binding instanceof UnaryOperatorDeclaration) {
      throw new CheckerError(reportError(expr.position, "expected a binary operator here, but", expr.O.spelling, "is unary"));
    } else if (binding instanceof BinaryOperatorDeclaration) {
      final BinaryOperatorDeclaration binopDecl = (BinaryOperatorDeclaration)binding;

      if (binopDecl.ARG1TYPE.equals(StdEnvironment.anyType) && binopDecl.ARG2TYPE.equals(StdEnvironment.anyType)) {
        if (!e1Type.equals(e2Type)) {
          throw new CheckerError(reportError(expr.position, 
                "mismatched types for operator", binopDecl.O.spelling,
                ", expected expressions of the same type, but found types",
                e1Type, "and", e2Type));
        }
      } else {
        if (!e1Type.equals(binopDecl.ARG1TYPE)) {
          throw new CheckerError(reportError(expr.position, "expected first expression of type", binopDecl.ARG1TYPE, "but was of type", e1Type));
        }

        if (!e2Type.equals(binopDecl.ARG2TYPE)) {
          throw new CheckerError(reportError(expr.position, "expected second expression of type", binopDecl.ARG2TYPE, "but was of type", e2Type));
        }
      }  
      expr.type = binopDecl.RESTYPE;
    } else {
      throw new CheckerError(reportError(expr.position, "a binary operator was expected here"));
    }

    return expr.type;
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
    this.idTable.save(decl.I.spelling, decl);
    decl.I.accept(this, null);
    decl.T = (TypeDenoter)decl.T.accept(this, null);

    return null;
  }

  @Override
  public Object visit(final ProcDeclaration decl, Object arg) {
    this.idTable.save(decl.I.spelling, decl);
    decl.I.accept(this, null);
    this.idTable.openScope();
    decl.FPS.accept(this, null);
    decl.C.accept(this, null);
    this.idTable.closeScope();

    return null;
  }

  @Override
  public Object visit(final FuncDeclaration decl, Object arg) {
    this.idTable.save(decl.I.spelling, decl);
    decl.I.accept(this, null);
    this.idTable.openScope();
    decl.FPS.accept(this, null);
    decl.T = (TypeDenoter)decl.T.accept(this, null);
    decl.E.accept(this, null);
    this.idTable.closeScope();

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
    decl.D1.accept(this, null);
    decl.D2.accept(this, null);

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
    final Declaration binding = (Declaration)td.I.accept(this, null);

    if (binding == null) {
      throw new CheckerError(reportError(td.position, td.I, "is an undeclared type identifier"));
    }

    if (!(binding instanceof TypeDeclaration)) {
      throw new CheckerError(reportError(td.position, td.I, "is not a type identifier"));
    }

    return ((TypeDeclaration)binding).T;
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
    fps.FP.accept(this, null);
    return null;
  }

  @Override
  public Object visit(final MultipleFormalParameterSequence fps, Object arg) {
    fps.FP.accept(this, null);
    fps.FPS.accept(this, null);

    return null;
  }

  @Override
  public Object visit(final ConstFormalParameter param, Object arg) {
    if (this.idTable.isPresent(param.I.spelling)) {
      throw new CheckerError(reportError(param.position, param.I.spelling, "is already declared"));
    }

    this.idTable.save(param.I.spelling, param);
    param.I.accept(this, null);
    param.T = (TypeDenoter)param.T.accept(this, null);

    return null;
  }

  @Override
  public Object visit(final VarFormalParameter param, Object arg) {
    if (this.idTable.isPresent(param.I.spelling)) {
      throw new CheckerError(reportError(param.position, param.I.spelling, "is already declared"));
    }

    this.idTable.save(param.I.spelling, param);
    param.I.accept(this, null);
    param.T = (TypeDenoter)param.T.accept(this, null);

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
    final FormalParameterSequence fps = (FormalParameterSequence) arg;

    if (fps instanceof SingleFormalParameterSequence) {
      final SingleFormalParameterSequence sfps = (SingleFormalParameterSequence)fps;
      aps.AP.accept(this, sfps.FP);
    } else if (fps instanceof MultipleFormalParameterSequence) {
      throw new CheckerError(reportError(aps.position, "too few actual parameters"));
    }

    return null;
  }

  @Override
  public Object visit(final MultipleActualParameterSequence aps, Object arg) {
    final FormalParameterSequence fps = (FormalParameterSequence)arg;

    if (fps instanceof SingleFormalParameterSequence) {
      throw new CheckerError(reportError(aps.position, "too few actual parameters"));
    } else {
      final MultipleFormalParameterSequence mfps = (MultipleFormalParameterSequence)fps;
      aps.AP.accept(this, mfps.FP);
      aps.APS.accept(this, mfps.FPS);
    }

    return null;
  }

  @Override
  public Object visit(final ConstActualParameter param, Object arg) {
    final ConstFormalParameter cfp = (ConstFormalParameter)arg;
    final TypeDenoter actualType = (TypeDenoter)param.E.accept(this, null);

    if (!actualType.equals(cfp.T)) {
      throw new CheckerError(reportError(param.position, "mismatch in actual parameter types, expected", cfp.T, "got", actualType));
    }

    return null;
  }

  @Override
  public Object visit(final VarActualParameter param, Object arg) {
    final VarFormalParameter vfp = (VarFormalParameter)arg;
    final TypeDenoter actualType = (TypeDenoter)param.V.accept(this, null);

    if (!actualType.equals(vfp.T)) {
      throw new CheckerError(reportError(param.position, "mismatch in actual parameter types, expected", vfp.T, "got", actualType));
    }

    if (!param.V.variable) {
      throw new CheckerError(reportError(param.position, "expected param to be a variable, but was not"));
    }

    return null;
  }

  @Override
  public Object visit(final ProcActualParameter param, Object arg) {
    final FormalParameter fp = (FormalParameter)arg;
    final Declaration binding = (Declaration)param.I.accept(this, null);

    if (binding == null) {
      throw new CheckerError(reportError(param.position, param.I, "is not declared"));
    }

    if (!(binding instanceof ProcFormalParameter) || (binding instanceof ProcDeclaration)) {
      throw new CheckerError(reportError(param.position, param.I, "is not a valid procedure identifier"));
    } 

    if (!(fp instanceof ProcFormalParameter)) {
      throw new CheckerError(reportError(param.position, "proc actual parameter was not expected here"));
    } else{
      FormalParameterSequence fps = null;
      if (binding instanceof ProcFormalParameter) {
        fps = ((ProcFormalParameter)binding).FPS;
      } else {
        fps = ((ProcDeclaration)binding).FPS;
      }

      final ProcFormalParameter pfp = (ProcFormalParameter)fp;

      if (!fps.equals(pfp.FPS)) {
        throw new CheckerError(reportError(param.position, 
              "mismatch in procedure type signature. expected",
              pfp.FPS,
              "got",
              fps));
      }
    }

    return null;
  }

  @Override
  public Object visit(final FuncActualParameter param, Object arg) {
    final FormalParameter fp = (FormalParameter)arg;
    final Declaration binding = (Declaration)param.I.accept(this, null);

    if (binding == null) {
      throw new CheckerError(reportError(param.position, param.I, "is not declared"));
    }

    if (!((binding instanceof FuncFormalParameter) || (binding instanceof FuncDeclaration))) {
      throw new CheckerError(reportError(param.position, param.I, "is not a valid function identifier"));
    } 

    if (!(fp instanceof FuncFormalParameter)) {
      throw new CheckerError(reportError(param.position, "func actual parameter was not expected here"));
    } else {
      FormalParameterSequence fps = null;
      TypeDenoter td = null;
      if (binding instanceof FuncFormalParameter) {
        fps = ((FuncFormalParameter)binding).FPS;
        td = ((FuncFormalParameter)binding).T;
      } else {
        fps = ((FuncDeclaration)binding).FPS;
        td = ((FuncDeclaration)binding).T;
      }

      final FuncFormalParameter ffp = (FuncFormalParameter)fp;

      if (!fps.equals(ffp.FPS) || !(td.equals(ffp.T))) {
        throw new CheckerError(reportError(param.position,
              "mismatch in function type signature.expected",
              ffp.FPS,
              "arguments, and return type",
              ffp.T,
              "got",
              fps,
              "with return type",
              td));
      }
    }

    return null;
  }

  @Override
  public Object visit(final SimpleVname vname, Object arg) {
    final Declaration binding = (Declaration)vname.I.accept(this, null);

    if (binding instanceof VarDeclaration) {
      vname.variable = true;
      vname.type = ((VarDeclaration)binding).T;
    } else if (binding instanceof ConstDeclaration) {
      vname.variable = false;
      vname.type = ((ConstDeclaration)binding).E.type;
    } else if (binding instanceof VarFormalParameter) {
      vname.variable = true;
      vname.type = ((VarFormalParameter)binding).T;
    } else if (binding instanceof ConstFormalParameter) {
      vname.variable = false;
      vname.type = ((ConstFormalParameter)binding).T;
    } else {
      throw new CheckerError(reportError(vname.position, vname.I.spelling, "is not a var or const identifier"));
    }

    return vname.type;
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
    final Declaration binding = (Declaration)this.idTable.retrieve(id.spelling);
    id.decl = binding;

    return id.decl;
  }

  @Override
  public Object visit(final IntegerLiteral il, Object arg) {
    return StdEnvironment.intType;
  }

  @Override
  public Object visit(final CharacterLiteral cl, Object arg) {
    return StdEnvironment.charType;
  }

  @Override
  public Object visit(final Operator op, Object arg) {
    final Declaration binding = (Declaration)this.idTable.retrieve(op.spelling);
    op.decl = binding;

    return op.decl;
  }
}
