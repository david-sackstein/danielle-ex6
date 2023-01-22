package oop.ex6.strings;

import static oop.ex6.strings.Primitives.*;

public class Literals {
    public static final String INT = "-?" + WHITESPACES + DIGIT + "+";
    // TODO: check whether DOUBLE covers first digit zero or starting with a dot.
    public static final String DOUBLE = "(-?[1-9]+\\d*(\\.[0-9])*[0-9]*|0(\\.[0-9])*[0-9]*)";
    public static final String CHAR = "'.'";
    public static final String STRING = "\".*\"";
    public static final String BOOLEAN = "true|false|" + DOUBLE + "|" + INT;
    public static final String ANY = "(" +
        "(" + Literals.INT + ")|" +
        "(" + Literals.STRING + ")|" +
        "(" + Literals.DOUBLE + ")|" +
        "(" + Literals.BOOLEAN + ")|" +
        "(" + Literals.CHAR + "))";

}
