/**
 * Circle.java
 * Author: Ethan
 * Date: April 16, 2026
 * Description: Base class representing a circle with a radius.
 *              This serves as the parent class in the Circle -> Disk -> Puck
 *              inheritance hierarchy. Demonstrates basic OOP concepts like
 *              encapsulation, constructors, and method overriding.
 */
package SkillBuilders;

public class Circle {
    // the radius is the one measurement that defines a circle
    private double radius;

    /**
     * Constructor - sets up the circle with a given radius
     * pre: none
     * post: Circle object created with the specified radius
     */
    public Circle(double radius) {
        this.radius = radius;
    }

    /**
     * Returns the radius of this circle
     * pre: none
     * post: radius value returned
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Calculates and returns the area using pi * r^2
     * pre: none
     * post: area of the circle returned
     */
    public double area() {
        return Math.PI * radius * radius;
    }

    /**
     * Two circles are equal if they have the same radius.
     * We round to 2 decimals so tiny floating point diffs don't mess things up.
     * pre: none
     * post: true returned if radii match, false otherwise
     */
    public boolean equals(Object obj) {
        if (obj instanceof Circle) {
            Circle other = (Circle) obj;
            // rounding avoids floating point weirdness like 3.0000001 != 3.0
            return Math.round(this.radius * 100) == Math.round(other.radius * 100);
        }
        return false;
    }

    /**
     * Returns a nice readable string with the circle's info
     * pre: none
     * post: formatted string returned
     */
    public String toString() {
        return "Circle [radius=" + String.format("%.2f", radius) +
               ", area=" + String.format("%.2f", area()) + "]";
    }
}
