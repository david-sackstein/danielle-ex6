package oop.ex6.strings;

import static oop.ex6.strings.Primitives.*;

public class Declarations {
    public static final String INT = getLine("int", Assignment.OPTIONAL_INT);
    public static final String STRING = getLine("String", Assignment.OPTIONAL_STRING);
    public static final String DOUBLE = getLine("double", Assignment.OPTIONAL_DOUBLE);
    public static final String BOOLEAN = getLine("boolean", Assignment.OPTIONAL_BOOLEAN);
    public static final String CHAR = getLine("char", Assignment.OPTIONAL_CHAR);

    ;

    public static String getLine(String typeString, String partialDeclaration) {
        String notLastDeclaration = "(" + partialDeclaration + "," + WHITESPACES + ")";
        String lastDeclaration = "(" + partialDeclaration + ")";

        return OPTIONAL_FINAL_AT_START +
                "(" + typeString + ") +" + WHITESPACES +
                notLastDeclaration + "*" +
                lastDeclaration +
                ";" + END_LINE;
    }

}
