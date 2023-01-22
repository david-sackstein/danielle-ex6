package oop.ex6.patterns;

public class Literals {
    public static final String INT = "-?" + Primitives.ANY_WHITESPACES + Primitives.DIGIT + "+";
    public static final String DOUBLE = "(-?[1-9]+\\d*(\\.[0-9])*[0-9]*|0(\\.[0-9])*[0-9]*)";
    public static final String CHAR = "'.'";
    public static final String STRING = "\"" + Primitives.ANY_WHITESPACES + ".*" + Primitives.ANY_WHITESPACES + "\"";
    public static final String BOOLEAN = "true|false|" + DOUBLE + "|" + INT;
}
