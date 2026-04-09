package Mastery;

public class Minivan extends Vehicle {
private int seats;

    public Minivan(String mk, String md, int y, int s) {
        super(mk, md, y);
        seats = s;
    }

    @Override
    public void drive() {
System.out.println(toString() + " is taking kids to soccer practice");
    }

// open the doors
    public void openSlidingDoors() {
        System.out.println("sliding doors opened for " + seats + " people");
    }
}
