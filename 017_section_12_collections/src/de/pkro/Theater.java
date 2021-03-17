package de.pkro;

import java.util.*;

public class Theater {
    private final String theaterName;
    private List<Seat> seats = new ArrayList<>();
    static final Comparator<Seat> PRICE_ORDER = new Comparator<Seat>() {
        @Override
        public int compare(Seat seat1, Seat seat2) {
            if(seat1.getPrice() < seat2.getPrice()) {
                return -1;
            }
            else if(seat1.getPrice() > seat2.getPrice()) {
                return 1;
            }
            return 0;
        }
    };



    public Theater(String theaterName, int numRows, int seatsPerRow) {
        this.theaterName = theaterName;

        int lastRow = 'A' + (numRows - 1);
        for (char row = 'A'; row <= lastRow; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                double price = 12.00;
                if(row < 'D' && seatNum <=0) {
                    price = 14.00;
                } else if(row > 'F' || (seatNum < 4 || seatNum > 9)) {
                    price = 7.00;
                }
                seats.add(new Seat(row + String.format("%02d", seatNum), price));
            }
        }
    }

    public String getTheaterName() {
        return theaterName;
    }

    public boolean reserveSeat(String seatNumber) {
        Seat requestSeat = new Seat(seatNumber, 1.0);
        // instead of looping / comparing, efficient bindarySearch (also see implementation in Collections)
        // can be used now that we imnplemented the Comparable interface in seat:
        int foundSeat = Collections.binarySearch(seats, requestSeat, null);
        if (foundSeat == -1) {
            System.out.println("Seat not found");
            return false;
        }

        return seats.get(foundSeat).reserve();
    }

    public Collection<Seat> getSeats() {
        return seats;
    }

    //public class Seat implements Comparable<Seat> {
    public class Seat {
        private final String seatNumber;
        private double price;
        private boolean reserved = false;

        public Seat(String seatNumber, double price) {
            this.seatNumber = seatNumber;
            this.price = price;
        }

        public boolean isReserved() {
            return reserved;
        }

        public boolean reserve() {
            if (!reserved) {
                System.out.println("Seat " + seatNumber + " reserved");
                reserved = true;
                return true;
            }
            return false;
        }

        public boolean cancel() {
            if (reserved) {
                System.out.println("Seat " + seatNumber + " canceled");
                reserved = false;
                return true;
            }
            return false;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return price;
        }

        /*@Override
        public int compareTo(Seat other) {
            return this.seatNumber.compareToIgnoreCase(other.getSeatNumber());
        }*/
    }
}
