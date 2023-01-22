package oop.ex6.Tokenizer;

/**
 * RegexStrings generates all the regex strings required by the RegexFactory
 */
public class Patterns {




    public static final String RETURN_STATEMENT = Stam.START_LINE + "return" + Stam.ANY_WHITESPACES + ";" + Stam.END_LINE;
    public static final String END_OF_BLOCK = Stam.START_LINE + "}" + Stam.END_LINE;




    public static final String DECLARATION_INT = getDeclarationsLine("int", Assignment.OPTIONAL_ASSIGNMENT_INT);
    public static final String DECLARATION_STRING = getDeclarationsLine("String", Assignment.OPTIONAL_ASSIGNMENT_STRING);
    public static final String DECLARATION_DOUBLE = getDeclarationsLine("double", Assignment.OPTIONAL_ASSIGNMENT_DOUBLE);
    public static final String DECLARATION_BOOLEAN = getDeclarationsLine("boolean", Assignment.OPTIONAL_ASSIGNMENT_BOOLEAN);
    public static final String DECLARATION_CHAR = getDeclarationsLine("char", Assignment.OPTIONAL_ASSIGNMENT_CHAR);

    public static final String ARGUMENT_DECLARATION = Stam.FINAL + "(int|double|String|boolean|char)" + Stam.ANY_WHITESPACES + Assignment.NAME_OF_VARIABLE;
    public static final String ARGUMENT_VALUE = getArgumentValue();
    public static final String CONDITION_EXPRESSION = getConditionExpression();

    public static final String METHOD_DECLARATION = getMethodDeclaration();
    public static final String METHOD_INVOCATION = getMethodInvocation();
    public static final String CONDITION_BLOCK = getConditionBlock();


    private static String getDeclarationsLine(String typeString, String partialDeclaration) {
        String notLastDeclaration = "(" + partialDeclaration + "," + Stam.ANY_WHITESPACES + ")";
        String lastDeclaration = "(" + partialDeclaration + ")";

        return Stam.FINAL_AT_START +
                "(" + typeString + ") +" + Stam.ANY_WHITESPACES +
                notLastDeclaration + "*" +
                lastDeclaration +
                ";" + Stam.END_LINE;
    }

    private static String getMethodDeclaration() {
        return getList("void" + Stam.ANY_WHITESPACES + Assignment.NAME_OF_METHOD, ARGUMENT_DECLARATION, ",", "?", "\\{");
    }

    private static String getConditionBlock() {
        String orAnd = "(\\|\\||\\&\\&)";
        String conditionExpression = Stam.ANY_WHITESPACES + CONDITION_EXPRESSION + Stam.ANY_WHITESPACES;
        return getList("(while|if)", conditionExpression, orAnd, "+", "\\{");
    }

    private static String getMethodInvocation() {
        String methodName = Stam.START_LINE + Assignment.NAME_OF_METHOD;
        String notLastArgument = "(" + ARGUMENT_VALUE + Stam.ANY_WHITESPACES + ")";
        String lastArgument = "(,"+Stam.ANY_WHITESPACES + ARGUMENT_VALUE + Stam.ANY_WHITESPACES + ")";
        return methodName + "\\(" +
                notLastArgument + "?" +
                lastArgument + "*" +
                "\\)" + Stam.ANY_WHITESPACES + ";" + Stam.END_LINE;
    }

    private static String getList(
            String openingToken, String listElement, String delimiter, String repeater, String closingToken) {
        String methodName = Stam.START_LINE + openingToken + Stam.ANY_WHITESPACES;
        String notLastArgument = "(" + Stam.ANY_WHITESPACES + listElement + Stam.ANY_WHITESPACES + delimiter + Stam.ANY_WHITESPACES + ")";
        String lastArgument = "(" + Stam.ANY_WHITESPACES + listElement + Stam.ANY_WHITESPACES + ")";
        return methodName + "\\(" + Stam.ANY_WHITESPACES +
                notLastArgument + "*" +
                lastArgument + repeater +
                "\\)" + Stam.ANY_WHITESPACES + closingToken + Stam.END_LINE;
    }

    private static String getArgumentValue() {
        String literalString = "(" +
                "(" + Literals.INT_LITERAL + ")|" +
                "(" + Literals.STRING_LITERAL + ")|" +
                "(" + Literals.DOUBLE_LITERAL + ")|" +
                "(" + Literals.BOOLEAN_LITERAL + ")|" +
                "(" + Literals.CHAR_LITERAL + "))";

        return Stam.ANY_WHITESPACES + "(" + literalString + "|" + Assignment.NAME_OF_VARIABLE + ")";
    }

    private static String getConditionExpression() {
        String literalString = "(" + Literals.INT_LITERAL + "|" + Literals.DOUBLE_LITERAL + "|" + Literals.BOOLEAN_LITERAL + ")";

        return "(" + literalString + "|" + Assignment.NAME_OF_VARIABLE + ")";
    }
}