package com.z0ltan.compilers.triangle.checker.matchers;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.Description;

import com.z0ltan.compilers.triangle.ast.Program;
import com.z0ltan.compilers.triangle.ast.Declaration;
import com.z0ltan.compilers.triangle.ast.ProcDeclaration;
import com.z0ltan.compilers.triangle.ast.FuncDeclaration;
import com.z0ltan.compilers.triangle.ast.BinaryOperatorDeclaration;
import com.z0ltan.compilers.triangle.ast.BinaryExpression;
import com.z0ltan.compilers.triangle.ast.CallExpression;
import com.z0ltan.compilers.triangle.ast.VnameExpression;
import com.z0ltan.compilers.triangle.ast.IntegerExpression;
import com.z0ltan.compilers.triangle.ast.TypeDenoter;
import com.z0ltan.compilers.triangle.checker.StdEnvironment;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import static com.z0ltan.compilers.triangle.scanner.SourcePosition.dummyPosition;
import static com.z0ltan.compilers.triangle.helper.traveller.Traveller.travel;

public class IsOdd extends TypeSafeMatcher<Program> {
  @Override
  public boolean matchesSafely(final Program program) {
    final Declaration nDecl = (Declaration)travel(program, "C.D.D1.I.decl");

    final TypeDenoter intDecl1 = (TypeDenoter)travel(program, "C.D.D1.T");
    assertThat(intDecl1, is(equalTo(StdEnvironment.intType)));

    final FuncDeclaration oddDecl = (FuncDeclaration)travel(program, "C.D.D2.I.decl");

    final TypeDenoter boolDecl1 = (TypeDenoter)travel(program, "C.D.D2.T");
    assertThat(boolDecl1, is(equalTo(StdEnvironment.boolType)));

    final BinaryExpression binexpr1 = (BinaryExpression)travel(program, "C.D.D2.E");
    assertThat(binexpr1.type, is(equalTo(StdEnvironment.boolType)));

    final VnameExpression vnamexpr1 = (VnameExpression)travel(program, "C.D.D2.E.E1.E1");
    assertThat(vnamexpr1.type, is(equalTo(StdEnvironment.intType)));

    final BinaryOperatorDeclaration binopdecl1 = (BinaryOperatorDeclaration)travel(program, "C.D.D2.E.E1.O.decl");
    assertThat(binopdecl1, is(equalTo(StdEnvironment.modDecl)));

    final IntegerExpression iexpr1 = (IntegerExpression)travel(program, "C.D.D2.E.E1.E2");
    assertThat(iexpr1.type, is(equalTo(StdEnvironment.intType)));

    final BinaryExpression binexpr2 = (BinaryExpression)travel(program, "C.D.D2.E.E1");
    assertThat(binexpr2.type, is(equalTo(StdEnvironment.intType)));

    final BinaryOperatorDeclaration binopdecl2 = (BinaryOperatorDeclaration)travel(program, "C.D.D2.E.O.decl");
    assertThat(binopdecl2, is(equalTo(StdEnvironment.noteqDecl)));

    final IntegerExpression iexpr2 = (IntegerExpression)travel(program, "C.D.D2.E.E2");
    assertThat(iexpr2.type, is(equalTo(StdEnvironment.intType)));

    final Declaration getintDecl = (Declaration)travel(program, "C.C.C1.I.decl");
    assertThat(getintDecl, is(equalTo(StdEnvironment.getintDecl)));

    final Declaration nAppliedDecl1 = (Declaration)travel(program, "C.C.C1.APS.AP.V.I.decl");
    assertThat(nAppliedDecl1, is(equalTo(nDecl)));

    final FuncDeclaration oddAppliedDecl1 = (FuncDeclaration)travel(program, "C.C.C2.E.I.decl");
    assertThat(oddAppliedDecl1, is(equalTo(oddDecl)));

    final Declaration nAppliedDecl2 = (Declaration)travel(program, "C.C.C2.E.APS.AP.E.V.I.decl");
    assertThat(nAppliedDecl2, is(equalTo(nDecl)));

    final CallExpression callexpr1 = (CallExpression)travel(program, "C.C.C2.E");
    assertThat(callexpr1.type, is(equalTo(StdEnvironment.boolType)));

    final VnameExpression vnamexpr2 = (VnameExpression)travel(program, "C.C.C2.E.APS.AP.E");
    assertThat(vnamexpr2.type, is(equalTo(StdEnvironment.intType)));

    final ProcDeclaration putintDecl1 = (ProcDeclaration)travel(program, "C.C.C2.C1.I.decl");
    assertThat(putintDecl1, is(equalTo(StdEnvironment.putintDecl)));

    final IntegerExpression iexpr3 = (IntegerExpression)travel(program, "C.C.C2.C1.APS.AP.E");
    assertThat(iexpr3.type, is(equalTo(StdEnvironment.intType)));

    final ProcDeclaration putintDecl2 = (ProcDeclaration)travel(program, "C.C.C2.C2.I.decl");
    assertThat(putintDecl2, is(equalTo(StdEnvironment.putintDecl)));

    final IntegerExpression iexpr4 = (IntegerExpression)travel(program, "C.C.C2.C2.APS.AP.E");
    assertThat(iexpr4.type, is(equalTo(StdEnvironment.intType)));

    return true;
  }

  @Override
  public void describeTo(final Description description) {
    description.appendText("is an ast for testcase odd");
  }

  public static Matcher odd() {
    return new IsOdd();
  }
}




