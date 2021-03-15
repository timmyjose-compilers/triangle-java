package com.z0ltan.compilers.triangle.checker.matchers;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.Description;

import com.z0ltan.compilers.triangle.ast.Program;
import com.z0ltan.compilers.triangle.ast.Declaration;
import com.z0ltan.compilers.triangle.ast.ProcDeclaration;
import com.z0ltan.compilers.triangle.ast.CallExpression;
import com.z0ltan.compilers.triangle.ast.UnaryExpression;
import com.z0ltan.compilers.triangle.ast.VnameExpression;
import com.z0ltan.compilers.triangle.ast.VarFormalParameter;
import com.z0ltan.compilers.triangle.ast.Vname;
import com.z0ltan.compilers.triangle.checker.StdEnvironment;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import static com.z0ltan.compilers.triangle.scanner.SourcePosition.dummyPosition;
import static com.z0ltan.compilers.triangle.helper.traveller.Traveller.travel;

public class IsEcho extends TypeSafeMatcher<Program> {
  @Override
  public boolean matchesSafely(final Program program) {
    final Declaration chDecl = (Declaration)travel(program, "C.D.D1.I.decl");
    final Declaration echoDecl = (Declaration)travel(program, "C.D.D2");

    final Declaration notOpDecl1 = (Declaration)travel(program, "C.D.D2.C.E.O.decl");
    assertThat(notOpDecl1, is(equalTo(StdEnvironment.notDecl)));

    final UnaryExpression unexpr1 = (UnaryExpression)travel(program, "C.D.D2.C.E");
    assertThat(unexpr1.type, is(equalTo(StdEnvironment.boolType)));

    final CallExpression callexpr1 = (CallExpression)travel(program, "C.D.D2.C.E.E");
    assertThat(callexpr1.type, is(equalTo(StdEnvironment.boolType)));

    final Declaration eolDecl1 = (Declaration)travel(program, "C.D.D2.C.E.E.I.decl");
    assertThat(eolDecl1, is(equalTo(StdEnvironment.eolDecl)));

    final Declaration getDecl1 = (Declaration)travel(program, "C.D.D2.C.C.C1.I.decl");
    assertThat(getDecl1, is(equalTo(StdEnvironment.getDecl)));

    final Declaration chAppliedDecl1 = (Declaration)travel(program, "C.D.D2.C.C.C1.APS.AP.V.I.decl");
    assertThat(chAppliedDecl1, is(equalTo(chDecl)));

    final Vname chVname = (Vname)travel(program, "C.D.D2.C.C.C1.APS.AP.V");
    assertThat(chVname.type, is(equalTo(StdEnvironment.charType)));

    final Declaration putDecl1 = (Declaration)travel(program, "C.D.D2.C.C.C2.I.decl");
    assertThat(putDecl1, is(equalTo(StdEnvironment.putDecl)));

    final Declaration chAppliedDecl2 = (Declaration)travel(program, "C.D.D2.C.C.C2.APS.AP.E.V.I.decl");
    assertThat(chAppliedDecl2, is(equalTo(chDecl)));

    final VnameExpression vnamexpr1 = (VnameExpression)travel(program, "C.D.D2.C.C.C2.APS.AP.E");
    assertThat(vnamexpr1.type, is(equalTo(StdEnvironment.charType)));

    final Declaration echoAppliedDecl1 = (Declaration)travel(program, "C.C.I.decl");
    assertThat(echoAppliedDecl1, is(equalTo(echoDecl)));

    return true;
  }

  @Override
  public void describeTo(final Description description) {
    description.appendText("is an ast for testcase echo");
  }

  public static Matcher echo() {
    return new IsEcho();
  }
}



