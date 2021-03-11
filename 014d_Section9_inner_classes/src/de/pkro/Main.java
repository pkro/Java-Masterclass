package de.pkro;

import java.awt.image.BandCombineOp;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        /*******************************
         * Gearbox example
         */
        //Gearbox mcLaren = new Gearbox(6);
        // explicit instantiation of inner class (inner class must be public for this:
        // AVOID
        // Gearbox.Gear first = mcLaren.new Gear(1, 12.3);
        // WRONG
        // Gearbox.Gear second = Gearbox.new Gear(1, 2.2); // not possible, instance is necessary

        /*mcLaren.addGear(1, 5.3);
        mcLaren.addGear(2, 10.6);
        mcLaren.addGear(3, 15.9);*/

        /*mcLaren.operateClutch(true);
        mcLaren.changeGear(1);
        mcLaren.operateClutch(false);
        System.out.println(mcLaren.wheelSpeed(1000));
        mcLaren.changeGear(2);
        System.out.println(mcLaren.wheelSpeed(3000));
        mcLaren.operateClutch(true);
        mcLaren.changeGear(3);
        mcLaren.operateClutch(false);
        System.out.println(mcLaren.wheelSpeed(6000));*/

        // inner interface section

        /****************************************
         * button example (inner interface / class)
         */

        Button btnPrint = new Button("yay button print");

        class ClickListener implements Button.OnClickListener {
            public ClickListener() {
                System.out.println("I've been attached");
            }

            @Override
            public void onClick(String title) {
                System.out.println(title + " was clicked");
            }
        }

        btnPrint.setOnClickListener(new ClickListener());
        //listen(btnPrint);

        Button anotherButton = new Button("this one uses an anonymous class");

        anotherButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(String title) {
                System.out.println("Anonymous event listener class onClick event");
            }
        });
        listen(anotherButton);

    }

    private static void listen(Button button) {
        boolean quit = false;
        while(!quit) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    quit = true;
                    break;
                case 1:
                    button.onClick();
                    break;
            }
        }

    }
}
