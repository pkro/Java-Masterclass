package de.pkro;

import javax.crypto.spec.PSource;
import java.util.*;
import java.util.function.*;

interface UpperConcat {
  public String upperAndConcat(String s1, String s2);
}

public class Main {
  public static void main(String[] args) {
    Employee john = new Employee("John Doe", 30);
    Employee tim = new Employee("Tim Doe", 21);
    Employee jack = new Employee("Jack Doe", 21);
    Employee snow = new Employee("Snow White", 22);
    Employee red = new Employee("Red", 35);
    Employee prince = new Employee("Prince charming", 40);
    Employee abraham = new Employee("Abraham Abrahamson", 1000);

    List<Employee> employees = new ArrayList<>();
    employees.add(john);
    employees.add(tim);
    employees.add(jack);
    employees.add(snow);
    employees.add(abraham);
    employees.add(red);
    employees.add(prince);

    // employees.forEach(employee -> System.out.println(employee.getName()));

    // System.out.println("Employees over 30:");

    // "conventional"
    /*for(Employee employee:employees) {
      if(employee.getAge()>30) {
        System.out.println(employee.getName());
      }
    }*/

    // Lambda
    /*employees.forEach(
    employee -> {
      if (employee.getAge() > 30) {
        System.out.println(employee.getName());
      }
    });*/

    // employees.stream().filter(e->e.getAge()>30).forEach(e->System.out.println(e.getName()));

    /*
    printEmployeesByAge(employees, "Employees over 30", e -> e.getAge() > 30);
    printEmployeesByAge(employees, "Employees 30 and under", e -> e.getAge() <= 30);

    printEmployeesByAge(
        employees,
        "Employees between 20 and 30",
        new Predicate<Employee>() {
          @Override
          public boolean test(Employee employee) {
            return employee.getAge() >= 20 && employee.getAge() <= 30;
          }
        });
  */
    /*IntPredicate gt15 = i -> i > 15;
    IntPredicate lt30 = i -> i < 30;
    System.out.println(gt15.and(lt30).test(50));
    System.out.println(gt15.or(lt30).test(50));

    IntSupplier supplier = ()->new Random().nextInt(1000);

    for (int i = 0; i<10;i++) {
      System.out.println(supplier.getAsInt());
    }*/

    /*employees.forEach(
    employee -> {
      String lastName = employee.getName().substring(employee.getName().indexOf(' ')+1);
      System.out.println(lastName);
    });*/

    Function<Employee, String> getLastName =
        employee -> employee.getName().substring(employee.getName().indexOf(' ') + 1);

    Function<Employee, String> getFirstName =
        employee -> employee.getName().substring(0,employee.getName().indexOf(' '));

    Function<Employee, String> upperCase = employee -> employee.getName().toUpperCase();
    Function<String, String> firstName = name -> name.substring(0, name.indexOf(' '));
    Function<Employee, String> chainedFunction = upperCase.andThen(firstName);
    Function<Employee, String> composedVersion = firstName.compose(upperCase);
    // System.out.println(chainedFunction.apply(john)); // JOHN
    // System.out.println(composedVersion.apply(john)); // JOHN
    /*System.out.println(getAName(getFirstName, john));
    System.out.println(getAName(getLastName, john));*/

    BiFunction<String, Employee, String> concatAge =
        (String name, Employee employee) -> name.concat(" " + employee.getAge());

    String upperName = upperCase.apply(john);
    System.out.println(concatAge.apply(upperName, john)); // JOHN DOE 30

    IntUnaryOperator incBy5 = i->i+5;
    System.out.println(incBy5.applyAsInt(10));

    Consumer<String> c1 = s->s.toUpperCase();
    Consumer<String> c2 = s -> System.out.println(s);
    c1.andThen(c2).accept("Hello world");
  }

  private static String getAName(Function<Employee, String> getName, Employee employee) {
    return getName.apply(employee);
  }

  private static void printEmployeesByAge(
      List<Employee> employees, String ageText, Predicate<Employee> ageCondition) {
    System.out.println(ageText);
    for (Employee employee : employees) {
      if (ageCondition.test(employee)) {
        System.out.println(employee.getName());
      }
    }
  }
}

class Employee {
  private String name;
  private int age;

  public Employee(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
