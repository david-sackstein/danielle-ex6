package oop.ex6.main;

import oop.ex6.LexicalAnalysis.LexicalException;
import oop.ex6.SyntaxAnalysis.Compiler;
import oop.ex6.SyntaxAnalysis.SyntaxException;

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
            System.out.printf("PASSED");

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
            return returnZero();
        } catch (IOException ex) {
            return returnTwo(ex);
        } catch (SyntaxException ex) {
            return returnOne(ex);
        } catch (LexicalException ex) {
            return returnOne(ex);
        } catch (Exception ex) {
            return returnOne(ex);
        }
    }

    private static String returnZero() {
        System.out.println("0");
        return "0";
    }

    private static String returnOne(Exception ex) {
        System.out.println("1");
        //System.out.printf("%s\n", ex.getMessage());
        return "1";
    }

    private static String returnTwo(IOException ex) {
        System.out.println("2");
        //System.out.printf("%s\n", ex.getMessage());
        return "2";
    }
}
