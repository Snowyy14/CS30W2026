package Mastery;

// StudentSemesterAverage.java
// Author: Ethan
// Date: March 18, 2026
// GUI app for entering student grades. Uses a separate StudentRecord class
// and saves data using both regular file streams AND object serialization.
// Two files are used: studentData.txt (text) and studentData.ser (serialized).

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class StudentSemesterAverage implements ActionListener {

    JFrame frame;
    JPanel topPanel, buttonPanel;
    JLabel nameLabel, gradeLevelLabel, semesterLabel;
    JLabel grade1Label, grade2Label, grade3Label, grade4Label;
    JLabel averageLabel;
    JTextField nameField, gradeLevelField, semesterField;
    JTextField grade1Field, grade2Field, grade3Field, grade4Field;
    JTextArea displayArea;
    JButton saveButton, viewButton;

    final String TEXT_FILE = "studentData.txt";
    final String SER_FILE = "studentData.ser";

    public StudentSemesterAverage() {
        // set up the window
        frame = new JFrame("Student Grade Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // top section - input fields
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(8, 2, 5, 2));
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        nameLabel = new JLabel("Student Name:");
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        topPanel.add(nameLabel);
        nameField = new JTextField(15);
        topPanel.add(nameField);

        gradeLevelLabel = new JLabel("Grade Level:");
        gradeLevelLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        topPanel.add(gradeLevelLabel);
        gradeLevelField = new JTextField(15);
        topPanel.add(gradeLevelField);

        semesterLabel = new JLabel("Semester Number:");
        semesterLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        topPanel.add(semesterLabel);
        semesterField = new JTextField(15);
        topPanel.add(semesterField);

        grade1Label = new JLabel("Grade 1:");
        grade1Label.setFont(new Font("SansSerif", Font.BOLD, 12));
        topPanel.add(grade1Label);
        grade1Field = new JTextField(15);
        topPanel.add(grade1Field);

        grade2Label = new JLabel("Grade 2:");
        grade2Label.setFont(new Font("SansSerif", Font.BOLD, 12));
        topPanel.add(grade2Label);
        grade2Field = new JTextField(15);
        topPanel.add(grade2Field);

        grade3Label = new JLabel("Grade 3:");
        grade3Label.setFont(new Font("SansSerif", Font.BOLD, 12));
        topPanel.add(grade3Label);
        grade3Field = new JTextField(15);
        topPanel.add(grade3Field);

        grade4Label = new JLabel("Grade 4:");
        grade4Label.setFont(new Font("SansSerif", Font.BOLD, 12));
        topPanel.add(grade4Label);
        grade4Field = new JTextField(15);
        topPanel.add(grade4Field);

        averageLabel = new JLabel("Average:");
        averageLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        topPanel.add(averageLabel);
        topPanel.add(new JLabel(""));

        frame.add(topPanel, BorderLayout.NORTH);

        // middle section - text area to show saved records
        displayArea = new JTextArea(8, 40);
        displayArea.setEditable(false);
        displayArea.setFont(new Font("SansSerif", Font.PLAIN, 11));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        frame.add(scrollPane, BorderLayout.CENTER);

        // bottom section - buttons
        buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));

        saveButton = new JButton("Save to File");
        saveButton.setActionCommand("Save");
        saveButton.addActionListener(this);
        buttonPanel.add(saveButton);

        viewButton = new JButton("View File Contents");
        viewButton.setActionCommand("View");
        viewButton.addActionListener(this);
        buttonPanel.add(viewButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(520, 450);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        String cmd = event.getActionCommand();

        if (cmd.equals("Save")) {
            try {
                // grab input from the text fields
                String name = nameField.getText();
                String gradeLevel = gradeLevelField.getText();
                String semester = semesterField.getText();
                double g1 = Double.parseDouble(grade1Field.getText());
                double g2 = Double.parseDouble(grade2Field.getText());
                double g3 = Double.parseDouble(grade3Field.getText());
                double g4 = Double.parseDouble(grade4Field.getText());

                // make a StudentRecord object
                StudentRecord record = new StudentRecord(name, gradeLevel, semester, g1, g2, g3, g4);

                // show the average
                averageLabel.setText("Average: " + record.getAverage() + "%");

                // save as plain text using FileWriter/BufferedWriter
                FileWriter fw = new FileWriter(TEXT_FILE, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(record.toString());
                bw.newLine();
                bw.close();
                fw.close();

                // also save using object serialization
                ArrayList<StudentRecord> records = loadSerializedRecords();
                records.add(record);
                saveSerializedRecords(records);

                JOptionPane.showMessageDialog(frame, "Data saved successfully!",
                        "Message", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numbers for all grades.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error saving: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if (cmd.equals("View")) {
            // read from the text file with FileReader/BufferedReader
            try {
                FileReader fr = new FileReader(new File(TEXT_FILE));
                BufferedReader br = new BufferedReader(fr);

                displayArea.setText("");
                String line = br.readLine();
                while (line != null) {
                    displayArea.append(line + "\n");
                    line = br.readLine();
                }
                br.close();
                fr.close();

            } catch (FileNotFoundException e) {
                displayArea.setText("No saved data yet.");
            } catch (IOException e) {
                displayArea.setText("Error reading: " + e.getMessage());
            }
        }
    }

    // loads previously serialized StudentRecord objects from the .ser file
    @SuppressWarnings("unchecked")
    private ArrayList<StudentRecord> loadSerializedRecords() {
        ArrayList<StudentRecord> records = new ArrayList<>();
        File f = new File(SER_FILE);
        if (f.exists()) {
            try {
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);
                records = (ArrayList<StudentRecord>) ois.readObject();
                ois.close();
                fis.close();
            } catch (Exception e) {
                // if something goes wrong, just start fresh
            }
        }
        return records;
    }

    // saves the list of StudentRecord objects to the .ser file
    private void saveSerializedRecords(ArrayList<StudentRecord> records) {
        try {
            FileOutputStream fos = new FileOutputStream(SER_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(records);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Error saving serialized data: " + e.getMessage());
        }
    }

    private static void runGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        StudentSemesterAverage app = new StudentSemesterAverage();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                runGUI();
            }
        });
    }
}

