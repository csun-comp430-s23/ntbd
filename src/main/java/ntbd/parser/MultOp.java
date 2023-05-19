package main.java.ntbd.parser;

public class MultOp implements Op {
    @Override
    public boolean equals(final Object other) {
        return other instanceof MultOp;
    }

    @Override
    public int hashCode() {
        return 17;
    }

    @Override
    public String toString() {
        return "MultOp";
    }
}