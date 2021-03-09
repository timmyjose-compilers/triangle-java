package com.z0ltan.compilers.triangle.ast;

public interface Visitor {
  Object visit(Program prog, Object arg);

  Object visit(EmptyCommand cmd, Object arg);
  Object visit(AssignCommand cmd, Object arg);
  Object visit(CallCommand cmd, Object arg);
  Object visit(LetCommand cmd, Object arg);
  Object visit(IfCommand cmd, Object arg);
  Object visit(WhileCommand cmd, Object arg);
  Object visit(SequentialCommand cmd, Object arg);

  Object visit(EmptyExpression expr, Object arg);
  Object visit(IntegerExpression expr, Object arg);
  Object visit(CharacterExpression expr, Object arg);
  Object visit(VnameExpression expr, Object arg);
  Object visit(LetExpression expr, Object arg);
  Object visit(CallExpression expr, Object arg);
  Object visit(IfExpression expr, Object arg);
  Object visit(UnaryExpression expr, Object arg);
  Object visit(BinaryExpression expr, Object arg);
  Object visit(ArrayExpression expr, Object arg);
  Object visit(RecordExpression expr, Object arg);

  Object visit(SingleArrayAggregate agg, Object arg);
  Object visit(MultipleArrayAggregate agg, Object arg);
  Object visit(SingleRecordAggregate agg, Object arg);
  Object visit(MultipleRecordAggregate agg, Object arg);

  Object visit(ConstDeclaration decl, Object arg);
  Object visit(VarDeclaration decl, Object arg);
  Object visit(ProcDeclaration decl, Object arg);
  Object visit(FuncDeclaration decl, Object arg);
  Object visit(TypeDeclaration decl, Object arg);
  Object visit(UnaryOperatorDeclaration decl, Object arg);
  Object visit(BinaryOperatorDeclaration decl, Object arg);
  Object visit(SequentialDeclaration decl, Object arg);

  Object visit(AnyTypeDenoter td, Object arg);
  Object visit(ErrorTypeDenoter td, Object arg);
  Object visit(BoolTypeDenoter td, Object arg);
  Object visit(CharTypeDenoter td, Object arg);
  Object visit(IntTypeDenoter td, Object arg);
  Object visit(ArrayTypeDenoter td, Object arg);
  Object visit(SimpleTypeDenoter td, Object arg);
  Object visit(SingleFieldTypeDenoter td, Object arg);
  Object visit(MultipleFieldTypeDenoter td, Object arg);
  Object visit(RecordTypeDenoter td, Object arg);

  Object visit(EmptyFormalParameterSequence fps, Object arg);
  Object visit(SingleFormalParameterSequence fps, Object args);
  Object visit(MultipleFormalParameterSequence fps, Object arg);

  Object visit(ConstFormalParameter param, Object arg);
  Object visit(VarFormalParameter param, Object arg);
  Object visit(ProcFormalParameter param, Object arg);
  Object visit(FuncFormalParameter param, Object arg);

  Object visit(EmptyActualParameterSequence aps, Object arg);
  Object visit(SingleActualParameterSequence aps, Object arg);
  Object visit(MultipleActualParameterSequence aps, Object arg);

  Object visit(ConstActualParameter param, Object arg);
  Object visit(VarActualParameter param, Object arg);
  Object visit(ProcActualParameter param, Object arg);
  Object visit(FuncActualParameter param, Object arg);

  Object visit(SimpleVname vname, Object arg);
  Object visit(DotVname vname, Object arg);
  Object visit(SubscriptVname vname, Object arg);

  Object visit(Identifier id, Object arg);
  Object visit(IntegerLiteral il, Object arg);
  Object visit(CharacterLiteral cl, Object arg);
  Object visit(Operator op, Object arg);
}
