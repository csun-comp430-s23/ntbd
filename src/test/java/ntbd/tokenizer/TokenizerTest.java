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

    @Test
    public void testMult() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize("*");
        final Token[] expected = new Token[]{ new MultToken() };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testDiv() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize("/");
        final Token[] expected = new Token[]{ new DivToken() };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testColonEquals() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize(":-");
        final Token[] expected = new Token[]{ new ColonEqualsToken() };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testNot() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize("Not");
        final Token[] expected = new Token[]{ new NotToken() };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testUnderscore() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize("_");
        final Token[] expected = new Token[]{ new UnderscoreToken() };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testComma() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize(",");
        final Token[] expected = new Token[]{ new CommaToken() };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testEquals() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize("=");
        final Token[] expected = new Token[]{ new EqualsToken() };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testLessThan() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize("<");
        final Token[] expected = new Token[]{ new LessThanToken() };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testPeriod() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize(".");
        final Token[] expected = new Token[]{ new PeriodToken() };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testSemiColon() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize(";");
        final Token[] expected = new Token[]{ new SemiColonToken() };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testPlus() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize("+");
        final Token[] expected = new Token[]{ new PlusToken() };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testRightSquareBracket() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize("]");
        final Token[] expected = new Token[]{ new RightSquareBracketToken() };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testMoreThan() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize(">");
        final Token[] expected = new Token[]{ new MoreThanToken() };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testIs() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize("is");
        final Token[] expected = new Token[]{ new IsToken() };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testMinus() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize("-");
        final Token[] expected = new Token[]{ new MinusToken() };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testLeftSquareBracket() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize("[");
        final Token[] expected = new Token[]{ new LeftSquareBracketToken() };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testMathExpression() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize("(1 + 2) * (3/4) = 2.25");
        final Token[] expected = new Token[]{
            new LeftParenToken(),
            new NumToken(1),
            new PlusToken(),
            new NumToken(2),
            new RightParenToken(),
            new MultToken(),
            new LeftParenToken(),
            new NumToken(3),
            new DivToken(),
            new NumToken(4),
            new RightParenToken(),
            new EqualsToken(),
            new NumToken(2),
            new PeriodToken(),
            new NumToken(25)
        };
        assertArrayEquals(expected, tokens);
    }

    @Test
    public void testVariableAssignmentExpression() throws TokenizerException {
        final Token[] tokens = Tokenizer.tokenize("X is 5 + 4");
        final Token[] expected = new Token[]{
            new VariableToken("X"),
            new IsToken(),
            new NumToken(5),
            new PlusToken(),
            new NumToken(4)
        };
        assertArrayEquals(expected, tokens);
    }

    @Test(expected = TokenizerException.class)
    public void testCannotTokenize() throws TokenizerException {
        Tokenizer.tokenize("$");
    }
}