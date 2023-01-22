package oop.ex6.regex;

/**
 * RegexStrings generates all the regex strings required by the RegexFactory
 */
public class Method {

    public static final String ARGUMENT_VALUE = getArgumentValue();
    public static final String CALL = getCall();

    private static String getCall() {
        String methodName = Constants.START_LINE + Constants.NAME_OF_METHOD;
        String notLastArgument = "(" + ARGUMENT_VALUE + Constants.ANY_WHITESPACES + ")";
        String lastArgument = "(,"+ Constants.ANY_WHITESPACES + ARGUMENT_VALUE + Constants.ANY_WHITESPACES + ")";
        return methodName + "\\(" +
                notLastArgument + "?" +
                lastArgument + "*" +
                "\\)" + Constants.ANY_WHITESPACES + ";" + Constants.END_LINE;
    }

    private static String getArgumentValue() {
        String literalString = "(" +
                "(" + Literals.INT + ")|" +
                "(" + Literals.STRING + ")|" +
                "(" + Literals.DOUBLE + ")|" +
                "(" + Literals.BOOLEAN + ")|" +
                "(" + Literals.CHAR + "))";

        return Constants.ANY_WHITESPACES + "(" + literalString + "|" + Constants.NAME_OF_VARIABLE + ")";
    }

}