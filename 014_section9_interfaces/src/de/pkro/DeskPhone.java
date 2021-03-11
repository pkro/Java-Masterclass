package de.pkro;

public class DeskPhone implements ITelephone {
    int myNumber;
    private boolean isRinging;

    public DeskPhone(int myNumber) {
        this.myNumber = myNumber;
        isRinging = false;
    }

    @Override
    public void powerOn() {
        System.out.println("deskphone doesn't need to be powered on");
    }

    @Override
    public void dial(int phoneNumber) {
        System.out.println("Dialing " + phoneNumber);
    }

    @Override
    public void answer() {
        if(isRinging) {
            System.out.println("Answering");
            isRinging = false;
        }
    }

    @Override
    public boolean callPhone(int phoneNumber) {
        if(phoneNumber == myNumber) {
            isRinging = true;
            System.out.println("ring ting");
        } else {
            isRinging = false;
        }
        return isRinging;
    }

    @Override
    public boolean isRinging() {
        return isRinging;
    }
}
