package de.pkro;

import java.util.ArrayList;

public class Branch {
    private String name;
    private ArrayList<Customer> customers;

    public Branch(String name) {
        this.name = name;
        customers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Customer> getCustomers(){
        return customers;
    }

    public boolean newCustomer(String name, double initialTransaction) {
        if(findCustomer(name) == null) {
            Customer cust = new Customer(name, initialTransaction);
            customers.add(cust);
            return true;
        }
        return false;
    }

    public boolean addCustomerTransaction(String name, double transaction) {
        Customer customer = findCustomer(name);
        if(customer != null) {
            customer.addTransaction(transaction);
            return true;
        }
        return false;
    }

    private Customer findCustomer(String name) {
        for(Customer customer: customers) {
            if(customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }
}
