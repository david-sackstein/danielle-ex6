package oop.ex6.strings;

public class Primitives {
    public static final String WHITESPACES = "\\s*";
    public static final String DIGIT = "[0-9]";
    public static final String NAME_OF_VARIABLE = "(_\\w+|[A-Za-z]+[\\w]*)";
    public static final String NAME_OF_METHOD = "([A-Za-z]+[\\w]*)" + WHITESPACES;

    public static final String START_LINE = "^" + WHITESPACES;
    public static final String END_LINE = WHITESPACES + "$";
    public static final String COMMENT_LINE = START_LINE + "//+.*";
    public static final String EMPTY_LINE = START_LINE;

    public static final String END_OF_BLOCK = START_LINE + "}" + END_LINE;
    public static final String RETURN_STATEMENT = START_LINE + "return" + WHITESPACES + ";" + END_LINE;

    public static final String OPTIONAL_FINAL = "(final)?" + WHITESPACES;
    public static final String OPTIONAL_FINAL_AT_START = START_LINE + OPTIONAL_FINAL;
}