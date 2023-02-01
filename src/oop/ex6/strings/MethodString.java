package oop.ex6.strings;

import static oop.ex6.strings.Primitives.*;

/**
 * RegexStrings generates all the regex strings required by the RegexFactory
 */
public class MethodString {

    public static final String ARGUMENT = OPTIONAL_FINAL + ANY_TYPE + WHITESPACES + NAME_OF_VARIABLE;
    public static final String DECLARATION = getDeclaration();

    public static final String ARGUMENT_VALUE = getArgumentValue();
    public static final String CALL = getCall();

    private static String getDeclaration() {
        return Helper.getMethodOrCondition("void" + WHITESPACES + NAME_OF_METHOD, ARGUMENT,
                ",", "?", "\\{");
    }

    private static String getArgumentValue() {
        return WHITESPACES + "(" + Literals.ANY + "|" + NAME_OF_VARIABLE + ")";
    }

    private static String getCall() {
        String methodName = START_LINE + NAME_OF_METHOD;
        String notLastArgument = "(" + ARGUMENT_VALUE + WHITESPACES + ")";
        String lastArgument = "(,"+ WHITESPACES + ARGUMENT_VALUE + WHITESPACES + ")";
        return methodName + "\\(" +
            notLastArgument + "?" +
            lastArgument + "*" +
            "\\)" + WHITESPACES + ";" + END_LINE;
    }
}