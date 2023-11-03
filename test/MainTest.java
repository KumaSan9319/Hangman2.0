import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

class MainTest {

    @BeforeEach
    void setUp() {
        Main.wordToGuess = "beertje";
    }

    @Test
    void testIfGameCanPrepareWord() {
        // fill arraylist with words from a txt file
        List<String> wordList = Main.prepareWords();
        // make wordToGuess a random word from the arraylist
        Main.wordToGuess = Main.prepareWordToGuess(wordList);

        // testing if wordToGuess actually gets turned into a workable string
        Assertions.assertNotNull(Main.wordToGuess);
    }

    @Test
    void testIfGamePrintsStateCorrectly() {
        // adding a letter to playerGuesses to test the output
        Main.playerGuesses.add('e');

        // creating a PrintStream to be able to test the console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // testing the output of printWordProgress
        Main.printWordProgress();
        Assertions.assertEquals("_EE___E", outContent.toString().trim());
    }

    @Test
    void testIfGuessedLetterListIsPrintedCorrectly() {
        // adding a letter to playerGuesses to test the output
        Main.playerGuesses.add('e');

        // creating a PrintStream to be able to test the console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // testing the output of printGuessedLetterList
        Main.printGuessedLetterList();
        Assertions.assertEquals("Letters you've guessed: E", outContent.toString().trim());
    }

    @Test
    void testIfRemainingLivesArePrintedCorrectly() {
        Main.lives = 8;

        // creating a PrintStream to be able to test the console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // testing the output of printCurrentLives
        Main.printCurrentLives();
        Assertions.assertEquals("Guesses left: 8", outContent.toString().trim());

        Main.lives = 10;
    }

    @Test
    void testCharacterUserInput() {
        // setting simulated user input
        String simulatedInput = "a";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // testing whether getPlayerInput correctly goes into characterGuess and adds the input to playerGuesses
        Main.getPlayerInput();
        Assertions.assertTrue(Main.playerGuesses.contains('a'));
        Assertions.assertEquals(9, Main.lives);

        Main.lives = 10;
    }

    @Test
    void testCorrectWordUserInput() {
        // setting simulated user input
        String simulatedInput = "beertje";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // testing whether getPlayerInput correctly goes into wordGuess and ends the game if guess was correct
        Main.getPlayerInput();
        Assertions.assertTrue(Main.gameOver);
    }

    @Test
    void testIncorrectWordUserInput() {
        // setting simulated user input
        String simulatedInput = "vlegel";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // testing whether an incorrect word guess correctly takes away a life
        Main.getPlayerInput();
        Assertions.assertEquals(Main.lives, 9);
    }

    @Test
    void testEndingGame() {
        Main.gameOver = false;

        Main.lives = 3;
        Main.gameLost();
        Assertions.assertFalse(Main.gameOver);

        Main.lives = 0;
        Main.gameLost();
        Assertions.assertTrue(Main.gameOver);

        Main.lives = 10;
    }
}