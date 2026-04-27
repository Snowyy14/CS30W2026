/**
 * CarLot.java
 * Author: Ethan
 * Date: April 16, 2026
 * Description: Aggregation class that manages a collection of vehicles on a car lot.
 *              Demonstrates the "has-a" relationship - a CarLot HAS vehicles.
 *              Uses polymorphism since the vehicle list stores Vehicle references
 *              that can actually be Car, Truck, or Minivan objects. Provides methods
 *              to add vehicles, search by make/model, list inventory, and show
 *              stats about the lot.
 */
package Mastery;

import java.util.ArrayList;

public class CarLot {
    // name of the dealership
    private String lotName;

    // polymorphic list - holds any type of Vehicle (Car, Truck, Minivan)
    private ArrayList<Vehicle> inventory;

    /**
     * Constructor - creates a car lot with a name and empty inventory
     * pre: none
     * post: CarLot created with empty inventory list
     */
    public CarLot(String name) {
        this.lotName = name;
        this.inventory = new ArrayList<Vehicle>();
    }

    /**
     * Returns the lot name
     * pre: none
     * post: lot name returned
     */
    public String getLotName() {
        return lotName;
    }

    /**
     * Adds a vehicle to the lot. Works with any Vehicle subclass thanks to
     * polymorphism - you can pass a Car, Truck, or Minivan and it just works.
     * pre: vehicle is not null
     * post: vehicle added to inventory
     */
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
        System.out.println("Added to lot: " + vehicle.getYear() + " " +
                           vehicle.getMake() + " " + vehicle.getModel());
    }

    /**
     * Searches for vehicles by make (like "Toyota" or "Ford").
     * Returns all matches since a lot could have multiple Toyotas for example.
     * pre: none
     * post: list of matching vehicles returned
     */
    public ArrayList<Vehicle> searchByMake(String make) {
        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle v : inventory) {
            if (v.getMake().equalsIgnoreCase(make)) {
                results.add(v);
            }
        }
        return results;
    }

    /**
     * Searches for a specific vehicle by make AND model.
     * Returns the first match or null if nothing found.
     * pre: none
     * post: matching vehicle or null returned
     */
    public Vehicle searchByMakeModel(String make, String model) {
        for (Vehicle v : inventory) {
            if (v.getMake().equalsIgnoreCase(make) &&
                v.getModel().equalsIgnoreCase(model)) {
                return v;
            }
        }
        return null;
    }

    /**
     * Lists all vehicles currently on the lot.
     * Polymorphism means toString() calls the right version for each vehicle type.
     * pre: none
     * post: all vehicles printed to console
     */
    public void listAllVehicles() {
        System.out.println("--- Inventory at " + lotName + " ---");
        if (inventory.isEmpty()) {
            System.out.println("  No vehicles on the lot.");
            return;
        }
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + inventory.get(i));
        }
    }

    /**
     * Lists only the cars on the lot using instanceof to filter
     * pre: none
     * post: all cars printed
     */
    public void listCars() {
        System.out.println("--- Cars at " + lotName + " ---");
        for (Vehicle v : inventory) {
            if (v instanceof Car) {
                System.out.println("  " + v);
            }
        }
    }

    /**
     * Lists only the trucks on the lot
     * pre: none
     * post: all trucks printed
     */
    public void listTrucks() {
        System.out.println("--- Trucks at " + lotName + " ---");
        for (Vehicle v : inventory) {
            if (v instanceof Truck) {
                System.out.println("  " + v);
            }
        }
    }

    /**
     * Lists only the minivans on the lot
     * pre: none
     * post: all minivans printed
     */
    public void listMinivans() {
        System.out.println("--- Minivans at " + lotName + " ---");
        for (Vehicle v : inventory) {
            if (v instanceof Minivan) {
                System.out.println("  " + v);
            }
        }
    }

    /**
     * Returns how many vehicles are on the lot
     * pre: none
     * post: count returned
     */
    public int getVehicleCount() {
        return inventory.size();
    }

    /**
     * Finds the vehicle with the best highway fuel economy on the lot.
     * Useful for customers who do lots of highway driving.
     * pre: inventory is not empty
     * post: most fuel efficient vehicle returned
     */
    public Vehicle getMostFuelEfficient() {
        if (inventory.isEmpty()) return null;
        Vehicle best = inventory.get(0);
        for (Vehicle v : inventory) {
            if (v.getFuelEconomyHwy() > best.getFuelEconomyHwy()) {
                best = v;
            }
        }
        return best;
    }

    /**
     * Returns a summary of the lot
     * pre: none
     * post: formatted string returned
     */
    public String toString() {
        return lotName + " [" + inventory.size() + " vehicles in stock]";
    }
}
