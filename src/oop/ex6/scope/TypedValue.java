package oop.ex6.scope;

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
        Any
    }

    public TypedValue(Type type) {
        this.type = type;
    }
    public Type type;

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
        if (lhs == Type.Int && rhs == Type.Char) {
            return true;
        }
        if (lhs == Type.Any || rhs == Type.Any) {
            return true;
        }
        if (lhs == TypedValue.Type.Boolean) {
            if (rhs == Type.Double || rhs == Type.Int) {
                return true;
            }
        }
        if (lhs == TypedValue.Type.Double) {
            return rhs == Type.Int || rhs == Type.Char;
        }
        return false;
    }
}
