// MAD 300 Lab 1-3
// Author: Michael Marcuzzi
// Date: 2025-09-25
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
// Lab 1
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
}*/

/*
// Lab 2
public class Hangman {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Define 3 different word banks
        String[] countries = {"canada", "mexico", "italy", "germany", "hungary"};
        String[] food = {"pizza", "hotdog", "hamburger", "pasta", "potato"};
        String[] animals = {"kangaroo", "elephant", "tiger", "shark", "dolphin"};

        boolean cont = true;

        while (cont) {
            // Make menu
            System.out.println("\n===== Hangman Menu =====");
            System.out.println("1. Countries");
            System.out.println("2. Food");
            System.out.println("3. Animals");
            System.out.println("4. Exit");
            System.out.print("Choose a topic (1-4): ");

            // Get user input
            int choice = input.nextInt();
            input.nextLine(); // consume newline

            // Create array to store words in selected category
            String[] words;

            // Create menu switch
            switch (choice) {
                case 1:
                    words = countries;
                    break;
                case 2:
                    words = food;
                    break;
                case 3:
                    words = animals;
                    break;
                case 4:
                    System.out.println("Exiting game. Goodbye!");
                    cont = false;
                    continue;
                default:
                    System.out.println("Invalid choice, try again.");
                    continue;
            }

            // Run program
            playHangman(words, input);
        }

        input.close();
    }

    // Main program to handle game
    public static void playHangman(String[] words, Scanner input) {
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
    }
}*/

// Lab 3
public class Hangman {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean cont = true;

        while (cont) {
            System.out.println("\n===== Hangman Menu =====");
            System.out.println("1. Countries");
            System.out.println("2. Food");
            System.out.println("3. Animals");
            System.out.println("4. Exit");
            System.out.print("Choose a topic (1-4): ");

            int choice = input.nextInt();
            input.nextLine(); // consume newline

            String filename;

            switch (choice) {
                case 1:
                    filename = "countries.txt";
                    break;
                case 2:
                    filename = ".idea/food.txt";
                    break;
                case 3:
                    filename = "animals.txt";
                    break;
                case 4:
                    System.out.println("Exiting game. Goodbye!");
                    cont = false;
                    continue;
                default:
                    System.out.println("Invalid choice, try again.");
                    continue;
            }

            // Load words from the selected file
            List<String> words = loadWordsFromFile(filename);

            if (words.isEmpty()) {
                System.out.println("No words found in " + filename + ". Please check the file.");
                continue;
            }

            playHangman(words, input);
        }

        input.close();
    }

    // Method to read words from a file
    public static List<String> loadWordsFromFile(String filename) {
        List<String> words = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
                String word = fileScanner.nextLine().trim().toLowerCase();
                if (!word.isEmpty()) {
                    words.add(word);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found -> " + filename);
        }
        return words;
    }

    public static void playHangman(List<String> words, Scanner input) {
        // Pick a random word and create a new array with equal length
        String word = words.get((int) (Math.random() * words.size()));
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
    }
}