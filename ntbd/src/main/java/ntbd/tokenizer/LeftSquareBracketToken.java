package ntbd.tokenizer;

public class LeftSquareBracketToken implements Token{
    @Override
    public boolean equals(final Object other) {
        return other instanceof LeftSquareBracketToken;
    }

    @Override
    public int hashCode() {
        return 22;
    }

    @Override
    public String toString() {
        return "LeftSquareBracketToken";
    }
}