import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    void gameInitializeTest() {
        //test if constructor sets values, including random word, correctly
        Game game = new Game();

        //print word just for me to see which one it picks
        System.out.println(game.getWordToGuess().getWordToGuess());

        Assertions.assertNotNull(game.getWordToGuess());
        Assertions.assertEquals(10, game.getLives());
        Assertions.assertEquals(Game.GameStatus.INPROGRESS, game.getGameStatus());
    }

}
