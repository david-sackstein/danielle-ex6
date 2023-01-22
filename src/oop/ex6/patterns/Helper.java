package oop.ex6.patterns;

public class Helper {
    public static String getList(
            String openingToken, String listElement, String delimiter, String repeater, String closingToken) {
        String methodName = Constants.START_LINE + openingToken + Constants.ANY_WHITESPACES;
        String notLastArgument = "(" + Constants.ANY_WHITESPACES + listElement + Constants.ANY_WHITESPACES + delimiter + Constants.ANY_WHITESPACES + ")";
        String lastArgument = "(" + Constants.ANY_WHITESPACES + listElement + Constants.ANY_WHITESPACES + ")";
        return methodName + "\\(" + Constants.ANY_WHITESPACES +
                notLastArgument + "*" +
                lastArgument + repeater +
                "\\)" + Constants.ANY_WHITESPACES + closingToken + Constants.END_LINE;
    }
}
