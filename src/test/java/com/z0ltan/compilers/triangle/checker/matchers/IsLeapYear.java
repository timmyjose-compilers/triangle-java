package com.z0ltan.compilers.triangle.checker.matchers;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.Description;

import com.z0ltan.compilers.triangle.ast.Program;
import com.z0ltan.compilers.triangle.ast.Declaration;
import com.z0ltan.compilers.triangle.ast.ProcDeclaration;
import com.z0ltan.compilers.triangle.ast.FuncDeclaration;
import com.z0ltan.compilers.triangle.ast.TypeDeclaration;
import com.z0ltan.compilers.triangle.ast.ConstDeclaration;
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

public class IsLeapYear extends TypeSafeMatcher<Program> {
  @Override
  public boolean matchesSafely(final Program program) {
    return false;
  }

  @Override
  public void describeTo(final Description description) {
    description.appendText("an ast for testcase leapyear");
  }

  public static Matcher leapYear() {
    return new IsLeapYear();
  }
}
