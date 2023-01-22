package oop.ex6.regex;

import oop.ex6.patterns.Assignment;

import java.util.regex.Pattern;

public class Assign {
    public static final Pattern ASSIGNMENT_INT = Pattern.compile(Assignment.INT);
    public static final Pattern ASSIGNMENT_STRING = Pattern.compile(Assignment.STRING);
    public static final Pattern ASSIGNMENT_DOUBLE = Pattern.compile(Assignment.DOUBLE);
    public static final Pattern ASSIGNMENT_BOOLEAN = Pattern.compile(Assignment.BOOLEAN);
    public static final Pattern ASSIGNMENT_CHAR = Pattern.compile(Assignment.CHAR);
    public static final Pattern ASSIGNMENT_INT_LINE = Pattern.compile(Assignment.INT_LINE);
    public static final Pattern ASSIGNMENT_STRING_LINE = Pattern.compile(Assignment.STRING_LINE);
    public static final Pattern ASSIGNMENT_DOUBLE_LINE = Pattern.compile(Assignment.DOUBLE_LINE);
    public static final Pattern ASSIGNMENT_BOOLEAN_LINE = Pattern.compile(Assignment.BOOLEAN_LINE);
    public static final Pattern ASSIGNMENT_CHAR_LINE = Pattern.compile(Assignment.CHAR_LINE);

    public static final Pattern OPTIONAL_ASSIGNMENT_INT = Pattern.compile(Assignment.OPTIONAL_INT);
    public static final Pattern OPTIONAL_ASSIGNMENT_STRING = Pattern.compile(Assignment.OPTIONAL_STRING);
    public static final Pattern OPTIONAL_ASSIGNMENT_DOUBLE = Pattern.compile(Assignment.OPTIONAL_DOUBLE);
    public static final Pattern OPTIONAL_ASSIGNMENT_BOOLEAN = Pattern.compile(Assignment.OPTIONAL_BOOLEAN);
    public static final Pattern OPTIONAL_ASSIGNMENT_CHAR = Pattern.compile(Assignment.OPTIONAL_CHAR);
}
