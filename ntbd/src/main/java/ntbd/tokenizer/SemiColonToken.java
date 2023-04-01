package ntbd.tokenizer;

public class SemiColonToken implements Token{
    @Override
    public boolean equals(final Object other) {
        return other instanceof SemiColonToken;
    }

    @Override
    public int hashCode() {
        return 25;
    }

    @Override
    public String toString() {
        return "SemiColonToken";
    }
}
