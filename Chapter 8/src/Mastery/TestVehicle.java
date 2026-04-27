/**
 * TestVehicle.java
 * Author: Ethan
 * Date: April 16, 2026
 * Description: Test application for the Vehicle inheritance hierarchy and the
 *              CarLot aggregation class. Creates Car, Truck, and Minivan objects
 *              with realistic specs including the new fuel economy and capacity
 *              fields, adds them to a CarLot, and tests polymorphism, searching,
 *              filtering by type, and finding the most fuel efficient vehicle.
 *              Demonstrates abstract classes, inheritance, and aggregation.
 */
package Mastery;

import java.util.ArrayList;

public class TestVehicle {

    public static void main(String[] args) {
        System.out.println("=== Vehicle & CarLot Testing Application ===\n");

        // --- Create vehicles with realistic specs ---
        // Car(make, model, year, cityMPG, hwyMPG, seats, cargoVol, trunkSpace)
        Car car1 = new Car("Toyota", "Camry", 2024, 28, 39, 5, 15.1, 15.1);
        Car car2 = new Car("Honda", "Civic", 2023, 31, 40, 5, 14.8, 14.8);

        // Truck(make, model, year, cityMPG, hwyMPG, seats, cargoVol, carryingCap)
        Truck truck1 = new Truck("Ford", "F-150", 2024, 20, 26, 6, 77.4, 3325);
        Truck truck2 = new Truck("Chevrolet", "Silverado", 2023, 19, 24, 6, 71.7, 2280);

        // Minivan(make, model, year, cityMPG, hwyMPG, seats, cargoVol, slidingDoors)
        Minivan van1 = new Minivan("Honda", "Odyssey", 2024, 19, 28, 8, 158.0, 2);
        Minivan van2 = new Minivan("Toyota", "Sienna", 2024, 36, 36, 8, 101.0, 2);

        // --- Test individual vehicle info ---
        System.out.println("--- Vehicle Details ---");
        System.out.println("Car 1: " + car1);
        System.out.println("Car 2: " + car2);
        System.out.println("Truck 1: " + truck1);
        System.out.println("Van 1: " + van1);

        // --- Test the new fuel economy and capacity getters ---
        System.out.println("\n--- New Field Testing ---");
        System.out.println("Camry city mpg: " + car1.getFuelEconomyCity());
        System.out.println("Camry hwy mpg: " + car1.getFuelEconomyHwy());
        System.out.println("F-150 seating: " + truck1.getSeatingCapacity());
        System.out.println("Odyssey cargo volume: " + van1.getCargoVolume() + " cu ft");

        // --- Test abstract drive method (polymorphism in action) ---
        System.out.println("\n--- Drive Method (Polymorphism) ---");
        car1.drive();
        truck1.drive();
        van1.drive();

        // --- Test specific subclass methods ---
        System.out.println("\n--- Subclass-Specific Methods ---");
        car1.packTrunk();
        truck1.loadCargo();
        van1.openSlidingDoors();

        // --- Test accelerate and brake ---
        System.out.println("\n--- Speed Testing ---");
        car1.accelerate(60);
        System.out.println("Camry speed after accelerating 60: " + car1.getSpeed() + " mph");
        car1.brake(25);
        System.out.println("Camry speed after braking 25: " + car1.getSpeed() + " mph");
        car1.brake(100); // try to brake more than current speed
        System.out.println("Camry speed after heavy brake: " + car1.getSpeed() + " mph (min 0)");

        // --- Test equals ---
        System.out.println("\n--- Equals Testing ---");
        Car car3 = new Car("Toyota", "Camry", 2024, 28, 39, 5, 15.1, 15.1);
        System.out.println("car1 equals car3 (same make/model/year)? " + car1.equals(car3));
        System.out.println("car1 equals car2 (Camry vs Civic)? " + car1.equals(car2));
        System.out.println("car1 equals truck1 (Car vs Truck)? " + car1.equals(truck1));

        // --- Test CarLot aggregation class ---
        System.out.println("\n--- CarLot Aggregation Class ---");
        CarLot lot = new CarLot("Westview Auto Sales");
        System.out.println("Created: " + lot.getLotName() + "\n");

        // add all vehicles to the lot - polymorphism lets us add any vehicle type
        lot.addVehicle(car1);
        lot.addVehicle(car2);
        lot.addVehicle(truck1);
        lot.addVehicle(truck2);
        lot.addVehicle(van1);
        lot.addVehicle(van2);

        // show full inventory
        System.out.println();
        lot.listAllVehicles();

        // filter by type
        System.out.println();
        lot.listCars();
        System.out.println();
        lot.listTrucks();
        System.out.println();
        lot.listMinivans();

        // search by make
        System.out.println("\n--- Search by Make: Toyota ---");
        ArrayList<Vehicle> toyotas = lot.searchByMake("Toyota");
        for (Vehicle v : toyotas) {
            System.out.println("  " + v);
        }

        // search by make and model
        System.out.println("\n--- Search by Make/Model: Ford F-150 ---");
        Vehicle found = lot.searchByMakeModel("Ford", "F-150");
        System.out.println("  " + (found != null ? found : "Not found"));

        // most fuel efficient
        System.out.println("\n--- Most Fuel Efficient (Highway) ---");
        Vehicle efficient = lot.getMostFuelEfficient();
        System.out.println("  " + efficient);

        // lot summary
        System.out.println("\n--- Lot Summary ---");
        System.out.println(lot);
        System.out.println("Total vehicles: " + lot.getVehicleCount());

        System.out.println("\n=== All Vehicle Tests Complete ===");
    }
}

