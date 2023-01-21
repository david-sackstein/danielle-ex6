package oop.ex6.SyntaxAnalysis;
import java.io.*;
import java.util.ArrayList;

public class LinesReader {
    public ArrayList<String> getLines(String fileName) throws IOException {
        var lines = new ArrayList<String>();

        try(BufferedReader bufferedReader = getBufferedReader(fileName)){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (isWhiteSpaceLine(line)){
                    continue;
                }
                lines.add(line);
            }
            return lines;
        }
    }

    private BufferedReader getBufferedReader(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);
        return new BufferedReader(fileReader);
    }

    private static boolean isWhiteSpaceLine(String line) {
        return line.matches("\\s*");
    }
}
