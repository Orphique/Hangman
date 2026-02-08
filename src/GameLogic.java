/*  Group 4
Deng Yonghan (10271600)
Bo HaoTian (10270562)
Dylan Carson Sisnawan (10270412)
Muhammad Farhan Bin Rosni (10265771)
Matthew Albert Cahyadi(10270548)
*/
import java.util.ArrayList;

public class GameLogic {
    private static final int MAX_INCORRECT_GUESSES = 6;
    private final String hiddenWord;
    private String displayWord;
    private final ArrayList<Character> guessedLetters = new ArrayList<>();
    private int incorrectGuesses = 0;

    public GameLogic(String word) {
        // Constructor - assign hidden word and create series of underscores
        displayWord = word.replaceAll(".", "_");
        hiddenWord = word;
    }

    public boolean guessLetter(char guess) {
        // restricting guessedLetters
        if(String.valueOf(guessedLetters).contains(String.valueOf(guess))){
            return true;
        } else guessedLetters.add(guess);

        // manipulate displayWord string
        // Decide if guess wrong or correct
        if(hiddenWord.indexOf(guess) != -1){
            for(int i = 0; i < hiddenWord.length(); i++){
                //revealed all correct guess word in the correct positions within the word's representation
                if(guess == hiddenWord.charAt(i)){
                    displayWord = displayWord.substring(0,i) + guess + displayWord.substring(i+1);
                }
            }
        } else incorrectGuesses++; // Counted wrong guess
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

    // Decide win or lose
    public boolean isGameOver() {
        return isLost() || isWon();
    }

    // Won if there are no underscores left
    public boolean isWon() {
        return !displayWord.contains("_");
    }

    // Lost if there are no longer any attempt
    public boolean isLost() {
        return incorrectGuesses == MAX_INCORRECT_GUESSES;
    }
}
