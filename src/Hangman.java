// MAD 300 Lab 1-3
// Author: Michael Marcuzzi
// Date: 2025-09-25

import java.util.*;

public class Hangman {
    public static void main(String[] args) {
        // Variable init
        Scanner input = new Scanner(System.in);
        String[] words = {"write", "that", "program", "java", "computer", "hangman"};
        boolean cont = true;

        while (cont) {
            // Pick a random word and create a new array with equal length
            String word = words[(int) (Math.random()*words.length)];
            char[] guessedWord = new char[word.length()];
            // Fill with *'s
            for (int i=0; i<guessedWord.length; i++) {
                guessedWord[i] = '*';
            }

            // While the guessed word does not equal the actual word..
            int misses = 0;
            while (!String.valueOf(guessedWord).equals(word)) {
                System.out.print("(Guess) Enter a letter in word " + String.valueOf(guessedWord) + " > ");
                char guess = input.next().charAt(0);

                // If letter already guessed
                if (String.valueOf(guessedWord).indexOf(guess) >= 0) {
                    System.out.println(guess + " is already in the word");
                } else if (word.indexOf(guess) < 0) { // Wrong guess
                    System.out.println(guess + " is not in the word");
                    misses++;
                } else { // Correct guess
                    for (int i=0; i<word.length(); i++) {
                        if (word.charAt(i) == guess) {
                            guessedWord[i] = guess;
                        }
                    }
                }
            }
            // Word completed
            System.out.println("The word is " + word + ". You missed " + misses + (misses == 1 ? " time" : " times"));

            // Ask user to play again
            System.out.print("Do you want to guess another word? Enter y or n > ");
            char answer = input.next().charAt(0);
            if (answer != 'y') {
                cont = false;
            }
        }

        // Close input reader
        input.close();
    }
}