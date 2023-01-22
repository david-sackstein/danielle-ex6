package oop.ex6.main;

import oop.ex6.SyntaxAnalysis.Compiler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Sjavac {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        try {
            String fileName = args[0];

            LinesReader linesReader = new LinesReader();
            ArrayList<String> lines = linesReader.getLines(fileName);

            for (int i = 1; i < 205; i++) {
                RunLineTest(lines.get(i - 1));
            }
            System.out.println("PASSED");

        } catch (Exception ex) {
            System.out.printf("FAILED : %s\n", ex.getMessage());
        }
    }

    /**
     * @param line
     * @throws Exception
     */
    private static void RunLineTest(String line) throws Exception {
        String[] tokens = line.split(" ");
        if (tokens.length < 2) {
            return;
        }
        String testFileName = tokens[0];
        String result = tokens[1];

        String actual = RunTest(testFileName);

        if (!Objects.equals(actual, result)) {
            throw new Exception(testFileName);
        }
    }

    /**
     * @param testFileName
     * @return
     */
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
