package oop.ex6.scope;

import java.util.Stack;

/**
 *  Represents a variable found in code
 */
public class Variable extends TypedValue {

    public String name;
    public boolean isFinal;

    /**
     * Assigned values are local to scopes in the sense that a variable that was initialized in a child scope is not
     * considered initialized in the parent scope.
     * To support this, Variable has a stack of assigned values that are pushed when encountered in the scope and are
     * popped when the scope ends.
     * A Variable is considered initialized if an only if there is at least one assigned value in the stack.
     */
    public Stack<TypedValue> assignedValues;

    public Variable(String name, Type type) {
        super(type);
        this.name = name;
        assignedValues = new Stack<TypedValue>();
    }

    public boolean isInitialized() {
        return !assignedValues.empty();
    }

    /**
     * Adds an assignment to the variable or throws an exception if not valid.
     * @param value
     * @throws Exception
     */
    public void addAssignment(TypedValue value) throws Exception {

        if (isFinal) {
            throw new Exception("Cannot assign to a final variable");
        }

        addInitializer(value);
    }

    /**
     * Adds a non-null initializer for the Variable in the current scope
     * @param value
     * @throws Exception
     */
    public void addInitializer(TypedValue value) throws Exception {
        if (value != null && value.type != type) {
            throw new Exception("Cannot assign type " + value.type + " to type " + type);
        }

        if (value != null) {
            assignedValues.push(value);
        }
    }

    /**
     * Removes the most recently added assignment in the current scope
     */
    public void removeAssignment() {
        assignedValues.pop();
    }

    /**
     * gets the currently assigned TypedValue by peeking the stack.
     * @return
     */
    public TypedValue getAssignedValue() {
        if (assignedValues.empty()) {
            return null;
        }
        return assignedValues.peek();
    }

    public String toString() {
        return String.format("%s %s isInitialized = %s isFinal = %s",
                name,
                type,
                isInitialized() ? "true" : "false",
                isFinal ? "true" : "false");
    }
}
