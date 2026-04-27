/**
 * TestEmployee.java
 * Author: Ethan
 * Date: April 16, 2026
 * Description: Test application for the UEmployee inheritance hierarchy and the
 *              University aggregation class. Creates Faculty and Staff objects,
 *              adds them to a University, and tests polymorphism, equals, searching,
 *              and other University operations. Demonstrates both "is-a" (inheritance)
 *              and "has-a" (aggregation) relationships.
 */
package Mastery;

public class TestEmployee {

    public static void main(String[] args) {
        System.out.println("=== University Employee Testing Application ===\n");

        // --- Create individual employees ---
        // faculty members teach and do research in their departments
        Faculty f1 = new Faculty("Dr. Smith", 85000, "Computer Science");
        Faculty f2 = new Faculty("Dr. Johnson", 92000, "Mathematics");
        Faculty f3 = new Faculty("Dr. Brown", 78000, "English");

        // staff members handle the non-academic side of the university
        Staff s1 = new Staff("Bob Williams", 45000, "Admissions Coordinator");
        Staff s2 = new Staff("Alice Chen", 40000, "IT Support Specialist");
        Staff s3 = new Staff("Carlos Rivera", 52000, "Head Custodian");

        // --- Test individual employee info ---
        System.out.println("--- Individual Employee Details ---");
        System.out.println(f1);
        System.out.println(f2);
        System.out.println(s1);
        System.out.println(s2);

        // --- Test equals ---
        System.out.println("\n--- Equals Testing ---");
        Faculty f4 = new Faculty("Dr. Smith", 90000, "Computer Science");
        System.out.println("f1 equals f4 (same name & dept, diff salary)? " + f1.equals(f4));
        System.out.println("f1 equals f2 (different people)? " + f1.equals(f2));
        System.out.println("f1 equals s1 (faculty vs staff)? " + f1.equals(s1));

        // --- Test getters and setters ---
        System.out.println("\n--- Getter/Setter Testing ---");
        System.out.println("f2 department: " + f2.getDepartmentName());
        f2.setDepartmentName("Applied Mathematics");
        System.out.println("f2 department after change: " + f2.getDepartmentName());
        System.out.println("s1 title: " + s1.getJobTitle());
        s1.setSalary(48000);
        System.out.println("s1 after raise: " + s1);

        // --- Test University aggregation class ---
        System.out.println("\n--- University Aggregation Class ---");
        University uni = new University("Westview University");
        System.out.println("Created: " + uni.getUniversityName());

        // add all our employees - polymorphism lets us add both Faculty and Staff
        System.out.println();
        uni.addEmployee(f1);
        uni.addEmployee(f2);
        uni.addEmployee(f3);
        uni.addEmployee(s1);
        uni.addEmployee(s2);
        uni.addEmployee(s3);

        // list by type
        System.out.println();
        uni.listFaculty();
        System.out.println();
        uni.listStaff();

        // list everyone
        System.out.println();
        uni.listAllEmployees();

        // search for a specific employee
        System.out.println("\n--- Search Testing ---");
        UEmployee found = uni.findEmployee("Alice Chen");
        if (found != null) {
            System.out.println("Found: " + found);
        }

        UEmployee notFound = uni.findEmployee("Nobody Here");
        System.out.println("Search for 'Nobody Here': " + (notFound == null ? "Not found" : notFound));

        // university summary
        System.out.println("\n--- University Summary ---");
        System.out.println(uni);
        System.out.println("Total employees: " + uni.getEmployeeCount());
        System.out.println("Total payroll: $" + String.format("%.2f", uni.getTotalPayroll()));

        // remove an employee
        System.out.println("\n--- Remove Testing ---");
        uni.removeEmployee("Dr. Brown");
        System.out.println("After removal: " + uni);

        System.out.println("\n=== All Employee Tests Complete ===");
    }
}

/*
 * ======================== SCREEN DUMP ========================
 *
 * === University Employee Testing Application ===
 *
 * --- Individual Employee Details ---
 * Employee: Dr. Smith | Salary: $85000.00 | Dept: Computer Science
 * Employee: Dr. Johnson | Salary: $92000.00 | Dept: Mathematics
 * Employee: Bob Williams | Salary: $45000.00 | Title: Admissions Coordinator
 * Employee: Alice Chen | Salary: $40000.00 | Title: IT Support Specialist
 *
 * --- Equals Testing ---
 * f1 equals f4 (same name & dept, diff salary)? true
 * f1 equals f2 (different people)? false
 * f1 equals s1 (faculty vs staff)? false
 *
 * --- Getter/Setter Testing ---
 * f2 department: Mathematics
 * f2 department after change: Applied Mathematics
 * s1 title: Admissions Coordinator
 * s1 after raise: Employee: Bob Williams | Salary: $48000.00 | Title: Admissions Coordinator
 *
 * --- University Aggregation Class ---
 * Created: Westview University
 *
 * Added: Dr. Smith to Westview University
 * Added: Dr. Johnson to Westview University
 * Added: Dr. Brown to Westview University
 * Added: Bob Williams to Westview University
 * Added: Alice Chen to Westview University
 * Added: Carlos Rivera to Westview University
 *
 * --- Faculty at Westview University ---
 *   Employee: Dr. Smith | Salary: $85000.00 | Dept: Computer Science
 *   Employee: Dr. Johnson | Salary: $92000.00 | Dept: Applied Mathematics
 *   Employee: Dr. Brown | Salary: $78000.00 | Dept: English
 *
 * --- Staff at Westview University ---
 *   Employee: Bob Williams | Salary: $48000.00 | Title: Admissions Coordinator
 *   Employee: Alice Chen | Salary: $40000.00 | Title: IT Support Specialist
 *   Employee: Carlos Rivera | Salary: $52000.00 | Title: Head Custodian
 *
 * --- All Employees at Westview University ---
 *   Employee: Dr. Smith | Salary: $85000.00 | Dept: Computer Science
 *   Employee: Dr. Johnson | Salary: $92000.00 | Dept: Applied Mathematics
 *   Employee: Dr. Brown | Salary: $78000.00 | Dept: English
 *   Employee: Bob Williams | Salary: $48000.00 | Title: Admissions Coordinator
 *   Employee: Alice Chen | Salary: $40000.00 | Title: IT Support Specialist
 *   Employee: Carlos Rivera | Salary: $52000.00 | Title: Head Custodian
 *
 * --- Search Testing ---
 * Found: Employee: Alice Chen | Salary: $40000.00 | Title: IT Support Specialist
 * Search for 'Nobody Here': Not found
 *
 * --- University Summary ---
 * Westview University [6 employees, Total Payroll: $395000.00]
 * Total employees: 6
 * Total payroll: $395000.00
 *
 * --- Remove Testing ---
 * Removed: Dr. Brown
 * After removal: Westview University [5 employees, Total Payroll: $317000.00]
 *
 * === All Employee Tests Complete ===
 *
 * ======================== END SCREEN DUMP ========================
 */
