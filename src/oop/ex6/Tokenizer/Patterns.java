package oop.ex6.Tokenizer;

/**
 * RegexStrings generates all the regex strings required by the RegexFactory
 */
public class Patterns {

    public static final String RETURN_STATEMENT = Stam.START_LINE + "return" + Stam.ANY_WHITESPACES + ";" + Stam.END_LINE;
    public static final String END_OF_BLOCK = Stam.START_LINE + "}" + Stam.END_LINE;

    public static final String ARGUMENT_DECLARATION = Stam.FINAL + "(int|double|String|boolean|char)" + Stam.ANY_WHITESPACES + Stam.NAME_OF_VARIABLE;
    public static final String ARGUMENT_VALUE = getArgumentValue();
    public static final String CONDITION_EXPRESSION = getConditionExpression();

    public static final String METHOD_INVOCATION = getMethodInvocation();
    public static final String CONDITION_BLOCK = getConditionBlock();

    private static String getConditionBlock() {
        String orAnd = "(\\|\\||\\&\\&)";
        String conditionExpression = Stam.ANY_WHITESPACES + CONDITION_EXPRESSION + Stam.ANY_WHITESPACES;
        return getList("(while|if)", conditionExpression, orAnd, "+", "\\{");
    }

    private static String getMethodInvocation() {
        String methodName = Stam.START_LINE + Stam.NAME_OF_METHOD;
        String notLastArgument = "(" + ARGUMENT_VALUE + Stam.ANY_WHITESPACES + ")";
        String lastArgument = "(,"+Stam.ANY_WHITESPACES + ARGUMENT_VALUE + Stam.ANY_WHITESPACES + ")";
        return methodName + "\\(" +
                notLastArgument + "?" +
                lastArgument + "*" +
                "\\)" + Stam.ANY_WHITESPACES + ";" + Stam.END_LINE;
    }

    public static String getList(
            String openingToken, String listElement, String delimiter, String repeater, String closingToken) {
        String methodName = Stam.START_LINE + openingToken + Stam.ANY_WHITESPACES;
        String notLastArgument = "(" + Stam.ANY_WHITESPACES + listElement + Stam.ANY_WHITESPACES + delimiter + Stam.ANY_WHITESPACES + ")";
        String lastArgument = "(" + Stam.ANY_WHITESPACES + listElement + Stam.ANY_WHITESPACES + ")";
        return methodName + "\\(" + Stam.ANY_WHITESPACES +
                notLastArgument + "*" +
                lastArgument + repeater +
                "\\)" + Stam.ANY_WHITESPACES + closingToken + Stam.END_LINE;
    }

    private static String getArgumentValue() {
        String literalString = "(" +
                "(" + Literals.INT + ")|" +
                "(" + Literals.STRING + ")|" +
                "(" + Literals.DOUBLE + ")|" +
                "(" + Literals.BOOLEAN + ")|" +
                "(" + Literals.CHAR + "))";

        return Stam.ANY_WHITESPACES + "(" + literalString + "|" + Stam.NAME_OF_VARIABLE + ")";
    }

    private static String getConditionExpression() {
        String literalString = "(" + Literals.INT + "|" + Literals.DOUBLE + "|" + Literals.BOOLEAN + ")";

        return "(" + literalString + "|" + Stam.NAME_OF_VARIABLE + ")";
    }
}