package de.pkro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

  public static void main(String[] args) {
    List<String> someBingoNumbers =
        Arrays.asList("N40", "B10", "N46", "B6", "g53", "G49", "G60", "I17", "I26", "O71");
    someBingoNumbers.stream()
        .map(String::toUpperCase)
        .peek(System.out::println)
        .filter(n -> n.startsWith("G"))
        .sorted()
        .forEach(System.out::println);

    Stream<String> ioNumberStream = Stream.of("I26", "I17", "I29", "O71");
    Stream<String> inNumberStream = Stream.of("N40", "N36", "I26", "I17", "I29", "O71");
    Stream<String> concatStream = Stream.concat(ioNumberStream, inNumberStream);
    // System.out.println(concatStream.distinct().count());
    /*    concatStream
    .map(
        s -> {
          System.out.println(s);
          return s;
        })
    .forEach(s -> System.out.println(s));*/

    Employee e1 = new Employee("John Doe", 30);
    Employee e2 = new Employee("Jack Doe", 40);
    Employee e3 = new Employee("Jane Doe", 50);
    Employee e4 = new Employee("Bill Smithee", 20);
    Employee e5 = new Employee("Joe smithee", 60);
    Employee e6 = new Employee("Peer K", 49);

    Department hr = new Department("Human ressources");
    hr.addEmployee(e1);
    hr.addEmployee(e2);
    hr.addEmployee(e3);

    Department accounting = new Department("Accounting");
    accounting.addEmployee(e5);
    accounting.addEmployee(e6);
    accounting.addEmployee(e4);

    List<Department> departments = new ArrayList<>();
    departments.add(hr);
    departments.add(accounting);

    departments.stream()
        .flatMap(department -> department.getEmployees().stream())
        .forEach(System.out::println);

    /*List<String> sortedGNumbers =
    someBingoNumbers.stream()
            .map(String::toUpperCase)
            .filter(s -> s.startsWith("G"))
            .sorted()
            .collect(Collectors.toList());*/

    List<String> sortedGNumbers =
        someBingoNumbers.stream()
            .map(String::toUpperCase)
            .filter(s -> s.startsWith("G"))
            .sorted()
            .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    // collect(supplier (creates objects), accumulator (used to add single item), combiner (used by
    // java to improve efficiency of operation)
    for (String s : sortedGNumbers) {
      System.out.println(s);
    }

    departments.stream()
        .flatMap(department -> department.getEmployees().stream())
        .reduce((em1, em2) -> em1.getAge() < em2.getAge() ? em1 : em2)
        .ifPresent(System.out::println);

    Stream.of("ABC", "AC", "BAA", "CCCC", "XY", "ST")
            .filter(
                    s -> {
                      // this doesn't print out anything if we leave off
                      // the forEach at the end
                      System.out.println(s);
                      return s.length() == 3;
                    })
            .count(); // terminal operation
  }


}
