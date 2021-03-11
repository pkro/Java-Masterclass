package de.pkro;

public class MobilePhone implements ITelephone {
    int myNumber;
    private boolean isRinging;
    private boolean isOn;

    public MobilePhone(int myNumber) {
        this.myNumber = myNumber;
        isOn = true;
        isRinging = false;
    }

    @Override
    public void powerOn() {
        isOn = true;
    }

    @Override
    public void dial(int phoneNumber) {
        if(isOn) {
            System.out.println("Dialing " + phoneNumber);
        }
        else {
            System.out.println("Phone not turned on");
        }
    }

    @Override
    public void answer() {
        if(isRinging) {
            System.out.println("Answering the mobile phone");
            isRinging = false;
        }
    }

    @Override
    public boolean callPhone(int phoneNumber) {
        if(phoneNumber == myNumber && isOn) {
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
