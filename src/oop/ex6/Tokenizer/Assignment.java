package oop.ex6.Tokenizer;

public class Assignment {
    public static final String ASSIGNMENT_INT_RIGHT_SIDE = getAssignmentRightSide(Literals.INT_LITERAL);
    public static final String ASSIGNMENT_STRING_RIGHT_SIDE = getAssignmentRightSide(Literals.STRING_LITERAL);
    public static final String ASSIGNMENT_DOUBLE_RIGHT_SIDE = getAssignmentRightSide(Literals.DOUBLE_LITERAL);
    public static final String ASSIGNMENT_BOOLEAN_RIGHT_SIDE = getAssignmentRightSide(Literals.BOOLEAN_LITERAL);
    public static final String ASSIGNMENT_CHAR_RIGHT_SIDE = getAssignmentRightSide(Literals.CHAR_LITERAL);


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

    public static final String ASSIGNMENT_INT_LINE = Stam.START_LINE + "(" + ASSIGNMENT_INT + ");";
    public static final String ASSIGNMENT_STRING_LINE = Stam.START_LINE + "(" + ASSIGNMENT_STRING + ");";
    public static final String ASSIGNMENT_DOUBLE_LINE = Stam.START_LINE + "(" + ASSIGNMENT_DOUBLE + ");";
    public static final String ASSIGNMENT_BOOLEAN_LINE = Stam.START_LINE + "(" + ASSIGNMENT_BOOLEAN + ");";
    public static final String ASSIGNMENT_CHAR_LINE = Stam.START_LINE + "(" + ASSIGNMENT_CHAR + ");";

    public static final String NAME_OF_VARIABLE = "(_\\w+|[A-Za-z]+[\\w]*)";
    public static final String NAME_OF_METHOD = "([A-Za-z]+[\\w]*)" + Stam.ANY_WHITESPACES;

    private static String getSingleOptionalAssignment(String rightSide) {
        return NAME_OF_VARIABLE + rightSide + "?" + Stam.ANY_WHITESPACES;
    }

    private static String getSingleAssignment(String rightSide) {
        return NAME_OF_VARIABLE + rightSide + Stam.ANY_WHITESPACES;
    }

    private static String getAssignmentRightSide(String literalString) {
        return "(" + Stam.ANY_WHITESPACES + "=" + Stam.ANY_WHITESPACES + "(" + literalString + "|" + NAME_OF_VARIABLE + "))";
    }
}
