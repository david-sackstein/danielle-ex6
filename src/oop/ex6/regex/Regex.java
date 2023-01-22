package oop.ex6.regex;

import oop.ex6.patterns.*;

import java.util.regex.Pattern;

/**
 * Patterns provides all the compiled regex patterns required by the Tokenizer
 * The compilation of each regex is performed only once throughout the program
 */
public class Regex {
    public static final Pattern EMPTY_LINE = Pattern.compile(Constants.EMPTY_LINE);
    public static final Pattern COMMENT_LINE = Pattern.compile(Constants.COMMENT_LINE);
    public static final Pattern RETURN_STATEMENT = Pattern.compile(Constants.RETURN_STATEMENT);
    public static final Pattern END_OF_BLOCK = Pattern.compile(Constants.END_OF_BLOCK);
    public static final Pattern NAME_OF_VARIABLE = Pattern.compile(Constants.NAME_OF_VARIABLE);

}