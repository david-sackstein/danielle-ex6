package oop.ex6.strings;

import static oop.ex6.strings.Primitives.*;

public class Helper {
    public static String getList(
        String openingToken, String listElement, String delimiter, String repeater, String closingToken) {

        String methodName = START_LINE + openingToken + WHITESPACES;
        String notLastArgument = "(" + WHITESPACES + listElement + WHITESPACES + delimiter + WHITESPACES + ")";
        String lastArgument = "(" + WHITESPACES + listElement + WHITESPACES + ")";
        return methodName + "\\(" + WHITESPACES +
                notLastArgument + "*" +
                lastArgument + repeater +
                "\\)" + WHITESPACES + closingToken + END_LINE;
    }
}
