package oop.ex6.regex;

import oop.ex6.strings.*;

import java.util.regex.Pattern;

public class RegexDeclarations {
    public static final Pattern INT = Pattern.compile(Declarations.INT);
    public static final Pattern STRING = Pattern.compile(Declarations.STRING);
    public static final Pattern DOUBLE = Pattern.compile(Declarations.DOUBLE);
    public static final Pattern BOOLEAN = Pattern.compile(Declarations.BOOLEAN);
    public static final Pattern CHAR = Pattern.compile(Declarations.CHAR);

    public static final Pattern ARGUMENT = Pattern.compile(MethodString.ARGUMENT);
    public static final Pattern METHOD = Pattern.compile(MethodString.DECLARATION);
}
