package oop.ex6.main;

import oop.ex6.syntax.Compiler;
import oop.ex6.fileReader.FileToLines;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Sjavac {

    public static void main(String[] args){
        try {
            String fileName = args[0];

            FileToLines linesReader = new FileToLines();
            ArrayList<String> lines =  linesReader.createNonEmptyLineArray(fileName);

            for (int i = 1; i < 20; i++) {
                RunLineTest(lines.get(i - 1));
            }
            System.out.println("PASSED");

        } catch (Exception ex) {
            System.out.printf("FAILED : %s\n", ex.getMessage());
        }
    }

//    public static void main(String[] args){
//        String fileName = args[0];
//        System.out.println(RunTest(fileName));
//    }


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
