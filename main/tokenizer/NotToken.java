package main.tokenizer;

public class NotToken implements Token{
    @Override
    public boolean equals(final Object other) {
        return other instanceof NotToken;
    }

    @Override
    public int hashCode() {
        return 24;
    }

    @Override
    public String toString() {
        return "NotToken";
    }
}
