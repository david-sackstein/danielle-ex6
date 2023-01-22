package oop.ex6.Tokenizer;

public class Helper {
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
}
