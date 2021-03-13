package de.pkro;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Node node = null;
        org.w3c.dom.Node anotherNodeType;
        //javafx.scene.Node node = null;

        String varFour = "private to main()";

        ScopeCheck scopeInstance = new ScopeCheck();
        System.out.println("scopeInstance varOne is " + scopeInstance.getVarOne());
        System.out.println(varFour);
        scopeInstance.timesTwo();

        ScopeCheck.InnerClass innerClass = scopeInstance.new InnerClass();
        innerClass.timesTwo();
        // System.out.println(innerClass.varThree); // not accessible here, but in containing class

        // Challenge
        X x = new X(new Scanner(System.in));
        x.x();
    }
}
