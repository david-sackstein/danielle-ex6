package oop.ex6.Tokenizer;

public class Constants {
    public static final String ANY_WHITESPACES = "\\s*";
    public static final String DIGIT = "[0-9]";
    public static final String NAME_OF_VARIABLE = "(_\\w+|[A-Za-z]+[\\w]*)";
    public static final String NAME_OF_METHOD = "([A-Za-z]+[\\w]*)" + ANY_WHITESPACES;

    public static final String START_LINE = "^" + ANY_WHITESPACES;
    public static final String END_LINE = ANY_WHITESPACES + "$";
    public static final String COMMENT_LINE = START_LINE + "[/][/]+.*";
    public static final String EMPTY_LINE = START_LINE;

    public static final String END_OF_BLOCK = START_LINE + "}" + END_LINE;
    public static final String RETURN_STATEMENT = START_LINE + "return" + ANY_WHITESPACES + ";" + END_LINE;

    public static final String FINAL = "(final)*" + ANY_WHITESPACES;
    public static final String FINAL_AT_START = START_LINE + FINAL;
}
