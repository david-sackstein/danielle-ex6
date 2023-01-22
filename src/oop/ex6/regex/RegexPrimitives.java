package oop.ex6.regex;

import oop.ex6.patterns.*;

import java.util.regex.Pattern;

/**
 * Patterns provides all the compiled regex patterns required by the Tokenizer
 * The compilation of each regex is performed only once throughout the program
 */
public class RegexPrimitives {
    public static final Pattern EMPTY_LINE = Pattern.compile(Primitives.EMPTY_LINE);
    public static final Pattern COMMENT_LINE = Pattern.compile(Primitives.COMMENT_LINE);
    public static final Pattern RETURN_STATEMENT = Pattern.compile(Primitives.RETURN_STATEMENT);
    public static final Pattern END_OF_BLOCK = Pattern.compile(Primitives.END_OF_BLOCK);
    public static final Pattern NAME_OF_VARIABLE = Pattern.compile(Primitives.NAME_OF_VARIABLE);
}