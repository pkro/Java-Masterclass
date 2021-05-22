package de.pkro;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class UtilitesTestParameterized {
  Utilities utils;
  private String input;
  private String expected;

  public UtilitesTestParameterized(String input, String expected) {
    this.input = input;
    this.expected = expected;
  }

  @Parameterized.Parameters
  public static Collection<Object> testConditions() {
    return Arrays.asList(
        new Object[][] {
          {"ABCDEFF", "ABCDEF"},
          {"AB88EFFG", "AB8EFG"},
          {"A", "A"}
        });
  }

  @Test
  public void removePairsTest() {
    assertEquals(expected, utils.removePairs(input));
  }

  @Before
  public void setup() {
    utils = new Utilities();
  }
}
