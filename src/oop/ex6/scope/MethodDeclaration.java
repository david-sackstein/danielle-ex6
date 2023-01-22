package oop.ex6.scope;

import java.util.ArrayList;

/**
 * Represents the first line of a method body, declaring its signature
 * The declaration contains a methodName and a list of arguments
 */
public class MethodDeclaration {

    public MethodDeclaration() {
        arguments = new ArrayList<Variable>();
    }

    public String methodName;
    public ArrayList<Variable> arguments;

    /**
     * Adds a Variable representing an argument
     * @param variable
     * @throws Exception
     */
    public void addVariable(Variable variable) throws Exception {

        for(Variable v : arguments) {
            if (v.name.equals(variable.name)) {
                throw new Exception("Argument methodName already exists");
            }
        }

        arguments.add(variable);
    }

    public String getMethodName(){
        return methodName;
    }
}
