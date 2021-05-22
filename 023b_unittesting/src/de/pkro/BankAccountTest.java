package de.pkro;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class BankAccountTest {
  private static int count;
  private BankAccount account;

  @org.junit.BeforeClass
  public static void beforeClass() {
    System.out.println("run once before all tests");
  }

  @org.junit.AfterClass
  public static void afterClass() {
    System.out.println("teardown after all tests.  Count = " + count++);
  }

  @org.junit.Before
  public void setup() {
    System.out.println("Setting up a fresh account. Count = " + count++);
    account = new BankAccount("Peer", "Teer", 1000.00, BankAccount.CHECKING);
  }

  @org.junit.After
  public void teardown() {
    System.out.println("teardown after individual test.  Count = " + count++);
    account = null;
  }

  @org.junit.Test
  public void deposit() {
    account.deposit(500.00, true);
    assertEquals(1500.00, account.getBalance(), 0);
  }

  @org.junit.Test
  public void withdraw_branch() {
    account.withdraw(600.00, true);
    assertEquals(400.00, account.getBalance(), 0);
  }

  @org.junit.Test(expected = IllegalArgumentException.class)
  public void withdraw_atm() {
    account.withdraw(501.00, false);
  }

  @org.junit.Test
  public void withdraw_atm_exception_with_catch() {
    try {
      account.withdraw(501.00, false);
      fail("Should have thrown IllegalArgumentException");
    } catch(IllegalArgumentException e) {/*noop -> passes*/}
  }

  @org.junit.Test
  public void getBalance_deposit() {
    account.deposit(500.00, true);
    assertEquals(1500.00, account.getBalance(), 0);
  }

  @org.junit.Test
  public void getBalance_withdraw() {
    account.withdraw(500.00, true);
    assertEquals(500.00, account.getBalance(), 0);
  }

  @Test
  public void isChecking_true() {
    assertTrue(account.isChecking());
  }

  @Test
  public void isChecking_false() {
    BankAccount account = new BankAccount("Peer", "Teer", 1000.00, BankAccount.SAVINGS);
    assertFalse("the account should NOT be a checking account", account.isChecking());
  }
}
