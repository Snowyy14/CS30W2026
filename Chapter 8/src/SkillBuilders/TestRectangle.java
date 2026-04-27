/**
 * TestRectangle.java
 * Author: Ethan
 * Date: April 16, 2026
 * Description: Client code that tests every feature of the Rectangle class
 *              across all 5 parts of the review exercises. Tests constructors,
 *              getters/setters, area/perimeter, displayAreaFormula, equals,
 *              toString, Comparable (compareTo), and ComparableArea (compareToArea).
 */
package SkillBuilders;

public class TestRectangle {

    public static void main(String[] args) {
        System.out.println("=== Rectangle Testing Application ===\n");

        // --- Part 1: Testing overloaded constructors and basic methods ---
        System.out.println("--- Part 1: Constructors, Getters, Area, Perimeter ---");

        // no-arg constructor should give us a 1x1 rectangle
        Rectangle defaultRect = new Rectangle();
        System.out.println("Default rectangle: " + defaultRect);
        System.out.println("  Length: " + defaultRect.getLength());
        System.out.println("  Width: " + defaultRect.getWidth());
        System.out.println("  Area: " + String.format("%.2f", defaultRect.area()));
        System.out.println("  Perimeter: " + String.format("%.2f", defaultRect.perimeter()));

        // parameterized constructor
        Rectangle rect1 = new Rectangle(5.0, 3.0);
        Rectangle rect2 = new Rectangle(8.0, 4.0);
        System.out.println("\nRect1 (5x3): " + rect1);
        System.out.println("Rect2 (8x4): " + rect2);

        // test setters
        defaultRect.setLength(10.0);
        defaultRect.setWidth(7.0);
        System.out.println("\nDefault rect after setLength(10) and setWidth(7): " + defaultRect);

        // --- Part 2: Testing the static displayAreaFormula method ---
        System.out.println("\n--- Part 2: Display Area Formula ---");
        // calling it on the class itself since its static, no object needed
        Rectangle.displayAreaFormula();

        // --- Part 3: Testing equals and toString ---
        System.out.println("\n--- Part 3: Equals and ToString ---");
        Rectangle rect3 = new Rectangle(5.0, 3.0);  // same as rect1
        Rectangle rect4 = new Rectangle(3.0, 5.0);  // same area but different dimensions

        System.out.println("Rect1: " + rect1);
        System.out.println("Rect3: " + rect3);
        System.out.println("Rect4: " + rect4);
        System.out.println("Rect1 equals Rect3 (both 5x3)? " + rect1.equals(rect3));
        System.out.println("Rect1 equals Rect4 (5x3 vs 3x5)? " + rect1.equals(rect4));
        System.out.println("Rect1 equals Rect2 (5x3 vs 8x4)? " + rect1.equals(rect2));

        // --- Part 4: Testing Comparable interface ---
        System.out.println("\n--- Part 4: Comparable (compareTo) ---");
        Rectangle rect5 = new Rectangle(5.0, 3.0);  // same as rect1
        Rectangle rect6 = new Rectangle(5.0, 6.0);  // same length, wider

        int cmp1 = rect1.compareTo(rect2);
        System.out.println("Rect1 (5x3) compareTo Rect2 (8x4): " + cmp1 +
                           (cmp1 < 0 ? " (Rect1 is less)" : cmp1 > 0 ? " (Rect1 is greater)" : " (equal)"));

        int cmp2 = rect1.compareTo(rect5);
        System.out.println("Rect1 (5x3) compareTo Rect5 (5x3): " + cmp2 +
                           (cmp2 == 0 ? " (they're equal)" : ""));

        int cmp3 = rect6.compareTo(rect1);
        System.out.println("Rect6 (5x6) compareTo Rect1 (5x3): " + cmp3 +
                           (cmp3 > 0 ? " (Rect6 is greater)" : ""));

        // --- Part 5: Testing ComparableArea interface ---
        System.out.println("\n--- Part 5: ComparableArea (compareToArea) ---");

        // rect4 is 3x5 = 15, rect1 is 5x3 = 15 -> same area different dimensions
        int area1 = rect1.compareToArea(rect4);
        System.out.println("Rect1 (5x3, area=15) compareToArea Rect4 (3x5, area=15): " + area1 +
                           (area1 == 0 ? " (same area)" : ""));

        int area2 = rect1.compareToArea(rect2);
        System.out.println("Rect1 (5x3, area=15) compareToArea Rect2 (8x4, area=32): " + area2 +
                           (area2 == -1 ? " (Rect1 has less area)" : ""));

        int area3 = rect2.compareToArea(rect1);
        System.out.println("Rect2 (8x4, area=32) compareToArea Rect1 (5x3, area=15): " + area3 +
                           (area3 == 1 ? " (Rect2 has more area)" : ""));

        System.out.println("\n=== All Rectangle Tests Complete ===");
    }
}

/*
 * ======================== SCREEN DUMP ========================
 *
 * === Rectangle Testing Application ===
 *
 * --- Part 1: Constructors, Getters, Area, Perimeter ---
 * Default rectangle: Rectangle [length=1.00, width=1.00, area=1.00, perimeter=4.00]
 *   Length: 1.0
 *   Width: 1.0
 *   Area: 1.00
 *   Perimeter: 4.00
 *
 * Rect1 (5x3): Rectangle [length=5.00, width=3.00, area=15.00, perimeter=16.00]
 * Rect2 (8x4): Rectangle [length=8.00, width=4.00, area=32.00, perimeter=24.00]
 *
 * Default rect after setLength(10) and setWidth(7): Rectangle [length=10.00, width=7.00, area=70.00, perimeter=34.00]
 *
 * --- Part 2: Display Area Formula ---
 * Area Formula: Area = length x width
 *
 * --- Part 3: Equals and ToString ---
 * Rect1: Rectangle [length=5.00, width=3.00, area=15.00, perimeter=16.00]
 * Rect3: Rectangle [length=5.00, width=3.00, area=15.00, perimeter=16.00]
 * Rect4: Rectangle [length=3.00, width=5.00, area=15.00, perimeter=16.00]
 * Rect1 equals Rect3 (both 5x3)? true
 * Rect1 equals Rect4 (5x3 vs 3x5)? false
 * Rect1 equals Rect2 (5x3 vs 8x4)? false
 *
 * --- Part 4: Comparable (compareTo) ---
 * Rect1 (5x3) compareTo Rect2 (8x4): -1 (Rect1 is less)
 * Rect1 (5x3) compareTo Rect5 (5x3): 0 (they're equal)
 * Rect6 (5x6) compareTo Rect1 (5x3): 1 (Rect6 is greater)
 *
 * --- Part 5: ComparableArea (compareToArea) ---
 * Rect1 (5x3, area=15) compareToArea Rect4 (3x5, area=15): 0 (same area)
 * Rect1 (5x3, area=15) compareToArea Rect2 (8x4, area=32): -1 (Rect1 has less area)
 * Rect2 (8x4, area=32) compareToArea Rect1 (5x3, area=15): 1 (Rect2 has more area)
 *
 * === All Rectangle Tests Complete ===
 *
 * ======================== END SCREEN DUMP ========================
 */
