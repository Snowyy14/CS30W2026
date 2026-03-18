package Mastery;

/*
 * WordGuess.java
 * Author: Ethan
 * Date: March 18, 2026
 * Description: A word guessing game where the player guesses letters of a
 *              secret word. The player starts with 100 points and loses 10
 *              points per guess. The game ends when the word is guessed,
 *              the player guesses the entire word, or the score reaches 0.
 */

import java.util.Scanner;

/**
 * Plays a word guessing game with one player.
 * The player guesses letters of the secret word "BRAIN".
 * Score starts at 100 and decreases by 10 for each guess.
 */
public class WordGuess {

    public static void main(String[] args) {
        // Constants
        final String SECRET_WORD = "BRAIN";
        final String FLAG = "!";
        final int STARTING_SCORE = 100;
        final int POINTS_PER_GUESS = 10;

        // Variables for tracking the game state
        String wordSoFar = "";       // the word displayed with dashes and guessed letters
        String updatedWord = "";     // used to build updated version of wordSoFar
        String letterGuess;          // the player's letter guess
        String wordGuess = "";       // the player's word guess (if they use !)
        int numGuesses = 0;          // total number of guesses made
        int score = STARTING_SCORE;  // player's current score

        // Create Scanner for user input
        Scanner input = new Scanner(System.in);

        /* begin game */
        System.out.println("WordGuess game.\n");

        // Generate dashes to represent the secret word
        for (int i = 0; i < SECRET_WORD.length(); i++) {
            wordSoFar += "-";  // one dash per letter
        }
        System.out.println(wordSoFar);                        // display dashes
        System.out.println("Score: " + score + "\n");         // display starting score

        /* allow player to make guesses */
        do {
            // Prompt user for a letter guess
            System.out.print("Enter a letter (" + FLAG + " to guess entire word): ");
            letterGuess = input.nextLine();
            letterGuess = letterGuess.toUpperCase();  // convert to uppercase

            // Increment number of guesses and update score
            numGuesses += 1;
            score -= POINTS_PER_GUESS;

            /*
             * If the player correctly guessed a letter, extract the string in
             * wordSoFar up to the letter guessed, append the guessed letter,
             * then append the rest of wordSoFar after the guessed letter.
             */
            if (!letterGuess.equals(FLAG)) {
                // Check every position in the secret word for the guessed letter
                updatedWord = "";
                for (int i = 0; i < SECRET_WORD.length(); i++) {
                    if (String.valueOf(SECRET_WORD.charAt(i)).equals(letterGuess)) {
                        updatedWord += letterGuess;  // replace dash with letter
                    } else {
                        updatedWord += wordSoFar.charAt(i);  // keep existing character
                    }
                }
                wordSoFar = updatedWord;
            }

            // Display current state of the word and score
            System.out.println(wordSoFar);
            System.out.println("Score: " + score + "\n");

            // Check if score has reached 0
            if (score <= 0) {
                System.out.println("Your score reached 0!");
                break;  // exit the loop, player loses
            }

        } while (!letterGuess.equals(FLAG) && !wordSoFar.equals(SECRET_WORD));

        /* finish game and display message and number of guesses */

        // If player chose to guess the entire word
        if (letterGuess.equals(FLAG) && score > 0) {
            System.out.print("What is your guess? ");
            wordGuess = input.nextLine();
            wordGuess = wordGuess.toUpperCase();  // convert to uppercase
        }

        // Determine win or loss
        if (wordGuess.equals(SECRET_WORD) || wordSoFar.equals(SECRET_WORD)) {
            System.out.println("You won!");
        } else {
            System.out.println("Sorry. You lose.");
        }

        // Display the secret word and number of guesses
        System.out.println("The secret word is " + SECRET_WORD);
        System.out.println("You made " + numGuesses + " guesses.");
        System.out.println("Your final score: " + score);

        input.close();
    }
}
