/**
 * ComparableArea.java
 * Author: Ethan
 * Date: April 16, 2026
 * Description: Interface for comparing objects by their area. Any shape class
 *              that implements this can be compared to another shape based on
 *              how much space they take up, not just their dimensions.
 *              Used by Rectangle (part 5 of the review exercises).
 */
package SkillBuilders;

public interface ComparableArea {

    /**
     * Compares this object's area to another object's area.
     * Returns 0 if areas are the same, -1 if this object has less area,
     * and 1 if this object has more area.
     *
     * @param other the object to compare area against
     * @return 0 if equal area, -1 if less, 1 if greater
     */
    int compareToArea(Object other);
}
