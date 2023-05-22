package ntbd.parser;

public class BodyStmt implements Stmt, Body{
    public final ParseResult<Body> body1;
    public final ParseResult<Body> body2;

    public BodyStmt(final ParseResult<Body> result, final ParseResult<Body> result2){
        this.body1 = result;
        this.body2 = result2;
    }

    @Override
    public boolean equals (final Object other){
        if (other instanceof BodyStmt){
            final BodyStmt otherBody = (BodyStmt)other;
            return (body1.equals(otherBody.body1) &&
                    body2.equals(otherBody.body2));
        }
        else{
            return false;
        }
    }

    @Override
    public int hashCode(){
        return(body1.hashCode() + body2.hashCode());
    }

    @Override
    public String toString(){
        return ("Body1Statement(" + body1.toString() + " Body2Statement(" + body2.toString());
    }

}