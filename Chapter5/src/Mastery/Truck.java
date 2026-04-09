package Mastery;

public class Truck extends Vehicle {
    private double carryCap; // carrying capacity

    public Truck(String m, String mod, int y, double cc) {
super(m, mod, y);
carryCap = cc;
    }

    @Override
    public void drive() {
      System.out.println(toString() + " is hauling stuff slowly.");
    }

    public void loadCargo() {
        System.out.println("loading " + carryCap + " lbs of cargo in the back");
    }
}
