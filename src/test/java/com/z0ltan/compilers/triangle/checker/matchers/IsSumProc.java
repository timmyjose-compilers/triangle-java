package com.z0ltan.compilers.triangle.checker.matchers;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.Description;

import com.z0ltan.compilers.triangle.ast.Program;
import com.z0ltan.compilers.triangle.ast.Declaration;
import com.z0ltan.compilers.triangle.ast.ProcDeclaration;
import com.z0ltan.compilers.triangle.ast.VnameExpression;
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

public class IsSumProc extends TypeSafeMatcher<Program> {
  @Override
  public boolean matchesSafely(final Program program) {
    final Declaration xDecl = (Declaration)travel(program, "C.D.D1.D1.D1.I.decl");

    final TypeDenoter intDecl1 = (TypeDenoter)travel(program, "C.D.D1.D1.D1.T");
    assertThat(intDecl1, is(equalTo(StdEnvironment.intType)));

    final Declaration yDecl = (Declaration)travel(program, "C.D.D1.D1.D2.I.decl");

    final TypeDenoter intDecl2 = (TypeDenoter)travel(program, "C.D.D1.D1.D2.T");
    assertThat(intDecl2, is(equalTo(StdEnvironment.intType)));
 
    final Declaration sDecl = (Declaration)travel(program, "C.D.D1.D2.I.decl");

    final TypeDenoter intDecl3 = (TypeDenoter)travel(program, "C.D.D1.D2.T");
    assertThat(intDecl3, is(equalTo(StdEnvironment.intType)));

    final ProcDeclaration addDecl = (ProcDeclaration)travel(program, "C.D.D2.I.decl");

    final ConstFormalParameter aParamDecl = (ConstFormalParameter)travel(program, "C.D.D2.FPS.FP.I.decl");

    final TypeDenoter intDecl4 = (TypeDenoter)travel(program, "C.D.D2.FPS.FP.T");
    assertThat(intDecl4, is(equalTo(StdEnvironment.intType)));

    final ConstFormalParameter bParamDecl = (ConstFormalParameter)travel(program, "C.D.D2.FPS.FPS.FP.I.decl");

    final TypeDenoter intDecl5 = (TypeDenoter)travel(program, "C.D.D2.FPS.FPS.FP.T");
    assertThat(intDecl5, is(equalTo(StdEnvironment.intType)));

    final VarFormalParameter rParamDecl = (VarFormalParameter)travel(program, "C.D.D2.FPS.FPS.FPS.FP.I.decl");

    final TypeDenoter intDecl6 = (TypeDenoter)travel(program, "C.D.D2.FPS.FPS.FPS.FP.T");
    assertThat(intDecl6, is(equalTo(StdEnvironment.intType)));

    final VarFormalParameter rParamAppliedDecl = (VarFormalParameter)travel(program, "C.D.D2.C.V.I.decl");
    assertThat(rParamAppliedDecl, is(equalTo(rParamDecl)));

    final BinaryExpression binexpr1 = (BinaryExpression)travel(program, "C.D.D2.C.E");
    assertThat(binexpr1.type, is(equalTo(StdEnvironment.intType)));

    final VnameExpression vnameexpr1 = (VnameExpression)travel(program, "C.D.D2.C.E.E1");
    assertThat(vnameexpr1.type, is(equalTo(StdEnvironment.intType)));

    final ConstFormalParameter aParamAppliedDecl = (ConstFormalParameter)travel(program, "C.D.D2.C.E.E1.V.I.decl");
    assertThat(aParamAppliedDecl, is(equalTo(aParamDecl)));

    final BinaryOperatorDeclaration addAppliedDecl1 = (BinaryOperatorDeclaration)travel(program, "C.D.D2.C.E.O.decl");
    assertThat(addAppliedDecl1, is(equalTo(StdEnvironment.addDecl)));

    final VnameExpression vnameexpr2 = (VnameExpression)travel(program, "C.D.D2.C.E.E2");
    assertThat(vnameexpr2.type, is(equalTo(StdEnvironment.intType)));

    final ConstFormalParameter bParamAppliedDecl = (ConstFormalParameter)travel(program, "C.D.D2.C.E.E2.V.I.decl");
    assertThat(bParamAppliedDecl, is(equalTo(bParamDecl)));

    final ProcDeclaration getintDecl1 = (ProcDeclaration)travel(program, "C.C.C1.C1.C1.I.decl");
    assertThat(getintDecl1, is(equalTo(StdEnvironment.getintDecl)));

    final Declaration xAppliedDecl1 = (Declaration)travel(program, "C.C.C1.C1.C1.APS.AP.V.I.decl");
    assertThat(xAppliedDecl1, is(equalTo(xDecl)));

    final ProcDeclaration getintDecl2 = (ProcDeclaration)travel(program, "C.C.C1.C1.C2.I.decl");
    assertThat(getintDecl2, is(equalTo(StdEnvironment.getintDecl)));

    final Declaration yAppliedDecl1 = (Declaration)travel(program, "C.C.C1.C1.C2.APS.AP.V.I.decl");
    assertThat(yAppliedDecl1, is(equalTo(yDecl)));

    final ProcDeclaration addAppliedDecl2 = (ProcDeclaration)travel(program, "C.C.C1.C2.I.decl");
    assertThat(addAppliedDecl2, is(equalTo(addDecl)));

    final Declaration xAppliedDecl2 = (Declaration)travel(program, "C.C.C1.C2.APS.AP.E.V.I.decl");
    assertThat(xAppliedDecl2, is(equalTo(xDecl)));

    final VnameExpression vnameexpr4 = (VnameExpression)travel(program, "C.C.C1.C2.APS.APS.AP.E");
    assertThat(vnameexpr4.type, is(equalTo(StdEnvironment.intType)));

    final Declaration yAppliedDecl2 = (Declaration)travel(program, "C.C.C1.C2.APS.APS.AP.E.V.I.decl");
    assertThat(yAppliedDecl2, is(equalTo(yDecl)));

    final Declaration sAppliedDecl1 = (Declaration)travel(program, "C.C.C1.C2.APS.APS.APS.AP.V.I.decl");
    assertThat(sAppliedDecl1, is(equalTo(sDecl)));

    final ProcDeclaration putintDecl1 = (ProcDeclaration)travel(program, "C.C.C2.I.decl");
    assertThat(putintDecl1, is(equalTo(StdEnvironment.putintDecl)));

    final VnameExpression vnameexpr5 = (VnameExpression)travel(program, "C.C.C2.APS.AP.E");
    assertThat(vnameexpr5.type, is(equalTo(StdEnvironment.intType)));

    final Declaration sAppliedDecl2 = (Declaration)travel(program, "C.C.C2.APS.AP.E.V.I.decl");
    assertThat(sAppliedDecl2, is(equalTo(sDecl)));

    return true;
  }

  @Override
  public void describeTo(final Description description) {
    description.appendText("an ast for testcase sumproc");
  }

  public static Matcher sumProc() {
    return new IsSumProc();
  }
}





