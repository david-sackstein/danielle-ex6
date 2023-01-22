package oop.ex6.strings;

import static oop.ex6.strings.Primitives.*;

/**
 * RegexStrings generates all the regex strings required by the RegexFactory
 */
public class Method {

    public static final String ARGUMENT = OPTIONAL_FINAL + ANY_TYPES + WHITESPACES + NAME_OF_VARIABLE;
    public static final String METHOD = getMethodDeclaration();

    public static final String ARGUMENT_VALUE = getArgumentValue();
    public static final String CALL = getCall();

    private static String getCall() {
        String methodName = START_LINE + NAME_OF_METHOD;
        String notLastArgument = "(" + ARGUMENT_VALUE + WHITESPACES + ")";
        String lastArgument = "(,"+ WHITESPACES + ARGUMENT_VALUE + WHITESPACES + ")";
        return methodName + "\\(" +
                notLastArgument + "?" +
                lastArgument + "*" +
                "\\)" + WHITESPACES + ";" + END_LINE;
    }

    private static String getArgumentValue() {
        String literalString = "(" +
                "(" + Literals.INT + ")|" +
                "(" + Literals.STRING + ")|" +
                "(" + Literals.DOUBLE + ")|" +
                "(" + Literals.BOOLEAN + ")|" +
                "(" + Literals.CHAR + "))";

        return WHITESPACES + "(" + literalString + "|" + NAME_OF_VARIABLE + ")";
    }

    private static String getMethodDeclaration() {
        return Helper.getMethodOrCondition("void" + WHITESPACES + NAME_OF_METHOD, ARGUMENT, ",", "?", "\\{");
    }
}