import java.util.Random;
public class WordLoader {
    private static final String[] WORDS = {
            "JAVA", "PROGRAMMING", "OBJECT", "ORIENTED", "HANGMAN"
    };
    private final Random random = new Random();
    public String selectRandomWord() {
        return WORDS[random.nextInt(WORDS.length)];
    }
}
