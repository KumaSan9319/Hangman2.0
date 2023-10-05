import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class Main {

    private static String wordToGuess;
    private static int lives = 10;
    private static boolean gameOver = false;
    private static final List<Character> playerGuesses = new ArrayList<>();


    public static void main(String[] args) throws FileNotFoundException {

    List<String> wordList = prepareWords();
    wordToGuess = prepareWordToGuess(wordList);

    while (!gameOver) {
        printWordProgress();
        printGuessedLetterList();
        printCurrentLives();
        getPlayerInput();
        gameLost();
    }

    }

    // Import external file and prepare list of words to pick from
    public static List<String> prepareWords() throws FileNotFoundException {
        List<String> wordList = new ArrayList<>();

//        File dictionary = new File("resources/dictionary.txt");
//
//        Scanner fileScanner = new Scanner(dictionary);
//
//        while (fileScanner.hasNextLine()) {
//            wordList.add(fileScanner.nextLine());
//        }
//
//        return wordList;


        // code die ik nog had geprobeerd gebaseerd op jou pointer:

        try (InputStream inputStream = Main.class.getClassLoader()
                .getResourceAsStream("resources/dictionary.txt")){
            Scanner fileScanner = new Scanner(inputStream);

            while (fileScanner.hasNextLine()) {
                wordList.add(fileScanner.nextLine());
            }

            fileScanner.close();

        } catch (Exception e) {
            System.out.println("Wordlist not found, exiting game.");
            System.exit(0);
        }
        return wordList;
    }

    // Set a random word from the list as the word to guess
    public static String prepareWordToGuess(List<String> list) {
        return list.get((new Random()).nextInt(list.size()));
    }

    // Prints correctly guessed letters or underscores where letters have not been guessed yet
    public static void printWordProgress() {
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (playerGuesses.contains(wordToGuess.charAt(i))) {
                System.out.print(Character.toUpperCase(wordToGuess.charAt(i)));
            } else {
                System.out.print("_");
            }
        }

        System.out.println();
    }

    // Prints all the letters the player has guessed
    public static void printGuessedLetterList() {
        System.out.print("Letters you've guessed: ");

        for (char guesses : playerGuesses) {
            System.out.print(Character.toUpperCase(guesses) + " ");
        }

        System.out.println();
    }

    // Prints the amount of guesses player has left
    public static void printCurrentLives() {
        System.out.println("Guesses left: " + lives);
    }

    // Takes player guess and checks if letter guess or word guess
    public static void getPlayerInput() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your guess: ");

        String playerInput = input.nextLine();

        if (playerInput.length() == 1) characterGuess(playerInput);
        if (playerInput.length() > 1) wordGuess(playerInput);
    }

    // Adds letter input to list of guesses and takes away a life if the letter wasn't in wordToGuess
    public static void characterGuess(String playerInput) {
        playerGuesses.add(playerInput.charAt(0));

        if (!wordToGuess.contains(playerInput)) lives--;
    }

    // Checks if guess is the correct word and takes away a life if it isn't
    public static void wordGuess(String playerInput) {
        if (playerInput.equals(wordToGuess)) {
            System.out.println("Correct! You win!");
            gameOver = true;
        } else {
            System.out.println("Too bad, that was not the word.");
            lives--;
        }
    }

    // Ends the game when lives gets to 0
    public static void gameLost() {
        if (lives == 0) {
            System.out.println("You're out of guesses, Game Over!");
            System.out.println("The word was: " + wordToGuess);

            gameOver = true;
        }
    }

}
