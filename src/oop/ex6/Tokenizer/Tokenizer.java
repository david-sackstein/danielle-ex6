package oop.ex6.Tokenizer;

import oop.ex6.AbstractSyntaxTree.*;
import oop.ex6.regex.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LexicalAnalyzer parses javaS text at the textual level.
 * It uses regular expressions to perform textual validation of text and to break the text into tokens
 */
public class Tokenizer {

    public boolean isCommentOrEmptyLine(String line) {
        return RegexPrimitives.COMMENT_LINE.matcher(line).matches() ||
                RegexPrimitives.EMPTY_LINE.matcher(line).matches();
    }

    public boolean isReturnStatement(String line) {
        return RegexPrimitives.RETURN_STATEMENT.matcher(line).matches();
    }

    public boolean isEndOfBlock(String line) {
        return RegexPrimitives.END_OF_BLOCK.matcher(line).matches();
    }

    public boolean isNameOfVariable(String token) {
        return RegexPrimitives.NAME_OF_VARIABLE.matcher(token).matches();
    }

    public boolean isTypeMatch(TypedValue.Type expectedType, String rightSide) {
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
        return splitDeclaration(line, RegexDeclarations.INT, RegexAssignment.OPTIONAL_INT);
    }

    public ArrayList<ArrayList<String>> splitDeclarationString(String line) {
        return splitDeclaration(line, RegexDeclarations.STRING, RegexAssignment.OPTIONAL_STRING);
    }

    public ArrayList<ArrayList<String>> splitDeclarationBoolean(String line) {
        return splitDeclaration(line, RegexDeclarations.BOOLEAN, RegexAssignment.OPTIONAL_BOOLEAN);
    }

    public ArrayList<ArrayList<String>> splitDeclarationChar(String line) {
        return splitDeclaration(line, RegexDeclarations.CHAR, RegexAssignment.OPTIONAL_CHAR);
    }

    public ArrayList<ArrayList<String>> splitDeclarationDouble(String line) {
        return splitDeclaration(line, RegexDeclarations.DOUBLE, RegexAssignment.OPTIONAL_DOUBLE);
    }

    public ArrayList<ArrayList<String>> splitAssignmentInt(String line) {
        return splitAssignmentLine(line, RegexAssignment.INT_LINE, RegexAssignment.INT);
    }

    public ArrayList<ArrayList<String>> splitAssignmentString(String line) {
        return splitAssignmentLine(line, RegexAssignment.STRING_LINE, RegexAssignment.STRING);
    }

    public ArrayList<ArrayList<String>> splitAssignmentBoolean(String line) {
        return splitAssignmentLine(line, RegexAssignment.BOOLEAN_LINE, RegexAssignment.BOOLEAN);
    }

    public ArrayList<ArrayList<String>> splitAssignmentChar(String line) {
        return splitAssignmentLine(line, RegexAssignment.CHAR_LINE, RegexAssignment.CHAR);
    }

    public ArrayList<ArrayList<String>> splitAssignmentDouble(String line) {
        return splitAssignmentLine(line, RegexAssignment.DOUBLE_LINE, RegexAssignment.DOUBLE);
    }

    public ArrayList<ArrayList<String>> splitMethodDeclaration(String line) {
        return splitMethod(line, RegexDeclarations.METHOD, RegexDeclarations.ARGUMENT);
    }

    public ArrayList<ArrayList<String>> splitMethodInvocation(String line) {
        return splitMethod(line, RegexBlocks.METHOD_INVOCATION, RegexBlocks.ARGUMENT_VALUE);
    }

    public ArrayList<ArrayList<String>> splitBlockCondition(String line) {
        return splitDeclaration(line, RegexBlocks.CONDITION_BLOCK, RegexBlocks.CONDITION_EXPRESSION);
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

    private static ArrayList<ArrayList<String>> splitMethod(String line, Pattern patternMethod, Pattern patternArgument) {

        Matcher matcher = patternMethod.matcher(line);
        if (matcher.matches()) {

            ArrayList<ArrayList<String>> arrayLists = new ArrayList<ArrayList<String>>();

            arrayLists.add(
                    getMethodName(matcher));

            int indexOfArgumentList = matcher.end(1);

            Matcher argumentMatcher = patternArgument.matcher(line);
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

    private ArrayList<ArrayList<String>> splitDeclaration(String line, Pattern declarationPattern, Pattern assignmentPattern) {

        Matcher declarationMatcher = declarationPattern.matcher(line);

        if (!declarationMatcher.find()) {
            return null;
        }

        return splitOptionalAssignments(line, assignmentPattern);
    }

    private ArrayList<ArrayList<String>> splitAssignmentLine(String line, Pattern lineMatcher, Pattern assignmentPattern) {

        if (!lineMatcher.matcher(line).matches()) {
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
        return RegexLiterals.INT.matcher(token).matches();
    }

    private boolean isBoolean(String token) {
        return RegexLiterals.BOOLEAN.matcher(token).matches();
    }

    private boolean isDouble(String token) {
        return RegexLiterals.DOUBLE.matcher(token).matches();
    }

    private boolean isString(String token) {
        return RegexLiterals.STRING.matcher(token).matches();
    }

    private boolean isChar(String token) {
        return RegexLiterals.CHAR.matcher(token).matches();
    }
}
