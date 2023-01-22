package oop.ex6.scope;

/**
 *
 */
public class ConditionalScope extends BlockScope {

    private final BlockType blockType;

    public enum BlockType {
        WhileLoop,
        IfBlock
    }

    /**
     * @param parent
     * @param blockType
     */
    public ConditionalScope(Scope parent, BlockType blockType) {
        super(parent);
        this.blockType = blockType;
    }

    /**
     * @return
     * @throws Exception
     */
    @Override
    public Scope yourScopeEnded() throws Exception {

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
        return findVariable(name, type, localVariables);
    }
}
