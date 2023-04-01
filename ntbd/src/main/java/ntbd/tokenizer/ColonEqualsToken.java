package ntbd.src.main.java.ntbd.tokenizer;

public class ColonEqualsToken implements Token{
    public boolean equals(final Object other) {
        return other instanceof ColonEqualsToken;
    }

    @Override
    public int hashCode() {
        return 11;
    }

    @Override
    public String toString(){
        return "ColonEqualsToken";
    }
}