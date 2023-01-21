package oop.ex6.SyntaxAnalysis;

import oop.ex6.AbstractSyntaxTree.*;
import oop.ex6.SyntaxAnalysis.*;
import oop.ex6.main.LinesReader;

import java.util.ArrayList;

/**
 * The Compiler reads the javas text, and runs each each line through the SyntaxAnalyzer.
 * If no exception was thrown the compilation is considered to have succeeded.
 */
public class Compiler {

    /**
     * Creates the global scope and parses each line in the specified file in that context using a SyntaxAnalyzer.
     * @param fileName
     * @throws Exception
     */
    public void compile(String fileName) throws Exception {
        ArrayList<String> lines  = new LinesReader().getLines(fileName);

        SyntaxAnalyzer parser = new SyntaxAnalyzer();

        GlobalScope globalScope = new GlobalScope();
        Scope scope = globalScope;

        for(String line : lines) {
            scope = parser.parse(line, scope);
        }
        globalScope.assertNoUnresolvedNames();
    }
}

