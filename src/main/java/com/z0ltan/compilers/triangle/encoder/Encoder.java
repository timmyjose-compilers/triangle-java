package com.z0ltan.compilers.triangle.encoder;

import com.z0ltan.compilers.triangle.ast.Visitor;
import com.z0ltan.compilers.triangle.ast.Program;
import com.z0ltan.compilers.triangle.ast.EmptyCommand;
import com.z0ltan.compilers.triangle.ast.AssignCommand;
import com.z0ltan.compilers.triangle.ast.CallCommand;
import com.z0ltan.compilers.triangle.ast.IfCommand;
import com.z0ltan.compilers.triangle.ast.WhileCommand;
import com.z0ltan.compilers.triangle.ast.LetCommand;
import com.z0ltan.compilers.triangle.ast.SequentialCommand;
import com.z0ltan.compilers.triangle.ast.EmptyExpression;
import com.z0ltan.compilers.triangle.ast.CallExpression;
import com.z0ltan.compilers.triangle.ast.IfExpression;
import com.z0ltan.compilers.triangle.ast.LetExpression;
import com.z0ltan.compilers.triangle.ast.VnameExpression;
import com.z0ltan.compilers.triangle.ast.IntegerExpression;
import com.z0ltan.compilers.triangle.ast.CharacterExpression;
import com.z0ltan.compilers.triangle.ast.UnaryExpression;
import com.z0ltan.compilers.triangle.ast.ArrayExpression;
import com.z0ltan.compilers.triangle.ast.RecordExpression;
import com.z0ltan.compilers.triangle.ast.BinaryExpression;
import com.z0ltan.compilers.triangle.ast.VarDeclaration;
import com.z0ltan.compilers.triangle.ast.ConstDeclaration;
import com.z0ltan.compilers.triangle.ast.ProcDeclaration;
import com.z0ltan.compilers.triangle.ast.FuncDeclaration;
import com.z0ltan.compilers.triangle.ast.UnaryOperatorDeclaration;
import com.z0ltan.compilers.triangle.ast.BinaryOperatorDeclaration;
import com.z0ltan.compilers.triangle.ast.TypeDeclaration;
import com.z0ltan.compilers.triangle.ast.SequentialDeclaration;
import com.z0ltan.compilers.triangle.ast.SimpleTypeDenoter;
import com.z0ltan.compilers.triangle.ast.IntTypeDenoter;
import com.z0ltan.compilers.triangle.ast.CharTypeDenoter;
import com.z0ltan.compilers.triangle.ast.BoolTypeDenoter;
import com.z0ltan.compilers.triangle.ast.ErrorTypeDenoter;
import com.z0ltan.compilers.triangle.ast.AnyTypeDenoter;
import com.z0ltan.compilers.triangle.ast.ArrayTypeDenoter;
import com.z0ltan.compilers.triangle.ast.RecordTypeDenoter;
import com.z0ltan.compilers.triangle.ast.SingleFieldTypeDenoter;
import com.z0ltan.compilers.triangle.ast.MultipleFieldTypeDenoter;
import com.z0ltan.compilers.triangle.ast.VarFormalParameter;
import com.z0ltan.compilers.triangle.ast.ConstFormalParameter;
import com.z0ltan.compilers.triangle.ast.ProcFormalParameter;
import com.z0ltan.compilers.triangle.ast.FuncFormalParameter;
import com.z0ltan.compilers.triangle.ast.EmptyFormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.SingleFormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.MultipleFormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.VarActualParameter;
import com.z0ltan.compilers.triangle.ast.ConstActualParameter;
import com.z0ltan.compilers.triangle.ast.ProcActualParameter;
import com.z0ltan.compilers.triangle.ast.FuncActualParameter;
import com.z0ltan.compilers.triangle.ast.EmptyActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.SingleActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.MultipleActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.SimpleVname;
import com.z0ltan.compilers.triangle.ast.DotVname;
import com.z0ltan.compilers.triangle.ast.SubscriptVname;
import com.z0ltan.compilers.triangle.ast.SingleRecordAggregate;
import com.z0ltan.compilers.triangle.ast.MultipleRecordAggregate;
import com.z0ltan.compilers.triangle.ast.SingleArrayAggregate;
import com.z0ltan.compilers.triangle.ast.MultipleArrayAggregate;
import com.z0ltan.compilers.triangle.ast.Identifier;
import com.z0ltan.compilers.triangle.ast.IntegerLiteral;
import com.z0ltan.compilers.triangle.ast.CharacterLiteral;
import com.z0ltan.compilers.triangle.ast.Operator;

