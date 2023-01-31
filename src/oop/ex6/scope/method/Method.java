package oop.ex6.scope.method;
import oop.ex6.scope.NonGlobalScope;
import oop.ex6.scope.Scope;
import oop.ex6.tokenizer.Tokenizer;
import oop.ex6.variableDeclaration.Type;
import oop.ex6.variableDeclaration.Variable;

import java.util.ArrayList;

import static oop.ex6.exceptions.ExceptionMessages.NO_RETURN_ON_END_OF_SCOPE;


public class Method extends NonGlobalScope {
    private final MethodSignature methodSignature;


    public Method(Scope outerScope, MethodSignature methodSignature) {
        super(outerScope);
        this.methodSignature = methodSignature;
    }


    @Override
    protected void endExceptionThrow() throws Exception {
        throwExceptionByCondition(()-> (!foundReturnStatement), NO_RETURN_ON_END_OF_SCOPE);
    }



    /**
     Finds a variable in the current local scope by its name and type
     @param name The name of the variable
     @param type The type of the variable
     @return The variable if it exists, null otherwise
     */
    @Override
    public Variable findLocalVariable(String name, Type type){
        //find in argument
        Variable v = searchVariableInScope(name, type, methodSignature.getArgumentsArray());
        if (v != null) {
            return v;
        }
        //find in local variables
        v = searchVariableInScope(name, type, localVariables);
        return v;
    }



    public String getMethodName() {
        return methodSignature.getMethodName();
    }


    MethodSignature getMethodSignature(){
        return methodSignature;
    }


    /**
     * creates the suiting method to the method definition in the code.
     * @param line line of method definition in code.
     * @param scope scope of method.
     * @return the suiting method to the method definition in code.
     * @throws Exception if the method definition is illegal.
     */
    public static Method lineToMethodScope(String line, Scope scope) throws Exception{
        ArrayList<ArrayList<String>> splitedMethodDeclaration =
                Tokenizer.splitMethodDeclaration(line);

        if (splitedMethodDeclaration == null) {
            return null;
        }

        return createMethodInScope(scope, splitedMethodDeclaration);
    }


    /**
     * creates the suiting method to the splited method definition in the code.
     * @param scope scope of method.
     * @param splitedMethodDeclaration the code of the method definition splited.
     * @return the suiting method to the method definition in code.
     * @throws Exception  if the method definition is illegal.
     */
    private static Method createMethodInScope(
            Scope scope, ArrayList<ArrayList<String>> splitedMethodDeclaration)
            throws Exception{

        MethodSignature methodSignature =
                MethodSignature.getMethodSignature(splitedMethodDeclaration);
        Method method =  new Method(scope, methodSignature);
        scope.methodInScope(method);
        return method;
    }

}
