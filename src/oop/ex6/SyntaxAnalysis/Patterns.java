package oop.ex6.SyntaxAnalysis;

import java.util.regex.Pattern;

public class Patterns {
    private static final String ANY_WHITESPACES = "\\s*";
    private static final String START_LINE = "^" + ANY_WHITESPACES;
    private static final String END_LINE = ANY_WHITESPACES + "$";
    private static final String FINAL = "(final)*" + ANY_WHITESPACES;
    private static final String FINAL_AT_START = START_LINE + FINAL;

    private static final String COMMENT_LINE = START_LINE + "//+.*";
    private static final String EMPTY_LINE = START_LINE;
    private static final String DIGIT = "[0-9]";
    private static final String ANY_DIGITS = "[0-9]*";
    private static final String INT_LITERAL = "-?" + ANY_WHITESPACES + DIGIT + "+";
    private static final String DOUBLE_LITERAL = "-?[0-9]+(\\.[0-9]+)?"; // TODO
    private static final String CHAR_LITERAL = "'.'";
    private static final String STRING_LITERAL = "\"" + ANY_WHITESPACES + "[\\w]*" + ANY_WHITESPACES + "\"";
    private static final String BOOLEAN_LITERAL = "true|false|" + INT_LITERAL + "|" + DOUBLE_LITERAL;
    private static final String NAME_OF_VARIABLE = "([_A-Za-z]+[\\w]*)" + ANY_WHITESPACES;
    private static final String RETURN_STATEMENT = START_LINE + "return" + ANY_WHITESPACES + ";" + END_LINE;
    private static final String END_OF_BLOCK = START_LINE + "}" + END_LINE;

    private static final String ASSIGNMENT_INT_RIGHT_SIDE = getAssignmentRightSide(INT_LITERAL);
    private static final String ASSIGNMENT_STRING_RIGHT_SIDE = getAssignmentRightSide(STRING_LITERAL);
    private static final String ASSIGNMENT_DOUBLE_RIGHT_SIDE = getAssignmentRightSide(DOUBLE_LITERAL);
    private static final String ASSIGNMENT_BOOLEAN_RIGHT_SIDE = getAssignmentRightSide(BOOLEAN_LITERAL);
    private static final String ASSIGNMENT_CHAR_RIGHT_SIDE = getAssignmentRightSide(CHAR_LITERAL);

    public static final Pattern PATTEN_EMPTY_LINE = Pattern.compile(EMPTY_LINE);
    public static final Pattern PATTEN_COMMENT_LINE = Pattern.compile(COMMENT_LINE);

    public static final Pattern PATTEN_RETURN_STATEMENT = Pattern.compile(RETURN_STATEMENT);
    public static final Pattern PATTEN_END_OF_BLOCK = Pattern.compile(END_OF_BLOCK);

    private static final String OPTIONAL_ASSIGNMENT_INT = getSingleOptionalAssignment(ASSIGNMENT_INT_RIGHT_SIDE);
    private static final String OPTIONAL_ASSIGNMENT_STRING = getSingleOptionalAssignment(ASSIGNMENT_STRING_RIGHT_SIDE);
    private static final String OPTIONAL_ASSIGNMENT_DOUBLE = getSingleOptionalAssignment(ASSIGNMENT_DOUBLE_RIGHT_SIDE);
    private static final String OPTIONAL_ASSIGNMENT_BOOLEAN = getSingleOptionalAssignment(ASSIGNMENT_BOOLEAN_RIGHT_SIDE);
    private static final String OPTIONAL_ASSIGNMENT_CHAR = getSingleOptionalAssignment(ASSIGNMENT_CHAR_RIGHT_SIDE);

    private static final String ASSIGNMENT_INT = getSingleAssignment(ASSIGNMENT_INT_RIGHT_SIDE);
    private static final String ASSIGNMENT_STRING = getSingleAssignment(ASSIGNMENT_STRING_RIGHT_SIDE);
    private static final String ASSIGNMENT_DOUBLE = getSingleAssignment(ASSIGNMENT_DOUBLE_RIGHT_SIDE);
    private static final String ASSIGNMENT_BOOLEAN = getSingleAssignment(ASSIGNMENT_BOOLEAN_RIGHT_SIDE);
    private static final String ASSIGNMENT_CHAR = getSingleAssignment(ASSIGNMENT_CHAR_RIGHT_SIDE);

