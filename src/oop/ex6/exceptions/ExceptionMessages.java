package oop.ex6.exceptions;

import oop.ex6.variableDeclaration.Type;

public class ExceptionMessages {
    public static final String UNINITIALIZED_FINAL =
            "A final variable must be initialized.";
    public static final String ILLEGAL_SYNTAX =
            "The compiler can't recognize the syntax.";
    public static final String REGEX_FAIL = "Regex failed.";
    public static final String ILLEGAL_TYPE_NAME =
            "Type or name is illegal.";
    public static final String RETURN_WITH_NO_END =
            "Return statement with no } in the next line is not allowed.";
    public static final String EMPTY_ARRAY =
            "The given array of string arrays is empty.";
    public static final String FINAL_VARIABLE_ASSIGNMENT =
            "reassignment of a final variable is not allowed.";
    public static final String TWO_ARGS_SAME_NAME =
            "Method can not get to arguments with the same name.";
    public static final String NO_RETURN_ON_END_OF_SCOPE =
            "Method ending without a return is not allowed.";
    public static final String METHOD_NON_GLOBAL_SCOPE =
            "Method in a scope which is non-global is not allowed.";
    public static final String VARIABLE_AFTER_RETURN =
            "Adding variables after the return statement is not allowed";
    public static final String ASSIGNMENT_AFTER_RETURN =
            "Adding assignment after the return statement is not allowed";
    public static final String METHOD_CALL_AFTER_RETURN =
            "method call after the return statement is not allowed";
    public static final String REDECLARE_VARIABLE_IN_METHOD =
            "Redeclare local variable in method is not allowed";
    public static final String NO_METHOD_MATCHING_TO_CALL =
            "No defined method matching to the call was found.";
    public static final String METHOD_NAME_EXISTS =
            "method with the same name already exists.";
    public static final String ILLEGAL_REDECLARE =
            "The variable was already declared, redeclaration is not allowed.";
    public static final String ASSIGNMENT_IN_GLOBAL_SCOPE =
            "Assignments in the global scope is not allowed.";
    public static final String METHOD_CALL_IN_GLOBAL_SCOPE =
            "Calling a method in the global scope is not allowed.";
    public static final String RETURN_IN_GLOBAL_SCOPE =
            "Return statement in global scope is not allowed.";
    public static final String END_SCOPE_GLOBAL_SCOPE =
            "End of scope in global scope is not allowed.";
    public static final String UNRESOLVED_METHODS =
            "There are unresolved methods.";
    public static final String UNRESOLVED_VARIABLES =
            "There are unresolved variables.";

    public static final String ASSIGNMENT_WITH_UNRESOLVED =
            "Global variables assigned with unresolved variables is not allowed.";

    public static String assignmentOfWrongTypes(Type valueType, Type variableType){
        return "assignment of value from type " + valueType + " with variable from type " +
                variableType + " is not allowed";
    }

    public static String useUnitializedVar(String varName){
        return "use of variable " + varName + " is not allowed, because it was never initialized.";
    }

    public static String varUnfound(String varName){
        return "The use of " + varName + " is not allowed, because it's declaration is unfound.";
    }

}
