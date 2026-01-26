package Additional;

import java.util.ArrayList;
import java.util.Scanner;
public class GameUI {
    private String DisplayWord;
    private final Scanner scanner = new Scanner(System.in);

    // Display welcome scene
    public void displayWelcome() {
        System.out.println("===== Welcome to Hangman game! =====");
    }

    // Display guess result
    public void displayGameState(GameLogic game) {
        System.out.println("------------------------------------");

        // Hangman
        drawHangman(game.getIncorrectGuesses());

        // Display underscores word
        DisplayWord = game.getDisplayWord();
        System.out.printf("Word: %s%n",DisplayWord);

        // Display incorrect guesses attempt
        System.out.printf("Incorrect Guesses: %d/%d%n",game.getIncorrectGuesses(), game.getMaxIncorrectGuesses());
        // Display incorrect guesses character
        System.out.println(formatGuessedLetters(game.getGuessedLetters()));
    }

    // Received the guessed letter from player
    public char getGuessFromUser() {
        System.out.print("Guess a letter: ");
        String word = scanner.next(".").toUpperCase();
        return word.charAt(0); // Only one character
    }

    // Display win or lose
    public void displayResult(GameLogic game) {
        System.out.println("===== Game Over! =====");
        if(game.isWon()){
            System.out.println("Congratulations, you have WON!");
        }else {
            System.out.printf("Sorry, you LOST! The word was: %s%n", game.getHiddenWord());
            drawHangman(game.getIncorrectGuesses());
        }
        game.Continue(offerToContinue());
    }

    // Revealed the already used letters
    private String formatGuessedLetters(ArrayList<Character> letters) {
        String guess = "Used Letters: ";

        // loop through letters array one by one
        guess += letters.toString().replace("[", "").replace("]", "");

        guess += "\n------------------------------------";
        return guess;
    }

    // Display hangman and its stage
    private void drawHangman(int stage) {
        System.out.println("+---+");
        if (stage == 0) System.out.println("|   | \n| \n| \n| \n|");
        else if (stage == 1) System.out.println("|   | \n|   O \n| \n| \n|");
        else if (stage == 2) System.out.println("|   | \n|   O \n|   | \n| \n|");
        else if (stage == 3) System.out.println("|   | \n|   O \n|  /| \n| \n|");
        else if (stage == 4) System.out.println("|   | \n|   O \n|  /|\\ \n| \n|");
        else if (stage == 5) System.out.println("|   | \n|   O \n|  /|\\ \n|  / \n|");
        else System.out.println("|   | \n|   O \n|  /|\\ \n|  / \\ \n|");
        System.out.println("=======");
    }

    private char offerToContinue(){
        System.out.print("Do you want to continue ?(y/n) ");
        String answer = scanner.next().toLowerCase();
        return answer.charAt(0);
    }


    // Game mode option logic
    public void modeOption() {
        System.out.println("1) 2 Player");
        System.out.println("2) Multiplayer");
        System.out.println("3) Single player");
        System.out.print("Select mode: ");
        int mode = scanner.nextInt();
        while (mode < 1 || mode > 3) {
            System.out.print("Try again. Select mode: ");
            mode = scanner.nextInt();
        }
        System.out.println();
    }
    public String wordOption(){
        System.out.println("1) Random word");
        System.out.println("2) Pick a word");
        System.out.print("Select mode: ");
        int wording = scanner.nextInt();
        while(wording < 1 || wording > 2){
            System.out.println("Try again. Select mode: ");
            wording = scanner.nextInt();
        }
        if(wording == 2) {
            System.out.print("Enter phrase: ");
            return scanner.next().toUpperCase();
        }
        return "1";
    }

}