package com.z0ltan.compilers.triangle.checker.matchers;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.Description;

import com.z0ltan.compilers.triangle.ast.Program;
import com.z0ltan.compilers.triangle.ast.EmptyCommand;

import static com.z0ltan.compilers.triangle.scanner.SourcePosition.dummyPosition;

public class IsEmptyCommand extends TypeSafeMatcher<Program> {
  @Override
  public boolean matchesSafely(final Program program) {
    final Program emptyProgram = new Program(new EmptyCommand(dummyPosition()), dummyPosition());
    return program.equals(emptyProgram);
  }

  @Override
  public void describeTo(final Description description) {
    description.appendText("is an empty command");
  }

  public static Matcher emptycommand() {
    return new IsEmptyCommand();
  }
}
