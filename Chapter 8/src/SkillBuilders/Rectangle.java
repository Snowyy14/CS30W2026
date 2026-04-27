/**
 * Rectangle.java
 * Author: Ethan
 * Date: April 16, 2026
 * Description: Rectangle class that combines all 5 parts of the review exercises.
 *
 *              Part 1: Overloaded constructors (no-arg sets 1x1, parameterized
 *                      sets custom length/width). Getters, setters, area, perimeter.
 *              Part 2: Static method displayAreaFormula() shows the formula.
 *              Part 3: Override equals() (same length and width) and toString().
 *              Part 4: Implements Comparable - compares by width and height.
 *              Part 5: Implements ComparableArea - compares by area (0, -1, or 1).
 *
 *              This class demonstrates overloading, overriding, interfaces, and
 *              how a single class can implement multiple interfaces at once.
 */
package SkillBuilders;

public class Rectangle implements Comparable<Rectangle>, ComparableArea {
    // the two dimensions that define a rectangle
    private double length;
    private double width;

    /**
     * No-arg constructor - creates a default 1x1 rectangle.
     * Handy when I need a rectangle but don't know the size yet.
     * pre: none
     * post: 1x1 rectangle created
     */
    public Rectangle() {
        this.length = 1.0;
        this.width = 1.0;
    }

    /**
     * Parameterized constructor - creates a rectangle with specific dimensions.
     * pre: length and width should be positive
     * post: rectangle created with given length and width
     */
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    // --- Getters and Setters ---

    /**
     * Returns the length
     * pre: none
     * post: length value returned
     */
    public double getLength() {
        return length;
    }

    /**
     * Sets a new length value
     * pre: none
     * post: length updated
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * Returns the width
     * pre: none
     * post: width value returned
     */
    public double getWidth() {
        return width;
    }

    /**
     * Sets a new width value
     * pre: none
     * post: width updated
     */
    public void setWidth(double width) {
        this.width = width;
    }

    // --- Calculations ---

    /**
     * Area is just length times width, basic geometry stuff
     * pre: none
     * post: area returned
     */
    public double area() {
        return length * width;
    }

    /**
     * Perimeter is the total distance around the outside
     * pre: none
     * post: perimeter returned
     */
    public double perimeter() {
        return 2 * (length + width);
    }

    // --- Part 2: Class (static) method ---

    /**
     * Displays the formula for calculating rectangle area.
     * This is a class method (static) so it can be called without an object.
     * pre: none
     * post: formula printed to console
     */
    public static void displayAreaFormula() {
        System.out.println("Area Formula: Area = length x width");
    }

    // --- Part 3: equals and toString overrides ---

    /**
     * Two rectangles are equal when they have the same length AND width.
     * Rounds to 2 decimal places to avoid floating point comparison issues.
     * pre: none
     * post: true if same dimensions, false otherwise
     */
    public boolean equals(Object obj) {
        if (obj instanceof Rectangle) {
            Rectangle other = (Rectangle) obj;
            // round both values to avoid the classic 0.1 + 0.2 != 0.3 issue
            return Math.round(this.length * 100) == Math.round(other.length * 100) &&
                   Math.round(this.width * 100) == Math.round(other.width * 100);
        }
        return false;
    }

    /**
     * Returns a clean string showing the rectangle's dimensions, area, perimeter
     * pre: none
     * post: formatted string returned
     */
    public String toString() {
        return "Rectangle [length=" + String.format("%.2f", length) +
               ", width=" + String.format("%.2f", width) +
               ", area=" + String.format("%.2f", area()) +
               ", perimeter=" + String.format("%.2f", perimeter()) + "]";
    }

    // --- Part 4: Comparable interface ---

    /**
     * Comparable implementation - compares rectangles by width first, then height.
     * This lets us sort rectangles in a consistent way.
     * pre: other is not null
     * post: negative if this is "less", positive if "greater", 0 if same
     */
    @Override
    public int compareTo(Rectangle other) {
        // check width first
        int widthCompare = Double.compare(this.width, other.width);
        if (widthCompare != 0) {
            return widthCompare;
        }
        // if widths are the same, break the tie with length
        return Double.compare(this.length, other.length);
    }

    // --- Part 5: ComparableArea interface ---

    /**
     * Compares this rectangle's area to another rectangle's area.
     * Returns 0 if same area, -1 if this is smaller, 1 if this is bigger.
     * This is different from compareTo because it only cares about area,
     * not the specific dimensions.
     * pre: other should be a Rectangle
     * post: -1, 0, or 1 returned based on area comparison
     */
    @Override
    public int compareToArea(Object obj) {
        if (obj instanceof Rectangle) {
            Rectangle other = (Rectangle) obj;
            double thisArea = this.area();
            double otherArea = other.area();

            // using a small epsilon for floating point comparison
            if (Math.abs(thisArea - otherArea) < 0.001) {
                return 0;   // areas are essentially equal
            } else if (thisArea < otherArea) {
                return -1;  // this rectangle is smaller
            } else {
                return 1;   // this rectangle is bigger
            }
        }
        return 1; // default - can't really compare to non-rectangles
    }
}