    private static final String ASSIGNMENT_INT_LINE = START_LINE + "(" + ASSIGNMENT_INT + ")+";
    private static final String ASSIGNMENT_STRING_LINE = START_LINE + "(" + ASSIGNMENT_STRING + ")+";
    private static final String ASSIGNMENT_DOUBLE_LINE = START_LINE + "(" + ASSIGNMENT_DOUBLE + ")+";
    private static final String ASSIGNMENT_BOOLEAN_LINE = START_LINE + "(" + ASSIGNMENT_BOOLEAN + ")+";
    private static final String ASSIGNMENT_CHAR_LINE = START_LINE + "(" + ASSIGNMENT_CHAR + ")+";

    private static final String DECLARATION_INT = getDeclarationsLine("int", OPTIONAL_ASSIGNMENT_INT);
    private static final String DECLARATION_STRING = getDeclarationsLine("String", OPTIONAL_ASSIGNMENT_STRING);
    private static final String DECLARATION_DOUBLE = getDeclarationsLine("double", OPTIONAL_ASSIGNMENT_DOUBLE);
    private static final String DECLARATION_BOOLEAN = getDeclarationsLine("boolean", OPTIONAL_ASSIGNMENT_BOOLEAN);
    private static final String DECLARATION_CHAR = getDeclarationsLine("char", OPTIONAL_ASSIGNMENT_CHAR);

    private static final String ARGUMENT_DECLARATION = FINAL + "(int|double|String|boolean|char)" + ANY_WHITESPACES + NAME_OF_VARIABLE;
    private static final String METHOD_DECLARATION = getMethodDeclaration();
    private static final String METHOD_INVOCATION = getMethodInvocation();

    public static final Pattern PATTERN_OPTIONAL_ASSIGNMENT_INT = Pattern.compile(OPTIONAL_ASSIGNMENT_INT);
    public static final Pattern PATTERN_OPTIONAL_ASSIGNMENT_STRING = Pattern.compile(OPTIONAL_ASSIGNMENT_STRING);
    public static final Pattern PATTERN_OPTIONAL_ASSIGNMENT_DOUBLE = Pattern.compile(OPTIONAL_ASSIGNMENT_DOUBLE);
    public static final Pattern PATTERN_OPTIONAL_ASSIGNMENT_BOOLEAN = Pattern.compile(OPTIONAL_ASSIGNMENT_BOOLEAN);
    public static final Pattern PATTERN_OPTIONAL_ASSIGNMENT_CHAR = Pattern.compile(OPTIONAL_ASSIGNMENT_CHAR);

    public static final Pattern PATTERN_ASSIGNMENT_INT = Pattern.compile(ASSIGNMENT_INT);
    public static final Pattern PATTERN_ASSIGNMENT_STRING = Pattern.compile(ASSIGNMENT_STRING);
    public static final Pattern PATTERN_ASSIGNMENT_DOUBLE = Pattern.compile(ASSIGNMENT_DOUBLE);
    public static final Pattern PATTERN_ASSIGNMENT_BOOLEAN = Pattern.compile(ASSIGNMENT_BOOLEAN);
    public static final Pattern PATTERN_ASSIGNMENT_CHAR = Pattern.compile(ASSIGNMENT_CHAR);

    public static final Pattern PATTERN_ASSIGNMENT_INT_LINE  = Pattern.compile(ASSIGNMENT_INT_LINE);
    public static final Pattern PATTERN_ASSIGNMENT_STRING_LINE  = Pattern.compile(ASSIGNMENT_STRING_LINE);
    public static final Pattern PATTERN_ASSIGNMENT_DOUBLE_LINE  = Pattern.compile(ASSIGNMENT_DOUBLE_LINE);
    public static final Pattern PATTERN_ASSIGNMENT_BOOLEAN_LINE  = Pattern.compile(ASSIGNMENT_BOOLEAN_LINE);
    public static final Pattern PATTERN_ASSIGNMENT_CHAR_LINE = Pattern.compile(ASSIGNMENT_CHAR_LINE);

