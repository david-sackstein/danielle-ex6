package oop.ex6.Tokenizer;

import java.util.regex.Pattern;

/**
 * Patterns provides all the compiled regex patterns required by the Tokenizer
 * The compilation of each regex is performed only once throughout the program
 */
public class Regex {
    public static final Pattern EMPTY_LINE = Pattern.compile(Stam.EMPTY_LINE);
    public static final Pattern COMMENT_LINE = Pattern.compile(Stam.COMMENT_LINE);
    public static final Pattern RETURN_STATEMENT = Pattern.compile(Patterns.RETURN_STATEMENT);
    public static final Pattern END_OF_BLOCK = Pattern.compile(Patterns.END_OF_BLOCK);

    public static final Pattern OPTIONAL_ASSIGNMENT_INT = Pattern.compile(Assignment.OPTIONAL_INT);
    public static final Pattern OPTIONAL_ASSIGNMENT_STRING = Pattern.compile(Assignment.OPTIONAL_STRING);
    public static final Pattern OPTIONAL_ASSIGNMENT_DOUBLE = Pattern.compile(Assignment.OPTIONAL_DOUBLE);
    public static final Pattern OPTIONAL_ASSIGNMENT_BOOLEAN = Pattern.compile(Assignment.OPTIONAL_BOOLEAN);
    public static final Pattern OPTIONAL_ASSIGNMENT_CHAR = Pattern.compile(Assignment.OPTIONAL_CHAR);

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

    public static final Pattern DECLARATION_INT = Pattern.compile(Patterns.DECLARATION_INT);
    public static final Pattern DECLARATION_STRING = Pattern.compile(Patterns.DECLARATION_STRING);
    public static final Pattern DECLARATION_DOUBLE = Pattern.compile(Patterns.DECLARATION_DOUBLE);
    public static final Pattern DECLARATION_BOOLEAN = Pattern.compile(Patterns.DECLARATION_BOOLEAN);
    public static final Pattern DECLARATION_CHAR = Pattern.compile(Patterns.DECLARATION_CHAR);

    public static final Pattern NAME_OF_VARIABLE = Pattern.compile(Stam.NAME_OF_VARIABLE);

    public static final Pattern INT_LITERAL = Pattern.compile(Literals.INT);
    public static final Pattern DOUBLE_LITERAL = Pattern.compile(Literals.DOUBLE);
    public static final Pattern STRING_LITERAL = Pattern.compile(Literals.STRING);
    public static final Pattern BOOLEAN_LITERAL = Pattern.compile(Literals.BOOLEAN);
    public static final Pattern CHAR_LITERAL = Pattern.compile(Literals.CHAR);

    public static final Pattern ARGUMENT_DECLARATION = Pattern.compile(Patterns.ARGUMENT_DECLARATION);
    public static final Pattern METHOD_DECLARATION = Pattern.compile(Patterns.METHOD_DECLARATION);
    public static final Pattern ARGUMENT_VALUE = Pattern.compile(Patterns.ARGUMENT_VALUE);
    public static final Pattern METHOD_INVOCATION = Pattern.compile(Patterns.METHOD_INVOCATION);
    public static final Pattern CONDITION_BLOCK = Pattern.compile(Patterns.CONDITION_BLOCK);
    public static final Pattern CONDITION_EXPRESSION = Pattern.compile(Patterns.CONDITION_EXPRESSION);
}