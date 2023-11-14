public class Game {

    public enum GameStatus {
        WON,
        LOST,
        INPROGRESS
    }

    private final WordToGuess wordToGuess;
    private final int lives;
    private final GameStatus gameStatus;

    public WordToGuess getWordToGuess() {
        return wordToGuess;
    }

    public int getLives() {
        return lives;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Game() {
        this.wordToGuess = new WordToGuess();
        this.lives = 10;
        this.gameStatus = GameStatus.INPROGRESS;
    }
}
