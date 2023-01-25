package oop.ex6.scope;

import java.util.ArrayList;

/**
 * A BlockScope represents a scope of code - either a method, a while conditional block or an if conditional block
 */
public abstract class BlockScope extends Scope {

    /**
     * Assignments created in the block scope are not considered assignments outside the current scope.
     * localAssignments keeps a record of all the assignments made in this scope so they can be reverted when it ends.
     */
    protected ArrayList<Variable> localAssignments;
    protected ArrayList<Variable> localVariables;
    protected boolean hasReturned;

    /**
     * @param parent
     */
    public BlockScope(Scope parent) {
        super(parent);

        localVariables = new ArrayList<Variable>();
        localAssignments = new ArrayList<Variable>();
    }

    /**
     * @param method
     * @throws Exception
     */
    @Override
    public void addMethod(MethodScope method) throws Exception {
        throw new Exception ("Add method is not allowed in a code block");
    }

    /**
     * Adds a variable declaration to the scope or throws an exception if not supported
     * @param variable
     * @throws Exception
     */
    @Override
    public void addVariable(Variable variable) throws Exception {
        if (hasReturned) {
            throw new Exception("Cannot add variables after the return statement");
        }
        Variable v = findLocalVariable(variable.name, TypedValue.Type.Any);
        if (v != null) {
            throw new Exception("Cannot redeclare local variable in method");
        }
        localVariables.add(variable);
    }


    /**
     * Adds an assignment statement to the scope or throws an exception if not supported
     * @param variable
     * @param initializer
     * @throws Exception
     */
    @Override
    public void addAssignment(Variable variable, TypedValue initializer) throws Exception {
        if (hasReturned) {
            throw new Exception("Assignments are not allowed after a return statement");
        }
        variable.addAssignment(initializer);
        localAssignments.add(variable);
    }

    /**
     * Adds a method invocation statement to the scope or throws an exception if not supported
     * @param invocation
     * @throws Exception
     */
    @Override
    public void addInvocation(MethodInvocation invocation) throws Exception {
        if (hasReturned) {
            throw new Exception("Method invocations are not allowed after a return statement");
        }
        MethodScope method = findMethod(invocation);
        if (method != null) {
            if (! method.isInvocationOf(invocation)) {
                throw new Exception("A method called with incorrect arguments");
            }
        }
    }

    /**
     * Returns a Variable with specified name and type in this scope or in parent scopes
     * or throws an exception if a match was not found
     * @param name
     * @param type may also take the value of TypedValue.Type.Any to match variables of any type
     * @return
     * @throws Exception
     */
    @Override
    public Variable findVariable(String name, TypedValue.Type type) throws Exception {

        Variable v = findLocalVariable(name, type);
        if (v != null) {
            return v;
        }
        return parent.findVariable(name, type);
    }

    /**
     * Returns a method that matches the specified invocation in the current scope or in parent scopes
     * or throws an exception if a match was not found
     * @param invocation
     * @return
     * @throws Exception
     */
    @Override
    public MethodScope findMethod(MethodInvocation invocation) throws Exception {
        return parent.findMethod(invocation);
    }

    @Override
    public void returnFromScope() {
        hasReturned = true;
    }

    /**
     * Invalidate assignments made within the scope. Called when the block encs
     * @throws Exception
     */
    protected void removeLocalAssignments()  {
        for (Variable v : localAssignments) {
            v.removeAssignment();
        }
    }

    /**
     * @param name
     * @param type
     * @return
     * @throws Exception
     */
    abstract Variable findLocalVariable(String name, TypedValue.Type type) throws Exception;
}
