package de.pkro;

public class Main {

    public static void main(String[] args) {
        /*Car porsche = new Car();
        System.out.println(porsche.getModel());
        porsche.setModel("porschE");
        System.out.println(porsche.getModel());*/

        //BankAccount myAccount = new BankAccount(12345, 500, "Peer", "a@b.de", "0815");
        /*BankAccount myAccount = new BankAccount();
        System.out.println(myAccount.toString());
        System.out.println(myAccount.getEmail());

        myAccount.deposit(300);
        System.out.println(myAccount.withdraw(400)); // should be 300
        System.out.println(myAccount.withdraw(200)); // should be 100*/

        /*VipCustomer v1 = new VipCustomer();
        System.out.println(v1.getName() + " " + v1.getEmail() + v1.getCreditLimit());

        VipCustomer v2 = new VipCustomer("Peer", 3000);
        System.out.println(v2.getName() + " " + v2.getEmail() + v2.getCreditLimit());

        VipCustomer v3 = new VipCustomer("Peerky", 2000, "a@b.cd");
        System.out.println(v3.getName() + " " + v3.getEmail() + v3.getCreditLimit());*/

        /*Point a = new Point(6,5);
        Point b = new Point(3,1);
        System.out.println("distance(0,0)=" + a.distance());
        System.out.println("distance(b)=" + a.distance(b));
        System.out.println("distance(2,2)=" + a.distance(2,2));*/

        /*Animal a = new Animal("ani",1,1,5,35);
        Dog d = new Dog("Wuffi", 30, 20,2,1,4, 32, "fluffy");
        d.bark();
        d.eat();
        d.eat("wurst");
        d.move(30);
        d.move(5);
        a.eat();
        a.move(5);*/

        /*StaticVarTest a = new StaticVarTest();
        StaticVarTest b = new StaticVarTest();
        a.maxSpeed = 5;
        System.out.println(b.maxSpeed); // 5*/

        /*Vehicle v = new Vehicle();

        ChallengeCar c = new ChallengeCar();

        c.setSpeed(500);
        c.setSpeed(250);
        System.out.println("Car moving towards "+c.getDirection());
        c.steer(-90);
        c.steer(-90);
        c.steer(-90);
        c.steer(-90);
        c.steer(-90);
        System.out.println("Car moving towards "+c.getDirection() + " at " + c.getSpeed() + "kmh");

        Kaefer k = new Kaefer();
        k.setSpeed(200);*/

        /*Cylinder c = new Cylinder(2,5);
        System.out.println(c.getVolume());*/

        Cuboid c = new Cuboid(5,10,5);
        System.out.println(c.getVolume());

    }

}
