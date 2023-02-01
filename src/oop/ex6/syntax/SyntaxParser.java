package oop.ex6.syntax;
import oop.ex6.scope.*;
import oop.ex6.scope.ifOrWhile.IfWhileScope;
import oop.ex6.scope.method.Method;
import oop.ex6.scope.method.MethodCall;
import oop.ex6.scope.method.MethodSignature;
import oop.ex6.tokenizer.Tokenizer;
import oop.ex6.variableDeclaration.Type;
import oop.ex6.variableDeclaration.Value;
import oop.ex6.variableDeclaration.Variable;
import java.util.ArrayList;

import static oop.ex6.exceptions.ExceptionMessages.*;

/**
 * breaks javaS text into Strings, and creates Variables/Methods etc. out of them.
 */
public class SyntaxParser {

    private final ArrayList<MethodSignature> methodSignatures;
    private final ArrayList<MethodCall> methodCalls;

    public SyntaxParser() {
        this.methodSignatures = new ArrayList<>();
        this.methodCalls = new ArrayList<>();
    }



    /**
     checks if all method calls in the list of methodCalls have corresponding declarations in
     the list of methodSignatures.
     @return a boolean indicating whether all method calls have declarations (true) or not (false)
     */
    private boolean allCallsHaveDeclerations(){
        for (MethodCall call : methodCalls){
            if (!callHasDeclaration(call)){
                return false;
            }
        }
        return true;
    }



    /**
     checks if a single method call has a corresponding declaration in the list of methodSignatures.
     @param call the MethodCall to be checked
     @return a boolean indicating whether the method call has a declaration (true) or not (false)
     */
    private boolean callHasDeclaration(MethodCall call){
        for (MethodSignature methodSignature : methodSignatures){
            if (call.isCallOf(methodSignature)){
                return true;
            }
        }
        return false;
    }



    /** finds the relevant scope after reading the given line.
     * @param line the given line.
     * @param currentScope the scope the line is part of.
     * @return the relevant scope after the line is read.
     * @throws Exception if something was illegal.
     */
    public Scope parseIntoScope(String line, String nextLine, Scope currentScope)
            throws Exception {

        if (nextLine == null){ //got to end of file
            Scope.throwExceptionByCondition(()->!allCallsHaveDeclerations(), NO_METHOD_MATCHING_TO_CALL);
        }

        if (Tokenizer.isCommentOrEmptyLine(line)) {
            return currentScope;
        }

        if (Tokenizer.isReturnStatement(line)) {
            currentScope.returnInScope();
            Scope.throwExceptionByCondition(()->!Tokenizer.isEndOfBlock(nextLine), RETURN_WITH_NO_END);
            return currentScope;
        }

        if (Tokenizer.isEndOfBlock(line)) {
            return currentScope.endScope();
        }

        if (saveVariablesToScope(line, currentScope)) {
            return currentScope;
        }

        if (assignVariable(line, currentScope)) {
            return currentScope;
        }

        if (isLineLegalMethodCall(line)) {
            MethodCall call = MethodCall.runMethodCall(line, currentScope);
            methodCalls.add(call);
            return currentScope;
        }

        Scope conditionalBlock = IfWhileScope.createIfWhileScope(line, currentScope);
        if (conditionalBlock != null) {
            return conditionalBlock;
        }

        Method method = Method.lineToMethodScope(line, currentScope);
        if (method != null) {
            methodSignatures.add(method.getMethodSignature());
            return method;
        }

        Tokenizer.splitMethodCall(line);
        Scope.throwExceptionByCondition(()->true, ILLEGAL_SYNTAX);
        return null;
    }



    private boolean isLineLegalMethodCall(String line){
        ArrayList<ArrayList<String>> splited = Tokenizer.splitMethodCall(line);
        return (splited != null);
    }


