package ntbd.parser;

import java.util.ArrayList;

import ntbd.tokenizer.Token;

public class Fact {
    ArrayList <ParseResult<Term>> terms;

    public Fact(Token token, ArrayList<ParseResult<Term>> terms) {
        this.terms = terms;
    }
    

}
