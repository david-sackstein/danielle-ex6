package oop.ex6.main;

import oop.ex6.AbstractSyntaxTree.*;
import oop.ex6.SyntaxAnalysis.*;

import java.util.*;

public class Compiler {

    public void compile(String fileName) throws Exception {

        LinesReader linesReader = new LinesReader();
        List<String> lines  = linesReader.getLines(fileName);

        LanguageParser parser = new LanguageParser();

        GlobalScope globalScope = new GlobalScope();
        Scope scope = globalScope;

        for(String line : lines) {
            scope = parser.parse(line, scope);
        }

        globalScope.assertNoUnresolvedVariables();
    }
}

