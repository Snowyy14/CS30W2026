/**
 * Disk.java
 * Author: Ethan
 * Date: April 16, 2026
 * Description: Subclass of Circle that adds thickness to create a 3D disk shape.
 *              A disk is basically a circle with some depth to it, like a coin
 *              or hockey puck. This is the middle class in the inheritance chain
 *              Circle -> Disk -> Puck.
 */
package SkillBuilders;

public class Disk extends Circle {
    // thickness gives the circle a 3rd dimension, making it a disk
    private double thickness;

    /**
     * Constructor - creates a disk with a radius and thickness
     * pre: none
     * post: Disk object created with specified radius and thickness
     */
    public Disk(double radius, double thickness) {
        super(radius); // let Circle handle the radius part
        this.thickness = thickness;
    }

    /**
     * Returns how thick the disk is
     * pre: none
     * post: thickness value returned
     */
    public double getThickness() {
        return thickness;
    }

    /**
     * Volume is just the area of the circle face times the thickness.
     * Think of it like stacking super thin circles on top of each other.
     * pre: none
     * post: volume of the disk returned
     */
    public double volume() {
        return area() * thickness;
    }

    /**
     * Two disks are equal when they have the same radius AND thickness.
     * Calls the parent equals for radius, then checks thickness too.
     * pre: none
     * post: true if both radius and thickness match, false otherwise
     */
    public boolean equals(Object obj) {
        if (obj instanceof Disk) {
            Disk other = (Disk) obj;
            // parent checks radius, then we check thickness ourselves
            return super.equals(other) &&
                   Math.round(this.thickness * 100) == Math.round(other.thickness * 100);
        }
        return false;
    }

    /**
     * Returns a readable string showing all disk properties
     * pre: none
     * post: formatted string returned
     */
    public String toString() {
        return "Disk [radius=" + String.format("%.2f", getRadius()) +
               ", thickness=" + String.format("%.2f", thickness) +
               ", area=" + String.format("%.2f", area()) +
               ", volume=" + String.format("%.2f", volume()) + "]";
    }
}
