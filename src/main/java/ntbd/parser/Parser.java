package ntbd.parser;

import ntbd.tokenizer.*;

import java.text.ParseException;
import java.util.ArrayList;

import main.java.ntbd.parser.NumberExp;
import main.java.ntbd.parser.VariableExp;

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

    // term ::= number | atom | variable | atom ’(‘ term (‘,’ term)* ’)’
    private ParseResult<Term> parseTerm(int position) throws ParseException {
        final Token token = getToken(position);

        if (token instanceof NumToken){
            return new ParseResult<Term>(new NumberExp(((NumToken)token).value), position + 1); 
        // } else if (token instanceof AtomToken){
        //     return new ParseResult<Term>(new NumberExp(((NumToken)token).value), position + 1); 
        // }
        } else if (token instanceof VariableToken){
            return new ParseResult<Expression>(new VariableExp(new Variable(((VariableToken)token).name)), position + 1);
        } else if (token instanceof AtomToken && getToken(position + 1) instanceof LeftParenToken){
                    
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
        }

    // op ::= `<` | `>`
    public ParseResult <Op> parseOp (final int position) throws ParseException{
        final Token token = getToken(position);
        Op op = null;
        if (token instanceof LessThanToken){
            op = new LessThanOp();
        } else if (token instanceof MoreThanToken){
            op = new MoreThanOp();
        }else{
            throw new ParseException("Expected operator; received: " + token.toString());
        }

        assert(op != null);
        return new ParseResult<Op> (op, position + 1);
    }

    // exp ::= number | variable | exp mathop exp
    public ParseResult<Expression> parseExp(final int position) throws ParseException{
        final Token token = getToken();

        if (token instanceof NumToken){
            return new ParseResult<Expression>(new NumberExp(((NumToken)token).value), position + 1); 
        } else if (token instanceof VariableToken){
            return new ParseResult<Expression>(new VariableExp(new Variable(((VariableToken)token).name)), position + 1);
        // } need help with exp mathop exp part
        } else{
            throw new ParseException("Expected expression; received:" + token.toString());
        }
    }

    //mathop ::= `+` | `-` | `*` | `/`
    public ParseResult <Op> parseMathOp(final int position) throws ParseException{
        final Token token = getToken(position);
        Op op = null;
        if (token instanceof PlusToken){
            op = new PlusOp();
        } else if (token instanceof MinusToken){
            op = new MinusOp();
        } else if (token instanceof MultToken){
            op = new MultOp();
        } else if (toke instanceof DivToken){
            op = new DivOp();
        } else {
            throw new ParseException("Expected operator; received: " + token.toString());
        }

        assert(op != null);
        return new ParseResult<Op> (op, position + 1);
    }

    // primary-exp ::= number | variable | `(` exp `)`
    public ParseResult<Expression> parsePrimaryExp(final int position) throws ParseException{
        final Token token = getToken(position);

        if (token instanceof NumToken){
            return new ParseResult<Expression>(new NumberExp(((NumToken)token).value), position + 1);
        } else if (token instanceof VariableToken){
            return new ParseResult<Expression>(new VariableExp(new Variable(((VariableToken)token).name)), position + 1);
        } else if (token instanceof LeftParenToken){
            final ParseResult<Expression> expression = parseExp(position + 1);
            assertTokenIs(position + 1, new RightParenToken());
            return new ParseResult<Exp>(new Exp(expression.result), position + 1);
        } else{
            throw new ParseException("Expected expression; received: " + token.toString());
        }
    }

    // mult-exp ::= primary-exp ((`*` | `/`) primary-exp)*
    public ParseResult<Expression> parseMultExp(final int position) throws ParseException{
        return null;
    }

}
/*
 * atom ::= <<starts with lowercase letter>>
 * variable ::= <<starts with uppercase letter>>
 * number ::= <<number>>
 * knowledgebase ::= fact*
 * fact ::= fact || rule
 * fact ::= atom ’(‘ term (‘,’ term)* ’).’
 * term ::= number | atom | variable | atom ’(‘ term (‘,’ term)* ’)’
 * rule ::= atom ’(‘ term (‘,’ term)* ’)’ ‘:-’ body ‘.’
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