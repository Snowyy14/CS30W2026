/**
 * Faculty.java
 * Author: Ethan
 * Date: April 16, 2026
 * Description: Subclass of UEmployee representing faculty members (professors,
 *              instructors, etc.). Faculty have a department in addition to the
 *              inherited name and salary. This shows inheritance in action -
 *              I don't have to rewrite name/salary logic, I just add
 *              department-specific stuff on top.
 */
package Mastery;

public class Faculty extends UEmployee {
    // the academic department this faculty member belongs to (like "CS" or "Math")
    private String departmentName;

    /**
     * Constructor - creates a faculty member with name, salary, and department.
     * The super call handles name and salary since UEmployee already knows how.
     * pre: none
     * post: Faculty object created with all fields set
     */
    public Faculty(String name, double salary, String department) {
        super(name, salary);
        this.departmentName = department;
    }

    /**
     * Returns the department name
     * pre: none
     * post: department name returned
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Changes the department (for transfers between departments)
     * pre: none
     * post: department updated
     */
    public void setDepartmentName(String department) {
        this.departmentName = department;
    }

    /**
     * Two faculty members are equal if they have the same name AND department.
     * Uses the parent equals for name, then checks department ourselves.
     * pre: none
     * post: true if name and department match, false otherwise
     */
    public boolean equals(Object obj) {
        if (obj instanceof Faculty) {
            Faculty other = (Faculty) obj;
            return super.equals(other) &&
                   this.departmentName.equalsIgnoreCase(other.departmentName);
        }
        return false;
    }

    /**
     * Builds on the parent toString and adds the department info
     * pre: none
     * post: formatted string returned
     */
    public String toString() {
        return super.toString() + " | Dept: " + departmentName;
    }
}
