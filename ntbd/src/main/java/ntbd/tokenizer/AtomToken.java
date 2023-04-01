package ntbd.src.main.java.ntbd.tokenizer;

public class AtomToken implements Token {
    public final String name;

    public AtomToken(String s) {
        name = s;
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof AtomToken &&
                name.equals(((AtomToken)other).name));
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "AtomToken(" + name + ")";
    }
}