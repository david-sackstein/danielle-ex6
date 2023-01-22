package oop.ex6.strings;

public class Assignment {
    public static final String INT_RIGHT_SIDE = getRightSide(Literals.INT);
    public static final String STRING_RIGHT_SIDE = getRightSide(Literals.STRING);
    public static final String DOUBLE_RIGHT_SIDE = getRightSide(Literals.DOUBLE);
    public static final String BOOLEAN_RIGHT_SIDE = getRightSide(Literals.BOOLEAN);
    public static final String CHAR_RIGHT_SIDE = getRightSide(Literals.CHAR);

    public static final String OPTIONAL_INT = getSingleOptional(INT_RIGHT_SIDE);
    public static final String OPTIONAL_STRING = getSingleOptional(STRING_RIGHT_SIDE);
    public static final String OPTIONAL_DOUBLE = getSingleOptional(DOUBLE_RIGHT_SIDE);
    public static final String OPTIONAL_BOOLEAN = getSingleOptional(BOOLEAN_RIGHT_SIDE);
    public static final String OPTIONAL_CHAR = getSingleOptional(CHAR_RIGHT_SIDE);

    public static final String INT = getSingle(INT_RIGHT_SIDE);
    public static final String STRING = getSingle(STRING_RIGHT_SIDE);
    public static final String DOUBLE = getSingle(DOUBLE_RIGHT_SIDE);
    public static final String BOOLEAN = getSingle(BOOLEAN_RIGHT_SIDE);
    public static final String CHAR = getSingle(CHAR_RIGHT_SIDE);

    public static final String INT_LINE = Primitives.START_LINE + "(" + INT + ");";
    public static final String STRING_LINE = Primitives.START_LINE + "(" + STRING + ");";
    public static final String DOUBLE_LINE = Primitives.START_LINE + "(" + DOUBLE + ");";
    public static final String BOOLEAN_LINE = Primitives.START_LINE + "(" + BOOLEAN + ");";
    public static final String CHAR_LINE = Primitives.START_LINE + "(" + CHAR + ");";

    private static String getSingleOptional(String rightSide) {
        return Primitives.NAME_OF_VARIABLE + rightSide + "?" + Primitives.ANY_WHITESPACES;
    }

    private static String getSingle(String rightSide) {
        return Primitives.NAME_OF_VARIABLE + rightSide + Primitives.ANY_WHITESPACES;
    }

    private static String getRightSide(String literalString) {
        return "(" + Primitives.ANY_WHITESPACES + "=" + Primitives.ANY_WHITESPACES + "(" + literalString + "|" + Primitives.NAME_OF_VARIABLE + "))";
    }
}
