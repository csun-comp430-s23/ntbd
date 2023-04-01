public class VariableToken implements Token {
    public final String name;

    public VariableToken(String s) {
        name = s;
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof VariableToken &&
                name.equals(((VariableToken)other).name));
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