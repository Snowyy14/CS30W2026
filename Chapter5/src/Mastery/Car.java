package Mastery;

public class Car extends Vehicle {
private double trunkSpace; // how big the trunk is

    public Car(String mk, String md, int y, double ts) {
        super(mk, md, y);
        trunkSpace = ts;
    }

    @Override
    public void drive() {
System.out.println(toString() + " is driving down the road vroom");
    }

// put things in the trunk
    public void packTrunk() {
        System.out.println("packing items in the " + trunkSpace + " trunk");
    }
}
