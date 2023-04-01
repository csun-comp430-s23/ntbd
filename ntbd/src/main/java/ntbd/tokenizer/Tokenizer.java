package ntbd.src.main.java.ntbd.tokenizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Token[] tokenize() throws TokenizerException {
        final List<Token> tokens = new ArrayList<Token>();
        skipWhitespace();
        while (position < input.length()) {
            tokens.add(readToken());
            skipWhitespace();
        }
        return tokens.toArray(new Token[tokens.size()]);
    }

    public void skipWhitespace() {
        while (position < input.length() &&
                Character.isWhitespace(input.charAt(position))) {
            position++;
        }
    }

    public Token readToken() throws TokenizerException {
        Token token = tokenizeNumber();
        if (token != null) {
            return token;
        }
        token = tokenizeNameOrReservedWord();
        if (token != null) {
            return token;
        }
        token = tokenizeSymbol();
        if (token != null) {
            return token;
        }
        throw new TokenizerException("Could not tokenize: " +
                input.charAt(position));
    }

    public NumToken tokenizeNumber() {
        String digits = "";
        while (position < input.length() &&
               Character.isDigit(input.charAt(position))) {
            digits += input.charAt(position);
            position++;
        }

        if (digits.length() > 0) {
            return new NumToken(Integer.parseInt(digits));
        } else {
            return null;
        }
    }

    public static final Map<String, Token> RESERVED_WORDS = new HashMap<String, Token>() {{
        put("is", new IsToken());
        put("true", new TrueToken());
        put("false", new FalseToken());
        put("Not", new NotToken());
    }};

    public Token tokenizeNameOrReservedWord() {
        String name = "";

        if (Character.isLetter(input.charAt(position))) {
            name += input.charAt(position);
            position++;
            while (position < input.length() &&
                   Character.isLetterOrDigit(input.charAt(position))) {
                name += input.charAt(position);
                position++;
            }

            if (RESERVED_WORDS.containsKey(name)) {
                return RESERVED_WORDS.get(name);
            } else if (Character.isUpperCase(name.charAt(0))){
                return new VariableToken(name);
            } else {
                return new AtomToken(name);
            }
        } else {
            return null;
        }
    }

    public static final SymbolPair[] SYMBOLS = new SymbolPair[] {
        new SymbolPair("(", new LeftParenToken()),
        new SymbolPair(")", new RightParenToken()),
        new SymbolPair("+", new PlusToken()),
        new SymbolPair("-", new MinusToken()),
        new SymbolPair("*", new MultToken()),
        new SymbolPair("/", new DivToken()),
        new SymbolPair("<", new LessThanToken()),
        new SymbolPair(">", new MoreThanToken()),
        new SymbolPair(".", new PeriodToken()),
        new SymbolPair(":-", new ColonEqualsToken()),
        new SymbolPair(",", new CommaToken()),
        new SymbolPair("_", new UnderscoreToken()),
        new SymbolPair("=", new EqualsToken()),
        new SymbolPair("[", new LeftSquareBracketToken()),
        new SymbolPair("]", new RightSquareBracketToken()),
        new SymbolPair(";", new SemiColonToken())
    };

    public Token tokenizeSymbol() {
        for (final SymbolPair pair : SYMBOLS) {
            if (input.startsWith(pair.asString, position)) {
                position += pair.asString.length();
                return pair.asToken;
            }
        }

        return null;
    }
}
