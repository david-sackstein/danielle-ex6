package oop.ex6.regex;

import oop.ex6.strings.*;

import java.util.regex.Pattern;
/**
 * this class contains compiled regular expression patterns for checking various blocks expressions
 * in the program. The blocks and expressions include method invocations and condition statements.
 * It also contains a regular expression for an argument.
 */

public class RegexBlocks {
    public static final Pattern ARGUMENT_VALUE = Pattern.compile(MethodString.ARGUMENT_VALUE);
    public static final Pattern METHOD_INVOCATION = Pattern.compile(MethodString.CALL);

    public static final Pattern CONDITION_BLOCK = Pattern.compile(Condition.BLOCK);
    public static final Pattern CONDITION_EXPRESSION = Pattern.compile(Condition.EXPRESSION);
}
