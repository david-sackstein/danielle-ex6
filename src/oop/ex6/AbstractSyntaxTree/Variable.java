package oop.ex6.AbstractSyntaxTree;

public class Variable extends TypedValue {

    public String name;
    public boolean isFinal;
    public boolean isInitialized;
    public TypedValue assignedValue;

    public Variable(String name, Type type) {
        super(type);
        this.name = name;
    }

    public void addAssignment(TypedValue value) throws Exception {
        if (value != null && value.type != type) {
            throw new Exception("Cannot assign type " + value.type + " to type " + type);
        }
        assignedValue = value;
        isInitialized = assignedValue != null;
    }
}
