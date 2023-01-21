package oop.ex6.AbstractSyntaxTree;

import java.util.ArrayList;
import java.util.Objects;

public class Method extends Scope {

    public Method(Scope parent) {
        super(parent);

        localVariables = new ArrayList<Variable>();
    }

    @Override
    public void addScope(Scope scope) {

    }

    @Override
    public void addVariable(Variable variable) throws Exception {
        if (hasReturned) {
            throw new Exception("Cannot add variables after the return statement");
        }
    }

    @Override
    public Variable findVariable(String name, TypedValue.Type type) {

        Variable v = findVariable(name, methodDeclaration.arguments);
        if (v != null) {
            return v;
        }

        v = findVariable(name, localVariables);
        if (v != null) {
            return v;
        }

        return parent.findVariable(name, type);
    }

    private Variable findVariable(String name, ArrayList<Variable> variableList) {
        for(Variable variable : variableList) {
            if (variable.name.equals(name)){
                return variable;
            }
        }
        return null;
    }

    @Override
    public void youHaveReturned() {
        hasReturned = true;
    }

    @Override
    public Scope yourScopeEnded() throws Exception {
        if (! hasReturned) {
            throw new Exception("Missing return statement");
        }
        return parent;
    }

    public MethodDeclaration methodDeclaration;
    public ArrayList<Variable> localVariables;
    public ArrayList<Assignment> assignments;
    public ArrayList<ConditionalBlock> conditionalBlocks;
    public ArrayList<MethodInvocation> methodInvocations;

    private boolean hasReturned;
}
