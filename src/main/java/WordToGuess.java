import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordToGuess {

    private final String wordToGuess;

    public WordToGuess() {
        this.wordToGuess = prepareWordToGuess(prepareWordList());
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    // Import external file and prepare list of words to pick from
    public List<String> prepareWordList() {
        List<String> wordList = new ArrayList<>();

        try (InputStream inputStream = Main.class.getClassLoader()
                .getResourceAsStream("dictionary.txt")){
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

}
