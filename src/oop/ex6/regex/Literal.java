package oop.ex6.regex;

import oop.ex6.patterns.Literals;

import java.util.regex.Pattern;

public class Literal {
    public static final Pattern INT_LITERAL = Pattern.compile(Literals.INT);
    public static final Pattern DOUBLE_LITERAL = Pattern.compile(Literals.DOUBLE);
    public static final Pattern STRING_LITERAL = Pattern.compile(Literals.STRING);
    public static final Pattern BOOLEAN_LITERAL = Pattern.compile(Literals.BOOLEAN);
    public static final Pattern CHAR_LITERAL = Pattern.compile(Literals.CHAR);
}
