package oop.ex6.fileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;


public class FileToLines {

    /**
     * Returns an ArrayList of non-empty lines from a given file.
     * @param fileName the name of the file to read
     * @return an ArrayList of non-empty lines
     * @throws IOException if there is an error reading the file
     */
    public ArrayList<String> createNonEmptyLineArray(String fileName) throws IOException {

        ArrayList<String> linesArray = new ArrayList<>();

        try (BufferedReader reader = getBufferedReader(fileName)) {

            String line;
            while ((line = reader.readLine()) != null) {
                // if not an empty line
                if (!line.matches("\\s*")) {
                    linesArray.add(line);
                }
            }
        }
        return linesArray;
    }


    /**
     * Returns a BufferedReader for a given file.
     * @param fileName the name of the file to read
     * @return a BufferedReader for the file
     * @throws FileNotFoundException if the file is not found
     */
    private BufferedReader getBufferedReader(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        return new BufferedReader(new FileReader(file));
    }
}
