package oop.ex6.regex;

public class Condition {
    public static final String EXPRESSION = getExpression();
    public static final String BLOCK = getBlock();

    private static String getBlock() {
        String orAnd = "(\\|\\||\\&\\&)";
        String conditionExpression = Constants.ANY_WHITESPACES + EXPRESSION + Constants.ANY_WHITESPACES;
        return Helper.getList("(while|if)", conditionExpression, orAnd, "+", "\\{");
    }

    private static String getExpression() {
        String literalString = "(" + Literals.INT + "|" + Literals.DOUBLE + "|" + Literals.BOOLEAN + ")";
        return "(" + literalString + "|" + Constants.NAME_OF_VARIABLE + ")";
    }
}
