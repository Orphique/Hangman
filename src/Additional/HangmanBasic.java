package Additional;

public class HangmanBasic {
    public static void main(String[] args) {

        // Instance: Store the guessing words to be used in the Hangman game
        WordLoader loader = new WordLoader();

        // Instance: Display the user interface of the Hangman game.
        GameUI ui = new GameUI();

        // Display Welcome scene
        ui.displayWelcome();

        // Display option for guessing word
        // 1) Random word
        // 2) Pick a word
        String S = ui.wordOption();
        String wordToGuess = S == "1" ? loader.selectRandomWord() : S;

        // Implement the rules of the Hangman game
        GameLogic game = new GameLogic(wordToGuess);

        while (!game.continuing) {
            while (!game.isGameOver()) {
                // Display result of each guess
                ui.displayGameState(game);

                // Player guess one letter at a time.
                game.guessLetter(ui.getGuessFromUser());
            }

            // Display win or lose scene
            ui.displayResult(game);
        }
    }
}