    /**
     This method handles the assignments of different variable types
     (int, double, char, string, boolean).
     @param line the line of code to be parsed
     @param scope the scope in which the variable is to be saved
     @return true if an assignment was done, false otherwise
     @throws Exception if there was an error in the assignment
     (e.g. invalid variable type, final variable being reassigned, etc.)
     */
    private boolean assignVariable(String line, Scope scope) throws Exception {
        ArrayList<ArrayList<String>> arrayLists = Tokenizer.splitAssignmentInt(line);
        if (arrayLists != null) {
            assignTypedVariable(scope, arrayLists, Type.Int);
            return true;
        }
        arrayLists = Tokenizer.splitAssignmentString(line);
        if (arrayLists != null) {
            assignTypedVariable(scope, arrayLists, Type.String);
            return true;
        }
        arrayLists = Tokenizer.splitAssignmentDouble(line);
        if (arrayLists != null) {
            assignTypedVariable(scope, arrayLists, Type.Double);
            return true;
        }
        arrayLists = Tokenizer.splitAssignmentBoolean(line);
        if (arrayLists != null) {
            assignTypedVariable(scope, arrayLists, Type.Boolean);
            return true;
        }
        arrayLists = Tokenizer.splitAssignmentChar(line);
        if (arrayLists != null) {
            assignTypedVariable(scope, arrayLists, Type.Char);
            return true;
        }
        return false;
    }



    /**
     This method performs the assignment of a variable in a given scope.
     @param scope the scope in which the variable is to be assigned
     @param splited an array of tokens representing the assignment statement
     @param type the type of the variable being assigned
     @throws Exception if there was an error in the assignment (e.g. invalid variable type, final variable being reassigned, etc.)
     */
    private void assignTypedVariable(Scope scope, ArrayList<ArrayList<String>> splited, Type type)
            throws Exception{
        for (ArrayList<String> part : splited) {
            String name = part.get(1);
            Variable variable = scope.searchVariableInScope(name, type);

            // throw exceptions
            Scope.throwExceptionByCondition(()->(variable == null),
                    varUnfound(name));
            Scope.throwExceptionByCondition(()->(variable.getType() != type),
                    assignmentOfWrongTypes(type, variable.getType()));
            Scope.throwExceptionByCondition(variable::getFinalIndicator,
                    FINAL_VARIABLE_ASSIGNMENT);

            scope.assignmentInScope(variable, Value.intialize(scope, part, type));
        }
    }

//    private boolean allCallsHaveDeclerations(){
//        for (MethodCall call : methodCalls){
//
//        }
//    }



    /**
     This method saves variables of different types
     (int, double, char, string, boolean) to the specified scope.
     @param line the line of code to be parsed
     @param scope the scope in which the variable is to be saved
     @return true if the variable was saved, false otherwise
     @throws Exception if there was an error in saving the variable
     (e.g. invalid variable type, variable already exists
     */
    private boolean saveVariablesToScope(String line, Scope scope) throws Exception {

        ArrayList<ArrayList<String>> splited = Tokenizer.splitDeclarationString(line);
        if (splited != null) {
            saveTypedVariablesToScope(scope, splited, Type.String);
            return true;
        }
        splited = Tokenizer.splitDeclarationChar(line);
        if (splited != null) {
            saveTypedVariablesToScope(scope, splited, Type.Char);
            return true;
        }
        splited = Tokenizer.splitDeclarationDouble(line);
        if (splited != null) {
            saveTypedVariablesToScope(scope, splited, Type.Double);
            return true;
        }
        splited = Tokenizer.splitDeclarationBoolean(line);
        if (splited != null) {
            saveTypedVariablesToScope(scope, splited, Type.Boolean);
            return true;
        }
        splited = Tokenizer.splitDeclarationInt(line);
        if (splited != null) {
            saveTypedVariablesToScope(scope, splited, Type.Int);
            return true;
        }
        return false;
    }



    /**
     Adds variables declarations to the scope.
     @param scope The scope in which the variables are being declared.
     @param splited A list of lists of strings split from the input source code.
     @param type The type of the variables being declared.
     @throws Exception if the input array is empty or if a final variable is not initialized.
     */
    private void saveTypedVariablesToScope(
            Scope scope, ArrayList<ArrayList<String>> splited, Type type) throws Exception {

        Scope.throwExceptionByCondition(splited::isEmpty, EMPTY_ARRAY);

        // has final statement
        boolean hasFinalStatement = splited.get(0).get(1).equals("final");
        if (hasFinalStatement) {
            splited.remove(0);
        }
        splited.remove(0);
        for (ArrayList<String> partString : splited){
            String name = partString.get(1);

            Variable variable = new Variable(name, type);
            variable.setFinalIndicator(hasFinalStatement);
            variable.initializeVariableWithValue(
                Value.intialize(scope, partString, type));

            Scope.throwExceptionByCondition(
                    ()->(hasFinalStatement && variable.notYetInitialized()), UNINITIALIZED_FINAL);

            scope.variableInScope(variable);
        }
    }
}
