package oop.ex6.scope;

import java.util.ArrayList;

/**
 * Scope represents a part of the code that can contain expressions and statements
 */
public abstract class Scope {
    Scope parent;
    Scope(Scope parent) {
        this.parent = parent;
    }

    /**
     * Adds a method body to the scope or throws an exception if not supported
     * @param method
     * @throws Exception
     */
    public abstract void addMethod(MethodScope method) throws Exception;

    /**
     * Adds a variable declaration to the scope or throws an exception if not supported
     * @param variable
     * @throws Exception
     */
    public abstract void addVariable(Variable variable) throws Exception;

    /**
     * Adds an assignment statement to the scope or throws an exception if not supported
     * @param variable
     * @param initializer
     * @throws Exception
     */
    public abstract void addAssignment(Variable variable, TypedValue initializer) throws Exception;

    /**
     * Adds a method invocation statement to the scope or throws an exception if not supported
     * @param invocation
     * @throws Exception
     */
    public abstract void addInvocation(MethodInvocation invocation) throws Exception;

    /**
     * Returns a Variable with specified name and type in this scope or in parent scopes
     * or throws an exception if a match was not found
     * @param name
     * @param type may also take the value of TypedValue.Type.Any to match variables of any type
     * @return
     * @throws Exception
     */
    public abstract Variable findVariable(String name, TypedValue.Type type) throws Exception;

    /**
     * Returns a method that matches the specified invocation in the current scope or in parent scopes
     * or throws an exception if a match was not found
     * @param invocation
     * @return
     * @throws Exception
     */
    public abstract MethodScope findMethod(MethodInvocation invocation) throws Exception;

    /**
     * Adds a return statement to the scope or throws an exception if not supported
     * @throws Exception
     */
    public abstract void returnFromScope() throws Exception;

    /**
     * Ends the current scope and returns the parent scope or throws an exception if not valid
     * @return
     * @throws Exception
     */
    public abstract Scope endOfScope() throws Exception;

    /**
     * Utility function that returns a Variable from a specified ArrayList<Variable> that matches a specified name
     * and type, or throws an exception if not found.
     * @param name
     * @param type
     * @param variableList
     * @return
     * @throws Exception
     */
    protected Variable findVariable(String name, TypedValue.Type type, ArrayList<Variable> variableList) throws Exception {
        for (Variable variable : variableList) {
            boolean hasMatchingType = (variable.type == type) || (type == TypedValue.Type.Any);
            if (variable.name.equals(name) && hasMatchingType) {
                return variable;
            }
        }
        return null;
    }
}
