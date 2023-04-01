package ntbd.src.main.java.ntbd.tokenizer;

public class IsToken implements Token{
    @Override
    public boolean equals(final Object other) {
        return other instanceof IsToken;
    }

    @Override
    public int hashCode() {
        return 20;
    }
    
    @Override
    public String toString() {
        return "IsToken";
    }
}
