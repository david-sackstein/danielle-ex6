package oop.ex6.regex;

import oop.ex6.strings.Literals;

import java.util.regex.Pattern;

public class RegexLiterals {
    public static final Pattern INT = Pattern.compile(Literals.INT);
    public static final Pattern DOUBLE = Pattern.compile(Literals.DOUBLE);
    public static final Pattern STRING = Pattern.compile(Literals.STRING);
    public static final Pattern BOOLEAN = Pattern.compile(Literals.BOOLEAN);
    public static final Pattern CHAR = Pattern.compile(Literals.CHAR);
}
