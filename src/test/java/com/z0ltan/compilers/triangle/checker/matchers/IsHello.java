package com.z0ltan.compilers.triangle.checker.matchers;

import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import com.z0ltan.compilers.triangle.ast.Program;
import com.z0ltan.compilers.triangle.ast.CallCommand;
import com.z0ltan.compilers.triangle.ast.SingleActualParameterSequence;
import com.z0ltan.compilers.triangle.ast.ConstActualParameter;
import com.z0ltan.compilers.triangle.ast.IntTypeDenoter;
import com.z0ltan.compilers.triangle.ast.IntegerExpression;

import static com.z0ltan.compilers.triangle.scanner.SourcePosition.dummyPosition;

public class IsHello extends TypeSafeMatcher<Program> {
  @Override
  public boolean matchesSafely(Program program) {
    final IntegerExpression iexpr =
      (IntegerExpression)((ConstActualParameter)((SingleActualParameterSequence)((CallCommand)program.C).APS).AP).E;
    return iexpr.type.equals(new IntTypeDenoter(dummyPosition()));
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("is a valid ast for testHello");
  }

  public static Matcher hello() {
    return new IsHello();
  }
}
