package oop.ex6.scope;

import java.util.ArrayList;

/**
 *
 */
public class MethodScope extends BlockScope {

    public MethodDeclaration methodDeclaration;

    public MethodScope(Scope parent) {
        super(parent);
    }

    /**
     * @return
     * @throws Exception
     */
    @Override
    public Scope endOfScope() throws Exception {
        if (!hasReturned) {
            throw new Exception("Missing return statement");
        }
        removeLocalAssignments();

        return parent;
    }

    /**
     * @param name
     * @param type
     * @return
     * @throws Exception
     */
    @Override
    Variable findLocalVariable(String name, TypedValue.Type type) throws Exception {
        Variable v = findVariable(name, type, methodDeclaration.arguments);
        if (v != null) {
            return v;
        }

        v = findVariable(name, type, localVariables);
        if (v != null) {
            return v;
        }

        return null;
    }

    public String methodName() {
        return methodDeclaration.getMethodName();
    }

    /**
     * @param invocation
     * @return
     */
    public boolean isInvocationOf(MethodInvocation invocation) {
        if (!invocation.methodName.equals(methodName())) {
            return false;
        }

        ArrayList<TypedValue> invocationArguments = invocation.arguments;
        ArrayList<Variable> declarationArguments = methodDeclaration.arguments;

        if (invocationArguments.size() != declarationArguments.size()) {
            return false;
        }

        for (int i = 0; i < invocationArguments.size(); i++) {

            TypedValue.Type invokedType = invocationArguments.get(i).type;
            TypedValue.Type declaredType = declarationArguments.get(i).type;

            if (invokedType != TypedValue.Type.Any) {
                // literal or resolved variable
                if (! TypedValue.isAssignable(declaredType, invokedType)) {
                    return false;
                }
            } else {
                // a variable which is still unresolved (in global scope)
                Variable variable = (Variable) (invocationArguments.get(i));
                variable.type = declaredType;
            }
        }
        return true;
    }
}
