package com.z0ltan.compilers.triangle.encoder;

public class Encoder implements Visitor {
  @Override
  public public Object visit(Program prog, Object arg)  {
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
  Object visit(SingleRecordAggregate agg, Object arg) {
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

  }
  @Override
  public Object visit(ErrorTypeDenoter td, Object arg) {

  }
  @Override
  public Object visit(BoolTypeDenoter td, Object arg) {

  }
  @Override
  public Object visit(CharTypeDenoter td, Object arg) {

  }
  @Override
  public Object visit(IntTypeDenoter td, Object arg) {

  }
  @Override
  public Object visit(ArrayTypeDenoter td, Object arg) {

  }
  @Override
  public Object visit(SimpleTypeDenoter td, Object arg) {

  }
  @Override
  public Object visit(SingleFieldTypeDenoter td, Object arg) {

  }
  @Override
  public Object visit(MultipleFieldTypeDenoter td, Object arg) {

  }
  @Override
  public Object visit(RecordTypeDenoter td, Object arg) {

  }

  @Override
  public Object visit(EmptyFormalParameterSequence fps, Object arg) {

  }
  Object visit(SingleFormalParameterSequence fps, Object args);
  @Override
  public Object visit(MultipleFormalParameterSequence fps, Object arg) {

  }

  @Override
  public Object visit(ConstFormalParameter param, Object arg) {

  }
  @Override
  public Object visit(VarFormalParameter param, Object arg) {

  }
  @Override
  public Object visit(ProcFormalParameter param, Object arg) {

  }
  @Override
  public Object visit(FuncFormalParameter param, Object arg) {

  }

  @Override
  public Object visit(EmptyActualParameterSequence aps, Object arg) {

  }
  @Override
  public Object visit(SingleActualParameterSequence aps, Object arg) {

  }
  @Override
  public Object visit(MultipleActualParameterSequence aps, Object arg) {

  }

  @Override
  public Object visit(ConstActualParameter param, Object arg) {

  }
  @Override
  public Object visit(VarActualParameter param, Object arg) {

  }
  Object visit(ProcActualParameter param, Object arg);
  @Override
  public Object visit(FuncActualParameter param, Object arg) {

  }

  @Override
  public Object visit(SimpleVname vname, Object arg) {

  }
  @Override
  public Object visit(DotVname vname, Object arg) {

  }
  @Override
  public Object visit(SubscriptVname vname, Object arg) {

  }

  @Override
  public Object visit(Identifier id, Object arg) {
    return null;
  }

  @Override
  Object visit(IntegerLiteral il, Object arg) {
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
