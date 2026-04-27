/**
 * Truck.java
 * Author: Ethan
 * Date: April 16, 2026
 * Description: Subclass of Vehicle representing a truck. Trucks have a carrying
 *              capacity (payload in lbs) in addition to the inherited vehicle
 *              attributes. Implements the abstract drive() method with truck-specific
 *              behavior. Trucks are the heavy-duty option in the vehicle hierarchy.
 */
package Mastery;

public class Truck extends Vehicle {
    // max carrying capacity in pounds
    private double carryingCapacity;

    /**
     * Constructor - creates a truck with all vehicle specs plus carrying capacity.
     * pre: none
     * post: Truck created with all fields set
     */
    public Truck(String make, String model, int year,
                 double fuelCity, double fuelHwy,
                 int seats, double cargo, double carryingCapacity) {
        super(make, model, year, fuelCity, fuelHwy, seats, cargo);
        this.carryingCapacity = carryingCapacity;
    }

    /**
     * Returns the max carrying capacity
     * pre: none
     * post: carrying capacity returned
     */
    public double getCarryingCapacity() {
        return carryingCapacity;
    }

    /**
     * Implements abstract drive from Vehicle.
     * Trucks haul stuff and go a bit slower because of the weight.
     * pre: none
     * post: driving message printed
     */
    @Override
    public void drive() {
        System.out.println(toString() + " is hauling a heavy load down the highway.");
    }

    /**
     * Truck-specific method to load cargo into the bed
     * pre: none
     * post: loading message printed
     */
    public void loadCargo() {
        System.out.println("Loading up to " + carryingCapacity + " lbs of cargo into the truck bed.");
    }

    /**
     * Adds carrying capacity info to the parent toString
     * pre: none
     * post: formatted string returned
     */
    public String toString() {
        return super.toString() + " | Payload: " + carryingCapacity + " lbs";
    }
}
