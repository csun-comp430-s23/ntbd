package ntbd.parser;

public class DivOp implements Op {
    @Override
    public boolean equals(final Object other) {
        return other instanceof DivOp;
    }

    @Override
    public int hashCode() {
        return 18;
    }

    @Override
    public String toString() {
        return "DivOp";
    }
}