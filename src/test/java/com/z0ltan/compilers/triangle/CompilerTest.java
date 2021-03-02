package com.z0ltan.compilers.triangle;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.junit.Assert.*;

public class CompilerTest extends TestCase {
  public CompilerTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(CompilerTest.class);
  }

  public void testHello() {
    assertEquals(1, 1);
  }
}
