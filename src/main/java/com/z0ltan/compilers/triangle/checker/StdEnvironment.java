package com.z0ltan.compilers.triangle.checker;

import com.z0ltan.compilers.triangle.ast.TypeDenoter;
import com.z0ltan.compilers.triangle.ast.TypeDeclaration;
import com.z0ltan.compilers.triangle.ast.ConstDeclaration;
import com.z0ltan.compilers.triangle.ast.UnaryOperatorDeclaration;
import com.z0ltan.compilers.triangle.ast.BinaryOperatorDeclaration;
import com.z0ltan.compilers.triangle.ast.ProcDeclaration;
import com.z0ltan.compilers.triangle.ast.FuncDeclaration;

import static com.z0ltan.compilers.triangle.scanner.SourcePosition.dummyPosition;

public class StdEnvironment {
  public static TypeDenoter anyType;
  public static TypeDenoter errorType;
  public static TypeDenoter intType;
  public static TypeDenoter charType;
  public static TypeDenoter boolType;

  public static TypeDeclaration intDecl;
  public static TypeDeclaration charDecl;
  public static TypeDeclaration boolDecl;

  public static ConstDeclaration falseDecl;
  public static ConstDeclaration trueDecl;

  public static UnaryOperatorDeclaration notDecl;
  public static BinaryOperatorDeclaration andDecl;
  public static BinaryOperatorDeclaration orDecl;
  public static BinaryOperatorDeclaration addDecl;
  public static BinaryOperatorDeclaration subDecl;
  public static BinaryOperatorDeclaration multDecl;
  public static BinaryOperatorDeclaration divDecl;
  public static BinaryOperatorDeclaration modDecl;
  public static BinaryOperatorDeclaration eqDecl;
  public static BinaryOperatorDeclaration noteqDecl;
  public static BinaryOperatorDeclaration lessThanDecl;
  public static BinaryOperatorDeclaration lessThanOrEqualToDecl;
  public static BinaryOperatorDeclaration greaterThanDecl;
  public static BinaryOperatorDeclaration greaterThanOrEqualToDecl;

  public static ProcDeclaration getDecl;
  public static ProcDeclaration getintDecl;
  public static ProcDeclaration geteolDecl;
  public static ProcDeclaration putDecl;
  public static ProcDeclaration putintDecl;
  public static ProcDeclaration puteolDecl;

  public static FuncDeclaration chrDecl;
  public static FuncDeclaration ordDecl;
  public static FuncDeclaration eolDecl;
  public static FuncDeclaration eofDecl;
}