    public static final Pattern PATTERN_DECLARATION_INT = Pattern.compile(DECLARATION_INT);
    public static final Pattern PATTERN_DECLARATION_STRING = Pattern.compile(DECLARATION_STRING);
    public static final Pattern PATTERN_DECLARATION_DOUBLE = Pattern.compile(DECLARATION_DOUBLE);
    public static final Pattern PATTERN_DECLARATION_BOOLEAN = Pattern.compile(DECLARATION_BOOLEAN);
    public static final Pattern PATTERN_DECLARATION_CHAR = Pattern.compile(DECLARATION_CHAR);
    public static final Pattern PATTERN_NAME_OF_VARIABLE = Pattern.compile(NAME_OF_VARIABLE);

    public static final Pattern PATTERN_INT_LITERAL = Pattern.compile(INT_LITERAL);
    public static final Pattern PATTERN_DOUBLE_LITERAL = Pattern.compile(DOUBLE_LITERAL);
    public static final Pattern PATTERN_STRING_LITERAL = Pattern.compile(STRING_LITERAL);
    public static final Pattern PATTERN_BOOLEAN_LITERAL = Pattern.compile(BOOLEAN_LITERAL);
    public static final Pattern PATTERN_CHAR_LITERAL = Pattern.compile(CHAR_LITERAL);

    public static final Pattern PATTERN_ARGUMENT = Pattern.compile(ARGUMENT_DECLARATION);
    public static final Pattern PATTERN_METHOD_DECLARATION = Pattern.compile(METHOD_DECLARATION);
    public static final Pattern PATTERN_METHOD_INVOCATION = Pattern.compile(METHOD_INVOCATION);

    public static String getAssignmentRightSide(String literalString) {
        String s = "(=" + ANY_WHITESPACES + "(" + literalString + "|" + NAME_OF_VARIABLE + "))";
        return s;
    }

    public static String getSingleOptionalAssignment(String rightSide) {
        return NAME_OF_VARIABLE + rightSide + "?" + ANY_WHITESPACES;
    }

    public static String getSingleAssignment(String rightSide) {
        return NAME_OF_VARIABLE + rightSide + ANY_WHITESPACES;
    }

    public static String getDeclarationsLine(String typeString, String partialDeclaration) {

        String notLastDeclaration = "(" + partialDeclaration + "," + ANY_WHITESPACES + ")";
        String lastDeclaration = "(" + partialDeclaration + ")";

        return FINAL_AT_START +
            "(" + typeString + ") +" + // TODO
            notLastDeclaration + "*" +
            lastDeclaration +
            ";" + END_LINE;
    }

    public static String getMethodDeclaration() {
        String notLastArgument = "(" + ARGUMENT_DECLARATION + "," + ANY_WHITESPACES + ")";
        String lastArgument = "(" + ARGUMENT_DECLARATION + ")";
        String methodName = START_LINE + "void" + ANY_WHITESPACES + NAME_OF_VARIABLE;
        return methodName + "\\(" +
            notLastArgument + "*" +
            lastArgument + "?" +
            "\\)" + ANY_WHITESPACES + "\\{" + END_LINE;
    }

    //TODO not working
    public static String getMethodInvocation() {

//        String argument = "(" + INT_LITERAL + ")" + "|" +
//            "(" + DOUBLE_LITERAL + ")" + "|" +
//            "(" + STRING_LITERAL + ")" + "|" +
//            "(" + BOOLEAN_LITERAL + ")" + "|" +
//            "(" + CHAR_LITERAL + ")" + "|" +
//            NAME_OF_VARIABLE + ANY_WHITESPACES;
        String argument =
            "((" + INT_LITERAL + ")|" + "(" + "[_A-Za-z]+[\\w]*)";

        String notLastArgument = "(" + argument + ANY_WHITESPACES  + "),+ANY_WHITESPACES+)";
        String lastArgument = "(" + argument + "))";
        String methodName = START_LINE + NAME_OF_VARIABLE;

        String s = methodName + "\\(" +
            notLastArgument + "*" +
            lastArgument + "?" +
            "\\)" + ANY_WHITESPACES + ";" + END_LINE;

        return s;
    }
}
