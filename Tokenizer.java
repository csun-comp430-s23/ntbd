import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private final String input;
    private int position;

    public Tokenizer(final String input) {
        this.input = input;
        position = 0;
    }

    public static Token[] tokenize(final String input)
        throws TokenizerException {
        return new Tokenizer(input).tokenize();
    }

    private Token[] tokenize() {
        final List<Token> tokens = new ArrayList<Token>();
        skipWhitespace();
        while (position < input.length()) {
            tokens.add(readToken());
            skipWhitespace();
        }
        return tokens.toArray(new Token[tokens.size()]);
    }

    private void skipWhitespace() {
        while (position < input.length() &&
               Character.isWhitespace(input.charAt(position))) {
            position++;
        }
    }
}
