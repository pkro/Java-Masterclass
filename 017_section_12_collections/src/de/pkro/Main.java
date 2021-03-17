package de.pkro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        /*Theater theater = new Theater("Peers theater", 3, 5);
        theater.reserveSeat("B02");
        theater.reserveSeat("B02");
        if(theater.reserveSeat("A01")) {
            System.out.println("Please pay");
        } else {
            System.out.println("Already reserved, choose another");
        }

        if(theater.reserveSeat("A01")) {
            System.out.println("Please pay");
        } else {
            System.out.println("Already reserved, choose another");
        }*/

        //theater.getSeats();

        /*printList(theater.seats);

        // shallow copy
        List<Theater.Seat> seatsCopy = new ArrayList<Theater.Seat>(theater.seats);
        Collections.reverse(seatsCopy);
        printList(seatsCopy);
        Collections.shuffle(seatsCopy);
        printList(seatsCopy);
        sortList(seatsCopy);
        printList(seatsCopy);*/

        // Comparable part

        Theater theater = new Theater("Peers theater", 3, 5);
        printList(theater.getSeats());

        List<Theater.Seat> seatsCopy = new ArrayList<Theater.Seat>(theater.getSeats());
        seatsCopy.add(theater.new Seat("A14", 99.00));
        Collections.sort(seatsCopy, Theater.PRICE_ORDER);
        printList(seatsCopy);


    }

    public static void printList(Collection<Theater.Seat> seats) {
        for(Theater.Seat s: seats) {
            System.out.print(s.getSeatNumber()+":$" + s.getPrice()+ " ");
        }
        System.out.println();
        System.out.println("===================");
    }

    /*public static void sortList(List<? extends Theater.Seat> list) {
        boolean isSorted = false;
        System.out.print("Sorting");
        while (!isSorted) {
            isSorted = true;
            for(int i=0; i<list.size()-1; i++) {
                System.out.print(".");
                if(list.get(i).compareTo(list.get(i+1)) > 0) {
                    Collections.swap(list, i, i+1);
                    isSorted = false;
                }
            }
        }
        System.out.println();
    }*/
}
