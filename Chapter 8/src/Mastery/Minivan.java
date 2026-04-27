/**
 * Minivan.java
 * Author: Ethan
 * Date: April 16, 2026
 * Description: Subclass of Vehicle representing a minivan. Minivans have sliding
 *              doors and are built for carrying families around. The sliding door
 *              count is the unique attribute that sets minivans apart from cars
 *              and trucks. Implements the abstract drive() method with minivan-
 *              specific behavior.
 */
package Mastery;

public class Minivan extends Vehicle {
    // number of sliding doors (usually 1 or 2)
    private int slidingDoors;

    /**
     * Constructor - creates a minivan with all vehicle specs plus sliding door count.
     * pre: none
     * post: Minivan created with all fields set
     */
    public Minivan(String make, String model, int year,
                   double fuelCity, double fuelHwy,
                   int seats, double cargo, int slidingDoors) {
        super(make, model, year, fuelCity, fuelHwy, seats, cargo);
        this.slidingDoors = slidingDoors;
    }

    /**
     * Returns the number of sliding doors
     * pre: none
     * post: sliding door count returned
     */
    public int getSlidingDoors() {
        return slidingDoors;
    }

    /**
     * Implements abstract drive from Vehicle.
     * Minivans are all about the family trips.
     * pre: none
     * post: driving message printed
     */
    @Override
    public void drive() {
        System.out.println(toString() + " is taking the family to soccer practice.");
    }

    /**
     * Minivan-specific method to open the sliding doors
     * pre: none
     * post: door opening message printed
     */
    public void openSlidingDoors() {
        System.out.println("Opening " + slidingDoors + " sliding door(s) for easy passenger access.");
    }

    /**
     * Adds sliding door info to the parent toString
     * pre: none
     * post: formatted string returned
     */
    public String toString() {
        return super.toString() + " | Sliding Doors: " + slidingDoors;
    }
}
