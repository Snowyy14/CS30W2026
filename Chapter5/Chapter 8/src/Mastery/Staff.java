/**
 * Staff.java
 * Author: Ethan
 * Date: April 16, 2026
 * Description: Subclass of UEmployee representing staff members (admins,
 *              custodians, IT support, etc.). Staff have a job title in addition
 *              to the inherited name and salary. Just like Faculty, this shows
 *              how inheritance lets us reuse code from UEmployee without
 *              copying it all over again.
 */
package Mastery;

public class Staff extends UEmployee {
    // the specific job title for this staff member (like "Admissions" or "IT Support")
    private String jobTitle;

    /**
     * Constructor - creates a staff member with name, salary, and job title.
     * Uses super to pass name and salary up to UEmployee.
     * pre: none
     * post: Staff object created with all fields set
     */
    public Staff(String name, double salary, String title) {
        super(name, salary);
        this.jobTitle = title;
    }

    /**
     * Returns the staff member's job title
     * pre: none
     * post: job title returned
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Updates the job title (for role changes or promotions)
     * pre: none
     * post: job title updated
     */
    public void setJobTitle(String title) {
        this.jobTitle = title;
    }

    /**
     * Two staff members are equal if they have the same name AND title.
     * pre: none
     * post: true if name and title match, false otherwise
     */
    public boolean equals(Object obj) {
        if (obj instanceof Staff) {
            Staff other = (Staff) obj;
            return super.equals(other) &&
                   this.jobTitle.equalsIgnoreCase(other.jobTitle);
        }
        return false;
    }

    /**
     * Builds on the parent toString and adds the job title
     * pre: none
     * post: formatted string returned
     */
    public String toString() {
        return super.toString() + " | Title: " + jobTitle;
    }
}
