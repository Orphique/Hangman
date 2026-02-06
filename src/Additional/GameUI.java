package Additional;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
        System.out.println("------------------------------------");
    }

    // Received the guessed letter from player
    public char getGuessFromUser(GameLogic game) {
        while(true) {
            try {
                System.out.print("Guess a letter: ");
                String word = scanner.next(".").toUpperCase();
                if (!Character.isLetter(word.charAt(0))) throw new Error();
                if (game.getGuessedLetters().contains(word.charAt(0))) throw new Exception();
                return word.charAt(0); // Only one character
            } catch (InputMismatchException e) {
                System.out.println("Only 1 letter. Try again");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Already guessed. Try again");
                scanner.nextLine();
            } catch (Error e) {
                System.out.println("Numbers and special characters are not allowed. Try again");
                scanner.nextLine();
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
        for (int i = 0; i < letters.size(); i++) {
            guess += letters.get(i);
        }

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
        while(true) {
            try {
                System.out.print("Select mode: ");
                int mode = scanner.nextInt();
                if (mode < 1 || mode > 3) throw new Exception();
                return mode;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Not a number, try again");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Only 1, 2 or 3. Try again");
                scanner.nextLine();
            }
        }
    }

    public String wordOption(){
        System.out.println("1) Random word");
        System.out.println("2) Pick a word");

        while(true) {
            try {
                System.out.print("Select mode: ");
                int wording = scanner.nextInt();
                if(wording == 1) return null;
                if(wording == 2) {
                    System.out.print("Enter phrase: ");
                    return scanner.next().toUpperCase();
                }
                 throw new Exception();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Not a number, try again");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Only 1 or 2 . Try again");
                scanner.nextLine();
            }
        }
    }

    public void playerTurn(int playerNum){
        System.out.println("--------------------------");
        System.out.println("player "+playerNum+" turn");
        System.out.println("--------------------------");
    }

    public void displayResult2P(GameLogic... players) {
        System.out.println("===== Game Over! =====");
        boolean won = false;
        for (GameLogic player : players) {
            if (player.isWon()) {
                System.out.println("Congratulations, " + player.getPlayerName() +
                        " have WON! The word was: " + player.getHiddenWord());
                won = true;
                break;
            }
        }
        if (!won) System.out.println("DRAW! The word was: " + players[0].getHiddenWord());

        List<List<String>> allPlayers = new ArrayList<>();
        for (GameLogic player : players){
            List<String> lines = new ArrayList<>();

            for (String hangman : getHangman(player.getIncorrectGuesses())){
                lines.add(hangman);
            }
            lines.add(player.getPlayerName());
            lines.add("Incorrect Guesses: "+ player.getIncorrectGuesses()+"/"+player.getMaxIncorrectGuesses());
            lines.add(formatGuessedLetters(player.getGuessedLetters()));

            allPlayers.add(lines);
        }
        int rows = allPlayers.get(0).size();

        for (int row = 0; row < rows; row++) {
            for (List<String> player : allPlayers) {
                System.out.printf("%-50s", player.get(row));
            }
            System.out.println();
        }
    }

    public int PlayerNumber(){
        while(true) {
            try {
                System.out.print("How many player ?");
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Not a number, try again");
                scanner.nextLine();
            }
        }
    }

    private String[] getHangman(int stage) {
        return switch (stage) {
            case 0 -> new String[]{
                    "+---+",
                    "|   |",
                    "|",
                    "|",
                    "|",
                    "======="
            };
            case 1 -> new String[]{
                    "+---+",
                    "|   |",
                    "|   O",
                    "|",
                    "|",
                    "======="
            };
            case 2 -> new String[]{
                    "+---+",
                    "|   |",
                    "|   O",
                    "|   |",
                    "|",
                    "======="
            };
            case 3 -> new String[]{
                    "+---+",
                    "|   |",
                    "|   O",
                    "|  /|",
                    "|",
                    "======="
            };
            case 4 -> new String[]{
                    "+---+",
                    "|   |",
                    "|   O",
                    "|  /|\\",
                    "|",
                    "======="
            };
            case 5 -> new String[]{
                    "+---+",
                    "|   |",
                    "|   O",
                    "|  /|\\",
                    "|  /",
                    "======="
            };
            default -> new String[]{
                    "+---+",
                    "|   |",
                    "|   O",
                    "|  /|\\",
                    "|  / \\",
                    "======="
            };
        };
    }
}