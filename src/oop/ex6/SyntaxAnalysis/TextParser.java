package oop.ex6.SyntaxAnalysis;

import oop.ex6.AbstractSyntaxTree.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {

    public boolean isCommentOrEmptyLine(String line) {
        return Patterns.PATTEN_COMMENT_LINE.matcher(line).matches() ||
            Patterns.PATTEN_EMPTY_LINE.matcher(line).matches();
    }

    public boolean isReturnStatement(String line) {
        return Patterns.PATTEN_RETURN_STATEMENT.matcher(line).matches();
    }

    public boolean isEndOfBlock(String line) {
        return Patterns.PATTEN_END_OF_BLOCK.matcher(line).matches();
    }

    public boolean isMethodCall(String line) {
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

    int a, b=2, c=4;
    public ArrayList<ArrayList<String>> splitDeclarationInt(String line) {
        return splitDeclaration(line,
            Patterns.PATTERN_DECLARATION_INT,
            Patterns.PATTERN_OPTIONAL_ASSIGNMENT_INT);
    }

    public ArrayList<ArrayList<String>> splitDeclarationString(String line) {
        return splitDeclaration(line, Patterns.PATTERN_DECLARATION_STRING, Patterns.PATTERN_OPTIONAL_ASSIGNMENT_STRING);
    }

    public ArrayList<ArrayList<String>> splitDeclarationBoolean(String line) {
        return splitDeclaration(line, Patterns.PATTERN_DECLARATION_BOOLEAN, Patterns.PATTERN_OPTIONAL_ASSIGNMENT_BOOLEAN);
    }

    public ArrayList<ArrayList<String>> splitDeclarationChar(String line) {
        return splitDeclaration(line, Patterns.PATTERN_DECLARATION_CHAR, Patterns.PATTERN_OPTIONAL_ASSIGNMENT_CHAR);
    }

    public ArrayList<ArrayList<String>> splitDeclarationDouble(String line) {
        return splitDeclaration(line, Patterns.PATTERN_DECLARATION_DOUBLE, Patterns.PATTERN_OPTIONAL_ASSIGNMENT_DOUBLE);
    }

    public ArrayList<ArrayList<String>> splitAssignmentInt(String line) {
        return splitAssignmentLine(line, Patterns.PATTERN_ASSIGNMENT_INT_LINE, Patterns.PATTERN_ASSIGNMENT_INT);
    }

    public ArrayList<ArrayList<String>> splitAssignmentString(String line) {
        return splitAssignmentLine(line, Patterns.PATTERN_ASSIGNMENT_STRING_LINE, Patterns.PATTERN_ASSIGNMENT_STRING);
    }

    public ArrayList<ArrayList<String>> splitAssignmentBoolean(String line) {
        return splitAssignmentLine(line, Patterns.PATTERN_ASSIGNMENT_BOOLEAN_LINE, Patterns.PATTERN_ASSIGNMENT_BOOLEAN);
    }

    public ArrayList<ArrayList<String>> splitAssignmentChar(String line) {
        return splitAssignmentLine(line, Patterns.PATTERN_ASSIGNMENT_CHAR_LINE, Patterns.PATTERN_ASSIGNMENT_CHAR);
    }

    public ArrayList<ArrayList<String>> splitAssignmentDouble(String line) {
        return splitAssignmentLine(line, Patterns.PATTERN_ASSIGNMENT_DOUBLE_LINE, Patterns.PATTERN_ASSIGNMENT_DOUBLE);
    }

    public static ArrayList<ArrayList<String>> splitMethod(String line) {

        Matcher matcher = Patterns.PATTERN_METHOD_DECLARATION.matcher(line);
        if (matcher.matches()) {

            ArrayList<ArrayList<String>> arrayLists = new ArrayList<ArrayList<String>>();

            arrayLists.add(
                    getMethodName(matcher));

            int indexOfArgumentList = matcher.end(1);

            Matcher argumentMatcher = Patterns.PATTERN_ARGUMENT.matcher(line);
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
        return Patterns.PATTERN_NAME_OF_VARIABLE.matcher(token).matches();
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
        return Patterns.PATTERN_INT_LITERAL.matcher(token).matches();
    }

    private boolean isBoolean(String token) {
        return Patterns.PATTERN_BOOLEAN_LITERAL.matcher(token).matches();
    }

    private boolean isDouble(String token) {
        return Patterns.PATTERN_DOUBLE_LITERAL.matcher(token).matches();
    }

    private boolean isString(String token) {
        return Patterns.PATTERN_STRING_LITERAL.matcher(token).matches();
    }

    private boolean isChar(String token) {
        return Patterns.PATTERN_CHAR_LITERAL.matcher(token).matches();
    }

    public static void Test() {
        String line = "f(a, b);";

        Matcher matcher = Patterns.PATTERN_METHOD_INVOCATION.matcher(line);

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
