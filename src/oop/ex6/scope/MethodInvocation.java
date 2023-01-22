package oop.ex6.scope;

import java.util.ArrayList;

/**
 * Represents an invocation of a method
 * An invocation contains a methodName and a list of arguments.
 */
public class MethodInvocation {

    public String methodName;
    public ArrayList<TypedValue> arguments;

    public MethodInvocation() {
        arguments = new ArrayList<TypedValue>();
    }
}