/*
 * ======================== SCREEN DUMP ========================
 *
 * === Vehicle & CarLot Testing Application ===
 *
 * --- Vehicle Details ---
 * Car 1: 2024 Toyota Camry [City: 28.0 mpg, Hwy: 39.0 mpg, Seats: 5, Cargo: 15.1 cu ft] | Trunk: 15.1 cu ft
 * Car 2: 2023 Honda Civic [City: 31.0 mpg, Hwy: 40.0 mpg, Seats: 5, Cargo: 14.8 cu ft] | Trunk: 14.8 cu ft
 * Truck 1: 2024 Ford F-150 [City: 20.0 mpg, Hwy: 26.0 mpg, Seats: 6, Cargo: 77.4 cu ft] | Payload: 3325.0 lbs
 * Van 1: 2024 Honda Odyssey [City: 19.0 mpg, Hwy: 28.0 mpg, Seats: 8, Cargo: 158.0 cu ft] | Sliding Doors: 2
 *
 * --- New Field Testing ---
 * Camry city mpg: 28.0
 * Camry hwy mpg: 39.0
 * F-150 seating: 6
 * Odyssey cargo volume: 158.0 cu ft
 *
 * --- Drive Method (Polymorphism) ---
 * 2024 Toyota Camry [City: 28.0 mpg, Hwy: 39.0 mpg, Seats: 5, Cargo: 15.1 cu ft] | Trunk: 15.1 cu ft is cruising down the road.
 * 2024 Ford F-150 [City: 20.0 mpg, Hwy: 26.0 mpg, Seats: 6, Cargo: 77.4 cu ft] | Payload: 3325.0 lbs is hauling a heavy load down the highway.
 * 2024 Honda Odyssey [City: 19.0 mpg, Hwy: 28.0 mpg, Seats: 8, Cargo: 158.0 cu ft] | Sliding Doors: 2 is taking the family to soccer practice.
 *
 * --- Subclass-Specific Methods ---
 * Packing items into the 15.1 cu ft trunk.
 * Loading up to 3325.0 lbs of cargo into the truck bed.
 * Opening 2 sliding door(s) for easy passenger access.
 *
 * --- Speed Testing ---
 * Camry speed after accelerating 60: 60.0 mph
 * Camry speed after braking 25: 35.0 mph
 * Camry speed after heavy brake: 0.0 mph (min 0)
 *
 * --- Equals Testing ---
 * car1 equals car3 (same make/model/year)? true
 * car1 equals car2 (Camry vs Civic)? false
 * car1 equals truck1 (Car vs Truck)? false
 *
 * --- CarLot Aggregation Class ---
 * Created: Westview Auto Sales
 *
 * Added to lot: 2024 Toyota Camry
 * Added to lot: 2023 Honda Civic
 * Added to lot: 2024 Ford F-150
 * Added to lot: 2023 Chevrolet Silverado
 * Added to lot: 2024 Honda Odyssey
 * Added to lot: 2024 Toyota Sienna
 *
 * --- Inventory at Westview Auto Sales ---
 *   1. 2024 Toyota Camry [City: 28.0 mpg, Hwy: 39.0 mpg, Seats: 5, Cargo: 15.1 cu ft] | Trunk: 15.1 cu ft
 *   2. 2023 Honda Civic [City: 31.0 mpg, Hwy: 40.0 mpg, Seats: 5, Cargo: 14.8 cu ft] | Trunk: 14.8 cu ft
 *   3. 2024 Ford F-150 [City: 20.0 mpg, Hwy: 26.0 mpg, Seats: 6, Cargo: 77.4 cu ft] | Payload: 3325.0 lbs
 *   4. 2023 Chevrolet Silverado [City: 19.0 mpg, Hwy: 24.0 mpg, Seats: 6, Cargo: 71.7 cu ft] | Payload: 2280.0 lbs
 *   5. 2024 Honda Odyssey [City: 19.0 mpg, Hwy: 28.0 mpg, Seats: 8, Cargo: 158.0 cu ft] | Sliding Doors: 2
 *   6. 2024 Toyota Sienna [City: 36.0 mpg, Hwy: 36.0 mpg, Seats: 8, Cargo: 101.0 cu ft] | Sliding Doors: 2
 *
 * --- Cars at Westview Auto Sales ---
 *   2024 Toyota Camry [City: 28.0 mpg, Hwy: 39.0 mpg, Seats: 5, Cargo: 15.1 cu ft] | Trunk: 15.1 cu ft
 *   2023 Honda Civic [City: 31.0 mpg, Hwy: 40.0 mpg, Seats: 5, Cargo: 14.8 cu ft] | Trunk: 14.8 cu ft
 *
 * --- Trucks at Westview Auto Sales ---
 *   2024 Ford F-150 [City: 20.0 mpg, Hwy: 26.0 mpg, Seats: 6, Cargo: 77.4 cu ft] | Payload: 3325.0 lbs
 *   2023 Chevrolet Silverado [City: 19.0 mpg, Hwy: 24.0 mpg, Seats: 6, Cargo: 71.7 cu ft] | Payload: 2280.0 lbs
 *
 * --- Minivans at Westview Auto Sales ---
 *   2024 Honda Odyssey [City: 19.0 mpg, Hwy: 28.0 mpg, Seats: 8, Cargo: 158.0 cu ft] | Sliding Doors: 2
 *   2024 Toyota Sienna [City: 36.0 mpg, Hwy: 36.0 mpg, Seats: 8, Cargo: 101.0 cu ft] | Sliding Doors: 2
 *
 * --- Search by Make: Toyota ---
 *   2024 Toyota Camry [City: 28.0 mpg, Hwy: 39.0 mpg, Seats: 5, Cargo: 15.1 cu ft] | Trunk: 15.1 cu ft
 *   2024 Toyota Sienna [City: 36.0 mpg, Hwy: 36.0 mpg, Seats: 8, Cargo: 101.0 cu ft] | Sliding Doors: 2
 *
 * --- Search by Make/Model: Ford F-150 ---
 *   2024 Ford F-150 [City: 20.0 mpg, Hwy: 26.0 mpg, Seats: 6, Cargo: 77.4 cu ft] | Payload: 3325.0 lbs
 *
 * --- Most Fuel Efficient (Highway) ---
 *   2023 Honda Civic [City: 31.0 mpg, Hwy: 40.0 mpg, Seats: 5, Cargo: 14.8 cu ft] | Trunk: 14.8 cu ft
 *
 * --- Lot Summary ---
 * Westview Auto Sales [6 vehicles in stock]
 * Total vehicles: 6
 *
 * === All Vehicle Tests Complete ===
 *
 * ======================== END SCREEN DUMP ========================
 */
