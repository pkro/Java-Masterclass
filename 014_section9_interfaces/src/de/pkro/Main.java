package de.pkro;

public class Main {

    public static void main(String[] args) {
        ITelephone timsPhone = new DeskPhone(123);
        ITelephone peersPhone = new MobilePhone(345);

        timsPhone.powerOn();

        timsPhone.callPhone(123);
        timsPhone.dial(123);
        timsPhone.answer();

        peersPhone.powerOn();
        peersPhone.callPhone(345);
        peersPhone.dial(123);
        peersPhone.answer();
    }
}
