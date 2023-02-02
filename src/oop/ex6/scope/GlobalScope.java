package oop.ex6.scope;
import oop.ex6.scope.method.MethodCall;
import oop.ex6.scope.method.Method;
import oop.ex6.variableDeclaration.Type;
import oop.ex6.variableDeclaration.Value;
import oop.ex6.variableDeclaration.Variable;
import java.util.ArrayList;
import static oop.ex6.exceptions.ExceptionMessages.*;


public class GlobalScope extends Scope {
    public final ArrayList<Variable> globalVariables = new ArrayList<>();
    public final ArrayList<Variable> unresolvedVariables = new ArrayList<>();
    public final ArrayList<MethodCall> unresolvedMethods = new ArrayList<>();
    public final ArrayList<Method> methods = new ArrayList<>();


    public GlobalScope() {
        super(null);
    }



    /**
     * Adds a Method to the list of methods in the current scope
     * @param methodAdd The Method object to add
     * @throws Exception If a Method with the same name as `methodToAdd` already exists in the current scope
     */
    @Override
    public void methodInScope(Method methodAdd) throws Exception {
        for (Method method : methods){

            throwExceptionByCondition(
                    ()-> method.getMethodName().equals(methodAdd.getMethodName()), METHOD_NAME_EXISTS);
        }
        methods.add(methodAdd);

        //remove from unresolvedMethods array:
        ArrayList<MethodCall> removeArray = new ArrayList<>();
        for(MethodCall unresolved : unresolvedMethods) {
            if (unresolved.methodNameMatch(methodAdd)) {
                removeArray.add(unresolved);
            }
        }
        for(MethodCall methodRemove : removeArray) {
            unresolvedMethods.remove(methodRemove);
        }
    }


    /**
     * Adds a Variable to the global scope
     * @param variable The Variable object to add
     * @throws Exception If a variable with the same name as `variable` already exists in the global scope
     */
    @Override
    public void variableInScope(Variable variable) throws Exception {

        Variable v = searchForVariableInScope(variable.getName(), globalVariables);

        throwExceptionByCondition(()-> v != null, ILLEGAL_REDECLARE);

        globalVariables.add(variable);
        // remove from unresolved array
        ArrayList<Variable> removeArray = new ArrayList<>();

        for (Variable unresolvedVar : unresolvedVariables) {
            throwExceptionByCondition(
                    ()->variable.getLastAssignedValue() == unresolvedVar, ASSIGNMENT_WITH_UNRESOLVED);

            if (unresolvedVar.getName().equals(variable.getName())) {
                removeArray.add(unresolvedVar);
            }
        }

        for (Variable methodRemove : removeArray) {
            unresolvedVariables.remove(methodRemove);
        }
    }



    /**
     * Throws an Exception indicating that assignments are not allowed in the global scope
     * @param variable The Variable object to assign a value to
     * @param value The Value to assign to `variable`
     * @throws Exception Always thrown with the message "Assignments are not allowed in the global scope"
     */
    @Override
    public void assignmentInScope(Variable variable, Value value) throws Exception {
        throw new Exception(ASSIGNMENT_IN_GLOBAL_SCOPE);
    }


    /**
     * Throws an Exception indicating that MethodScope invocations are not allowed in the global scope
     * @param methodCall The MethodCall object to invoke
     * @throws Exception Always thrown with the message "MethodScope invocation is not allowed in the global scope"
     */
    @Override
    public void methodCallInScope(MethodCall methodCall) throws Exception {
        Method method = searchForMethod(methodCall);
        if (method != null) {
            throwExceptionByCondition(
                    ()-> (!methodCall.matchCallToMethod(method)), NO_METHOD_MATCHING_TO_CALL);
        }
    }


    /**
     * Searches for a Variable with the given name and type in the global scope
     * If the variable is not found, creates a new unresolved Variable with the given name and type,
     * adds it to the list of unresolved variables and returns it.
     * @param name The name of the Variable to search for
     * @param type The type of the Variable to search for
     * @return The Variable object if found, or an unresolved Variable object with the
     * given name and type if not found.
     * @throws Exception If an error occurs during the search
     */
    @Override
    public Variable searchVariableInScope(String name, Type type) throws Exception {

        Variable v = super.searchVariableInScope(name, type, globalVariables);
        if (v != null) {
            return v;
        }

        Variable newVariable = new Variable(name, type);
        newVariable.addAssignment(new Value(type));
        unresolvedVariables.add(newVariable);

        return newVariable;
    }

    /**
     * Searches for a Method with the given MethodCall object
     * If the Method is not found, adds the MethodCall object to the
     * list of unresolved methods and returns `null`.
     * @param methodCall The MethodCall object to search for
     * @return The Method object if found, or `null` if not found
     */
    @Override
    public Method searchForMethod(MethodCall methodCall){
        for (Method method : methods) {
            if (methodCall.methodNameMatch(method)) {
                return method;
            }
        }
        unresolvedMethods.add(methodCall);
        return null;
    }



    /**
     * Throws an Exception indicating that Return statements are not allowed in the global scope
     * @throws Exception Always thrown with the message "Return statement is not allowed in global scope"
     */
    @Override
    public void returnInScope() throws Exception {
        throw new Exception(RETURN_IN_GLOBAL_SCOPE);
    }



    /**
     * Throws an exception as end of scope is not allowed in global scope.
     * @throws Exception with message END_SCOPE_GLOBAL_SCOPE
     */
    @Override
    public Scope endScope() throws Exception {
        throw new Exception(END_SCOPE_GLOBAL_SCOPE);
    }


    /**
     * Asserts that there are no unresolved variables or methods.
     * @throws Exception with message "There are unresolved variables" if unresolvedVariables.size() > 0
     * @throws Exception with message "There are unresolved methods" if unresolvedMethods.size() > 0
     */
    public void checkForUnresolvedNames() throws Exception {
        throwExceptionByCondition( () -> !unresolvedVariables.isEmpty(), UNRESOLVED_VARIABLES);
        throwExceptionByCondition(() -> !unresolvedMethods.isEmpty(), UNRESOLVED_METHODS);
    }


    /**
     * Searches for a variable in the list of variables, by name only.
     * @param name The name of the variable to search for
     * @param variableList The list of variables to search in
     * @return The variable if found, null otherwise
     */
    private Variable searchForVariableInScope(String name, ArrayList<Variable> variableList) {
        return super.searchVariableInScope(name, Type.Any, variableList);
    }


}
