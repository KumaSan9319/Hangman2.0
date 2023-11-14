import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WordToGuessTest {

    @Test
    void prepareWordToGuessTest(){
        //test if method can still connect to external file and prepare a random word
        WordToGuess word = new WordToGuess();
        System.out.println(word.getWordToGuess());

        Assertions.assertNotNull(word.getWordToGuess());
    }

}
