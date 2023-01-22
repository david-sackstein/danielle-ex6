package oop.ex6.SyntaxAnalysis;

import oop.ex6.AbstractSyntaxTree.*;
import oop.ex6.Tokenizer.Tokenizer;

import java.util.ArrayList;
import java.util.Objects;

/**
 * SyntaxAnalyzer uses a LexicalAnalyzer to break javaS text into tokens and
 * creates language elements from those tokens.
 */
public class SyntaxAnalyzer {

    private Tokenizer tokenizer;

    public SyntaxAnalyzer() {
        tokenizer = new Tokenizer();
    }

    /**
     * Accepts a line of text and interprets it within the context of the current scope or throws an exception if
     * the line was invalid, either at the textual level or at the level of the language syntax.
     * parse checks the possibility that the line may match any of all allowed syntactical structures.
     * @param line
     * @param scope
     * @return returns the scope that is active after the line is read. For instance, if the line is method invocation
     * or a variable declaration, the scope is the same scope provided as an argument. However if the line represents
     * a method declaration or the start of a conditional block, then the method or conditional block respectively
     * are returned as they themselves represent new scopes.
     * @throws Exception
     */
    public Scope parse(String line, Scope scope) throws Exception {

        if (tokenizer.isCommentOrEmptyLine(line)) {
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

        if (doMethodInvocation(line, scope)){
            return scope;
        }

        if (tokenizer.isReturnStatement(line)) {
            scope.youAreReturning();
            return scope;
        }

        if (tokenizer.isEndOfBlock(line)) {
            return scope.yourScopeEnded();
        }

        Scope conditionalBlock = createConditionalBlock (line, scope);
        if (conditionalBlock != null){
            return conditionalBlock;
        }

        tokenizer.splitMethodInvocation(line);

        throw new Exception("Unrecognized syntax");
    }

    /**
     * @param line
     * @param scope
     * @return
     * @throws Exception
     */
    private MethodScope createMethod(String line, Scope scope) throws Exception {
        ArrayList<ArrayList<String>> arrayLists = tokenizer.splitMethodDeclaration(line);
        if (arrayLists == null) {
            return null;
        }
        MethodDeclaration methodDeclaration = getMethodDeclaration(arrayLists);
        MethodScope method = new MethodScope(scope);
        method.methodDeclaration = methodDeclaration;

        scope.addMethod(method);

        return method;
    }

    /**
     * @param line
     * @param scope
     * @return
     * @throws Exception
     */
    private boolean doMethodInvocation(String line, Scope scope) throws Exception {
        ArrayList<ArrayList<String>> arrayLists = tokenizer.splitMethodInvocation(line);
        if (arrayLists == null) {
            return false;
        }
        MethodInvocation invocation = getMethodInvocation(scope, arrayLists);
        scope.addInvocation(invocation);
        return true;
    }

    /**
     * @param line
     * @param scope
     * @return
     * @throws Exception
     */
    private ConditionalScope createConditionalBlock(String line, Scope scope) throws Exception {
        ArrayList<ArrayList<String>> arrayLists = tokenizer.splitBlockCondition(line);
        if (arrayLists == null) {
            return null;
        }
        return getConditionalBlock(arrayLists, scope);
    }

    /**
     * @param arrayLists
     * @param scope
     * @return
     * @throws Exception
     */
    private ConditionalScope getConditionalBlock(ArrayList<ArrayList<String>> arrayLists, Scope scope) throws Exception {

        ConditionalScope.BlockType blockType = getBlockType(arrayLists.get(0).get(0));

        ConditionalScope conditionalScope = new ConditionalScope(scope, blockType);

        for (int i = 1; i < arrayLists.size(); i++) {

            String conditionExpression = arrayLists.get(i).get(1);

            if (tokenizer.isTypeMatch(TypedValue.Type.Boolean, conditionExpression) ||
                    tokenizer.isTypeMatch(TypedValue.Type.Int, conditionExpression) ||
                    tokenizer.isTypeMatch(TypedValue.Type.Double, conditionExpression)) {
                continue;
            }

            if (!tokenizer.isNameOfVariable(conditionExpression)) {
                throw new Exception("The regex failed");
            }

            Variable variable = getVariableInitializer(
                    scope,
                    conditionExpression,
                    TypedValue.Type.Boolean, TypedValue.Type.Int, TypedValue.Type.Double);
        }

        return conditionalScope;
    }

    private ConditionalScope.BlockType getBlockType(String blockTypeString) {
        return blockTypeString.trim().equals("if") ? ConditionalScope.BlockType.IfBlock : ConditionalScope.BlockType.WhileLoop;
    }

    /**
     * @param scope
     * @param arrayLists
     * @return
     * @throws Exception
     */
    private MethodInvocation getMethodInvocation(Scope scope, ArrayList<ArrayList<String>> arrayLists) throws Exception {

        MethodInvocation invocation = new MethodInvocation();
        invocation.methodName = arrayLists.get(0).get(0);

        for (int i = 1; i < arrayLists.size(); i++) {

            String argumentToken = arrayLists.get(i).get(1);
            TypedValue typedValue = createTypedValueArgument(scope, argumentToken);
            invocation.arguments.add(typedValue);
        }
        return invocation;
    }

    /**
     * @param scope
     * @param argumentToken
     * @return
     * @throws Exception
     */
    private TypedValue createTypedValueArgument(Scope scope, String argumentToken) throws Exception {
        if (tokenizer.isTypeMatch(TypedValue.Type.Int, argumentToken)){
            return new TypedValue(TypedValue.Type.Int);
        }
        if (tokenizer.isTypeMatch(TypedValue.Type.Double, argumentToken)){
            return new TypedValue(TypedValue.Type.Double);
        }
        if (tokenizer.isTypeMatch(TypedValue.Type.String, argumentToken)){
            return new TypedValue(TypedValue.Type.String);
        }
        if (tokenizer.isTypeMatch(TypedValue.Type.Char, argumentToken)){
            return new TypedValue(TypedValue.Type.Char);
        }
        if (tokenizer.isTypeMatch(TypedValue.Type.Boolean, argumentToken)){
            return new TypedValue(TypedValue.Type.Boolean);
        }
        Variable variable = scope.findVariable(argumentToken, TypedValue.Type.Any);
        if (! variable.isInitialized()) {
            throw new Exception("Uninitialized arguments passed to method");
        }
        return variable;
    }

    /**
     * @param arrayLists
     * @return
     * @throws Exception
     */
    private MethodDeclaration getMethodDeclaration(ArrayList<ArrayList<String>> arrayLists) throws Exception {

        MethodDeclaration methodDeclaration = new MethodDeclaration();

        methodDeclaration.methodName = arrayLists.get(0).get(0);

        for (int i = 1; i < arrayLists.size(); i++) {

            ArrayList<String> argumentTokens = arrayLists.get(i);

            Variable variable = getVariable(argumentTokens);

            methodDeclaration.addVariable(variable);
        }

        return methodDeclaration;
    }

    /**
     * @param argumentTokens
     * @return
     * @throws Exception
     */
    private Variable getVariable(ArrayList<String> argumentTokens) throws Exception {

        boolean isFinal = argumentTokens.get(1).equals("final");
        int start = isFinal ? 1 : 0;

        String nameString = argumentTokens.get(start + 2);
        String typeString = argumentTokens.get(start + 1);

        TypedValue.Type type = tokenizer.getTypeFromString(typeString);
        Variable variable = new Variable(nameString, type);
        variable.isFinal = isFinal;
        variable.addInitializer(new TypedValue(type)); // because arguments passed to a method must have been initialized

        return variable;
    }

    /**
     * @param line
     * @param scope
     * @return
     * @throws Exception
     */
    private boolean doAssignments(String line, Scope scope) throws Exception {
        ArrayList<ArrayList<String>> arrayLists = tokenizer.splitAssignmentInt(line);
        if (arrayLists != null) {
            doAssignments(scope, arrayLists, TypedValue.Type.Int);
            return true;
        }
        arrayLists = tokenizer.splitAssignmentString(line);
        if (arrayLists != null) {
            doAssignments(scope, arrayLists, TypedValue.Type.String);
            return true;
        }
        arrayLists = tokenizer.splitAssignmentDouble(line);
        if (arrayLists != null) {
            doAssignments(scope, arrayLists, TypedValue.Type.Double);
            return true;
        }
        arrayLists = tokenizer.splitAssignmentBoolean(line);
        if (arrayLists != null) {
            doAssignments(scope, arrayLists, TypedValue.Type.Boolean);
            return true;
        }
        arrayLists = tokenizer.splitAssignmentChar(line);
        if (arrayLists != null) {
            doAssignments(scope, arrayLists, TypedValue.Type.Char);
            return true;
        }
        return false;
    }

    /**
     * @param scope
     * @param arrayLists
     * @param type
     * @throws Exception
     */
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
            if (variable.isFinal) {
                throw new Exception("Cannot assign a value to a final variable");
            }
            scope.addAssignment(variable, getInitializer(scope, tokens, type));
        }
    }

    /**
     * @param line
     * @param scope
     * @return
     * @throws Exception
     */
    private boolean addVariablesDeclarations(String line, Scope scope) throws Exception {

        ArrayList<ArrayList<String>> tokens = tokenizer.splitDeclarationInt(line);
        if (tokens != null) {
            addVariablesDeclarations(scope, tokens, TypedValue.Type.Int);
            return true;
        }
        tokens = tokenizer.splitDeclarationString(line);
        if (tokens != null) {
            addVariablesDeclarations(scope, tokens, TypedValue.Type.String);
            return true;
        }
        tokens = tokenizer.splitDeclarationDouble(line);
        if (tokens != null) {
            addVariablesDeclarations(scope, tokens, TypedValue.Type.Double);
            return true;
        }
        tokens = tokenizer.splitDeclarationBoolean(line);
        if (tokens != null) {
            addVariablesDeclarations(scope, tokens, TypedValue.Type.Boolean);
            return true;
        }
        tokens = tokenizer.splitDeclarationChar(line);
        if (tokens != null) {
            addVariablesDeclarations(scope, tokens, TypedValue.Type.Char);
            return true;
        }
        return false;
    }

    /**
     * @param scope
     * @param arrayLists
     * @param type
     * @throws Exception
     */
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
            variable.isFinal = isFinal;

            variable.addInitializer(
                    getInitializer(scope, partialDeclarationGroup, type));

            if (isFinal && !variable.isInitialized()) {
                throw new SyntaxException("Final declarations must be initialized");
            }

            scope.addVariable(variable);
        }
    }

    /**
     * @param scope
     * @param tokens
     * @param expectedType
     * @return
     * @throws Exception
     */
    private TypedValue getInitializer(Scope scope, ArrayList<String> tokens, TypedValue.Type expectedType) throws Exception {
        if (tokens.size() <= 2) { // no assignment
            return null;
        }

        String rightSide = tokens.get(3);

        if (tokenizer.isTypeMatch(expectedType, rightSide)) {
            return new TypedValue(expectedType);
        }

        if (!tokenizer.isNameOfVariable(rightSide)) {
            throw new Exception("Invalid type or variable methodName");
        }

        Variable variable = getVariableInitializer(scope, rightSide, expectedType);

        return variable;
    }

    /**
     * @param scope
     * @param variableName
     * @param typeArray
     * @return
     * @throws Exception
     */
    private Variable getVariableInitializer(Scope scope, String variableName, TypedValue.Type... typeArray) throws Exception {
        for(TypedValue.Type type : typeArray) {
            Variable variable = scope.findVariable(variableName, type);
            if (variable == null) {
                continue;
            }

            if (variable.type != type) {
                throw new Exception("Left and right sides of an assignment have different types");
            }

            if (!variable.isInitialized()) {
                throw new Exception("Use of uninitialized variable " + variableName + " in expression");
            }
            return variable;
        }
        throw new Exception("Could not find variable " + variableName + " in scope");
    }
}
