package oop.ex6.variableDeclaration;
import oop.ex6.scope.Scope;
import oop.ex6.tokenizer.Tokenizer;

import java.util.ArrayList;
import java.util.Stack;

import static oop.ex6.exceptions.ExceptionMessages.*;
import static oop.ex6.exceptions.ExceptionMessages.varUnfound;


public class Variable extends Value {

    private final String name;
    private boolean finalIndicator;

    //stack of assigned values that are pushed when assigned in the scope
    // and are popped when the scope ends.
    private final Stack<Value> assignedValues;


    public Variable(String name, Type type) {
        super(type);
        this.name = name;
        assignedValues = new Stack<>();
    }

    /**
     * Determines if the object has not yet been initialized.
     * @return false if the object has been initialized, true otherwise
     */
    public boolean notYetInitialized() {
        return assignedValues.empty();
    }


    /**
     * Adds an assignment to the variable or throws an exception if not valid.
     * @param value value to assign.
     * @throws Exception thrown with an informative message: FINAL_VARIABLE_ASSIGNMENT
     */
    public void addAssignment(Value value) throws Exception {

        if (finalIndicator) {
            throw new Exception(FINAL_VARIABLE_ASSIGNMENT);
        }

        initializeVariableWithValue(value);
    }


    /**
     * Adds a non-null initializer for the Variable in the current scope
     * @param value value to assign.
     * @throws Exception thrown with an informative message: assignmentOfWrongTypes.
     */
    public void initializeVariableWithValue(Value value) throws Exception {
        if (value == null){return;}
        assignmentTypesValid(value.getType());
        assignedValues.push(value);
    }


    /**
     * throws an exception if the variable type and the value type are different.
     * @param type the values type
     * @throws Exception thrown with an informative message: assignmentOfWrongTypes.
     */
    private void assignmentTypesValid(Type type) throws Exception{
        if (type != this.getType()) {
            throw new Exception(assignmentOfWrongTypes(type, this.getType()));
        }
    }


    /**
     * pops out from the assignedValues stack the most recent assignment in the scope.
     */
    public void popAssignment() {
        assignedValues.pop();
    }


    /**
     * gets the last assignment value in the scope.
     * @return the last assignment value in the scope.
     */
    public Value getLastAssignedValue() {
        return !assignedValues.empty()? assignedValues.peek() : null;
    }

    public String getName() {return name;}

    public boolean getFinalIndicator() {return finalIndicator;}

    public void setFinalIndicator(boolean bool) {this.finalIndicator = bool;}


    /**
     * creates a variable given an argument (as a String) of a function.
     * @param argument the String argument.
     * @return a variable given an argument (as a String) of a function.
     * @throws Exception if the given String is an illegal variable.
     */
    public static Variable getVariable(
            ArrayList<String> argument) throws Exception{

        boolean hasFinalStatement = argument.get(1).equals("final");

        String type = argument.get(1);
        String name = argument.get(2);

        if (hasFinalStatement){
            type = argument.get(2);
            name = argument.get(3);
        }
        return createVariable(hasFinalStatement, Tokenizer.getTypeFromString(type), name);
    }



    /**
     * create a variable given its properties: name, type, final.
     * @param hasFinal determines if the variable should be final.
     * @param type the type of the variable.
     * @param name the name of the variable.
     * @return a variable given its properties: name, type, final.
     * @throws Exception if the initialization is illegal.
     */
    private static Variable createVariable(boolean hasFinal, Type type, String name)  throws Exception {
        Variable variable = new Variable(name, type);
        variable.setFinalIndicator(hasFinal);
        variable.initializeVariableWithValue(new Value(type));
        return variable;
    }

    /**
     Retrieves the initializer of a variable based on the scope and the variable name.
     @param scope The scope in which the variable is being searched.
     @param variableName The name of the variable being searched.
     @param typeArray An array of possible types the variable might have.
     @return The initializer of the found variable.
     @throws Exception if the variable is not found in the scope,
     has a different type than the assignment, or is not yet initialized.
     */
    public static Variable getVariableInitializer(
            Scope scope, String variableName, ArrayList<Type> typeArray) throws Exception {
        for (Type type : typeArray) {
            Variable variable = scope.searchVariableInScope(variableName, type);
            if (variable != null){

                Scope.throwExceptionByCondition(()->(variable.getType() != type),
                        assignmentOfWrongTypes(type, variable.getType()));

                Scope.throwExceptionByCondition(variable::notYetInitialized,
                        useUninitializedVar(variableName));

                return variable;
            }
        }
        throw new Exception(varUnfound(variableName));
    }

}
