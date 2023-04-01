package ntbd.tokenizer;

public class PeriodToken implements Token{
    @Override
    public boolean equals(final Object other) {
        return other instanceof PeriodToken;
    }

    @Override
    public int hashCode() {
        return 10;
    }

    @Override
    public String toString() {
        return "PeriodToken";
    }
}
