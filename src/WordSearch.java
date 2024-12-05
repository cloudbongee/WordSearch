

import java.io.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

/**
 * The WordSearch class implements the functionalities of the classes within the WordSearch project,
 * it scans a file and outputs one to create a collection of randomized word-search puzzles
 * @author Jaime Meyer Beilis Michel
 * @since november 22, 2024, Fall 2024 semester at the University of arizona
 *
 * The user needs to utilize this by adding a filename argument when calling this java class.
 * Such that the input in a terminal would be:
 * java WordSearch.java /path/to/file.txt
 */
public class WordSearch {
    public static void main(String[] args) throws IOException { // main engine of the java, read file, act from there
        String filename = args[0];
        Grid grid  = readFile(filename);
        game(grid, true); // the false corresponds to the debug boolean, which prints out the answer key.
    }


    // readFile takes the input from the argument passed and utilizes the classes to represent in an output file
    public static Grid readFile(String filename) throws IOException {
        File newFile = new File(filename);
        Scanner readFile = new Scanner(newFile);

        String[] lengths = readFile.nextLine().split(" ");
        // integers and columns are supposed to be switched around
        Grid grid = new Grid(Integer.parseInt(lengths[1]), Integer.parseInt(lengths[0]));

        Random rand = new Random();

        // set an initial three directions, just forcing some variability on the grid
        for(int i = 0; i < 3; i++ ){
            String line = readFile.nextLine().trim().toUpperCase();
            if(!grid.getWordList().containsKey(line)){
                grid.addToWordlist(line);
                grid.add(line, i);
            }
        }

        // iterate the rest of the words
        while(readFile.hasNextLine()) {
            String line = readFile.nextLine();
            if(!grid.getWordList().containsKey(line)){
                line = line.toUpperCase();
                grid.addToWordlist(line);
                grid.add(line, rand.nextInt(3));
            }
        }
        grid.fillEmpty();
        // pour information into another file
        FileWriter fileWriter = new FileWriter("output_" + filename);
        fileWriter.write(grid.toString());
        fileWriter.close();

        return grid;
    }

    // I gotta say that I am pretty happy at how efficient making a word class turned out to be.

    /**
     * The game method implements the logic for playing a Word Search game
     * @param grid The grid class which will be updated per word guess
     * @param debug boolean, when true the answer key is revealed before the game starts. So it's faster to play
     */
    public static void game(Grid grid, boolean debug){
        if(debug) {
            // printing the answer key for easier testing. This is obviously not part of the game
            for (Word word : grid.getWordList().values()) {
                System.out.println(
                        word.getValue() + ": " + word.getRow() + " , " + word.getCol() + ", " + word.getDirection());
            }}

        // read the terminal input to play the game
        Scanner readInput = new Scanner(System.in);
        while(!grid.gameFinished()){ // play the game until all the words have been found, runs a for loop on all words
            // game sequence
            System.out.println(grid.toString());
            System.out.print("Enter word found: "); String word = readInput.nextLine().trim().toUpperCase(); // word

            System.out.print("\nEnter x: ");  int x = Integer.parseInt(readInput.nextLine()); // coordinates of word
            System.out.print("\nEnter y: ");  int y = Integer.parseInt(readInput.nextLine());

            System.out.print("\n[H]orizontal [V]ertical [D]iagonal? "); String direction = readInput.nextLine().toUpperCase(); // direction

            // match method intermediates the actions of matching (such as drawing the asterisks), but returns bool
            if(!grid.match(word, x, y, direction)) System.out.println("\n" + word + " not found");
            else System.out.println("\n" + word + " removed");
        }

        // print when finished. Exit
        System.out.println("\nAll words found!");
    }
}