package Mastery;

/*
 * 
 * 
 * 
 * 
 * 
 * 
 */


// WordGuess.java
// Author: Ethan
// Date: March 18, 2026
// Plays a word guessing game. Reads a random secret word from words.txt,
// then lets the player guess letters one at a time. Player starts with
// 100 points and loses 10 each guess. Type ! to guess the whole word.

import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WordGuess {

    public static void main(String[] args) {
        final String FLAG = "!";
        final int START_SCORE = 100;
        final int PENALTY = 10;

        // file reading stuff
        File wordFile;
        FileReader fr;
        BufferedReader reader;
        Random rand = new Random();

        int numWords, wordToGuess;
        String secretWord = "";
        String wordSoFar = "", updatedWord = "";
        String letterGuess, wordGuess = "";
        int numGuesses = 0;
        int score = START_SCORE;

        Scanner input = new Scanner(System.in);

        // read the secret word from the file
        try {
            wordFile = new File("words.txt");
            fr = new FileReader(wordFile);
            reader = new BufferedReader(fr);

            // first line has the number of words
            numWords = Integer.parseInt(reader.readLine().trim());

            // pick a random word
            wordToGuess = rand.nextInt(numWords) + 1;

            // skip lines until we get to the one we want
            for (int i = 0; i < wordToGuess; i++) {
                secretWord = reader.readLine().trim();
            }

            secretWord = secretWord.toUpperCase();

            reader.close();
            fr.close();

        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find words.txt - make sure it's in the right folder.");
            return;
        } catch (IOException e) {
            System.out.println("Something went wrong reading the file: " + e.getMessage());
            return;
        }

        // start the game
        System.out.println("WordGuess game.\n");

        // show dashes for each letter
        for (int i = 0; i < secretWord.length(); i++) {
            wordSoFar += "-";
        }
        System.out.println(wordSoFar);
        System.out.println("Score: " + score + "\n");

        // main game loop
        do {
            System.out.print("Enter a letter (" + FLAG + " to guess entire word): ");
            letterGuess = input.nextLine();
            letterGuess = letterGuess.toUpperCase();

            numGuesses++;
            score -= PENALTY;

            // if they typed a letter (not the flag), check it against the word
            if (!letterGuess.equals(FLAG)) {
                updatedWord = "";
                for (int i = 0; i < secretWord.length(); i++) {
                    if (String.valueOf(secretWord.charAt(i)).equals(letterGuess)) {
                        updatedWord += letterGuess;
                    } else {
                        updatedWord += wordSoFar.charAt(i);
                    }
                }
                wordSoFar = updatedWord;
            }

            System.out.println(wordSoFar);
            System.out.println("Score: " + score + "\n");

            // if score hits 0, game over
            if (score <= 0) {
                System.out.println("Your score hit 0!");
                break;
            }

        } while (!letterGuess.equals(FLAG) && !wordSoFar.equals(secretWord));

        // handle word guess if they typed !
        if (letterGuess.equals(FLAG) && score > 0) {
            System.out.print("What is your guess? ");
            wordGuess = input.nextLine();
            wordGuess = wordGuess.toUpperCase();
        }

        // win or lose
        if (wordGuess.equals(secretWord) || wordSoFar.equals(secretWord)) {
            System.out.println("You won!");
        } else {
            System.out.println("Sorry. You lose.");
        }

        System.out.println("The secret word is " + secretWord);
        System.out.println("You made " + numGuesses + " guesses.");
        System.out.println("Final score: " + score);

        input.close();
    }
}

/*
 * = SCREEN DUMPS
 *
 * Test Case 1: Win by guessing all letters
 * -------------------------------------------
 * WordGuess game.
 *
 * -----
 * Score: 100
 *
 * Enter a letter (! to guess entire word): b
 * B----
 * Score: 90
 *
 * Enter a letter (! to guess entire word): r
 * BR---
 * Score: 80
 *
 * Enter a letter (! to guess entire word): a
 * BRA--
 * Score: 70
 *
 * Enter a letter (! to guess entire word): i
 * BRAI-
 * Score: 60
 *
 * Enter a letter (! to guess entire word): n
 * BRAIN
 * Score: 50
 *
 * You won!
 * The secret word is BRAIN
 * You made 5 guesses.
 * Final score: 50
 *
 * Test Case 2: Win by guessing the word with !
 * -----------------------------------------------
 * WordGuess game.
 *
 * -----
 * Score: 100
 *
 * Enter a letter (! to guess entire word): h
 * H----
 * Score: 90
 *
 * Enter a letter (! to guess entire word): e
 * HE---
 * Score: 80
 *
 * Enter a letter (! to guess entire word): !
 * HE---
 * Score: 70
 *
 * What is your guess? hello
 * You won!
 * The secret word is HELLO
 * You made 3 guesses.
 * Final score: 70
 *
 * Test Case 3: Lose by wrong word guess
 * ----------------------------------------
 * WordGuess game.
 *
 * -----
 * Score: 100
 *
 * Enter a letter (! to guess entire word): s
 * S----
 * Score: 90
 *
 * Enter a letter (! to guess entire word): !
 * S----
 * Score: 80
 *
 * What is your guess? smile
 * Sorry. You lose.
 * The secret word is STORM
 * You made 2 guesses.
 * Final score: 80
 *
 * Test Case 4: Lose by score reaching 0
 * ----------------------------------------
 * WordGuess game.
 *
 * -----
 * Score: 100
 *
 * Enter a letter (! to guess entire word): z
 * -----
 * Score: 90
 *
 * Enter a letter (! to guess entire word): x
 * -----
 * Score: 80
 *
 * Enter a letter (! to guess entire word): q
 * -----
 * Score: 70
 *
 * Enter a letter (! to guess entire word): w
 * -----
 * Score: 60
 *
 * Enter a letter (! to guess entire word): v
 * -----
 * Score: 50
 *
 * Enter a letter (! to guess entire word): j
 * -----
 * Score: 40
 *
 * Enter a letter (! to guess entire word): k
 * -----
 * Score: 30
 *
 * Enter a letter (! to guess entire word): f
 * -----
 * Score: 20
 *
 * Enter a letter (! to guess entire word): y
 * -----
 * Score: 10
 *
 * Enter a letter (! to guess entire word): p
 * -----
 * Score: 0
 *
 * Your score hit 0!
 * Sorry. You lose.
 * The secret word is DANCE
 * You made 10 guesses.
 * Final score: 0
 *
 *
 */
