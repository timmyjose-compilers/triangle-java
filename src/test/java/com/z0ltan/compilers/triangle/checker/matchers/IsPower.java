package com.z0ltan.compilers.triangle.checker.matchers;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.Description;

import com.z0ltan.compilers.triangle.ast.Program;
import com.z0ltan.compilers.triangle.ast.Declaration;
import com.z0ltan.compilers.triangle.ast.ProcDeclaration;
import com.z0ltan.compilers.triangle.ast.FuncDeclaration;
import com.z0ltan.compilers.triangle.ast.IfExpression;
import com.z0ltan.compilers.triangle.ast.CallExpression;
import com.z0ltan.compilers.triangle.ast.VnameExpression;
import com.z0ltan.compilers.triangle.ast.IntegerExpression;
import com.z0ltan.compilers.triangle.ast.BinaryExpression;
import com.z0ltan.compilers.triangle.ast.BinaryOperatorDeclaration;
import com.z0ltan.compilers.triangle.ast.TypeDenoter;
import com.z0ltan.compilers.triangle.ast.VarFormalParameter;
import com.z0ltan.compilers.triangle.ast.ConstFormalParameter;
import com.z0ltan.compilers.triangle.checker.StdEnvironment;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import static com.z0ltan.compilers.triangle.scanner.SourcePosition.dummyPosition;
import static com.z0ltan.compilers.triangle.helper.traveller.Traveller.travel;

