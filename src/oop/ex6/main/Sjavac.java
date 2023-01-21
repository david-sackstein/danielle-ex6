package oop.ex6.main;

import oop.ex6.SyntaxAnalysis.*;

import java.io.IOException;
import java.util.List;

public class Sjavac {

    public static void main(String[] args) {
        LexicalAnalyzer.Test();
        try {
            String fileName = args[0];

            LinesReader linesReader = new LinesReader();
            List<String> lines = linesReader.getLines(fileName);
            for (int i = 1; i < 200; i++) {
                RunLineTest(lines.get(i-1));
            }
            System.out.printf("PASSED");
        } catch (Exception ex) {
            System.out.printf("FAILED : %s\n", ex.getMessage());
        }
    }

    private static void RunLineTest(String line) throws Exception {
        String[] tokens = line.split(" ");
        if (tokens.length < 2){
            return;
        }
        String testFileName = tokens[0];
        String expectedResult = tokens[1];

        String actualResult = RunTest(testFileName);

        if (!actualResult.equals(expectedResult)) {
            throw new Exception(testFileName);
        }
    }

    private static String RunTest(String testFileName) {
        try {
            new Compiler().compile(testFileName);
            return "0";
        } catch (IOException ex) {
            return "2";
        } catch (Exception ex) {
            return "1";
        }
    }
}
