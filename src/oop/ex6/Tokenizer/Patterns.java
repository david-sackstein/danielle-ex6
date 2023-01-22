package oop.ex6.Tokenizer;

/**
 * RegexStrings generates all the regex strings required by the RegexFactory
 */
public class Patterns {

    public static final String ANY_WHITESPACES = "\\s*";
    public static final String START_LINE = "^" + ANY_WHITESPACES;
    public static final String END_LINE = ANY_WHITESPACES + "$";
    public static final String FINAL = "(final)*" + ANY_WHITESPACES;
    public static final String FINAL_AT_START = START_LINE + FINAL;

    public static final String COMMENT_LINE = START_LINE + "[/][/]+.*";
    public static final String EMPTY_LINE = START_LINE;
    public static final String DIGIT = "[0-9]";
    public static final String INT_LITERAL = "-?" + ANY_WHITESPACES + DIGIT + "+";
    public static final String DOUBLE_LITERAL = "(-?[1-9]+\\d*(\\.[0-9])*[0-9]*|0(\\.[0-9])*[0-9]*)";

    public static final String CHAR_LITERAL = "'.'";
    public static final String STRING_LITERAL = "\"" + ANY_WHITESPACES + ".*" + ANY_WHITESPACES + "\"";
    public static final String BOOLEAN_LITERAL = "true|false|" + DOUBLE_LITERAL + "|" + INT_LITERAL;
    public static final String NAME_OF_VARIABLE = "(_\\w+|[A-Za-z]+[\\w]*)";// + ANY_WHITESPACES;
    public static final String NAME_OF_METHOD = "([A-Za-z]+[\\w]*)" + ANY_WHITESPACES;

    public static final String RETURN_STATEMENT = START_LINE + "return" + ANY_WHITESPACES + ";" + END_LINE;
    public static final String END_OF_BLOCK = START_LINE + "}" + END_LINE;

    public static final String ASSIGNMENT_INT_RIGHT_SIDE = getAssignmentRightSide(INT_LITERAL);
    public static final String ASSIGNMENT_STRING_RIGHT_SIDE = getAssignmentRightSide(STRING_LITERAL);
    public static final String ASSIGNMENT_DOUBLE_RIGHT_SIDE = getAssignmentRightSide(DOUBLE_LITERAL);
    public static final String ASSIGNMENT_BOOLEAN_RIGHT_SIDE = getAssignmentRightSide(BOOLEAN_LITERAL);
    public static final String ASSIGNMENT_CHAR_RIGHT_SIDE = getAssignmentRightSide(CHAR_LITERAL);

    public static final String OPTIONAL_ASSIGNMENT_INT = getSingleOptionalAssignment(ASSIGNMENT_INT_RIGHT_SIDE);
    public static final String OPTIONAL_ASSIGNMENT_STRING = getSingleOptionalAssignment(ASSIGNMENT_STRING_RIGHT_SIDE);
    public static final String OPTIONAL_ASSIGNMENT_DOUBLE = getSingleOptionalAssignment(ASSIGNMENT_DOUBLE_RIGHT_SIDE);
    public static final String OPTIONAL_ASSIGNMENT_BOOLEAN = getSingleOptionalAssignment(ASSIGNMENT_BOOLEAN_RIGHT_SIDE);
    public static final String OPTIONAL_ASSIGNMENT_CHAR = getSingleOptionalAssignment(ASSIGNMENT_CHAR_RIGHT_SIDE);

    public static final String ASSIGNMENT_INT = getSingleAssignment(ASSIGNMENT_INT_RIGHT_SIDE);
    public static final String ASSIGNMENT_STRING = getSingleAssignment(ASSIGNMENT_STRING_RIGHT_SIDE);
    public static final String ASSIGNMENT_DOUBLE = getSingleAssignment(ASSIGNMENT_DOUBLE_RIGHT_SIDE);
    public static final String ASSIGNMENT_BOOLEAN = getSingleAssignment(ASSIGNMENT_BOOLEAN_RIGHT_SIDE);
    public static final String ASSIGNMENT_CHAR = getSingleAssignment(ASSIGNMENT_CHAR_RIGHT_SIDE);

    public static final String ASSIGNMENT_INT_LINE = START_LINE + "(" + ASSIGNMENT_INT + ");";
    public static final String ASSIGNMENT_STRING_LINE = START_LINE + "(" + ASSIGNMENT_STRING + ");";
    public static final String ASSIGNMENT_DOUBLE_LINE = START_LINE + "(" + ASSIGNMENT_DOUBLE + ");";
    public static final String ASSIGNMENT_BOOLEAN_LINE = START_LINE + "(" + ASSIGNMENT_BOOLEAN + ");";
    public static final String ASSIGNMENT_CHAR_LINE = START_LINE + "(" + ASSIGNMENT_CHAR + ");";

