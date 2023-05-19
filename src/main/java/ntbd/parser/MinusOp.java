package main.java.ntbd.parser;

public class MinusOp implements Op {
    @Override
    public boolean equals(final Object other) {
        return other instanceof MinusOp;
    }

    @Override
    public int hashCode() {
        return 16;
    }

    @Override
    public String toString() {
        return "MinusOp";
    }
}