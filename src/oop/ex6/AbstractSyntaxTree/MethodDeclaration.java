package oop.ex6.AbstractSyntaxTree;

import java.util.ArrayList;

public class MethodDeclaration extends Expression {

    public MethodDeclaration() {
        arguments = new ArrayList<Variable>();
    }

    public String name;
    public ArrayList<Variable> arguments;

    public void addVariable(Variable variable) throws Exception {

        for(Variable v : arguments) {
            if (v.name.equals(variable.name)) {
                throw new Exception("Argument name already exists");
            }
        }

        arguments.add(variable);
    }
}
