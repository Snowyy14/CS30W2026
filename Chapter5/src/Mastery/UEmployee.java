package Mastery;

// uemployee class
public class UEmployee {
    private String name;
    private double salary;

// constructor for the employee
    public UEmployee(String n, double s) {
        name = n;
        salary = s;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void setName(String name) { this.name = name; }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String toString() {
return "Employee: " + name + " Salary: $" + String.format("%.2f", salary);
    }
}
