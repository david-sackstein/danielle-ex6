package oop.ex6.strings;

/**
 * RegexStrings generates all the regex strings required by the RegexFactory
 */
public class Method {

    public static final String ARGUMENT_VALUE = getArgumentValue();
    public static final String CALL = getCall();

    private static String getCall() {
        String methodName = Primitives.START_LINE + Primitives.NAME_OF_METHOD;
        String notLastArgument = "(" + ARGUMENT_VALUE + Primitives.ANY_WHITESPACES + ")";
        String lastArgument = "(,"+ Primitives.ANY_WHITESPACES + ARGUMENT_VALUE + Primitives.ANY_WHITESPACES + ")";
        return methodName + "\\(" +
                notLastArgument + "?" +
                lastArgument + "*" +
                "\\)" + Primitives.ANY_WHITESPACES + ";" + Primitives.END_LINE;
    }

    private static String getArgumentValue() {
        String literalString = "(" +
                "(" + Literals.INT + ")|" +
                "(" + Literals.STRING + ")|" +
                "(" + Literals.DOUBLE + ")|" +
                "(" + Literals.BOOLEAN + ")|" +
                "(" + Literals.CHAR + "))";

        return Primitives.ANY_WHITESPACES + "(" + literalString + "|" + Primitives.NAME_OF_VARIABLE + ")";
    }
}