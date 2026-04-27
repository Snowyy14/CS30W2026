/**
 * Vehicle.java
 * Author: Ethan
 * Date: April 16, 2026
 * Description: Abstract base class for all vehicles. Contains common attributes
 *              like make, model, year, speed, and the new fuel economy / capacity
 *              fields (fuelEconomyCity, fuelEconomyHwy, seatingCapacity, cargoVolume).
 *              The abstract drive() method forces each subclass to define its own
 *              driving behavior, showing how abstract classes work as blueprints.
 *              You can't create a plain Vehicle object - it has to be a Car, Truck, etc.
 */
package Mastery;

public abstract class Vehicle {
    // basic vehicle identity
    private String make;
    private String model;
    private int year;
    private double speed;

    // new fields required by the assignment
    // fuel economy in MPG for city and highway driving
    private double fuelEconomyCity;
    private double fuelEconomyHwy;

    // how many people can fit and how much cargo space there is
    private int seatingCapacity;
    private double cargoVolume; // in cubic feet

    /**
     * Constructor - sets up a vehicle with all its specs.
     * Speed starts at 0 because the vehicle isn't moving yet.
     * pre: none
     * post: Vehicle created with all attributes set, speed at 0
     */
    public Vehicle(String make, String model, int year,
                   double fuelEconomyCity, double fuelEconomyHwy,
                   int seatingCapacity, double cargoVolume) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.speed = 0.0;
        this.fuelEconomyCity = fuelEconomyCity;
        this.fuelEconomyHwy = fuelEconomyHwy;
        this.seatingCapacity = seatingCapacity;
        this.cargoVolume = cargoVolume;
    }

    /**
     * Abstract method - each vehicle type drives differently.
     * Car cruises down the road, truck hauls stuff, minivan takes kids around.
     */
    public abstract void drive();

    // --- Getters ---

    /** Returns the make (manufacturer) */
    public String getMake() { return make; }

    /** Returns the model name */
    public String getModel() { return model; }

    /** Returns the model year */
    public int getYear() { return year; }

    /** Returns current speed */
    public double getSpeed() { return speed; }

    /** Returns city fuel economy in MPG */
    public double getFuelEconomyCity() { return fuelEconomyCity; }

    /** Returns highway fuel economy in MPG */
    public double getFuelEconomyHwy() { return fuelEconomyHwy; }

    /** Returns how many passengers can fit */
    public int getSeatingCapacity() { return seatingCapacity; }

    /** Returns cargo volume in cubic feet */
    public double getCargoVolume() { return cargoVolume; }

    /**
     * Increases the speed by the given amount (like pressing the gas)
     * pre: amount should be positive
     * post: speed increased
     */
    public void accelerate(double amount) {
        speed += amount;
    }

    /**
     * Decreases the speed (like pressing the brake pedal).
     * Won't go below 0 because you can't drive backwards by braking.
     * pre: amount should be positive
     * post: speed decreased, minimum 0
     */
    public void brake(double amount) {
        speed -= amount;
        if (speed < 0) {
            speed = 0; // can't go negative
        }
    }

    /**
     * Two vehicles are equal if they have the same make, model, and year.
     * pre: none
     * post: true if same vehicle identity, false otherwise
     */
    public boolean equals(Object obj) {
        if (obj instanceof Vehicle) {
            Vehicle other = (Vehicle) obj;
            return this.make.equalsIgnoreCase(other.make) &&
                   this.model.equalsIgnoreCase(other.model) &&
                   this.year == other.year;
        }
        return false;
    }

    /**
     * Returns a formatted string with the vehicle's key info
     * pre: none
     * post: formatted string returned
     */
    public String toString() {
        return year + " " + make + " " + model +
               " [City: " + fuelEconomyCity + " mpg" +
               ", Hwy: " + fuelEconomyHwy + " mpg" +
               ", Seats: " + seatingCapacity +
               ", Cargo: " + cargoVolume + " cu ft]";
    }
}