// Separate class for storing student data
// Implements Serializable so objects can be saved/loaded from a file
class StudentRecord implements Serializable {

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

/*
 * === SCREEN DUMPS 
 *
 * Test Case 1: Save Student Data
 * --------------------------------
 * Student Name: ethan
 * Grade Level: 12
 * Semester Number: 2
 * Grade 1: 50
 * Grade 2: 49
 * Grade 3: 70
 * Grade 4: 30
 * [Clicked "Save to File"]
 * Average: 49.8%
 * Dialog: "Data saved successfully!"
 *
 * Test Case 2: Save Another Student
 * -----------------------------------
 * Student Name: Kevin Henderson
 * Grade Level: 12
 * Semester Number: 2
 * Grade 1: 77
 * Grade 2: 83
 * Grade 3: 92
 * Grade 4: 65
 * [Clicked "Save to File"]
 * Average: 79.2%
 * Dialog: "Data saved successfully!"
 *
 * Test Case 3: View File Contents
 * ---------------------------------
 * [Clicked "View File Contents"]
 * Text Area displays:
 * Name: ethan, Grade Level: 12, Semester: 2, Grades: 50.0, 49.0, 70.0, 30.0, Average: 49.8%
 * Name: Kevin Henderson, Grade Level: 12, Semester: 2, Grades: 77.0, 83.0, 92.0, 65.0, Average: 79.2%
 *
 * Test Case 4: Invalid Input
 * ----------------------------
 * Grade 1: abc
 * [Clicked "Save to File"]
 * Dialog: "Please enter valid numbers for all grades."
 *
 *
 */
