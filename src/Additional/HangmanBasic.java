package Additional;

public class HangmanBasic {
    public static void main(String[] args) {
        boolean continuing = true;

        // Instance: Store the guessing words to be used in the Hangman game
        WordLoader loader = new WordLoader();

        // Instance: Display the user interface of the Hangman game.
        GameUI ui = new GameUI();

        // Display Welcome scene
        ui.displayWelcome();

        do {
            // Display option for game mode
            // 1) Single Player
            // 2) 2 Player
            // 3) Multiplayer
            int Mode = ui.modeOption();

            switch (Mode) {
                case 1:
                    // Display option for guessing word
                    // 1) Random word
                    // 2) Pick a word
                    String S = ui.wordOption();
                    String wordToGuess = S == "1" ? loader.selectRandomWord() : S;

                    // Instance: Implement the rules of the Hangman game
                    GameLogic game = new GameLogic(wordToGuess);
                    while (!game.isGameOver()) {
                        // Display result of each guess
                        ui.displayGameState(game);

                        // Player guess one letter at a time.
                        game.guessLetter(ui.getGuessFromUser());
                    }

                    // Display win or lose scene
                    ui.displayResult(game);
                    break;
                case 2:
                    // Player 1 turn to pick player 2 guess word
                    ui.playerTurn(1);
                    // Display option for player 2 guessing word
                    // 1) Random word
                    // 2) Pick a word
                    String S2 = ui.wordOption();
                    String wordToGuess2 = S2 == "1" ? loader.selectRandomWord() : S2;


                    // Player 2 turn to pick player 1 guess word
                    ui.playerTurn(2);
                    // Display option for player 1 guessing word
                    // 1) Random word
                    // 2) Pick a word
                    String S1 = ui.wordOption();
                    String wordToGuess1 = S1 == "1" ? loader.selectRandomWord() : S1;

                    // Instance: Implement the rules of the Hangman game
                    GameLogic game1 = new GameLogic(wordToGuess1, "player1");
                    GameLogic game2 = new GameLogic(wordToGuess2, "player2");
                    while (true) {
                        // Player 1 turn
                        ui.playerTurn(1);
                        // Display result of player 1 guess
                        ui.displayGameState(game1);
                        // Player 1 guess one letter at a time.
                        game1.guessLetter(ui.getGuessFromUser());
                        // Player 1 win first
                        if (game1.isWon()) break;

                        // Player 2 turn
                        ui.playerTurn(2);
                        // Display result of player 2 guess
                        ui.displayGameState(game2);
                        // Player2 guess one letter at a time.
                        game2.guessLetter(ui.getGuessFromUser());
                        // Player 2 win first
                        if (game2.isWon()) break;
                    }

                    // Display win or lose scene
                    ui.displayResult2P(game1, game2);
                    break;
                case 3:
                    // Ask how many player
                    int numPlayer = ui.PlayerNumber();

                    // Display option for guessing word
                    // 1) Random word
                    // 2) Pick a word
                    String sAll = ui.wordOption();
                    String wordToGuessAll = sAll == "1" ? loader.selectRandomWord() : sAll;

                    // Create instance per player
                    GameLogic[] games = new GameLogic[numPlayer];
                    for(int i = 1; i <= numPlayer; i++) {
                        games[i-1] = new GameLogic(wordToGuessAll, "player"+i);
                    }
                    boolean end = true;
                    while (end) {
                        int turn = 1;
                        for (GameLogic Player : games) {
                            ui.playerTurn(turn);
                            ui.displayGameState(Player);
                            Player.guessLetter(ui.getGuessFromUser());
                            if(Player.isWon()) {
                                end = false;
                                break;
                            }
                            turn++;
                        }
                    }
                    ui.displayResult2P(games);
                    break;
            }
            // Ask player to continue or not
            continuing = ui.offerToContinue();
        } while (continuing);
    }
}
