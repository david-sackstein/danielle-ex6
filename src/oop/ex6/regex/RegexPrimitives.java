package oop.ex6.regex;

import oop.ex6.strings.*;

import java.util.regex.Pattern;

/**
 * this class contains compiled regular expressions used to match primitives patterns.
 */
 public class RegexPrimitives {
    public static final Pattern EMPTY_LINE = Pattern.compile(Primitives.EMPTY_LINE);
    public static final Pattern COMMENT_LINE = Pattern.compile(Primitives.COMMENT_LINE);
    public static final Pattern RETURN_STATEMENT = Pattern.compile(Primitives.RETURN_STATEMENT);
    public static final Pattern END_OF_BLOCK = Pattern.compile(Primitives.END_OF_BLOCK);
    public static final Pattern NAME_OF_VARIABLE = Pattern.compile(Primitives.NAME_OF_VARIABLE);
}