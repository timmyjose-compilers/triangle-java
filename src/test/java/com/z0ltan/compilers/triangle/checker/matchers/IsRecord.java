package com.z0ltan.compilers.triangle.checker.matchers;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.Description;

import com.z0ltan.compilers.triangle.ast.Program;
import com.z0ltan.compilers.triangle.ast.Declaration;
import com.z0ltan.compilers.triangle.ast.ProcDeclaration;
import com.z0ltan.compilers.triangle.ast.FuncDeclaration;
import com.z0ltan.compilers.triangle.ast.TypeDeclaration;
import com.z0ltan.compilers.triangle.ast.IfExpression;
import com.z0ltan.compilers.triangle.ast.CallExpression;
import com.z0ltan.compilers.triangle.ast.VnameExpression;
import com.z0ltan.compilers.triangle.ast.IntegerExpression;
import com.z0ltan.compilers.triangle.ast.BinaryExpression;
import com.z0ltan.compilers.triangle.ast.RecordExpression;
import com.z0ltan.compilers.triangle.ast.BinaryOperatorDeclaration;
import com.z0ltan.compilers.triangle.ast.TypeDenoter;
import com.z0ltan.compilers.triangle.ast.FieldTypeDenoter;
import com.z0ltan.compilers.triangle.ast.VarFormalParameter;
import com.z0ltan.compilers.triangle.ast.ConstFormalParameter;
import com.z0ltan.compilers.triangle.checker.StdEnvironment;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import static com.z0ltan.compilers.triangle.scanner.SourcePosition.dummyPosition;
import static com.z0ltan.compilers.triangle.helper.traveller.Traveller.travel;

