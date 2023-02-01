package oop.ex6.syntax;
import oop.ex6.scope.*;
import oop.ex6.fileReader.FileToLines;
import java.util.ArrayList;

/**
 * The Compiler reads the javas text, and runs each line through the SyntaxAnalyzer.
 * If no exception was thrown the compilation is considered to have succeeded.
 */
public class Compiler {

    /**
     * Creates the global scope and parses each line in the specified file in that
     * context using a SyntaxAnalyzer.
     * @param fileName
     * @throws Exception
     */
    public void compile(String fileName) throws Exception {
        ArrayList<String> lines  = new FileToLines().createNonEmptyLineArray(fileName);

        SyntaxParser parser = new SyntaxParser();
        GlobalScope globalScope = new GlobalScope();
        Scope scope = globalScope;

        for(int i=0; i< lines.size(); i++) {
            String line = lines.get(i);
            String nextLine = (i == lines.size() - 1)? null: lines.get(i + 1);
            scope = parser.parseIntoScope(line, nextLine, scope);
        }
        globalScope.checkForUnresolvedNames();
    }
}

