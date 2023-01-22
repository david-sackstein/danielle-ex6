package oop.ex6.strings;

import static oop.ex6.strings.Primitives.*;

public class Condition {
    public static final String EXPRESSION = getExpression();
    public static final String BLOCK = getBlock();

    private static String getBlock() {
        String orAnd = "(\\|\\||\\&\\&)";
        String conditionExpression = WHITESPACES + EXPRESSION + WHITESPACES;
        return Helper.getList("(while|if)", conditionExpression, orAnd, "+", "\\{");
    }

    private static String getExpression() {
        String literalString = "(" + Literals.INT + "|" + Literals.DOUBLE + "|" + Literals.BOOLEAN + ")";
        return "(" + literalString + "|" + NAME_OF_VARIABLE + ")";
    }
}
