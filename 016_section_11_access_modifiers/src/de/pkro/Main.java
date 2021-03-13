package de.pkro;

public class Main {
    public static int multiplier = 7;
    public static void main(String[] args) {

        // Access modifiers part
	    /*Account account = new Account("Peers account");
        account.deposit(1000);
        account.withdraw(500);
        account.withdraw(-200);
        account.deposit(-20);
        account.calculateBalance();*/

        // static
        /*StaticTest a = new StaticTest("a");
        System.out.println(a.getName() + " is instance nr " + StaticTest.getNumInstances());
        StaticTest b = new StaticTest("b");
        System.out.println(b.getName() + " is instance nr " + StaticTest.getNumInstances());
        StaticTest c = new StaticTest("c");
        System.out.println(c.getName() + " is instance nr " + StaticTest.getNumInstances());*/

        //System.out.println(multiply(5));

        /*SomeClass a = new SomeClass("a");
        SomeClass b = new SomeClass("b");
        SomeClass c = new SomeClass("c");

        System.out.println(Math.PI);*/

/*        int pw = 4343;
        //Password password = new Password(pw);
        Password password = new ExtendedPassword(pw); // compromised because overrides encryption, solved by making this illegal by declaring storePassword final
        password.storePassword();
        password.letMeIn(1);
        password.letMeIn(1223223);
        password.letMeIn(4343);*/

        StaticInitializationBlockTest sib = new StaticInitializationBlockTest();
        sib.someMethod();

    }

    // public int multiply(int num) { // doesn't work
    public static int multiply(int num) {
        return num * multiplier;
    }
}
