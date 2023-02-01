package oop.ex6.scope.ifOrWhile;
import oop.ex6.scope.NonGlobalScope;
import oop.ex6.scope.Scope;
import oop.ex6.tokenizer.Tokenizer;
import oop.ex6.variableDeclaration.Type;
import oop.ex6.variableDeclaration.Variable;

import java.util.ArrayList;

import static oop.ex6.exceptions.ExceptionMessages.REGEX_FAIL;


public class IfWhileScope extends NonGlobalScope {
    public IfWhileScope(Scope outerScope) {
        super(outerScope);
    }

    @Override
    protected void endExceptionThrow(){}


    @Override
    public Variable findLocalVariable(String name, Type type) {
        return searchVariableInScope(name, type, localVariables);
    }


    /**
     Creates an IfWhileScope object by parsing a given line of code and the current scope.
     @param line The line of code to be parsed.
     @param scope The current scope.
     @return A new IfWhileScope object or null if the line does not contain an if/while block.
     @throws Exception If there is a failure in the parsing process.
     */
    public static IfWhileScope createIfWhileScope(String line, Scope scope) throws Exception{
        ArrayList<ArrayList<String>> splited = Tokenizer.splitBlockCondition(line);
        if (splited != null) {
            splited.remove(0);
            return getIfWhileBlock(splited, scope);
        }
        return null;
    }


    /**
     Helper method to create an IfWhileScope object from a list of tokenized condition expressions.
     @param splited A list of tokenized condition expressions.
     @param scope The current scope.
     @return A new IfWhileScope object.
     @throws Exception If there is a failure in the parsing process.
     */
    public static IfWhileScope getIfWhileBlock(ArrayList<ArrayList<String>> splited, Scope scope)
            throws Exception{

        IfWhileScope IfWhileScope = new IfWhileScope(scope);

        for (ArrayList<String> part : splited) {

            String condition = part.get(1);

            if (!(Tokenizer.isTypeMatch(Type.Boolean, condition) ||
                    Tokenizer.isTypeMatch(Type.Int, condition) ||
                    Tokenizer.isTypeMatch(Type.Double, condition))){

                Scope.throwExceptionByCondition(()->Tokenizer.isNotNameOfVariable(condition),
                    REGEX_FAIL);
                Variable.getVariableInitializer(scope, condition,
                        Type.Boolean, Type.Int, Type.Double);
            }
        }
        return IfWhileScope;
    }
}
