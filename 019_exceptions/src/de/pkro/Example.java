package de.pkro;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

// This is NOT necessarily a good example of using exceptions
// the
public class Example {
  public static void main(String[] args) {
    try {
      int result = divide();
      System.out.println(result);
    } catch(ArithmeticException e) {
      System.out.println(e.toString());
      System.out.println("Unable to perform division");
    }

  }

  // in real life these would probably handled in main
  private static int divide() {
    int x, y;
    try {
      x = getInt();
      y = getInt();
      System.out.println("x is " + x + ", y is " + y);
      return x / y;
    } catch (NoSuchElementException e) {
      throw new ArithmeticException("no suitable input");
    } catch (ArithmeticException e) {
      throw new ArithmeticException("attempt to divide by 0");
    }
  }

  private static int getInt() {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println("Please enter a number");
      try {
        return scanner.nextInt();

      } catch (InputMismatchException e) {
        scanner.nextLine();
        System.out.println("Please enter only numbers");
      }
    }
  }
}
