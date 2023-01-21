package oop.ex6.AbstractSyntaxTree;

public class TypedValue extends Expression {

    public enum Type {
        Char,
        Double,
        Int,
        String,
        Boolean
    }

    public TypedValue(Type type) {
        this.type = type;
    }
    public Type type;
}
