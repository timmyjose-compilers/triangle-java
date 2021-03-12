package com.z0ltan.compilers.triangle.checker.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.Matcher;

import com.z0ltan.compilers.triangle.ast.Program;
import com.z0ltan.compilers.triangle.ast.AssignCommand;
import com.z0ltan.compilers.triangle.ast.CallCommand;
import com.z0ltan.compilers.triangle.ast.Expression;
import com.z0ltan.compilers.triangle.ast.IntegerExpression;
import com.z0ltan.compilers.triangle.ast.BinaryExpression;
import com.z0ltan.compilers.triangle.ast.VnameExpression;
import com.z0ltan.compilers.triangle.ast.SimpleVname;
import com.z0ltan.compilers.triangle.ast.Declaration;
import com.z0ltan.compilers.triangle.ast.VarDeclaration;
import com.z0ltan.compilers.triangle.ast.ProcDeclaration;
import com.z0ltan.compilers.triangle.ast.SingleFormalParameterSequence;
import com.z0ltan.compilers.triangle.ast.VarFormalParameter;
import com.z0ltan.compilers.triangle.ast.SimpleTypeDenoter;
import com.z0ltan.compilers.triangle.ast.Identifier;
import com.z0ltan.compilers.triangle.ast.IntegerLiteral;
import com.z0ltan.compilers.triangle.ast.Operator;

import com.z0ltan.compilers.triangle.checker.StdEnvironment;

import static com.z0ltan.compilers.triangle.scanner.SourcePosition.dummyPosition;
import static com.z0ltan.compilers.triangle.helper.traveller.Traveller.travel;

public class IsInc extends TypeSafeMatcher<Program> {
  @Override
  public boolean matchesSafely(final Program program) {
    final Declaration xActualDecl = (Declaration)travel(program, "C.D1.I.decl");
    final Declaration xExpectedDecl = new VarDeclaration(new Identifier("x", dummyPosition()), StdEnvironment.intType, dummyPosition());
    if (!xActualDecl.equals(xExpectedDecl)) {
      return false;
    }

    final Declaration incActualDecl1 = (Declaration)travel(program, "C.D2.I.decl");
    final Declaration incExpectedDecl = 
      new ProcDeclaration(
          new Identifier("inc", dummyPosition()),
          new SingleFormalParameterSequence(
            new VarFormalParameter(new Identifier("n", dummyPosition()), 
              new SimpleTypeDenoter(new Identifier("Integer", dummyPosition()), dummyPosition()),
              dummyPosition()),
            dummyPosition()),
          new AssignCommand(
            new SimpleVname(new Identifier("n", dummyPosition()), dummyPosition()),
            new BinaryExpression(
              new VnameExpression(new SimpleVname(new Identifier("n", dummyPosition()), dummyPosition()), dummyPosition()),
              new Operator("+", dummyPosition()),
              new IntegerExpression(new IntegerLiteral("1", dummyPosition()), dummyPosition()),
              dummyPosition()),
            dummyPosition()),
          dummyPosition());

    if (!incActualDecl1.equals(incExpectedDecl)) {
      return false;
    }

    final SimpleVname nvname1 = (SimpleVname)travel(program, "C.D2.C.E.E1");
    if (!nvname1.variable) {
      return false;
    }

    final Declaration nActualDecl = (Declaration)travel(program, "C.D2.C.I.decl");
    if (!nActualDecl.equals(incActualDecl1)) {
      return false;
    }

    final SimpleVname nvname2 = (SimpleVname)travel(program, "C.D2.C.E.E1.V");
    if (!nvname2.variable) {
      return false;
    }

    final Expression vexpr = (Expression)travel(program, "C.D2.C.E.E1");
    if (!vexpr.type.equals(StdEnvironment.intType)) {
      return false;
    }

    final Declaration opPlusActualDecl = (Declaration)travel(program, "C.D2.C.E.O.decl");
    final Declaration opPlusExpectedDecl = (Declaration)StdEnvironment.addDecl;
    if (!opPlusActualDecl.equals(opPlusExpectedDecl)) {
      return false;
    }

    final Expression iexpr = (Expression)travel(program, "C.D2.C.E.E2");
    if (!iexpr.type.equals(StdEnvironment.intType)) {
      return false;
    }

    final Expression binExpr = (Expression)travel(program, "C.D2.C.E");
    if (!binExpr.type.equals(StdEnvironment.intType)) {
      return false;
    }

    final Identifier getint = (Identifier)travel(program, "C.C1.C1.I");
    final Declaration getintActualDecl = (Declaration)travel(program, "C.C1.C1.I.decl");
    if (!getintActualDecl.equals(StdEnvironment.getintDecl)) {
      return false;
    }

    final SimpleVname nvname3 = (SimpleVname)travel(program, "C.C1.C1.APS.AP.V");
    if (!nvname3.variable) {
      return false;
    }

    final Declaration appliedX1Decl = (Declaration)travel(program, "C.C1.C1.APS.AP.V.I.decl");
    if (!appliedX1Decl.equals(xActualDecl)) {
      return false;
    }

    final Declaration incActualDecl2 = (Declaration)travel(program, "C.C1.C2.I.decl");
    if (!incActualDecl2.equals(incActualDecl1)) {
      return false;
    }

    final SimpleVname nvname4 = (SimpleVname)travel(program, "C.C1.C2.APS.AP.V");
    if (!nvname4.variable) {
      return false;
    }

    final Declaration appliedX2Decl = (Declaration)travel(program, "C.C1.C2.APS.AP.V.I.decl");
    if (!appliedX2Decl.equals(xActualDecl)) {
      return false;
    }

    final Declaration putintActualDecl = (Declaration)travel(program, "C.C2.I.decl");
    if (!putintActualDecl.equals(StdEnvironment.putintDecl)) {
      return false;
    }

    final SimpleVname nvname5 = (SimpleVname)travel(program, "C.C2.APS.AP.V");
    if (!nvname5.variable) {
      return false;
    }

    final Declaration appliedX3Decl = (Declaration)travel(program, "C.C2.APS.AP.V.I.decl");
    if (!appliedX3Decl.equals(xActualDecl)) {
      return false;
    }

    return true;
  }

  @Override
  public void describeTo(final Description description) {
    description.appendText("is a valid ast for testInc");
  }

  public static Matcher inc() {
    return new IsInc();
  }
}
