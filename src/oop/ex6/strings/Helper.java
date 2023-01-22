package oop.ex6.strings;

import static oop.ex6.strings.Primitives.*;

public class Helper {
    public static String getMethodOrCondition(
        String openingToken, String listElement, String delimiter, String repeater, String closingToken) {

        String start = START_LINE + openingToken + WHITESPACES;
        String expression = WHITESPACES + listElement + WHITESPACES;
        String notLastExpression = "(" + expression + delimiter + WHITESPACES + ")";
        String lastExpression = "(" + expression + WHITESPACES + ")";
        return start + "\\(" + WHITESPACES +
            notLastExpression + "*" +
            lastExpression + repeater +
            "\\)" + WHITESPACES + closingToken + END_LINE;
    }
}
