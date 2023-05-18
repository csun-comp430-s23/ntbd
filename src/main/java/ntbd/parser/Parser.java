package ntbd.parser;

import ntbd.tokenizer.*;

import java.util.ArrayList;

public class Parser {
    private final Token[] tokens;

    public Parser(final Token[] tokens) {
        this.tokens = tokens;
    }

    public Token getToken(final int position) throws ParseException {
        if (position >= 0 && position < tokens.length) {
            return tokens[position];
        } else {
            throw new ParseException("Out of tokens");
        }
    }

    public void assertTokenIs(final int position, final Token expected) throws ParseException {
        final Token received = getToken(position);
        if (!expected.equals(received)) {
            throw new ParseException("Expected: " + expected.toString() +
                    ", Received: " + received.toString());
        }
    }

    public static KnowledgeBase parseKnowledgeBase(final Token[] tokens) throws ParseException {
        final Parser parser = new Parser(tokens);
        final ParseResult<KnowledgeBase> knowledgeBase = parser.parseKnowledgeBase(0);
        if (knowledgeBase.nextPosition == tokens.length) {
            return knowledgeBase.result;
        } else {
            throw new ParseException("Remaining tokens at end, starting with: " +
                    parser.getToken(knowledgeBase.nextPosition).toString());
        }
    }

    // knowledgebase ::= fact*
    public ParseResult<KnowledgeBase> parseKnowledgeBase(final int position) throws ParseException {
        final ParseResult<Fact> fact = parseStatement(position);
        ArrayList<Fact> result = new ArrayList<Fact>();
        result.add((Fact) fact.result);
        return new ParseResult<KnowledgeBase>(new KnowledgeBase(result), fact.nextPosition);
    }

    // fact ::= fact || rule
    private ParseResult<Fact> parseStatement(int position) throws ParseException {
        try {
            return parseFact(position);
        } catch (final ParseException e1) {
            return parseRule(position);
        }
    }

    private ParseResult<Fact> parseRule(int position) throws ParseException {
        return null;
    }

    //fact ::= atom ’(‘ term (‘,’ term)* ’).’
    private ParseResult<Fact> parseFact(int position) throws ParseException {
        if(getToken(position) instanceof AtomToken && getToken(position + 1) instanceof LeftParenToken) {
            
            final ParseResult<Term> term = parseTerm(position + 2);
        }
        ArrayList<ParseResult<Term>> terms = new ArrayList<ParseResult<Term>>();
        int temp = 3;
        while(true) {
            try {
                assertTokenIs(position + temp, new CommaToken());
                temp++;
                final ParseResult<Term> tempTerm = parseTerm(position + temp);
                terms.add(tempTerm);
                temp++;
            } catch (Exception e) {
                break;
            }
        }
        if (getToken(position + temp) instanceof RightParenToken && temp == ((terms.size()*2) + 3)) {
            return new ParseResult<Fact>(new Fact(getToken(position), terms), position+temp+1);
        }
        return null;
    }

    private ParseResult<Term> parseTerm(int i) throws ParseException {
        return null;
    }
}
/*
 * atom ::= <<starts with lowercase letter>>
 * variable ::= <<starts with uppercase letter>>
 * number ::= <<number>>
 * fact ::= fact || rule
 * knowledgebase ::= fact*
 * term ::= number | atom | variable | atom ’(‘ term (‘,’ term)* ’)’
 * rule ::= atom ’(‘ term (‘,’ term)* ’)’ ‘:-’ body ‘.’
 * fact ::= atom ’(‘ term (‘,’ term)* ’).’
 * 
 * //body ::- body ‘;’ body | body ‘,’ body | fact | term ‘=’ term | exp op exp
 * | var `is` exp
 * primary-body ::= term | term ‘=’ term | var `is` exp | relationalExp
 * relationalOp relationalExp
 * and-body ::= primary-body (`,` primary-body)*
 * or-body ::= and-body (`;` and-body)*
 * body ::= or-body
 * op ::= `<` | `>`
 * 
 * 
 * //exp ::= number | variable | exp mathop exp
 * //mathop ::= `+` | `-` | `*` | `/`
 * primary-exp ::= number | variable | `(` exp `)`
 * mult-exp ::= primary-exp ((`*` | `/`) primary-exp)*
 * add-exp ::= mult-exp ((`+` | `-`) mult-exp)*
 * exp ::= add-exp
 */