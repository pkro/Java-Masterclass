package de.pkro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    List<String> someBingoNumbers =
        Arrays.asList("N40", "B10", "N46", "B6", "g53", "G49", "G60", "I17", "I26", "O71");
    someBingoNumbers
            .stream()
            .map(String::toUpperCase)
            .filter(n -> n.startsWith("G"))
            .sorted()
            .forEach(s -> System.out.println(s));
  }
}
