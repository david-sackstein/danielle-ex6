package oop.ex6.variableDeclaration;


import oop.ex6.scope.Scope;
import oop.ex6.tokenizer.Tokenizer;

import java.util.ArrayList;

import static oop.ex6.exceptions.ExceptionMessages.ILLEGAL_TYPE_NAME;

public class Value {

    private Type type;

    public Value(Type type) {this.type = type;}

    public Type getType(){return type;}

    public void setType(Type type){this.type = type;}


    /**
     * Determines if a value of one type can be assigned to a variable of another type.
     * @param type1 the target type
     * @param type2 the source type
     * @return true if the assignment is possible, false otherwise
     */
    public static boolean assignable(Type type1, Type type2) {
        return type1 == type2 || type1 == Type.Any || type2 == Type.Any ||
                intToCharAssign(type1, type2) ||
                booleanToNumber(type1, type2) ||
                doubleToIntOrChar(type1, type2);
    }

    private static boolean intToCharAssign(Type type1, Type type2){
        return type1 == Type.Int && type2 == Type.Char;
    }

    private static boolean booleanToNumber(Type type1, Type type2){
        return type1 == Type.Boolean && (type2 == Type.Double || type2 == Type.Int);
    }

    private static boolean doubleToIntOrChar(Type type1, Type type2){
        return type1 == Type.Double && (type2 == Type.Int || type2 == Type.Char);
    }


    /**
     Initializes a variable with a value.
     @param scope The scope in which the variable is being initialized.
     @param splited A list of strings split from the input source code.
     @param expectedType The expected type of the variable being initialized.
     @return The value of the initialized variable.
     @throws Exception if the input value is not a match with the
     expected type or if the input is not a name of a variable.
     */
    public static Value intialize(Scope scope, ArrayList<String> splited, Type expectedType)
            throws Exception {
        if (splited.size() > 2){

            String rightSide = splited.get(3);

            if (Tokenizer.isTypeMatch(expectedType, rightSide)) {
                return new Value(expectedType);
            }
            Scope.throwExceptionByCondition(()->(Tokenizer.isNotNameOfVariable(rightSide)),
                    ILLEGAL_TYPE_NAME);

            ArrayList<Type> types = new ArrayList<>();
            types.add(expectedType);

            return Variable.getVariableInitializer(scope, rightSide, types);
        }
        return null;
    }


    /**
    The method creates a typed Value object from a given string argument token.
    The method checks if the argument token matches any of the primitive data types
    (int, double, string, char, boolean) and returns the corresponding Value object.
    If the argument token is not a primitive data type, the method searches for the
    variable in the current scope using the provided argumentToken.
    If the variable is not found, an exception is thrown.
    If the variable is found, but it's not yet initialized, another exception is thrown.
    If the variable is found, and it's initialized, the method returns the corresponding Value object.
    @param scope The current scope
    @param argumentToken The string representation of the argument
    @return A typed Value object
    @throws Exception If the argument token is not a primitive data type and the
    corresponding variable is not found or not yet initialized.
    */

    public static Value createTypedValueArgument(Scope scope, String argumentToken)
            throws Exception {

        //find type
        Type type = null;
        if (Tokenizer.isTypeMatch(Type.Int, argumentToken)) {type = Type.Int;}
        else if (Tokenizer.isTypeMatch(Type.Double, argumentToken)) {type = Type.Double;}
        else if (Tokenizer.isTypeMatch(Type.String, argumentToken)) {type = Type.String;}
        else if (Tokenizer.isTypeMatch(Type.Char, argumentToken)) {type = Type.Char;}
        else if (Tokenizer.isTypeMatch(Type.Boolean, argumentToken)) {type = Type.Boolean;}

        if (type != null){return new Value(type);}
        Variable variable = scope.searchVariableInScope(argumentToken, Type.Any);
        Scope.throwExceptionByCondition(variable::notYetInitialized,
                "Uninitialized arguments passed to method");
        return variable;
    }

}
