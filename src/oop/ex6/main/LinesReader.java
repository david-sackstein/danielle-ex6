package oop.ex6.main;
import java.io.*;
import java.util.ArrayList;

/**
 * LinesReader takes the name of a file and returns all non-empty lines in the file in an ArrayList<String>
 */
public class LinesReader {
    public ArrayList<String> getLines(String fileName) throws IOException {
        ArrayList<String> lines = new ArrayList<String>();

        BufferedReader bufferedReader = getBufferedReader(fileName);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (isWhiteSpaceLine(line)){
                continue;
            }
            lines.add(line);
        }

        bufferedReader.close();
        return lines;
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
