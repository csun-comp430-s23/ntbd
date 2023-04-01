package ntbd.tokenizer;

public class BooleanToken implements Token{
    public boolean equals (final Object other) {
        return other instanceof BooleanToken;
    }

    public int hashCode(){
        return 3;
    }

    public String toString(){
        return "BooleanToken";
    }
}
