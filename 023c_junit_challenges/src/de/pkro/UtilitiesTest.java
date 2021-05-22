package de.pkro;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

public class UtilitiesTest {
  private static Utilities utils;

  @BeforeClass
  public static void initAll() {
    utils = new Utilities();
  }

  @Test
  public void everyNthChar_second() {
    char[] test = {'h', 'e', 'l', 'l', 'o'};
    char[] result = utils.everyNthChar(test, 2);
    assertArrayEquals(new char[] {'e', 'l'}, result);
  }

  @Test
  public void everyNthChar_last() {
    char[] test = {'h', 'e', 'l', 'l', 'o'};
    char[] result = utils.everyNthChar(test, 5);
    assertArrayEquals(new char[] {'o'}, result);
  }

  @Test
  public void everyNthChar_outofbounds() {
    char[] test = {'h', 'e', 'l', 'l', 'o'};
    char[] result = utils.everyNthChar(test, 6);
    assertArrayEquals(test, result);
  }

  @Test
  public void everyNthChar_n_eq_1() {
    char[] test = {'h', 'e', 'l', 'l', 'o'};
    char[] result = utils.everyNthChar(test, 1);
    assertArrayEquals(test, result);
  }

  @Test
  public void everyNthChar_n_lt_1() {
    char[] test = {'h', 'e', 'l', 'l', 'o'};
    char[] result = utils.everyNthChar(test, 0);
    assertArrayEquals(new char[] {'h', 'e', 'l', 'l', 'o'}, result);
  }

  @Test
  public void everyNthChar_in_is_null() {
    char[] test = null;
    char[] result = utils.everyNthChar(test, 0);
    assertArrayEquals(null, result);
  }

  @Test
  public void removePairs1() {
    String test = "AAABCDDEFF";
    assertEquals("ABCDEF", utils.removePairs(test));
  }

  @Test
  public void removePairs2() {
    String test = "ABCCABDEEF";
    assertEquals("ABCABDEF", utils.removePairs(test));
  }

  @Test
  public void removePairs3() {
    String test = "11";
    assertEquals("1", utils.removePairs(test));
  }

  @Test
  public void removePairs4() {
    String test = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    assertEquals("a", utils.removePairs(test));
  }

  @Test
  public void removePairs5() {
    String test = "a";
    assertEquals("a", utils.removePairs(test));
  }

  @Test
  public void removePairs6() {
    String test = null;
    assertNull(utils.removePairs(null));
  }

  @Test
  public void converter() {
    assertEquals(300, utils.converter(10, 5));
  }

  @Test(expected = ArithmeticException.class)
  public void converter_exception() {
    utils.converter(10, 0);
  }

  @Test
  public void nullIfOddLength() {
    assertNull(utils.nullIfOddLength("aaa"));
    assertNotNull(utils.nullIfOddLength("aa"));
  }
}
