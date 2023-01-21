package oop.ex6.AbstractSyntaxTree;

public abstract class Scope {
    Scope parent;
    Scope(Scope parent) {
        this.parent = parent;
    }

    public abstract void addScope(Scope scope);
    public abstract void addVariable(Variable variable) throws Exception;
    public abstract Variable findVariable(String name, TypedValue.Type type);
    public abstract void youHaveReturned() throws Exception;
    public abstract Scope yourScopeEnded() throws Exception;
}
