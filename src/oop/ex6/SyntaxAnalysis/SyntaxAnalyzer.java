package oop.ex6.SyntaxAnalysis;

import oop.ex6.AbstractSyntaxTree.*;

import java.util.ArrayList;
import java.util.Objects;

public class SyntaxAnalyzer {

    private LexicalAnalyzer lexicalAnalyzer;

    public SyntaxAnalyzer() {
        lexicalAnalyzer = new LexicalAnalyzer();
    }

    public Scope parse(String line, Scope scope) throws Exception {

        if (lexicalAnalyzer.isCommentOrEmptyLine(line)) {
            return scope;
        }

        if (addVariablesDeclarations(line, scope)) {
            return scope;
        }

        if (doAssignments(line, scope)) {
            return scope;
        }

        Scope method = createMethod(line, scope);
        if (method != null) {
            return method;
        }

        if (lexicalAnalyzer.isReturnStatement(line)) {
            scope.youHaveReturned();
            return scope;
        }

        if (lexicalAnalyzer.isEndOfBlock(line)) {
            return scope.yourScopeEnded();
        }

        if (lexicalAnalyzer.isMethodInvocation(line)) {
            return scope;
        }

        throw new Exception("Unrecognized syntax");
    }

    private Method createMethod(String line, Scope scope) throws Exception {
        ArrayList<ArrayList<String>> arrayLists = lexicalAnalyzer.splitMethod(line);
        if (arrayLists == null) {
            return null;
        }
        MethodDeclaration methodDeclaration = getMethodDeclaration(arrayLists);
        Method method = new Method(scope);
        method.methodDeclaration = methodDeclaration;
        return method;
    }

    private MethodDeclaration getMethodDeclaration(ArrayList<ArrayList<String>> arrayLists) throws Exception {

        MethodDeclaration methodDeclaration = new MethodDeclaration();

        methodDeclaration.name = arrayLists.get(0).get(0);

        for (int i = 1; i < arrayLists.size(); i++) {

            ArrayList<String> argumentTokens = arrayLists.get(i);

            Variable variable = getVariable(argumentTokens);

            methodDeclaration.addVariable(variable);
        }

        return methodDeclaration;
    }

    private Variable getVariable(ArrayList<String> argumentTokens) throws Exception {

        boolean isFinal = argumentTokens.get(1).equals("final");
        int start = isFinal ? 1 : 0;

        String nameString = argumentTokens.get(start + 2);
        String typeString = argumentTokens.get(start + 1);

        Variable variable = new Variable(nameString, lexicalAnalyzer.getTypeFromString(typeString));
        variable.isFinal = isFinal;
        variable.isInitialized = true; // because arguments passed to a method must have been initialized

        return variable;
    }

    private boolean doAssignments(String line, Scope scope) throws Exception {
        ArrayList<ArrayList<String>> arrayLists = lexicalAnalyzer.splitAssignmentInt(line);
        if (arrayLists != null) {
            doAssignments(scope, arrayLists, TypedValue.Type.Int);
            return true;
        }
        arrayLists = lexicalAnalyzer.splitAssignmentString(line);
        if (arrayLists != null) {
            doAssignments(scope, arrayLists, TypedValue.Type.String);
            return true;
        }
        arrayLists = lexicalAnalyzer.splitAssignmentDouble(line);
        if (arrayLists != null) {
            doAssignments(scope, arrayLists, TypedValue.Type.Double);
            return true;
        }
        arrayLists = lexicalAnalyzer.splitAssignmentBoolean(line);
        if (arrayLists != null) {
            doAssignments(scope, arrayLists, TypedValue.Type.Boolean);
            return true;
        }
        arrayLists = lexicalAnalyzer.splitAssignmentChar(line);
        if (arrayLists != null) {
            doAssignments(scope, arrayLists, TypedValue.Type.Char);
            return true;
        }
        return false;
    }

    private void doAssignments(Scope scope, ArrayList<ArrayList<String>> arrayLists, TypedValue.Type type) throws Exception {
        for (ArrayList<String> tokens : arrayLists) { // TODO are we allowed more than one
            String name = tokens.get(1);
            Variable variable = scope.findVariable(name, type);
            if (variable == null) {
                throw new Exception("No such variable " + name);
            }
            if (variable.type != type) {
                throw new Exception("Assigned to incorrect type");
            }
            variable.addAssignment(
                    getInitializer(scope, tokens, type));
        }
    }

    private boolean addVariablesDeclarations(String line, Scope scope) throws Exception {

        ArrayList<ArrayList<String>> tokens = lexicalAnalyzer.splitDeclarationInt(line);
        if (tokens != null) {
            addVariablesDeclarations(scope, tokens, TypedValue.Type.Int);
            return true;
        }
        tokens = lexicalAnalyzer.splitDeclarationString(line);
        if (tokens != null) {
            addVariablesDeclarations(scope, tokens, TypedValue.Type.String);
            return true;
        }
        tokens = lexicalAnalyzer.splitDeclarationDouble(line);
        if (tokens != null) {
            addVariablesDeclarations(scope, tokens, TypedValue.Type.Double);
            return true;
        }
        tokens = lexicalAnalyzer.splitDeclarationBoolean(line);
        if (tokens != null) {
            addVariablesDeclarations(scope, tokens, TypedValue.Type.Boolean);
            return true;
        }
        tokens = lexicalAnalyzer.splitDeclarationChar(line);
        if (tokens != null) {
            addVariablesDeclarations(scope, tokens, TypedValue.Type.Char);
            return true;
        }
        return false;
    }

    private void addVariablesDeclarations(Scope scope, ArrayList<ArrayList<String>> arrayLists, TypedValue.Type type) throws Exception {

        if (arrayLists.size() == 0) {
            throw new SyntaxException("No tokens");
        }

        int index = 0;

        ArrayList<String> finalGroup = arrayLists.get(index);
        boolean isFinal = Objects.equals(finalGroup.get(1), "final");

        if (isFinal) {
            index++;
        }

        ArrayList<String> typeGroup = arrayLists.get(index++);
        typeGroup.get(1);

        for (; index < arrayLists.size(); index++) {

            ArrayList<String> partialDeclarationGroup = arrayLists.get(index);
            String name = partialDeclarationGroup.get(1);

            Variable variable = new Variable(name, type);

            variable.addAssignment(
                    getInitializer(scope, partialDeclarationGroup, type));

            if (isFinal && !variable.isInitialized) {
                throw new SyntaxException("Final declarations must be initialized");
            }

            scope.addVariable(variable);
        }
    }

    private TypedValue getInitializer(Scope scope, ArrayList<String> tokens, TypedValue.Type expectedType) throws Exception {
        if (tokens.size() <= 2) { // no assignment
            return null;
        }

        String rightSide = tokens.get(3);

        if (lexicalAnalyzer.isTypeMatch(expectedType, rightSide)) {
            return new TypedValue(expectedType);
        }

        if (!lexicalAnalyzer.isNameOfVariable(rightSide)) {
            throw new Exception("Invalid type or variable name");
        }

        Variable variable = scope.findVariable(rightSide, expectedType);
        if (variable == null) {
            throw new Exception("Could not find variable " + rightSide + " in scope");
        }

        if (variable.type != expectedType) {
            throw new Exception("Left and right sides of an assignment have different types");
        }

        if (!variable.isInitialized) {
            throw new Exception("Use of uninitialized variable " + rightSide + " in expression");
        }

        return variable;
    }
}
