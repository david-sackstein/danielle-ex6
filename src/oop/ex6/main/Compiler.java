package oop.ex6.main;

import oop.ex6.AbstractSyntaxTree.*;
import oop.ex6.SyntaxAnalysis.*;

import java.util.ArrayList;

public class Compiler {

    public void compile(String fileName) throws Exception {
        ArrayList<String> lines  = new LinesReader().getLines(fileName);

        SyntaxAnalyzer parser = new SyntaxAnalyzer();

        GlobalScope globalScope = new GlobalScope();
        Scope scope = globalScope;

        for(String line : lines) {
            scope = parser.parse(line, scope);
        }

        globalScope.assertNoUnresolvedVariables();
    }
}

