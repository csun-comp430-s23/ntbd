package ntbd.parser;

public class BooleanExp implements Expression{
    public final boolean value;

    public BooleanExp(final boolean value){
        this.value = value;
    }

    @Override
    public boolean equals(final Object other){
        return (other instanceof BooleanExp && value == ((BooleanExp)other).value);
    }

    @Override
    public int hashCode(){
        return(value) ? 1 : 0;
    }

    @Override
    public String toString(){
        return "BooleanExp(" + value + ")";
    }
}