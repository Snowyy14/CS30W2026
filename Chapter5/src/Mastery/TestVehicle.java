package Mastery;

public class TestVehicle {
    public static void main(String[] args) {
Car c = new Car("toyota", "camry", 2011, 15.1);
        Truck t = new Truck("ford", "f150", 2018, 3000);
Minivan m = new Minivan("honda", "odyssey", 2020, 7);

System.out.println("--- car test ---");
        c.drive();
c.accelerate(45);
        c.packTrunk();
System.out.println("car speed: " + c.getSpeed() + " mph");

        System.out.println("\n--- truck test ---");
        t.drive();
t.accelerate(30);
        t.loadCargo();
        System.out.println("truck speed: " + t.getSpeed() + " mph");

System.out.println("\n--- minivan test ---");
        m.drive();
        m.accelerate(55);
m.brake(10);
        m.openSlidingDoors();
        System.out.println("minivan speed is " + m.getSpeed() + " mph");
    }
}
