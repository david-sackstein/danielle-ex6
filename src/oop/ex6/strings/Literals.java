package oop.ex6.strings;

import static oop.ex6.strings.Primitives.*;

public class Literals {
    public static final String INT = "-?" + WHITESPACES + DIGIT + "+";
    public static final String DOUBLE = "(-?[1-9]+\\d*(\\.[0-9])*[0-9]*|0(\\.[0-9])*[0-9]*)";
    public static final String CHAR = "'.'";
    public static final String STRING = "\"" + WHITESPACES + ".*" + WHITESPACES + "\"";
    public static final String BOOLEAN = "true|false|" + DOUBLE + "|" + INT;
}
