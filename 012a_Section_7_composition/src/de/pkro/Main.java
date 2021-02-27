package de.pkro;

public class Main {

    public static void main(String[] args) {
        /*
        Dimensions dimensions = new Dimensions(20, 20, 5);
        Case theCase = new Case("22b", "Dell", "240", dimensions);
        Monitor theMonitor = new Monitor("27incher", "Iiyama", 27, new Resolution(2540,1440));
        Motherboard motherboard = new Motherboard("apc200","apc", 4, 2, "1.5");
        PC pc = new PC(theCase,theMonitor,motherboard);

        pc.getTheCase().pressPowerButton();
        pc.getMotherboard().loadProgram("Pitfall");
        pc.getMonitor().drawPixelAt(20, 20, "green");
        pc.getTheCase().pressPowerButton();*/

        Dimensions dimensions = new Dimensions(5, 2.2, 6.5);
        Lamp lamp = new Lamp(300);
        Couch couch = new Couch(3,true);
        Room room = new Room(dimensions, lamp, couch);

        System.out.println("Room has a living space of " + room.getLivingSpace());
        System.out.println("Light is " + (room.getCeilingLamp().isOn() ? "on" : "off"));
        room.getCeilingLamp().setOn(true);
        room.toggleSleepMode();
        room.toggleSleepMode();
        room.getCeilingLamp().switchOnOff();
        room.getCeilingLamp().setOn(false);

    }


}
