package ntbd.tokenizer;

public class CommaToken implements Token{
    public boolean equals(final Object other) {
        return other instanceof CommaToken;
    }

    @Override
    public int hashCode() {
        return 14;
    }

    @Override
    public String toString(){
        return "CommaToken";
    }
}
