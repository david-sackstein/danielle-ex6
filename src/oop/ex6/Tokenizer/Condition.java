package oop.ex6.Tokenizer;

public class Condition {
    public static final String CONDITION_EXPRESSION = getConditionExpression();
    public static final String CONDITION_BLOCK = getConditionBlock();

    private static String getConditionBlock() {
        String orAnd = "(\\|\\||\\&\\&)";
        String conditionExpression = Stam.ANY_WHITESPACES + CONDITION_EXPRESSION + Stam.ANY_WHITESPACES;
        return Helper.getList("(while|if)", conditionExpression, orAnd, "+", "\\{");
    }

    private static String getConditionExpression() {
        String literalString = "(" + Literals.INT + "|" + Literals.DOUBLE + "|" + Literals.BOOLEAN + ")";

        return "(" + literalString + "|" + Stam.NAME_OF_VARIABLE + ")";
    }
}
