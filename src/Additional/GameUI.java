package Additional;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
    public char getGuessFromUser(GameLogic game) {
        while(true) {
            try {
                Scanner scnr = new Scanner(System.in);
                System.out.print("Guess a letter: ");
                String word = scnr.next(".").toUpperCase();
                if (game.getGuessedLetters().contains(word.charAt(0))) throw new Exception();
                return word.charAt(0); // Only one character
            } catch (InputMismatchException e) {
                System.out.println("Only 1 letter. Try again");
            } catch (Exception e) {
                System.out.println("Already guessed. Try again");
            }
        }
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

    public boolean offerToContinue(){
        System.out.print("Do you want to continue ?(y/n) ");
        String answer = scanner.next().toLowerCase();
        return answer.equals("y");
    }

    // Game mode option logic
    public int modeOption() {
        System.out.println("1) Single player");
        System.out.println("2) 2 player");
        System.out.println("3) Multiplayer");
        int mode = 0;
        while(true) {
            try {
                Scanner scnr = new Scanner(System.in);
                System.out.print("Select mode: ");
                mode = scnr.nextInt();
                if (mode < 1 || mode > 3) throw new Exception();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Not a number, try again");
            } catch (Exception e) {
                System.out.println("Only 1, 2 or 3. Try again");
            }
        }
        return mode;
    }

    public String wordOption(){
        System.out.println("1) Random word");
        System.out.println("2) Pick a word");
        System.out.print("Select mode: ");
        int wording = 0;
        while(true) {
            try {
                Scanner scnr = new Scanner(System.in);
                System.out.print("Select mode: ");
                wording = scnr.nextInt();
                if (wording < 1 || wording > 2) throw new Exception();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Not a number, try again");
            } catch (Exception e) {
                System.out.println("Only 1 or 2 . Try again");
            }
        }
        if(wording == 2) {
            System.out.print("Enter phrase: ");
            return scanner.next().toUpperCase();
        }
        return "1";
    }

    public void playerTurn(int playerNum){
        System.out.println("--------------------------");
        System.out.println("player "+playerNum+" turn");
        System.out.println("--------------------------");
    }
    public void displayResult2P(GameLogic... players) {
        System.out.println("===== Game Over! =====");
        for (GameLogic player : players) {
            if (player.isWon()) {
                System.out.println("Congratulations, " + player.getPlayerName() + " have WON!");
            }
        }
    }

    public int PlayerNumber(){
        while(true) {
            try {
                Scanner scnr = new Scanner(System.in);
                System.out.print("How many player ?");
                return scnr.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Not a number, try again");
            }
        }
    }
}