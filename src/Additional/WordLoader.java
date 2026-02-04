package Additional;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.Arrays;
import java.util.Random;
public class WordLoader {
    private static String[] WORDS = new String[20];
    private final Random random = new Random();

    // Select random word from WORDS using randomize index
    public String selectRandomWord() {
        fetch();
        String word = WORDS[random.nextInt(WORDS.length)];
        return word.substring(1, word.length() - 1);
    }
    public void fetch() {
        try {
            URL url = new URL("https://random-word-api.herokuapp.com/word?number=20");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            String[] response = new String[1];

            int i = 0;
            while ((inputLine = reader.readLine()) != null) {
                response[i++] = inputLine;
            }
            reader.close();

            WORDS = response[0].substring(1, response[0].length() - 1).split(",");
        } catch (Exception e) {
            System.out.println("Failed to fetch words. Using fallback.");
            WORDS = new String[] {"JAVA", "PROGRAMMING", "OBJECT", "ORIENTED", "HANGMAN"};
        }
    }

}


