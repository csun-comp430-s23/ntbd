public class RightSquareBracketToken implements Token{
    @Override
    public boolean equals(final Object other) {
        return other instanceof RightSquareBracketToken;
    }

    @Override
    public int hashCode() {
        return 22;
    }

    @Override
    public String toString() {
        return "RightSquareBracketToken";
    }
}