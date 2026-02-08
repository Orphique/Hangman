/*  Group 4
Deng Yonghan (10271600)
Bo HaoTian (10270562)
Dylan Carson Sisnawan (10270412)
Muhammad Farhan Bin Rosni (10265771)
Matthew Albert Cahyadi(10270548)
*/

public class HangmanBasic {
    public static void main(String[] args) {

        // Instance: Store the guessing words to be used in the Hangman game
        WordLoader loader = new WordLoader();

        // Instance: Display the user interface of the Hangman game.
        GameUI ui = new GameUI();

        // Select random word from WordLoader
        String wordToGuess = loader.selectRandomWord();

        // Implement the rules of the Hangman game
        GameLogic game = new GameLogic(wordToGuess);

        // Display Welcome scene
        ui.displayWelcome();
        while (!game.isGameOver()) {
            // Display result of each guess
            ui.displayGameState(game);

            // Player guess one letter at a time.
            game.guessLetter(ui.getGuessFromUser(game));
        }

        // Display win or lose scene
        ui.displayResult(game);
    }
}
