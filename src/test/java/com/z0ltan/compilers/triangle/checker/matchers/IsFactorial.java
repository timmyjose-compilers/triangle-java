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

public class IsFactorial extends TypeSafeMatcher<Program> {
  @Override
  public boolean matchesSafely(final Program program) {
    final Declaration nDecl = (Declaration)travel(program, "C.D.D1.D1.I.decl");

    final TypeDenoter nTypeDenoter = (TypeDenoter)travel(program, "C.D.D1.D1.T");
    assertThat(nTypeDenoter, is(equalTo(StdEnvironment.intType)));

    final FuncDeclaration factorialDecl = (FuncDeclaration)travel(program, "C.D.D1.D2.I.decl");

    final ConstFormalParameter nParamDecl = (ConstFormalParameter)travel(program, "C.D.D1.D2.FPS.FP.I.decl");

    final TypeDenoter nParamTypeDenoter = (TypeDenoter)travel(program, "C.D.D1.D2.FPS.FP.T");
    assertThat(nParamTypeDenoter, is(equalTo(StdEnvironment.intType)));

    final TypeDenoter factorialTypeDenoter = (TypeDenoter)travel(program, "C.D.D1.D2.T");
    assertThat(factorialTypeDenoter, is(equalTo(StdEnvironment.intType)));

    final IfExpression ifexpr1 = (IfExpression)travel(program, "C.D.D1.D2.E");
    assertThat(ifexpr1.type, is(equalTo(StdEnvironment.intType)));

    final BinaryExpression binexpr1 = (BinaryExpression)travel(program, "C.D.D1.D2.E.E1");
    assertThat(binexpr1.type, is(equalTo(StdEnvironment.boolType)));

    final VnameExpression vnameexpr1 = (VnameExpression)travel(program, "C.D.D1.D2.E.E1.E1");
    assertThat(vnameexpr1.type, is(equalTo(StdEnvironment.intType)));

    final ConstFormalParameter nParamAppliedDecl1 = (ConstFormalParameter)travel(program, "C.D.D1.D2.E.E1.E1.V.I.decl");
    assertThat(nParamAppliedDecl1, is(equalTo(nParamDecl)));

    final BinaryOperatorDeclaration lessThanOrEqualToDecl = (BinaryOperatorDeclaration)travel(program, "C.D.D1.D2.E.E1.O.decl");
    assertThat(lessThanOrEqualToDecl, is(equalTo(StdEnvironment.lessThanOrEqualToDecl)));

    final IntegerExpression iexpr1 = (IntegerExpression)travel(program, "C.D.D1.D2.E.E1.E2");
    assertThat(iexpr1.type, is(equalTo(StdEnvironment.intType)));

    final IntegerExpression iexpr2 = (IntegerExpression)travel(program, "C.D.D1.D2.E.E2");
    assertThat(iexpr2.type, is(equalTo(StdEnvironment.intType)));

    final BinaryExpression binexpr2 = (BinaryExpression)travel(program, "C.D.D1.D2.E.E3");
    assertThat(binexpr2.type, is(equalTo(StdEnvironment.intType)));

    final VnameExpression vnameexpr2 = (VnameExpression)travel(program, "C.D.D1.D2.E.E3.E1");
    assertThat(vnameexpr2.type, is(equalTo(StdEnvironment.intType)));

    final ConstFormalParameter nParamAppliedDecl2 = (ConstFormalParameter)travel(program, "C.D.D1.D2.E.E3.E1.V.I.decl");
    assertThat(nParamAppliedDecl2, is(equalTo(nParamDecl)));

    final BinaryOperatorDeclaration multDecl = (BinaryOperatorDeclaration)travel(program, "C.D.D1.D2.E.E3.O.decl");
    assertThat(multDecl, is(equalTo(StdEnvironment.multDecl)));

    final FuncDeclaration factorialAppliedDecl1 = (FuncDeclaration)travel(program, "C.D.D1.D2.E.E3.E2.I.decl");
    assertThat(factorialAppliedDecl1, is(equalTo(factorialDecl)));

    final BinaryExpression binexpr3 = (BinaryExpression)travel(program, "C.D.D1.D2.E.E3.E2.APS.AP.E");
    assertThat(binexpr3.type, is(equalTo(StdEnvironment.intType)));

    final VnameExpression vnameexpr3 = (VnameExpression)travel(program, "C.D.D1.D2.E.E3.E2.APS.AP.E.E1");
    assertThat(vnameexpr3.type, is(equalTo(StdEnvironment.intType)));

    final ConstFormalParameter nParamAppliedDecl3 = (ConstFormalParameter)travel(program, "C.D.D1.D2.E.E3.E2.APS.AP.E.E1.V.I.decl");
    assertThat(nParamAppliedDecl3, is(equalTo(nParamDecl)));

    final BinaryOperatorDeclaration subDecl = (BinaryOperatorDeclaration)travel(program, "C.D.D1.D2.E.E3.E2.APS.AP.E.O.decl");
    assertThat(subDecl, is(equalTo(StdEnvironment.subDecl)));

    final IntegerExpression iexpr3 = (IntegerExpression)travel(program, "C.D.D1.D2.E.E3.E2.APS.AP.E.E2");
    assertThat(iexpr3.type, is(equalTo(StdEnvironment.intType)));

    final ProcDeclaration readnumberDecl = (ProcDeclaration)travel(program, "C.D.D2.I.decl");

    final VarFormalParameter n2Decl = (VarFormalParameter)travel(program, "C.D.D2.FPS.FP.I.decl");

    final TypeDenoter n2TypeDenoter = (TypeDenoter)travel(program, "C.D.D2.FPS.FP.T");
    assertThat(n2TypeDenoter, is(equalTo(StdEnvironment.intType)));

    final ProcDeclaration getintDecl = (ProcDeclaration)travel(program, "C.D.D2.C.I.decl");
    assertThat(getintDecl, is(equalTo(StdEnvironment.getintDecl)));

    final VarFormalParameter n2AppliedDecl1 = (VarFormalParameter)travel(program, "C.D.D2.C.APS.AP.V.I.decl");
    assertThat(n2AppliedDecl1, is(equalTo(n2Decl)));

    final ProcDeclaration readnumberAppliedDecl = (ProcDeclaration)travel(program, "C.C.C1.C1.C1.I.decl");
    assertThat(readnumberAppliedDecl, is(equalTo(readnumberDecl)));

    final Declaration nAppliedDecl1 = (Declaration)travel(program, "C.C.C1.C1.C1.APS.AP.V.I.decl");
    assertThat(nAppliedDecl1, is(equalTo(nDecl)));

    final ProcDeclaration puteolDecl1 = (ProcDeclaration)travel(program, "C.C.C1.C1.C2.I.decl");
    assertThat(puteolDecl1, is(equalTo(StdEnvironment.puteolDecl)));

    final ProcDeclaration puteolDecl2 = (ProcDeclaration)travel(program, "C.C.C1.C2.I.decl");
    assertThat(puteolDecl2, is(equalTo(StdEnvironment.puteolDecl)));

    final ProcDeclaration puteolDecl3 = (ProcDeclaration)travel(program, "C.C.C2.I.decl");
    assertThat(puteolDecl2, is(equalTo(StdEnvironment.puteolDecl)));

    final CallExpression callexpr1 = (CallExpression)travel(program, "C.C.C2.APS.AP.E");
    assertThat(callexpr1.type, is(equalTo(StdEnvironment.intType)));

    final FuncDeclaration factorialAppliedDecl2 = (FuncDeclaration)travel(program, "C.C.C2.APS.AP.E.I.decl");
    assertThat(factorialAppliedDecl2, is(equalTo(factorialDecl)));

    final Declaration nAppliedDecl2 = (Declaration)travel(program, "C.C.C2.APS.AP.E.APS.AP.E.V.I.decl");
    assertThat(nAppliedDecl2, is(equalTo(nDecl)));

    return true;
  }

  @Override
  public void describeTo(final Description description) {
    description.appendText("is an ast for testcase factorial");
  }

  public static Matcher factorial() {
    return new IsFactorial();
  }
}
