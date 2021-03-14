package com.z0ltan.compilers.triangle.checker.matchers;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.Description;

import com.z0ltan.compilers.triangle.ast.Program;
import com.z0ltan.compilers.triangle.ast.Declaration;
import com.z0ltan.compilers.triangle.ast.ProcDeclaration;
import com.z0ltan.compilers.triangle.ast.AssignCommand;
import com.z0ltan.compilers.triangle.ast.Expression;
import com.z0ltan.compilers.triangle.ast.BinaryExpression;
import com.z0ltan.compilers.triangle.ast.VnameExpression;
import com.z0ltan.compilers.triangle.ast.IntegerExpression;
import com.z0ltan.compilers.triangle.ast.SimpleTypeDenoter;
import com.z0ltan.compilers.triangle.ast.SingleFormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.VarFormalParameter;
import com.z0ltan.compilers.triangle.ast.SimpleVname;
import com.z0ltan.compilers.triangle.ast.Identifier;
import com.z0ltan.compilers.triangle.ast.Operator;
import com.z0ltan.compilers.triangle.ast.IntegerLiteral;
import com.z0ltan.compilers.triangle.checker.StdEnvironment;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import static com.z0ltan.compilers.triangle.scanner.SourcePosition.dummyPosition;
import static com.z0ltan.compilers.triangle.helper.traveller.Traveller.travel;

public class IsInc extends TypeSafeMatcher<Program> {
  @Override
  public boolean matchesSafely(final Program program) {
    final Declaration nDecl1 = (Declaration)travel(program, "C.D.D2.FPS.FP.I.decl");
    final VarFormalParameter nExpectedDecl = new VarFormalParameter(new Identifier("n", dummyPosition()), StdEnvironment.intType, dummyPosition());
    assertThat(nDecl1, is(equalTo(nExpectedDecl)));

    final BinaryExpression binexpr1 = (BinaryExpression)travel(program, "C.D.D2.C.E");
    assertThat(binexpr1.type, is(equalTo(StdEnvironment.intType)));

    final Declaration getintDecl1 = (Declaration)travel(program, "C.C.C1.C1.I.decl");
    assertThat(getintDecl1, is(equalTo(StdEnvironment.getintDecl)));

    final Declaration xAppliedDecl1 = (Declaration)travel(program, "C.C.C1.C1.APS.AP.V.I.decl");
    final Declaration xExpectedDecl = (Declaration)travel(program, "C.D.D1");
    assertThat(xAppliedDecl1, is(equalTo(xExpectedDecl)));

    final Declaration incDecl = (Declaration)travel(program, "C.C.C1.C2.I.decl");
    final Declaration incExpectedDecl = (Declaration)travel(program, "C.D.D2");
    assertThat(incDecl, is(equalTo(incExpectedDecl)));

    final Declaration xAppliedDecl2 = (Declaration)travel(program, "C.C.C1.C2.APS.AP.V.I.decl");
    assertThat(xAppliedDecl2, is(equalTo(xExpectedDecl)));

    final Declaration putintDecl = (Declaration)travel(program, "C.C.C2.I.decl");
    assertThat(putintDecl, is(equalTo(StdEnvironment.putintDecl)));

    final Declaration xAppliedDecl3 = (Declaration)travel(program, "C.C.C2.APS.AP.E.V.I.decl");
    assertThat(xAppliedDecl3, is(equalTo(xExpectedDecl)));

    return true;
  }

  @Override
  public void describeTo(final Description description) {
    description.appendText("is an ast for testcase inc");
  }

  public static Matcher inc() {
    return new IsInc();
  }
}


