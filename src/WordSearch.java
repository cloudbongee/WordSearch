import java.io.*;
import java.util.Scanner;

public class WordSearch {
    public static void main(String[] args) throws IOException {
        String filename = args[0];
        readFile(filename);
    }

    public static void readFile(String filename) throws IOException {
        File newFile = new File(filename);
        Scanner readFile = new Scanner(newFile);

        String[] lengths = readFile.nextLine().split(" ");
        // integers and columns are supposed to be switched around
        Grid grid = new Grid(Integer.parseInt(lengths[1]), Integer.parseInt(lengths[0]));

        while(readFile.hasNextLine()) {
            String line = readFile.nextLine();
            line = line.toUpperCase();
            grid.add(line);
        }
        grid.fillEmpty();

        FileWriter fileWriter = new FileWriter("output_" + filename);
        fileWriter.write(grid.toString());
        fileWriter.close();
    }



}