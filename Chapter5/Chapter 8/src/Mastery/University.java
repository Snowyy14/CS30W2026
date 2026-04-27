/**
 * University.java
 * Author: Ethan
 * Date: April 16, 2026
 * Description: Aggregation class that manages a collection of university employees
 *              (both Faculty and Staff). This demonstrates the "has-a" relationship -
 *              a University HAS employees (as opposed to "is-a"). Uses polymorphism
 *              since the employee array holds UEmployee references that can point to
 *              either Faculty or Staff objects. Also demonstrates how an aggregation
 *              class can provide high-level operations over a collection of objects.
 */
package Mastery;

import java.util.ArrayList;

public class University {
    // the name of the university
    private String universityName;

    // using ArrayList instead of array since we don't know how many employees there'll be
    // polymorphism in action - this list can hold Faculty AND Staff since both extend UEmployee
    private ArrayList<UEmployee> employees;

    /**
     * Constructor - creates a university with a name and an empty employee list
     * pre: none
     * post: University created, ready to have employees added
     */
    public University(String name) {
        this.universityName = name;
        this.employees = new ArrayList<UEmployee>();
    }

    /**
     * Returns the university name
     * pre: none
     * post: name returned
     */
    public String getUniversityName() {
        return universityName;
    }

    /**
     * Adds an employee to the university. Since it takes a UEmployee reference,
     * you can pass in either a Faculty or Staff object and it works either way.
     * That's polymorphism doing its thing.
     * pre: employee is not null
     * post: employee added to the list
     */
    public void addEmployee(UEmployee employee) {
        employees.add(employee);
        System.out.println("Added: " + employee.getName() + " to " + universityName);
    }

    /**
     * Removes an employee by name. Searches through the list and removes the
     * first match it finds.
     * pre: none
     * post: employee removed if found, message printed either way
     */
    public void removeEmployee(String name) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getName().equalsIgnoreCase(name)) {
                System.out.println("Removed: " + employees.get(i).getName());
                employees.remove(i);
                return;
            }
        }
        System.out.println("Employee '" + name + "' not found.");
    }

    /**
     * Searches for an employee by name and returns them.
     * Returns null if nobody by that name works here.
     * pre: none
     * post: matching employee returned, or null if not found
     */
    public UEmployee findEmployee(String name) {
        for (UEmployee emp : employees) {
            if (emp.getName().equalsIgnoreCase(name)) {
                return emp;
            }
        }
        return null;
    }

    /**
     * Returns how many employees the university has
     * pre: none
     * post: count returned
     */
    public int getEmployeeCount() {
        return employees.size();
    }

    /**
     * Prints out all faculty members only. Uses instanceof to filter
     * since Faculty and Staff are mixed in the same list.
     * pre: none
     * post: all faculty printed to console
     */
    public void listFaculty() {
        System.out.println("--- Faculty at " + universityName + " ---");
        boolean found = false;
        for (UEmployee emp : employees) {
            if (emp instanceof Faculty) {
                System.out.println("  " + emp.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("  No faculty members found.");
        }
    }

    /**
     * Prints out all staff members only. Same instanceof filtering trick.
     * pre: none
     * post: all staff printed to console
     */
    public void listStaff() {
        System.out.println("--- Staff at " + universityName + " ---");
        boolean found = false;
        for (UEmployee emp : employees) {
            if (emp instanceof Staff) {
                System.out.println("  " + emp.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("  No staff members found.");
        }
    }

    /**
     * Lists every employee regardless of type. Polymorphism means toString()
     * automatically calls the right version (Faculty or Staff) for each object.
     * pre: none
     * post: all employees printed to console
     */
    public void listAllEmployees() {
        System.out.println("--- All Employees at " + universityName + " ---");
        if (employees.isEmpty()) {
            System.out.println("  No employees.");
        }
        for (UEmployee emp : employees) {
            System.out.println("  " + emp.toString());
        }
    }

    /**
     * Calculates the total payroll cost (sum of all salaries)
     * pre: none
     * post: total payroll returned
     */
    public double getTotalPayroll() {
        double total = 0;
        for (UEmployee emp : employees) {
            total += emp.getSalary();
        }
        return total;
    }

    /**
     * Returns a summary string for the university
     * pre: none
     * post: formatted string returned
     */
    public String toString() {
        return universityName + " [" + employees.size() + " employees, Total Payroll: $" +
               String.format("%.2f", getTotalPayroll()) + "]";
    }
}
