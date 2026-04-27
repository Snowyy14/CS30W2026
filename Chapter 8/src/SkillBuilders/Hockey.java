/**
 * Hockey.java
 * Author: Ethan
 * Date: April 16, 2026
 * Description: Test application for the Puck class. Creates multiple pucks with
 *              different weights to test standard vs youth division detection,
 *              the equals method, compareTo from Comparable, and the full
 *              inheritance chain (Circle -> Disk -> Puck). Also tests edge cases
 *              like non-regulation pucks.
 */
package SkillBuilders;

public class Hockey {

    public static void main(String[] args) {
        System.out.println("=== Hockey Puck Testing Application ===\n");

        // create a few pucks with different weights to test the division logic
        Puck standardPuck1 = new Puck(5.5);  // top end of standard range
        Puck standardPuck2 = new Puck(5.0);  // bottom end of standard range
        Puck youthPuck1 = new Puck(4.0);     // bottom end of youth range
        Puck youthPuck2 = new Puck(4.5);     // top end of youth range
        Puck oddPuck = new Puck(6.0);        // too heavy, should be non-regulation
        Puck matchPuck = new Puck(5.5);      // same weight as standardPuck1 for equals test

        // --- Test basic puck info ---
        System.out.println("--- Puck Details ---");
        System.out.println("Puck 1: " + standardPuck1);
        System.out.println("Puck 2: " + standardPuck2);
        System.out.println("Puck 3: " + youthPuck1);
        System.out.println("Puck 4: " + youthPuck2);
        System.out.println("Puck 5: " + oddPuck);
        System.out.println("Puck 6: " + matchPuck);

        // --- Test getDivision ---
        System.out.println("\n--- Division Check ---");
        System.out.println("Puck 1 division: " + standardPuck1.getDivision());
        System.out.println("Puck 3 division: " + youthPuck1.getDivision());
        System.out.println("Puck 5 division: " + oddPuck.getDivision());

        // --- Test getWeight ---
        System.out.println("\n--- Weight Check ---");
        System.out.println("Puck 1 weight: " + standardPuck1.getWeight() + " oz");
        System.out.println("Puck 4 weight: " + youthPuck2.getWeight() + " oz");

        // --- Test inherited methods from Disk and Circle ---
        System.out.println("\n--- Inherited Methods (from Circle and Disk) ---");
        System.out.println("Puck 1 radius: " + standardPuck1.getRadius() + " inches");
        System.out.println("Puck 1 thickness: " + standardPuck1.getThickness() + " inches");
        System.out.println("Puck 1 area (face): " + String.format("%.2f", standardPuck1.area()) + " sq inches");
        System.out.println("Puck 1 volume: " + String.format("%.2f", standardPuck1.volume()) + " cubic inches");

        // --- Test equals method ---
        System.out.println("\n--- Equals Testing ---");
        System.out.println("Puck 1 equals Puck 6 (both 5.5 oz)? " + standardPuck1.equals(matchPuck));
        System.out.println("Puck 1 equals Puck 2 (5.5 vs 5.0)? " + standardPuck1.equals(standardPuck2));
        System.out.println("Puck 3 equals Puck 4 (4.0 vs 4.5)? " + youthPuck1.equals(youthPuck2));

        // --- Test Comparable (compareTo) ---
        System.out.println("\n--- Comparable Testing ---");
        int result1 = standardPuck1.compareTo(standardPuck2);
        System.out.println("Puck 1 (5.5) compared to Puck 2 (5.0): " + result1 +
                           (result1 > 0 ? " (Puck 1 is heavier)" : " (Puck 2 is heavier)"));

        int result2 = youthPuck1.compareTo(youthPuck2);
        System.out.println("Puck 3 (4.0) compared to Puck 4 (4.5): " + result2 +
                           (result2 < 0 ? " (Puck 3 is lighter)" : " (Puck 4 is lighter)"));

        int result3 = standardPuck1.compareTo(matchPuck);
        System.out.println("Puck 1 (5.5) compared to Puck 6 (5.5): " + result3 +
                           (result3 == 0 ? " (they're the same weight)" : ""));

        System.out.println("\n=== All Puck Tests Complete ===");
    }
}

/*
 * ======================== SCREEN DUMP ========================
 *
 * === Hockey Puck Testing Application ===
 *
 * --- Puck Details ---
 * Puck 1: Puck [weight=5.5 oz, division=Standard, diameter=3.0 in, thickness=1.0 in]
 * Puck 2: Puck [weight=5.0 oz, division=Standard, diameter=3.0 in, thickness=1.0 in]
 * Puck 3: Puck [weight=4.0 oz, division=Youth, diameter=3.0 in, thickness=1.0 in]
 * Puck 4: Puck [weight=4.5 oz, division=Youth, diameter=3.0 in, thickness=1.0 in]
 * Puck 5: Puck [weight=6.0 oz, division=Non-regulation, diameter=3.0 in, thickness=1.0 in]
 * Puck 6: Puck [weight=5.5 oz, division=Standard, diameter=3.0 in, thickness=1.0 in]
 *
 * --- Division Check ---
 * Puck 1 division: Standard
 * Puck 3 division: Youth
 * Puck 5 division: Non-regulation
 *
 * --- Weight Check ---
 * Puck 1 weight: 5.5 oz
 * Puck 4 weight: 4.5 oz
 *
 * --- Inherited Methods (from Circle and Disk) ---
 * Puck 1 radius: 1.5 inches
 * Puck 1 thickness: 1.0 inches
 * Puck 1 area (face): 7.07 sq inches
 * Puck 1 volume: 7.07 cubic inches
 *
 * --- Equals Testing ---
 * Puck 1 equals Puck 6 (both 5.5 oz)? true
 * Puck 1 equals Puck 2 (5.5 vs 5.0)? false
 * Puck 3 equals Puck 4 (4.0 vs 4.5)? false
 *
 * --- Comparable Testing ---
 * Puck 1 (5.5) compared to Puck 2 (5.0): 1 (Puck 1 is heavier)
 * Puck 3 (4.0) compared to Puck 4 (4.5): -1 (Puck 3 is lighter)
 * Puck 1 (5.5) compared to Puck 6 (5.5): 0 (they're the same weight)
 *
 * === All Puck Tests Complete ===
 *
 * ======================== END SCREEN DUMP ========================
 */
