package Mastery;

// CountVowels.java
// Author: Ethan
// Date: March 18, 2026
// Asks the user for a file name then reads through it and counts
// how many vowels are in the file. Uses File, FileReader, and BufferedReader.

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CountVowels {

    public static void main(String[] args) {
        File textFile;
        FileReader in;
        BufferedReader readFile;
        String fileName;
        String lineInFile, lowercaseText, letter;
        int vowelSum = 0;

        Scanner input = new Scanner(System.in);

        // ask for the file name
        System.out.print("Enter the file name: ");
        fileName = input.nextLine();

        // try to open and read the file
        try {
            textFile = new File(fileName);
            in = new FileReader(textFile);
            readFile = new BufferedReader(in);

            // go through each line
            lineInFile = readFile.readLine();
            while (lineInFile != null) {
                lowercaseText = lineInFile.toLowerCase();

                // check each character to see if its a vowel
                for (int i = 0; i < lowercaseText.length(); i++) {
                    letter = String.valueOf(lowercaseText.charAt(i));

                    if (letter.equals("a") || letter.equals("e") ||
                        letter.equals("i") || letter.equals("o") ||
                        letter.equals("u")) {
                        vowelSum++;
                    }
                }

                lineInFile = readFile.readLine();
            }

            // show results
            System.out.println("The number of vowels in " + fileName + " is " + vowelSum);

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

/*
 *  SCREEN DUMPS 
 *
 * Test Case 1: Count vowels in testfile.txt
 * -------------------------------------------
 * Enter the file name: testfile.txt
 * The number of vowels in testfile.txt is 37
 *
 * Test Case 2: File not found
 * -------------------------------------------
 * Enter the file name: fakefile.txt
 * File not found: fakefile.txt
 *
 * Test Case 3: Count vowels in words.txt
 * -------------------------------------------
 * Enter the file name: words.txt
 * The number of vowels in words.txt is 13
 *
 * 
 */
