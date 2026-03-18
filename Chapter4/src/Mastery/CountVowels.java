package Mastery;

/*
 * CountVowels.java
 * Author: Ethan
 * Date: March 18, 2026
 * Description: Prompts the user for a file name, reads the text file,
 *              and counts the number of vowels (a, e, i, o, u) in it.
 */

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Counts the number of vowels in a text file specified by the user.
 */
public class CountVowels {

    public static void main(String[] args) {
        // Variables for file reading
        File textFile;
        FileReader in;
        BufferedReader readFile;
        String fileName;
        String lineInFile, lowercaseText, letter;
        int vowelSum = 0;

        // Create Scanner for user input
        Scanner input = new Scanner(System.in);

        /* prompt the user for the name of the file */
        System.out.print("Enter the file name: ");
        fileName = input.nextLine();

        /* count the vowels in the file */
        try {
            // Create File, FileReader, and BufferedReader objects
            textFile = new File(fileName);
            in = new FileReader(textFile);
            readFile = new BufferedReader(in);

            // Read lines from the file
            lineInFile = readFile.readLine();
            while (lineInFile != null) {
                // Convert the line to lowercase for comparison
                lowercaseText = lineInFile.toLowerCase();

                // Iterate through each character in the line
                for (int i = 0; i < lowercaseText.length(); i++) {
                    // Get the character as a String
                    letter = String.valueOf(lowercaseText.charAt(i));

                    // Check if the character is a vowel
                    if (letter.equals("a") || letter.equals("e") ||
                        letter.equals("i") || letter.equals("o") ||
                        letter.equals("u")) {
                        vowelSum++;  // update total vowels
                    }
                }

                // Read the next line
                lineInFile = readFile.readLine();
            }

            // Display the number of vowels found in the file
            System.out.println("The number of vowels in " + fileName + " is " + vowelSum);

            // Close readers
            readFile.close();
            in.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        input.close();
    }
}
