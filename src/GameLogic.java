import java.util.ArrayList;
import java.util.Arrays;

public class GameLogic {
    private static final int MAX_INCORRECT_GUESSES = 6;
    private final String hiddenWord;
    private String displayWord;
    private final ArrayList<Character> guessedLetters = new ArrayList<>();
    private int incorrectGuesses = 0;

    public GameLogic(String word) {
        displayWord = word.replaceAll(".", "_");
        hiddenWord = word;
    }

    public boolean guessLetter(char guess) {
        // restricting guessedLetters
        if(String.valueOf(guessedLetters).contains(String.valueOf(guess))){
            return true;
        } else guessedLetters.add(guess);

        // manipulate displayWord string
        if(hiddenWord.indexOf(guess) != -1){
            for(int i = 0; i < hiddenWord.length(); i++){
                if(guess == hiddenWord.charAt(i)){
                    displayWord = displayWord.substring(0,i) + guess + displayWord.substring(i+1);
                }
            }
        } else incorrectGuesses++;
        return true;
    }

    public String getDisplayWord() {
        return displayWord;
    }

    public String getHiddenWord() {
        return hiddenWord;
    }

    public ArrayList<Character> getGuessedLetters() {
        return guessedLetters;
    }

    public int getIncorrectGuesses() {
        return incorrectGuesses;
    }

    public int getMaxIncorrectGuesses() {
        return MAX_INCORRECT_GUESSES;
    }

    public boolean isGameOver() {
        return isLost() || isWon();
    }

    public boolean isWon() {
        return !displayWord.contains("_");
    }

    public boolean isLost() {
        return incorrectGuesses == MAX_INCORRECT_GUESSES;
    }
}
