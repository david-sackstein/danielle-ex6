package oop.ex6.Tokenizer;

/**
 * RegexStrings generates all the regex strings required by the RegexFactory
 */
public class Patterns {

    public static final String ARGUMENT_DECLARATION = Stam.FINAL + "(int|double|String|boolean|char)" + Stam.ANY_WHITESPACES + Stam.NAME_OF_VARIABLE;
    public static final String ARGUMENT_VALUE = getArgumentValue();
    public static final String CONDITION_EXPRESSION = getConditionExpression();

    public static final String METHOD_INVOCATION = getMethodInvocation();
    public static final String CONDITION_BLOCK = getConditionBlock();

    private static String getConditionBlock() {
        String orAnd = "(\\|\\||\\&\\&)";
        String conditionExpression = Stam.ANY_WHITESPACES + CONDITION_EXPRESSION + Stam.ANY_WHITESPACES;
        return Helper.getList("(while|if)", conditionExpression, orAnd, "+", "\\{");
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