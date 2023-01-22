package oop.ex6.Tokenizer;

public class Declarations {
    public static final String INT = getLine("int", Assignment.OPTIONAL_INT);
    public static final String STRING = getLine("String", Assignment.OPTIONAL_STRING);
    public static final String DOUBLE = getLine("double", Assignment.OPTIONAL_DOUBLE);
    public static final String BOOLEAN = getLine("boolean", Assignment.OPTIONAL_BOOLEAN);
    public static final String CHAR = getLine("char", Assignment.OPTIONAL_CHAR);
    public static final String METHOD = getMethod();

    public static String getLine(String typeString, String partialDeclaration) {
        String notLastDeclaration = "(" + partialDeclaration + "," + Stam.ANY_WHITESPACES + ")";
        String lastDeclaration = "(" + partialDeclaration + ")";

        return Stam.FINAL_AT_START +
                "(" + typeString + ") +" + Stam.ANY_WHITESPACES +
                notLastDeclaration + "*" +
                lastDeclaration +
                ";" + Stam.END_LINE;
    }

    private static String getMethod() {
        return Patterns.getList("void" + Stam.ANY_WHITESPACES + Stam.NAME_OF_METHOD, Patterns.ARGUMENT_DECLARATION, ",", "?", "\\{");
    }
}
