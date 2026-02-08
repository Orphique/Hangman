/*  Group 4
Deng Yonghan (10271600)
Bo HaoTian (10270562)
Dylan Carson Sisnawan (10270412)
Muhammad Farhan Bin Rosni (10265771)
Matthew Albert Cahyadi(10270548)
*/

import java.util.Random;
public class WordLoader {
    private static final String[] WORDS = {
            "JAVA", "PROGRAMMING", "OBJECT", "ORIENTED", "HANGMAN"
    };
    private final Random random = new Random();

    // Select random word from WORDS using randomize index
    public String selectRandomWord() {
        return WORDS[random.nextInt(WORDS.length)];
    }
}
