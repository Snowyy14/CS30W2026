package Mastery;

// StudentRecord.java
// Author: Ethan
// Date: March 18, 2026
// Stores a student's info and grades. Implements Serializable so we can
// save/load student objects to/from a file using object streams.

import java.io.Serializable;

public class StudentRecord implements Serializable {

    private String name;
    private String gradeLevel;
    private String semester;
    private double grade1, grade2, grade3, grade4;
    private double average;

    // constructor - takes in all the student info at once
    public StudentRecord(String name, String gradeLevel, String semester,
                         double g1, double g2, double g3, double g4) {
        this.name = name;
        this.gradeLevel = gradeLevel;
        this.semester = semester;
        this.grade1 = g1;
        this.grade2 = g2;
        this.grade3 = g3;
        this.grade4 = g4;
        this.average = Math.round(((g1 + g2 + g3 + g4) / 4.0) * 10.0) / 10.0;
    }

    // getters
    public String getName() { return name; }
    public String getGradeLevel() { return gradeLevel; }
    public String getSemester() { return semester; }
    public double getGrade1() { return grade1; }
    public double getGrade2() { return grade2; }
    public double getGrade3() { return grade3; }
    public double getGrade4() { return grade4; }
    public double getAverage() { return average; }

    // turns the record into a readable string for display
    public String toString() {
        return "Name: " + name + ", Grade Level: " + gradeLevel
             + ", Semester: " + semester
             + ", Grades: " + grade1 + ", " + grade2 + ", " + grade3 + ", " + grade4
             + ", Average: " + average + "%";
    }
}
