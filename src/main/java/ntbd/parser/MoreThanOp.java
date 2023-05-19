package main.java.ntbd.parser;

public class MoreThanOp implements Op {
    @Override
    public boolean equals(final Object other) {
        return other instanceof MoreThanOp;
    }

    @Override
    public int hashCode() {
        return 13;
    }

    @Override
    public String toString() {
        return "MoreThanOp";
    }
}