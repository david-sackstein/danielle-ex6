package oop.ex6.scope;
import oop.ex6.scope.method.MethodCall;
import oop.ex6.scope.method.Method;
import static oop.ex6.exceptions.ExceptionMessages.*;
import oop.ex6.variableDeclaration.Type;
import oop.ex6.variableDeclaration.Value;
import oop.ex6.variableDeclaration.Variable;
import java.util.ArrayList;


/**
 * method / while / if scope
 */
public abstract class NonGlobalScope extends Scope {

    protected ArrayList<Variable> localAssignments;
    protected ArrayList<Variable> localVariables;
    protected boolean foundReturnStatement;



    public NonGlobalScope(Scope outerScope) {
        super(outerScope);
        localVariables = new ArrayList<>();
        localAssignments = new ArrayList<>();
    }



    /**
     * throws an exception because a method can not be defined in a non-global scope.
     * @param method the method in the scope.
     * @throws Exception throws an exception because a method can not be defined in a non-global scope.
     */
    @Override
    public void methodInScope(Method method) throws Exception {
        throw new Exception (METHOD_NON_GLOBAL_SCOPE);
    }


    /**
     * throws in exception if got to return already, or if it is illegal to redeclare the variable.
     * @param variable the variable in the scope.
     * @throws Exception throws in exception if got to return already,
     *                   or if it is illegal to redeclare the variable.
     */
    @Override
    public void variableInScope(Variable variable) throws Exception {
        throwExceptionByCondition(() -> foundReturnStatement, VARIABLE_AFTER_RETURN);

        Variable v = findLocalVariable(variable.getName(), Type.Any);
        throwExceptionByCondition(() ->(v != null), REDECLARE_VARIABLE_IN_METHOD);

        localVariables.add(variable);
    }



    /**
     * assign if didn't get to return yet.
     * @param variable variable to be assigned.
     * @param value value of assignment.
     * @throws Exception throws exception if got to return statement.
     */
    @Override
    public void assignmentInScope(Variable variable, Value value) throws Exception {
        throwExceptionByCondition(() -> foundReturnStatement, ASSIGNMENT_AFTER_RETURN);
        variable.addAssignment(value);
        localAssignments.add(variable);
    }


    /**
     * throws exception if the call was done after a return statement or if there is no
     * method matching the call.
     * @param methodCall method call in the scope.
     * @throws Exception throws exception if the call was done after a return statement or if there is no
     *                   method matching the call.
     */
    @Override
    public void methodCallInScope(MethodCall methodCall) throws Exception {
        throwExceptionByCondition(() -> foundReturnStatement, METHOD_CALL_AFTER_RETURN);

        Method method = searchForMethod(methodCall);
        if (method != null) {
            throwExceptionByCondition(
                    ()-> (!methodCall.matchCallToMethod(method)), NO_METHOD_MATCHING_TO_CALL);
        }
    }


    /**
     * search for variable in the scope or in the outer scope.
     * @param name name of searched variable
     * @param type type of searched variable
     * @return variable searched.
     * @throws Exception if not found.
     */
    @Override
    public Variable searchVariableInScope(String name, Type type) throws Exception {

        Variable v = findLocalVariable(name, type);
        if (v != null) {
            return v;
        }
        return outerScope.searchVariableInScope(name, type);
    }


    /**
     * searches for a matching method to the method call.
     * @param methodCall the method call.
     * @return the matching method.
     * @throws Exception if not found.
     */
    @Override
    public Method searchForMethod(MethodCall methodCall) throws Exception {
        return outerScope.searchForMethod(methodCall);
    }


    /**
     * when got to return statement.
     */
    @Override
    public void returnInScope() {
        foundReturnStatement = true;
    }


    @Override
    public Scope endScope() throws Exception{
        endExceptionThrow();
        // remove the local assignments
        for (Variable v : localAssignments) {
            v.popAssignment();
        }
        return outerScope;
    }



    protected abstract void endExceptionThrow() throws Exception;



    public abstract Variable findLocalVariable(String name, Type type) throws Exception;
}
