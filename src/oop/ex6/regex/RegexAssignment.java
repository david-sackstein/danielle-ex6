package oop.ex6.regex;

import oop.ex6.strings.Assignment;

import java.util.regex.Pattern;

/**
 * This class contains compiled regular expression patterns for checking various types of assignments in
 * the program. The assignments include primitive types like int, double, boolean, char, and
 * string, as well as the respective optional values for each type.
 */

public class RegexAssignment {
    public static final Pattern INT = Pattern.compile(Assignment.INT);
    public static final Pattern STRING = Pattern.compile(Assignment.STRING);
    public static final Pattern DOUBLE = Pattern.compile(Assignment.DOUBLE);
    public static final Pattern BOOLEAN = Pattern.compile(Assignment.BOOLEAN);
    public static final Pattern CHAR = Pattern.compile(Assignment.CHAR);

    public static final Pattern INT_LINE = Pattern.compile(Assignment.INT_LINE);
    public static final Pattern STRING_LINE = Pattern.compile(Assignment.STRING_LINE);
    public static final Pattern DOUBLE_LINE = Pattern.compile(Assignment.DOUBLE_LINE);
    public static final Pattern BOOLEAN_LINE = Pattern.compile(Assignment.BOOLEAN_LINE);
    public static final Pattern CHAR_LINE = Pattern.compile(Assignment.CHAR_LINE);

    public static final Pattern OPTIONAL_INT = Pattern.compile(Assignment.OPTIONAL_INT);
    public static final Pattern OPTIONAL_STRING = Pattern.compile(Assignment.OPTIONAL_STRING);
    public static final Pattern OPTIONAL_DOUBLE = Pattern.compile(Assignment.OPTIONAL_DOUBLE);
    public static final Pattern OPTIONAL_BOOLEAN = Pattern.compile(Assignment.OPTIONAL_BOOLEAN);
    public static final Pattern OPTIONAL_CHAR = Pattern.compile(Assignment.OPTIONAL_CHAR);
}
