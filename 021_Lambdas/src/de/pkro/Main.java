package de.pkro;

import java.util.*;

interface UpperConcat {
  public String upperAndConcat(String s1, String s2);
}

public class Main {

  public static void main(String[] args) {
    // Anonymous class
    new Thread(new Runnable() {
      @Override
      public void run() {
        // this is the only non-boilerplate code
        System.out.println("printing from the Runnable anon");
      }
    }).start();

    // as a Lambda expression:
    new Thread(() -> System.out.println("Printing from lambda runnable")).start();

    // Multi line lambda (shouldn't really be done imho

    new Thread(() -> {
      System.out.println("Printing from multiline lambda runnable");
      System.out.println("Printing more from multiline  lambda runnable");
    }).start();

    Employee john = new Employee("John Doe", 30);
    Employee tim = new Employee("Tim Doe", 21);
    Employee jack = new Employee("Jack Doe", 21);
    Employee snow = new Employee("Snow White", 22);
    Employee abraham = new Employee("Abraham Abrahamson", 1000);

    List<Employee> employees = new ArrayList<>();
    employees.add(john);
    employees.add(tim);
    employees.add(jack);
    employees.add(snow);
    employees.add(abraham);

    employees.forEach(employee -> System.out.println(employee.getName()));

  /*

    // Comparator using an anonymous class
    Collections.sort(employees, new Comparator<Employee>() {
      @Override
      public int compare(Employee employee1, Employee employee2) {
        return employee1.getName().compareTo(employee2.getName());
      }
    });

    // Comparator using a Lambda expression
    Collections.sort(employees, (e1, e2) -> e1.getName().compareTo(e2.getName()));

    for (Employee employee : employees) {
      System.out.println(employee.getName());
    }

    // anonymous class implementing an interface
    String sillyString = doStringStuff(new UpperConcat() {
      @Override
      public String upperAndConcat(String s1, String s2) {
        return (s1 + s2).toUpperCase(Locale.ROOT);
      }
    }, employees.get(0).getName(), employees.get(1).getName());

    System.out.println(sillyString);

    // with Lambda
    UpperConcat uc = (s1, s2) -> (s1 + s2).toUpperCase();
    String sillyString2 = doStringStuff(uc, "one String", "And another");

    System.out.println(sillyString2);

    // or inline with Lambda
    String sillyString3 = doStringStuff((s1, s2) -> (s1 + s2).toUpperCase(Locale.ROOT),
                                        employees.get(2).getName(),
                                        employees.get(3).getName());

    System.out.println(sillyString3);

    UpperConcat uc2 = (s1, s2) -> {
      String result = (s1 + s2).toUpperCase();
      return result;
    };

    AnotherClass anotherClass = new AnotherClass();
    //System.out.println(anotherClass.doSomething());
    anotherClass.printValue();
*/
  }

  public final static String doStringStuff(UpperConcat uc, String s1, String s2) {
    return uc.upperAndConcat(s1, s2);
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

class AnotherClass {
  public String doSomething() {
    /*System.out.println("the AnotherClass' name is: " + getClass().getSimpleName());
    return Main.doStringStuff(new UpperConcat() {
      @Override
      public String upperAndConcat(String s1, String s2) {
        // no name as it's an anonymous class
        System.out.println("the anonymous classes name is: " + getClass().getSimpleName());
        return (s1 + s2).toUpperCase();
      }
    }, "String1", "String2");*/

    /*UpperConcat uc = (s1,s2) -> {
      // the this is still bound to the parent class
      System.out.println("the lambda class is " + getClass().getSimpleName());
      return (s1 + s2).toUpperCase();
    };*/
    int i = 0;

    UpperConcat uc = (s1, s2) -> {
      System.out.println("The lambda class is " + getClass().getSimpleName());
      System.out.println("i in the lambda = " + i);
      //System.out.println("i within lambda class = " + i);
      return (s1 + s2).toUpperCase();
    };

    /*UpperConcat uc = new UpperConcat() {
      @Override
      public String upperAndConcat(String s1, String s2) {
        System.out.println("i within anonymous class = " + i);
        return (s1 + s2).toUpperCase();
      }
    };*/

    return Main.doStringStuff(uc, "String1", "String2");
  }


  public void printValue() {
    int number = 25;
    Runnable r = () -> {
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("the value is " + number);
    };
    new Thread(r).start();
    // even though the function terminates before the thread prints the number, it will still print 25
  }
}

