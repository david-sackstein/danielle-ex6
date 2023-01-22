package oop.ex6.strings;

public class Condition {
    public static final String EXPRESSION = getExpression();
    public static final String BLOCK = getBlock();

    private static String getBlock() {
        String orAnd = "(\\|\\||\\&\\&)";
        String conditionExpression = Primitives.ANY_WHITESPACES + EXPRESSION + Primitives.ANY_WHITESPACES;
        return Helper.getList("(while|if)", conditionExpression, orAnd, "+", "\\{");
    }

    private static String getExpression() {
        String literalString = "(" + Literals.INT + "|" + Literals.DOUBLE + "|" + Literals.BOOLEAN + ")";
        return "(" + literalString + "|" + Primitives.NAME_OF_VARIABLE + ")";
    }
}