public class Encoder implements Visitor {
  @Override
  public Object visit(Program prog, Object arg)  {
    return null;
  }

  @Override
  public Object visit(EmptyCommand cmd, Object arg) {
    return null;
  }

  @Override
  public Object visit(AssignCommand cmd, Object arg) {
    return null;
  }

  @Override
  public Object visit(CallCommand cmd, Object arg) {
    return null;
  }

  @Override
  public Object visit(LetCommand cmd, Object arg) {
    return null;
  }

  @Override
  public Object visit(IfCommand cmd, Object arg) {
    return null;
  }

  @Override
  public Object visit(WhileCommand cmd, Object arg) {
    return null;
  }

  @Override
  public Object visit(SequentialCommand cmd, Object arg) {
    return null;
  }

  @Override
  public Object visit(EmptyExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(IntegerExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(CharacterExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(VnameExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(LetExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(CallExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(IfExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(UnaryExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(BinaryExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(ArrayExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(RecordExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(SingleArrayAggregate agg, Object arg) {
    return null;
  }

  @Override
  public Object visit(MultipleArrayAggregate agg, Object arg) {
    return null;
  }

  @Override
  public Object visit(SingleRecordAggregate agg, Object arg) {
    return null;
  }

  @Override
  public Object visit(MultipleRecordAggregate agg, Object arg) {
    return null;
  }

  @Override
  public Object visit(ConstDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(VarDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(ProcDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(FuncDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(TypeDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(UnaryOperatorDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(BinaryOperatorDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(SequentialDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(AnyTypeDenoter td, Object arg) {
    return null;
  }

  @Override
  public Object visit(ErrorTypeDenoter td, Object arg) {
    return null;
  }

  @Override
  public Object visit(BoolTypeDenoter td, Object arg) {
    return null;
  }

  @Override
  public Object visit(CharTypeDenoter td, Object arg) {
    return null;
  }

  @Override
  public Object visit(IntTypeDenoter td, Object arg) {
    return null;
  }

  @Override
  public Object visit(ArrayTypeDenoter td, Object arg) {
    return null;
  }

  @Override
  public Object visit(SimpleTypeDenoter td, Object arg) {
    return null;
  }

  @Override
  public Object visit(SingleFieldTypeDenoter td, Object arg) {
    return null;
  }

  @Override
  public Object visit(MultipleFieldTypeDenoter td, Object arg) {
    return null;
  }

  @Override
  public Object visit(RecordTypeDenoter td, Object arg) {
    return null;
  }

  @Override
  public Object visit(EmptyFormalParameterSequence fps, Object arg) {
    return null;
  }

  @Override
  public Object visit(SingleFormalParameterSequence fps, Object args) {
    return null;
  }

  @Override
  public Object visit(MultipleFormalParameterSequence fps, Object arg) {
    return null;
  }

  @Override
  public Object visit(ConstFormalParameter param, Object arg) {
    return null;
  }

  @Override
  public Object visit(VarFormalParameter param, Object arg) {
    return null;
  }

  @Override
  public Object visit(ProcFormalParameter param, Object arg) {
    return null;
  }

  @Override
  public Object visit(FuncFormalParameter param, Object arg) {
    return null;
  }

  @Override
  public Object visit(EmptyActualParameterSequence aps, Object arg) {
    return null;
  }

  @Override
  public Object visit(SingleActualParameterSequence aps, Object arg) {
    return null;
  }

  @Override
  public Object visit(MultipleActualParameterSequence aps, Object arg) {
    return null;
  }

  @Override
  public Object visit(ConstActualParameter param, Object arg) {
    return null;
  }

  @Override
  public Object visit(VarActualParameter param, Object arg) {
    return null;
  }

  @Override
  public Object visit(ProcActualParameter param, Object arg) {
    return null;
  }

  @Override
  public Object visit(FuncActualParameter param, Object arg) {
    return null;
  }

  @Override
  public Object visit(SimpleVname vname, Object arg) {
    return null;
  }

  @Override
  public Object visit(DotVname vname, Object arg) {
    return null;
  }

  @Override
  public Object visit(SubscriptVname vname, Object arg) {
    return null;
  }

  @Override
  public Object visit(Identifier id, Object arg) {
    return null;
  }

  @Override
  public Object visit(IntegerLiteral il, Object arg) {
    return null;
  }

  @Override
  public Object visit(CharacterLiteral cl, Object arg) {
    return null;
  }

  @Override
  public Object visit(Operator op, Object arg) {
    return null;
  }
}
