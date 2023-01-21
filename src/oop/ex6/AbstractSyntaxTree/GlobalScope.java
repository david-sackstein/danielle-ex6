package oop.ex6.AbstractSyntaxTree;

import java.util.ArrayList;
import java.util.Objects;

public class GlobalScope extends Scope {
    public ArrayList<Variable> globalVariables;
    public ArrayList<Variable> unresolvedVariables;
    public ArrayList<Method> methods;

    public GlobalScope() {
        super(null);
        globalVariables = new ArrayList<Variable>();
        unresolvedVariables = new ArrayList<Variable>();
        methods = new ArrayList<Method>();
    }

    @Override
    public void addScope(Scope scope) {
    }

    @Override
    public void addVariable(Variable variable) throws Exception {
        globalVariables.add(variable);

        ArrayList<Variable> removeList = new ArrayList<Variable>();
        for (Variable unresolved : unresolvedVariables){
            if (variable.assignedValue == unresolved){
                // prevents
                // int a = a;
                // and
                // int a = b;
                // int b = 2;

                throw new Exception("Global variables must not be assigned unresolved variables");
            }
            if (unresolved.name.equals(variable.name))
            {
                removeList.add(unresolved);
            }
        }

        for (Variable toRemove : removeList){
            unresolvedVariables.remove(toRemove);
        }
    }

    @Override
    public Variable findVariable(String name, TypedValue.Type type)
    {
        for(Variable variable : globalVariables) {
            if (variable.name.equals(name)){
                return variable;
            }
        }

        Variable unresolved = new Variable(name, type);
        unresolved.isInitialized = true;
        unresolvedVariables.add(unresolved);

        return unresolved;
    }

    @Override
    public void youHaveReturned() throws Exception {
        throw new Exception("Return statement is not allowed in global scope");
    }

    @Override
    public Scope yourScopeEnded() throws Exception {
        throw new Exception("End of scope is not allowed in global scope");
    }

    public void assertNoUnresolvedVariables() throws Exception {
        if(unresolvedVariables.size() > 0) {
            throw new Exception("There are unresolved variables");
        }
    }
}