    public static final String DECLARATION_INT = getDeclarationsLine("int", OPTIONAL_ASSIGNMENT_INT);
    public static final String DECLARATION_STRING = getDeclarationsLine("String", OPTIONAL_ASSIGNMENT_STRING);
    public static final String DECLARATION_DOUBLE = getDeclarationsLine("double", OPTIONAL_ASSIGNMENT_DOUBLE);
    public static final String DECLARATION_BOOLEAN = getDeclarationsLine("boolean", OPTIONAL_ASSIGNMENT_BOOLEAN);
    public static final String DECLARATION_CHAR = getDeclarationsLine("char", OPTIONAL_ASSIGNMENT_CHAR);

    public static final String ARGUMENT_DECLARATION = FINAL + "(int|double|String|boolean|char)" + ANY_WHITESPACES + NAME_OF_VARIABLE;
    public static final String ARGUMENT_VALUE = getArgumentValue();
    public static final String CONDITION_EXPRESSION = getConditionExpression();

    public static final String METHOD_DECLARATION = getMethodDeclaration();
    public static final String METHOD_INVOCATION = getMethodInvocation();
    public static final String CONDITION_BLOCK = getConditionBlock();

    private static String getAssignmentRightSide(String literalString) {
        return "(" + ANY_WHITESPACES + "=" + ANY_WHITESPACES + "(" + literalString + "|" + NAME_OF_VARIABLE + "))";
    }

    private static String getSingleOptionalAssignment(String rightSide) {
        return NAME_OF_VARIABLE + rightSide + "?" + ANY_WHITESPACES;
    }

    private static String getSingleAssignment(String rightSide) {
        return NAME_OF_VARIABLE + rightSide + ANY_WHITESPACES;
    }

    private static String getDeclarationsLine(String typeString, String partialDeclaration) {
        String notLastDeclaration = "(" + partialDeclaration + "," + ANY_WHITESPACES + ")";
        String lastDeclaration = "(" + partialDeclaration + ")";

        return FINAL_AT_START +
                "(" + typeString + ") +" + ANY_WHITESPACES +
                notLastDeclaration + "*" +
                lastDeclaration +
                ";" + END_LINE;
    }

    private static String getMethodDeclaration() {
        return getList("void" + ANY_WHITESPACES + NAME_OF_METHOD, ARGUMENT_DECLARATION, ",", "?", "\\{");
    }

    private static String getConditionBlock() {
        String orAnd = "(\\|\\||\\&\\&)";
        String conditionExpression = ANY_WHITESPACES + CONDITION_EXPRESSION + ANY_WHITESPACES;
        return getList("(while|if)", conditionExpression, orAnd, "+", "\\{");
    }

    private static String getMethodInvocation() {
        String methodName = START_LINE + NAME_OF_METHOD;
        String notLastArgument = "(" + ARGUMENT_VALUE + ANY_WHITESPACES + ")";
        String lastArgument = "(,"+ANY_WHITESPACES + ARGUMENT_VALUE + ANY_WHITESPACES + ")";
        return methodName + "\\(" +
                notLastArgument + "?" +
                lastArgument + "*" +
                "\\)" + ANY_WHITESPACES + ";" + END_LINE;
    }

    private static String getList(
            String openingToken, String listElement, String delimiter, String repeater, String closingToken) {
        String methodName = START_LINE + openingToken + ANY_WHITESPACES;
        String notLastArgument = "(" + ANY_WHITESPACES + listElement + ANY_WHITESPACES + delimiter + ANY_WHITESPACES + ")";
        String lastArgument = "(" + ANY_WHITESPACES + listElement + ANY_WHITESPACES + ")";
        return methodName + "\\(" + ANY_WHITESPACES +
                notLastArgument + "*" +
                lastArgument + repeater +
                "\\)" + ANY_WHITESPACES + closingToken + END_LINE;
    }

    private static String getArgumentValue() {
        String literalString = "(" +
                "(" + INT_LITERAL + ")|" +
                "(" + STRING_LITERAL + ")|" +
                "(" + DOUBLE_LITERAL + ")|" +
                "(" + BOOLEAN_LITERAL + ")|" +
                "(" + CHAR_LITERAL + "))";

        return ANY_WHITESPACES + "(" + literalString + "|" + NAME_OF_VARIABLE + ")";
    }

    private static String getConditionExpression() {
        String literalString = "(" + INT_LITERAL + "|" + DOUBLE_LITERAL + "|" + BOOLEAN_LITERAL + ")";

        return "(" + literalString + "|" + NAME_OF_VARIABLE + ")";
    }
}