package oop.ex6.regex;

import oop.ex6.patterns.*;

import java.util.regex.Pattern;

public class RegexBlocks {
    public static final Pattern ARGUMENT_VALUE = Pattern.compile(Method.ARGUMENT_VALUE);
    public static final Pattern METHOD_INVOCATION = Pattern.compile(Method.CALL);

    public static final Pattern CONDITION_BLOCK = Pattern.compile(Condition.BLOCK);
    public static final Pattern CONDITION_EXPRESSION = Pattern.compile(Condition.EXPRESSION);
}
