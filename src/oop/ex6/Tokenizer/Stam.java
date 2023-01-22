package oop.ex6.Tokenizer;

public class Stam {
    public static final String ANY_WHITESPACES = "\\s*";
    public static final String START_LINE = "^" + ANY_WHITESPACES;
    public static final String END_LINE = ANY_WHITESPACES + "$";
    public static final String FINAL = "(final)*" + ANY_WHITESPACES;
    public static final String FINAL_AT_START = START_LINE + FINAL;

    public static final String COMMENT_LINE = START_LINE + "[/][/]+.*";
    public static final String EMPTY_LINE = START_LINE;
    public static final String DIGIT = "[0-9]";

}
