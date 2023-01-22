package oop.ex6.strings;

public class Helper {
    public static String getList(
            String openingToken, String listElement, String delimiter, String repeater, String closingToken) {
        String methodName = Primitives.START_LINE + openingToken + Primitives.ANY_WHITESPACES;
        String notLastArgument = "(" + Primitives.ANY_WHITESPACES + listElement + Primitives.ANY_WHITESPACES + delimiter + Primitives.ANY_WHITESPACES + ")";
        String lastArgument = "(" + Primitives.ANY_WHITESPACES + listElement + Primitives.ANY_WHITESPACES + ")";
        return methodName + "\\(" + Primitives.ANY_WHITESPACES +
                notLastArgument + "*" +
                lastArgument + repeater +
                "\\)" + Primitives.ANY_WHITESPACES + closingToken + Primitives.END_LINE;
    }
}
