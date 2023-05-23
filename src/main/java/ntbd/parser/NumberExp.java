package ntbd.parser;

public class NumberExp implements Expression{
    public final int value;

    public NumberExp(final int value){
        this.value = value;
    }

    @Override
    public boolean equals(final Object other){
        return (other instanceof NumberExp && value == ((NumberExp)other).value);
    }

    @Override
    public int hashCode(){
        return value;
    }

    @Override
    public String toString(){
        return "NumberExp(" + value + ")";
    }
}