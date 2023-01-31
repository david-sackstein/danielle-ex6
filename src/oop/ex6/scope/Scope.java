package oop.ex6.scope;
import oop.ex6.scope.method.MethodCall;
import oop.ex6.scope.method.Method;
import oop.ex6.variableDeclaration.Type;
import oop.ex6.variableDeclaration.Value;
import oop.ex6.variableDeclaration.Variable;
import java.util.function.BooleanSupplier;
import java.util.ArrayList;



public abstract class Scope {
    protected Scope outerScope;

    Scope(Scope outerScope) {this.outerScope = outerScope;}



    /**
     * deals with a variable in the scope.
     * @param variable the variable in the scope
     * @throws Exception throws an exception if the variable is declared illegally.
     */
    public abstract void variableInScope(Variable variable) throws Exception;



    /**
     * deals with an assignment in the scope.
     * @param variable variable to be assigned.
     * @param value value of assignment.
     * @throws Exception throws an exception if the assignment is illegal.
     */
    public abstract void assignmentInScope(Variable variable, Value value) throws Exception;



    /**
     * deals with a method scope.
     * @param method the method in the scope
     * @throws Exception throws an exception if the scope is not suitable for a method.
     */
    public abstract void methodInScope(Method method) throws Exception;



    /**
     * searches in this scope and in the parent scopes for a method matching to the method call.
     * @param methodCall the method call.
     * @return a method that matches the method call.
     * @throws Exception throws an exception if matching method was not found.
     */
    public abstract Method searchForMethod(MethodCall methodCall) throws Exception;



    /**
     * deals with return statement in scope.
     * @throws Exception throws an exception if return statement is not supported in the scope.
     */
    public abstract void returnInScope() throws Exception;


    /**
     * deals with a method call in the scope.
     * @param methodCall method call in the scope.
     * @throws Exception throws an exception if the method call is illegal.
     */
    public abstract void methodCallInScope(MethodCall methodCall) throws Exception;



    /**
     * searches for a variable with the given name and type in the scope.
     * @param name name of searched variable
     * @param type type of searched variable
     * @return a variable with the given name and type in the scope.
     * @throws Exception throws an exception if matching variable was not found.
     */
    public abstract Variable searchVariableInScope(String name, Type type) throws Exception;



    /**
     * Ends the current scope.
     * @return the outer scope.
     * @throws Exception throws an exception if ending of scope was not valid.
     */
    public abstract Scope endScope() throws Exception;



    /**
     * searches for a variable with the given name and type in the given array
     * @param name name of the searched variable.
     * @param type type of the searched variable.
     * @param variableArray array to search in.
     * @return a variable with the given name and type in the given array,
     *         or null if was not found.
     */
    protected Variable searchVariableInScope(String name, Type type,
                                             ArrayList<Variable> variableArray){
        for (Variable variable : variableArray) {
            boolean hasMatchingType = (variable.getType() == type) || (type == Type.Any);
            if (variable.getName().equals(name) && hasMatchingType) {
                return variable;
            }
        }
        return null;
    }

    public static void throwExceptionByCondition(BooleanSupplier func, String message) throws Exception{
        if (func.getAsBoolean()){
            throw new Exception(message);
        }
    }
}
