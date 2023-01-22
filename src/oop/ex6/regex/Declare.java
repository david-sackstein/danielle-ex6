package oop.ex6.regex;

import oop.ex6.patterns.Declarations;

import java.util.regex.Pattern;

public class Declare {
    public static final Pattern DECLARATION_INT = Pattern.compile(Declarations.INT);
    public static final Pattern DECLARATION_STRING = Pattern.compile(Declarations.STRING);
    public static final Pattern DECLARATION_DOUBLE = Pattern.compile(Declarations.DOUBLE);
    public static final Pattern DECLARATION_BOOLEAN = Pattern.compile(Declarations.BOOLEAN);
    public static final Pattern DECLARATION_CHAR = Pattern.compile(Declarations.CHAR);
    public static final Pattern ARGUMENT_DECLARATION = Pattern.compile(Declarations.ARGUMENT);
    public static final Pattern METHOD_DECLARATION = Pattern.compile(Declarations.METHOD);
}
