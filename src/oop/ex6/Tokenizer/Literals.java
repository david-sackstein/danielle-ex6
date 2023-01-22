package oop.ex6.Tokenizer;

public class Literals {
    public static final String INT_LITERAL = "-?" + Stam.ANY_WHITESPACES + Stam.DIGIT + "+";
    public static final String DOUBLE_LITERAL = "(-?[1-9]+\\d*(\\.[0-9])*[0-9]*|0(\\.[0-9])*[0-9]*)";
    public static final String CHAR_LITERAL = "'.'";
    public static final String STRING_LITERAL = "\"" + Stam.ANY_WHITESPACES + ".*" + Stam.ANY_WHITESPACES + "\"";
    public static final String BOOLEAN_LITERAL = "true|false|" + DOUBLE_LITERAL + "|" + INT_LITERAL;

}
