package Mastery;

// the abstract vehicle thing
public abstract class Vehicle {
    private String make;
    private String model;
    private int yr;
    private double spd; // speed

    public Vehicle(String m, String mod, int y) {
        make = m;
model = mod;
        yr = y;
        spd = 0.0;
    }

// abstract method for driving
    public abstract void drive();

    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return yr; }
public double getSpeed() { return spd; }

// go faster
    public void accelerate(double a) {
        spd += a;
    }

// slow down
    public void brake(double a) {
        spd -= a;
        if (spd < 0) { spd = 0; } // dont go backwards
    }

    public String toString() {
return yr + " " + make + " " + model;
    }
}
