package ntbd.tokenizer;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class TokenizerTest {
    @Test
    public void testTokenizeLeftParen() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize("(");
        final Token[] expected = new Token[]{ new LeftParenToken() };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testTokenizeRightParen() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize(")");
        final Token[] expected = new Token[]{ new RightParenToken() };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testTrue() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize("true");
        final Token[] expected = new Token[]{ new TrueToken() };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testFalse() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize("false");
        final Token[] expected = new Token[]{ new FalseToken() };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testSingleDigitNumber() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize("7");
        final Token[] expected = new Token[]{ new NumToken(7) };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testMultiDigitNumber() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize("1234567");
        final Token[] expected = new Token[]{ new NumToken(1234567) };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testIdAndNumber() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize("7)");
        final Token[] expected = new Token[]{
            new NumToken(7),
            new RightParenToken()
        };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testTokenizeEmptyInput() throws TokenizerException {
        assertArrayEquals(new Token[0], Tokenizer.tokenize(""));
    }

    @Test
    public void testTokenizeOnlyWhitespace() throws TokenizerException {
        assertArrayEquals(new Token[0], Tokenizer.tokenize("   "));
    }

    @Test(expected = TokenizerException.class)
    public void testCannotTokenize() throws TokenizerException {
        Tokenizer.tokenize("$");
    }

    @Test
    public void testSingleCharAtom() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize("a");
        final Token[] expected = new Token[]{ new AtomToken("a") };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testMultiCharAtom() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize("abcde");
        final Token[] expected = new Token[]{ new AtomToken("abcde") };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testSingleCharVariable() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize("A");
        final Token[] expected = new Token[]{ new VariableToken("A") };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testMultiCharVariable() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize("Abcde");
        final Token[] expected = new Token[]{ new VariableToken("Abcde") };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testVariableAndAtom() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize("Variable atom");
        final Token[] expected = new Token[]{
            new VariableToken("Variable"),
            new AtomToken("atom")
        };
        assertArrayEquals(expected, tokens);
    }
}