public class EqualToken implements Token{
    @Override
    public boolean equals(final Object other) {
        return other instanceof EqualToken;
    }

    @Override
    public int hashCode() {
        return 21;
    }
    
    @Override
    public String toString() {
        return "EqualToken";
    }
}
