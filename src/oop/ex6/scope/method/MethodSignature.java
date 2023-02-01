package oop.ex6.scope.method;
import oop.ex6.variableDeclaration.Variable;
import java.util.ArrayList;
import static oop.ex6.exceptions.ExceptionMessages.TWO_ARGS_SAME_NAME;


public class MethodSignature {
    private final static int NAME = 0;
    private final String methodName;
    private final ArrayList<Variable> argumentsArray;

    public MethodSignature(String methodName) {
        argumentsArray = new ArrayList<>();
        this.methodName = methodName;
    }


    /**
     * saves an argument of the method in the argumentsArray field
     * @param variable the argument variable to be added
     * @throws Exception thrown when an argument with the same name was already saved into the array
     */
    public void addArgumentVariable(Variable variable) throws Exception {
        checkArgValidity(variable);
        argumentsArray.add(variable);
    }


    /**
     * check the validity of adding this argument:
     * if an argument with the same name exists, throw exception
     * @param variable the argument variable to be checked
     * @throws Exception thrown when an argument with the same name was already saved into the array.
     */
    private void checkArgValidity(Variable variable) throws Exception {
        for(Variable v : argumentsArray) {
            if (v.getName().equals(variable.getName())) {
                throw new Exception(TWO_ARGS_SAME_NAME);
            }
        }
    }

    public String getMethodName(){
        return methodName;
    }

    public ArrayList<Variable> getArgumentsArray(){
        return argumentsArray;
    }

    /**
     * creates the suiting method signature to the method definition in code.
     * @param splitedMethodDeclaration the code of the method definition splited.
     * @return the suiting method signature to the method definition in code.
     * @throws Exception if the method definition is illegal.
     */
    public static MethodSignature getMethodSignature(
            ArrayList<ArrayList<String>> splitedMethodDeclaration) throws Exception {

        MethodSignature methodSignature =
                new MethodSignature(splitedMethodDeclaration.get(NAME).get(NAME));
        splitedMethodDeclaration.remove(NAME);

        //add args
        for (ArrayList<String> argument : splitedMethodDeclaration){

            Variable variable = Variable.getVariable(argument);
            methodSignature.addArgumentVariable(variable);
        }
        return methodSignature;
    }


}
