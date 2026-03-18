package Mastery;

/*
 * StudentSemesterAverage.java
 * Author: Ethan
 * Date: March 18, 2026
 * Description: A GUI application that allows the user to enter a student's
 *              name, grade level, semester number, and four grades. The app
 *              calculates the average, saves data to a file, and can display
 *              all saved records from the file.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * Student Grade Application GUI.
 * Collects student info and grades, calculates average,
 * saves to file, and displays file contents.
 */
public class StudentSemesterAverage implements ActionListener {

    // GUI components
    JFrame frame;
    JPanel topPanel, bottomPanel, buttonPanel;
    JLabel nameLabel, gradeLeveLabel, semesterLabel;
    JLabel grade1Label, grade2Label, grade3Label, grade4Label;
    JLabel averageLabel;
    JTextField nameField, gradeLevelField, semesterField;
    JTextField grade1Field, grade2Field, grade3Field, grade4Field;
    JTextArea displayArea;
    JButton saveButton, viewButton;

    // File name for saving student data
    final String FILE_NAME = "studentData.txt";

    /**
     * Constructor - builds the GUI
     */
    public StudentSemesterAverage() {
        /* Create and set up the frame */
        frame = new JFrame("Student Grade Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        /* ---- Top Panel: labels, text fields, and average ---- */
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(8, 2, 5, 2));
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Student Name
        nameLabel = new JLabel("Student Name:");
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        topPanel.add(nameLabel);
        nameField = new JTextField(15);
        topPanel.add(nameField);

        // Grade Level
        gradeLeveLabel = new JLabel("Grade Level:");
        gradeLeveLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        topPanel.add(gradeLeveLabel);
        gradeLevelField = new JTextField(15);
        topPanel.add(gradeLevelField);

        // Semester Number
        semesterLabel = new JLabel("Semester Number:");
        semesterLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        topPanel.add(semesterLabel);
        semesterField = new JTextField(15);
        topPanel.add(semesterField);

        // Grade 1
        grade1Label = new JLabel("Grade 1:");
        grade1Label.setFont(new Font("SansSerif", Font.BOLD, 12));
        topPanel.add(grade1Label);
        grade1Field = new JTextField(15);
        topPanel.add(grade1Field);

        // Grade 2
        grade2Label = new JLabel("Grade 2:");
        grade2Label.setFont(new Font("SansSerif", Font.BOLD, 12));
        topPanel.add(grade2Label);
        grade2Field = new JTextField(15);
        topPanel.add(grade2Field);

        // Grade 3
        grade3Label = new JLabel("Grade 3:");
        grade3Label.setFont(new Font("SansSerif", Font.BOLD, 12));
        topPanel.add(grade3Label);
        grade3Field = new JTextField(15);
        topPanel.add(grade3Field);

        // Grade 4
        grade4Label = new JLabel("Grade 4:");
        grade4Label.setFont(new Font("SansSerif", Font.BOLD, 12));
        topPanel.add(grade4Label);
        grade4Field = new JTextField(15);
        topPanel.add(grade4Field);

        // Average (label only, no text field)
        averageLabel = new JLabel("Average:");
        averageLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        topPanel.add(averageLabel);
        topPanel.add(new JLabel("")); // empty placeholder

        frame.add(topPanel, BorderLayout.NORTH);

        /* ---- Center Panel: text area for displaying file contents ---- */
        displayArea = new JTextArea(8, 40);
        displayArea.setEditable(false);
        displayArea.setFont(new Font("SansSerif", Font.PLAIN, 11));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        frame.add(scrollPane, BorderLayout.CENTER);

        /* ---- Bottom Panel: buttons ---- */
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

        /* Size and display the frame */
        frame.setSize(520, 450);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Handle button click action events
     * pre: none
     * post: Save calculates average, saves to file, shows confirmation.
     *       View reads saved data from file and displays in text area.
     */
    public void actionPerformed(ActionEvent event) {
        String eventName = event.getActionCommand();

        if (eventName.equals("Save")) {
            // Validate input and calculate average
            try {
                String name = nameField.getText();
                String gradeLevel = gradeLevelField.getText();
                String semester = semesterField.getText();

                double g1 = Double.parseDouble(grade1Field.getText());
                double g2 = Double.parseDouble(grade2Field.getText());
                double g3 = Double.parseDouble(grade3Field.getText());
                double g4 = Double.parseDouble(grade4Field.getText());

                // Calculate average
                double avg = (g1 + g2 + g3 + g4) / 4.0;

                // Round to one decimal place
                avg = Math.round(avg * 10.0) / 10.0;

                // Display average on the label
                averageLabel.setText("Average: " + avg + "%");

                // Save to file (append mode)
                try {
                    FileWriter fw = new FileWriter(FILE_NAME, true);
                    BufferedWriter bw = new BufferedWriter(fw);

                    bw.write("Name: " + name + ", Grade Level: " + gradeLevel
                            + ", Semester: " + semester
                            + ", Grades: " + g1 + ", " + g2 + ", " + g3 + ", " + g4
                            + ", Average: " + avg + "%");
                    bw.newLine();

                    bw.close();
                    fw.close();

                    // Show success dialog
                    JOptionPane.showMessageDialog(frame,
                            "Data saved successfully!",
                            "Message",
                            JOptionPane.INFORMATION_MESSAGE);

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(frame,
                            "Error saving file: " + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame,
                        "Please enter valid numbers for all grades.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } else if (eventName.equals("View")) {
            // Read and display file contents
            try {
                File file = new File(FILE_NAME);
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                displayArea.setText(""); // clear previous contents
                String line = br.readLine();
                while (line != null) {
                    displayArea.append(line + "\n");
                    line = br.readLine();
                }

                br.close();
                fr.close();

            } catch (FileNotFoundException e) {
                displayArea.setText("No saved data found.");
            } catch (IOException e) {
                displayArea.setText("Error reading file: " + e.getMessage());
            }
        }
    }

    /**
     * Create and show the GUI.
     */
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
