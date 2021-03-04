package de.pkro;

import javax.print.attribute.IntegerSyntax;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Course code
        /*ArrayList<Integer> intList = new ArrayList<Integer>();
        intList.add(Integer.valueOf(10)); // unnecessary explicit boxing
        intList.add(10); // autoboxing;
        int unboxed = intList.get(1); // automatically unboxes to primitive type
        System.out.println(unboxed);*/

        // Challenge code

        Bank bank = new Bank("National Australia Bank");

        bank.addBranch("Adelaide");

        bank.addCustomer("Adelaide", "Tim", 50.05);
        bank.addCustomer("Adelaide", "Mike", 175.34);
        bank.addCustomer("Adelaide", "Percy", 220.12);

        bank.addCustomerTransaction("Adelaide", "Tim", 44.22);
        bank.addCustomerTransaction("Adelaide", "Tim", 12.44);
        bank.addCustomerTransaction("Adelaide", "Mike", 1.65);

        bank.listCustomers("Adelaide", true);

    }

}
