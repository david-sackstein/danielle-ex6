package oop.ex6.strings;

import static oop.ex6.strings.Primitives.*;

public class Declarations {
    public static final String INT = build("int", Assignment.OPTIONAL_INT);
    public static final String STRING = build("String", Assignment.OPTIONAL_STRING);
    public static final String DOUBLE = build("double", Assignment.OPTIONAL_DOUBLE);
    public static final String BOOLEAN = build("boolean", Assignment.OPTIONAL_BOOLEAN);
    public static final String CHAR = build("char", Assignment.OPTIONAL_CHAR);

    public static String build(String typeString, String partialDeclaration) {
        String notLastDeclaration = "(" + partialDeclaration + "," + WHITESPACES + ")";
        String lastDeclaration = "(" + partialDeclaration + ")";

        return OPTIONAL_FINAL_AT_START +
                "(" + typeString + ") +" + WHITESPACES +
                notLastDeclaration + "*" +
                lastDeclaration +
                ";" + END_LINE;
    }
}
