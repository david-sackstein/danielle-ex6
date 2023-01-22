package oop.ex6.regex;

public class Declarations {
    public static final String INT = getLine("int", Assignment.OPTIONAL_INT);
    public static final String STRING = getLine("String", Assignment.OPTIONAL_STRING);
    public static final String DOUBLE = getLine("double", Assignment.OPTIONAL_DOUBLE);
    public static final String BOOLEAN = getLine("boolean", Assignment.OPTIONAL_BOOLEAN);
    public static final String CHAR = getLine("char", Assignment.OPTIONAL_CHAR);
    public static final String ARGUMENT = Constants.FINAL + "(int|double|String|boolean|char)" + Constants.ANY_WHITESPACES + Constants.NAME_OF_VARIABLE;

    public static final String METHOD = getMethod();

    public static String getLine(String typeString, String partialDeclaration) {
        String notLastDeclaration = "(" + partialDeclaration + "," + Constants.ANY_WHITESPACES + ")";
        String lastDeclaration = "(" + partialDeclaration + ")";

        return Constants.FINAL_AT_START +
                "(" + typeString + ") +" + Constants.ANY_WHITESPACES +
                notLastDeclaration + "*" +
                lastDeclaration +
                ";" + Constants.END_LINE;
    }

    private static String getMethod() {
        return Helper.getList("void" + Constants.ANY_WHITESPACES + Constants.NAME_OF_METHOD, ARGUMENT, ",", "?", "\\{");
    }
}
