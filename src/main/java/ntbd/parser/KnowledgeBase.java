package ntbd.parser;

import java.util.ArrayList;

public class KnowledgeBase {
    public final ArrayList<Fact> facts;

    public KnowledgeBase(final ArrayList<Fact> facts) {
        this.facts = facts;
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof KnowledgeBase &&
                facts.equals(((KnowledgeBase)other).facts));
    }

    @Override
    public int hashCode() {
        return facts.hashCode();
    }

    @Override
    public String toString() {
        return facts.toString();
    }
}