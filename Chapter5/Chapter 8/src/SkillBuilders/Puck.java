/**
 * Puck.java
 * Author: Ethan
 * Date: April 16, 2026
 * Description: Subclass of Disk representing a hockey puck. A puck has a weight
 *              and is classified as either standard (5-5.5 oz) or youth (4-4.5 oz).
 *              All official pucks are 1 inch thick with a 3 inch diameter regardless
 *              of division. Implements Comparable so we can compare pucks by weight.
 *              This is the bottom of the Circle -> Disk -> Puck hierarchy.
 *
 *              Part 1: Basic Puck class with weight, standard/youth booleans
 *              Part 2: Added Comparable interface for weight comparison
 */
package SkillBuilders;

public class Puck extends Disk implements Comparable<Puck> {
    // weight in ounces - determines if its standard or youth
    private double weight;

    // these booleans get set based on the weight range
    private boolean standard;
    private boolean youth;

    // all official pucks use these exact dimensions
    private static final double PUCK_THICKNESS = 1.0;   // 1 inch thick
    private static final double PUCK_DIAMETER = 3.0;     // 3 inch diameter

    /**
     * Constructor - just needs the weight, everything else is set automatically.
     * The radius is half the diameter (1.5 inches), thickness is always 1 inch.
     * Standard vs youth is figured out from the weight.
     * pre: weight should be between 4.0 and 5.5 to be a valid puck
     * post: Puck created with appropriate division flags set
     */
    public Puck(double weight) {
        // diameter is 3 so radius is 1.5, thickness always 1
        super(PUCK_DIAMETER / 2.0, PUCK_THICKNESS);
        this.weight = weight;

        // figure out which division this puck belongs to based on weight
        // standard pucks are heavier (5-5.5 oz), youth are lighter (4-4.5 oz)
        this.standard = (weight >= 5.0 && weight <= 5.5);
        this.youth = (weight >= 4.0 && weight <= 4.5);
    }

    /**
     * Returns the weight in ounces
     * pre: none
     * post: weight returned
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Tells you what division this puck is for based on its weight.
     * If it doesn't fall in either range, it's non-regulation.
     * pre: none
     * post: division string returned
     */
    public String getDivision() {
        if (standard) {
            return "Standard";
        } else if (youth) {
            return "Youth";
        } else {
            // weight is outside both ranges so its not a legit game puck
            return "Non-regulation";
        }
    }

    /**
     * Two pucks are equal when they weigh the same.
     * We round to 1 decimal since puck weights are usually measured that way.
     * pre: none
     * post: true if weights match, false otherwise
     */
    public boolean equals(Object obj) {
        if (obj instanceof Puck) {
            Puck other = (Puck) obj;
            // round to 1 decimal place for practical comparison
            return Math.round(this.weight * 10) == Math.round(other.weight * 10);
        }
        return false;
    }

    /**
     * Comparable implementation - compares pucks by weight.
     * Negative means this puck is lighter, positive means heavier, 0 means same.
     * This is what lets us sort pucks or check equality through Comparable.
     * pre: other is not null
     * post: comparison result returned
     */
    @Override
    public int compareTo(Puck other) {
        // using Double.compare handles all the edge cases for us
        return Double.compare(this.weight, other.weight);
    }

    /**
     * Returns all the puck info in a nice readable format
     * pre: none
     * post: formatted string returned
     */
    public String toString() {
        return "Puck [weight=" + String.format("%.1f", weight) + " oz" +
               ", division=" + getDivision() +
               ", diameter=" + String.format("%.1f", getRadius() * 2) + " in" +
               ", thickness=" + String.format("%.1f", getThickness()) + " in]";
    }
}
