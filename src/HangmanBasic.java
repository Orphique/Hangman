public class HangmanBasic {
    public static void main(String[] args) {
        WordLoader loader = new WordLoader();

        GameUI ui = new GameUI();

        String wordToGuess = loader.selectRandomWord();

        GameLogic game = new GameLogic(wordToGuess);

        ui.displayWelcome();
        while (!game.isGameOver()) {
            ui.displayGameState(game);
            game.guessLetter(ui.getGuessFromUser());

        }
        ui.displayResult(game);
    }
}
