package oop.ex6.AbstractSyntaxTree;

public class ConditionalBlock extends Scope {

    public enum Type
    {
        WhileLoop,
        IfBlock
    }
    public ConditionalBlock(Scope parent) throws LanguageException {
        super(parent);
        throw new LanguageException("");
    }

    @Override
    public void addScope(Scope scope) {

    }

    @Override
    public void addVariable(Variable variable) {

    }

    @Override
    public Variable findVariable(String name, TypedValue.Type type) {
        return null;
    }

    @Override
    public void youHaveReturned() {
        hasReturned = true;
    }

    @Override
    public Scope yourScopeEnded() throws Exception {
        if (! hasReturned) {
            throw new Exception("Missing return statement");
        }
        return parent.yourScopeEnded();
    }

    public Type type;
    public ConditionalExpression conditionalExpression;

    private boolean hasReturned;
}
