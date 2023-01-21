package oop.ex6.main;

import oop.ex6.SyntaxAnalysis.*;

import java.io.IOException;
import java.util.List;

public class Sjavac {

    public static void main(String[] args) {

        try {
            runFileTests(args[0]);
            System.out.print("PASSED");
        } catch (Exception ex) {
            System.out.printf("FAILED : %s\n", ex.getMessage());
        }
    }

    private static void runFileTests(String fileName) throws Exception {
        LinesReader linesReader = new LinesReader();

        List<String> lines = linesReader.getLines(fileName);
        for (int i = 1; i < 200; i++) {
            runLineTest(lines.get(i-1));
        }
        System.out.println("Failed: " + failedCount);
    }

    static int failedCount = 0;

    private static void runLineTest(String line) throws Exception {
        String[] tokens = line.split(" ");
        if (tokens.length < 2){
            return;
        }
        String testFileName = tokens[0];
        String expectedResult = tokens[1];

        String actualResult = runTest(testFileName);

        if (!actualResult.equals(expectedResult)) {
            failedCount++;
            //throw new Exception(testFileName);
        }
    }

    private static String runTest(String testFileName) {
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
