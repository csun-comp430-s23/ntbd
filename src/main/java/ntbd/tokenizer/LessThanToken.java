package ntbd.tokenizer;

public class LessThanToken implements Token{
    public boolean equals(final Object other) {
        return other instanceof LessThanToken;
    }

    @Override
    public int hashCode() {
        return 12;
    }

    @Override
    public String toString(){
        return "LessThanToken";
    }
}