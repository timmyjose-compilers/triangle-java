package com.z0ltan.compilers.triangle.checker.matchers;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.Description;

import com.z0ltan.compilers.triangle.ast.Program;
import com.z0ltan.compilers.triangle.ast.Declaration;
import com.z0ltan.compilers.triangle.ast.IntegerExpression;
import com.z0ltan.compilers.triangle.checker.StdEnvironment;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import static com.z0ltan.compilers.triangle.scanner.SourcePosition.dummyPosition;
import static com.z0ltan.compilers.triangle.helper.traveller.Traveller.travel;

public class IsHello extends TypeSafeMatcher<Program> {
  @Override
  public boolean matchesSafely(final Program program) {
    final Declaration putintDecl = (Declaration)travel(program, "C.I.decl");
    final Declaration putintExpectedDecl = StdEnvironment.putintDecl;
    assertThat(putintDecl, is(putintExpectedDecl));

    final IntegerExpression iexpr1 = (IntegerExpression)travel(program, "C.APS.AP.E");
    assertThat(iexpr1.type, is(StdEnvironment.intType));

    return true;
  }

  @Override
  public void describeTo(final Description description) {
    description.appendText("an ast for testcase hello");
  }

  public static Matcher hello() {
    return new IsHello();
  }
}

