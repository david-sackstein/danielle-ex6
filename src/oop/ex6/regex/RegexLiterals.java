package oop.ex6.regex;

import oop.ex6.strings.Literals;

import java.util.regex.Pattern;
/**
 this class holds five final static Pattern variables, each representing a different data type of
 literal. These literals are: INT, DOUBLE, STRING, BOOLEAN, and CHAR.
 Each of the patterns is compiled using the regular expression string that corresponds to the respective
 literal in the Literals class.
 */
public class RegexLiterals {
    public static final Pattern INT = Pattern.compile(Literals.INT);
    public static final Pattern DOUBLE = Pattern.compile(Literals.DOUBLE);
    public static final Pattern STRING = Pattern.compile(Literals.STRING);
    public static final Pattern BOOLEAN = Pattern.compile(Literals.BOOLEAN);
    public static final Pattern CHAR = Pattern.compile(Literals.CHAR);
}
