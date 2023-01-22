package oop.ex6.Tokenizer;

public class Literals {
    public static final String INT = "-?" + Stam.ANY_WHITESPACES + Stam.DIGIT + "+";
    public static final String DOUBLE = "(-?[1-9]+\\d*(\\.[0-9])*[0-9]*|0(\\.[0-9])*[0-9]*)";
    public static final String CHAR = "'.'";
    public static final String STRING = "\"" + Stam.ANY_WHITESPACES + ".*" + Stam.ANY_WHITESPACES + "\"";
    public static final String BOOLEAN = "true|false|" + DOUBLE + "|" + INT;
}
