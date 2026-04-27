/**
 * Car.java
 * Author: Ethan
 * Date: April 16, 2026
 * Description: Subclass of Vehicle representing a car. Cars have trunk space
 *              in addition to the inherited vehicle attributes. Implements the
 *              abstract drive() method with car-specific behavior. This is one
 *              of three concrete subclasses that demonstrate polymorphism when
 *              stored together in a CarLot.
 */
package Mastery;

public class Car extends Vehicle {
    // how much trunk space the car has (in cubic feet)
    private double trunkSpace;

    /**
     * Constructor - creates a car with all vehicle specs plus trunk space.
     * pre: none
     * post: Car created with all fields set
     */
    public Car(String make, String model, int year,
               double fuelCity, double fuelHwy,
               int seats, double cargo, double trunkSpace) {
        super(make, model, year, fuelCity, fuelHwy, seats, cargo);
        this.trunkSpace = trunkSpace;
    }

    /**
     * Returns the trunk space
     * pre: none
     * post: trunk space returned
     */
    public double getTrunkSpace() {
        return trunkSpace;
    }

    /**
     * Implements the abstract drive method from Vehicle.
     * Cars cruise down the road smoothly.
     * pre: none
     * post: driving message printed
     */
    @Override
    public void drive() {
        System.out.println(toString() + " is cruising down the road.");
    }

    /**
     * Car-specific method to pack the trunk
     * pre: none
     * post: trunk packing message printed
     */
    public void packTrunk() {
        System.out.println("Packing items into the " + trunkSpace + " cu ft trunk.");
    }

    /**
     * Adds trunk space info to the parent toString
     * pre: none
     * post: formatted string returned
     */
    public String toString() {
        return super.toString() + " | Trunk: " + trunkSpace + " cu ft";
    }
}
