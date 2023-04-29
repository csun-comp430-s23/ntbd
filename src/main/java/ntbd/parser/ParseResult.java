package ntbd.parser;

public class ParseResult<T> {
    public int nextPosition;
    public T result;

    public ParseResult(final T result, final int nextPosition) {
        this.result = result;
        this.nextPosition = nextPosition;
    }

    @Override
    public boolean equals(final Object other) {
        if (other instanceof ParseResult) {
            final ParseResult<T> otherResult = (ParseResult<T>)other;
            return (result.equals(otherResult.result) &&
                    nextPosition == otherResult.nextPosition);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return result.hashCode() + nextPosition;
    }

    @Override
    public String toString() {
        return ("ParseResult(" +
                result.toString() + ", " +
                nextPosition + ")");
    }
}