public class IsRecord extends TypeSafeMatcher<Program> {
  @Override
  public boolean matchesSafely(final Program program) {
    final TypeDeclaration dateDecl = (TypeDeclaration)travel(program, "C.D.D1.D1.I.decl");

    final FieldTypeDenoter yFieldDecl = (FieldTypeDenoter)travel(program, "C.D.D1.D1.T.FTD");

    final TypeDenoter yFieldTypeDenoter = (TypeDenoter)travel(program, "C.D.D1.D1.T.FTD.T");
    assertThat(yFieldTypeDenoter, is(equalTo(StdEnvironment.intType)));

    final FieldTypeDenoter mFieldDecl = (FieldTypeDenoter)travel(program, "C.D.D1.D1.T.FTD.FTD");

    final TypeDenoter mFieldTypeDenoter = (TypeDenoter)travel(program, "C.D.D1.D1.T.FTD.FTD.T");
    assertThat(mFieldTypeDenoter, is(equalTo(StdEnvironment.intType)));

    final FieldTypeDenoter dFieldDecl = (FieldTypeDenoter)travel(program, "C.D.D1.D1.T.FTD.FTD.FTD");

    final TypeDenoter dFieldTypeDenoter = (TypeDenoter)travel(program, "C.D.D1.D1.T.FTD.FTD.FTD.T");
    assertThat(dFieldTypeDenoter, is(equalTo(StdEnvironment.intType)));

    final Declaration todayDecl = (Declaration)travel(program, "C.D.D1.D2.I.decl");

    final TypeDenoter todayTypeDenoter = (TypeDenoter)travel(program, "C.D.D1.D2.T");
    assertThat(todayTypeDenoter, is(equalTo(dateDecl.T)));

    final Declaration tomorrowDecl = (Declaration)travel(program, "C.D.D2.I.decl");

    final TypeDenoter tomorrowTypeDenoter = (TypeDenoter)travel(program, "C.D.D2.T");
    assertThat(tomorrowTypeDenoter, is(equalTo(dateDecl.T)));

    final Declaration todayAppliedDecl1 = (Declaration)travel(program, "C.C.C1.C1.C1.C1.C1.C1.C1.V.I.decl");
    assertThat(todayAppliedDecl1, is(equalTo(todayDecl)));

    final RecordExpression recordexpr1 = (RecordExpression)travel(program, "C.C.C1.C1.C1.C1.C1.C1.C1.E");
    assertThat(recordexpr1.type, is(equalTo(dateDecl.T)));

    final IntegerExpression iexpr1 = (IntegerExpression)travel(program, "C.C.C1.C1.C1.C1.C1.C1.C1.E.RA.E");
    assertThat(iexpr1.type, is(equalTo(StdEnvironment.intType)));

    final IntegerExpression iexpr2 = (IntegerExpression)travel(program, "C.C.C1.C1.C1.C1.C1.C1.C1.E.RA.RA.E");
    assertThat(iexpr2.type, is(equalTo(StdEnvironment.intType)));

    final IntegerExpression iexpr3 = (IntegerExpression)travel(program, "C.C.C1.C1.C1.C1.C1.C1.C1.E.RA.RA.RA.E");
    assertThat(iexpr3.type, is(equalTo(StdEnvironment.intType)));

    final ProcDeclaration putintDecl1 = (ProcDeclaration)travel(program, "C.C.C1.C1.C1.C1.C1.C1.C2.I.decl");
    assertThat(putintDecl1, is(equalTo(StdEnvironment.putintDecl)));

    final VnameExpression vnameexpr1 = (VnameExpression)travel(program, "C.C.C1.C1.C1.C1.C1.C1.C2.APS.AP.E");
    assertThat(vnameexpr1.type, is(equalTo(StdEnvironment.intType)));

    final Declaration todayAppliedDecl11 = (Declaration)travel(program, "C.C.C1.C1.C1.C1.C1.C1.C2.APS.AP.E.V.V.I.decl");
    assertThat(todayAppliedDecl11, is(equalTo(todayDecl)));

    final FieldTypeDenoter yFieldAppliedDecl2 = (FieldTypeDenoter)travel(program, "C.C.C1.C1.C1.C1.C1.C1.C2.APS.AP.E.V.I.decl");
    assertThat(yFieldAppliedDecl2, is(equalTo(yFieldDecl)));

    final ProcDeclaration putintDecl2 = (ProcDeclaration)travel(program, "C.C.C1.C1.C1.C1.C1.C2.I.decl");
    assertThat(putintDecl2, is(equalTo(StdEnvironment.putintDecl)));

    final Declaration todayAppliedDecl2 = (Declaration)travel(program, "C.C.C1.C1.C1.C1.C1.C2.APS.AP.E.V.V.I.decl");
    assertThat(todayAppliedDecl2, is(equalTo(todayDecl)));

    final FieldTypeDenoter mFieldAppliedDecl2 = (FieldTypeDenoter)travel(program, "C.C.C1.C1.C1.C1.C1.C2.APS.AP.E.V.I.decl");
    assertThat(mFieldAppliedDecl2, is(equalTo(mFieldDecl)));

    final ProcDeclaration putintDecl3 = (ProcDeclaration)travel(program, "C.C.C1.C1.C1.C1.C2.I.decl");
    assertThat(putintDecl3, is(equalTo(StdEnvironment.putintDecl)));

    final VnameExpression vnameexpr4 = (VnameExpression)travel(program, "C.C.C1.C1.C1.C1.C2.APS.AP.E");
    assertThat(vnameexpr4.type, is(equalTo(StdEnvironment.intType)));

    final Declaration todayAppliedDecl3 = (Declaration)travel(program, "C.C.C1.C1.C1.C1.C2.APS.AP.E.V.V.I.decl");
    assertThat(todayAppliedDecl3, is(equalTo(todayDecl)));

    final FieldTypeDenoter dFieldAppliedDecl2 = (FieldTypeDenoter)travel(program, "C.C.C1.C1.C1.C1.C2.APS.AP.E.V.I.decl");
    assertThat(dFieldAppliedDecl2, is(equalTo(dFieldDecl)));

    final Declaration tomorrowAppliedDecl1 = (Declaration)travel(program, "C.C.C1.C1.C1.C2.V.I.decl");
    assertThat(tomorrowAppliedDecl1, is(equalTo(tomorrowDecl)));

    final RecordExpression recordexpr2 = (RecordExpression)travel(program, "C.C.C1.C1.C1.C2.E");
    assertThat(recordexpr2.type, is(equalTo(dateDecl.T)));

    final VnameExpression vnameexpr2 = (VnameExpression)travel(program, "C.C.C1.C1.C1.C2.E.RA.E");
    assertThat(vnameexpr2.type, is(equalTo(StdEnvironment.intType)));

    final Declaration todayAppliedDecl4 = (Declaration)travel(program, "C.C.C1.C1.C1.C2.E.RA.E.V.V.I.decl");
    assertThat(todayAppliedDecl4, is(equalTo(todayDecl)));

    final FieldTypeDenoter yFieldAppliedDecl4 = (FieldTypeDenoter)travel(program, "C.C.C1.C1.C1.C2.E.RA.E.V.I.decl");
    assertThat(yFieldAppliedDecl4, is(equalTo(yFieldDecl)));

    final VnameExpression vnameexpr5 = (VnameExpression)travel(program, "C.C.C1.C1.C1.C2.E.RA.RA.E");
    assertThat(vnameexpr5.type, is(equalTo(StdEnvironment.intType)));

    final Declaration todayAppliedDecl5 = (Declaration)travel(program, "C.C.C1.C1.C1.C2.E.RA.RA.E.V.V.I.decl");
    assertThat(todayAppliedDecl5, is(equalTo(todayDecl)));

    final FieldTypeDenoter mFieldAppliedDecl4 = (FieldTypeDenoter)travel(program, "C.C.C1.C1.C1.C2.E.RA.RA.E.V.I.decl");
    assertThat(mFieldAppliedDecl4, is(equalTo(mFieldDecl)));

    final BinaryExpression binexpr1 = (BinaryExpression)travel(program, "C.C.C1.C1.C1.C2.E.RA.RA.RA.E");
    assertThat(binexpr1.type, is(equalTo(StdEnvironment.intType)));

    final VnameExpression vnameexpr3 = (VnameExpression) travel(program, "C.C.C1.C1.C1.C2.E.RA.RA.RA.E.E1");
    assertThat(vnameexpr3.type, is(equalTo(StdEnvironment.intType)));

    final Declaration todayAppliedDecl6 = (Declaration)travel(program, "C.C.C1.C1.C1.C2.E.RA.RA.RA.E.E1.V.V.I.decl");
    assertThat(todayAppliedDecl6, is(equalTo(todayDecl)));

    final FieldTypeDenoter dFieldAppliedDecl4 = (FieldTypeDenoter)travel(program, "C.C.C1.C1.C1.C2.E.RA.RA.RA.E.E1.V.I.decl");
    assertThat(dFieldAppliedDecl4, is(equalTo(dFieldDecl)));

    final BinaryOperatorDeclaration addDecl = (BinaryOperatorDeclaration)travel(program, "C.C.C1.C1.C1.C2.E.RA.RA.RA.E.O.decl");
    assertThat(addDecl, is(equalTo(StdEnvironment.addDecl)));

    final IntegerExpression iexpr4 = (IntegerExpression)travel(program, "C.C.C1.C1.C1.C2.E.RA.RA.RA.E.E2");
    assertThat(iexpr4.type, is(equalTo(StdEnvironment.intType)));

    final ProcDeclaration putintDecl4 = (ProcDeclaration)travel(program, "C.C.C1.C1.C2.I.decl");
    assertThat(putintDecl4, is(equalTo(StdEnvironment.putintDecl)));

    final Declaration tomorrowAppliedDecl2 = (Declaration)travel(program, "C.C.C1.C1.C2.APS.AP.E.V.V.I.decl");
    assertThat(tomorrowAppliedDecl2, is(equalTo(tomorrowDecl)));

    final FieldTypeDenoter yFieldAppliedDecl5 = (FieldTypeDenoter)travel(program, "C.C.C1.C1.C2.APS.AP.E.V.I.decl");
    assertThat(yFieldAppliedDecl5, is(equalTo(yFieldDecl)));

    final ProcDeclaration putintDecl5 = (ProcDeclaration)travel(program, "C.C.C1.C2.I.decl");
    assertThat(putintDecl5, is(equalTo(StdEnvironment.putintDecl)));

    final VnameExpression vnameexpr6 = (VnameExpression)travel(program, "C.C.C1.C2.APS.AP.E");
    assertThat(vnameexpr6.type, is(equalTo(StdEnvironment.intType)));

    final Declaration tomorrowAppliedDecl3 = (Declaration)travel(program, "C.C.C1.C2.APS.AP.E.V.V.I.decl");
    assertThat(tomorrowAppliedDecl3, is(equalTo(tomorrowDecl)));

    final FieldTypeDenoter mFieldAppliedDecl5 = (FieldTypeDenoter)travel(program, "C.C.C1.C2.APS.AP.E.V.I.decl");
    assertThat(mFieldAppliedDecl5, is(equalTo(mFieldDecl)));

    final ProcDeclaration putintDecl6 = (ProcDeclaration)travel(program, "C.C.C2.I.decl");
    assertThat(putintDecl6, is(equalTo(StdEnvironment.putintDecl)));

    final VnameExpression vnameexpr7 = (VnameExpression)travel(program, "C.C.C2.APS.AP.E");
    assertThat(vnameexpr7.type, is(equalTo(StdEnvironment.intType)));

    final Declaration tomorrowAppliedDecl4 = (Declaration)travel(program, "C.C.C2.APS.AP.E.V.V.I.decl");
    assertThat(tomorrowAppliedDecl4, is(equalTo(tomorrowDecl)));

    final FieldTypeDenoter dFieldAppliedDecl5 = (FieldTypeDenoter)travel(program, "C.C.C2.APS.AP.E.V.I.decl");
    assertThat(dFieldAppliedDecl5, is(equalTo(dFieldDecl)));

    return true;
  }

  @Override
  public void describeTo(final Description description) {
    description.appendText("is an ast for testcase record");
  }

  public static Matcher record() {
    return new IsRecord();
  }
}
