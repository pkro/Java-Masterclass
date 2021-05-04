package de.pkro;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {

    // Challenge 1
    Runnable runnable =
        () -> Arrays.stream("Let's split this up".split(" ")).forEach(System.out::println);

    // Challenge 2
    Function<String, String> everySecondChar =
        s -> {
          StringBuilder out = new StringBuilder();
          for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) {
              out.append(s.charAt(i));
            }
          }
          return out.toString();
        };

    // Challenge 3
    System.out.println(everySecondChar.apply("123456789"));

    // Challenge 4 + 5
    System.out.println(everySecondCharacter(everySecondChar, "123456789"));

    // Challenge 6 + 7
    Supplier<String> iLoveJava = () -> "I love Java";
    String supplierResult = iLoveJava.get();
    System.out.println(supplierResult);

    // Challenge 8
    // Interfaces that can be mapped to a lambda expression can only have ONE method
    // that MUST be implemented (other methods may exist if they have default implementations)
    // these are called functional interfaces

    // Challenge 9
    // java.util.concurrent.Callable has only one method (`call`)
    // and therefore the method the lambda implements can be infered by the java compiler

    // Challenge 8b
    // java.util.Comparator is a functional interface as only one method, compare,  MUST be
    // implemented

    // Challenge 9 + 10 + 11
    List<String> topNames2015 =
        Arrays.asList("Amelia", "Olivia", "emily", "isla", "ava", "oliver", "harry");
    topNames2015.stream()
        .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
        .sorted(String::compareTo)
        .forEach(System.out::println);

    // Challenge 12
    long namesWithA =
        topNames2015.stream().filter(s -> s.substring(0, 1).compareToIgnoreCase("a") == 0).count();
    System.out.println(namesWithA);

    // Challenge 13
    topNames2015.stream()
        .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
        .peek(System.out::println)
        .sorted(String::compareTo);

    // Nothing as the chain doesn't execute as no terminal operation takes place
    // (lazy evaluation)

    // Challenge 14
    System.out.println("challenge 14-------------------------");
    topNames2015.stream()
        .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
        .peek(System.out::println)
        .sorted(String::compareTo)
        .collect(Collectors.toList());
  }

  private static String everySecondCharacter(Function<String, String> f, String arg) {
    return f.apply(arg);
  }
}
