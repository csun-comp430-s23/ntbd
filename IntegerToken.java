public class IntegerToken implements Token{
    @Override
    public boolean equals(final Object other) {
        return other instanceof IntegerToken;
    }

    @Override
    public int hashCode() {
        return 8;
    }

    @Override
    public String toString() {
        return "IntegerToken";
    }
}
