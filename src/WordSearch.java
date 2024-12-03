

import java.io.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

/**
 * The WordSearch class implements the functionalities of the classes within the WordSearch project,
 * it scans a file and outputs one to create a collection of randomized wordsearch puzzles
 * @author Jaime Meyer Beilis Michel
 * @since november 22 2024, Fall 2024 semester at the University of arizona
 *
 * The user needs to utilize this by adding a filename argument when calling this java class.
 * Such that the input in a terminal would be:
 * java WordSearch.java /path/to/file.txt
 */
public class WordSearch {
    public static void main(String[] args) throws IOException { // main engine of the java, read file, act from there
        String filename = args[0];
        Grid grid  = readFile(filename);
    }


    // readFile takes the input from the argument passed and utilizes the classes to represent in an output file
    public static Grid readFile(String filename) throws IOException {
        File newFile = new File(filename);
        Scanner readFile = new Scanner(newFile);

        String[] lengths = readFile.nextLine().split(" ");
        // integers and columns are supposed to be switched around
        Grid grid = new Grid(Integer.parseInt(lengths[1]), Integer.parseInt(lengths[0]));

        Random rand = new Random();

        // set an initial three directions
        for(int i = 0; i < 3; i++ ){
            String line = readFile.nextLine().trim().toUpperCase();
            if(!grid.getWordList().containsKey(line)){
                grid.add(line, i);
                grid.addToWordlist(line);
            }
        }

        // iterate the rest of the words
        while(readFile.hasNextLine()) {
            String line = readFile.nextLine();
            if(!grid.getWordList().containsKey(line)){
                line = line.toUpperCase();
                grid.add(line, rand.nextInt(3));
                grid.addToWordlist(line);
            }
        }
        grid.fillEmpty();
        // pour information into another file
        FileWriter fileWriter = new FileWriter("output_" + filename);
        fileWriter.write(grid.toString());
        fileWriter.close();

        return grid;
    }

    public static void game(Grid grid){
        Scanner readInput = new Scanner(System.in);
        while(!grid.gameFinished()){
            System.out.println(grid.toString());
            System.out.print("Enter word found: ");
            String word = readInput.nextLine().toUpperCase();
            System.out.print("\nEnter x: ");
            int x = Integer.parseInt(readInput.nextLine());
            System.out.print("\nEnter y: ");
            int y = Integer.parseInt(readInput.nextLine());
            System.out.print("\n[H]orizontal [V]ertical [D]iagonal?");
        }
        System.out.println("\nAll words found!");
    }



}