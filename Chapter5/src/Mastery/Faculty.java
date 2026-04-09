package Mastery;

// faculty inherits from employee
public class Faculty extends UEmployee {
private String deptName; // department

    public Faculty(String n, double s, String dept) {
        super(n, s);
        deptName = dept;
    }

    public String getDepartmentName() {
        return deptName;
    }

    public void setDepartmentName(String d) {
        deptName = d;
    }

// returns string
    public String toString() {
        return super.toString() + " Dept: " + deptName;
    }
}
