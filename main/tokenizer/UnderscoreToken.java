package main.tokenizer;

public class UnderscoreToken implements Token{
    @Override
    public boolean equals(final Object other) {
        return other instanceof UnderscoreToken;
    }

    @Override
    public int hashCode() {
        return 19;
    }
    
    @Override
    public String toString() {
        return "UnderscoreToken";
    }
}
