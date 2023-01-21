package oop.ex6.AbstractSyntaxTree;

import java.util.ArrayList;

public class MethodInvocation extends Expression {

    public String methodName;
    public ArrayList<Variable> arguments;
}
