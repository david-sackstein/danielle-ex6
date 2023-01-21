package oop.ex6.SyntaxAnalysis;

import oop.ex6.AbstractSyntaxTree.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAnalyzer {

    private static final String ANY_WHITESPACES = "\\s*";
    private static final String START_LINE = "^" + ANY_WHITESPACES;
    private static final String END_LINE = ANY_WHITESPACES + "$";
    private static final String FINAL = "(final)*" + ANY_WHITESPACES;
    private static final String FINAL_AT_START = START_LINE + FINAL;

    private static final String COMMENT_LINE = START_LINE + "[/][/]+.*";
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

    private static final Pattern PATTEN_EMPTY_LINE = Pattern.compile(EMPTY_LINE);
    private static final Pattern PATTEN_COMMENT_LINE = Pattern.compile(COMMENT_LINE);

    private static final Pattern PATTEN_RETURN_STATEMENT = Pattern.compile(RETURN_STATEMENT);
    private static final Pattern PATTEN_END_OF_BLOCK = Pattern.compile(END_OF_BLOCK);

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

    private static final Pattern PATTERN_OPTIONAL_ASSIGNMENT_INT = Pattern.compile(OPTIONAL_ASSIGNMENT_INT);
    private static final Pattern PATTERN_OPTIONAL_ASSIGNMENT_STRING = Pattern.compile(OPTIONAL_ASSIGNMENT_STRING);
    private static final Pattern PATTERN_OPTIONAL_ASSIGNMENT_DOUBLE = Pattern.compile(OPTIONAL_ASSIGNMENT_DOUBLE);
    private static final Pattern PATTERN_OPTIONAL_ASSIGNMENT_BOOLEAN = Pattern.compile(OPTIONAL_ASSIGNMENT_BOOLEAN);
    private static final Pattern PATTERN_OPTIONAL_ASSIGNMENT_CHAR = Pattern.compile(OPTIONAL_ASSIGNMENT_CHAR);

    private static final Pattern PATTERN_ASSIGNMENT_INT = Pattern.compile(ASSIGNMENT_INT);
    private static final Pattern PATTERN_ASSIGNMENT_STRING = Pattern.compile(ASSIGNMENT_STRING);
    private static final Pattern PATTERN_ASSIGNMENT_DOUBLE = Pattern.compile(ASSIGNMENT_DOUBLE);
    private static final Pattern PATTERN_ASSIGNMENT_BOOLEAN = Pattern.compile(ASSIGNMENT_BOOLEAN);
    private static final Pattern PATTERN_ASSIGNMENT_CHAR = Pattern.compile(ASSIGNMENT_CHAR);

    private static final Pattern PATTERN_ASSIGNMENT_INT_LINE  = Pattern.compile(ASSIGNMENT_INT_LINE);
    private static final Pattern PATTERN_ASSIGNMENT_STRING_LINE  = Pattern.compile(ASSIGNMENT_STRING_LINE);
    private static final Pattern PATTERN_ASSIGNMENT_DOUBLE_LINE  = Pattern.compile(ASSIGNMENT_DOUBLE_LINE);
    private static final Pattern PATTERN_ASSIGNMENT_BOOLEAN_LINE  = Pattern.compile(ASSIGNMENT_BOOLEAN_LINE);
    private static final Pattern PATTERN_ASSIGNMENT_CHAR_LINE = Pattern.compile(ASSIGNMENT_CHAR_LINE);

    private static final Pattern PATTERN_DECLARATION_INT = Pattern.compile(DECLARATION_INT);
    private static final Pattern PATTERN_DECLARATION_STRING = Pattern.compile(DECLARATION_STRING);
    private static final Pattern PATTERN_DECLARATION_DOUBLE = Pattern.compile(DECLARATION_DOUBLE);
    private static final Pattern PATTERN_DECLARATION_BOOLEAN = Pattern.compile(DECLARATION_BOOLEAN);
    private static final Pattern PATTERN_DECLARATION_CHAR = Pattern.compile(DECLARATION_CHAR);
    private static final Pattern PATTERN_NAME_OF_VARIABLE = Pattern.compile(NAME_OF_VARIABLE);

    private static final Pattern PATTERN_INT_LITERAL = Pattern.compile(INT_LITERAL);
    private static final Pattern PATTERN_DOUBLE_LITERAL = Pattern.compile(DOUBLE_LITERAL);
    private static final Pattern PATTERN_STRING_LITERAL = Pattern.compile(STRING_LITERAL);
    private static final Pattern PATTERN_BOOLEAN_LITERAL = Pattern.compile(BOOLEAN_LITERAL);
    private static final Pattern PATTERN_CHAR_LITERAL = Pattern.compile(CHAR_LITERAL);

    private static final Pattern PATTERN_ARGUMENT = Pattern.compile(ARGUMENT_DECLARATION);
    private static final Pattern PATTERN_METHOD_DECLARATION = Pattern.compile(METHOD_DECLARATION);
    private static final Pattern PATTERN_METHOD_INVOCATION = Pattern.compile(METHOD_INVOCATION);

    private static String getAssignmentRightSide(String literalString) {
        return "(=" + ANY_WHITESPACES + "(" + literalString + "|" + NAME_OF_VARIABLE + "))";
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
                "(" + typeString + ") +" + // TODO
                notLastDeclaration + "*" +
                lastDeclaration +
                ";" + END_LINE;
    }

    private static String getMethodDeclaration() {
        String notLastArgument = "(" + ARGUMENT_DECLARATION + "," + ANY_WHITESPACES + ")";
        String lastArgument = "(" + ARGUMENT_DECLARATION + ")";
        String methodName = START_LINE + "void" + ANY_WHITESPACES + NAME_OF_VARIABLE;
        return methodName + "\\(" +
                notLastArgument + "*" +
                lastArgument + "?" +
                "\\)" + ANY_WHITESPACES + "\\{" + END_LINE;
    }

    //TODO not working
    private static String getMethodInvocation() {

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

    public boolean isCommentOrEmptyLine(String line) {
        return PATTEN_COMMENT_LINE.matcher(line).matches() ||
                PATTEN_EMPTY_LINE.matcher(line).matches();
    }

    public boolean isReturnStatement(String line) {
        return PATTEN_RETURN_STATEMENT.matcher(line).matches();
    }

    public boolean isEndOfBlock(String line) {
        return PATTEN_END_OF_BLOCK.matcher(line).matches();
    }

    public boolean isMethodInvocation(String line) {
        return true;
    }

    public boolean isTypeMatch(TypedValue.Type expectedType, String rightSide) throws Exception {
        if (expectedType == TypedValue.Type.Int && isInt(rightSide)) {
            return true;
        }
        if (expectedType == TypedValue.Type.Boolean && isBoolean(rightSide)) {
            return true;
        }
        if (expectedType == TypedValue.Type.Double && isDouble(rightSide)) {
            return true;
        }
        if (expectedType == TypedValue.Type.String && isString(rightSide)) {
            return true;
        }
        if (expectedType == TypedValue.Type.Char && isChar(rightSide)) {
            return true;
        }
        return false;
    }

    public ArrayList<ArrayList<String>> splitDeclarationInt(String line) {
        return splitDeclaration(line, PATTERN_DECLARATION_INT, PATTERN_OPTIONAL_ASSIGNMENT_INT);
    }

    public ArrayList<ArrayList<String>> splitDeclarationString(String line) {
        return splitDeclaration(line, PATTERN_DECLARATION_STRING, PATTERN_OPTIONAL_ASSIGNMENT_STRING);
    }

    public ArrayList<ArrayList<String>> splitDeclarationBoolean(String line) {
        return splitDeclaration(line, PATTERN_DECLARATION_BOOLEAN, PATTERN_OPTIONAL_ASSIGNMENT_BOOLEAN);
    }

    public ArrayList<ArrayList<String>> splitDeclarationChar(String line) {
        return splitDeclaration(line, PATTERN_DECLARATION_CHAR, PATTERN_OPTIONAL_ASSIGNMENT_CHAR);
    }

    public ArrayList<ArrayList<String>> splitDeclarationDouble(String line) {
        return splitDeclaration(line, PATTERN_DECLARATION_DOUBLE, PATTERN_OPTIONAL_ASSIGNMENT_DOUBLE);
    }

    public ArrayList<ArrayList<String>> splitAssignmentInt(String line) {
        return splitAssignmentLine(line, PATTERN_ASSIGNMENT_INT_LINE, PATTERN_ASSIGNMENT_INT);
    }

    public ArrayList<ArrayList<String>> splitAssignmentString(String line) {
        return splitAssignmentLine(line, PATTERN_ASSIGNMENT_STRING_LINE, PATTERN_ASSIGNMENT_STRING);
    }

    public ArrayList<ArrayList<String>> splitAssignmentBoolean(String line) {
        return splitAssignmentLine(line, PATTERN_ASSIGNMENT_BOOLEAN_LINE, PATTERN_ASSIGNMENT_BOOLEAN);
    }

    public ArrayList<ArrayList<String>> splitAssignmentChar(String line) {
        return splitAssignmentLine(line, PATTERN_ASSIGNMENT_CHAR_LINE, PATTERN_ASSIGNMENT_CHAR);
    }

    public ArrayList<ArrayList<String>> splitAssignmentDouble(String line) {
        return splitAssignmentLine(line, PATTERN_ASSIGNMENT_DOUBLE_LINE, PATTERN_ASSIGNMENT_DOUBLE);
    }

    public static ArrayList<ArrayList<String>> splitMethod(String line) {

        Matcher matcher = PATTERN_METHOD_DECLARATION.matcher(line);
        if (matcher.matches()) {

            ArrayList<ArrayList<String>> arrayLists = new ArrayList<ArrayList<String>>();

            arrayLists.add(
                    getMethodName(matcher));

            int indexOfArgumentList = matcher.end(1);

            Matcher argumentMatcher = PATTERN_ARGUMENT.matcher(line);
            if (argumentMatcher.find(indexOfArgumentList)) {
                arrayLists.add(
                        getArgumentTokens(argumentMatcher));
                while (argumentMatcher.find()) {
                    arrayLists.add(
                            getArgumentTokens(argumentMatcher));
                }
            }
            return arrayLists;
        }
        return null;
    }

    private static ArrayList<String> getMethodName(Matcher matcher) {
        ArrayList<String> methodName = new ArrayList<String>();
        methodName.add(matcher.group(1));
        return methodName;
    }

    private static ArrayList<String> getArgumentTokens(Matcher argumentMatcher) {

        ArrayList<String> tokens = new ArrayList<String>();
        for (int i = 0; i < argumentMatcher.groupCount() + 1; i++) {
            String token = argumentMatcher.group(i);
            if (token != null) {
                tokens.add(token);
            }
        }
        return tokens;
    }

    public boolean isNameOfVariable(String token) {
        return PATTERN_NAME_OF_VARIABLE.matcher(token).matches();
    }

    private ArrayList<ArrayList<String>> splitDeclaration(String line, Pattern declarationPattern, Pattern assignmentPattern) {

        Matcher declarationMatcher = declarationPattern.matcher(line);

        if (!declarationMatcher.find()) {
            return null;
        }

        return splitOptionalAssignments(line, assignmentPattern);
    }

    private ArrayList<ArrayList<String>> splitAssignmentLine(String line, Pattern lineMatcher, Pattern assignmentPattern) {

        if (! lineMatcher.matcher(line).matches()){
            return null;
        }

        return splitOptionalAssignments(line, assignmentPattern);
    }

    private ArrayList<ArrayList<String>> splitOptionalAssignments(String line, Pattern assignmentPattern) {
        ArrayList<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>>();

        Matcher partialMatcher = assignmentPattern.matcher(line);

        while (partialMatcher.find()) {

            ArrayList<String> tokens = new ArrayList<String>();

            for (int i = 0; i < partialMatcher.groupCount(); i++) {
                String group = partialMatcher.group(i);

                if (group != null) {
                    tokens.add(group);
                }
            }

            listOfLists.add(tokens);
        }

        return listOfLists.size() == 0 ? null : listOfLists;
    }

    private boolean isInt(String token) {
        return PATTERN_INT_LITERAL.matcher(token).matches();
    }

    private boolean isBoolean(String token) {
        return PATTERN_BOOLEAN_LITERAL.matcher(token).matches();
    }

    private boolean isDouble(String token) {
        return PATTERN_DOUBLE_LITERAL.matcher(token).matches();
    }

    private boolean isString(String token) {
        return PATTERN_STRING_LITERAL.matcher(token).matches();
    }

    private boolean isChar(String token) {
        return PATTERN_CHAR_LITERAL.matcher(token).matches();
    }

    public static void Test() {
        String line = "f(a, b);";

        Matcher matcher = PATTERN_METHOD_INVOCATION.matcher(line);

        if (matcher.matches()) {
            System.out.printf("");
        }
        System.out.printf("");
    }

    public static TypedValue.Type getTypeFromString(String typeString) throws Exception {
        switch (typeString) {
            case "String":
                return TypedValue.Type.String;
            case "double":
                return TypedValue.Type.Double;
            case "int":
                return TypedValue.Type.Int;
            case "char":
                return TypedValue.Type.Char;
            case "boolean":
                return TypedValue.Type.Boolean;
            default:
                throw new Exception("?");
        }
    }
}
