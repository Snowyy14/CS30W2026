/**
 * UEmployee.java
 * Author: Ethan
 * Date: April 16, 2026
 * Description: Base class for university employees. Stores the employee's name
 *              and salary. Faculty and Staff both extend this class since every
 *              university employee has those two things in common regardless of
 *              their specific role. This demonstrates the "is-a" relationship -
 *              a Faculty member IS A UEmployee, a Staff member IS A UEmployee.
 */
package Mastery;

public class UEmployee {
    // every employee has a name and a salary, no matter what their job is
    private String name;
    private double salary;

    /**
     * Constructor - sets up the employee with their name and starting salary.
     * pre: none
     * post: UEmployee created with given name and salary
     */
    public UEmployee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    /**
     * Returns the employee's name
     * pre: none
     * post: name returned
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the employee's salary
     * pre: none
     * post: salary returned
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Updates the employee's name (like if they legally change it)
     * pre: none
     * post: name updated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Updates the salary (raises, promotions, etc.)
     * pre: none
     * post: salary updated
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Two employees are equal if they have the same name.
     * In a real system you'd use an employee ID, but name works for this exercise.
     * pre: none
     * post: true if names match, false otherwise
     */
    public boolean equals(Object obj) {
        if (obj instanceof UEmployee) {
            UEmployee other = (UEmployee) obj;
            return this.name.equalsIgnoreCase(other.name);
        }
        return false;
    }

    /**
     * Returns a formatted string with the employee's basic info
     * pre: none
     * post: formatted string returned
     */
    public String toString() {
        return "Employee: " + name + " | Salary: $" + String.format("%.2f", salary);
    }
}
