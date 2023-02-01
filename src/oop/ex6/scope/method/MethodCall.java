package oop.ex6.scope.method;
import oop.ex6.scope.Scope;
import oop.ex6.tokenizer.Tokenizer;
import oop.ex6.variableDeclaration.Type;
import oop.ex6.variableDeclaration.Value;
import oop.ex6.variableDeclaration.Variable;

import java.util.ArrayList;


public class MethodCall {

    private final String callMethodName;
    private final ArrayList<Value> callArguments;

    public MethodCall(String callMethodName) {
        callArguments = new ArrayList<>();
        this.callMethodName = callMethodName;
    }

    public void addToCallArgumentsArray(Value arg){
        callArguments.add(arg);
    }

    public boolean methodNameMatch(Method method){
        return callMethodName.equals(method.getMethodName());
    }

    /**
     * Checks if this MethodCall matches the method represented by the given Method.
     * @param method The Method to be checked for matching this MethodCall.
     * @return true if the MethodCall matches this MethodSignature, false otherwise.
     */
    public boolean matchCallToMethod(Method method) {
        MethodSignature methodSignature = method.getMethodSignature();
        // match names
        if (!methodNameMatch(method)) {
            return false;
        }

        ArrayList<Value> invocationArguments = callArguments;
        ArrayList<Variable> declarationArguments = methodSignature.getArgumentsArray();

        // match arguments amount
        if (invocationArguments.size() != declarationArguments.size()) {
            return false;
        }
        // match arguments type
        return matchGivenArgsToSignature(invocationArguments, declarationArguments);
    }


    /**
     * Check if each argument in MethodCall is assignable to
     * the corresponding argument in MethodSignature.
     * @param callArguments argument given in call to the method
     * @param signatureArguments arguments of the method signature
     * @return true if they match, false otherwise.
     */
    private boolean matchGivenArgsToSignature(
            ArrayList<Value> callArguments, ArrayList<Variable> signatureArguments){

        int count = 0;
        for (Value givenValue : callArguments){
            Type givenType = givenValue.getType();
            Type declaredType = signatureArguments.get(count).getType();
            count ++;

            if (givenType != Type.Any) {
                if (!Value.assignable(declaredType, givenType)) {
                    return false;
                }
            } else {
                // If it's a variable which is still unresolved (in global scope)
                Variable variable = (Variable) (callArguments.get(count));
                variable.setType(declaredType);
            }
        }
        return true;
    }


    /**
     Runs a method call for the given line in the given scope using the given tokenizer.
     @param line the line that contains the method call
     @param scope the scope in which the method call is made
     @throws Exception if the method call is invalid
     */
    public static MethodCall runMethodCall(String line, Scope scope)
            throws Exception {
        ArrayList<ArrayList<String>> splited = Tokenizer.splitMethodCall(line);
        MethodCall call = getMethodCall(scope, splited);
        scope.methodCallInScope(call);
        return call;
    }



    /**
     Gets a method call for the given scope and split line.
     @param scope the scope in which the method call is made
     @param arrayLists the split line that contains the method call information
     @return the method call
     @throws Exception if the method call is invalid
     */
    public static MethodCall getMethodCall(Scope scope, ArrayList<ArrayList<String>> arrayLists)
            throws Exception{

        MethodCall call = new MethodCall(arrayLists.get(0).get(0));

        for (int i = 1; i < arrayLists.size(); i++) {

            String argumentToken = arrayLists.get(i).get(1);
            Value typedValue = Value.createTypedValueArgument(scope, argumentToken);
            call.addToCallArgumentsArray(typedValue);
        }
        return call;
    }

    public boolean isCallOf(MethodSignature methodSignature) {
        if (!callMethodName.equals(methodSignature.getMethodName())){return false;}
        ArrayList<Variable> argsSignature = methodSignature.getArgumentsArray();
        if (argsSignature.size() != callArguments.size()){return false;}

        for (int i=0; i<callArguments.size(); i++){
            if (!Value.assignable(callArguments.get(i).getType(), argsSignature.get(i).getType())){
                return false;
            }
        }
        return true;
    }
}
