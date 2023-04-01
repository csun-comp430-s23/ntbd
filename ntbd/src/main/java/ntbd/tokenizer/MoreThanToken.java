package ntbd.src.main.java.ntbd.tokenizer;

public class MoreThanToken implements Token{
    public boolean equals(final Object other) {
        return other instanceof MoreThanToken;
    }

    @Override
    public int hashCode() {
        return 13;
    }

    @Override
    public String toString(){
        return "MoreThanToken";
    }
}