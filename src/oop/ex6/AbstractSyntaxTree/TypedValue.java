package oop.ex6.AbstractSyntaxTree;

/**
 *  Represents a literal (Char, Double, Int, String, Boolean) or a Variable
 */
public class TypedValue {

    public enum Type {
        Char,
        Double,
        Int,
        String,
        Boolean,
        // Used as a wildcard when matching variables or to represent the type of a variable that appears
        // in a method invocation, when the method declaration has not been resolved
        Any
    }

    public TypedValue(Type type) {
        this.type = type;
    }
    public Type type;

    public String toString(){
        return String.format("type = %s", type);
    }

    /**
     * isAssignable returns true if and only if an TypedValue with the rhs type can be assigned to a
     * TypedValue of the lhs type
     * @param lhs
     * @param rhs
     * @return
     */
    public static boolean isAssignable(Type lhs, Type rhs) {
        if (lhs == rhs) {
            return true;
        }
        if (lhs == TypedValue.Type.Any || rhs == TypedValue.Type.Any) {
            return true;
        }
        if (lhs == TypedValue.Type.Boolean) {
            if (rhs == TypedValue.Type.Double || rhs == TypedValue.Type.Boolean || rhs == TypedValue.Type.Int) {
                return true;
            }
        }
        if (lhs == TypedValue.Type.Double) {
            if (rhs == TypedValue.Type.Double || rhs == TypedValue.Type.Int) {
                return true;
            }
        }
        return false;
    }
}
