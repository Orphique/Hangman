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

        drawHangman(game.getIncorrectGuesses());

        DisplayWord = game.getDisplayWord();
        System.out.printf("Word: %s%n",DisplayWord);

        System.out.printf("Incorrect Guesses: %d/%d%n",game.getIncorrectGuesses(), game.getMaxIncorrectGuesses());

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
}