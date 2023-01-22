package oop.ex6.scope;

import java.util.ArrayList;

/**
 *
 */
public class GlobalScope extends Scope {
    public ArrayList<Variable> globalVariables;
    public ArrayList<Variable> unresolvedVariables;
    public ArrayList<MethodInvocation> unresolvedMethods;
    public ArrayList<MethodScope> methods;

    public GlobalScope() {
        super(null);
        globalVariables = new ArrayList<Variable>();
        unresolvedVariables = new ArrayList<Variable>();
        unresolvedMethods = new ArrayList<MethodInvocation>();
        methods = new ArrayList<MethodScope>();
    }

    /**
     * @param methodToAdd
     * @throws Exception
     */
    @Override
    public void addMethod(MethodScope methodToAdd) throws Exception {
        for (MethodScope method : methods) {
            if (method.methodName().equals(methodToAdd.methodName())) {
                throw new Exception("MethodScope name already exists");
            }
        }
        methods.add(methodToAdd);
        resolveMethod(methodToAdd);
    }

    /**
     * @param variable
     * @throws Exception
     */
    @Override
    public void addVariable(Variable variable) throws Exception {

        Variable v = findVariable(variable.name, globalVariables);
        if (v != null) {
            throw new Exception("Cannot redeclare local variable in global scope");
        }

        globalVariables.add(variable);
        resolveVariable(variable);
    }

    /**
     * @param variable
     * @param initializer
     * @throws Exception
     */
    @Override
    public void addAssignment(Variable variable, TypedValue initializer) throws Exception {
        throw new Exception("Assignments are not allowed in the global scope");
    }

    /**
     * @param invocation
     * @throws Exception
     */
    @Override
    public void addInvocation(MethodInvocation invocation) throws Exception {
        throw new Exception("MethodScope invocation is not allowed in the global scope");
    }

    /**
     * @param name
     * @param type
     * @return
     * @throws Exception
     */
    @Override
    public Variable findVariable(String name, TypedValue.Type type) throws Exception {

        Variable v = super.findVariable(name, type, globalVariables);
        if (v != null) {
            return v;
        }

        Variable unresolved = new Variable(name, type);
        unresolved.addAssignment(new TypedValue(type));
        unresolvedVariables.add(unresolved);

        return unresolved;
    }

    /**
     * @param invocation
     * @return
     * @throws Exception
     */
    @Override
    public MethodScope findMethod(MethodInvocation invocation) throws Exception {
        for (MethodScope method : methods) {
            if (invocation.methodName.equals(method.methodName())) {
                return method;
            }
        }
        unresolvedMethods.add(invocation);
        return null;
    }

    /**
     * @throws Exception
     */
    @Override
    public void youAreReturning() throws Exception {
        throw new Exception("Return statement is not allowed in global scope");
    }

    /**
     * @return
     * @throws Exception
     */
    @Override
    public Scope yourScopeEnded() throws Exception {
        throw new Exception("End of scope is not allowed in global scope");
    }

    /**
     * @throws Exception
     */
    public void assertNoUnresolvedNames() throws Exception {
        if (unresolvedVariables.size() > 0) {
            throw new Exception("There are unresolved variables");
        }
        if (unresolvedMethods.size() > 0) {
            throw new Exception("There are unresolved methods");
        }
    }

    /**
     * @param name
     * @param variableList
     * @return
     */
    private Variable findVariable(String name, ArrayList<Variable> variableList) {
        for (Variable variable : variableList) {
            if (variable.name.equals(name)) {
                return variable;
            }
        }
        return null;
    }

    /**
     * @param methodToAdd
     */
    private void resolveMethod(MethodScope methodToAdd) {
        ArrayList<MethodInvocation> removeList = new ArrayList<MethodInvocation>();

        for(MethodInvocation unresolved : unresolvedMethods) {
            if (unresolved.methodName.equals(methodToAdd.methodName())) {
                removeList.add(unresolved);
            }
        }
        for(MethodInvocation toRemove : removeList) {
            unresolvedMethods.remove(toRemove);
        }
    }

    /**
     * @param variable
     * @throws Exception
     */
    private void resolveVariable(Variable variable) throws Exception {
        ArrayList<Variable> removeList = new ArrayList<Variable>();
        for (Variable unresolved : unresolvedVariables) {
            if (variable.getAssignedValue() == unresolved) {
                // prevents
                // int a = a;
                // and
                // int a = b;
                // int b = 2;
                throw new Exception("Global variables must not be assigned unresolved variables");
            }
            if (unresolved.name.equals(variable.name)) {
                removeList.add(unresolved);
            }
        }

        for (Variable toRemove : removeList) {
            unresolvedVariables.remove(toRemove);
        }
    }
}
