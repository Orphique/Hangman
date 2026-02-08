/*  Group 4
Deng Yonghan (10271600)
Bo HaoTian (10270562)
Dylan Carson Sisnawan (10270412)
Muhammad Farhan Bin Rosni (10265771)
Matthew Albert Cahyadi(10270548)
*/

package Additional;

public class HangmanAdditional {
    public static void main(String[] args) {
        boolean continuing;

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
                    String selectedWord = ui.wordOption();
                    String wordToGuess = selectedWord == null ? loader.selectRandomWord() : selectedWord;

                    // Instance: Implement the rules of the Hangman game
                    GameLogic game = new GameLogic(wordToGuess);
                    while (!game.isGameOver()) {
                        // Display result of each guess
                        ui.displayGameState(game);

                        // Player guess one letter at a time.
                        game.guessLetter(ui.getGuessFromUser(game));
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
                    String selectedWord2 = ui.wordOption();
                    String wordToGuess2 = selectedWord2 == null ? loader.selectRandomWord() : selectedWord2;


                    // Player 2 turn to pick player 1 guess word
                    ui.playerTurn(2);
                    // Display option for player 1 guessing word
                    // 1) Random word
                    // 2) Pick a word
                    String selectedWord1 = ui.wordOption();
                    String wordToGuess1 = selectedWord1 == null ? loader.selectRandomWord() : selectedWord1;

                    // Instance: Implement the rules of the Hangman game
                    GameLogic game1 = new GameLogic(wordToGuess1, "player1");
                    GameLogic game2 = new GameLogic(wordToGuess2, "player2");
                    while (!game1.isGameOver() || !game2.isGameOver()) {
                        if(!game1.isLost()){
                            // Player 1 turn
                            ui.playerTurn(1);
                            // Display result of player 1 guess
                            ui.displayGameState(game1);
                            // Player 1 guess one letter at a time.
                            game1.guessLetter(ui.getGuessFromUser(game1));
                            // Player 1 win first
                            if (game1.isWon()) break;
                        }
                        if(!game2.isLost()) {
                            // Player 2 turn
                            ui.playerTurn(2);
                            // Display result of player 2 guess
                            ui.displayGameState(game2);
                            // Player2 guess one letter at a time.
                            game2.guessLetter(ui.getGuessFromUser(game2));
                        }
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
                    String selectedWordAll = ui.wordOption();
                    String wordToGuessAll = selectedWordAll == null ? loader.selectRandomWord() : selectedWordAll;

                    // Create instance per player
                    GameLogic[] games = new GameLogic[numPlayer];
                    for(int i = 1; i <= numPlayer; i++) {
                        games[i-1] = new GameLogic(wordToGuessAll, "player"+i);
                    }
                    boolean end = true;
                    while (end) {
                        int turn = 1;
                        int alive = games.length;
                        for (GameLogic Player : games) {
                            // Still continue if one of player loses
                            if (Player.isLost()) {
                                alive--;
                                continue;
                            }
                            ui.playerTurn(turn++);
                            System.out.println(alive);
                            ui.displayGameState(Player);
                            Player.guessLetter(ui.getGuessFromUser(Player));
                            // someone won first
                            if(Player.isWon() ) {
                                end = false;
                                break;
                            }
                        }
                        // Draw scenario
                        if(alive == 0 ) break;
                    }
                    ui.displayResult2P(games);
                    break;
            }
            // Ask player to continue or not
            continuing = ui.offerToContinue();
        } while (continuing);
    }
}