public class IsPower extends TypeSafeMatcher<Program> {
  @Override
  public boolean matchesSafely(final Program program) {
    final Declaration mDecl = (Declaration)travel(program, "C.D.D1.D1.I.decl");

    final TypeDenoter mTypeDenoter = (TypeDenoter)travel(program, "C.D.D1.D1.T");
    assertThat(mTypeDenoter, is(equalTo(StdEnvironment.intType)));

    final Declaration nDecl = (Declaration)travel(program, "C.D.D1.D2.I.decl");

    final TypeDenoter nTypeDenoter = (TypeDenoter)travel(program, "C.D.D1.D2.T");
    assertThat(nTypeDenoter, is(equalTo(StdEnvironment.intType)));

    final FuncDeclaration powerDecl = (FuncDeclaration)travel(program, "C.D.D2.I.decl");

    final ConstFormalParameter aParamDecl = (ConstFormalParameter)travel(program, "C.D.D2.FPS.FP.I.decl");

    final TypeDenoter aParamTypeDenoter = (TypeDenoter)travel(program, "C.D.D2.FPS.FP.T");
    assertThat(aParamTypeDenoter, is(equalTo(StdEnvironment.intType)));

    final ConstFormalParameter bParamDecl = (ConstFormalParameter)travel(program, "C.D.D2.FPS.FPS.FP.I.decl");

    final TypeDenoter bParamTypeDenoter = (TypeDenoter)travel(program, "C.D.D2.FPS.FPS.FP.T");
    assertThat(bParamTypeDenoter, is(equalTo(StdEnvironment.intType)));

    final TypeDenoter powerTypeDenoter = (TypeDenoter)travel(program, "C.D.D2.T");
    assertThat(powerTypeDenoter, is(equalTo(StdEnvironment.intType)));

    final IfExpression ifexpr1 = (IfExpression)travel(program, "C.D.D2.E");
    assertThat(ifexpr1.type, is(equalTo(StdEnvironment.intType)));

    final BinaryExpression binexpr1 = (BinaryExpression)travel(program, "C.D.D2.E.E1");
    assertThat(binexpr1.type, is(equalTo(StdEnvironment.boolType)));

    final VnameExpression vnameexpr1 = (VnameExpression)travel(program, "C.D.D2.E.E1.E1");
    assertThat(vnameexpr1.type, is(equalTo(StdEnvironment.intType)));

    final ConstFormalParameter bParamAppliedDecl1 = (ConstFormalParameter)travel(program, "C.D.D2.E.E1.E1.V.I.decl");
    assertThat(bParamAppliedDecl1, is(equalTo(bParamDecl)));

    final BinaryOperatorDeclaration eqDecl1 = (BinaryOperatorDeclaration)travel(program, "C.D.D2.E.E1.O.decl");
    assertThat(eqDecl1, is(equalTo(StdEnvironment.eqDecl)));

    final IntegerExpression iexpr1 = (IntegerExpression)travel(program, "C.D.D2.E.E1.E2");
    assertThat(iexpr1.type, is(equalTo(StdEnvironment.intType)));

    final IntegerExpression iexpr2 = (IntegerExpression)travel(program, "C.D.D2.E.E2");
    assertThat(iexpr2.type, is(equalTo(StdEnvironment.intType)));

    final BinaryExpression binexpr2 = (BinaryExpression)travel(program, "C.D.D2.E.E3");
    assertThat(binexpr2.type, is(equalTo(StdEnvironment.intType)));

    final VnameExpression vnameexpr2 = (VnameExpression)travel(program, "C.D.D2.E.E3.E1");
    assertThat(vnameexpr2.type, is(equalTo(StdEnvironment.intType)));

    final ConstFormalParameter aParamAppliedDecl1 = (ConstFormalParameter)travel(program, "C.D.D2.E.E3.E1.V.I.decl");
    assertThat(aParamAppliedDecl1, is(equalTo(aParamDecl)));

    final BinaryOperatorDeclaration multDecl1 = (BinaryOperatorDeclaration)travel(program, "C.D.D2.E.E3.O.decl");
    assertThat(multDecl1, is(equalTo(StdEnvironment.multDecl)));

    final CallExpression callexpr1 = (CallExpression)travel(program, "C.D.D2.E.E3.E2");
    assertThat(callexpr1.type, is(equalTo(StdEnvironment.intType)));

    final FuncDeclaration powerAppiedDecl1 = (FuncDeclaration)travel(program, "C.D.D2.E.E3.E2.I.decl");
    assertThat(powerAppiedDecl1, is(equalTo(powerDecl)));

    final ConstFormalParameter aParamAppliedDecl2 = (ConstFormalParameter)travel(program, "C.D.D2.E.E3.E2.APS.AP.E.V.I.decl");
    assertThat(aParamAppliedDecl2, is(equalTo(aParamDecl)));

    final BinaryExpression binexpr3 = (BinaryExpression)travel(program, "C.D.D2.E.E3.E2.APS.APS.AP.E");
    assertThat(binexpr3.type, is(equalTo(StdEnvironment.intType)));

    final VnameExpression vnameexpr3 = (VnameExpression)travel(program, "C.D.D2.E.E3.E2.APS.APS.AP.E.E1");
    assertThat(vnameexpr3.type, is(equalTo(StdEnvironment.intType)));

    final ConstFormalParameter bParamAppliedDecl2 = (ConstFormalParameter)travel(program, "C.D.D2.E.E3.E2.APS.APS.AP.E.E1.V.I.decl");
    assertThat(bParamAppliedDecl2, is(equalTo(bParamDecl)));

    final BinaryOperatorDeclaration subDecl = (BinaryOperatorDeclaration)travel(program, "C.D.D2.E.E3.E2.APS.APS.AP.E.O.decl");
    assertThat(subDecl, is(equalTo(StdEnvironment.subDecl)));

    final IntegerExpression iexpr3 = (IntegerExpression)travel(program, "C.D.D2.E.E3.E2.APS.APS.AP.E.E2");
    assertThat(iexpr3.type, is(equalTo(StdEnvironment.intType)));

    final ProcDeclaration getintDecl1 = (ProcDeclaration)travel(program, "C.C.C1.C1.I.decl");
    assertThat(getintDecl1, is(equalTo(StdEnvironment.getintDecl)));

    final Declaration mAppliedDecl1 = (Declaration)travel(program, "C.C.C1.C1.APS.AP.V.I.decl");
    assertThat(mAppliedDecl1, is(equalTo(mDecl)));

    final ProcDeclaration getintDecl2 = (ProcDeclaration)travel(program, "C.C.C1.C2.I.decl");
    assertThat(getintDecl2, is(equalTo(StdEnvironment.getintDecl)));

    final Declaration nAppliedDecl1 = (Declaration)travel(program, "C.C.C1.C2.APS.AP.V.I.decl");
    assertThat(nAppliedDecl1, is(equalTo(nDecl)));

    final ProcDeclaration putintDecl1 = (ProcDeclaration)travel(program, "C.C.C2.I.decl");
    assertThat(putintDecl1, is(equalTo(StdEnvironment.putintDecl)));

    final FuncDeclaration powerAppliedDecl1 = (FuncDeclaration)travel(program, "C.C.C2.APS.AP.E.I.decl");
    assertThat(powerAppliedDecl1, is(equalTo(powerDecl)));

    final Declaration mAppliedDecl2 = (Declaration)travel(program, "C.C.C2.APS.AP.E.APS.AP.E.V.I.decl");
    assertThat(mAppliedDecl2, is(equalTo(mDecl)));

    final VnameExpression vnameexpr4 = (VnameExpression)travel(program, "C.C.C2.APS.AP.E.APS.AP.E");
    assertThat(vnameexpr4.type, is(equalTo(StdEnvironment.intType)));

    final Declaration nAppliedDecl2 = (Declaration)travel(program, "C.C.C2.APS.AP.E.APS.APS.AP.E.V.I.decl");
    assertThat(nAppliedDecl2, is(equalTo(nDecl)));

    return true;
  }

  @Override
  public void describeTo(final Description description) {
    description.appendText("is an ast for testcase power");
  }

  public static Matcher power() {
    return new IsPower();
  }
}
