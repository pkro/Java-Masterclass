package de.pkro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Department {
  private String name;
  private List<Employee> employees;

  public Department(String name) {
    this.name = name;
    this.employees = new ArrayList<Employee>();
  }

  public void addEmployee(Employee employee) {
    this.employees.add(employee);
  }

  public List<Employee> getEmployees() {
    return new ArrayList<>(employees);
  }
}
