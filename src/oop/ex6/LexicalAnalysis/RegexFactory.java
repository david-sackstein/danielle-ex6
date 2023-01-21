package oop.ex6.LexicalAnalysis;

import java.util.regex.Pattern;

/**
 * RegexFactory provides all the compiled regex patterns required by the LexicalAnalyzer
 * The compilation of each regex is performed only once throughout the program
 */
public class RegexFactory {

    public static final Pattern EMPTY_LINE = Pattern.compile(RegexStrings.EMPTY_LINE);
    public static final Pattern COMMENT_LINE = Pattern.compile(RegexStrings.COMMENT_LINE);
    public static final Pattern RETURN_STATEMENT = Pattern.compile(RegexStrings.RETURN_STATEMENT);
    public static final Pattern END_OF_BLOCK = Pattern.compile(RegexStrings.END_OF_BLOCK);

    public static final Pattern OPTIONAL_ASSIGNMENT_INT = Pattern.compile(RegexStrings.OPTIONAL_ASSIGNMENT_INT);
    public static final Pattern OPTIONAL_ASSIGNMENT_STRING = Pattern.compile(RegexStrings.OPTIONAL_ASSIGNMENT_STRING);
    public static final Pattern OPTIONAL_ASSIGNMENT_DOUBLE = Pattern.compile(RegexStrings.OPTIONAL_ASSIGNMENT_DOUBLE);
    public static final Pattern OPTIONAL_ASSIGNMENT_BOOLEAN = Pattern.compile(RegexStrings.OPTIONAL_ASSIGNMENT_BOOLEAN);
    public static final Pattern OPTIONAL_ASSIGNMENT_CHAR = Pattern.compile(RegexStrings.OPTIONAL_ASSIGNMENT_CHAR);

    public static final Pattern ASSIGNMENT_INT = Pattern.compile(RegexStrings.ASSIGNMENT_INT);
    public static final Pattern ASSIGNMENT_STRING = Pattern.compile(RegexStrings.ASSIGNMENT_STRING);
    public static final Pattern ASSIGNMENT_DOUBLE = Pattern.compile(RegexStrings.ASSIGNMENT_DOUBLE);
    public static final Pattern ASSIGNMENT_BOOLEAN = Pattern.compile(RegexStrings.ASSIGNMENT_BOOLEAN);
    public static final Pattern ASSIGNMENT_CHAR = Pattern.compile(RegexStrings.ASSIGNMENT_CHAR);

    public static final Pattern ASSIGNMENT_INT_LINE = Pattern.compile(RegexStrings.ASSIGNMENT_INT_LINE);
    public static final Pattern ASSIGNMENT_STRING_LINE = Pattern.compile(RegexStrings.ASSIGNMENT_STRING_LINE);
    public static final Pattern ASSIGNMENT_DOUBLE_LINE = Pattern.compile(RegexStrings.ASSIGNMENT_DOUBLE_LINE);
    public static final Pattern ASSIGNMENT_BOOLEAN_LINE = Pattern.compile(RegexStrings.ASSIGNMENT_BOOLEAN_LINE);
    public static final Pattern ASSIGNMENT_CHAR_LINE = Pattern.compile(RegexStrings.ASSIGNMENT_CHAR_LINE);

    public static final Pattern DECLARATION_INT = Pattern.compile(RegexStrings.DECLARATION_INT);
    public static final Pattern DECLARATION_STRING = Pattern.compile(RegexStrings.DECLARATION_STRING);
    public static final Pattern DECLARATION_DOUBLE = Pattern.compile(RegexStrings.DECLARATION_DOUBLE);
    public static final Pattern DECLARATION_BOOLEAN = Pattern.compile(RegexStrings.DECLARATION_BOOLEAN);
    public static final Pattern DECLARATION_CHAR = Pattern.compile(RegexStrings.DECLARATION_CHAR);
    public static final Pattern NAME_OF_VARIABLE = Pattern.compile(RegexStrings.NAME_OF_VARIABLE);

    public static final Pattern INT_LITERAL = Pattern.compile(RegexStrings.INT_LITERAL);
    public static final Pattern DOUBLE_LITERAL = Pattern.compile(RegexStrings.DOUBLE_LITERAL);
    public static final Pattern STRING_LITERAL = Pattern.compile(RegexStrings.STRING_LITERAL);
    public static final Pattern BOOLEAN_LITERAL = Pattern.compile(RegexStrings.BOOLEAN_LITERAL);
    public static final Pattern CHAR_LITERAL = Pattern.compile(RegexStrings.CHAR_LITERAL);

    public static final Pattern ARGUMENT_DECLARATION = Pattern.compile(RegexStrings.ARGUMENT_DECLARATION);
    public static final Pattern METHOD_DECLARATION = Pattern.compile(RegexStrings.METHOD_DECLARATION);
    public static final Pattern ARGUMENT_VALUE = Pattern.compile(RegexStrings.ARGUMENT_VALUE);
    public static final Pattern METHOD_INVOCATION = Pattern.compile(RegexStrings.METHOD_INVOCATION);
    public static final Pattern CONDITION_BLOCK = Pattern.compile(RegexStrings.CONDITION_BLOCK);
    public static final Pattern CONDITION_EXPRESSION = Pattern.compile(RegexStrings.CONDITION_EXPRESSION);
}