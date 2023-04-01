public class NumToken implements Token{
    public final int value;

    public NumToken(final int value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof NumToken &&
                value == ((NumToken)other).value);
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return "NumToken(" + value + ")";
    }
}
