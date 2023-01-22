package oop.ex6.patterns;

public class Declarations {
    public static final String INT = getLine("int", Assignment.OPTIONAL_INT);
    public static final String STRING = getLine("String", Assignment.OPTIONAL_STRING);
    public static final String DOUBLE = getLine("double", Assignment.OPTIONAL_DOUBLE);
    public static final String BOOLEAN = getLine("boolean", Assignment.OPTIONAL_BOOLEAN);
    public static final String CHAR = getLine("char", Assignment.OPTIONAL_CHAR);
    public static final String ARGUMENT = Primitives.FINAL + "(int|double|String|boolean|char)" + Primitives.ANY_WHITESPACES + Primitives.NAME_OF_VARIABLE;

    public static final String METHOD = getMethod();

    public static String getLine(String typeString, String partialDeclaration) {
        String notLastDeclaration = "(" + partialDeclaration + "," + Primitives.ANY_WHITESPACES + ")";
        String lastDeclaration = "(" + partialDeclaration + ")";

        return Primitives.FINAL_AT_START +
                "(" + typeString + ") +" + Primitives.ANY_WHITESPACES +
                notLastDeclaration + "*" +
                lastDeclaration +
                ";" + Primitives.END_LINE;
    }

    private static String getMethod() {
        return Helper.getList("void" + Primitives.ANY_WHITESPACES + Primitives.NAME_OF_METHOD, ARGUMENT, ",", "?", "\\{");
    }
}